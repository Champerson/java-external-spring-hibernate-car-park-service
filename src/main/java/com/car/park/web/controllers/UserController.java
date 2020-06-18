package com.car.park.web.controllers;

import com.car.park.entities.User;
import com.car.park.entities.UserRole;
import com.car.park.entities.dtos.UserDto;
import com.car.park.service.AssignmentService;
import com.car.park.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

import static com.car.park.entities.UserRole.ROLE_ADMIN;
import static com.car.park.entities.UserRole.ROLE_DRIVER;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static final String USER_VALIDATION_RESULT_ATTRIBUTE = "org.springframework.validation.BindingResult.userForm";
    private static final String USER_PASSWORD_VALIDATION_RESULT_ATTRIBUTE = "org.springframework.validation.BindingResult.userPasswordForm";

    private UserService userService;
    private AssignmentService assignmentService;

    @GetMapping(value = "/authorize")
    public RedirectView authorize(Principal principal) {
        User user = userService.getUserByLogin(principal.getName());
        if (ROLE_DRIVER.equals(user.getAccessRole())) {
            return redirectWithUrl("/user/office");
        } else if (ROLE_ADMIN.equals(user.getAccessRole())) {
            return redirectWithUrl("/user/admin/office");
        } else {
            return redirectWithUrl("/login");
        }
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        if (!model.containsAttribute("userForm")) {
            model.addAttribute("userForm", new UserDto());
        }
        return "registration-page";
    }

    @PostMapping(value = "/create")
    public RedirectView create(
            @Validated({UserDto.ValidationCreate.class, UserDto.ValidationUpdate.class}) UserDto userForm,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("userForm", userForm);
            redirectAttributes.addFlashAttribute(USER_VALIDATION_RESULT_ATTRIBUTE, result);
            return redirectWithUrl("/user/registration");
        } else {
            userService.registerNewUser(userForm);
            redirectAttributes.addFlashAttribute("successMessage", "success.registration.completed");
            return redirectWithUrl("/login");
        }
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam Long userId, RedirectAttributes redirectAttributes) {
        userService.deleteUser(userId);
        redirectAttributes.addFlashAttribute("successMessage", "success.user.deleted");
        return "redirect:users";
    }

    @PostMapping(value = "/edit")
    public String edit(
            @Validated(UserDto.ValidationUpdate.class) UserDto userForm,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("userForm", userForm);
            redirectAttributes.addFlashAttribute(USER_VALIDATION_RESULT_ATTRIBUTE, result);
        } else {
            userService.updateUser(userForm);
            redirectAttributes.addFlashAttribute("successMessage", "success.user.updated");
        }
        return "redirect:office";
    }

    @PostMapping(value = "/password/edit")
    public RedirectView editPassword(
            @Validated(UserDto.ValidationPasswordUpdate.class) UserDto userPasswordForm,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("userPasswordForm", userPasswordForm);
            redirectAttributes.addFlashAttribute(USER_PASSWORD_VALIDATION_RESULT_ATTRIBUTE, result);
        } else {
            userService.updateUserPassword(userPasswordForm);
            redirectAttributes.addFlashAttribute("successMessage", "success.user.password.changed");
        }
        return redirectWithUrl("/user/office");
    }

    @PostMapping(value = "/role/edit")
    public RedirectView editRole(
            @RequestParam Long userId,
            @RequestParam(value = "accessRole") UserRole userRole,
            RedirectAttributes redirectAttributes
    ) {
        userService.updateUserAccessRole(userId, userRole);
        redirectAttributes.addFlashAttribute("successMessage", "success.user.role.changed");
        return redirectWithUrl("/user/details/" + userId);
    }

    @GetMapping(value = "/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin-user-all-page";
    }

    @GetMapping(value = "/drivers/available/{assignmentId}")
    public String getAvailableDrivers(@PathVariable Long assignmentId, Model model) {
        model.addAttribute("assignmentId", assignmentId);
        model.addAttribute("users", userService.getDriversAvailableForAssignment());
        return "admin-route-assign-driver-page";
    }

    @GetMapping(value = "/details/{userId}")
    public String getUserDetails(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        return "admin-user-details-page";
    }

    @GetMapping(value = "/office")
    public String getUserOffice(Principal principal, Model model) {
        User user = userService.getUserByLogin(principal.getName());
        model.addAttribute("user", user);
        if (!model.containsAttribute("userForm")) {
            model.addAttribute("userForm", user);
        }
        if (!model.containsAttribute("userPasswordForm")) {
            model.addAttribute("userPasswordForm", new UserDto());
        }
        return "user-office-page";
    }

    @GetMapping(value = "/admin/office")
    public String getAdminOffice() {
        return "admin-menu-page";
    }

    @PostMapping(value = "/assignment/accept")
    public RedirectView acceptAssignmentByUser(Principal principal, RedirectAttributes redirectAttributes) {
        User user = userService.getUserByLogin(principal.getName());
        assignmentService.acceptAssignmentByUser(user);
        redirectAttributes.addFlashAttribute("successMessage", "success.user.assignment.accepted");
        return redirectWithUrl("/user/office");
    }

    @PostMapping(value = "/assignment/decline")
    public RedirectView declineAssignmentByUser(Principal principal, RedirectAttributes redirectAttributes) {
        User user = userService.getUserByLogin(principal.getName());
        assignmentService.declineUserAssignment(user);
        redirectAttributes.addFlashAttribute("successMessage", "success.user.assignment.declined");
        return redirectWithUrl("/user/office");
    }

    @PostMapping(value = "/assignment/admin/decline")
    public RedirectView declineAssignmentByAdmin(@RequestParam Long userId, RedirectAttributes redirectAttributes) {
        User user = userService.getUserById(userId);
        assignmentService.declineUserAssignment(user);
        redirectAttributes.addFlashAttribute("successMessage", "success.user.assignment.declined");
        return redirectWithUrl("/user/details/" + userId);
    }

    private RedirectView redirectWithUrl(String url) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl(url);
        return redirectView;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAssignmentService(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }
}

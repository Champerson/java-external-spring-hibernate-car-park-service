package com.car.park.web;

import com.car.park.entities.User;
import com.car.park.entities.UserRole;
import com.car.park.service.AssignmentService;
import com.car.park.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static final String INDEX_PAGE = "index";
    private static final String ADMIN_MENU_PAGE = "admin-menu-page";
    private static final String USER_OFFICE_PAGE = "user-office-page";
    private static final String ALL_USERS_PAGE = "admin-user-all-page";
    private static final String REGISTRATION_PAGE = "registration-page";
    private static final String USER_INFO_PAGE = "admin-user-details-page";
    private static final String UNASSIGNED_DRIVERS_PAGE = "admin-route-assign-driver-page";

    private UserService userService;
    private AssignmentService assignmentService;

    @GetMapping(value = "/authorize")
    public String authorize(Principal principal, Model model) {
        User user = userService.getUserByLogin(principal.getName());
        if (UserRole.ROLE_DRIVER.equals(user.getAccessRole())) {
            model.addAttribute("user", user);
            model.addAttribute("userForm", user);
            return USER_OFFICE_PAGE;
        } else if (UserRole.ROLE_ADMIN.equals(user.getAccessRole())) {
            return ADMIN_MENU_PAGE;
        } else {
            return INDEX_PAGE;
        }
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return REGISTRATION_PAGE;
    }

    @PostMapping(value = "/create")
    public String create(@Validated({User.ValidationBasic.class, User.ValidationAdditional.class}) User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return REGISTRATION_PAGE;
        } else {
            userService.registerNewUser(user);
            model.addAttribute("successMessage", "success.registration.completed");
            return INDEX_PAGE;
        }
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam Long userId, Model model) {
        userService.deleteUser(userId);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("successMessage", "success.user.deleted");
        return ALL_USERS_PAGE;
    }

    @PostMapping(value = "/edit")
    public String edit(
            @Validated(User.ValidationAdditional.class) @ModelAttribute("userForm") User userForm,
            BindingResult result,
            Model model
    ) {
        if (!result.hasErrors()) {
            userService.updateUser(userForm);
            model.addAttribute("successMessage", "success.user.updated");
        }
        model.addAttribute("user", userService.getUserById(userForm.getId()));
        return USER_OFFICE_PAGE;
    }

    @PostMapping(value = "/role/edit")
    public String editRole(
            @RequestParam Long userId,
            @RequestParam(value = "accessRole") UserRole userRole,
            Model model
    ) {
        User user = userService.updateUserAccessRole(userId, userRole);
        model.addAttribute("user", user);
        model.addAttribute("successMessage", "success.user.role.changed");
        return USER_INFO_PAGE;
    }

    @GetMapping(value = "/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return ALL_USERS_PAGE;
    }

    @GetMapping(value = "/drivers/available")
    public String getAvailableDrivers(@RequestParam Long assignmentId, Model model) {
        model.addAttribute("assignmentId", assignmentId);
        model.addAttribute("users", userService.getDriversAvailableForAssignment());
        return UNASSIGNED_DRIVERS_PAGE;
    }

    @GetMapping(value = "/details")
    public String getUserDetails(@RequestParam Long userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        return USER_INFO_PAGE;
    }

    @GetMapping(value = "/office")
    public String getUserOffice(Principal principal, Model model) {
        User user = userService.getUserByLogin(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("userForm", user);
        return USER_OFFICE_PAGE;
    }

    @PostMapping(value = "/assignment/accept")
    public String acceptAssignmentByUser(Principal principal, Model model) {
        User user = userService.getUserByLogin(principal.getName());
        assignmentService.acceptAssignmentByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("userForm", user);
        model.addAttribute("successMessage", "success.user.assignment.accepted");
        return USER_OFFICE_PAGE;
    }

    @PostMapping(value = "/assignment/decline")
    public String declineAssignmentByUser(Principal principal, Model model) {
        User user = userService.getUserByLogin(principal.getName());
        assignmentService.declineUserAssignment(user);
        model.addAttribute("user", user);
        model.addAttribute("userForm", user);
        model.addAttribute("successMessage", "success.user.assignment.declined");
        return USER_OFFICE_PAGE;
    }

    @PostMapping(value = "/assignment/admin/decline")
    public String declineAssignmentByAdmin(@RequestParam Long userId, Model model) {
        User user = userService.getUserById(userId);
        assignmentService.declineUserAssignment(user);
        model.addAttribute("user", user);
        model.addAttribute("successMessage", "success.user.assignment.declined");
        return USER_INFO_PAGE;
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

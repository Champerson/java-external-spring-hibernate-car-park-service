package com.car.park.web.controllers;

import com.car.park.entities.Assignment;
import com.car.park.entities.Route;
import com.car.park.service.AssignmentService;
import com.car.park.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/route")
public class RouteController {

    private static final String VALIDATION_RESULT_ATTRIBUTE = "org.springframework.validation.BindingResult.routeForm";

    private RouteService routeService;
    private AssignmentService assignmentService;

    @GetMapping(value = "/create")
    public String create(Model model) {
        if (!model.containsAttribute("routeForm")) {
            model.addAttribute("routeForm", new Route());
        }
        return "admin-route-create-page";
    }

    @PostMapping(value = "/create")
    public String create(
            @Valid Route routeForm,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("routeForm", routeForm);
            redirectAttributes.addFlashAttribute(VALIDATION_RESULT_ATTRIBUTE, result);
            return "redirect:create";
        } else {
            routeService.createNewRoute(routeForm);
            redirectAttributes.addFlashAttribute("successMessage", "success.route.created");
            return "redirect:routes";
        }
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam Long routeId, RedirectAttributes redirectAttributes) {
        routeService.deleteRoute(routeId);
        redirectAttributes.addFlashAttribute("successMessage", "success.route.deleted");
        return "redirect:routes";
    }

    @PostMapping(value = "/edit")
    public String edit(
            @Valid Route routeForm,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("routeForm", routeForm);
            redirectAttributes.addFlashAttribute(VALIDATION_RESULT_ATTRIBUTE, result);
        } else {
            routeService.updateRoute(routeForm);
            redirectAttributes.addFlashAttribute("successMessage", "success.route.updated");
        }
        return "redirect:details/" + routeForm.getId();
    }

    @GetMapping(value = "/routes")
    public String getAllRoutes(Model model) {
        model.addAttribute("routes", routeService.getAllRoutes());
        return "admin-route-all-page";
    }

    @GetMapping(value = "/details/{routeId}")
    public String getRouteDetails(@PathVariable Long routeId, Model model) {
        Route route = routeService.getRouteById(routeId);
        model.addAttribute("route", route);
        if (!model.containsAttribute("routeForm")) {
            model.addAttribute("routeForm", route);
        }
        return "admin-route-details-page";
    }

    @PostMapping(value = "/assign/bus")
    public RedirectView createBusAssignment(
            @RequestParam Long routeId,
            @RequestParam Long busId,
            RedirectAttributes redirectAttributes
    ) {
        assignmentService.assignBusToRoute(routeId, busId);
        redirectAttributes.addFlashAttribute("successMessage", "success.route.assignment.created");
        return redirectWithUrl("/route/details/" + routeId);
    }

    @PostMapping(value = "/assign/user")
    public RedirectView createDriverAssignment(@RequestParam Long userId, @RequestParam Long assignmentId) {
        Long routeId = assignmentService.getAssignmentById(assignmentId).getRoute().getId();
        assignmentService.assignDriverToBus(userId, assignmentId);
        return redirectWithUrl("/route/details/" + routeId);
    }

    @PostMapping(value = "/assignment/decline")
    public RedirectView declineUserAssignment(@RequestParam Long assignmentId, RedirectAttributes redirectAttributes) {
        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        assignmentService.declineUserAssignment(assignment.getDriver());
        redirectAttributes.addFlashAttribute("successMessage", "success.route.assignment.deleted");
        return redirectWithUrl("/route/details/" + assignment.getRoute().getId());
    }

    @PostMapping(value = "/assignment/delete")
    public RedirectView deleteAssignment(@RequestParam Long assignmentId, RedirectAttributes redirectAttributes) {
        Long routeId = assignmentService.getAssignmentById(assignmentId).getRoute().getId();
        assignmentService.deleteAssignmentFromRoute(assignmentId);
        redirectAttributes.addFlashAttribute("successMessage", "success.route.assignment.deleted");
        return redirectWithUrl("/route/details/" + routeId);
    }

    private RedirectView redirectWithUrl(String url) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl(url);
        return redirectView;
    }

    @Autowired
    public void setRouteService(RouteService routeService) {
        this.routeService = routeService;
    }

    @Autowired
    public void setAssignmentService(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }
}

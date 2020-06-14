package com.car.park.web;

import com.car.park.entities.Assignment;
import com.car.park.entities.Route;
import com.car.park.service.AssignmentService;
import com.car.park.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/route")
public class RouteController {

    private static final String ALL_ROUTES_PAGE = "admin-route-all-page";
    private static final String NEW_ROUTE_PAGE = "admin-route-create-page";
    private static final String ROUTE_INFO_PAGE = "admin-route-details-page";

    private RouteService routeService;
    private AssignmentService assignmentService;

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("routeForm", new Route());
        return NEW_ROUTE_PAGE;
    }

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute("routeForm") Route routeForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return NEW_ROUTE_PAGE;
        } else {
            routeService.createNewRoute(routeForm);
            model.addAttribute("routes", routeService.getAllRoutes());
            model.addAttribute("successMessage", "success.route.created");
            return ALL_ROUTES_PAGE;
        }
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam Long routeId, Model model) {
        routeService.deleteRoute(routeId);
        model.addAttribute("routes", routeService.getAllRoutes());
        model.addAttribute("successMessage", "success.route.deleted");
        return ALL_ROUTES_PAGE;
    }

    @PostMapping(value = "/edit")
    public String edit(
            @Valid @ModelAttribute("routeForm") Route routeForm,
            BindingResult result,
            Model model
    ) {
        if (!result.hasErrors()) {
            routeService.updateRoute(routeForm);
            model.addAttribute("successMessage", "success.route.updated");
        }
        model.addAttribute("route", routeService.getRouteById(routeForm.getId()));
        return ROUTE_INFO_PAGE;
    }

    @GetMapping(value = "/routes")
    public String getAllRoutes(Model model) {
        model.addAttribute("routes", routeService.getAllRoutes());
        return ALL_ROUTES_PAGE;
    }

    @GetMapping(value = "/details")
    public String getRouteDetails(@RequestParam Long routeId, Model model) {
        Route route = routeService.getRouteById(routeId);
        model.addAttribute("route", route);
        model.addAttribute("routeForm", route);
        return ROUTE_INFO_PAGE;
    }

    @PostMapping(value = "/assign/bus")
    public String createBusAssignment(@RequestParam Long routeId, @RequestParam Long busId, Model model) {
        assignmentService.assignBusToRoute(routeId, busId);
        Route route = routeService.getRouteById(routeId);
        model.addAttribute("route", route);
        model.addAttribute("routeForm", route);
        model.addAttribute("successMessage", "success.route.assignment.created");
        return ROUTE_INFO_PAGE;
    }

    @PostMapping(value = "/assign/user")
    public String createDriverAssignment(@RequestParam Long userId, @RequestParam Long assignmentId, Model model) {
        assignmentService.assignDriverToBus(userId, assignmentId);
        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        model.addAttribute("route", assignment.getRoute());
        model.addAttribute("routeForm", assignment.getRoute());
        return ROUTE_INFO_PAGE;
    }

    @PostMapping(value = "/assignment/decline")
    public String declineUserAssignment(@RequestParam Long assignmentId, Model model) {
        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        assignmentService.declineUserAssignment(assignment.getDriver());
        Route route = routeService.getRouteById(assignment.getRoute().getId());
        model.addAttribute("route", route);
        model.addAttribute("routeForm", route);
        model.addAttribute("successMessage", "success.route.assignment.deleted");
        return ROUTE_INFO_PAGE;
    }

    @PostMapping(value = "/assignment/delete")
    public String deleteAssignment(@RequestParam Long assignmentId, Model model) {
        Long routeId = assignmentService.getAssignmentById(assignmentId).getRoute().getId();
        assignmentService.deleteAssignmentFromRoute(assignmentId);
        Route route = routeService.getRouteById(routeId);
        model.addAttribute("route", route);
        model.addAttribute("routeForm", route);
        model.addAttribute("successMessage", "success.route.assignment.deleted");
        return ROUTE_INFO_PAGE;
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

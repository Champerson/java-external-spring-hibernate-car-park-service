package com.car.park.web.controllers;

import com.car.park.entities.Bus;
import com.car.park.entities.dtos.BusDto;
import com.car.park.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Web controller for handling requests related with bus
 * @see BusService
 */
@Controller
@RequestMapping(value = "/bus")
public class BusController {

    private static final String VALIDATION_RESULT_ATTRIBUTE = "org.springframework.validation.BindingResult.busForm";

    private BusService busService;

    @GetMapping(value = "/create")
    public String create(Model model) {
        if (!model.containsAttribute("busForm")) {
            model.addAttribute("busForm", new BusDto());
        }
        return "admin-bus-create-page";
    }

    @PostMapping(value = "/create")
    public String create(
            @Valid BusDto busForm,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("busForm", busForm);
            redirectAttributes.addFlashAttribute(VALIDATION_RESULT_ATTRIBUTE, result);
            return "redirect:create";
        } else {
            busService.createNewBus(busForm);
            redirectAttributes.addFlashAttribute("successMessage", "success.bus.created");
            return "redirect:buses";
        }
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam Long busId, RedirectAttributes redirectAttributes) {
        busService.deleteBus(busId);
        redirectAttributes.addFlashAttribute("successMessage", "success.bus.deleted");
        return "redirect:buses";
    }

    @PostMapping(value = "/edit")
    public String edit(
            @Valid BusDto busForm,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("busForm", busForm);
            redirectAttributes.addFlashAttribute(VALIDATION_RESULT_ATTRIBUTE, result);
        } else {
            busService.updateBus(busForm);
            redirectAttributes.addFlashAttribute("successMessage", "success.bus.updated");
        }
        return "redirect:details/" + busForm.getId();
    }

    @GetMapping(value = "/buses")
    public String getAllBuses(Model model) {
        model.addAttribute("buses", busService.getAllBuses());
        return "admin-bus-all-page";
    }

    @GetMapping(value = "/details/{busId}")
    public String getBusDetails(@PathVariable Long busId, Model model) {
        Bus bus = busService.getBusById(busId);
        model.addAttribute("bus", bus);
        if (!model.containsAttribute("busForm")) {
            model.addAttribute("busForm", bus);
        }
        return "admin-bus-details-page";
    }

    @GetMapping(value = "/buses/available/{routeId}")
    public String getAvailableBuses(@PathVariable Long routeId, Model model) {
        model.addAttribute("routeId", routeId);
        model.addAttribute("buses", busService.getBusesAvailableForAssignment());
        return "admin-route-assign-bus-page";
    }

    @Autowired
    public void setBusService(BusService busService) {
        this.busService = busService;
    }
}

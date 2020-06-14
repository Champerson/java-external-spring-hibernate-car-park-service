package com.car.park.web;

import com.car.park.entities.Bus;
import com.car.park.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/bus")
public class BusController {

    private static final String ALL_BUSES_PAGE = "admin-bus-all-page";
    private static final String NEW_BUS_PAGE = "admin-bus-create-page";
    private static final String BUS_INFO_PAGE = "admin-bus-details-page";
    private static final String UNASSIGNED_BUSES_PAGE = "admin-route-assign-bus-page";

    private BusService busService;

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("busForm", new Bus());
        return NEW_BUS_PAGE;
    }

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute("busForm") Bus busForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return NEW_BUS_PAGE;
        } else {
            busService.createNewBus(busForm);
            model.addAttribute("buses", busService.getAllBuses());
            model.addAttribute("successMessage", "success.bus.created");
            return ALL_BUSES_PAGE;
        }
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam Long busId, Model model) {
        busService.deleteBus(busId);
        model.addAttribute("buses", busService.getAllBuses());
        model.addAttribute("successMessage", "success.bus.deleted");
        return ALL_BUSES_PAGE;
    }

    @PostMapping(value = "/edit")
    public String edit(
            @Valid @ModelAttribute("busForm") Bus busForm,
            BindingResult result,
            Model model
    ) {
        if (!result.hasErrors()) {
            busService.updateBus(busForm);
            model.addAttribute("successMessage", "success.bus.updated");
        }
        model.addAttribute("bus", busService.getBusById(busForm.getId()));
        return BUS_INFO_PAGE;
    }

    @GetMapping(value = "/buses")
    public String getAllBuses(Model model) {
        model.addAttribute("buses", busService.getAllBuses());
        return ALL_BUSES_PAGE;
    }

    @GetMapping(value = "/details")
    public String getBusDetails(@RequestParam Long busId, Model model) {
        Bus bus = busService.getBusById(busId);
        model.addAttribute("bus", bus);
        model.addAttribute("busForm", bus);
        return BUS_INFO_PAGE;
    }

    @GetMapping(value = "/buses/available")
    public String getAvailableBuses(@RequestParam Long routeId, Model model) {
        model.addAttribute("routeId", routeId);
        model.addAttribute("buses", busService.getBusesAvailableForAssignment());
        return UNASSIGNED_BUSES_PAGE;
    }

    @Autowired
    public void setBusService(BusService busService) {
        this.busService = busService;
    }
}

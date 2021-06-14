package com.project.task.controller;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.project.task.dao.DaoCountry;
import com.project.task.dao.DaoRooms;
import com.project.task.models.Room;
import com.project.task.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/rooms")
public class MainController {
    public final DaoRooms daoRooms;
    public final DaoCountry daoCountry;
    @Autowired
    public MainController(DaoRooms daoRooms, RequestService requestService, DaoCountry daoCountry) { this.daoRooms = daoRooms;
        this.requestService = requestService; this.daoCountry=daoCountry;
    }
    private final RequestService requestService;
//    @RequestMapping("/")
//    public ModelAndView index(HttpServletRequest request) {
//        ModelAndView model = new ModelAndView("index");
//        String clientIp = requestService.getClientIp(request);
//        model.addObject("clientIp", clientIp);
//        return model;
//    }

    @GetMapping()
    public String rooms(Model model){
        model.addAttribute("rooms",  daoRooms.showRooms());
        return "show";
    }

    @RequestMapping("/{id}")
    public String showRoom(@PathVariable("id")int id, Model model, HttpServletRequest request) throws IOException, GeoIp2Exception {
        if(daoRooms.checkCountry("2.135.36.148", id)==true){
            model.addAttribute("room", daoRooms.showID(id));
            return "room";
        }
        else {
            return "oops";
        }

    }
    @GetMapping("/new")
    public String newRoom(Model model, @ModelAttribute("room") Room room) {
        model.addAttribute("countryList", daoCountry.showCountry() );
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("room")  Room room) {
        daoRooms.save(room);
        return "redirect:/rooms";
    }
    @RequestMapping("/ip")
    public String index(HttpServletRequest request, Model model) {

        String clientIp = requestService.getClientIp(request);
        model.addAttribute("clientIp", clientIp);
        return "ip";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") String id ) {
        daoRooms.changeState(id);
        return "state";
    }
//    @PutMapping("/{id}")
//    public Room update(@PathVariable int id, @RequestBody Room message) {
//
//
//        messageFromDb.putAll(message);
//        messageFromDb.put("id", id);
//
//        return messageFromDb;
//    }
//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("person") Room room, @PathVariable("id") int id) {
//        daoRooms.update(id, room);
//        return "redirect:/people";
//    }



}

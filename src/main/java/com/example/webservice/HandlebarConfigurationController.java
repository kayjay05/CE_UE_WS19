package com.example.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HandlebarConfigurationController {

    private String handlebar, material, gearshift, handle;
    private RestTemplate restTemplate = new RestTemplate();
    private static final Logger log = LoggerFactory.getLogger(WebserviceApplication.class);

    @RequestMapping(value = "/handlebar", method = {RequestMethod.GET, RequestMethod.POST})
    public String pickHandlebar(HttpServletRequest request, Model model) {
        String handlebars = restTemplate.getForObject("https://www.maripavi.at/produkt/lenkertyp", String.class);
        handlebar = request.getParameter("handlebar");
        model.addAttribute("handlebars", handlebars);

        return "handlebar";
    }

    @RequestMapping(value = "/material", method = {RequestMethod.GET, RequestMethod.POST})
    public String pickMaterial(HttpServletRequest request, Model model) {
        String availableMaterials = restTemplate.getForObject("https://www.maripavi.at/produkt/material?lenkertyp=" + handlebar, String.class);
        material = request.getParameter("material");
        model.addAttribute("availableMaterials", availableMaterials);
        return "material";
    }

    @RequestMapping(value = "/gearshift", method = {RequestMethod.GET, RequestMethod.POST})
    public String pickGearlever(HttpServletRequest request, Model model) {
        String gearshifts = restTemplate.getForObject("https://www.maripavi.at/produkt/schaltung?lenkertyp=" + handlebar, String.class);
        gearshift = request.getParameter("gearshift");
        model.addAttribute("gearshifts", gearshifts);
        return "gearshift";
    }

    @RequestMapping(value = "/handle", method = {RequestMethod.GET, RequestMethod.POST})
    public String pickHandle(HttpServletRequest request, Model model) {
        String handles = restTemplate.getForObject("https://www.maripavi.at/produkt/griff?material=" + material, String.class);
        handle = request.getParameter("handle");
        model.addAttribute("handles", handles);

        return "handle";
    }

    @RequestMapping(value = "/order", method = {RequestMethod.GET, RequestMethod.POST})
    public String orderOverview(Model model) {
        boolean correct = checkOrderComponents();
        String order;

        if (correct) {
            order = "[Lenkertyp: " + handlebar + ", Material: " + material + ", Schaltung: " + gearshift + ", Griff: " + handle + "]";
        } else
            order = "Ups, es ist etwas schief gelaufen - Bitte Eingaben überprüfen!";
        log.info(order);
        model.addAttribute("order", order);

        return "order";
    }

    public boolean checkOrderComponents() {
        boolean correctInput = false;
        if ((handlebar != null) && (handlebar.toLowerCase().equals("flatbarlenker"))
                || (handlebar.toLowerCase().equals("rennradlenker"))
                || (handlebar.toLowerCase().equals("bullhornlenker")))
            correctInput = true;
        else
            return false;
        if ((material != null) && (material.toLowerCase().equals("aluminium"))
                || (material.toLowerCase().equals("carbon")) || (material.toLowerCase().equals("stahl")))
            correctInput = true;
        else
            return false;
        if ((gearshift != null) && (gearshift.toLowerCase().equals("kettenschaltung"))
                || (gearshift.toLowerCase().equals("nabenschaltung"))
                || (gearshift.toLowerCase().equals("tretlagerschaltung")))
            correctInput = true;
        else
            return false;
        if ((handle != null) && (handle.toLowerCase().equals("ledergriff"))
                || (handle.toLowerCase().equals("kunststoffgriff")) || (handle.toLowerCase().equals("korkgriff"))
                || (handle.toLowerCase().equals("schaumstoffgriff")))
            correctInput = true;
        else
            correctInput = false;

        return correctInput;

    }


}

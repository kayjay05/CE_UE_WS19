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

    private String handlebarType;
    private String material;


    private RestTemplate restTemplate = new RestTemplate();
    private static final Logger log = LoggerFactory.getLogger(WebserviceApplication.class);

    // get all available handlebarTypes and display handlebar.html
    @RequestMapping(value = "/handlebar", method = { RequestMethod.GET, RequestMethod.POST })
    public String chooseHandlebar(HttpServletRequest request, Model model) {
        String handlebars = restTemplate.getForObject("https://www.maripavi.at/produkt/lenkertyp", String.class);
        handlebarType = request.getParameter("handlebarType");
        model.addAttribute("handlebars", handlebars);

        return "handlebar";
    }

    // get all available materials and display them on material.html
    @RequestMapping(value = "/material", method = { RequestMethod.GET, RequestMethod.POST })
    public String chooseMaterial(HttpServletRequest request, Model model) {
        String availableMaterials = restTemplate.getForObject("https://www.maripavi.at/produkt/material?lenkertyp=" + handlebarType, String.class);
        material = request.getParameter("material");
        model.addAttribute("availableMaterials", availableMaterials);

        return "material";
    }
}

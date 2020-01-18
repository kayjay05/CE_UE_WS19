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

    private String handlebar;
    private String material;
    private String gearshift;
    private String handle;


    private RestTemplate restTemplate = new RestTemplate();
    private static final Logger log = LoggerFactory.getLogger(WebserviceApplication.class);

    // get all available handlebarTypes and display handlebar.html
    @RequestMapping(value = "/handlebar", method = { RequestMethod.GET, RequestMethod.POST })
    public String pickHandlebar(HttpServletRequest request, Model model) {
        String handlebars = restTemplate.getForObject("https://www.maripavi.at/produkt/lenkertyp", String.class);
        handlebar = request.getParameter("handlebar");
        model.addAttribute("handlebars", handlebars);

        return "handlebar";
    }

    // get all available materials and display them on material.html
    @RequestMapping(value = "/material", method = { RequestMethod.GET, RequestMethod.POST })
    public String pickMaterial(HttpServletRequest request, Model model) {
        String restLink = "https://www.maripavi.at/produkt/material?lenkertyp=" + handlebar;
        String availableMaterials = restTemplate.getForObject(restLink, String.class);
        //String availableMaterials = restTemplate.getForObject("https://www.maripavi.at/produkt/material?lenkertyp=" + handlebar, String.class);
        material = request.getParameter("material");
        model.addAttribute("availableMaterials", availableMaterials);

        return "material";
    }

    // get all available gearlevers and display them on gearlever.html
    @RequestMapping(value = "/gearshift", method = { RequestMethod.GET, RequestMethod.POST })
    public String chooseGearlever(HttpServletRequest request, Model model) {
        String gearshifts = restTemplate.getForObject("https://www.maripavi.at/produkt/schaltung?lenkertyp=" + handlebar, String.class);
        gearshift = request.getParameter("gearshift");
        model.addAttribute("gearshifts", gearshifts);

        return "gearshift";
    }

    // get all available handles and display them on handle.html
    @RequestMapping(value = "/handle", method = { RequestMethod.GET, RequestMethod.POST })
    public String pickHandle(HttpServletRequest request, Model model) {
        String handles = restTemplate.getForObject("https://www.maripavi.at/produkt/griff?material=" + material, String.class);
        handle = request.getParameter("handle");
        model.addAttribute("handles", handles);

        return "handle";
    }

    // display the whole order on order.html
    @RequestMapping(value = "/order", method = { RequestMethod.GET, RequestMethod.POST })
    public String showOrder(Model model) {
        boolean correct = checkOrderComponents();
        String order;
        String confirmation = "";

        if (correct) {
            order = "Bestellung [Lenkertyp: " + handlebar + ", Material: " + material + ", Schaltung: " + gearshift + ", Griff: " + handle +
                    "] erfolgreich erstellt!";
            // confirmation = "Vielen Dank für Ihre Bestellung!";
        } else
            order = "Falsche Bestelldaten bitte erneut eingeben";
        log.info(order);
        model.addAttribute("order", order);
        // model.addAttribute("confirmation", confirmation);

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

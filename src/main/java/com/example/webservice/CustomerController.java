package com.example.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(WebserviceApplication.class);

    @RequestMapping("/createCustomer")
    public String inputCust() {
        return "custinput";
    }


    @RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
    public String cust(HttpServletRequest request, Model model) {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String message = "User " + firstName + " " + lastName + " created";
        model.addAttribute("message", message);

        log.info(message);

        return "createCustomer";
    }
}


    /*
    @RequestMapping("/account")
    public String[] getAccounts(@RequestParam(value="name", defaultValue = "dV") String name) {

    }

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

     */


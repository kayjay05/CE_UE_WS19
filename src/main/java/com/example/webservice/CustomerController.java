package com.example.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(WebserviceApplication.class);

    @RequestMapping("/createCustomer")
    public String inputCustomer() {
        return "inputCustomer";
    }

    @RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
    public String customer(HttpServletRequest request, Model model) {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String message = "User " + firstName + " " + lastName + " has been created.";
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


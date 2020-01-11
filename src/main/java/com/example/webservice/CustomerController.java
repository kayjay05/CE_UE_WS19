package com.example.webservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CustomerController {

    public String [] accounts;

    @RequestMapping("/accounts")
    public String[] getAllAccounts() {
       return accounts;
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


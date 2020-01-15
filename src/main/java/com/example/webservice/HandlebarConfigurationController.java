package com.example.webservice;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HandlebarConfigurationController {
    public void TestMethod(@RequestParam(value="test", defaultValue="sample") String name){

    }


}

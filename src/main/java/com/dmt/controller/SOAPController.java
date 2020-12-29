package com.dmt.controller;

import com.dmt.bean.Calculator;
import com.dmt.soap.client.SOAPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/soap/invoke")
public class SOAPController {

    @Autowired
    private SOAPClient client;

    @PostMapping("/calculator")
    public Object invokeCalculator(@RequestBody Calculator request) {

        Object invocation = null;

        try {
            invocation = client.invokeSOAPOperation(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return invocation;
    }
}
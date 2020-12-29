package com.dmt.soap.client;

import com.dmt.bean.Calculator;
import com.dmt.exception.ResourceNotFoundException;
import com.dmt.soap.binding.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Service
public class SOAPClient {

    @Autowired
    private Jaxb2Marshaller marshaller;

    private WebServiceTemplate template;

    private String url = "http://www.dneonline.com/calculator.asmx";

    private String callback = "http://tempuri.org/";

    public Object invokeSOAPOperation(Calculator calculator) throws ResourceNotFoundException {

        template = new WebServiceTemplate(marshaller);
        Object response = null;
        String operation = calculator.getOperation();
        Integer a = calculator.getIntA();
        Integer b = calculator.getIntB();

        if (operation.equalsIgnoreCase("Add")) {
            Add request = new Add();
            request.setIntA(a);
            request.setIntB(b);

            response = (AddResponse) template.marshalSendAndReceive(url, (Add) request,
                    new SoapActionCallback(callback + "Add"));
        } else if (operation.equalsIgnoreCase("Subtract")) {
            Subtract request = new Subtract();
            request.setIntA(a);
            request.setIntB(b);

            response = (SubtractResponse) template.marshalSendAndReceive(url, request,
                    new SoapActionCallback(callback + "Subtract"));
        } else if (operation.equalsIgnoreCase("Multiply")) {
            Multiply request = new Multiply();
            request.setIntA(a);
            request.setIntB(b);

            response = (MultiplyResponse) template.marshalSendAndReceive(url, request,
                    new SoapActionCallback(callback + "Multiply"));
        } else if (operation.equalsIgnoreCase("Divide")) {
            Divide request = new Divide();
            request.setIntA(a);
            request.setIntB(b);

            response = (DivideResponse) template.marshalSendAndReceive(url, request,
                    new SoapActionCallback(callback + "Divide"));
        } else {
            throw new ResourceNotFoundException("Invalid Calculator Operation : " + operation);
        }

        return response;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.stark.invoicecontroller.controller;

import br.stark.invoicecontroller.dto.PushDto;
import br.stark.invoicecontroller.dto.PushEventAction;
import br.stark.invoicecontroller.service.AMQPPublisher;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author lucas
 */
public class PushController {

    private Gson gson = new Gson();

    @PostMapping("/push")
    public void index(@RequestBody String json) {

        System.out.println(new Date() + " " + json);

        PushDto dto = gson.fromJson(json, PushDto.class);

        var eventAction = dto.getEvent().getEventAction();

        var publisher = new AMQPPublisher();

        switch (eventAction) {
            
            case PushEventAction.INVOICE:
                
                this.sendInvoiceAction(json, publisher);
                
                break;
            
            default:
                break;
        }
        
        publisher.close();

    }

    private void sendInvoiceAction(String json, AMQPPublisher publisher) {
        try {
            publisher.sendToQueue("invoiced_payment", json);
        } catch (IOException ex) {
            Logger.getLogger(PushController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

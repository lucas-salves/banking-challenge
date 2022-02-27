/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.stark.invoicecontroller.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 *
 * @author lucas
 */
@Data
public class PushDto {
    
    @SerializedName("event")
    private Event event;
    
    
    
    @Data
    public class Event {
        
        @SerializedName("subscription")
        private String eventCode;
        
        @SerializedName("workspaceId")
        private String workspaceId;
    }
}

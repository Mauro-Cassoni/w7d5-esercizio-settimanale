package it.epicode.w7d5.esercizio.settimanale.request;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

import java.time.LocalDate;


@Data
public class EventRequest {

    @NotBlank(message = "title request")
    private String title;

    @NotBlank(message = "description request")
    private String description;

    @NotBlank(message = "date request")
    private LocalDate date;

    @NotBlank(message = "location request")
    private String location;

    @NotBlank(message = "maxUser request")
    private int maxUser;

}

package it.epicode.w7d5.esercizio.settimanale.request;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;


@Data
public class EventRequest {

    @NotBlank(message = "title request")
    private String title;

    @NotBlank(message = "description request")
    private String description;

    @NotNull(message = "date request")
    private LocalDate date;

    @NotBlank(message = "location request")
    private String location;

    @NotNull(message = "maxUser request")
    private int maxUser;

}

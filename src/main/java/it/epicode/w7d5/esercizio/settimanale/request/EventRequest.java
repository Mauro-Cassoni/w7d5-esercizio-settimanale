package it.epicode.w7d5.esercizio.settimanale.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.w7d5.esercizio.settimanale.model.User;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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

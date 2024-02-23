package it.epicode.w7d5.esercizio.settimanale.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.w7d5.esercizio.settimanale.enums.Role;
import it.epicode.w7d5.esercizio.settimanale.model.Event;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class UserRequest {

    @NotBlank(message = "name request")
    private String name;

    @NotBlank(message = "surname request")
    private String surname;

    @NotEmpty(message = "password request")
    @Email(message = "insert valid mail")
    private String mail;

    @NotBlank(message = "password request")
    private String password;
}

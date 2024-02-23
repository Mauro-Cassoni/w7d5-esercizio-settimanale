package it.epicode.w7d5.esercizio.settimanale.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "email request")
    private String email;
    @NotBlank(message = "password request")
    private String password;
}

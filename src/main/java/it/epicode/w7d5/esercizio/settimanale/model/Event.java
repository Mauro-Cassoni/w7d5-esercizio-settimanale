package it.epicode.w7d5.esercizio.settimanale.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.w7d5.esercizio.settimanale.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String surname;

    @Column(unique = true)
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @ManyToMany(mappedBy = "events")
    private List<User> users;
}

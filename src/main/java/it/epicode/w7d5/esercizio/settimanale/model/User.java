package it.epicode.w7d5.esercizio.settimanale.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.w7d5.esercizio.settimanale.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;

    @Column(unique = true)
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;
}

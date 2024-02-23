package it.epicode.w7d5.esercizio.settimanale.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.w7d5.esercizio.settimanale.enums.Role;
import it.epicode.w7d5.esercizio.settimanale.exception.OutOfRange;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private LocalDate date;
    private String location;
    private int maxUser;

    @ManyToMany
    @JoinTable(
            name = "user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    public void addUser(User user) throws OutOfRange {
        users.add(user);
    }
}

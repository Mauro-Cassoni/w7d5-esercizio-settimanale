package it.epicode.w7d5.esercizio.settimanale.repository;

import it.epicode.w7d5.esercizio.settimanale.model.Event;
import it.epicode.w7d5.esercizio.settimanale.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>, PagingAndSortingRepository<Event, Integer> {
}

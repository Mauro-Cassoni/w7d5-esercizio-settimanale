package it.epicode.w7d5.esercizio.settimanale.service;

import it.epicode.w7d5.esercizio.settimanale.exception.AlreadyAssignedException;
import it.epicode.w7d5.esercizio.settimanale.exception.NotFoundException;
import it.epicode.w7d5.esercizio.settimanale.exception.OutOfRange;
import it.epicode.w7d5.esercizio.settimanale.model.Event;
import it.epicode.w7d5.esercizio.settimanale.model.User;
import it.epicode.w7d5.esercizio.settimanale.repository.EventRepository;
import it.epicode.w7d5.esercizio.settimanale.request.EventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserService userService;

    public Page<Event> getAllEvent(Pageable pageable){

        return eventRepository.findAll(pageable);
    }

    public Event getEventById(int id) throws NotFoundException {
        return eventRepository.findById(id).orElseThrow(()->new NotFoundException("eventi by id= " + id + " not found"));
    }

    public Event saveEvent(EventRequest eventRequest){
        Event x = new Event();
        x.setTitle(eventRequest.getTitle());
        x.setDescription(eventRequest.getDescription());
        x.setDate(eventRequest.getDate());
        x.setLocation(eventRequest.getLocation());
        x.setMaxUser(eventRequest.getMaxUser());

        return eventRepository.save(x);
    }

    public Event updateEvent(int id, EventRequest eventRequest) throws NotFoundException{
        Event x = getEventById(id);

        x.setTitle(eventRequest.getTitle());
        x.setDescription(eventRequest.getDescription());
        x.setDate(eventRequest.getDate());
        x.setLocation(eventRequest.getLocation());
        x.setMaxUser(eventRequest.getMaxUser());

        return eventRepository.save(x);
    }

    public void deleteEvent(int id) throws NotFoundException{
        Event a = getEventById(id);
        eventRepository.delete(a);
    }

    public Event setUser(int id_event, int id_user) {
        Event event = getEventById(id_event);
        User user = userService.getUserById(id_user);

        if (event.getMaxUser() <= event.getUsers().size()){
            throw new OutOfRange("Event is full");
        }

        if (event.getUsers().contains(user)){
            throw new AlreadyAssignedException("User is already in the participant list");
        }

        event.addUser(user);

        return eventRepository.save(event);
    }
}

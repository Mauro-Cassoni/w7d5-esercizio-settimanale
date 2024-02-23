package it.epicode.w7d5.esercizio.settimanale.controller;

import it.epicode.w7d5.esercizio.settimanale.exception.BadRequestException;
import it.epicode.w7d5.esercizio.settimanale.exception.CustomResponse;
import it.epicode.w7d5.esercizio.settimanale.exception.NotFoundException;

import it.epicode.w7d5.esercizio.settimanale.model.Event;
import it.epicode.w7d5.esercizio.settimanale.request.EventRequest;
import it.epicode.w7d5.esercizio.settimanale.service.EventService;
import it.epicode.w7d5.esercizio.settimanale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {
    
    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<CustomResponse> getAllEvents(Pageable pageable) {
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), eventService.getAllEvent(pageable), HttpStatus.OK);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/search/{id}")
    public ResponseEntity<CustomResponse> getEventById(@PathVariable int id){
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), eventService.getEventById(id), HttpStatus.OK);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<CustomResponse> saveEvent(@RequestBody @Validated EventRequest eventRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()) throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        if (bindingResult.hasErrors()){
            return CustomResponse.error(bindingResult.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }
        try{
            return CustomResponse.success(HttpStatus.OK.toString(), eventService.saveEvent(eventRequest), HttpStatus.OK);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse> updateEvent(@PathVariable int id, @RequestBody @Validated EventRequest eventRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()) throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        if(bindingResult.hasErrors()){
            return CustomResponse.error(bindingResult.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            return CustomResponse.success(HttpStatus.OK.toString(), eventService.updateEvent(id, eventRequest), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse> deleteEvent(@PathVariable int id) {
        try{
            eventService.deleteEvent(id);
            return CustomResponse.emptyResponse("Event by id=" + id + " deleted", HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/assign/{id}/set")
    public ResponseEntity<CustomResponse> setUser(@PathVariable int id,@RequestParam int id_user){
        try {
            Event event = eventService.setUser(id, id_user);

            return CustomResponse.success(HttpStatus.OK.toString(), event, HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

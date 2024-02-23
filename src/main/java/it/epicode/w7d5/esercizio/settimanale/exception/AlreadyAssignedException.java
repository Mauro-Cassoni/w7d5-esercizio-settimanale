package it.epicode.w7d5.esercizio.settimanale.exception;

public class AlreadyAssignedException extends RuntimeException{
    public AlreadyAssignedException() {
    }

    public AlreadyAssignedException(String message) {
        super(message);
    }
}

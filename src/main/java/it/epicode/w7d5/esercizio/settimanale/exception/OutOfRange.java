package it.epicode.w7d5.esercizio.settimanale.exception;

public class OutOfRange extends RuntimeException{
    public OutOfRange() {
    }

    public OutOfRange(String message) {
        super(message);
    }
}

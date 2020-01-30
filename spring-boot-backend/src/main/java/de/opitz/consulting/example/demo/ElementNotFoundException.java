package de.opitz.consulting.example.demo;

public class ElementNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -7813749101421418205L;

    public ElementNotFoundException(String exception) {
        super(exception);
    }
}

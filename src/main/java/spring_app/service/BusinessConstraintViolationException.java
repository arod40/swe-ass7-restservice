package spring_app.service;

public class BusinessConstraintViolationException extends Exception {
    public BusinessConstraintViolationException(String msg) {
        super(msg);
    }
}

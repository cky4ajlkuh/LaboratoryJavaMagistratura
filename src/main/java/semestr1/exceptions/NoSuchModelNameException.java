package semestr1.exceptions;

//задания несуществующего имени модели
public class NoSuchModelNameException extends Exception {
    public NoSuchModelNameException() {
        super();
    }

    public NoSuchModelNameException(String message) {
        super(message);
    }
}

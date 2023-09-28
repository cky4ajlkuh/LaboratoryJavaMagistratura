package labs.first.exceptions;

//дублирования названия моделей
public class DuplicateModelNameException extends Exception {
    public DuplicateModelNameException() {
        super();
    }

    public DuplicateModelNameException(String message) {
        super(message);
    }
}

package labs.exceptions;

//задание неверной цены модели
public class ModelPriceOutOfBoundsException extends RuntimeException {

    public ModelPriceOutOfBoundsException() {
        super();
    }

    public ModelPriceOutOfBoundsException(String message) {
        super(message);
    }
}

package labs.calculator.fx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.util.Arrays;

public class CalculatorController {

    @FXML
    public Button dropAllButton;
    @FXML
    private Button clearButton;

    @FXML
    private Button divideButton;

    @FXML
    private Button eightButton;

    @FXML
    private Button equallyButton;

    @FXML
    private Button fiveButton;

    @FXML
    private Button fourButton;

    @FXML
    private Button minusButton;

    @FXML
    private Button multiplyButton;
    @FXML
    private Button nineButton;

    @FXML
    private Button oneButton;

    @FXML
    private Button plusButton;

    @FXML
    private Button pointButton;

    @FXML
    private Button sevenButton;

    @FXML
    private Button sixButton;

    @FXML
    private TextField textField;

    @FXML
    private Button threeButton;

    @FXML
    private Button twoButton;

    @FXML
    private Button zeroButton;
    private boolean isOperator;
    private boolean isResult;
    private boolean isNumber;
    private String operator = null;

    private double firstValue;
    private double secondValue;

    public void onKeysPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().getName().equals("0") | keyEvent.getCode().getName().equals("Numpad 0")) {
            zeroButton.fire();
        }
        if (keyEvent.getCode().getName().equals("1") | keyEvent.getCode().getName().equals("Numpad 1")) {
            oneButton.fire();
        }
        if (keyEvent.getCode().getName().equals("2") | keyEvent.getCode().getName().equals("Numpad 2")) {
            twoButton.fire();
        }
        if (keyEvent.getCode().getName().equals("3") | keyEvent.getCode().getName().equals("Numpad 3")) {
            threeButton.fire();
        }
        if (keyEvent.getCode().getName().equals("4") | keyEvent.getCode().getName().equals("Numpad 4")) {
            fourButton.fire();
        }
        if (keyEvent.getCode().getName().equals("5") | keyEvent.getCode().getName().equals("Numpad 5")) {
            fiveButton.fire();
        }
        if (keyEvent.getCode().getName().equals("6") | keyEvent.getCode().getName().equals("Numpad 6")) {
            sixButton.fire();
        }
        if (keyEvent.getCode().getName().equals("7") | keyEvent.getCode().getName().equals("Numpad 7")) {
            sevenButton.fire();
        }
        if (keyEvent.getCode().getName().equals("8") | keyEvent.getCode().getName().equals("Numpad 8")) {
            eightButton.fire();
        }
        if (keyEvent.getCode().getName().equals("9") | keyEvent.getCode().getName().equals("Numpad 9")) {
            nineButton.fire();
        }
        if (keyEvent.getCode().getName().equals("Minus") | keyEvent.getCode().getName().equals("Subtract")) {
            minusButton.fire();
        }
        if (keyEvent.getCode().getName().equals("Add")) {
            plusButton.fire();
        }
        if (keyEvent.getCode().getName().equals("Divide")) {
            divideButton.fire();
        }
        if (keyEvent.getCode().getName().equals("Multiply")) {
            multiplyButton.fire();
        }
        if (keyEvent.getCode().getName().equals("Enter") | keyEvent.getCode().getName().equals("Equals")) {
            equallyButton.fire();
        }
        if (keyEvent.getCode().getName().equals("Backspace")) {
            clearButton.fire();
        }
        if (keyEvent.getCode().getName().equals("Period") | keyEvent.getCode().getName().equals("Decimal")) {
            pointButton.fire();
        }
    }

    public void onZeroButtonAction() {
        if (isOperator) {
            textField.clear();
            textField.setText("0");
            isOperator = false;
        } else {
            textField.setText(textField.getText() + "0");
        }
        isNumber = true;
    }

    public void onOneButtonAction() {
        if (isOperator) {
            textField.clear();
            textField.setText("1");
            isOperator = false;
        } else {
            textField.setText(textField.getText() + "1");
        }
        isNumber = true;
    }

    public void onTwoButtonAction() {
        if (isOperator) {
            textField.clear();
            textField.setText("2");
            isOperator = false;
        } else {
            textField.setText(textField.getText() + "2");
        }
        isNumber = true;
    }

    public void onThreeButtonAction() {
        if (isOperator) {
            textField.clear();
            textField.setText("3");
            isOperator = false;

        } else {
            textField.setText(textField.getText() + "3");
        }
        isNumber = true;
    }

    public void onFourButtonAction() {
        if (isOperator) {
            textField.clear();
            textField.setText("4");
            isOperator = false;

        } else {
            textField.setText(textField.getText() + "4");
        }
        isNumber = true;
    }

    public void onFiveButtonAction() {
        if (isOperator) {
            textField.clear();
            textField.setText("5");
            isOperator = false;
        } else {
            textField.setText(textField.getText() + "5");
        }
        isNumber = true;
    }

    public void onSixButtonAction() {
        if (isOperator) {
            textField.clear();
            textField.setText("6");
            isOperator = false;
        } else {
            textField.setText(textField.getText() + "6");
        }
        isNumber = true;
    }

    public void onSevenButtonAction() {
        if (isOperator) {
            textField.clear();
            textField.setText("7");
            isOperator = false;
        } else {
            textField.setText(textField.getText() + "7");
        }
        isNumber = true;
    }

    public void onEightButtonAction() {
        if (isOperator) {
            textField.clear();
            textField.setText("8");
            isOperator = false;
        } else {
            textField.setText(textField.getText() + "8");
        }
        isNumber = true;
    }

    public void onNineButtonAction() {
        if (isOperator) {
            textField.clear();
            textField.setText("9");
            isOperator = false;
        } else {
            textField.setText(textField.getText() + "9");
        }
        isNumber = true;
    }

    public void onPlusButtonAction() {
        if (!isEmpty()) {
            if (isNumber) {
                secondValue = Double.parseDouble(textField.getText());
                if (operator != null && !isResult) {
                    textField.setText(String.valueOf(calculation()));
                }
                isNumber = false;
            }
            firstValue = Double.parseDouble(textField.getText());
            isOperator = true;
            isResult = false;
            operator = "+";
        }
    }

    public void onMultiplyButtonAction() {
        if (!isEmpty()) {
            if (isNumber) {
                secondValue = Double.parseDouble(textField.getText());
                if (operator != null && !isResult) {
                    textField.setText(String.valueOf(calculation()));
                }
                isNumber = false;
            }
            firstValue = Double.parseDouble(textField.getText());
            isOperator = true;
            isResult = false;
            operator = "*";
        }
    }

    public void onMinusButtonAction() {
        if (!isEmpty()) {
            if (isNumber) {
                secondValue = Double.parseDouble(textField.getText());
                if (operator != null && !isResult) {
                    double d = calculation();
                    textField.setText(String.valueOf(d));
                }
                isNumber = false;
            }
            firstValue = Double.parseDouble(textField.getText());
            isOperator = true;
            isResult = false;
            operator = "-";
        } else {
            textField.setText("-");
        }
    }

    public void onDivideButtonAction() {
        if (!isEmpty()) {
            if (isNumber) {
                secondValue = Double.parseDouble(textField.getText());
                if (operator != null && !isResult) {
                    textField.setText(String.valueOf(calculation()));
                }
                isNumber = false;
            }
            firstValue = Double.parseDouble(textField.getText());
            isOperator = true;
            isResult = false;
            operator = "/";
        }
    }

    public void onPercentButtonAction() {

    }

    public void onDropAllButtonAction() {
        textField.clear();
        operator = null;
        firstValue = 0;
        secondValue = 0;
        isNumber = false;
        isResult = false;
        isOperator = false;
    }

    public void onDropButtonAction() {
        textField.clear();
    }

    public void onClearButtonAction() {
        if (!isEmpty()) {
            textField.setText(String.valueOf(Arrays.copyOf(textField.getText().toCharArray(), textField.getText().length() - 1)));
        }
    }

    public void onReverseButtonAction() {
        if (isNotChar() && !isEmpty()) {
            if (Double.parseDouble(textField.getText()) != 0) {
                textField.setText(String.valueOf(1 / Double.parseDouble(textField.getText())));
            } else {
                throwMessage("Деление на ноль!");
            }
        }
    }

    public void onPowButtonAction() {
        if (isNotChar() && !isEmpty()) {
            textField.setText(String.valueOf(Math.pow(Double.parseDouble(textField.getText()), 2)));
        }
    }

    public void onSqrtButtonAction() {
        if (isNotChar() && !isEmpty()) {
            if (Double.parseDouble(textField.getText()) >= 0) {
                textField.setText(String.valueOf(Math.sqrt(Double.parseDouble(textField.getText()))));
            } else {
                throwMessage("Корень из отрицательно числа!");
            }
        }
    }

    public void onChangeSignButtonAction() {
        if (isNotChar() && !isEmpty()) {
            textField.setText(String.valueOf((-1) * Double.parseDouble(textField.getText())));
        }
    }

    public void onPointButtonAction() {
        if (isEmpty()) {
            textField.setText(textField.getText() + "0.");
        } else {
            char[] array = textField.getText().toCharArray();
            for (int i = 0; i < array.length; i++) {
                if (array[i] == '.') {
                    break;
                } else if (i == array.length - 1) {
                    textField.setText(textField.getText() + ".");
                }
            }
        }
    }

    public void onEquallyButtonAction() {
        if (!isEmpty()) {
            secondValue = Double.parseDouble(textField.getText());
            textField.setText(String.valueOf(calculation()));
            firstValue = secondValue;
            secondValue = 0;
            isResult = true;
            isOperator = false;
            isNumber = false;
        }
    }

    private boolean isEmpty() {
        return textField.getText().isEmpty();
    }

    private boolean isNotChar() {
        return !textField.getText().equals(".") | !textField.getText().equals("-");
    }

    private double calculation() {
        if (operator.equals("+")) {
            return firstValue + secondValue;
        }
        if (operator.equals("-")) {
            return firstValue - secondValue;
        }
        if (operator.equals("/")) {
            if (secondValue != 0) {
                return firstValue / secondValue;
            } else {
                throwMessage("Деление на ноль!");
            }
        }
        return firstValue * secondValue;
    }

    private void throwMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ой!");
        alert.setHeaderText(message);
        alert.showAndWait();
        dropAllButton.fire();
    }
}

package labs.calculator;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CalculatorApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/constructor.fxml"));
        loader.setClassLoader(getClass().getClassLoader());
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("icons8-math.gif"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

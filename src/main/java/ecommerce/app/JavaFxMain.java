package ecommerce.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;


public class JavaFxMain extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/ecommerce/ui/login.fxml")
        );

        Font.loadFont(getClass().getResourceAsStream("/fonts/Inter-Light.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Inter-Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Inter-Medium.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Inter-SemiBold.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Inter-Bold.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Inter-ExtraBold.ttf"), 12);

        Parent root = loader.load();
        Scene scene = new Scene(root, 1500, 800);
        root.setStyle("-fx-font-smoothing-type: lcd;");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

package ecommerce.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;


public class JavaFxMain extends Application {
    public class FontDebug {
        public static void printFonts() {
            Font.getFamilies().forEach(System.out::println);
        }
    }


    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/ecommerce/ui/login.fxml")
        );
        
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1500, 800));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

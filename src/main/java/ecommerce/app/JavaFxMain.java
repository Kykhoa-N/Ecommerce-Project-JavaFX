package ecommerce.app;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import ecommerce.customUI.*;

import java.io.IOException;
import java.util.Arrays;


public class JavaFxMain extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        customFont.loadInter();
        initTheme();
        initScreen(stage);
    }

    public void initScreen(Stage stage) throws IOException {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/ecommerce/ui/login.fxml"));
        Parent loginRoot = loginLoader.load();
        Scene loginScene = new Scene(loginRoot, 1500, 800);

        stage.setScene(loginScene);
        stage.show();
        // stop auto-focus on first TextField
        Platform.runLater(loginRoot::requestFocus);
    }

    public void initTheme() throws IOException {
        FXMLLoader themeLoader = new FXMLLoader(getClass().getResource("/ecommerce/ui/theme.fxml"));
        Parent themeRoot = themeLoader.load();
        Scene themeScene = new Scene(themeRoot, 1500, 800);

        Stage stage = new Stage();
        stage.setScene(themeScene);
        stage.show();
        Platform.runLater(themeRoot::requestFocus);
    }

    public static void main(String[] args) {
        int[] rgb = new int[]{255, 125, 0};
        int[] rgb2 = new int[]{255, 0, 0};
        int[] rgb3 = new int[]{255, 255, 0};

        System.out.println("Pastel Orange: " + Arrays.toString(PastelColorMath.modernDirtyRgb(rgb[0], rgb[1],rgb[2])));
        System.out.println("UIColor Orange: " + Arrays.toString(UIColorMath.modernDirtyRgb(rgb[0], rgb[1],rgb[2])));

        System.out.println("Pastel Red: " + Arrays.toString(PastelColorMath.modernDirtyRgb(rgb2[0], rgb2[1],rgb2[2])));
        System.out.println("UIColor Red: " + Arrays.toString(UIColorMath.modernDirtyRgb(rgb2[0], rgb2[1],rgb2[2])));

        System.out.println("Pastel Yellow: " + Arrays.toString(PastelColorMath.modernDirtyRgb(rgb3[0], rgb3[1],rgb3[2])));
        System.out.println("UIColor Yellow: " + Arrays.toString(UIColorMath.modernDirtyRgb(rgb3[0], rgb3[1],rgb3[2])));

        launch();
    }
}

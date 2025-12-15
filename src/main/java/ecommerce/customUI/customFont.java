package ecommerce.customUI;
import javafx.scene.text.Font;

public final class customFont {

    private customFont() {
        // prevent instantiation
    }

    public static void loadInter() {
        Font.loadFont(customFont.class.getResourceAsStream("/fonts/Inter-Light.ttf"), 12);
        Font.loadFont(customFont.class.getResourceAsStream("/fonts/Inter-Regular.ttf"), 12);
        Font.loadFont(customFont.class.getResourceAsStream("/fonts/Inter-Medium.ttf"), 12);
        Font.loadFont(customFont.class.getResourceAsStream("/fonts/Inter-SemiBold.ttf"), 12);
        Font.loadFont(customFont.class.getResourceAsStream("/fonts/Inter-Bold.ttf"), 12);
        Font.loadFont(customFont.class.getResourceAsStream("/fonts/Inter-ExtraBold.ttf"), 12);
    }
}
package ecommerce.customUI;

import javafx.geometry.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.fxml.*;
import javafx.scene.*;

import java.text.DecimalFormat;
import java.util.*;

import ecommerce.model.*;
import ecommerce.app.*;
import javafx.scene.shape.*;

public class CustomUI {

    public static Button iconButton(String buttonStyle, String iconStyle, String svgPath) {
        SVGPath icon = new SVGPath();
        icon.setContent(svgPath);
        icon.getStyleClass().add(iconStyle);
        icon.setScaleX(0.052);
        icon.setScaleY(0.052);

        StackPane iconBox = new StackPane(icon);
        iconBox.getStyleClass().add("icon-box");

        Button button = new Button();
        button.setGraphic(iconBox);
        button.getStyleClass().add(buttonStyle);

        return button;
    }

    public static void cellMoneyFormat(TableColumn<Product, Double> priceCol) {

        priceCol.setCellFactory(col -> new TableCell<Product, Double>() {

            private final DecimalFormat format = new DecimalFormat("$#,##0.00");

            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);

                if (empty || value == null) {
                    setText(null);
                } else {
                    setText(format.format(value));
                }
            }
        });
    }
}

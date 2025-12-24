package ecommerce.customUI;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.*;
import java.util.*;


public class CustomDialog {

    private final Dialog<Void> dialog;
    private final DialogPane pane;
    private GridPane grid;

    public CustomDialog() {

        // Custom Dialog Setup
        dialog = new Dialog<>();
        pane = dialog.getDialogPane();
        pane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/ecommerce/css/theme.css")).toExternalForm());
        pane.getStyleClass().add("ui-dialog");

        // Permit Buttons Actions with Cancel Feature of Dialog but remove default Visual
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        pane.lookupButton(ButtonType.CANCEL).setManaged(false);
        Node bar = pane.lookup(".button-bar");
        if (bar instanceof Region r) {
            r.setMinHeight(0);
            r.setPrefHeight(0);
            r.setMaxHeight(0);
        }

        // Form Setup
        grid = new GridPane();
        grid.getStyleClass().add("ui-dialog");
    }

    public Dialog<Void> getDialog() {
        return dialog;
    }

    public DialogPane getPane() {
        return pane;
    }

    public GridPane getGrid() {
        return grid;
    }

}

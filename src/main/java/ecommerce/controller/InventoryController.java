package ecommerce.controller;


import javafx.geometry.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.fxml.*;
import javafx.scene.*;
import java.util.*;

import ecommerce.model.*;
import ecommerce.app.*;
import ecommerce.customUI.*;


public class InventoryController implements UseAppContext {

    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, String> nameCol;
    @FXML private TableColumn<Product, String> categoryCol;
    @FXML private TableColumn<Product, Double> priceCol;
    @FXML private TableColumn<Product, Integer> qtyCol;

    private AppContext app;

    @FXML
    private void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    @Override
    public void loadInfo(AppContext appContext) {
        this.app = appContext;
        refresh();
    }

    public void refresh() {
        productTable.setItems(app.getProductService().getObservableProducts());
    }

    public void onAddProduct() {

        // Dialog Setup
        CustomDialog dialogObject = new CustomDialog();
        Dialog<Void> dialog = dialogObject.getDialog();
        DialogPane pane = dialogObject.getPane();
        GridPane grid = dialogObject.getGrid();

        // Dialog Field
        TextField nameField = new TextField();
        TextField categoryField = new TextField();
        TextField priceField = new TextField();
        TextField qtyField = new TextField();
        Button addBtn = new Button("Add");
        Button cancelBtn = new Button("Cancel");
        HBox actionBar = new HBox(15, addBtn, cancelBtn);

        // Custom Design
        cancelBtn.getStyleClass().add("white-block-button");
        addBtn.getStyleClass().add("primary-block-button");
        actionBar.setAlignment(Pos.CENTER);

        // Placement
        grid.addRow(0, new Label("Name"), nameField);
        grid.addRow(1, new Label("Category"), categoryField);
        grid.addRow(2, new Label("Price"), priceField);
        grid.addRow(3, new Label("Quantity"), qtyField);
        grid.add(actionBar, 0, 5, 2, 1);



        pane.setContent(grid);   // ✅ set content ONCE


        addBtn.setOnAction(e -> {
            app.getProductService().add(
                    app.getCurrentUser(),
                    nameField.getText().trim(),
                    categoryField.getText().trim(),
                    Double.parseDouble(priceField.getText().trim()),
                    Integer.parseInt(qtyField.getText().trim())
            );
            dialog.close();
        });

        cancelBtn.setOnAction(e -> {
            dialog.close();
        });

        dialog.showAndWait();
        refresh();
    }
}
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
    @FXML private TableColumn<Product, Void> actionCol;

    private AppContext app;

    @Override
    public void loadInfo(AppContext appContext) {
        this.app = appContext;
        refresh();
    }

    @FXML
    private void initialize() {
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        actionCol.setCellFactory(column -> new TableCell<>() {

            private final Button editBtn = new Button("Edit");
            private final Button deleteBtn = new Button("Delete");
            private final HBox box = new HBox(8, editBtn, deleteBtn);

            {
                box.setAlignment(Pos.CENTER_RIGHT);

                editBtn.getStyleClass().add("white-block-button");
                deleteBtn.getStyleClass().add("danger-block-button");

                editBtn.setOnAction(e -> {
                    Product product = getTableView().getItems().get(getIndex());
                    onEditProduct(product);
                });

                deleteBtn.setOnAction(e -> {
                    Product product = getTableView().getItems().get(getIndex());
                    onDeleteProduct(product);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : box);
            }
        });

    }

    @FXML
    private void onEditProduct(Product product) {
        // Dialog Setup
        CustomDialog dialogObject = new CustomDialog();
        Dialog<Void> dialog = dialogObject.getDialog();
        DialogPane pane = dialogObject.getPane();
        GridPane grid = dialogObject.getGrid();

        // Dialog Field
        TextField categoryField = new TextField();
        TextField priceField = new TextField();
        TextField qtyField = new TextField();
        Button editBtn = new Button("Edit");
        Button cancelBtn = new Button("Cancel");
        HBox actionBar = new HBox(15, editBtn, cancelBtn);

        // Custom Design
        categoryField.setText(product.getCategory());
        priceField.setText(String.valueOf(product.getPrice()));
        qtyField.setText(String.valueOf(product.getQuantity()));
        cancelBtn.getStyleClass().add("white-block-button");
        editBtn.getStyleClass().add("primary-block-button");
        actionBar.setAlignment(Pos.CENTER);

        // Placement
        dialog.setTitle(product.getName());
        grid.addRow(0, new Label("Category"), categoryField);
        grid.addRow(1, new Label("Price"), priceField);
        grid.addRow(2, new Label("Quantity"), qtyField);
        grid.add(actionBar, 0, 4, 2, 1);

        // Display form onto the dialog
        pane.setContent(grid);

        // Button Functionality
        editBtn.setOnAction(e -> {
            app.getProductService().update(
                    app.getCurrentUser(),
                    product.getName(),
                    categoryField.getText().trim(),
                    Double.parseDouble(priceField.getText().trim()),
                    Integer.parseInt(qtyField.getText().trim())
            );
            dialog.close();
        });

        cancelBtn.setOnAction(e -> {
            dialog.close();
        });

        // Always needed
        dialog.showAndWait();
        productTable.refresh();
        refresh();
    }

    @FXML
    private void onDeleteProduct(Product product) {
        app.getProductService().remove(app.getCurrentUser(), product.getName());
        refresh();
    }

    public void refresh() {
        productTable.setItems(app.getProductService().getObservableProducts());
    }

    @FXML
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
        
        // Display form onto the dialog
        pane.setContent(grid);

        // Button Functionality
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

        // Always needed
        dialog.showAndWait();
        refresh();
    }
}
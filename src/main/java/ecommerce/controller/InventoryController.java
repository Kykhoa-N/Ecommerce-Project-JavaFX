package ecommerce.controller;


import javafx.geometry.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.fxml.*;
import javafx.scene.*;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import java.util.*;

import ecommerce.model.*;
import ecommerce.app.*;
import ecommerce.customUI.*;


public class InventoryController implements UseAppContext {

    @FXML private TextField searchTextField;
    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, String> nameCol;
    @FXML private TableColumn<Product, String> categoryCol;
    @FXML private TableColumn<Product, Double> priceCol;
    @FXML private TableColumn<Product, Integer> qtyCol;
    @FXML private TableColumn<Product, Void> actionCol;



    private AppContext app;
    private int view = 0;
    private FilteredList<Product> filtered;
    private SortedList<Product> sorted;

    @Override
    public void loadInfo(AppContext appContext) {
        this.app = appContext;
        // Build base list for current "view"
        var base = app.getProductService().getObservableProducts(
                app.getProductService().viewAll(app.getCurrentUser(), view)
        );

        // Wrap in filter + sort (only ONCE)
        filtered = new FilteredList<>(base, p -> true);
        sorted = new SortedList<>(filtered);

        // Keep table header sorting working if you click headers
        sorted.comparatorProperty().bind(productTable.comparatorProperty());

        // Set items ONCE
        productTable.setItems(sorted);

        // Listen on every letter typed (ONCE)
        searchTextField.textProperty().addListener((obs, oldVal, newVal) -> applySearch(newVal));
    }

    public void refresh() {
        var base = app.getProductService().getObservableProducts(
                app.getProductService().viewAll(app.getCurrentUser(), view)
        );

        filtered = new FilteredList<>(base, p -> true);
        sorted = new SortedList<>(filtered);
        sorted.comparatorProperty().bind(productTable.comparatorProperty());
        productTable.setItems(sorted);

        // Re-apply current search text after refresh
        applySearch(searchTextField.getText());
    }

    @FXML
    private void initialize() {
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        CustomUI.cellMoneyFormat(priceCol);
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        actionCol.setCellFactory(column -> new TableCell<>() {

            private final Button editBtn = CustomUI.iconButton( "icon-text-button", "primary-icon", "M89.258 21.138 C 57.793 25.278,31.502 48.613,22.851 80.078 L 20.703 87.891 20.703 200.000 L 20.703 312.109 22.851 319.922 C 30.593 348.081,51.919 369.407,80.078 377.149 L 87.891 379.297 200.000 379.297 L 312.109 379.297 319.922 377.149 C 348.089 369.405,369.476 348.010,377.130 319.922 L 379.259 312.109 379.278 253.769 C 379.297 195.783,379.287 195.415,377.663 193.222 C 372.039 185.629,360.752 185.405,355.745 192.787 C 354.378 194.802,354.272 198.123,353.859 251.953 C 353.463 303.456,353.286 309.439,352.036 313.672 C 346.411 332.714,332.262 346.738,313.281 352.085 C 308.471 353.440,302.505 353.516,200.000 353.516 C 97.495 353.516,91.529 353.440,86.719 352.085 C 67.554 346.686,53.314 332.446,47.915 313.281 C 45.740 305.560,45.692 94.667,47.863 86.807 C 53.067 67.969,67.376 53.519,86.385 47.903 C 90.546 46.674,96.756 46.493,147.656 46.119 C 199.343 45.740,204.512 45.585,206.761 44.355 C 214.546 40.094,214.556 28.101,206.779 22.337 C 204.412 20.583,101.459 19.533,89.258 21.138 M291.969 33.984 C 282.394 36.415,279.436 38.515,263.672 54.075 L 248.828 68.726 251.846 74.402 C 271.125 110.665,290.119 129.609,326.075 148.438 L 331.297 151.172 345.937 136.328 C 369.143 112.799,373.187 97.157,360.969 78.188 C 358.146 73.804,324.933 40.754,320.977 38.391 C 313.129 33.704,300.583 31.798,291.969 33.984 M181.164 136.523 C 131.236 186.589,128.933 189.138,126.284 197.266 C 124.911 201.477,116.406 259.200,116.406 264.306 C 116.406 274.791,125.209 283.594,135.694 283.594 C 142.098 283.594,198.830 275.022,203.516 273.347 C 211.786 270.390,215.194 267.293,264.715 217.736 L 313.415 169.002 306.122 164.856 C 273.528 146.326,253.636 126.444,235.178 93.945 C 232.921 89.971,231.019 86.719,230.953 86.719 C 230.886 86.719,208.481 109.131,181.164 136.523");
            private final Button deleteBtn = CustomUI.iconButton( "icon-text-button", "shade-icon", "M61.587 0.792 C 33.625 5.567,10.380 26.865,2.154 55.243 L 0.391 61.328 0.391 200.000 L 0.391 338.672 2.154 344.757 C 9.738 370.922,29.078 390.262,55.243 397.846 L 61.328 399.609 200.000 399.609 L 338.672 399.609 344.757 397.846 C 370.922 390.262,390.262 370.922,397.846 344.757 L 399.609 338.672 399.609 200.000 L 399.609 61.328 397.846 55.243 C 390.317 29.270,371.512 10.321,345.177 2.174 L 339.453 0.403 202.344 0.286 C 126.934 0.222,63.593 0.449,61.587 0.792 M142.723 109.411 C 143.932 109.861,157.312 122.601,172.456 137.722 L 199.990 165.216 227.924 137.351 C 263.294 102.071,259.104 103.349,277.931 122.100 C 296.658 140.752,297.904 136.682,262.632 172.070 L 234.795 200.000 262.632 227.930 C 297.904 263.318,296.658 259.248,277.931 277.900 C 259.104 296.651,263.293 297.928,227.930 262.654 L 200.000 234.795 172.070 262.632 C 136.682 297.904,140.752 296.658,122.100 277.931 C 103.349 259.104,102.072 263.293,137.346 227.930 L 165.205 200.000 137.368 172.070 C 102.097 136.683,103.343 140.754,122.069 122.095 C 135.721 108.493,137.429 107.443,142.723 109.411" );
            private final HBox box = new HBox(20, editBtn, deleteBtn);

            {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER_LEFT);

                box.setAlignment(Pos.CENTER_RIGHT);
                box.setFillHeight(true);

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
        System.out.println("ADD " + nameField.getText() + " product.");
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
        System.out.println("EDIT " + product.getName() + " product.");
    }

    @FXML
    private void onDeleteProduct(Product product) {
        app.getProductService().remove(app.getCurrentUser(), product.getName());
        refresh();
        System.out.println("REMOVE " + product.getName() + " product.");
    }

    @FXML
    private void onSortPrice() {
        view = 1;
        refresh();
        System.out.println("display SORT PRICE.");
    }

    @FXML
    private void onSortAlphabet() {
        view = 0;
        refresh();
        System.out.println("display SORT ALPHABET.");
    }

    private void applySearch(String text) {
        String q = (text == null) ? "" : text.trim().toLowerCase();

        filtered.setPredicate(p -> {
            if (q.isEmpty()) return true;

            return p.getName().toLowerCase().contains(q)
                    || p.getCategory().toLowerCase().contains(q)
                    || String.valueOf(p.getPrice()).contains(q)
                    || String.valueOf(p.getQuantity()).contains(q);
        });
    }
}
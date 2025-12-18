package ecommerce.controller;


import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.fxml.*;
import javafx.scene.*;
import java.util.*;

import ecommerce.repo.*;
import ecommerce.model.*;
import ecommerce.app.*;

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
        productTable.setItems(app.getProductService().getObservableProducts());
    }


}
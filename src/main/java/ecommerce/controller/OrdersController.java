package ecommerce.controller;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.fxml.*;
import javafx.scene.*;
import java.util.*;

import ecommerce.model.User;
import ecommerce.app.*;

public class OrdersController implements UseAppContext {

    private AppContext app;

    @Override
    public void loadInfo(AppContext appContext) {
        this.app = appContext;
    }
}

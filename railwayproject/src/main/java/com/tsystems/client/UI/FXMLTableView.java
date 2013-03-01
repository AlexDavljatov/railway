package com.tsystems.client.UI;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/26/13
 * Time: 3:34 AM
 * To change this template use File | Settings | File Templates.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXMLTableView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FXML TableView Example");
        //Pane myPane = (Pane)FXMLLoader.load(getClass().getResource("/fxml/fxml_tableview.fxml"));
        Pane myPane = (Pane) FXMLLoader.load(FXMLTableView.class.getResource("/fxml/fxml_tableview.fxml"));
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
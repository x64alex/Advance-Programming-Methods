package com.example.toylanguage;

import com.example.toylanguage.View.gui.ListViewController;
import com.example.toylanguage.View.gui.ProgramViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader listLoader = new FXMLLoader(Main.class.getResource("ListView.fxml"));
        Scene scene = new Scene(listLoader.load(), 1500, 700);
        ListViewController listViewController = listLoader.getController();


        stage.setTitle("Select:");
        stage.setScene(scene);
        stage.show();

        FXMLLoader programLoader = new FXMLLoader(Main.class.getResource("ProgramView.fxml"));
        Scene programScene = new Scene(programLoader.load(), 1400, 1000);
        ProgramViewController programController = programLoader.getController();
        Stage secondaryStage = new Stage();
        listViewController.setProgramViewController(programController);

        secondaryStage.setTitle("Interpreter");
        secondaryStage.setScene(programScene);
        secondaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
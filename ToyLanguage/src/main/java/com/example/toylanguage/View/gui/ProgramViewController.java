package com.example.toylanguage.View.gui;

import com.example.toylanguage.Controller.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProgramViewController {

    private Controller ctr;

    public void setController(Controller controller){
        ctr = controller;
        populate();
    }


    @FXML
    private TextField prgStates;

    @FXML
    public void initialize() {
    }

    private void populate() {
        populateTextField();
    }

    private void populateTextField() {
        prgStates.setText(Integer.toString(ctr.getNumberPrgStates()));
    }
}

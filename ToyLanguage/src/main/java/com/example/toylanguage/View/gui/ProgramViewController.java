package com.example.toylanguage.View.gui;

import com.example.toylanguage.Controller.*;
import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Heap.MyIHeap;
import com.example.toylanguage.Model.Statments.IStmt;
import com.example.toylanguage.Model.Values.Value;
import com.example.toylanguage.View.Examples;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


class Pair<T1, T2> {
    T1 first;
    T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
}

public class ProgramViewController {

    private Controller ctr;

    public void setController(Controller controller){
        ctr = controller;
        populate();
    }


    @FXML
    private TextField prgStates;

    @FXML
    private TableView<Pair<Integer, Value>> heapTable;

    @FXML
    public TableColumn<Pair<Integer, Value>, Integer> heapTableAddress;

    @FXML
    public TableColumn<Pair<Integer, Value>, String> heapTableValue;

    @FXML
    private ListView<Value> output;
    @FXML
    private ListView<Value> fileTable;
    @FXML
    private ListView<Integer> programStates;
    @FXML
    private Button runOneStep;

    @FXML
    public void initialize() {

        heapTableAddress.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().first).asObject());
        heapTableValue.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().second.toString()));
        runOneStep.setOnAction(actionEvent -> {
            if(ctr == null){
                Alert alert = new Alert(Alert.AlertType.ERROR, "The program was not selected", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if(ctr.prgStatesDone()){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Nothing left to execute", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            try {
                ctr.oneStepAll();
                populate();
            } catch (MyException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        });
    }

    private void populate() {
        populateTextField();
        populateHeap();
        populateOutput();
        populateFileTable();
        populateProgramStates();
    }



    private void populateHeap() {
        MyIHeap<Integer, Value> heap = this.ctr.getHeap();
        List<Pair<Integer, Value>> heapTableList = new ArrayList<>();
        for (Map.Entry<Integer, Value> entry : heap.getContent().entrySet())
            heapTableList.add(new Pair<>(entry.getKey(), entry.getValue()));
        heapTable.setItems(FXCollections.observableList(heapTableList));
        heapTable.refresh();

    }
    private void populateTextField() {
        prgStates.setText(Integer.toString(ctr.getNumberPrgStates()));
    }

    private void populateOutput() {
        output.setItems(FXCollections.observableArrayList(ctr.getOutput().getList()));
    }

    private void populateFileTable() {
        fileTable.setItems(FXCollections.observableArrayList(ctr.getFileTable().getFileNames()));
    }

    private void populateProgramStates() {
        programStates.setItems(FXCollections.observableArrayList(ctr.getPrgStateIdentifiers()));
    }
}

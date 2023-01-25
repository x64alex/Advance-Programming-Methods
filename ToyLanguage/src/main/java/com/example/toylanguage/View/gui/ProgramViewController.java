package com.example.toylanguage.View.gui;

import com.example.toylanguage.Controller.*;
import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Barrier.ICyclicBarrier;
import com.example.toylanguage.Model.ADT.Heap.MyIHeap;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.PairBarrier.PairBarrier;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Statments.IStmt;
import com.example.toylanguage.Model.Values.IntValue;
import com.example.toylanguage.Model.Values.Value;
import com.example.toylanguage.View.Examples;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.Collections;
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
    private Integer selectedPrgState;

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
    private TableView<Pair<String, Value>> symTableView;

    @FXML
    public TableColumn<Pair<String, Value>, String> symTableName;
    @FXML
    public TableColumn<Pair<String, Value>, String> symTableValue;

    @FXML
    private TableView<Pair<String, Pair<String, String>>> barrierTableView;

    @FXML
    public TableColumn<Pair<String, Pair<String, String>>, String> barrierTableIndex;
    @FXML
    public TableColumn<Pair<String, Pair<String, String>>, String> barrierTableValue;
   // public TableColumn<Pair<String, Pair<String, String>>, String> barrierTableList;

    @FXML
    private ListView<Value> output;
    @FXML
    private ListView<Value> fileTable;
    @FXML
    private ListView<Integer> programStates;

    @FXML
    private ListView<IStmt> exeStackTable;
    @FXML
    private Button runOneStep;

    @FXML
    public void initialize() {
        heapTableAddress.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().first).asObject());
        heapTableValue.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().second.toString()));
        symTableName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().first.toString()));
        symTableValue.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().second.toString()));
        barrierTableIndex.setCellValueFactory(p-> new SimpleStringProperty(p.getValue().first));
        barrierTableValue.setCellValueFactory(p-> new SimpleStringProperty(p.getValue().second.first));
       // barrierTableList.setCellValueFactory(p-> new SimpleStringProperty(p.getValue().second.second));

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

        programStates.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                selectedPrgState = newValue;
                populateExeStackTable();
                populateSymTable();
            }
        });
    }

    private void populate() {
        selectedPrgState = ctr.getFirstPrgState();
        populateTextField();
        populateHeap();
        populateOutput();
        populateFileTable();
        populateProgramStates();
        populateExeStackTable();
        populateSymTable();
        populateVarrierTableView();
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
    private void populateExeStackTable() {
        PrgState prgState = ctr.getPrgState(selectedPrgState);
        List<IStmt> exeStack = prgState.getStk().getElements();
        List<IStmt> reversedStack = new ArrayList<>();
        for (int i = exeStack.size() - 1; i >= 0 ; i--) {
            reversedStack.add(exeStack.get(i));
        }
        exeStackTable.setItems(FXCollections.observableArrayList(reversedStack));
    }

    private void populateSymTable() {
        PrgState prgState = ctr.getPrgState(selectedPrgState);
        MyIDictionary<String, Value> symTable = prgState.getSymTable();
        List<Pair<String, Value>> symTableList = new ArrayList<>();
        for (Map.Entry<String, Value> entry : symTable.getContent().entrySet())
            symTableList.add(new Pair<>(entry.getKey(), entry.getValue()));
        symTableView.setItems(FXCollections.observableList(symTableList));
        symTableView.refresh();

    }

    private void populateVarrierTableView() {
        PrgState prgState = ctr.getPrgState(selectedPrgState);
        ICyclicBarrier<IntValue, PairBarrier<IntValue, List<Integer>>> barrier = prgState.getBarrier();
        Map<IntValue, PairBarrier<IntValue, List<Integer>>> map = barrier.getBarrier();
        //List<Pair<String, Value>> symTableList = new ArrayList<>();
        List<Pair<String, Pair<String, String>>> list = new ArrayList<>();
        map.forEach((k,v)-> {
            list.add(new Pair(k.toString(),new Pair(v.getFirst().toString(), v.getSecond().toString())));
                }

        );
        //for (Map.Entry<IntValue, PairBarrier<IntValue, List<Integer>>> entry : map)
        barrierTableView.setItems(FXCollections.observableList(list));
        barrierTableView.refresh();
        //  /<TableColumn fx:id="barrierTableList"prefWidth="75.0" text="List" />
    }
}

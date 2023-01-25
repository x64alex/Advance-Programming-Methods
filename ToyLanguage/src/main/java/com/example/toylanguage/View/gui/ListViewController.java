package com.example.toylanguage.View.gui;


import com.example.toylanguage.Controller.Controller;
import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Barrier.CyclicBarrier;
import com.example.toylanguage.Model.ADT.Barrier.ICyclicBarrier;
import com.example.toylanguage.Model.ADT.SymTable.MyDictionary;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.ADT.FileTable.MyFileTable;
import com.example.toylanguage.Model.ADT.List.MyIList;
import com.example.toylanguage.Model.ADT.List.MyList;
import com.example.toylanguage.Model.ADT.Stack.MyIStack;
import com.example.toylanguage.Model.ADT.Stack.MyStack;
import com.example.toylanguage.Model.PairBarrier.PairBarrier;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Statments.IStmt;
import com.example.toylanguage.Model.Types.IntType;
import com.example.toylanguage.Model.Values.IntValue;
import com.example.toylanguage.Model.Values.StringValue;
import com.example.toylanguage.Model.Values.Value;
import com.example.toylanguage.Repository.MyIRepository;
import com.example.toylanguage.Repository.MyRepository;
import com.example.toylanguage.View.Examples;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.io.BufferedReader;
import java.util.List;

public class ListViewController {

	private ProgramViewController programViewController;

	public void setProgramViewController(ProgramViewController programViewController) {
		this.programViewController = programViewController;
	}
	@FXML
	private ListView<IStmt> statements;
	@FXML
	private Button displayButton;
	 
	 
	 @FXML
	 public void initialize() {
		 statements.setItems(FXCollections.observableArrayList(Examples.exampleList()));
		// To set selection model
		 statements.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	 
	      // Select item at index
		 statements.getSelectionModel().selectIndices(1);
		 displayButton.setOnAction(actionEvent -> {
			 int index = statements.getSelectionModel().getSelectedIndex();
			 if (index < 0)
				 return;
			 IStmt stmt = Examples.exampleList()[index];
			 MyIStack<IStmt> stk = new MyStack<>();
			 MyIDictionary<String, Value> sTable = new MyDictionary<>();
			 MyIList<Value> ot = new MyList<>();
			 MyFileTable<StringValue, BufferedReader> ft = new MyFileTable<>();
			 ICyclicBarrier<IntValue, PairBarrier<IntValue, List<Integer>>> barrier = new CyclicBarrier<>();
			 PrgState prg = new PrgState(stk,sTable,ot,ft,barrier,stmt);
			 MyIRepository repo = new MyRepository(prg, "log"+index+".txt");
			 Controller ctr = new Controller(repo);
			 try{
				 ctr.runTypeChecker();
				 programViewController.setController(ctr);
			 } catch (MyException exception) {
				 Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage(), ButtonType.OK);
				 alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
				 alert.showAndWait();
			 }
		 });

	 }
	 
	 
}

package application;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.AssignmentStm;
import model.CompoundStm;
import model.ConstExpr;
import model.ForkStm;
import model.IStatement;
import model.Pair;
import model.PrgState;
import model.PrintStm;
import model.VarExpr;
import model.newStm;
import model.readExpr;
import model.writeStm;
import repository.IRepo;
import repository.Repo;
import util.MyFileTable;
import util.MyHeap;
import util.MyOutput;
import util.MyStack;
import util.MySymTable;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.event.ChangeListener;

import controller.Controller;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ChoosePrgController {
	
	private Stage mainStage;
	@FXML
	private Button changeSceneButton;
	@FXML
	private ListView<IStatement> prgList;
	ArrayList<Controller> ctrls;
	IStatement stmToExec;
	
	
	public void populate(){
		CompoundStm stm1 = new CompoundStm(new CompoundStm(new CompoundStm(new AssignmentStm("v", new ConstExpr(10)), new newStm("a", new ConstExpr(22))), 
				new ForkStm(new CompoundStm(new writeStm("a", new ConstExpr(30)), new CompoundStm(new AssignmentStm("v", new ConstExpr(32)), new CompoundStm(new PrintStm(new VarExpr("v")), new PrintStm(new readExpr("a"))))))), new CompoundStm(new PrintStm(new VarExpr("v")), new PrintStm(new readExpr("a"))));
		
		CompoundStm stm2 = new CompoundStm(new CompoundStm(new CompoundStm(new AssignmentStm("v", new ConstExpr(10)), new newStm("a", new ConstExpr(22))), 
				new ForkStm(new CompoundStm(new writeStm("a", new ConstExpr(30)), new CompoundStm(new CompoundStm(new AssignmentStm("v", new ConstExpr(32)), new PrintStm(new VarExpr("v"))), new CompoundStm(new ForkStm(new PrintStm(new VarExpr("v"))), new PrintStm(new readExpr("a"))))))), new CompoundStm(new PrintStm(new VarExpr("v")), new PrintStm(new readExpr("a"))));
		
		MyStack<IStatement> stack1 = new MyStack<>();
		MySymTable<String, Integer> symTable1 = new MySymTable<>();
		MyOutput<Integer> output1 = new MyOutput<>();
		MyFileTable<Integer, Pair> fileTable1 = new MyFileTable<>();
		MyHeap<Integer> heap1 = new MyHeap<>();
		PrgState p1 = new PrgState(stack1, symTable1, output1, fileTable1,heap1, stm1, 1);
		
		IRepo repo1 = new Repo("file1.txt");
		repo1.addProgram(p1);
		Controller c1 = new Controller(repo1);
		
		
		MyStack<IStatement> stack2 = new MyStack<>();
		MySymTable<String, Integer> symTable2 = new MySymTable<>();
		MyOutput<Integer> output2 = new MyOutput<>();
		MyFileTable<Integer, Pair> fileTable2 = new MyFileTable<>();
		MyHeap<Integer> heap2 = new MyHeap<>();
		PrgState p2 = new PrgState(stack2, symTable2, output2, fileTable2, heap2, stm2, 1);
		
		
		IRepo repo2 = new Repo("file2.txt");
		repo2.addProgram(p2);
		Controller c2 = new Controller(repo2);
		
		ctrls = new ArrayList<>();
		ctrls.add(c1);
		ctrls.add(c2);
		
	}
	
	private ObservableList<IStatement> getPrgList() {
		 
		 ArrayList<IStatement> prg = new ArrayList<>();
		 ctrls.forEach(c->prg.add(c.getRepo().getPrgs().get(0).getPrg()));
		
	     ObservableList<IStatement> prgs = FXCollections.observableArrayList(prg);
	     
	     return prgs;
	  }
	@FXML
	public void initialize() {
		  populate();
		  
		  prgList.setItems(getPrgList());
		  
	      prgList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

	      prgList.getSelectionModel().selectIndices(1);
	 
	      prgList.getFocusModel().focus(2);
	      
	 }
	
	public ArrayList<Controller> getCtrls() {
		return this.ctrls;
	}
	public void handleButtonAction(ActionEvent event) throws IOException{
		/*
		Stage stage; 
		Parent root;
		
			stage=(Stage) changeSceneButton.getScene().getWindow();
	        root = FXMLLoader.load(getClass().getResource("MainC.fxml"));
	        Scene scene = new Scene(root, 400, 400);
	        stage.setScene(scene);
	        stage.show();
	    */    
	   FXMLLoader loader = new FXMLLoader(getClass().getResource("MainC.fxml"));
	   Scene secondScene = new Scene(loader.load());
	   SampleController controller = loader.getController();
	   controller.setProgram(this.stmToExec);
	   
	   Stage newWindow = new Stage();
	   newWindow.setTitle("INTERPRETER");
	   newWindow.setScene(secondScene);
	   
	   newWindow.initModality(Modality.WINDOW_MODAL);
	   
	   newWindow.initOwner(mainStage);
	   
	   newWindow.setX(mainStage.getX() - 400);
	   newWindow.setY(mainStage.getY());
	   
	   newWindow.show();
	}
	
	public void setStage(Stage stage) {
		mainStage = stage;
		
	}
	
	public void handleMouseClick(MouseEvent event) {
		stmToExec = prgList.getSelectionModel().getSelectedItem();
	}
	
	public IStatement getStm() {
		return this.stmToExec;
	}
	
		
}

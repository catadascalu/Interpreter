package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;

import controller.Controller;
import exception.MyException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.IStatement;
import model.Pair;
import model.PrgState;
import repository.IRepo;
import repository.Repo;
import util.FileTableTuple;
import util.HeapTuple;
import util.IFileTable;
import util.IHeap;
import util.ISymTable;
import util.MyFileTable;
import util.MyHeap;
import util.MyOutput;
import util.MyStack;
import util.MySymTable;
import util.TableTuple;

public class SampleController {
	@FXML
	private Button runButton;
	@FXML
	private TextField textField;
	@FXML 
	private ListView<Integer> prgIDList;
	@FXML
	private ListView<IStatement> stack; 
	@FXML
	private ListView<Integer> output;
	@FXML
	private TableView<TableTuple> symtable;
	
	@FXML
	private TableColumn<TableTuple, String> varName;
	
	@FXML
	private TableColumn<TableTuple, Integer> value;
	
	@FXML
	private TableView<FileTableTuple> fileTable;
	//ChoosePrgController ctrl2;
	@FXML
	private TableColumn<FileTableTuple, Integer> fd;
	@FXML
	private TableColumn<FileTableTuple, String> fileName;
	
	@FXML
	private TableView<HeapTuple> heap;
	//ChoosePrgController ctrl2;
	@FXML
	private TableColumn<HeapTuple, Integer> address;
	@FXML
	private TableColumn<HeapTuple, Integer> heapValue;
	
	IStatement stm;
	//ArrayList<PrgState> programStates;
	Controller ctrl;
	
	
	public void setProgram(IStatement originalProgram) {
		this.stm = originalProgram;
		PrgState program = new PrgState(new MyStack<>(), new MySymTable<>(), new MyOutput<>(), new MyFileTable<>(), new MyHeap<>(), this.stm, 1);
		IRepo repo = new Repo("file1.txt");
		repo.addProgram(program);
		ctrl = new Controller(repo);
		this.textField.setText(new Integer(repo.getPrgs().size()).toString());
		this.textField.setEditable(false);		
		
		//this.prgIDList = new ListView<Integer> ();
		this.prgIDList.setItems(getPrgIDList());
		
		initStack();
		prgIDList.setItems(getPrgIDList());
		//stack.setItems(getStack());
		output.setItems(getOutput());
		initSymTable();
		initFileTable();
		initHeap();
		
	}
	
	
	private ObservableList<Integer> getPrgIDList(){
		ArrayList<Integer> ids = new ArrayList<Integer>();
		//this.ctrl.getRepo().getPrgs().forEach(p->ids.add(p.getId()));
		for(PrgState p : this.ctrl.getRepo().getPrgs())
			ids.add(p.getId());
		ObservableList<Integer> list = FXCollections.observableArrayList(ids);
		return list;
		
	}
	
	private ObservableList<IStatement> getStack(){
		ArrayList<IStatement> stms = new ArrayList<IStatement>();
		for(PrgState p : this.ctrl.getRepo().getPrgs())
		{
			if(p.getPrg().equals(this.stm))
				if(p.getExecStack().size() != 0)
					p.getExecStack().getStack().forEach(e -> stms.add(e));
					
		}
		ObservableList<IStatement> list = FXCollections.observableArrayList(stms);
		return list;
		
	}
	
	private ObservableList<Integer> getOutput(){
		ArrayList<Integer> output = new ArrayList<Integer>();
		for(PrgState p : this.ctrl.getRepo().getPrgs())
		{
			if(p.getPrg().equals(this.stm))
				if(p.getOutput().size() != 0)
					p.getOutput().getOutput().forEach(e -> output.add(e));
					
		}
		ObservableList<Integer> list = FXCollections.observableArrayList(output);
		return list;
		
	}
	
	public PrgState getPrgBySt()
	{
		for(PrgState p: ctrl.getRepo().getPrgs())
		{
			if(p.getPrg().equals(stm))
				return p;
		}
		return null;
	}
	
	private void initSymTable()
	{
		
		varName.setCellValueFactory(new PropertyValueFactory<>("varName"));
		value.setCellValueFactory(new PropertyValueFactory<>("value"));
		
		
		symtable.setItems(getSymTable());
	
	}
	
	private ObservableList<TableTuple> getSymTable(){
		
		ObservableList<TableTuple> tuples = FXCollections
                .observableArrayList();

        ISymTable<String, Integer> sym = getPrgBySt().getSymTable();
        
        Map<String, Integer> dict = sym.getContent();

         for(Entry<String, Integer> a: dict.entrySet())
         {
        	 TableTuple s = new TableTuple();
        	 s.setVarName(a.getKey());
        	 s.setValue(a.getValue());
        	 tuples.add(s);
        	 
         }
        

        return tuples;
		
	}
	
	private void initFileTable()
	{
		
		fileName.setCellValueFactory(new PropertyValueFactory<>("fileName"));
		fd.setCellValueFactory(new PropertyValueFactory<>("fd"));
		
		
		fileTable.setItems(getFileTable());
	
	}
	
	private ObservableList<FileTableTuple> getFileTable(){
		
		ObservableList<FileTableTuple> tuples = FXCollections
                .observableArrayList();

        IFileTable<Integer, Pair> file = getPrgBySt().getFileTable();
        
        Map<Integer, Pair> dict = file.getContent();

         for(Entry<Integer, Pair> a: dict.entrySet())
         {
        	 FileTableTuple s = new FileTableTuple();
        	 s.setfileName(a.getValue().getFileName());
        	 s.setDescriptor(a.getKey());
        	 tuples.add(s);
        	 
         }
        

        return tuples;
	}
	
	private void initHeap()
	{
		
		address.setCellValueFactory(new PropertyValueFactory<HeapTuple, Integer>("address"));
		heapValue.setCellValueFactory(new PropertyValueFactory<HeapTuple, Integer>("heapValue"));
		
		
		heap.setItems(getHeap());
	
	}
	
	private ObservableList<HeapTuple> getHeap(){
		
		ObservableList<HeapTuple> tuples = FXCollections
                .observableArrayList();

        IHeap<Integer> heap = getPrgBySt().getHeap();
        
        Map<Integer, Integer> dict = heap.getContent();

         for(Entry<Integer, Integer> a: dict.entrySet())
         {
        	 HeapTuple s = new HeapTuple();
        	 s.setAddress(a.getKey());
        	 s.setHeapValue(a.getValue());
        	 tuples.add(s);
        	 
         }
        

        return tuples;
	}
	
	
	public void initStack() {
		this.stack.setItems(getStack());
	}
	
	public void fillOutput() {
		this.output.setItems(getOutput());
	}
	
	
	public void setFileTable() {
		this.fileTable.setItems(getFileTable());
		
	}
	
	public void setHeap() {
		this.heap.setItems(getHeap());
		
	}
	
	public void handleButtonAction(ActionEvent event) throws InterruptedException {
		this.ctrl.setExecutor(Executors.newFixedThreadPool(2));
		List<PrgState> notCompleted = this.ctrl.removeCompletedPrgStates(this.ctrl.getRepo().getPrgs());
		if(notCompleted.size() > 0)
		try {
		this.ctrl.oneStepForAll(notCompleted);
		if(notCompleted.get(0).getPrg().equals(stm) == false)
			this.stm = notCompleted.get(0).getPrg();
		initStack();
		initSymTable();
		initFileTable();
		initHeap();
		fillOutput();
		this.prgIDList.setItems(getPrgIDList());
		this.textField.setText(new Integer(this.ctrl.getRepo().getPrgs().size()).toString());
		}
		catch(MyException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	//@FXML
	public void initPrgStatesIDList() {
		//this.prgIDList = new ListView<>();
		//ArrayList<Integer> ids = new ArrayList<>();
		//this.ctrl.getRepo().getPrgs().forEach(p->ids.add(p.getId()));
		//ObservableList<Integer> list = FXCollections.observableArrayList(ids);
		this.prgIDList.setItems(getPrgIDList());
	}
	
	public void handleMouseClick(MouseEvent event) {
		int id = prgIDList.getSelectionModel().getSelectedItem();
		for(PrgState p : this.ctrl.getRepo().getPrgs()) {
			if(p.getId() == id) {
				this.stm = p.getPrg();
				this.initFileTable();
				this.initHeap();
				this.initStack();
				this.initSymTable();
			}
			
		}
	}
	
}

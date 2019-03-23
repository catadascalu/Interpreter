package model;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import exception.MyException;
import util.IExecStack;
import util.IFileTable;
import util.ISymTable;

public class openStm implements IStatement{
	private String var_file_id;
	private String fileName;
	
	public openStm(String varName, String fName) {
		var_file_id = varName;
		fileName = fName;
	}
	
	@Override
	
	public String toString() {
		return var_file_id + " --> " + fileName;
	}
	
	public PrgState execute(PrgState prg){
		//IExecStack<IStatement> stack = prg.getExecStack();
		ISymTable<String, Integer> table = prg.getSymTable();
		IFileTable<Integer, Pair> fileTable = prg.getFileTable();
		
		/*
		if(!fileTable.contains(fileName)) {
			File file = new File(fileName);
			 
			Desktop desktop = Desktop.getDesktop();
			if(file.exists()) {
				try {
					desktop.open(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			*/
		if(!fileTable.contains(fileName)) {
			try {
				BufferedReader bf = new BufferedReader(new FileReader(fileName));
				fileTable.add(new Integer(fileTable.getDescriptor()+1), new Pair(fileName, bf));
				table.add(var_file_id, fileTable.getDescriptor()+1);
				fileTable.setDescriptor(fileTable.getDescriptor()+1);
			} catch(MyException e) {
				System.out.println(e.getMessage());
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
		}
		return null;
	}
}

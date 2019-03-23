package repository;
import model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.*;

import exception.MyException;

public class Repo implements IRepo{
	List<PrgState> myProgramState;
	int current;
	String logFilePath;
	
	public Repo(String path) {
		myProgramState = new ArrayList<>();
		current = 0;
		logFilePath = path;
		
	}
	@Override
	public void setCurrent(int value) {
		this.current = value;
	}
	
	@Override
	public List<PrgState> getPrgs(){
		return this.myProgramState;
	}
	@Override
	public void addProgram(PrgState prgState) {
		myProgramState.add(prgState);
	}
	/*
	@Override
	public PrgState getCrtPrg() throws MyException{
		if(this.myProgramState.size() !=0) {
		return this.myProgramState.get(current);
		}
		else
			throw new MyException("Empty repo. No more program states.\n");
	}
	*/
	/*
	public PrgState getNextPrg() throws MyException{
		current = current + 1;
		if(this.myProgramState.size() !=0) {
			return this.myProgramState.get(current);
			}
			else
				throw new MyException("Empty repo. No more program states.\n");
		
	}
	*/
	@Override
	public void displayAll()throws MyException {
		if(this.myProgramState.size() != 0) {
			Integer i = 1;
			for(PrgState p : this.myProgramState) {
				System.out.println("PROGRAM " + i.toString() + ": \n" + p.getPrg().toString());
				i=i+1;
			}
		}
		else
			throw new MyException("No programs.");
	}
	@Override
	public void clear() throws IOException {
		PrintWriter clear = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, false)));
	}
	@Override
	public void logPrgStateExec(PrgState p, int i) throws IOException {
		String newLine = System.getProperty("line.separator");
		PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
		logFile.append("PROGRAM " + new Integer(p.getId()).toString());
		logFile.append(newLine);
		logFile.append("ITERATION:  " + new Integer(i).toString());
		logFile.append(newLine);
		logFile.append("STACK:");
		logFile.append(newLine);
		logFile.append(p.getExecStack().toString());
		logFile.append(newLine);
		logFile.append("SYMTABLE:");
		logFile.append(newLine);
		logFile.append(p.getSymTable().toString());
		logFile.append(newLine);
		logFile.append("OUTPUT:");
		logFile.append(newLine);
		logFile.append(p.getOutput().toString());
		logFile.append(newLine);
		logFile.append("FILETABLE:");
		logFile.append(newLine);
		logFile.append(p.getFileTable().toString());
		logFile.append(newLine);
		logFile.append("HEAP:");
		logFile.append(newLine);
		logFile.append(p.getHeap().toString());
		logFile.append(newLine);
		
		//logFile.append(p.toString());
		//logFile.append(newLine);
		//logFile.println(p.toString());
		logFile.flush();
		logFile.close();
	}
	@Override
	public void setPrgList(List<PrgState> list) {
		this.myProgramState = list;
		
	}
}

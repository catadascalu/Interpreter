package controller;

import repository.*;
import util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import exception.MyException;
import model.*;
public class Controller {
	IRepo repository;
	ExecutorService executor;
	
	public Controller(IRepo repo) {
		repository = repo;
	}
	
	public Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer, Integer> heap){
		return heap.entrySet().stream().filter(e->symTableValues.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
	
	
	public List<Integer> closeOpenFiles(Collection<Integer> symbolTableValues,Map<Integer, Pair> fileTable) {
		return fileTable.entrySet().stream().filter(entry -> symbolTableValues.contains(entry.getKey())).map(entry -> entry.getKey()).collect(Collectors.toList());
	}
	
	public void oneStepForAll(List<PrgState> list) throws InterruptedException, MyException{
		
		list.forEach(prg->{
			try {
				repository.logPrgStateExec(prg, 0);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		
		List<Callable<PrgState>> callList = list.stream().map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();})).collect(Collectors.toList());
		
		List<PrgState> executionList = executor.invokeAll(callList).stream().map(future -> { try {
			return future.get();
		} 
		
		catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		}).filter(p->p!=null).collect(Collectors.toList());
		
		list.addAll(executionList);
		
		list.forEach(prg -> {
			try {
				repository.logPrgStateExec(prg, 0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		
		repository.setPrgList(list);
	}
	/*
	public void allStep() throws IOException, MyException {
		PrgState p = new PrgState();
		
		try {
			p = this.repository.getCrtPrg();
		}
		catch(MyException e) {
			System.out.println(e.getMessage());
		}
		Integer i =0;
		while(!p.getExecStack().isEmpty()) {
			System.out.println("Iteration " + i.toString() + "\n");
			//try {
			executeOneStep(p);
			p.getHeap().setContent(conservativeGarbageCollector(p.getSymTable().getContent().values(), p.getHeap().getContent()));
			this.repository.logPrgStateExec(p, i);
			i = i+1;
			
			//}
			/*catch(MyException e) {*/
				//i = i+1;
				/*System.out.println(e.getMessage());
			}*/
			//System.out.println(p.toString());
			/*finally {
				for(Integer e : closeOpenFiles(p.getSymTable().getContent().values(), p.getFileTable().getContent())) {
					p.getFileTable().lookUp(e).getBF().close();
				}
			}
		}
		
	}
	*/
	
	public void allStep() throws InterruptedException, MyException {
		executor = Executors.newFixedThreadPool(2);
		
		List<PrgState> notCompleted = removeCompletedPrgStates(repository.getPrgs());
		
		while(notCompleted.size() > 0) {
			
			//IHeap<Integer> heap  = notCompleted.get(0).getHeap();
			//notCompleted.forEach(p -> heap.setContent(conservativeGarbageCollector(p.getSymTable().getContent().values(), heap.getContent())));
			
			
			oneStepForAll(notCompleted);
			
			notCompleted = removeCompletedPrgStates(repository.getPrgs());
		}
		
		executor.shutdownNow();
		
		repository.getPrgs().forEach(p->System.out.println(p.toString()));
		
		List<PrgState> tempList = repository.getPrgs();
		IFileTable<Integer, Pair> fileTable = tempList.get(0).getFileTable();
		tempList.forEach(p -> {
		for(Integer e : closeOpenFiles(p.getSymTable().getContent().values(), 
				fileTable.getContent())) {
			try {
				fileTable.lookUp(e).getBF().close();
			} catch (IOException | MyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				fileTable.delete(e);
			} catch (MyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}}
		});
		repository.setPrgList(notCompleted);
	}
	
	
	public IRepo getRepo() {
		return this.repository;
	}
	
	public ExecutorService getExecutor() {
		return this.executor;
	}
	public void setExecutor(ExecutorService ex) {
		this.executor = ex;
	}
	public void displayAll() {
		try {
		this.repository.displayAll();
		}
		catch(MyException e) {
			System.out.println(e.getMessage());
		}
	}

	
	public List<PrgState> removeCompletedPrgStates(List<PrgState> inPrgList){
		return inPrgList.stream().filter(p->p.isNotCompleted()).collect(Collectors.toList());
	}
}

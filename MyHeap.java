package util;

import java.util.HashMap;
import java.util.Map;

import exception.MyException;

public class MyHeap<Integer> implements IHeap<Integer>{
	Map<Integer, Integer> heap;
	int firstFree;
	
	public MyHeap() {
		heap = new HashMap<Integer, Integer>();
		firstFree = 1;
	}
	@Override
	public void add(Integer address, Integer content)throws MyException {
		if(address.equals(0))
			throw new MyException("Invalid address.\n");
		this.heap.put(address, content);	
	}

	@Override
	public Integer lookUp(Integer address) {
		return this.heap.get(address);
	}

	@Override
	public int size() {
		return this.heap.size();
	}

	@Override
	public int getFirstFree() {
		return this.firstFree;
	}

	@Override
	public void setFirstFree(int ff)throws MyException {
		if(ff == 0)
			throw new MyException("Can't set to invalid address.\n");
		this.firstFree = ff;
	}
	
	@Override
	public String toString() {
		String s = "";
		if(this.heap.size() == 0)
			return s + "empty.";
		for(HashMap.Entry<Integer, Integer> e : heap.entrySet()) {
			s = s + e.getKey().toString() + " --> " + e.getValue().toString() + "      "+ "\n";
		}
		return s;
	}
	@Override
	public void update(Integer address, Integer content) throws MyException {
		if(address.equals(0))
			throw new MyException("Invalid address for update.\n");
		
		if(this.contains(address)){
			this.heap.put(address, content);
		}
		else
			throw new MyException("Invalid address.\n");
	}
	@Override
	public boolean contains(Integer address) {
		return this.heap.get(address) != null;
	}
	@Override
	public void setContent(Map<Integer, Integer> map) {
		this.heap = map;
		
	}
	@Override
	public Map<Integer, Integer> getContent() {
		return this.heap;
	}

}

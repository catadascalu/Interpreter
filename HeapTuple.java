package util;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HeapTuple {

	
	private SimpleIntegerProperty address;
	private SimpleIntegerProperty heapValue;
	
	public HeapTuple(Integer a, Integer v)
	{
		address = new SimpleIntegerProperty(a);
		heapValue = new SimpleIntegerProperty(v);
	}
	
	public HeapTuple() {
		// TODO Auto-generated constructor stub
		address = new SimpleIntegerProperty();
		heapValue = new SimpleIntegerProperty();
	}

	public Integer getAddress()
	{
		return address.get();
		
	}
	
	public Integer getHeapValue()
	{
		return heapValue.get();
	}

	public void setAddress(Integer a)
	{
		address.set(a);
	}
	
	public void setHeapValue(Integer v)
	{
		heapValue.set(v);
	}
}

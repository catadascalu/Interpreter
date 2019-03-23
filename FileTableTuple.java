package util;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public class FileTableTuple {
	
	
	private final SimpleIntegerProperty fd;
	private final SimpleStringProperty fileName;
	
	public FileTableTuple(Integer descriptor, String fn)
	{
		fd = new SimpleIntegerProperty(descriptor);
		fileName = new SimpleStringProperty(fn);
	}
	
	public FileTableTuple() {
		// TODO Auto-generated constructor stub
		fd = new SimpleIntegerProperty();
		fileName = new SimpleStringProperty();
	}

	public String getfileName()
	{
		return fileName.get();
		
	}
	
	public Integer getDescriptor()
	{
		return fd.get();
	}

	public void setfileName(String name)
	{
		fileName.set(name);
	}
	
	public void setDescriptor(Integer v)
	{
		fd.set(v);
	}
	

}

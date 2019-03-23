package model;

import java.io.BufferedReader;

public class Pair{
	String fileName;
	BufferedReader instance;
	
	public Pair(String fn, BufferedReader bf) {
		this.fileName = fn;
		this.instance = bf;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public BufferedReader getBF() {
		return this.instance;
	}
	
	public void setFileName(String fn) {
		this.fileName = fn;
	}
	
	public void setBF(BufferedReader bf) {
		this.instance = bf;
	}
	
	@Override
	public String toString() {
		return this.fileName + " --> " + this.instance.toString() + "\n";
	}
}

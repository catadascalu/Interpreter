package repository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exception.MyException;
import model.PrgState;

public interface IRepo {
	void setCurrent(int value);
	List<PrgState> getPrgs();
	void setPrgList(List<PrgState> list);
	void addProgram(PrgState prgState);
	//PrgState getCrtPrg()throws MyException;
	//PrgState getNextPrg() throws MyException;
	void displayAll() throws MyException;
	void logPrgStateExec(PrgState p, int i) throws IOException;
	void clear()throws IOException;
	
}

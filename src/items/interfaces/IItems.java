package items.interfaces;

import items.excecoes.OutofItemsException;

/**
 * @author Vicente
 * @author Felipe Moret
 */

public interface IItems {
	
	public String getName();
	public int getNumber();
	public void setNumbers(int number);
	public void increase();
	public void increase(int quantidade);
	public void decrease();
	public void effect()throws OutofItemsException;
	public void notifica();
	
}

package visual;

import anima.component.ISupports;

/**
 * Interface que define aqueles métodos que executam alguma ação quando um
 * evento o chama;
 * 
 * @author eitiyamamoto
 *
 */
public interface IAction extends ISupports{
	
	public int getKey();
	
	public void setKey(int key);
	
	public void execute();
}

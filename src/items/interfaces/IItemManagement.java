package items.interfaces;

import java.util.ArrayList;

import items.excecoes.OutofItemsException;
import items.itemManagement.ItemsList;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;


/**
 * @author Vicente
 * @author Felipe Moret
 */

@ComponentInterface("<http://santanvarzea.com/items.interfaces.IItemManagement>")
public interface IItemManagement extends ISupports {
	
	public void startInventoryNumbers(ArrayList<Integer> quantities);
	public void useItem(ItemsList place) throws OutofItemsException;
	public void obtainItem(ItemsList place);
	public void obtainItem(ItemsList place, int quantidade);
	public int displayNumber(ItemsList place);
	public void setNumber(ItemsList place, int number);
	public String displayName(ItemsList place);
}

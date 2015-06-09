package items.interfaces;

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
	
	public void useItem(ItemsList place) throws OutofItemsException;
	public void obtainItem(ItemsList place);
	public int displayNumber(ItemsList place);
	public String displayName(ItemsList place);
}

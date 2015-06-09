package items.interfaces;

import items.itemManagement.ItemsList;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;


/**
 * @author Vicente
 * @author Felipe Moret
 */

@ComponentInterface("<http://santanvarzea.com/items.interfaces.IItemManagement>")
public interface IItemManagement extends ISupports {
	
	public void useItem(ItemsList place);
	public void obtainItem(ItemsList place);
	public int displayNumber(ItemsList place);
	public String displayName(ItemsList place);
}

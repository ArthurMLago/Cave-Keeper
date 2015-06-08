package items.interfaces;

import anima.annotation.ComponentInterface;
import anima.component.ISupports;


/**
 * @author Vicente
 * @author Felipe Moret
 */

@ComponentInterface("<http://santanvarzea.com/items.interfaces.IItemManagement>")
public interface IItemManagement extends ISupports {
	
	public void useItem(int palace);
	public void obtainItem(int place);
	public int displayNumber(int place);
	public String displayName(int place);
}

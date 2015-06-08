package items.interfaces;

import anima.component.ISupports;


/**
 * @author Vicente
 *
 */
public interface IItemManagement extends ISupports {
	
	public void useItem(int palace);
	public void obtainItem(int place);
	public int displayNumber(int place);
	public String displayName(int place);
}

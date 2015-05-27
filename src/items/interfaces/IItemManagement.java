package items.interfaces;

public interface IItemManagement {
	
	public void startInventory();
	public void useItem(int palace);
	public void obtainItem(int place);
	public int displayNumber(int place);
	public String displayName(int place);
}

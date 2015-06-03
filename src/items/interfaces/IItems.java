package items.interfaces;

/**
 * @author Vicente
 * metodos: [getters]: getName(), getNumber()
 *          [setters]: increase(), decrese()
 *          effect: chamada quando o item for usado
 *          notifica: chama notificacao, normalmente precedida ao uso ou obtencao de item 
 */
public interface IItems {
	
	public String getName();
	public int getNumber();
	public void setNumber(int number);
	public void increase();
	public void decrease();
	public void effect();
	public void notifica();
	
}

package items.interfaces;

import items.excecoes.OutofItemsException;

/**
 * @author Vicente
 * @author Felipe Moret
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
	public void effect()throws OutofItemsException;
	public void notifica();
	
}

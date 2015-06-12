package ioComponent;

/**
 * Interface que define aqueles métodos que executam alguma ação quando um
 * evento o chama;
 * 
 * @author eitiyamamoto
 *
 */
public interface IAction {

	/**
	 * Retorna a chave utilizada para executar a ação
	 * 
	 * @return key - chave da ação
	 */
	public int getKey();

	/**
	 * Define a chave utilizada para executar a ação
	 * 
	 * @param key
	 *            - Chave da ação
	 */
	public void setKey(int key);

	/**
	 * Executa a ação desejada
	 */
	public void execute();
}

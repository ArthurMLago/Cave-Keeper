package visual.interfaces;

import player.IPlayerAction;

/**
 * Interface de ação que depende de um player
 * @author eitiyamamoto
 *
 */
public interface IActionPlayer extends IAction{
	/**
	 * Conecta um player que terá uma função executada
	 * 
	 * @param player - Player utilizado;
	 */
	public void connect(IPlayerAction player);
}

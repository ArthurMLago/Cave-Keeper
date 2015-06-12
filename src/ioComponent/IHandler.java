package ioComponent;

import org.newdawn.slick.Input;

/**
 * Interface que administra as ações executas por uma ação;
 * @author eitiyamamoto
 *
 */
public interface IHandler {
	/**
	 * Verifica se há alguma ação registrada para o comando
	 * realizado
	 * @param input - Responsável por observar as entradas
	 */
	public void handle(Input input);
}

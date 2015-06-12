package ioComponent;

import visual.interfaces.IMapVisual;

/**
 * Interface de ação que necessita de um mapvisual
 * 
 * @author eitiyamamoto
 *
 */
public interface IActionMapVisual extends IAction{
	/**
	 * Conecta um mapa a ser utilizado na execução da ação
	 * 
	 * @param map - Mapa que terá ua função executada
	 */
	public void connect(IMapVisual map);
}

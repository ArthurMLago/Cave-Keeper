package visual.interfaces;

import map.interfaces.IGameMap;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;
import gameController.GameController;
import gameController.IGameController;
/**
 * Interface do Mapa Visual e suas ações
 * @author eitiyamamoto
 *
 */

@ComponentInterface("<http://cave.com/visual.IMapVisual>")
public interface IMapVisual extends ISupports{
	
	/**
	 * Realiza a ação flare que ilumina todo o mapa por um período de tempo
	 */
	public void flareVisual();
	
	/**
	 * Representa visualmente o local onde a bala chegou
	 * 
	 * @param direction - Recebe o lado que o tiro foi
	 */
	public void shootDirection(char direction);
	
	/**
	 * Muda o lado que o player está olhando
	 * @param facing - Lado que o player está olhando
	 */
	public void changePlayerFacing(char facing);
	
	public void playFootstep(float gain);
	
	public void start();
	
	public void end();
	
	public void message(String messageTxt);
}

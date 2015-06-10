package visual.interfaces;

import anima.component.ISupports;
import gameController.GameController;
import gameController.IGameController;
/**
 * Interface do Mapa Visual e suas ações
 * @author eitiyamamoto
 *
 */
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
	
	public void playEffect(float gain, String type);
	
	public void connect(GameController gameController);
	
	public void start();
	
	public void end();
	
	public void message(String messageTxt);
}

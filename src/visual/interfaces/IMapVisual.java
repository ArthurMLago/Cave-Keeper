package visual.interfaces;

import anima.component.ISupports;
import gameController.IGameController;
/**
 * Interface do Mapa Visual e suas ações
 * @author eitiyamamoto
 *
 */
public interface IMapVisual extends ISupports{
	/**
	 * Conecta um gameController
	 * @param gameController
	 */
	public void connect(IGameController gameController);
	
	/**
	 * Realiza a ação flare que ilumina todo o mapa por um período de tempo
	 */
	public void flareVisual();
	
	public void shoot();
}

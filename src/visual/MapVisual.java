package visual;

import gameController.IGameController;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import visual.interfaces.IAudioEffect;
import visual.interfaces.IMapVisual;
import anima.component.base.ComponentBase;

public class MapVisual extends ComponentBase implements IMapVisual,
		IAudioEffect {
	private SlickMap compositeMap;
	private IGameController gameController;
	private AppGameContainer agc;

	public MapVisual() {
		compositeMap = new SlickMap("Cave's Keeper");
	}

	@Override
	public void connect(IGameController gameController) {
		this.gameController = gameController;
		compositeMap.connect(gameController);
		try {
			agc = new AppGameContainer(compositeMap);
			agc.setDisplayMode(gameController.getMap().getLimiteX() * 32,
					gameController.getMap().getLimiteY() * 32, false);
			agc.start();
		} catch (SlickException e) {
			Logger.getLogger(MapVisual.class.getName()).log(Level.SEVERE, null,
					e);
		}
	}

	@Override
	public void flareVisual() {
		compositeMap.flare();
	}

	@Override
	public void playEffect(float gain, String type) {
		if ("footstep".compareTo(type) == 0)
			compositeMap.playFootstep(gain);
	}

	@Override
	public void shoot() {
		compositeMap.shoot();
	}
}

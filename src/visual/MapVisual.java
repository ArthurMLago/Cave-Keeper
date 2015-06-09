package visual;

import gameController.GameController;
import gameController.IGameController;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import visual.interfaces.IAudioEffect;
import visual.interfaces.IMapVisual;
import anima.annotation.Component;
import anima.component.base.ComponentBase;

@Component(id = "<http://cave.com/visual.MapVisual>", provides = { "<http://cave.com/visual.IMapVisual>" })
public class MapVisual extends ComponentBase implements IMapVisual,
		IAudioEffect {
	private SlickMap compositeMap;
	private AppGameContainer agc;
	private GameController gameController;
	public static int SIZEIMAGE = 32;

	public MapVisual() {
		compositeMap = new SlickMap("Cave's Keeper");
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
	public void shootDirection(char direction) {
		compositeMap.shootDirection(direction);
	}

	@Override
	public void changePlayerFacing(char facing) {
		compositeMap.faceSprite(facing);
	}

	@Override
	public void connect(GameController gameController) {
		this.gameController = gameController;
		try {
			agc = new AppGameContainer(compositeMap);
			agc.setDisplayMode(gameController.getMap().getLimitX() * SIZEIMAGE,
					gameController.getMap().getLimitY() * SIZEIMAGE, false);
			agc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
}

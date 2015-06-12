package ioComponent.actions;

import ioComponent.Facing;
import ioComponent.interfaces.IActionPlayerMapVisual;
import gameController.GameController;
import visual.interfaces.IMapVisual;
import player.IPlayerMax;

public class PlayerShootLeftAction implements IActionPlayerMapVisual {
	private int key;
	private IPlayerMax player;
	private IMapVisual map;

	@Override
	public int getKey() {
		return key;
	}

	@Override
	public void setKey(int key) {
		this.key = key;
	}

	@Override
	public void execute() {
		if (player.shoot(Facing.WEST))
			map.shootDirection(Facing.WEST);
		player.checkLighter();
		
	}

	@Override
	public void connect(IPlayerMax player) {
		this.player = player;
	}

	@Override
	public void connect(IMapVisual map) {
		this.map = map;
	}
}
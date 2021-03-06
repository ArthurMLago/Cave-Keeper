package ioComponent.actions;

import ioComponent.Facing;
import ioComponent.interfaces.IActionPlayer;
import gameController.GameController;
import player.IPlayerMax;

public class PlayerUpAction implements IActionPlayer {
	private int key;
	private IPlayerMax player;

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
		if (player.move(Facing.NORTH)) {
			GameController.getSharedInstance().move();
			player.checkLighter();
		}
	}

	@Override
	public void connect(IPlayerMax player) {
		this.player = player;
	}
	
}

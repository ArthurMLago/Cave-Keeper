package ioComponent;

import player.IPlayerMax;
import gameController.GameController;

public class PlayerRightAction implements IActionPlayer {
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
		if(player.move(Facing.EAST)){
			GameController.getSharedInstance().move();
			player.checkLighter();
		}
	}

	@Override
	public void connect(IPlayerMax player) {
		this.player = player;
	}

}
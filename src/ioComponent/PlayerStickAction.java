package ioComponent;

import player.IPlayerMax;

public class PlayerStickAction implements IActionPlayer {
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
		player.useStick();
		player.checkLighter();
	}

	@Override
	public void connect(IPlayerMax player) {
		this.player = player;
	}

}
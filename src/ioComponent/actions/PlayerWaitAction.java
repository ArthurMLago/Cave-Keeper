package ioComponent.actions;

import ioComponent.interfaces.IActionPlayer;
import player.IPlayerMax;

public class PlayerWaitAction implements IActionPlayer {
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
		player.checkLighter();
	}

	@Override
	public void connect(IPlayerMax player) {
		this.player = player;
	}

}
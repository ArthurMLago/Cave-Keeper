package ioComponent.actions;

import player.IPlayerMax;
import ioComponent.interfaces.IActionPlayer;

public class PlayerFlashAction implements IActionPlayer {
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
		player.useFlash();
		player.checkLighter();
	}

	@Override
	public void connect(IPlayerMax player) {
		this.player = player;
	}

}
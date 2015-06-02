package player;

import visual.IActionPlayer;
import player.IPlayerAction;

public class PlayerDownAction implements IActionPlayer {
	private int key;
	private IPlayerAction player;

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
		player.move('S');
	}

	@Override
	public void connect(IPlayer player) {
		this.player = player;
	}

}
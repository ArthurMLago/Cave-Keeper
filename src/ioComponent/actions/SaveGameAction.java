package ioComponent.actions;

import saveGame.IsaveGame;
import ioComponent.interfaces.IActionSave;

public class SaveGameAction implements IActionSave {

	private int key;
	private IsaveGame comp;
	
	@Override
	public int getKey() {
		return this.key;
	}

	@Override
	public void setKey(int key) {
		this.key = key;
	}

	@Override
	public void execute() {
	}

	@Override
	public void connect(IsaveGame svg) {
		this.comp = svg;

	}

}

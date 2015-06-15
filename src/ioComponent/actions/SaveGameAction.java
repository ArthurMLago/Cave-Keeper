package ioComponent.actions;

import java.util.ArrayList;

import anima.component.ISupports;
import saveGame.IsaveGame;
import saveGame.IsaveManagement;
import gameController.GameController;
import ioComponent.interfaces.IActionSave;

public class SaveGameAction implements IActionSave {

	private int key;
	private IsaveManagement comp;
	
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
		comp.serializeEverything(GameController.getSharedInstance().getComponentsToSave());
	}

	@Override
	public void connect(IsaveManagement svg) {
		this.comp = svg;

	}

}

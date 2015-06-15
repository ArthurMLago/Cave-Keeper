package ioComponent.interfaces;

import saveGame.IsaveGame;
import saveGame.IsaveManagement;

public interface IActionSave extends IAction {
	public void connect(IsaveManagement svg);
}

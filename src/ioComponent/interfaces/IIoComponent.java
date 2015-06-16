package ioComponent.interfaces;

import org.newdawn.slick.Input;

import player.IPlayerMax;
import saveGame.IsaveManagement;
import visual.interfaces.IMapVisual;
import anima.component.ISupports;

public interface IIoComponent extends ISupports {
	
	public void connect(IMapVisual mv, IPlayerMax p, IsaveManagement svg);
	public void setActions();
	public void setCommand(Input command);
	public void executeAction();
	
}

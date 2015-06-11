package ioComponent;

import player.IPlayerMax;
import visual.interfaces.IMapVisual;
import anima.component.ISupports;

public interface IIoComponent extends ISupports {
	
	public void connect(IMapVisual mv, IPlayerMax p);
	public void setActions();
	
}

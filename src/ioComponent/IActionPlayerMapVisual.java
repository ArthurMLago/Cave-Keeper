package ioComponent;


import player.IPlayerMax;
import visual.interfaces.IMapVisual;

public interface IActionPlayerMapVisual extends IAction{
	public void connect(IPlayerMax player);
	public void connect(IMapVisual map);
}

package visual.interfaces;

import player.IPlayerAction;
import player.IPlayerMax;

public interface IActionPlayerMapVisual extends IAction{
	public void connect(IPlayerAction player);
	public void connect(IMapVisual map);
}

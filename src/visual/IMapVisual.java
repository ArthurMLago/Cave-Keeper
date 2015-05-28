package visual;

import anima.component.ISupports;
import gameController.IGameController;

public interface IMapVisual extends ISupports{
	public void connect(IGameController gameController);
	public void flareVisual();
}

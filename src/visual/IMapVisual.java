package visual;

import gameController.IGameController;
import anima.annotation.Component;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;

@ComponentInterface("<http://santanvarzea.com/visual.IMapVisual>")
public interface IMapVisual extends ISupports {
	public void connect(IGameController gameController);
	public void flareVisual();
	public void playEffect(float gain, String type);
	public void shootDirection(char direction);
	public void changePlayerFacing(int facing);		

}

package visual;

import visual.interfaces.IAction;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;

@ComponentInterface("<http://santanvarzea.com/visual.IActionHandler>")
public interface IActionHandler extends ISupports {
	public void handle(Input input);
	public void connect(IAction execute);

}

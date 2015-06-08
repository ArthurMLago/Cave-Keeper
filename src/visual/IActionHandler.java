package visual;

import visual.interfaces.IAction;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;

import com.sun.corba.se.spi.orbutil.fsm.Input;

@ComponentInterface("<http://santanvarzea.com/visual.IActionHandler>")
public interface IActionHandler extends ISupports {
	public void handle(Input input);
	public void connect(IAction execute);

}

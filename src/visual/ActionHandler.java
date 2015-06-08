package visual;

import java.util.ArrayList;

import org.newdawn.slick.Input;

import visual.interfaces.IAction;
import visual.interfaces.IHandler;
import anima.annotation.ComponentInterface;
import anima.component.IRequires;
import anima.component.base.ComponentBase;

/**
 * Classe básica que executa todas as ações que tiverem sua chave registrada para a ação
 * 
 * @author eitiyamamoto
 *
 */

@ComponentInterface("<http://santanvarzea.com/visual.ActionHandler>")
public class ActionHandler extends ComponentBase implements IHandler, IRequires<IAction>{
	private ArrayList<IAction> command;
	
	public ActionHandler() {
		command = new ArrayList<IAction>();
	}

	@Override
	public void handle(Input input) {
		for(IAction ie : command){
			if(input.isKeyPressed(ie.getKey())){
				ie.execute();
			}
		}
	}

	@Override
	public void connect(IAction execute) {
		command.add(execute);
	}

}

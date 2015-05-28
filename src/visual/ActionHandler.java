package visual;

import java.util.ArrayList;

import org.newdawn.slick.Input;

import anima.component.IRequires;
import anima.component.base.ComponentBase;

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
		System.out.println(execute.getKey());
		command.add(execute);
	}

}

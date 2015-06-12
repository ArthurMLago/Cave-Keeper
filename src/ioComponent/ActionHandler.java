package ioComponent;

import ioComponent.interfaces.IAction;
import ioComponent.interfaces.IHandler;

import java.util.ArrayList;

import org.newdawn.slick.Input;

/**
 * Classe básica que executa todas as ações que tiverem sua chave registrada para a ação
 * 
 * @author eitiyamamoto
 *
 */

public class ActionHandler implements IHandler {
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

	public void connect(IAction execute) {
		command.add(execute);
	}

}

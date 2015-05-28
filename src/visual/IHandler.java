package visual;

import org.newdawn.slick.Input;

import anima.component.ISupports;

/**
 * Interface que administra as ações executas por uma ação;
 * @author eitiyamamoto
 *
 */
public interface IHandler extends ISupports{
	public void handle(Input input);
}

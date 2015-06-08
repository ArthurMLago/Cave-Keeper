package saveGame;

import anima.annotation.ComponentInterface;
/**
 * @author Vicente
 * @author Felipe Moret
 */
import anima.component.ISupports;

@ComponentInterface("<http://santanvarzea.com/saveGame.IsaveGame>")
public interface IsaveGame<T> extends ISupports {

	public void saveState(T e);
	public T obtainState();
}

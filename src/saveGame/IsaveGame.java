package saveGame;

import anima.component.ISupports;

public interface IsaveGame<T> extends ISupports {

	public void saveState(T e);
	public T obtainState();
}

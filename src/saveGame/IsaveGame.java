package saveGame;

import anima.annotation.ComponentInterface;
import anima.component.ISupports;

/**
 * 
 * @author Vicente
 * @author Felipe Moret
 * @param <T> parametro generico para que a serializacao seja feita para qualquer objeto
 */


public interface IsaveGame<T> {

	public void saveState(T e, String Folder);
	public T obtainState(String Folder);
}

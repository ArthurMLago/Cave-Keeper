package saveGame;

import java.util.ArrayList;

import anima.annotation.ComponentInterface;
import anima.component.ISupports;

@ComponentInterface("<http://cave.com/saveGame.IsaveManagement>")
public interface IsaveManagement extends ISupports {

	public void serializeEverything(ArrayList <ISupports> components);
	public ArrayList <ISupports> deserializeEverything();
}

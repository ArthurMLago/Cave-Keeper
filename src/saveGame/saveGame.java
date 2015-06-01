package saveGame;

import items.inventory.GeneralItems;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class saveGame <T extends Serializable> {
	public static void saveState(Serializable e, String name) {
		try {
			FileOutputStream fileOut = new FileOutputStream("/temp/name.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(e);
			
			out.close();
			fileOut.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		}
	}
	
	public static Serializable obtainState(Serializable e1, String name) {
		
		try {
			FileInputStream fileIn = new FileInputStream("/tmp/name.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e1 = (Serializable) in.readObject();
			
			in.close();
	        fileIn.close();
	        
	        return e1;
		}
		catch(IOException i) {
			i.printStackTrace();
			return null;
		}
		catch(ClassNotFoundException c) {
	       c.printStackTrace();
	       return null;
		}
	}
}

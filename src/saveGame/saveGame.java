package saveGame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class saveGame <T>{
	
	public static Employee inicia() {	
		Employee e = new Employee();
		e.name = "Reyan Ali";
	    e.address = "Phokka Kuan, Ambehta Peer";
	    e.SSN = 11122333;
	    e.number = 101;
	    
	    return e;
	}
	
	public void saveState(T e) {
		try {
			FileOutputStream fileOut = new FileOutputStream("src/saveGame/tmp/alou.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(e);
			
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved");
		}
		catch(IOException i) {
			i.printStackTrace();
		}
	}
	
	public T obtainState() {
		
		try {
			T e1 = null;
			FileInputStream fileIn = new FileInputStream("src/saveGame/tmp/alou.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e1 = (T) in.readObject();
			
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

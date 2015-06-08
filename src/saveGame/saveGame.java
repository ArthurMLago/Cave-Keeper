package saveGame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import anima.annotation.Component;

/**
 * 
 * @author Vicente
 * @@author Felipe Moret
 *
 * @param <T> uso de Generics para poder serializar quarquer objeto
 */

@Component(id="<http://santanvarzea.com/saveGame.saveGame>", provides={"<http://santanvarzea.com/saveGame.IsaveGame>"})
public class saveGame <T>{
	
	
//	   saveGame<Employee> salvador = new saveGame<Employee>();

/**
 * 	
 * @param e recebe parametro de um tipo generico T e faz sua serializacao, levando o arquivo a
 * src/saveGame/tmp/alou.ser
 */
	public void saveState(T e) {
		try {
			FileOutputStream fileOut = new FileOutputStream("src/saveGame/tmp/teste.ser");
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
	
/**
 * classe que le o arquivo src/saveGame/tmp/alou.ser e deserializa o objeto	
 * @return o objeto serializado
 */
	
	
	@SuppressWarnings("unchecked")
	public T obtainState() {
		
		try {
			T e1 = null;
			FileInputStream fileIn = new FileInputStream("src/saveGame/tmp/teste.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e1 = (T) in.readObject();
			
			in.close();
	        fileIn.close();
	        System.out.println("Deserialization complete");
	        
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

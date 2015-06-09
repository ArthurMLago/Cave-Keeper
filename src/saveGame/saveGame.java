package saveGame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import anima.annotation.Component;
import anima.component.base.ComponentBase;

/**
 * 
 * @author Vicente
 * @author Felipe Moret
 * @param <T> uso de Generics para poder serializar quarquer objeto
 * 
 * classe responsavel pela serializacao e deserializacao dos objetos do jogo
 */

@Component(id="<http://santanvarzea.com/saveGame.saveGame>", provides={"<http://santanvarzea.com/saveGame.IsaveGame>"})
public class saveGame<T> extends ComponentBase implements IsaveGame<T>{
	
	
/**
 * metodo que faz a serializacao do objeto e
 * @param e: um tipo generico T
 * @param Folder: guarda a serializacao do objeto em uma pasta tmp com o arquivo .ser
 */
	
	public void saveState(T e, String Folder) {
		try {
			FileOutputStream fileOut = new FileOutputStream("src/saveGame/tmp/"	+ Folder + ".ser");
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
 * classe que le o arquivo de nome Folder na pasta tmp e deserializa os objetos
 * @param Folder: nome da pasta em que se encontra o arquivo .ser
 * @return o objeto serializado
 */
	
	@SuppressWarnings("unchecked")
	public T obtainState(String Folder) {
		
		try {
			T e1 = null;
			FileInputStream fileIn = new FileInputStream("src/saveGame/tmp/" + Folder + ".ser");
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

//saveGame<Employee> salvador = new saveGame<Employee>();

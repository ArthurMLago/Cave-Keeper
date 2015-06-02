package saveGame;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializeDemo
{
   public static void main(String [] args)
   {
	   
	   Employee e = null;
	   saveGame<Employee> salvador = new saveGame<Employee>();
	   
	   e = salvador.obtainState();
	   
	   System.out.println("Name: " + e.name);
	   System.out.println("Address: " + e.address);
       System.out.println("SSN: " + e.SSN);
      System.out.println("Number: " + e.number);
	   
//	   
//	   Employee e = new Employee();
//	    e.name = "Reyan Ali";
//	    e.address = "Phokka Kuan, Ambehta Peer";
//	    e.SSN = 11122333;
//	    e.number = 101;
//	    
//	   saveGame<Employee> salvador = new saveGame<Employee>();
//	   
//	   salvador.saveState(e);
//	   
//	   System.out.println("Name: " + e.name);
//	   System.out.println("Address: " + e.address);
//       System.out.println("SSN: " + e.SSN);
//       System.out.println("Number: " + e.number);
//	   
	   
	   
	   
	   
	   
//	   
//      Employee e = new Employee();
//      e.name = "Reyan Ali";
//      e.address = "Phokka Kuan, Ambehta Peer";
//      e.SSN = 11122333;
//      e.number = 101;
//      try
//      {
//         FileOutputStream fileOut =
//         new FileOutputStream("src/saveGame/tmp/employee.ser");
//         ObjectOutputStream out = new ObjectOutputStream(fileOut);
//         out.writeObject(e);
//         out.close();
//         fileOut.close();
//         System.out.printf("Serialized data is saved in /tmp/employee.ser");
//      }catch(IOException i)
//      {
//          i.printStackTrace();
//      }
   }
}

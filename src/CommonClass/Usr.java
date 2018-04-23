package CommonClass;
import java.io.Serializable;
public class Usr implements Serializable{
	 private CommandIndex CommandNum;
	 private String usrname;
	 private String password;
     public Usr(CommandIndex num,String name,String pass) {
    	  CommandNum=num;
    	  usrname=name;
    	  password=pass;
     }
     public String GetName() {
    	 return usrname;
     }
     public String GetPass() {
    	 return password;
     }
	public CommandIndex GetCommand() {
		// TODO Auto-generated method stub
		return CommandNum;
	}
}

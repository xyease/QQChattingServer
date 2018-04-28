package CommonClass;
import java.io.Serializable;
public class Datagram implements Serializable{
	 private CommandIndex CommandNum;
	 private Flag flag;//response
	 private String usrname;
	 private String password;
	 private String usr2;//∫√”—…Í«Î
     public Datagram(Flag f) {
          flag=f;
    }     
     public Datagram(CommandIndex num, String name, String pass) {
   	      CommandNum=num;
   	      usrname=name;
   	      password=pass;
	}
     public Datagram(CommandIndex num,String name) {
    	 CommandNum=num;
    	 usrname=name;
     }
	public String GetName() {
    	 return usrname;
     }
	public void SetName(String name) {
		usrname=name;
	}
     public String GetPass() {
    	 return password;
     }
     public CommandIndex GetCommand() {
    	 return CommandNum;
     }
     public Flag GetFlag() {return flag;}
     public void SetFlag(Flag f) {flag=f;}
     public String GetUsr2() {
    	 return usr2;
     }
     public void SetUsr2(String name) {
    	 usr2=name;
     }
}


package CommonClass;
import java.io.Serializable;
public class Usr implements Serializable{
	 private String usrname;
	 private String password;
    public Usr(String name,String pass) {
   	  usrname=name;
   	  password=pass;
    }
    public String GetName() {
   	 return usrname;
    }
    public String GetPass() {
   	 return password;
    }
}

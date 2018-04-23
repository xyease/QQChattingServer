package CommonClass;

import java.io.Serializable;

public class Response implements Serializable{
     private Flag flag;
     public Response(Flag f) {flag=f;}
     public Flag GetFlag() {return flag;}
     public void SetFlag(Flag f) {flag=f;}
}

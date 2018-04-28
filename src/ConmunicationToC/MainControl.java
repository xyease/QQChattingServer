package ConmunicationToC;
import CommonClass.*;
import MySql.LoginSql;
//import CommonClass.CommandIndex;
import MySql.RegisterSql;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
public class MainControl {
	public static void main(String[] args){
		  ServerSocket serverSocket=null;
        try {
           serverSocket = new ServerSocket(9999);
           int count = 0;// 记录客户端的数量
           System.out.println("服务器启动，等待客户端的连接。。。");
           Socket socket = null;
           while(true){
           socket=serverSocket.accept();
           ++count;
           Thread serverHandleThread=new Thread(new ServerHandleThread(socket));
           serverHandleThread.setPriority(4);
           serverHandleThread.start();
           System.out.println("上线的客户端有" + count + "个！");
           InetAddress inetAddress = socket.getInetAddress();
           System.out.println("当前客户端的IP地址是："+inetAddress.getHostAddress());
           }
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         } 
  }
}

class ServerHandleThread implements Runnable{
    Socket socket=null;
     
    public ServerHandleThread(Socket socket) {
        super();
        this.socket = socket;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        OutputStream os = null;
        ObjectOutputStream oos=null;
        //PrintWriter pw = null;
        InputStream is=null;
        ObjectInputStream ois=null;
        try {
        is = socket.getInputStream();
        ois=new ObjectInputStream(is);
        os=socket.getOutputStream();
        oos=new ObjectOutputStream(os);
        }catch(IOException e){
        	e.printStackTrace();
        }        
        try {    
        	while(true) {
            //readObject()方法必须保证服务端和客户端的User包名一致，要不然会出现找不到类的错误
        		
            Datagram datagram=(Datagram) ois.readObject();
            //socket.shutdownInput();
            System.out.println("客户端发送的对象："+datagram);
            CommandIndex command=datagram.GetCommand();
            switch(command) {
            case Register:{
            	int result=RegisterSql.RegisterVerify(datagram);	
            	Datagram response=new Datagram(Flag.Success);
                //socket.shutdownInput();// 禁用套接字的输入流
                if(result==1) response.SetFlag(Flag.Success);
                else if(result==-1) response.SetFlag(Flag.UserNameUsed);
                else response.SetFlag(Flag.Failed);
                oos.writeObject(response); 
            }
            case Login:{
            	int result=LoginSql.Login_Sql(datagram);       
            	Datagram response=new Datagram(Flag.Success);
                //socket.shutdownInput();// 禁用套接字的输入流
                if(result==1) response.SetFlag(Flag.Success);
                else if(result==-1) response.SetFlag(Flag.ErrorPassword);
                else if(result==0)  response.SetFlag(Flag.IllegalUsrName);
                else response.SetFlag(Flag.Failed);
                oos.writeObject(response);
                //socket.shutdownInput();
                //pw.flush();
                //socket.shutdownOutput();
            }
            case FriendRequest:{
            	System.out.println("FriendRequest");
            }
           }
        }         
        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block        	
            e.printStackTrace();
        }finally{
            try{
                if(oos!=null) oos.close();
                if(os!=null)  os.close();
                if(ois!=null)  ois.close();
                if(is!=null)  is.close();
                if(socket!=null) socket.close();

            }catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }

        }
    }
    
} 

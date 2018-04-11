package ConmunicationToC;
import CommonClass.Usr;
import MySql.RegisterSql;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
public class RegisterVerify {
	public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            int count = 0;// ��¼�ͻ��˵�����
            System.out.println("�������������ȴ��ͻ��˵����ӡ�����");
            Socket socket = null;
            while(true){
                socket=serverSocket.accept();
                ++count;
                Thread serverHandleThread=new Thread(new ServerHandleThread(socket));
                serverHandleThread.setPriority(4);
                serverHandleThread.start();
                System.out.println("���ߵĿͻ�����" + count + "����");
                InetAddress inetAddress = socket.getInetAddress();
                System.out.println("��ǰ�ͻ��˵�IP��ַ�ǣ�"+inetAddress.getHostAddress());
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
        PrintWriter pw = null;
        try {
            InputStream is = socket.getInputStream();
            ObjectInputStream ois=new ObjectInputStream(is);
            //readObject()�������뱣֤����˺Ϳͻ��˵�User����һ�£�Ҫ��Ȼ������Ҳ�����Ĵ���
            Usr user=(Usr) ois.readObject();
            System.out.println("�ͻ��˷��͵Ķ���"+user);
            
            int result=RegisterSql.RegisterVerify(user);
            
            socket.shutdownInput();// �����׽��ֵ�������
            os=socket.getOutputStream();
            pw=new PrintWriter(os);
            if(result==1) pw.println("Success");
            else if(result==-1) pw.println("User name has been used");
            else pw.println("Failed");
            pw.flush();
            socket.shutdownOutput();
            
        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try{
                if(pw!=null){
                    pw.close();
                }
                if(os!=null){
                    os.close();
                }
                if(socket!=null){
                    socket.close();
                }
            }catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }

        }
    }
    
} 

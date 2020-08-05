package Client;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private int port;

    public Client(int port){
        this.port = port;
    }

    public void sendWolCommand(String mac, String ip){
        Socket server = null;

        try{
            server = new Socket(ip, port);
            Scanner in = new Scanner(server.getInputStream());
            PrintWriter out = new PrintWriter(server.getOutputStream(), true);

            out.println(mac);
        }
        catch(UnknownHostException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally{
            if(server != null){
                try{
                    server.close();
                }
                catch(IOException ignored){}
            }
        }
    }
}

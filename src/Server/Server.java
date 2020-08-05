package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private final int port;
    public Server(int port){
        this.port = port;
    }
    public void startServer() throws IOException {
        ServerSocket server = new ServerSocket(port);
        while (true){
            Socket client = null;

            try {
                client = server.accept();
                handleConnection(client);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            finally{
                if (client != null){
                    try{
                        client.close();
                    }
                    catch(IOException ignored){}
                }
            }
        }
    }

    private void handleConnection(Socket client) throws IOException{
        Scanner in = new Scanner(client.getInputStream());
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);

        WolGenerator.sendMagicPacket(in.nextLine());
    }
}

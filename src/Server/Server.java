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
        System.out.println("Starting WolServer");
        ServerSocket server = new ServerSocket(port);
        while (true){

            try (Socket client = server.accept()) {
                handleConnection(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleConnection(Socket client) throws IOException{
        Scanner in = new Scanner(client.getInputStream());
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        String mac = in.nextLine();
        WolGenerator.sendMagicPacket(mac);
        System.out.println("Received WOL request from " + client.getInetAddress().getHostAddress() + " for MAC " + mac);
        out.println("Sent WoL command");
    }
}

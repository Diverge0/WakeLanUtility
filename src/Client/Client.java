package Client;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
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

        try (Socket server = new Socket(ip, port)) {
            Scanner in = new Scanner(server.getInputStream());
            PrintWriter out = new PrintWriter(server.getOutputStream(), true);

            out.println(mac);
            final JFrame parent = new JFrame();
            parent.add(new JLabel(in.nextLine(), SwingConstants.CENTER));
            parent.setSize(200, 180);
            parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            parent.setVisible(true);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

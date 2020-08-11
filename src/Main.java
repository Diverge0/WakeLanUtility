import Client.Client;
import Server.Server;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        if (args.length == 0) {
            Client client = new Client(2056);
            client.sendWolCommand("50:E5:49:B4:11:14", "192.168.178.66");
        }
        else if (args[0].equals("server")){
            Server server = new Server(2056);
            server.startServer();
        }
        else if (args[0].equals("client")){
            Client client = new Client(2056);
            client.sendWolCommand(args[1], args[2]);
        }
        else {
            System.out.println("Garbage ");
            TimeUnit.SECONDS.sleep(5);
        }
    }
}

import Client.Client;
import Server.Server;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        if (args.length == 0) {
            System.out.println("Proceeding to do absolutely nothing");
            TimeUnit.SECONDS.sleep(5);

        }
        else if (args[0].equals("server")){
            Server server = new Server(420);
            server.startServer();
        }
        else if (args[0].equals("client")){
            Client client = new Client(420);
            client.sendWolCommand(args[1], args[2]);
        }
        else {
            System.out.println("Garbage ");
            TimeUnit.SECONDS.sleep(5);
        }
    }
}

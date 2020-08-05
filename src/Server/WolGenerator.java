package Server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class WolGenerator {
    public static void sendMagicPacket(String mac){
        byte[] magicPacket = createMagicPacket(mac);
        try {
            DatagramPacket readyMagicPacket = new DatagramPacket(magicPacket, magicPacket.length, InetAddress.getByName("192.168.178.255"), 9);
            DatagramSocket socket = new DatagramSocket();
            socket.send(readyMagicPacket);
            socket.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private static byte[] createMagicPacket(String mac){
        byte[] magicPacket = new byte[102];

        try{
            byte[] macPart = getMacBytes(mac);
            for (int i = 0; i < 6; i++) {
                magicPacket[i] = (byte) 0xff;
            }
            for (int j = 6; j < magicPacket.length; j += macPart.length) {
                System.arraycopy(macPart, 0, magicPacket, j, macPart.length);
            }
            return magicPacket;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] getMacBytes(String macString) throws IllegalArgumentException{
        byte[] macBytes = new byte[6];
        String[] splitByHex = macString.split("(\\:|\\-)");
        if (splitByHex.length != 6) {
            throw new IllegalArgumentException("Invalid Mac address.");
        }
        try {
            for (int i = 0; i < 6; i++) {
                macBytes[i] = (byte) Integer.parseInt(splitByHex[i], 16);
            }
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return macBytes;
    }
}

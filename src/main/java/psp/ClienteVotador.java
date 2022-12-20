package psp;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ClienteVotador {

    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket=new DatagramSocket();

        Scanner scanner=new Scanner(System.in);


        System.out.println("¿A quién quieres votar?");
        String nombreCandidato=scanner.next();

        SocketAddress socketAddress=new InetSocketAddress("localhost", 7000);
        byte[] buffer= nombreCandidato.getBytes();
        DatagramPacket datagramPacket= new DatagramPacket(buffer, buffer.length, socketAddress);

        datagramSocket.send(datagramPacket);

    }
}

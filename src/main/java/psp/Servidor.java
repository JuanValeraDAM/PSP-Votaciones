package psp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Servidor {
    /*
 Se desea implementar un sistema de votaciones usando sockets. Hay un servidor que recibe los votos, siendo cada uno un mensaje UDP con el nombre del candidato votado en el campo de datos. Estos mensajes no reciben respuesta.

El servidor mantendrá el recuento permanentemente, actualizándolo conforme reciba votos.

En cualquier momento, se podrá conectar un cliente mediante TCP para preguntar cuántos votos lleva un determinado candidato. El protocolo de esta comunicación será:

el cliente envía el nombre del candidato;
el servidor responde con la cantidad de votos en el formato: "VOTOS x" (siendo x la cantidad), o bien responde con el texto "NINGUNO" si no se han recibido votos para ese candidato;
ambos cierran la conexión.
Desarrolla esta solución, incluyendo:

el servidor;
un cliente votador;
un cliente que consulta votos.
     */

    /*
    BORRADOR:

     */
    public static void main(String[] args) throws IOException {
        Map<String, Integer> votaciones = new HashMap<>();
        byte[] bufferVotaciones = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bufferVotaciones, bufferVotaciones.length);

        ServerSocket serverSocket = new ServerSocket(5000);
        DatagramSocket datagramSocket = new DatagramSocket(7000);

        HiloRecibirVotacion hiloRecibirVotacion = new HiloRecibirVotacion(datagramSocket, datagramPacket, votaciones);
        hiloRecibirVotacion.start();

        while (true) {
            Socket socketConectado = serverSocket.accept();
            HiloAtenderRecuento hiloAtenderRecuento = new HiloAtenderRecuento(socketConectado, votaciones);
            hiloAtenderRecuento.start();
        }


    }
}
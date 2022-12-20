package psp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HiloRecibirVotacion extends Thread {
    private final Map<String, Integer> votaciones;
    private final DatagramPacket datagramPacket;
    private final DatagramSocket datagramSocket;

    public HiloRecibirVotacion(DatagramSocket datagramSocket, DatagramPacket datagramPacket, Map<String, Integer> votaciones) {
        this.datagramPacket = datagramPacket;
        this.votaciones = votaciones;
        this.datagramSocket=datagramSocket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                datagramSocket.receive(datagramPacket);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            String nombreDelCandidato = new String(datagramPacket.getData(),
                    0, datagramPacket.getLength(), StandardCharsets.UTF_8);
            if (votaciones.containsKey(nombreDelCandidato)) {
                votaciones.put(nombreDelCandidato, votaciones.get(nombreDelCandidato) + 1);
            } else {
                votaciones.put(nombreDelCandidato, 1);
            }
        }
    }
}

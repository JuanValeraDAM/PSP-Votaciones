package psp;


import java.io.*;
import java.net.Socket;
import java.util.Map;

public class HiloAtenderRecuento extends Thread {

    private final Socket socket;
    private final Map<String, Integer> votaciones;

    public HiloAtenderRecuento(Socket serverSocket, Map<String, Integer> votaciones) {
        this.socket = serverSocket;
        this.votaciones = votaciones;
    }

    @Override
    public void run() {
        try {

            OutputStream outputStream=socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String nombreCandidato = bufferedReader.readLine();

            if(votaciones.containsKey(nombreCandidato)){
                printWriter.println("Votos: "+votaciones.get(nombreCandidato));
                printWriter.flush();
            }else{
                printWriter.println("Ninguno");
                printWriter.flush();
            }
            socket.close();
            System.out.println("Se ha cerrado el socket del hilo Servidor");



        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

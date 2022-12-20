package psp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteParaRecuento {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);

        OutputStream outputStream=socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println("Introduce el nombre del candidato del que deseas saber los votos");
        String nombreCandidato=new Scanner(System.in).next();

        printWriter.println(nombreCandidato);
        printWriter.flush();

        String recuentoVotos=bufferedReader.readLine();
        System.out.println(recuentoVotos);

        socket.close();
    }
}

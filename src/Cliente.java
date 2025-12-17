import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {


    public static void main(String[] args) throws IOException {

        //me conecto con el servidor
        Socket socket = new Socket("localhost", 5000);

        //leo el texto que recibo del servidor
        BufferedReader entrada = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        //envio al serrvidor texto
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

        //area de lectura escribe usuario
        BufferedReader escribe_usuario = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println(entrada.readLine());

        while (true) {
            salida.println(escribe_usuario.readLine());
            System.out.println(entrada.readLine());
            // PRUEBA SUBIDA GIT
        }
    }
}


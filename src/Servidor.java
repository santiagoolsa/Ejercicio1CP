import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


public class Servidor {
    public static void main(String[] args) throws IOException {

        //defino el servidor TCP y el puerto 5000
        ServerSocket server = new ServerSocket(5000);
        System.out.println("Servidor TCP listo");

        //mientras  haya un servidor que acepte entrara y creará un cliente y lo inicia
        //por cada cliente crea un hilo
        while (true) {

            //se acepta la conexion
            Socket cliente = server.accept();

            //se crea un hilo
            new Thread(() -> handleClient(cliente)).start();
        }
    }

    private static void handleClient(Socket cliente) {
        try {

            //leo los datos recibidos por el cliente
            BufferedReader entrada_datos_cliente = new BufferedReader(
                    new InputStreamReader(cliente.getInputStream()));

            //envvio datos al cliente
            PrintWriter salida_datos_cliente = new PrintWriter(cliente.getOutputStream(), true);

            Random r = new Random();
            int numero_introducido = r.nextInt(100) + 1;

            salida_datos_cliente.println("Adivina el número (1-100)");

            String linea;
            while ((linea = entrada_datos_cliente.readLine()) != null) {
                int numero_secreto;

                try {
                    numero_secreto = Integer.parseInt(linea);
                } catch (NumberFormatException e) {
                    salida_datos_cliente.println("Número incorrecto");
                    continue;
                }

                if (numero_secreto < numero_introducido) salida_datos_cliente.println("El número es mayor");
                else if (numero_secreto > numero_introducido) salida_datos_cliente.println("El número es menor");
                else {
                    salida_datos_cliente.println("Número correcto. Nuevo juego, adivina otro número (1-100)");
                    numero_introducido = r.nextInt(100) + 1;
                }
            }

            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//subida con conmits con comentarios
import java.net.*;
import java.io.*;
import java.util.Random;


public class Servidor {
    public static void main(String[] args) throws IOException {

        //defino el servidor
        ServerSocket server = new ServerSocket(5000);
        System.out.println("Servidor TCP listo");

        while (true) {
            Socket cliente = server.accept();
            new Thread(() -> handleClient(cliente)).start();
        }
    }

    private static void handleClient(Socket cliente) {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(cliente.getInputStream()));
            PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);

            Random r = new Random();
            int secret = r.nextInt(100) + 1;

            out.println("Adivina el numero (1-100)");

            String line;
            while ((line = in.readLine()) != null) {
                int guess;

                try {
                    guess = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    out.println("Numero invalido");
                    continue;
                }

                if (guess < secret) out.println("El numero es mayor");
                else if (guess > secret) out.println("El numero es menor");
                else {
                    out.println("Numero correcto. Nuevo juego");
                    secret = r.nextInt(100) + 1;
                }
            }

            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
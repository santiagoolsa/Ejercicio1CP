import java.net.*;
import java.io.*;

public class Cliente {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader kb = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println(in.readLine());

        while (true) {
            out.println(kb.readLine());
            System.out.println(in.readLine());
        }
    }
}//prueba inserciono git 2
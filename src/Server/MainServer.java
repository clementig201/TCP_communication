package Server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) {

        System.out.println("Server: inizio esecuzione");

        try {
            // 1 istanzio il socket del server
            ServerSocket server = new ServerSocket(3000);
            System.out.println("Server: in attesa di client");
            // 2 invoco il metodo accept
            Socket clientSocket = server.accept();
            System.out.println("Server: il client si è connesso");

            // 6 ilserver deve leggere ciò che gli è stato inviato
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String messaggio = br.readLine();
            System.out.println("il client"+ clientSocket + "ha scritto" + messaggio);
        } catch (IOException e) {
            System.err.println("porta non disponibile," +
                    "errore nella creazione della connection socket");
        }
    }
}

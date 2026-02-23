package Client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MainClient {
    public static void main(String[] args) {

         System.out.println("Client: avvio del client");
        try {
            // 3 istanzio il socket dell client
            Socket socket = new Socket("localhost",3000);
            // 4 il client srive un messaggio
            System.out.println("Client: il client si è connesso al server");
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(outputStream);
            //  5 stiamo scrivendo nella ram del client nell output stream
            pw.print("Ciao server\n");
            //  5 abbiamo spedito il messaggio al server liberando un area di memoria
            pw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }
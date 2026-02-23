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

            // 3 ottengo gli stream di input e output
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter pw = new PrintWriter(outputStream);
            // 4 leggo i messaggi dalla tastiera
            BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));

            String messaggio = "";
            String risposta = "";

            // 5 ciclo finché nessuno invia una parola chiave di chiusura
            while (!messaggio.equalsIgnoreCase("arrivederci") && !messaggio.equalsIgnoreCase("chiudi")
                    && !risposta.equalsIgnoreCase("arrivederci") && !risposta.equalsIgnoreCase("chiudi")) {

                // 6 il server legge ciò che gli è stato inviato dal client
                messaggio = br.readLine();
                if (messaggio == null) break;
                System.out.println("Client ha scritto: " + messaggio);

                if (messaggio.equalsIgnoreCase("arrivederci") || messaggio.equalsIgnoreCase("chiudi")) break;

                // 7 il server scrive la risposta nella ram nell output stream
                System.out.print("Server, scrivi un messaggio: ");
                risposta = tastiera.readLine();
                pw.println(risposta);
                // 8 abbiamo spedito il messaggio al client liberando un area di memoria
                pw.flush();
                System.out.println("Messaggio inviato al Client");
            }

            System.out.println("Server: chiusura connessione.");
            inputStream.close();
            outputStream.close();
            clientSocket.close();
            server.close();

        } catch (IOException e) {
            System.err.println("porta non disponibile," +
                    "errore nella creazione della connection socket");
        }
    }
}
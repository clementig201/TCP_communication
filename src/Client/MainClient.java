package Client;

import java.io.*;
import java.net.Socket;

public class MainClient {
    public static void main(String[] args) {

        System.out.println("Client: avvio del client");

        try {
            // 1 istanzio il socket del client
            Socket socket = new Socket("localhost", 3000);
            System.out.println("Client: il client si è connesso al server");

            // 2 ottengo gli stream di output e input
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(outputStream);
            InputStream inputStream = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            // 3 leggo i messaggi dalla tastiera
            BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));

            String messaggio = "";
            String risposta = "";

            // 4 ciclo finché nessuno invia una parola chiave di chiusura
            while (!messaggio.equalsIgnoreCase("arrivederci") && !messaggio.equalsIgnoreCase("chiudi")
                    && !risposta.equalsIgnoreCase("arrivederci") && !risposta.equalsIgnoreCase("chiudi")) {

                // 5 stiamo scrivendo nella ram del client nell output stream
                System.out.print("Client, scrivi un messaggio: ");
                messaggio = tastiera.readLine();
                pw.println(messaggio);
                // 6 abbiamo spedito il messaggio al server liberando un area di memoria
                pw.flush();
                System.out.println("Messaggio inviato al Server");

                if (messaggio.equalsIgnoreCase("arrivederci") || messaggio.equalsIgnoreCase("chiudi")) break;

                // 7 il client legge ciò che gli è stato inviato dal server
                risposta = br.readLine();
                if (risposta == null) break;
                System.out.println("il server ha scritto" + " " + risposta);
            }

            System.out.println("Client: chiusura connessione.");
            outputStream.close();
            socket.close();

        } catch (IOException e) {
            System.err.println("Errore nella connessione");
        }
    }
}
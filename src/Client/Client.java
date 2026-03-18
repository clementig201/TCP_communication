package Client;

import java.io.*;
import java.net.Socket;

public class Client {
    private String nome;
    private String colore;
    private Socket socket;

    public Client(String nome) {
        this.nome = nome;
    }

    // Crea la connessione vera al server
    private void socket(String indirizzoServer, int port) throws IOException {
        socket = new Socket(indirizzoServer, port);
    }

    public int Connetti(String nomeServer, int portaServer) {
        try {
            socket(nomeServer, portaServer);
            System.out.println("Client: il client si è connesso al server");
            return 1;
        } catch (IOException e) {
            System.err.println("Errore nella connessione: " + e.getMessage());
            return -1;
        }
    }
    public void scrivi(){

    }
}
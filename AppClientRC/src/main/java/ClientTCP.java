import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientTCP {
    public static void main(String[] args) {
        String serverAddress = "100.96.244.31";
        int port = 5005;

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectat la server pe " + serverAddress + ":" + port);
            System.out.println("Scrie 'exit' pentru a închide conversația.");

            String clientMessage;
            String serverResponse;

            while (true) {
                System.out.print("Client (Tu): ");
                clientMessage = scanner.nextLine();
                out.println(clientMessage);

                if ("exit".equalsIgnoreCase(clientMessage)) {
                    System.out.println("Ai închis conexiunea.");
                    break;
                }
                
                System.out.println("Aștept răspuns de la server...");
                serverResponse = in.readLine();

                if (serverResponse == null || "exit".equalsIgnoreCase(serverResponse)) {
                    System.out.println("Serverul a închis conexiunea.");
                    break;
                }

                System.out.println("Server: " + serverResponse);
            }

        } catch (IOException e) {
            System.err.println("Eroare la conexiune: " + e.getMessage());
        }
    }
}
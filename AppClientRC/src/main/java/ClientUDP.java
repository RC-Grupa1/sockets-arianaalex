import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientUDP {
    public static void main(String[] args) {
        String serverHost = "100.96.244.31";
        int serverPort = 5005;
        int bufferSize = 1024;

        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            InetAddress serverAddress = InetAddress.getByName(serverHost);
            System.out.println("Client UDP pornit. Trimite un mesaj serverului...");

            while (true) {
                System.out.print("Client (Tu): ");
                String message = scanner.nextLine();
                byte[] sendData = message.getBytes(StandardCharsets.UTF_8);

                DatagramPacket sendPacket = new DatagramPacket(
                        sendData, sendData.length, serverAddress, serverPort
                );
                socket.send(sendPacket);

                if ("exit".equalsIgnoreCase(message)) {
                    System.out.println("Închidere chat...");
                    break;
                }

                byte[] receiveData = new byte[bufferSize];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                System.out.println("Aștept răspuns de la server (UDP)...");
                socket.receive(receivePacket);

                String response = new String(
                        receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8
                );

                if ("exit".equalsIgnoreCase(response.trim())) {
                    System.out.println("Serverul a cerut închiderea.");
                    break;
                }

                System.out.println("Server: " + response);
            }
        } catch (IOException e) {
            System.err.println("Eroare UDP: " + e.getMessage());
        }
    }
}
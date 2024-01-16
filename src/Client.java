import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6000);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter a text: ");
            String text = scanner.nextLine();
            System.out.print("Enter an index (0-9): ");
            int index = scanner.nextInt();
            out.println(text + ":" + index);

            String response = in.readLine();
            String[] parts = response.split(" ");
            int receivedIndex = Integer.parseInt(parts[0]);
            String encryptedText = parts[1];
            int key = Integer.parseInt(parts[2]);

            // Decrypt the text
            StringBuilder decryptedText = new StringBuilder();
            for (char c : encryptedText.toCharArray()) {
                decryptedText.append((char) (c - key));
            }
            System.out.println("Received index: " + receivedIndex);
            System.out.println("Decrypted text: " + decryptedText.toString());

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

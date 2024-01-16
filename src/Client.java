import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            while (true){
                Socket socket = new Socket("localhost", 6000);

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                Scanner scanner = new Scanner(System.in);

                System.out.print("Enter a text: ");
                String text = scanner.nextLine();

                out.println(text);

                String response = in.readLine();
                String[] parts = response.split(",");
                int key = Integer.parseInt(parts[0]);
                String encryptedText = parts[1];

                System.out.println("key: " + key);
                System.out.println("Decrypted text: " + EncryptionManager.getInstance().decrypt(key, encryptedText));
                System.out.println("---------------------------------------------------------------------");

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

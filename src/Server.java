import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6000);
            System.out.println("Server is running and waiting for connections...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket);

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                String data = in.readLine();
                System.out.println("Client data: " + data);

                String text = data.toUpperCase();
                int keyNumber = EncryptionManager.getInstance().random();

                String encryptedData = EncryptionManager.getInstance().encrypt(keyNumber, text);

                out.println(keyNumber + "," + encryptedData);
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

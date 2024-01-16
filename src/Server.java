import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server is running and waiting for connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                InputStream input = clientSocket.getInputStream();
                OutputStream output = clientSocket.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

                StringBuilder data = new StringBuilder();
                do {
                    int c = input.read();
                    data.append((char) c);
                } while (input.available() > 0);

                System.out.println("Client data: " + data);

                

                writer.flush();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

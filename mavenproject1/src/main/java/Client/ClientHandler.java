package Client;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Map<String, String> userCredentials;

    public ClientHandler(Socket socket, Map<String, String> userCredentials) {
        this.clientSocket = socket;
        this.userCredentials = userCredentials;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String username = reader.readLine();
            String password = reader.readLine();

            // Kiểm tra thông tin đăng nhập
            if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
                writer.println("Đăng nhập thành công!");
            } else {
                writer.println("Tên đăng nhập hoặc mật khẩu không chính xác.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
package Client;

import GUI.Login;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class Client implements Login.LoginListener {
    public static void main(String[] args) {
        // Khởi tạo và hiển thị giao diện đăng nhập
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Login(new Client()).setVisible(true);
        });
    }

    @Override
    public void onLogin(String username, String password, String ip, String port) {
        connectToServer(ip, Integer.parseInt(port), username, password);
    }

    private void connectToServer(String ip, int port, String username, String password) {
        try (Socket socket = new Socket(ip, port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Gửi thông tin đăng nhập tới máy chủ
            writer.println(username);
            writer.println(password);

            // Nhận phản hồi từ máy chủ
            String response = reader.readLine();
            JOptionPane.showMessageDialog(null, response);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi kết nối: " + ex.getMessage());
        }
    }
}
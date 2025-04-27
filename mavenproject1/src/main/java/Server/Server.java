package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import Client.ClientHandler;
import GUI.ServerGUI;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int N_THREADS = 10;
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private Map<String, String> userCredentials; // Danh sách tên đăng nhập và mật khẩu

    public Server() {
        executorService = Executors.newFixedThreadPool(N_THREADS);
        userCredentials = new HashMap<>();
        initializeCredentials(); // Khởi tạo thông tin đăng nhập
    }

    private void initializeCredentials() {
        // Thêm thông tin đăng nhập (username: password)
        userCredentials.put("admin", "password123");
        userCredentials.put("user", "pass456");
    }

    public String openPort(String id, String portStr) {
        int port;
        try {
            port = Integer.parseInt(portStr);
            if (port < 1 || port > 65535) {
                return "Cổng không hợp lệ. Vui lòng nhập cổng từ 1 đến 65535.";
            }
        } catch (NumberFormatException e) {
            return "Cổng không hợp lệ. Vui lòng nhập cổng từ 1 đến 65535.";
        }

        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

            serverSocket = new ServerSocket(port);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                shutdownExecutor(executorService);
            }));

            listenForClients();
            return "Server với ID: " + id + " đã mở trên cổng " + port + ".";
        } catch (IOException ex) {
            return "Không thể khởi động server trên cổng " + port + ": " + ex.getMessage();
        }
    }

    private void listenForClients() {
        new Thread(() -> {
            while (!executorService.isShutdown()) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    ClientHandler clientHandler = new ClientHandler(clientSocket, userCredentials);
                    executorService.execute(clientHandler);
                } catch (IOException e) {
                    if (executorService.isShutdown()) {
                        break;
                    }
                }
            }
        }).start();
    }

    private void shutdownExecutor(ExecutorService executor) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                }
            }
        } catch (InterruptedException ie) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new ServerGUI().setVisible(true);
        });
    }
}
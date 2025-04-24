package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import Client.ClientHandler;

public class Server {
    private static final int N_THREADS = 10;
    private ServerSocket serverSocket;
    private ExecutorService executorService;

    public Server() {
        executorService = Executors.newFixedThreadPool(N_THREADS);
        //System.out.println("ThreadPool Server đang khởi tạo với " + N_THREADS + " thread...");
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
          //  System.out.println("Server với ID: " + id + " đang lắng nghe trên cổng " + port + "...");

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
          //      System.out.println("Đang shutdown ExecutorService...");
                shutdownExecutor(executorService);
           //     System.out.println("ExecutorService đã shutdown.");
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
                    //System.out.println("Đang chờ client kết nối...");
                    Socket clientSocket = serverSocket.accept();
                    //System.out.println("Client mới đã kết nối: " + clientSocket.getInetAddress().getHostAddress());

                    ClientHandler clientHandler = new ClientHandler(clientSocket);
                    executorService.execute(clientHandler);
                } catch (IOException e) {
                    if (executorService.isShutdown()) {
                       // System.out.println("Server socket đã đóng do executor shutdown.");
                        break;
                    }
                    //System.err.println("Lỗi khi chấp nhận kết nối client: " + e.getMessage());
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
                   // System.err.println("ExecutorService không thể dừng hẳn.");
                }
            }
        } catch (InterruptedException ie) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
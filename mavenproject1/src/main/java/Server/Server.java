package Server;

public class Server {
    public String openPort(String id, String port) {
        try {
            int portNumber = Integer.parseInt(port);
            // Logic mở port thực tế có thể được thêm vào đây
            return "Mở Port " + portNumber + " cho ID " + id + " thành công!";
        } catch (NumberFormatException e) {
            return "Vui lòng nhập số cổng hợp lệ!";
        }
    }
}
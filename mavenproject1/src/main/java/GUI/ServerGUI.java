package GUI;

import javax.swing.*;
import java.awt.*;
import Server.Server;

public class ServerGUI extends JFrame {
    private JTextField txtId;
    private JTextField txtPort;
    private JButton btnOpenPort;
    private Server server; // Đối tượng server

    public ServerGUI() {
        server = new Server(); // Khởi tạo đối tượng Server
        initComponents();
    }

    private void initComponents() {
        setTitle("Giao diện Server");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JLabel lblId = new JLabel("ID:");
        txtId = new JTextField(20);
        JLabel lblPort = new JLabel("Port:");
        txtPort = new JTextField(5);
        btnOpenPort = new JButton("Mở Port");

        btnOpenPort.addActionListener(evt -> openPortAction());

        // Thiết lập layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lblId)
                .addComponent(lblPort))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(txtId)
                .addComponent(txtPort)
                .addComponent(btnOpenPort))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblId)
                .addComponent(txtId))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblPort)
                .addComponent(txtPort))
            .addComponent(btnOpenPort)
        );

        pack();
    }

    private void openPortAction() {
        String id = txtId.getText().trim();
        String port = txtPort.getText().trim();

        // Kiểm tra xem ID và Port có được nhập đầy đủ không
        if (id.isEmpty() || port.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ ID và Port.");
            return;
        }

        // Kiểm tra số Port có hợp lệ không
        int portNum;
        try {
            portNum = Integer.parseInt(port);
            if (portNum < 1 || portNum > 65535) {
                throw new NumberFormatException("Port phải nằm trong khoảng 1 đến 65535.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số Port không hợp lệ: " + e.getMessage());
            return;
        }

        // Gọi phương thức mở Port từ lớp Server
        String message = server.openPort(id, port);
        JOptionPane.showMessageDialog(this, message);
        
        // Chỉ đóng cửa sổ nếu thành công
        if (message.contains("đã mở")) {
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ServerGUI().setVisible(true));
    }
}
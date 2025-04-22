package GUI;

import javax.swing.*;
import java.awt.*;
import Server.Server; // Nhập lớp Server từ package Server

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
        String id = txtId.getText();
        String port = txtPort.getText();

        // Gọi phương thức mở port từ lớp Server
        String message = server.openPort(id, port);
        JOptionPane.showMessageDialog(this, message);
        
        // Đóng giao diện sau khi hiển thị thông báo
        dispose(); // Đóng cửa sổ
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ServerGUI().setVisible(true));
    }
}
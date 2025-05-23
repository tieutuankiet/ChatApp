package GUI;

import javax.swing.*;

public class Login extends javax.swing.JFrame {
    private LoginListener listener;

    public Login(LoginListener listener) {
        this.listener = listener;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        JLabel lblUsername = new JLabel("Tên đăng nhập:");
        JTextField txtUsername = new JTextField(20);
        JLabel lblPassword = new JLabel("Mật khẩu:");
        JPasswordField txtPassword = new JPasswordField(20);
        JLabel lblIp = new JLabel("IP:");
        JTextField txtIp = new JTextField(15);
        JLabel lblPort = new JLabel("Port:");
        JTextField txtPort = new JTextField(5);
        JButton btnLogin = new JButton("Đăng nhập");
        JButton btnRegister = new JButton("Đăng ký");

        btnLogin.addActionListener(evt -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());
            String ip = txtIp.getText();
            String port = txtPort.getText();
            listener.onLogin(username, password, ip, port);
        });
        btnRegister.addActionListener(evt -> openRegisterForm());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lblUsername)
                .addComponent(lblPassword)
                .addComponent(lblIp)
                .addComponent(lblPort))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(txtUsername)
                .addComponent(txtPassword)
                .addComponent(txtIp)
                .addComponent(txtPort)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnLogin)
                    .addComponent(btnRegister)))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblUsername)
                .addComponent(txtUsername))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblPassword)
                .addComponent(txtPassword))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblIp)
                .addComponent(txtIp))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblPort)
                .addComponent(txtPort))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnLogin)
                .addComponent(btnRegister))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the form
    }

    private void openRegisterForm() {
        new Register().setVisible(true); // Mở form đăng ký
    }

    public interface LoginListener {
        void onLogin(String username, String password, String ip, String port);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Login(null).setVisible(true));
    }
}
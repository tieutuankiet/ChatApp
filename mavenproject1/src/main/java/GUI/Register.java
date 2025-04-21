package GUI;

import javax.swing.*;
import java.awt.*;

public class Register extends JFrame {
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JTextField txtFullName;
    private JRadioButton rbtnMale, rbtnFemale;
    private JTextField txtBirthDate;
    private JButton btnRegister, btnSendOTP;

    public Register() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Đăng ký tài khoản mới");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Tạo panel với layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField(20);
        JLabel lblPassword = new JLabel("Mật khẩu:");
        txtPassword = new JPasswordField(20);
        JLabel lblFullName = new JLabel("Họ tên:");
        txtFullName = new JTextField(10);
        JLabel lblGender = new JLabel("Giới tính:");
        rbtnMale = new JRadioButton("Nam");
        rbtnFemale = new JRadioButton("Nữ");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbtnMale);
        genderGroup.add(rbtnFemale);
        JLabel lblBirthDate = new JLabel("Ngày sinh:");
        txtBirthDate = new JTextField(20);
        btnSendOTP = new JButton("Gửi OTP");
        btnRegister = new JButton("Đăng ký");

        // Thêm các thành phần vào panel
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(lblFullName);
        panel.add(txtFullName);
        panel.add(lblGender);
        panel.add(rbtnMale);
        panel.add(new JLabel()); // Thêm một ô trống
        panel.add(rbtnFemale);
        panel.add(lblBirthDate);
        panel.add(txtBirthDate);
        panel.add(btnSendOTP);
        panel.add(btnRegister);

        add(panel);
    }

    private void sendOTP() {
        JOptionPane.showMessageDialog(this, "OTP đã được gửi đến email!");
    }

    private void registerAccount() {
        JOptionPane.showMessageDialog(this, "Tài khoản đã được đăng ký!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Register().setVisible(true));
    }
}
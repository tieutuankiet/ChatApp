package GUI;

import javax.swing.*;

public class UpdateProfile extends JFrame {
    private JTextField txtFullName;
    private JRadioButton rbtnMale, rbtnFemale;
    private JTextField txtBirthDate;
    private JButton btnUpdate;

    public UpdateProfile() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Cập nhật thông tin tài khoản");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JLabel lblFullName = new JLabel("Họ tên:");
        txtFullName = new JTextField(20);
        JLabel lblGender = new JLabel("Giới tính:");
        rbtnMale = new JRadioButton("Nam");
        rbtnFemale = new JRadioButton("Nữ");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbtnMale);
        genderGroup.add(rbtnFemale);
        JLabel lblBirthDate = new JLabel("Ngày sinh:");
        txtBirthDate = new JTextField(10);
        btnUpdate = new JButton("Cập nhật");

        btnUpdate.addActionListener(e -> updateProfile());

        JPanel panel = new JPanel();
        panel.add(lblFullName);
        panel.add(txtFullName);
        panel.add(lblGender);
        panel.add(rbtnMale);
        panel.add(rbtnFemale);
        panel.add(lblBirthDate);
        panel.add(txtBirthDate);
        panel.add(btnUpdate);

        add(panel);
    }

    private void updateProfile() {
        // Xử lý cập nhật thông tin
        JOptionPane.showMessageDialog(this, "Thông tin đã được cập nhật!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UpdateProfile().setVisible(true));
    }
}
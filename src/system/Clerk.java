package system;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Clerk {

    private JFrame frame;
    private JLayeredPane layeredPane;
    private JPanel searchPanel;
    private JPanel settingsPanel;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField idFIeld;
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;


    public static void main() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Clerk window = new Clerk();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        createConnection();
    }

    public Clerk() {
        initialize();
    }

    private void initialize() {
        Image img1 = null;
        Image searchImg = null;
        Image settingsImg = null;
        Image logoutImg = null;
        try {
            img1 = ImageIO.read(new File("src/assets/hmslogo.png"));
            searchImg = ImageIO.read(new File("src/assets/search.png"));
            settingsImg = ImageIO.read(new File("src/assets/gear.png"));
            logoutImg = ImageIO.read(new File("src/assets/logout.png"));
        } catch (Exception e) {
            System.out.println(e);
        }


        frame = new JFrame();
        frame.setSize(840, 451);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0, 139, 139));
        panel.setBounds(0, 0, 248, 451);
        frame.getContentPane().add(panel);

        JLabel label = new JLabel("");
        label.setBounds(0, 0, 248, 129);
        Image newImg1 = img1.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        panel.setLayout(null);
        label.setIcon(new ImageIcon(newImg1));
        panel.add(label);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(0, 128, 128));
        panel_1.addMouseListener(new PanelButtonMouseAdapter(panel_1) {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchPanel(searchPanel);
            }
        });
        panel_1.setBounds(0, 139, 248, 44);
        panel.add(panel_1);

        JLabel icon1 = new JLabel("");
        icon1.setBounds(36, 0, 56, 44);
        Image newSearchImg = searchImg.getScaledInstance(icon1.getWidth(), icon1.getHeight(), Image.SCALE_SMOOTH);
        panel.setLayout(null);
        icon1.setIcon(new ImageIcon(newSearchImg));
        panel_1.add(icon1);

        JLabel lblSearchPatients = new JLabel("Search Patients");
        lblSearchPatients.setFont(new Font("Arial", Font.BOLD, 16));
        lblSearchPatients.setBounds(100, 0, 129, 44);
        panel_1.add(lblSearchPatients);

        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.addMouseListener(new PanelButtonMouseAdapter(panel_2) {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchPanel(settingsPanel);
            }
        });
        panel_2.setBackground(new Color(0, 128, 128));
        panel_2.setBounds(0, 193, 248, 44);
        panel.add(panel_2);

        JLabel icon2 = new JLabel("");
        icon2.setBounds(36, 0, 56, 44);
        Image newSettingsImg = settingsImg.getScaledInstance(icon2.getWidth(), icon2.getHeight(), Image.SCALE_SMOOTH);
        panel.setLayout(null);
        icon2.setIcon(new ImageIcon(newSettingsImg));
        panel_2.add(icon2);

        JLabel lblSettings = new JLabel("Settings");
        lblSettings.setFont(new Font("Arial", Font.BOLD, 16));
        lblSettings.setBounds(100, 0, 129, 44);
        panel_2.add(lblSettings);

        JPanel panel_3 = new JPanel();
        panel_3.setLayout(null);
        panel_3.addMouseListener(new PanelButtonMouseAdapter(panel_3) {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want Logout") == 0) {
                    frame.dispose();
                    Login l = null;
                    l = new Login();

                    l.setVisible(true);
                }
            }
        });
        panel_3.setBackground(new Color(0, 128, 128));
        panel_3.setBounds(0, 247, 248, 44);
        panel.add(panel_3);

        JLabel icon3 = new JLabel("");
        icon3.setBounds(36, 0, 56, 44);
        Image newLogoutImg = logoutImg.getScaledInstance(icon3.getWidth(), icon3.getHeight(), Image.SCALE_SMOOTH);
        panel.setLayout(null);
        icon3.setIcon(new ImageIcon(newLogoutImg));
        panel_3.add(icon3);

        JLabel lblLogout = new JLabel("Logout");
        lblLogout.setFont(new Font("Arial", Font.BOLD, 16));
        lblLogout.setBounds(100, 0, 129, 44);
        panel_3.add(lblLogout);

        layeredPane = new JLayeredPane();
        layeredPane.setBackground(new Color(47, 79, 79));
        layeredPane.setBounds(247, 0, 593, 451);
        frame.getContentPane().add(layeredPane);
        layeredPane.setLayout(new CardLayout(0, 0));

        searchPanel = new JPanel();
        searchPanel.setBackground(new Color(47, 79, 79));
        layeredPane.add(searchPanel, "name_27297756180700");
        searchPanel.setLayout(null);

        idFIeld = new JTextField();
        idFIeld.setBounds(155, 122, 180, 42);
        searchPanel.add(idFIeld);
        idFIeld.setColumns(10);

        JLabel lblNewLabel = new JLabel("Patient Id");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 17));
        lblNewLabel.setBounds(35, 121, 97, 42);
        searchPanel.add(lblNewLabel);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Arial", Font.BOLD, 18));
        lblName.setBounds(35, 247, 144, 42);
        searchPanel.add(lblName);

        JLabel lblBill = new JLabel("Bill");
        lblBill.setFont(new Font("Arial", Font.BOLD, 18));
        lblBill.setBounds(35, 309, 144, 42);
        searchPanel.add(lblBill);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("Arial", Font.BOLD, 18));
        lblAddress.setBounds(249, 249, 144, 42);
        searchPanel.add(lblAddress);

        JLabel lblType = new JLabel("Type");
        lblType.setFont(new Font("Arial", Font.BOLD, 18));
        lblType.setBounds(249, 309, 144, 42);
        searchPanel.add(lblType);

        JButton btnNewButton = new JButton("Search");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String temp = idFIeld.getText();

                Statement stmt;
                try {
                    stmt = (Statement) con.createStatement();
                    rs = stmt.executeQuery("select * from patients");
                    while (rs.next()) {
                        int idT = rs.getInt("id");
                        String nameT = rs.getString("name");
                        String addressT = rs.getString("address");
                        int billT = rs.getInt("bill");
                        String typeT = rs.getString("type");

                        lblName.setText(nameT);
                        lblBill.setText(Integer.toString(billT));
                        lblAddress.setText(addressT);
                        lblType.setText(typeT);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton.setFont(new Font("Arial", Font.BOLD, 16));
        btnNewButton.setBounds(361, 121, 144, 42);
        searchPanel.add(btnNewButton);

        settingsPanel = new JPanel();
        settingsPanel.setLayout(null);
        settingsPanel.setBackground(new Color(47, 79, 79));
        layeredPane.add(settingsPanel, "name_84580280859400");

        JLabel label_1 = new JLabel("Old Password");
        label_1.setFont(new Font("Arial", Font.BOLD, 15));
        label_1.setBounds(72, 100, 138, 51);
        settingsPanel.add(label_1);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(232, 100, 222, 51);
        settingsPanel.add(textField);

        JLabel label_2 = new JLabel("New Password");
        label_2.setFont(new Font("Arial", Font.BOLD, 15));
        label_2.setBounds(72, 176, 138, 51);
        settingsPanel.add(label_2);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(232, 176, 222, 51);
        settingsPanel.add(textField_1);

        JLabel label_3 = new JLabel("Confirm New Password");
        label_3.setFont(new Font("Arial", Font.BOLD, 13));
        label_3.setBounds(72, 257, 155, 51);
        settingsPanel.add(label_3);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(232, 257, 222, 51);
        settingsPanel.add(textField_2);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(72, 349, 138, 51);
        settingsPanel.add(btnClear);

        JButton btnChange = new JButton("Change");
        btnChange.setBounds(316, 349, 138, 51);
        settingsPanel.add(btnChange);


    }

    public void switchPanel(JPanel panel) {
        layeredPane.removeAll();
        layeredPane.add(panel);
        layeredPane.repaint();
        layeredPane.revalidate();
    }

    private class PanelButtonMouseAdapter extends MouseAdapter {

        JPanel panel;

        public PanelButtonMouseAdapter(JPanel panel) {
            this.panel = panel;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            panel.setBackground(new Color(112, 128, 144));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            panel.setBackground(new Color(0, 128, 128));
        }

        @Override
        public void mousePressed(MouseEvent e) {
            panel.setBackground(new Color(60, 179, 113));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            panel.setBackground(new Color(112, 128, 144));
        }
    }

    private static void createConnection() {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
            System.out.println("Success");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

package system;

import java.awt.EventQueue;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import java.awt.CardLayout;
import java.io.IOException;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Cashier {

    private JFrame frame;
    private JLayeredPane layeredPane;
    private JPanel billPanel;
    private JPanel paymentPanel;
    private JPanel settingsPanel;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;


    public static void main() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Cashier window = new Cashier();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Cashier() {
        initialize();
    }

    private void initialize() {
        Image img1 = null;
        Image addbillImg = null;
        Image paymentImg = null;
        Image settingsImg = null;
        Image logoutImg = null;
        try {
            img1 = ImageIO.read(new File("src/assets/hmslogo.png"));
            addbillImg = ImageIO.read(new File("src/assets/addbill.png"));
            paymentImg = ImageIO.read(new File("src/assets/payment.png"));
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
        panel_1.addMouseListener(new PanelButtonMouseAdapter(panel_1) {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchPanel(billPanel);
            }
        });
        panel_1.setBackground(new Color(0, 128, 128));
        panel_1.setBounds(0, 139, 248, 44);
        panel.add(panel_1);

        JLabel icon1 = new JLabel("");
        icon1.setBounds(36, 0, 56, 44);
        Image newaddbillImg = addbillImg.getScaledInstance(icon1.getWidth(), icon1.getHeight(), Image.SCALE_SMOOTH);
        panel.setLayout(null);
        icon1.setIcon(new ImageIcon(newaddbillImg));
        panel_1.add(icon1);

        JLabel lblAddBill = new JLabel("Add Bill");
        lblAddBill.setFont(new Font("Arial", Font.BOLD, 16));
        lblAddBill.setBounds(100, 0, 129, 44);
        panel_1.add(lblAddBill);

        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.addMouseListener(new PanelButtonMouseAdapter(panel_2) {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchPanel(paymentPanel);
            }
        });
        panel_2.setBackground(new Color(0, 128, 128));
        panel_2.setBounds(0, 193, 248, 44);
        panel.add(panel_2);

        JLabel icon2 = new JLabel("");
        icon2.setBounds(36, 0, 56, 44);
        Image newpaymentImg = paymentImg.getScaledInstance(icon2.getWidth(), icon2.getHeight(), Image.SCALE_SMOOTH);
        panel.setLayout(null);
        icon2.setIcon(new ImageIcon(newpaymentImg));
        panel_2.add(icon2);

        JLabel lblAddPayment = new JLabel("Add Payment");
        lblAddPayment.setFont(new Font("Arial", Font.BOLD, 16));
        lblAddPayment.setBounds(100, 0, 129, 44);
        panel_2.add(lblAddPayment);

        JPanel panel_4 = new JPanel();
        panel_4.setLayout(null);
        panel_4.addMouseListener(new PanelButtonMouseAdapter(panel_4) {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchPanel(settingsPanel);
            }
        });
        panel_4.setBackground(new Color(0, 128, 128));
        panel_4.setBounds(0, 247, 248, 44);
        panel.add(panel_4);

        JLabel label_7 = new JLabel("Settings");
        label_7.setFont(new Font("Arial", Font.BOLD, 16));
        label_7.setBounds(100, 0, 129, 44);
        panel_4.add(label_7);

        JLabel icon3 = new JLabel("");
        icon3.setBounds(36, 0, 56, 44);
        Image newsettingsImg = settingsImg.getScaledInstance(icon3.getWidth(), icon3.getHeight(), Image.SCALE_SMOOTH);
        panel.setLayout(null);
        icon3.setIcon(new ImageIcon(newsettingsImg));
        panel_4.add(icon3);

        JPanel panel_5 = new JPanel();
        panel_5.setLayout(null);
        panel_5.addMouseListener(new PanelButtonMouseAdapter(panel_5) {
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
        panel_5.setBackground(new Color(0, 128, 128));
        panel_5.setBounds(0, 301, 248, 44);
        panel.add(panel_5);

        JLabel label_9 = new JLabel("Logout");
        label_9.setFont(new Font("Arial", Font.BOLD, 16));
        label_9.setBounds(100, 0, 129, 44);
        panel_5.add(label_9);

        JLabel icon4 = new JLabel("");
        icon4.setBounds(36, 0, 56, 44);
        Image newlogoutImg = logoutImg.getScaledInstance(icon4.getWidth(), icon4.getHeight(), Image.SCALE_SMOOTH);
        panel.setLayout(null);
        icon4.setIcon(new ImageIcon(newlogoutImg));
        panel_5.add(icon4);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(247, 0, 593, 451);
        frame.getContentPane().add(layeredPane);
        layeredPane.setLayout(new CardLayout(0, 0));

        billPanel = new JPanel();
        layeredPane.add(billPanel, "name_92813300108000");
        billPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("billllll");
        lblNewLabel.setBounds(116, 235, 46, 13);
        billPanel.add(lblNewLabel);

        paymentPanel = new JPanel();
        layeredPane.add(paymentPanel, "name_92815853058600");
        paymentPanel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("payment");
        lblNewLabel_1.setBounds(139, 229, 46, 13);
        paymentPanel.add(lblNewLabel_1);

        settingsPanel = new JPanel();
        settingsPanel.setLayout(null);
        settingsPanel.setBackground(new Color(47, 79, 79));
        layeredPane.add(settingsPanel, "name_87327954796900");

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

        JButton button = new JButton("Clear");
        button.setBounds(72, 349, 138, 51);
        settingsPanel.add(button);

        JButton button_1 = new JButton("Change");
        button_1.setBounds(316, 349, 138, 51);
        settingsPanel.add(button_1);
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
}

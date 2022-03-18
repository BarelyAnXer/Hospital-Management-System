package system;

import java.awt.EventQueue;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField username;
    private JPasswordField password;

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        createConnection();
    }


    public Login() {

        BufferedImage logoimg = null;
        BufferedImage userimg = null;
        BufferedImage buttonimg = null;
        try {
            logoimg = ImageIO.read(new File("src/assets/hmslogo.png"));
            userimg = ImageIO.read(new File("src/assets/user.png"));
            buttonimg = ImageIO.read(new File("src/assets/pass.png"));
        } catch (IOException e) {
            System.out.println(e);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(604, 407);
        setLocationRelativeTo(null);
        setUndecorated(true);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 128));
        contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(180, 175, 250, 40);
        contentPane.add(panel);
        panel.setLayout(null);

        username = new JTextField();
        username.setText("Username");
        username.setBorder(null);
        username.setFont(new Font("Arial", Font.PLAIN, 16));
        username.setBounds(10, 0, 170, 40);
        panel.add(username);
        username.setColumns(10);

        JLabel usericon = new JLabel("");
        usericon.setBounds(204, 0, 46, 40);
        Image temp1 = userimg.getScaledInstance(usericon.getWidth(), usericon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon1 = new ImageIcon(temp1);
        usericon.setIcon(imageIcon1);
        panel.add(usericon);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(180, 236, 250, 40);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        password = new JPasswordField();
        password.setFont(new Font("Arial", Font.PLAIN, 16));
        password.setBorder(null);
        password.setEchoChar((char) 0);
        password.setBounds(10, 0, 170, 40);
        password.setText("Password");
        panel_1.add(password);

        JLabel passicon = new JLabel("");
        passicon.setBounds(204, 0, 46, 40);
        panel_1.add(passicon);


        JLabel BtnLbl = new JLabel("");
        BtnLbl.setBounds(204, 0, 46, 40);
        panel_1.add(BtnLbl);
        Image bimg = buttonimg.getScaledInstance(BtnLbl.getWidth(), BtnLbl.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon btnicon = new ImageIcon(bimg);
        BtnLbl.setIcon(btnicon);

        JLabel loginmes = new JLabel("");
        loginmes.setForeground(Color.RED);
        loginmes.setFont(new Font("Arial Black", Font.PLAIN, 14));
        loginmes.setHorizontalAlignment(SwingConstants.CENTER);
        loginmes.setBounds(180, 297, 250, 21);
        contentPane.add(loginmes);

        JPanel pnlbtn = new JPanel();
        pnlbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //validation
                //wag kalimutan mag settext sa loginmessage  = login mes

                try {
                    String query = "select username, password from users where username=?and password=?";
                    pst = con.prepareStatement(query);
                    pst.setString(1, username.getText());
                    pst.setString(2, password.getText());

                    System.out.println(username.getText());
                    System.out.println(password.getText());
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        System.out.println("here");

                        if (rs.getString("username").trim().equals("admin") && rs.getString("password").trim().equals("admin")) {
                            AdminTry ad = new AdminTry();
                            ad.main();
                            dispose();
                            System.out.println("her3e");
                        } else if (rs.getString("username").trim().equals("clerk") && rs.getString("password").trim().equals("clerk")) {
                            Clerk cl = new Clerk();
                            cl.main();
                            dispose();
                        } else if (rs.getString("username").trim().equals("cashier") && rs.getString("password").trim().equals("cashier")) {
                            Cashier ca = new Cashier();
                            ca.main();
                            dispose();
                        }
                    } else {

                        System.out.println("invalid username or password");
                        loginmes.setText("invalid username or password");
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }


            }
        });
        pnlbtn.setBackground(new Color(47, 79, 79));
        pnlbtn.setBounds(180, 328, 250, 50);
        contentPane.add(pnlbtn);
        pnlbtn.setLayout(null);

        JLabel lblNewLabel = new JLabel("LOGIN");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setBounds(115, 10, 125, 30);
        pnlbtn.add(lblNewLabel);

        JLabel loginIcon = new JLabel("");
        try {
            loginIcon.setIcon(new ImageIcon(ImageIO.read(new File("src/assets/login.png"))));
        } catch (Exception e) {
            System.out.println(e);
        }
        loginIcon.setBounds(47, 0, 58, 50);
        pnlbtn.add(loginIcon);


        JLabel x = new JLabel("x");
        x.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure About that?", "Confirm Exit", JOptionPane.YES_NO_OPTION) == 0) {
                    Login.this.dispose();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                x.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                x.setForeground(Color.WHITE);
            }
        });
        x.setForeground(Color.WHITE);
        x.setFont(new Font("Comic Sans MS", Font.BOLD, 19));
        x.setHorizontalAlignment(SwingConstants.CENTER);
        x.setBounds(564, 0, 40, 40);
        contentPane.add(x);

        JLabel trys = new JLabel("");
        trys.setIcon(new ImageIcon(logoimg));
        trys.setBounds(101, 10, 411, 183);
        Image dimg = logoimg.getScaledInstance(trys.getWidth(), trys.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        trys.setIcon(imageIcon);
        contentPane.add(trys);
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

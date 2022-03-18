package system;

import java.awt.EventQueue;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.*;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminTry {

    private JFrame frame;
    private JLayeredPane layeredPane;
    private JPanel patientsPanel;
    private JPanel doctorsPanel;
    private JPanel settingsPanel;

    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private JTextField addressField;
    private JTextField typeField;
    private JTextField nameField;
    private JTextField billField;
    private static JTable table_1;
    private static int specialId;
    private static int docSpecialId;
    private JTextField docAddressField;
    private JTextField docDepartmentField;
    private JTextField docNameField;
    private JTextField docPhField;
    private JTable docTable;
    private JTextField uno;
    private JTextField dos;
    private JTextField tres;

    public static void main() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminTry window = new AdminTry();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        createConnection();
        //add doclick dun sa referesh check mo muna yung sa pano gumagana
    }

    public AdminTry() {
        initialize();
    }

    private void initialize() {
        Image img1 = null;
        Image patientsImg = null;
        Image doctorsImg = null;
        Image settingsImg = null;
        Image logoutImg = null;
        try {
            img1 = ImageIO.read(new File("src/assets/hmslogo.png"));
            patientsImg = ImageIO.read(new File("src/assets/patient.png"));
            doctorsImg = ImageIO.read(new File("src/assets/doctor.png"));
            settingsImg = ImageIO.read(new File("src/assets/gear.png"));
            logoutImg = ImageIO.read(new File("src/assets/logout.png"));
        } catch (Exception e) {
            System.out.println(e);
        }

        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(47, 79, 79));
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 139, 139));
        panel.setBounds(0, 0, 248, 451);
        frame.getContentPane().add(panel);

        JLabel logo = new JLabel("");
        logo.setBounds(0, 0, 248, 129);
        Image newImg1 = img1.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);
        panel.setLayout(null);
        logo.setIcon(new ImageIcon(newImg1));
        panel.add(logo);

        JPanel panel_1 = new JPanel();
        panel_1.addMouseListener(new PanelButtonMouseAdapter(panel_1) {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchPanel(patientsPanel);
            }
        });
        panel_1.setBounds(0, 139, 248, 44);
        panel_1.setBackground(new Color(0, 128, 128));
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel icon1 = new JLabel("");
        icon1.setBounds(36, 0, 56, 44);
        panel_1.add(icon1);
        Image newPatientsImg = patientsImg.getScaledInstance(icon1.getWidth(), icon1.getHeight(), Image.SCALE_SMOOTH);
        icon1.setIcon(new ImageIcon(newPatientsImg));

        JLabel lblNewLabel = new JLabel("Patients");
        lblNewLabel.setBounds(100, 0, 129, 44);
        panel_1.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.setLayout(null);

        JPanel panel_2 = new JPanel();
        panel_2.addMouseListener(new PanelButtonMouseAdapter(panel_2) {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchPanel(doctorsPanel);
            }
        });
        panel_2.setBounds(0, 193, 248, 44);
        panel_2.setBackground(new Color(0, 128, 128));
        panel.add(panel_2);
        panel_2.setLayout(null);

        JLabel icon2 = new JLabel("");
        icon2.setBounds(36, 0, 56, 44);
        Image newDoctorsImg = doctorsImg.getScaledInstance(icon2.getWidth(), icon2.getHeight(), Image.SCALE_SMOOTH);
        panel.setLayout(null);
        icon2.setIcon(new ImageIcon(newDoctorsImg));
        panel_2.add(icon2);

        JLabel lblNewLabel_2 = new JLabel("Doctors");
        lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
        lblNewLabel_2.setBounds(100, 0, 129, 44);
        panel_2.add(lblNewLabel_2);

        JPanel panel_4 = new JPanel();
        panel_4.addMouseListener(new PanelButtonMouseAdapter(panel_4) {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchPanel(settingsPanel);
            }
        });
        panel_4.setBounds(0, 247, 248, 44);
        panel_4.setBackground(new Color(0, 128, 128));
        panel.add(panel_4);
        panel_4.setLayout(null);

        JLabel lblSettings = new JLabel("Settings");
        lblSettings.setFont(new Font("Arial", Font.BOLD, 16));
        lblSettings.setBounds(100, 0, 129, 44);
        panel_4.add(lblSettings);

        JLabel icon4 = new JLabel("");
        icon4.setBounds(36, 0, 56, 44);
        Image newSettingsImg = settingsImg.getScaledInstance(icon4.getWidth(), icon4.getHeight(), Image.SCALE_SMOOTH);
        panel.setLayout(null);
        icon4.setIcon(new ImageIcon(newSettingsImg));
        panel_4.add(icon4);

        JPanel panel_5 = new JPanel();
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
        panel_5.setLayout(null);
        panel_5.setBackground(new Color(0, 128, 128));
        panel_5.setBounds(0, 301, 248, 44);
        panel.add(panel_5);

        JLabel lblLogout = new JLabel("Logout");
        lblLogout.setFont(new Font("Arial", Font.BOLD, 16));
        lblLogout.setBounds(100, 0, 129, 44);
        panel_5.add(lblLogout);

        JLabel icon5 = new JLabel("");
        icon5.setBounds(36, 0, 56, 44);
        Image newLogoutImg = logoutImg.getScaledInstance(icon5.getWidth(), icon5.getHeight(), Image.SCALE_SMOOTH);
        panel.setLayout(null);
        icon5.setIcon(new ImageIcon(newLogoutImg));
        panel_5.add(icon5);

        layeredPane = new JLayeredPane();
        layeredPane.setBackground(new Color(0, 139, 139));
        layeredPane.setBounds(246, 0, 594, 451);
        frame.getContentPane().add(layeredPane);
        layeredPane.setLayout(new CardLayout(0, 0));

        patientsPanel = new JPanel();
        patientsPanel.setBackground(new Color(47, 79, 79));
        layeredPane.add(patientsPanel, "name_20808025832700");
        patientsPanel.setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Arial", Font.BOLD, 18));
        lblName.setBounds(10, 18, 64, 33);
        patientsPanel.add(lblName);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setFont(new Font("Arial", Font.BOLD, 18));
        lblAddress.setBounds(294, 18, 95, 33);
        patientsPanel.add(lblAddress);

        JLabel lblBill = new JLabel("Type:");
        lblBill.setFont(new Font("Arial", Font.BOLD, 18));
        lblBill.setBounds(294, 60, 64, 33);
        patientsPanel.add(lblBill);

        addressField = new JTextField();
        addressField.setColumns(10);
        addressField.setBounds(399, 19, 185, 33);
        patientsPanel.add(addressField);

        typeField = new JTextField();
        typeField.setColumns(10);
        typeField.setBounds(399, 61, 185, 33);
        patientsPanel.add(typeField);

        nameField = new JTextField();
        nameField.setColumns(10);
        nameField.setBounds(99, 18, 185, 33);
        patientsPanel.add(nameField);

        JLabel lblBill_1 = new JLabel("Bill:");
        lblBill_1.setFont(new Font("Arial", Font.BOLD, 18));
        lblBill_1.setBounds(10, 61, 64, 33);
        patientsPanel.add(lblBill_1);

        billField = new JTextField();
        billField.setColumns(10);
        billField.setBounds(99, 59, 185, 33);
        patientsPanel.add(billField);

        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(152, 154, 432, 287);
        patientsPanel.add(scrollPane1);

        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "ID", "FULLNAME", "ADDRESS", "BILL", "TYPE"
                }
        ));
        scrollPane1.setViewportView(table_1);

        JButton btnInsert = new JButton("Insert");
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = addressField.getText();
                int bill = Integer.parseInt(billField.getText());
                String type = typeField.getText();
				
				/*if(name.equals("") || address.equals("") || billField.getText().equals("") || type.equals("")) {
					System.out.println("enter something in the field");
				}*/
                //else{
                try {
                    pst = con.prepareStatement("INSERT INTO patients VALUES(?,?,?,?,?)");
                    pst.setString(1, null);
                    pst.setString(2, name);
                    pst.setString(3, address);
                    pst.setInt(4, bill);
                    pst.setString(5, type);
                    pst.execute();
                    System.out.println("data inserted");
                    pst.close();


                } catch (SQLException e1) {
                    System.out.println(e1);
                }
                //}
                // magpalitaw ng option pane pero di opetion pane tawag dun syempre alang option
                //para sa data inserted


            }
        });
        btnInsert.setBounds(10, 152, 132, 49);
        patientsPanel.add(btnInsert);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tm = (DefaultTableModel) table_1.getModel();
                int rowCount = tm.getRowCount();

                for (int i = rowCount - 1; i >= 0; i--) {
                    tm.removeRow(i);
                }

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
                        tm.addRow(new Object[]{idT, nameT, addressT, billT, typeT});
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnRefresh.setBounds(10, 392, 132, 49);
        patientsPanel.add(btnRefresh);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement stmt = con.prepareStatement("UPDATE patients SET name=?,address=?,bill=?,type=?  where id = ? ");
                    stmt.setString(1, nameField.getText());
                    stmt.setString(2, addressField.getText());
                    stmt.setInt(3, Integer.parseInt(billField.getText()));
                    stmt.setString(4, typeField.getText());
                    stmt.setInt(5, specialId);
                    stmt.executeUpdate();
                    System.out.println("updated");
                    stmt.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                //pindutin muna yung gusto iedit sa table then edit
                //then update pag na palitan na gusto

            }
        });
        btnUpdate.setBounds(10, 329, 132, 49);
        patientsPanel.add(btnUpdate);

        JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tm = (DefaultTableModel) table_1.getModel();
                int row = table_1.getSelectedRow();
                specialId = (int) tm.getValueAt(row, 0);
                String name = (String) tm.getValueAt(row, 1);
                String address = (String) tm.getValueAt(row, 2);
                String bill = tm.getValueAt(row, 3).toString();
                String type = (String) tm.getValueAt(row, 4);

                nameField.setText(name);
                addressField.setText(address);
                billField.setText(bill);
                typeField.setText(type);
            }
        });
        btnEdit.setBounds(10, 270, 132, 49);
        patientsPanel.add(btnEdit);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean b = table_1.getSelectionModel().isSelectionEmpty();

                System.out.println(b);
                if (!b == true) {
                    int row = table_1.getSelectedRow();
                    int id = (int) table_1.getValueAt(row, 0);
                    try {
                        PreparedStatement stmt = con.prepareStatement("DELETE FROM patients WHERE id = ?");
                        stmt.setInt(1, id);
                        stmt.executeUpdate();
                        System.out.println("deleted");
                        stmt.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    btnRefresh.doClick();
                }
            }
        });
        btnDelete.setBounds(10, 211, 132, 49);
        patientsPanel.add(btnDelete);

        doctorsPanel = new JPanel();
        doctorsPanel.setBackground(new Color(47, 79, 79));
        layeredPane.add(doctorsPanel, "name_20812117206800");
        doctorsPanel.setLayout(null);

        JLabel label = new JLabel("Name:");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setBounds(10, 18, 64, 33);
        doctorsPanel.add(label);

        JLabel label_2 = new JLabel("Address:");
        label_2.setFont(new Font("Arial", Font.BOLD, 18));
        label_2.setBounds(294, 18, 95, 33);
        doctorsPanel.add(label_2);

        JLabel lblDepartment = new JLabel("Department:");
        lblDepartment.setFont(new Font("Arial", Font.BOLD, 16));
        lblDepartment.setBounds(294, 60, 95, 33);
        doctorsPanel.add(lblDepartment);

        docAddressField = new JTextField();
        docAddressField.setColumns(10);
        docAddressField.setBounds(399, 19, 185, 33);
        doctorsPanel.add(docAddressField);

        docDepartmentField = new JTextField();
        docDepartmentField.setColumns(10);
        docDepartmentField.setBounds(399, 62, 185, 33);
        doctorsPanel.add(docDepartmentField);

        docNameField = new JTextField();
        docNameField.setColumns(10);
        docNameField.setBounds(99, 18, 185, 33);
        doctorsPanel.add(docNameField);

        JLabel lblPhoneNo = new JLabel("PH#:");
        lblPhoneNo.setFont(new Font("Arial", Font.BOLD, 18));
        lblPhoneNo.setBounds(10, 61, 64, 33);
        doctorsPanel.add(lblPhoneNo);

        docPhField = new JTextField();
        docPhField.setColumns(10);
        docPhField.setBounds(99, 59, 185, 33);
        doctorsPanel.add(docPhField);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(152, 154, 432, 287);
        doctorsPanel.add(scrollPane);

        docTable = new JTable();
        docTable.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "ID", "NAME", "ADDRESS", "PH#", "DEPARTMENT"
                }
        ));
        scrollPane.setViewportView(docTable);

        JButton docInsert = new JButton("Insert");
        docInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = docNameField.getText();
                String address = docAddressField.getText();
                String phnum = docPhField.getText();
                String department = docDepartmentField.getText();

                try {
                    pst = con.prepareStatement("INSERT INTO doctors VALUES(?,?,?,?,?)");
                    pst.setString(1, null);
                    pst.setString(2, name);
                    pst.setString(3, address);
                    pst.setString(4, phnum);
                    pst.setString(5, department);
                    pst.execute();
                    System.out.println("data inserted");
                    pst.close();


                } catch (SQLException e1) {
                    System.out.println(e1);
                }

            }
        });
        docInsert.setBounds(10, 152, 132, 49);
        doctorsPanel.add(docInsert);

        JButton docEdit = new JButton("Edit");
        docEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tm = (DefaultTableModel) docTable.getModel();
                int row = docTable.getSelectedRow();
                docSpecialId = (int) tm.getValueAt(row, 0);
                String name = (String) tm.getValueAt(row, 1);
                String address = (String) tm.getValueAt(row, 2);
                String phnum = tm.getValueAt(row, 3).toString();
                String department = (String) tm.getValueAt(row, 4);

                docNameField.setText(name);
                docAddressField.setText(address);
                docPhField.setText(phnum);
                docDepartmentField.setText(department);
            }
        });
        docEdit.setBounds(10, 270, 132, 49);
        doctorsPanel.add(docEdit);

        JButton docUpdate = new JButton("Update");
        docUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement stmt = con.prepareStatement("UPDATE doctors SET name=?,address=?,mobile=?,department=?  where id = ? ");
                    stmt.setString(1, docNameField.getText());
                    stmt.setString(2, docAddressField.getText());
                    stmt.setString(3, docPhField.getText());
                    stmt.setString(4, docDepartmentField.getText());
                    stmt.setInt(5, docSpecialId);
                    stmt.executeUpdate();
                    System.out.println("updated");
                    stmt.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        docUpdate.setBounds(10, 329, 132, 49);
        doctorsPanel.add(docUpdate);

        JButton docRefresh = new JButton("Refresh");
        docRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tm = (DefaultTableModel) docTable.getModel();
                int rowCount = tm.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    tm.removeRow(i);
                }
                Statement stmt;
                try {
                    stmt = (Statement) con.createStatement();
                    rs = stmt.executeQuery("select * from doctors");
                    while (rs.next()) {
                        int idT = rs.getInt("id");
                        String nameT = rs.getString("name");
                        String addressT = rs.getString("address");
                        String phT = rs.getString("mobile");
                        String departmentT = rs.getString("department");
                        tm.addRow(new Object[]{idT, nameT, addressT, phT, departmentT});
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        docRefresh.setBounds(10, 392, 132, 49);
        doctorsPanel.add(docRefresh);

        JButton docDelete = new JButton("Delete");
        docDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                boolean b = docTable.getSelectionModel().isSelectionEmpty();

                System.out.println(b);
                if (!b == true) {
                    int row = docTable.getSelectedRow();
                    int id = (int) docTable.getValueAt(row, 0);
                    try {
                        PreparedStatement stmt = con.prepareStatement("DELETE FROM doctors WHERE id = ?");
                        stmt.setInt(1, id);
                        stmt.executeUpdate();
                        System.out.println("deleted");
                        stmt.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    docRefresh.doClick();
                }

            }
        });
        docDelete.setBounds(10, 211, 132, 49);
        doctorsPanel.add(docDelete);

        settingsPanel = new JPanel();
        settingsPanel.setBackground(new Color(47, 79, 79));
        layeredPane.add(settingsPanel, "name_20817400995600");
        settingsPanel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Old Password");
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
        lblNewLabel_1.setBounds(72, 100, 138, 51);
        settingsPanel.add(lblNewLabel_1);

        uno = new JTextField();
        uno.setBounds(232, 101, 222, 51);
        settingsPanel.add(uno);
        uno.setColumns(10);

        JLabel lblNewPassword = new JLabel("New Password");
        lblNewPassword.setFont(new Font("Arial", Font.BOLD, 15));
        lblNewPassword.setBounds(72, 176, 138, 51);
        settingsPanel.add(lblNewPassword);

        dos = new JTextField();
        dos.setColumns(10);
        dos.setBounds(232, 176, 222, 51);
        settingsPanel.add(dos);

        JLabel lblConfirmNewPassword = new JLabel("Confirm New Password");
        lblConfirmNewPassword.setFont(new Font("Arial", Font.BOLD, 13));
        lblConfirmNewPassword.setBounds(72, 257, 155, 51);
        settingsPanel.add(lblConfirmNewPassword);

        tres = new JTextField();
        tres.setColumns(10);
        tres.setBounds(232, 257, 222, 51);
        settingsPanel.add(tres);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uno.setText("");
                dos.setText("");
                tres.setText("");
            }
        });
        btnClear.setBounds(72, 349, 138, 51);
        settingsPanel.add(btnClear);

        JButton btnChange = new JButton("Change");
        btnChange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //di pa gumagana chagne password mo

                Statement stmt;
                try {
                    stmt = (Statement) con.createStatement();
                    rs = stmt.executeQuery("select * from users");
                    while (rs.next()) {
                        String user = rs.getString("username");
                        String pass = rs.getString("password");
                        System.out.println(pass);
                        if (pass.indexOf(uno.getText()) != -1) {
                            String t1 = dos.getText();
                            String t2 = tres.getText();
                            if (t1.equals(t2)) {
                                PreparedStatement stmt2 = con.prepareStatement("UPDATE users SET password = ? where username = ? ");
                                stmt2.setString(1, tres.getText());
                                stmt2.setString(2, "admin");
                            }
                        }


                    }


                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });
        btnChange.setBounds(316, 349, 138, 51);
        settingsPanel.add(btnChange);

        frame.setUndecorated(true);
        frame.setSize(840, 451);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

    public void switchPanel(JPanel panel) {
        layeredPane.removeAll();
        layeredPane.add(panel);
        layeredPane.repaint();
        layeredPane.revalidate();
    }

    private static void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
            System.out.println("Success");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
}



















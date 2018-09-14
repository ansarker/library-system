package main.dashboard;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import main.DatabaseConnection;
import main.login.Login;

public class Dashboard extends javax.swing.JFrame {

    DatabaseConnection dbc = new DatabaseConnection();
    
    Connection conn;
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;

    String img_path = null;
    
    public Dashboard() {
        initComponents();

        UserPanel.setVisible(false);
        ProfilePanel.setVisible(false);
        LibraryPanel.setVisible(false);
        WhoAmIPanel.setVisible(false);

        BookID.setEditable(false);

        show_user();
        show_book();
        show_loan();
        
        disable_edit(false);
        issue_book_info_editability(false);
        issue_user_info_editability(false);
        
        whoami();
    }
    
    private void whoami(){
        String path = "/home/void/NetBeansProjects/LibrarySystem/src/img/formal-pp.JPG";
        WhoAmIPhotoLabel.setIcon(ResizedImage(WhoAmIPhotoLabel, path));
    }
    
    public ArrayList<Loan> loanList() {
        ArrayList<Loan> loansList = new ArrayList<>();
        try {    
            String sql = "SELECT * FROM Loan";
            
            conn = dbc.connect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            Loan loan;
            while(rs.next()) {
                loan = new Loan(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
                loansList.add(loan);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return loansList;
    }
    
    private void show_loan() {
        ArrayList<Loan> list = loanList();
        DefaultTableModel loanTable = (DefaultTableModel) LoanTable.getModel();
        Object[] row = new Object[5];
        for(int i = 0; i < list.size(); i++) {
            row[0] = i+1;
            row[1] = list.get(i).getIssueID();
            row[2] = list.get(i).getBookID();
            row[3] = list.get(i).getUserID();
            row[4] = list.get(i).getIssueDate();
            loanTable.addRow(row);
        }
    }

    public ArrayList<User> userList() {
        ArrayList<User> usersList = new ArrayList<>();
        String sql = "SELECT * FROM User";
        
        try {
            conn = dbc.connect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            User user;
            while (rs.next()) {
                user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                usersList.add(user);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return usersList;
    }

    private void show_user() {
        ArrayList<User> list = userList();
        DefaultTableModel userTable = (DefaultTableModel) UserTable.getModel();
        Object[] row = new Object[8];
        for (int i = 0; i < list.size(); i++) {
            row[0] = i + 1;
            row[1] = list.get(i).getUserID();
            row[2] = list.get(i).getMember_type();
            row[3] = list.get(i).getEmail();
            row[4] = list.get(i).getMobile();
            row[5] = list.get(i).getSex();
            row[6] = list.get(i).getDate_of_birth();
            row[7] = list.get(i).getAddress();

            userTable.addRow(row);
        }
    }

    public ArrayList<Book> bookList() {
        ArrayList<Book> booksList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Book";

            conn = dbc.connect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            Book book;
            while (rs.next()) {
                book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
                booksList.add(book);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return booksList;
    }

    private void show_book() {
        ArrayList<Book> list = bookList();
        DefaultTableModel bookTable = (DefaultTableModel) BookTable.getModel();
        Object[] row = new Object[9];
        for (int i = 0; i < list.size(); i++) {
            row[0] = i + 1;
            row[1] = list.get(i).getBookID();
            row[2] = list.get(i).getTitle();
            row[3] = list.get(i).getAuthor();
            row[4] = list.get(i).getCategory();
            row[5] = list.get(i).getPublisher();
            row[6] = list.get(i).getISBN();
            row[7] = list.get(i).getEdition();
            row[8] = list.get(i).getLanguage();
            
            bookTable.addRow(row);
        }
    }

    public void user_profile(String user_id) {
        String query = "SELECT * FROM USER WHERE user_id = " + user_id;
        try {
            conn = dbc.connect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                ProID.setText(rs.getString(1));
                ProRole.setText(rs.getString(3));
                ProEmail.setText(rs.getString(4));
                ProMobile.setText(rs.getString(5));
                ProDOB.setText(rs.getString(7));
                ProSex.setText(rs.getString(6));
                ProAddress.setText(rs.getString(8));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void disable_edit(boolean flag) {
        ProID.setEditable(flag);
        ProRole.setEditable(flag);
        ProEmail.setEditable(flag);
        ProMobile.setEditable(flag);
        ProDOB.setEditable(flag);
        ProSex.setEditable(flag);
        ProAddress.setEditable(flag);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        UserBtn = new javax.swing.JButton();
        ProfileBtn = new javax.swing.JButton();
        LibraryBtn = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        LibraryPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        BookTable = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        BookID = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        Title = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        Author = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Publisher = new javax.swing.JTextField();
        Category = new javax.swing.JTextField();
        ImagePath = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        ChooseImageBtn = new javax.swing.JButton();
        Language = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        Edition = new javax.swing.JTextField();
        ISBN = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Error = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        AddBookBtn = new javax.swing.JButton();
        ResetBtn = new javax.swing.JButton();
        DeleteBookBtn = new javax.swing.JButton();
        UpdateBookBtn = new javax.swing.JButton();
        DelBookID = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        IssueCategory = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        IssueISBN = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        IssueLanguage = new javax.swing.JTextField();
        IssueEdition = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        IssueAuthor = new javax.swing.JTextField();
        BookInfo = new javax.swing.JButton();
        IssueBookCover = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        IssueBookID = new javax.swing.JTextField();
        IssueBookTitle = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        IssuePublisher = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        IssueSex = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        IssueMobile = new javax.swing.JTextField();
        UserInfo = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        IssueUserID = new javax.swing.JTextField();
        IssueEmail = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        IssueDateOfBirth = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        IssueAddress = new javax.swing.JTextArea();
        jPanel13 = new javax.swing.JPanel();
        IssueBtn = new javax.swing.JButton();
        ErrorNotification = new javax.swing.JLabel();
        DateOfIssue = new com.toedter.calendar.JDateChooser();
        jLabel32 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        LoanTable = new javax.swing.JTable();
        jLabel70 = new javax.swing.JLabel();
        II = new javax.swing.JTextField();
        ID = new com.toedter.calendar.JDateChooser();
        jLabel76 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        UP = new javax.swing.JTextField();
        BI = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        UI = new javax.swing.JTextField();
        BT = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        BC = new javax.swing.JLabel();
        BA = new javax.swing.JTextField();
        UE = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        UserPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        UserTable = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        UserRole = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        UserDateOfBirth = new com.toedter.calendar.JDateChooser();
        Male = new javax.swing.JRadioButton();
        Female = new javax.swing.JRadioButton();
        UserEmail = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        UserAddress = new javax.swing.JTextArea();
        jLabel67 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        UserMobile = new javax.swing.JTextField();
        UserID = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        UserResetBtn = new javax.swing.JButton();
        DeleteUserBtn = new javax.swing.JButton();
        ProfilePanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPasswordField3 = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        ProID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        ProRole = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        ProAddress = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ProSex = new javax.swing.JTextField();
        ProDOB = new javax.swing.JTextField();
        ProMobile = new javax.swing.JTextField();
        ProEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        EditProfile = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        WhoAmIPanel = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        WhoAmIPhotoLabel = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Logout = new javax.swing.JMenuItem();
        ExitMenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        WhoAmI = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard");
        setSize(new java.awt.Dimension(840, 480));

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));

        jPanel2.setBackground(new java.awt.Color(2, 16, 28));

        UserBtn.setText("User");
        UserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserBtnActionPerformed(evt);
            }
        });

        ProfileBtn.setText("Profile");
        ProfileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProfileBtnActionPerformed(evt);
            }
        });

        LibraryBtn.setText("Library");
        LibraryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LibraryBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UserBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ProfileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(LibraryBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UserBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ProfileBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LibraryBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "ID", "Title", "Author", "Category", "Publisher", "ISBN", "Edition", "Language"
            }
        ));
        BookTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BookTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(BookTable);

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(183, 183, 183)));

        jLabel11.setText("Book ID");

        jLabel12.setText("Book Title*");

        jLabel13.setText("Author*");

        jLabel14.setText("Category*");

        jLabel16.setText("Publisher*");

        ImagePath.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        ImagePath.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ImagePath.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel19.setText("Book Cover");

        ChooseImageBtn.setText("Choose Image");
        ChooseImageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChooseImageBtnActionPerformed(evt);
            }
        });

        jLabel20.setText("Language");

        jLabel15.setText("ISBN*");

        jLabel17.setText("Edition");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Edition, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Publisher, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Author, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BookID, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ImagePath, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Language, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(ChooseImageBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(Error)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(BookID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Author, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Publisher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Edition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel19))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(ImagePath, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ChooseImageBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Language, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))))
                .addGap(27, 27, 27)
                .addComponent(Error)
                .addContainerGap())
        );

        AddBookBtn.setText("Add");
        AddBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBookBtnActionPerformed(evt);
            }
        });

        ResetBtn.setText("Reset");
        ResetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetBtnActionPerformed(evt);
            }
        });

        DeleteBookBtn.setText("Delete");
        DeleteBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBookBtnActionPerformed(evt);
            }
        });

        UpdateBookBtn.setText("Update");
        UpdateBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBookBtnActionPerformed(evt);
            }
        });

        jLabel77.setText("Book ID");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(AddBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(UpdateBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ResetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel77)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DelBookID, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DeleteBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddBookBtn)
                    .addComponent(UpdateBookBtn)
                    .addComponent(DeleteBookBtn)
                    .addComponent(ResetBtn)
                    .addComponent(DelBookID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add a Book", jPanel6);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Book Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(1, 1, 1))); // NOI18N

        jLabel26.setText("Edition");

        jLabel21.setText("Title");

        jLabel27.setText("Language");

        jLabel23.setText("Author");

        jLabel24.setText("Category");

        BookInfo.setText("Book Info");
        BookInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookInfoActionPerformed(evt);
            }
        });

        IssueBookCover.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel18.setText("Book ID");

        jLabel25.setText("Publisher");

        jLabel28.setText("ISBN No.");

        jLabel29.setText("Book Cover");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel21)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(IssueISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IssueLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IssueEdition, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IssuePublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IssueCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IssueAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IssueBookTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IssueBookID, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IssueBookCover, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BookInfo)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IssueBookID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(BookInfo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IssueBookTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IssueAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IssueCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IssuePublisher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IssueEdition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IssueLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IssueISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(IssueBookCover, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Borrower Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(1, 1, 1))); // NOI18N

        jLabel30.setText("Address");

        jLabel31.setText("Email");

        jLabel33.setText("Mobile");

        jLabel34.setText("Sex");

        UserInfo.setText("User Info");
        UserInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserInfoActionPerformed(evt);
            }
        });

        jLabel36.setText("User ID");

        jLabel37.setText("Date of Birth");

        IssueAddress.setColumns(20);
        IssueAddress.setRows(5);
        jScrollPane3.setViewportView(IssueAddress);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(jLabel31)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34)
                    .addComponent(jLabel37)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IssueDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IssueSex, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IssueMobile, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IssueEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IssueUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UserInfo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IssueUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(UserInfo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IssueEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IssueMobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IssueSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IssueDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel13.setBorder(null);

        IssueBtn.setText("Issue Book");
        IssueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IssueBtnActionPerformed(evt);
            }
        });

        jLabel32.setText("Date of Issue");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IssueBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DateOfIssue, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ErrorNotification))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DateOfIssue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(IssueBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ErrorNotification, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Issue a Book", jPanel7);

        LoanTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Serial", "Issue ID", "Book ID", "User ID", "Date of Issue"
            }
        ));
        LoanTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoanTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(LoanTable);

        jLabel70.setText("Issue ID");

        jLabel76.setText("Date of Issue");

        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        UP.setMaximumSize(new java.awt.Dimension(300, 2147483647));

        BI.setMaximumSize(new java.awt.Dimension(300, 2147483647));

        jLabel78.setText("Phone");

        UI.setMaximumSize(new java.awt.Dimension(300, 2147483647));

        BT.setMaximumSize(new java.awt.Dimension(300, 2147483647));

        jLabel72.setText("Title");

        BC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        BA.setMaximumSize(new java.awt.Dimension(300, 2147483647));

        UE.setMaximumSize(new java.awt.Dimension(300, 2147483647));

        jLabel71.setText("Book ID");

        jLabel73.setText("User ID");

        jLabel74.setText("Author");

        jLabel75.setText("Email");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel71)
                            .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BI, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                            .addComponent(BT, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                        .addGap(1, 1, 1))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel74)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BA, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel75))
                        .addComponent(jLabel73))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel78)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UI, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BC, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel71)
                            .addComponent(jLabel73)
                            .addComponent(UI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel72)
                            .addComponent(jLabel75)
                            .addComponent(UE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel74)
                            .addComponent(UP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel78)))
                    .addComponent(BC, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(II, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel76)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))
                        .addContainerGap())))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel70)
                            .addComponent(II, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel76)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Statistics", jPanel8);

        javax.swing.GroupLayout LibraryPanelLayout = new javax.swing.GroupLayout(LibraryPanel);
        LibraryPanel.setLayout(LibraryPanelLayout);
        LibraryPanelLayout.setHorizontalGroup(
            LibraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        LibraryPanelLayout.setVerticalGroup(
            LibraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        UserPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(46, 144, 247))); // NOI18N

        UserTable.setAutoCreateRowSorter(true);
        UserTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Serial", "User ID", "Role", "Email", "Mobile", "Sex", "DoB", "Address"
            }
        ));
        UserTable.setGridColor(new java.awt.Color(140, 218, 254));
        UserTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(UserTable);

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel66.setText("Mobile");

        jLabel68.setText("Date of Birth");

        buttonGroup2.add(Male);
        Male.setText("Male");

        buttonGroup2.add(Female);
        Female.setText("Female");

        UserAddress.setColumns(20);
        UserAddress.setRows(5);
        jScrollPane5.setViewportView(UserAddress);

        jLabel67.setText("Sex");

        jLabel64.setText("Role");

        jLabel65.setText("Email");

        jLabel63.setText("User ID");

        jLabel69.setText("Address");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel63)
                    .addComponent(jLabel64)
                    .addComponent(jLabel65)
                    .addComponent(jLabel66)
                    .addComponent(jLabel67)
                    .addComponent(jLabel68)
                    .addComponent(jLabel69))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UserEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5)
                    .addComponent(UserRole, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Male)
                    .addComponent(Female)
                    .addComponent(UserID, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserMobile, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(UserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserMobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Male)
                    .addComponent(jLabel67))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Female)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UserDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69))
                .addContainerGap())
        );

        jButton2.setText("Update");

        UserResetBtn.setText("Reset");
        UserResetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserResetBtnActionPerformed(evt);
            }
        });

        DeleteUserBtn.setText("Delete");
        DeleteUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteUserBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UserPanelLayout = new javax.swing.GroupLayout(UserPanel);
        UserPanel.setLayout(UserPanelLayout);
        UserPanelLayout.setHorizontalGroup(
            UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                    .addGroup(UserPanelLayout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(UserResetBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DeleteUserBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        UserPanelLayout.setVerticalGroup(
            UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UserPanelLayout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15))
                    .addGroup(UserPanelLayout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UserResetBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DeleteUserBtn)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        ProfilePanel.setBorder(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Change Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(0, 112, 255))); // NOI18N

        jButton1.setText("Update Password");

        jLabel9.setText("Confirm Password");

        jLabel7.setText("Current Password");

        jLabel8.setText("New Password");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPasswordField3, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jPasswordField2)
                            .addComponent(jPasswordField1))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "My Profile", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jLabel1.setText("User ID");

        jLabel10.setText("Role");

        jLabel6.setText("Address");

        jLabel5.setText("Sex");

        jLabel2.setText("Email");

        jLabel3.setText("Mobile");

        jLabel4.setText("Date of Birth");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(ProRole))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ProEmail)
                                    .addComponent(ProMobile)
                                    .addComponent(ProDOB)
                                    .addComponent(ProSex)
                                    .addComponent(ProAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(ProID, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProMobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton4.setText("Delete");

        EditProfile.setText("Edit");
        EditProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditProfileActionPerformed(evt);
            }
        });

        jButton3.setText("Update");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(EditProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EditProfile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );

        javax.swing.GroupLayout ProfilePanelLayout = new javax.swing.GroupLayout(ProfilePanel);
        ProfilePanel.setLayout(ProfilePanelLayout);
        ProfilePanelLayout.setHorizontalGroup(
            ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProfilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ProfilePanelLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ProfilePanelLayout.setVerticalGroup(
            ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProfilePanelLayout.createSequentialGroup()
                .addGroup(ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ProfilePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ProfilePanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        WhoAmIPanel.setBackground(new java.awt.Color(1, 1, 1));

        jPanel14.setBackground(new java.awt.Color(1, 1, 1));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Who Am I ?", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18), new java.awt.Color(255, 139, 0))); // NOI18N

        jLabel48.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(107, 234, 251));
        jLabel48.setText("Music");

        jLabel40.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(250, 165, 25));
        jLabel40.setText("anissarker.iub@gmail.com");

        jLabel59.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(96, 165, 253));
        jLabel59.setText("Philosophy");

        jLabel50.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(107, 234, 251));
        jLabel50.setText("Wandering");

        jLabel41.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(164, 164, 164));
        jLabel41.setText("github.com/ansarker");

        jLabel43.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(11, 188, 1));
        jLabel43.setText("Youtube:");

        jLabel22.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 66, 0));
        jLabel22.setText("Anis Sarker");

        jLabel55.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(11, 188, 1));
        jLabel55.setText("Interests:");

        jLabel51.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(11, 188, 1));
        jLabel51.setText("Education:");

        jLabel46.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(96, 165, 253));
        jLabel46.setText("Programming");

        jLabel57.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(96, 165, 253));
        jLabel57.setText("Psycho Matrix");

        jLabel39.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(250, 165, 25));
        jLabel39.setText("shahidsarker95@hotmail.com");

        jLabel47.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(107, 234, 251));
        jLabel47.setText("Travelling");

        jLabel52.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(210, 95, 247));
        jLabel52.setText("Computer Science");

        jLabel45.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(11, 188, 1));
        jLabel45.setText("Hobbies:");

        jLabel53.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(210, 95, 247));
        jLabel53.setText("Independent University Bangladesh, (IUB)");

        jLabel54.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(96, 165, 253));
        jLabel54.setText("Artifical Intelligence");

        jLabel58.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(96, 165, 253));
        jLabel58.setText("Psychology");

        jLabel42.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(11, 188, 1));
        jLabel42.setText("Github:");

        jLabel56.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(96, 165, 253));
        jLabel56.setText("Cognition");

        jLabel38.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(11, 188, 1));
        jLabel38.setText("Name:");

        jLabel44.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 0, 0));
        jLabel44.setText("UrontoShahid");

        jLabel49.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(107, 234, 251));
        jLabel49.setText("Painting");

        jLabel35.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(11, 188, 1));
        jLabel35.setText("Email:");

        WhoAmIPhotoLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(213, 1, 1), null));

        jLabel60.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 53, 0));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("Who Am I");

        jLabel61.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(54, 136, 255));
        jLabel61.setText("ansarker.github.io");

        jLabel62.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(11, 188, 1));
        jLabel62.setText("Web:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addComponent(jLabel51)
                            .addComponent(jLabel42)
                            .addComponent(jLabel35)
                            .addComponent(jLabel38))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39)
                            .addComponent(jLabel41)
                            .addComponent(jLabel52)
                            .addComponent(jLabel44)
                            .addComponent(jLabel53)
                            .addComponent(jLabel40)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel55)
                            .addComponent(jLabel45)
                            .addComponent(jLabel62))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56)
                            .addComponent(jLabel57)
                            .addComponent(jLabel59)
                            .addComponent(jLabel46)
                            .addComponent(jLabel47)
                            .addComponent(jLabel48)
                            .addComponent(jLabel49)
                            .addComponent(jLabel50)
                            .addComponent(jLabel58)
                            .addComponent(jLabel54)
                            .addComponent(jLabel61))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(WhoAmIPhotoLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(WhoAmIPhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel60))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(jLabel39))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel40)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(jLabel42))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(jLabel44))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(jLabel52))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(jLabel55))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(jLabel45))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel50)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jLabel62))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout WhoAmIPanelLayout = new javax.swing.GroupLayout(WhoAmIPanel);
        WhoAmIPanel.setLayout(WhoAmIPanelLayout);
        WhoAmIPanelLayout.setHorizontalGroup(
            WhoAmIPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WhoAmIPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        WhoAmIPanelLayout.setVerticalGroup(
            WhoAmIPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WhoAmIPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLayeredPane1.setLayer(LibraryPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(UserPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(ProfilePanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(WhoAmIPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(UserPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ProfilePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(LibraryPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(WhoAmIPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(UserPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ProfilePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(LibraryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(WhoAmIPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLayeredPane1)
        );

        jMenu1.setText("File");

        Logout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        Logout.setText("Logout");
        Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutActionPerformed(evt);
            }
        });
        jMenu1.add(Logout);

        ExitMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        ExitMenu.setText("Exit");
        ExitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitMenuActionPerformed(evt);
            }
        });
        jMenu1.add(ExitMenu);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        WhoAmI.setText("Who Am I ?");
        WhoAmI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WhoAmIActionPerformed(evt);
            }
        });
        jMenu2.add(WhoAmI);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitMenuActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitMenuActionPerformed

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
        Login login = new Login();
        login.show();
        this.hide();
    }//GEN-LAST:event_LogoutActionPerformed

    private void UserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserBtnActionPerformed
        UserPanel.setVisible(true);
        ProfilePanel.setVisible(false);
        LibraryPanel.setVisible(false);
        WhoAmIPanel.setVisible(false);
    }//GEN-LAST:event_UserBtnActionPerformed

    private void ProfileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfileBtnActionPerformed
        UserPanel.setVisible(false);
        ProfilePanel.setVisible(true);
        LibraryPanel.setVisible(false);
        WhoAmIPanel.setVisible(false);
    }//GEN-LAST:event_ProfileBtnActionPerformed

    private void EditProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditProfileActionPerformed
        disable_edit(true);
    }//GEN-LAST:event_EditProfileActionPerformed

    private void LibraryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LibraryBtnActionPerformed
        UserPanel.setVisible(false);
        ProfilePanel.setVisible(false);
        LibraryPanel.setVisible(true);
        WhoAmIPanel.setVisible(false);
    }//GEN-LAST:event_LibraryBtnActionPerformed

    private void AddBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBookBtnActionPerformed
        String title = null,
                author = null,
                category = null,
                publisher = null,
                isbn = null,
                edition,
                image_path,
                language;

        if (Title.getText().equals("")) {
            Error.setForeground(Color.red);
            Error.setText("Book title required.");
        } else {
            Error.setForeground(Color.green);
            title = Title.getText();
        }

        if (Author.getText().equals("")) {
            Error.setForeground(Color.red);
            Error.setText("Book author required.");
        } else {
            Error.setForeground(Color.green);
            author = Author.getText();
        }

        if (Category.getText().equals("")) {
            Error.setForeground(Color.red);
            Error.setText("Book category required.");
        } else {
            Error.setForeground(Color.green);
            category = Category.getText();
        }

        if (Publisher.getText().equals("")) {
            Error.setForeground(Color.red);
            Error.setText("Book publisher required.");
        } else {
            Error.setForeground(Color.green);
            publisher = Publisher.getText();
        }

        if (ISBN.getText().equals("")) {
            Error.setForeground(Color.red);
            Error.setText("ISBN no. required.");
        } else {
            Error.setForeground(Color.green);
            isbn = ISBN.getText();
        }

        edition = Edition.getText();
        image_path = img_path;
        language = Language.getText();

//        System.out.println(Arrays.toString(data));
        if (Title.getText().equals("") || Author.getText().equals("") || Category.getText().equals("") || Publisher.getText().equals("") || ISBN.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Book has not added! Please check required!");
        } else {
            String sql = "INSERT INTO Book(title, author, category, publisher, ISBN, edition, cover_path, language) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            String[] data = {title, author, category, publisher, isbn, edition, image_path, language};
            dbc.create(sql, data);
            
            DefaultTableModel bookTable = (DefaultTableModel) BookTable.getModel();
            bookTable.setRowCount(0);
            show_book();

            JOptionPane.showMessageDialog(rootPane, "Book has added successfully!");
        }
    }//GEN-LAST:event_AddBookBtnActionPerformed

    private void ChooseImageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChooseImageBtnActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & JPEG & PNG Images", "jpg", "jpeg", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(LibraryPanel);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            ImagePath.setIcon(ResizedImage(ImagePath, path));
            img_path = path;
            System.out.println("You chose to open this file: "
                    + chooser.getSelectedFile().getName() + "\nPath: " + path);
        }
    }//GEN-LAST:event_ChooseImageBtnActionPerformed

    private void ResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetBtnActionPerformed
        BookID.setText("");
        DelBookID.setText("");
        Title.setText("");
        Author.setText("");
        Category.setText("");
        Publisher.setText("");
        ISBN.setText("");
        Edition.setText("");
        ImagePath.setIcon(null);
        Language.setText("");
    }//GEN-LAST:event_ResetBtnActionPerformed

    private void BookInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookInfoActionPerformed
        if (IssueBookID.getText().equals("")) {
            ErrorNotification.setForeground(Color.red);
            ErrorNotification.setText("Book ID can't be empty!");
        } else {
            int issuebook_id = Integer.parseInt(IssueBookID.getText());
            try {
                String query = "SELECT * FROM Book WHERE book_id='" + issuebook_id + "'";
                conn = dbc.connect();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    ErrorNotification.setText("");
//                    JOptionPane.showMessageDialog(rootPane, rs.getString(2) + "\n" + rs.getString(5));
                    
                    IssueBookTitle.setText(rs.getString(2));
                    IssueAuthor.setText(rs.getString(3));
                    IssueCategory.setText(rs.getString(4));
                    IssuePublisher.setText(rs.getString(5));
                    IssueISBN.setText(rs.getString(6));
                    IssueEdition.setText(rs.getString(7));
                    IssueBookCover.setIcon(ResizedImage(IssueBookCover, rs.getString(8)));
                    IssueLanguage.setText(rs.getString(9));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_BookInfoActionPerformed
    
    private void issue_book_info_editability(boolean flag){
        IssueBookTitle.setEditable(flag);
        IssueAuthor.setEditable(flag);
        IssueCategory.setEditable(flag);
        IssuePublisher.setEditable(flag);
        IssueISBN.setEditable(flag);
        IssueEdition.setEditable(flag);
        IssueLanguage.setEditable(flag);
    }
    
    private void issue_user_info_editability(boolean flag){
        IssueEmail.setEditable(flag);
        IssueMobile.setEditable(flag);
        IssueSex.setEditable(flag);
        IssueDateOfBirth.setEditable(flag);
        IssueAddress.setEditable(flag);
    }
    
    private void UserInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserInfoActionPerformed
        if (IssueUserID.getText().equals("")) {
            ErrorNotification.setForeground(Color.red);
            ErrorNotification.setText("User ID can't be empty!");
        } else {
            try {
                String query = "SELECT * FROM User WHERE user_id=" + IssueUserID.getText();
                conn = dbc.connect();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    ErrorNotification.setText("");
                    
                    IssueEmail.setText(rs.getString(4));
                    IssueMobile.setText(rs.getString(5));
                    IssueSex.setText(rs.getString(6));
                    IssueDateOfBirth.setText(rs.getString(7));
                    IssueAddress.setText(rs.getString(8));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_UserInfoActionPerformed

    private void IssueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IssueBtnActionPerformed
        Date date = DateOfIssue.getDate();
        DateFormat date_format = new SimpleDateFormat("MMM d, yyyy");
        String issuedate = date_format.format(date);
        String bookid = IssueBookID.getText();
        String userid = IssueUserID.getText();
        
//        JOptionPane.showMessageDialog(rootPane, issuedate);
//        ErrorNotification.setForeground(Color.green);
//        ErrorNotification.setText(issuedate);
        
        if (IssueBookID.getText().equals("") || IssueUserID.getText().equals("") || DateOfIssue.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please select a book.\nor\nInsert your User ID.");
        } else {
            String sql = "INSERT INTO Loan(book_id, user_id, issue_date) VALUES(?, ?, ?)";
            String[] data = {bookid, userid, issuedate};
            dbc.create(sql, data);
            
            DefaultTableModel loanTable = (DefaultTableModel) LoanTable.getModel();
            loanTable.setRowCount(0);
            show_loan();

            JOptionPane.showMessageDialog(rootPane, "Book has issued successfully to ID: " + userid);
        }
    }//GEN-LAST:event_IssueBtnActionPerformed

    private void WhoAmIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WhoAmIActionPerformed
        UserPanel.setVisible(false);
        ProfilePanel.setVisible(false);
        LibraryPanel.setVisible(false);
        WhoAmIPanel.setVisible(true);
    }//GEN-LAST:event_WhoAmIActionPerformed

    private void UserTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserTableMouseClicked
        int row = UserTable.getSelectedRow();
        TableModel model = UserTable.getModel();
        
        UserID.setText(model.getValueAt(row, 1).toString());
        UserRole.setText(model.getValueAt(row, 2).toString());
        UserEmail.setText(model.getValueAt(row, 3).toString());
        UserMobile.setText(model.getValueAt(row, 4).toString());
        String sex = model.getValueAt(row, 5).toString();
        if (sex.equals("Male")) {
            Male.setSelected(true);
            Female.setSelected(false);
        } else {
            Male.setSelected(false);
            Female.setSelected(true);
        }
        
        try {
            String date = model.getValueAt(row, 6).toString();
            Date date_format = new SimpleDateFormat("dd/MMM/yyyy").parse(date);
            UserDateOfBirth.setDate(date_format);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        
        UserAddress.setText(model.getValueAt(row, 7).toString());
    }//GEN-LAST:event_UserTableMouseClicked

    private void UserResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserResetBtnActionPerformed
        UserID.setText("");
        UserRole.setText("");
        UserEmail.setText("");
        UserMobile.setText("");
        Male.setSelected(true);
        Female.setSelected(true);        
        UserAddress.setText("");
    }//GEN-LAST:event_UserResetBtnActionPerformed

    private void LoanTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoanTableMouseClicked
        int selectedRow = LoanTable.getSelectedRow();
        TableModel model = LoanTable.getModel();
        
//        to merge 3 tables from database
//        String query = "SELECT * FROM LOAN l INNER JOIN Book b on l.book_id=b.book_id INNER JOIN User u on l.user_id=u.user_id;";
        
        String query = "SELECT * FROM LOAN l INNER JOIN Book b on l.book_id=b.book_id INNER JOIN User u on l.user_id=u.user_id WHERE l.book_id=" + model.getValueAt(selectedRow, 2) +" AND l.user_id=" + model.getValueAt(selectedRow, 3);
           
        try {
            conn = dbc.connect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            
            while(rs.next()) {
                II.setText(rs.getString("issue_id"));
                
                try {
                    ID.setDate(new SimpleDateFormat("MMM d, yyyy").parse(rs.getString("issue_date")));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                
                BI.setText(Integer.toString(rs.getInt("book_id")));
                BT.setText(rs.getString("title"));
                BA.setText(rs.getString("author"));
                BC.setIcon(ResizedImage(BC, rs.getString("cover_path")));
                
                UI.setText(rs.getString("user_id"));
                UE.setText(rs.getString("email"));
                UP.setText(rs.getString("mobile"));
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_LoanTableMouseClicked

    private void DeleteUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteUserBtnActionPerformed
        
        String query = "DELETE FROM User WHERE user_id = " + UserID.getText() ;
        try {
            conn = dbc.connect();
            pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "User removed!");
            conn.close();
            
            DefaultTableModel userTable = (DefaultTableModel) UserTable.getModel();
            userTable.setRowCount(0);
            show_user();
            
            UserID.setText("");
            UserRole.setText("");
            UserEmail.setText("");
            UserMobile.setText("");
            Male.setSelected(true);
            Female.setSelected(true);        
            UserAddress.setText("");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }//GEN-LAST:event_DeleteUserBtnActionPerformed

    private void DeleteBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBookBtnActionPerformed
        String query = "DELETE FROM Book WHERE book_id = " + DelBookID.getText();
        
        if(DelBookID.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please enter a valid Book ID");
        } else {
            try {
                int confirm = JOptionPane.showConfirmDialog(rootPane, "Delete book?");
                if (confirm == JOptionPane.YES_OPTION) {
                    conn  = dbc.connect();
                    pstmt = conn.prepareStatement(query);
                    pstmt.executeUpdate();
                                        
                    DefaultTableModel bookTable = (DefaultTableModel) BookTable.getModel();
                    bookTable.setRowCount(0);
                    show_book();

                    DelBookID.setText("");
                    BookID.setText("");
                    Title.setText("");
                    Author.setText("");
                    Category.setText("");
                    Publisher.setText("");
                    ISBN.setText("");
                    Edition.setText("");
                    Language.setText("");
                    ImagePath.setIcon(null);
                            
                    JOptionPane.showMessageDialog(rootPane, "Book has deleted!");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Book has not deleted!");
                }
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_DeleteBookBtnActionPerformed

    private void BookTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BookTableMouseClicked
        int row = BookTable.getSelectedRow();
        TableModel model = BookTable.getModel();
        
        DelBookID.setText(model.getValueAt(row, 1).toString());
        BookID.setText(model.getValueAt(row, 1).toString());
        Title.setText(model.getValueAt(row, 2).toString());
        Author.setText(model.getValueAt(row, 3).toString());
        Category.setText(model.getValueAt(row, 4).toString());
        Publisher.setText(model.getValueAt(row, 5).toString());
        ISBN.setText(model.getValueAt(row, 6).toString());
        Edition.setText(model.getValueAt(row, 7).toString());
        Language.setText(model.getValueAt(row, 8).toString());
        
        String query = "SELECT * FROM Book WHERE book_id = " + BookID.getText();
        try {            
            conn = dbc.connect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            
            while(rs.next()) {
                ImagePath.setIcon(ResizedImage(ImagePath, rs.getString("cover_path")));
            }
            conn.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }//GEN-LAST:event_BookTableMouseClicked

    private void UpdateBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBookBtnActionPerformed
        String query = "UPDATE Book SET " +
                   "title = '" + Title.getText() + "', author = '" + Author.getText() + "', " +
                   "category = '" + Category.getText() + "', publisher = '" + Publisher.getText() + "', " + 
                   "ISBN = '" + ISBN.getText() + "', edition = '" + Edition.getText() + "', " + 
                   "language = '" + Language.getText() +
                   "' WHERE book_id = '" + BookID.getText() + "'";
        update_book(query);        
    }//GEN-LAST:event_UpdateBookBtnActionPerformed

    private void update_book(String query) {
        try {
            conn = dbc.connect();
            pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(rootPane, "Book information updated successfully!");

            DefaultTableModel bookTable = (DefaultTableModel) BookTable.getModel();
            bookTable.setRowCount(0);
            show_book();
            
            conn.close();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBookBtn;
    private javax.swing.JTextField Author;
    private javax.swing.JTextField BA;
    private javax.swing.JLabel BC;
    private javax.swing.JTextField BI;
    private javax.swing.JTextField BT;
    private javax.swing.JTextField BookID;
    private javax.swing.JButton BookInfo;
    private javax.swing.JTable BookTable;
    private javax.swing.JTextField Category;
    private javax.swing.JButton ChooseImageBtn;
    private com.toedter.calendar.JDateChooser DateOfIssue;
    private javax.swing.JTextField DelBookID;
    private javax.swing.JButton DeleteBookBtn;
    private javax.swing.JButton DeleteUserBtn;
    private javax.swing.JButton EditProfile;
    private javax.swing.JTextField Edition;
    private javax.swing.JLabel Error;
    private javax.swing.JLabel ErrorNotification;
    private javax.swing.JMenuItem ExitMenu;
    private javax.swing.JRadioButton Female;
    private com.toedter.calendar.JDateChooser ID;
    private javax.swing.JTextField II;
    private javax.swing.JTextField ISBN;
    private javax.swing.JLabel ImagePath;
    private javax.swing.JTextArea IssueAddress;
    private javax.swing.JTextField IssueAuthor;
    private javax.swing.JLabel IssueBookCover;
    private javax.swing.JTextField IssueBookID;
    private javax.swing.JTextField IssueBookTitle;
    private javax.swing.JButton IssueBtn;
    private javax.swing.JTextField IssueCategory;
    private javax.swing.JTextField IssueDateOfBirth;
    private javax.swing.JTextField IssueEdition;
    private javax.swing.JTextField IssueEmail;
    private javax.swing.JTextField IssueISBN;
    private javax.swing.JTextField IssueLanguage;
    private javax.swing.JTextField IssueMobile;
    private javax.swing.JTextField IssuePublisher;
    private javax.swing.JTextField IssueSex;
    private javax.swing.JTextField IssueUserID;
    private javax.swing.JTextField Language;
    private javax.swing.JButton LibraryBtn;
    private javax.swing.JPanel LibraryPanel;
    private javax.swing.JTable LoanTable;
    private javax.swing.JMenuItem Logout;
    private javax.swing.JRadioButton Male;
    private javax.swing.JTextField ProAddress;
    private javax.swing.JTextField ProDOB;
    private javax.swing.JTextField ProEmail;
    private javax.swing.JTextField ProID;
    private javax.swing.JTextField ProMobile;
    private javax.swing.JTextField ProRole;
    private javax.swing.JTextField ProSex;
    private javax.swing.JButton ProfileBtn;
    private javax.swing.JPanel ProfilePanel;
    private javax.swing.JTextField Publisher;
    private javax.swing.JButton ResetBtn;
    private javax.swing.JTextField Title;
    private javax.swing.JTextField UE;
    private javax.swing.JTextField UI;
    private javax.swing.JTextField UP;
    private javax.swing.JButton UpdateBookBtn;
    private javax.swing.JTextArea UserAddress;
    private javax.swing.JButton UserBtn;
    private com.toedter.calendar.JDateChooser UserDateOfBirth;
    private javax.swing.JTextField UserEmail;
    private javax.swing.JTextField UserID;
    private javax.swing.JButton UserInfo;
    private javax.swing.JTextField UserMobile;
    private javax.swing.JPanel UserPanel;
    private javax.swing.JButton UserResetBtn;
    private javax.swing.JTextField UserRole;
    private javax.swing.JTable UserTable;
    private javax.swing.JMenuItem WhoAmI;
    private javax.swing.JPanel WhoAmIPanel;
    private javax.swing.JLabel WhoAmIPhotoLabel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

    private ImageIcon ResizedImage(JLabel LabelName, String path) {
        ImageIcon myImage = new ImageIcon(path);
        Image img = myImage.getImage();
        Image newImage = img.getScaledInstance(LabelName.getWidth(), LabelName.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);

        return image;
    }
}

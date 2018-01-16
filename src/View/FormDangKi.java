package View;

import Model.ModTaiKhoan;
import Object.ObjTaiKhoan;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ThaiNguyen
 */
public class FormDangKi extends javax.swing.JFrame {

    /**
     * Creates new form RegisterForm
     */
    int flag=0;
    ObjTaiKhoan ObjTK = new ObjTaiKhoan();
    ObjTaiKhoan ObjTK1 = new ObjTaiKhoan();
    ModTaiKhoan ModTK = new ModTaiKhoan();
    public FormDangKi(ObjTaiKhoan TK) {
        initComponents();
        setLocationRelativeTo(null);
        jPanel1.setBackground(new Color(236,236,236,50));
        jPanel3.setBackground(new Color(0,0,0,80));
        
        jtxtHoten.setBackground(new Color(236,236,236,0));
        jtxtTenDangNhap.setBackground(new Color(236,236,236,0));
        txtPassword.setBackground(new Color(236,236,236,0));
        txtPassword.setEchoChar('\u0000');
        txtRePassword.setBackground(new Color(236,236,236,0));
        txtRePassword.setEchoChar('\u0000');
        jBtnTaoTK.setBackground(new Color(236,236,236,150));
        ObjTK1.setMatKhau(TK.getMatKhau());
        ObjTK1.setTenDangNhap(TK.getTenDangNhap());
    }
    public FormDangKi() {
        initComponents();
        setLocationRelativeTo(null);
        jPanel1.setBackground(new Color(236,236,236,50));
        jPanel3.setBackground(new Color(0,0,0,80));
        
        jtxtHoten.setBackground(new Color(236,236,236,0));
        jtxtTenDangNhap.setBackground(new Color(236,236,236,0));
        txtPassword.setBackground(new Color(236,236,236,0));
        txtPassword.setEchoChar('\u0000');
        txtRePassword.setBackground(new Color(236,236,236,0));
        txtRePassword.setEchoChar('\u0000');
        jBtnTaoTK.setBackground(new Color(236,236,236,150));
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
        jBtnBack = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jBtnTaoTK = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jtxtHoten = new javax.swing.JTextField();
        jtxtTenDangNhap = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        txtRePassword = new javax.swing.JPasswordField();
        jRbtnQLKT = new javax.swing.JRadioButton();
        jRbtnQuanLi = new javax.swing.JRadioButton();
        jRbtnQLBH = new javax.swing.JRadioButton();
        jRbtnQLK = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnBackMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnBackMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBtnBackMouseReleased(evt);
            }
        });

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Left_40px.png"))); // NOI18N

        javax.swing.GroupLayout jBtnBackLayout = new javax.swing.GroupLayout(jBtnBack);
        jBtnBack.setLayout(jBtnBackLayout);
        jBtnBackLayout.setHorizontalGroup(
            jBtnBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnBackLayout.createSequentialGroup()
                .addComponent(jLabel21)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jBtnBackLayout.setVerticalGroup(
            jBtnBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getContentPane().add(jBtnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jBtnTaoTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnTaoTKMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnTaoTKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnTaoTKMouseExited(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Tạo tài khoản");

        javax.swing.GroupLayout jBtnTaoTKLayout = new javax.swing.GroupLayout(jBtnTaoTK);
        jBtnTaoTK.setLayout(jBtnTaoTKLayout);
        jBtnTaoTKLayout.setHorizontalGroup(
            jBtnTaoTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        jBtnTaoTKLayout.setVerticalGroup(
            jBtnTaoTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnTaoTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 500, 110, 30));

        jtxtHoten.setBackground(new java.awt.Color(204, 204, 204));
        jtxtHoten.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jtxtHoten.setForeground(new java.awt.Color(255, 255, 255));
        jtxtHoten.setText("Nhập họ tên...");
        jtxtHoten.setBorder(null);
        jtxtHoten.setFocusable(false);
        jtxtHoten.setOpaque(false);
        jtxtHoten.setPreferredSize(new java.awt.Dimension(73, 17));
        jtxtHoten.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtxtHotenMousePressed(evt);
            }
        });
        getContentPane().add(jtxtHoten, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, 320, 30));

        jtxtTenDangNhap.setBackground(new java.awt.Color(204, 204, 204));
        jtxtTenDangNhap.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jtxtTenDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        jtxtTenDangNhap.setText("Nhập tên đăng nhập...");
        jtxtTenDangNhap.setBorder(null);
        jtxtTenDangNhap.setFocusable(false);
        jtxtTenDangNhap.setOpaque(false);
        jtxtTenDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtxtTenDangNhapMousePressed(evt);
            }
        });
        getContentPane().add(jtxtTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, 320, 30));

        txtPassword.setBackground(new java.awt.Color(204, 204, 204));
        txtPassword.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setText("Nhập mật khẩu...");
        txtPassword.setBorder(null);
        txtPassword.setEchoChar('\u0000');
        txtPassword.setFocusable(false);
        txtPassword.setOpaque(false);
        txtPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPasswordMousePressed(evt);
            }
        });
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, 320, 30));

        txtRePassword.setBackground(new java.awt.Color(204, 204, 204));
        txtRePassword.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtRePassword.setForeground(new java.awt.Color(255, 255, 255));
        txtRePassword.setText("Xác nhận mật khẩu");
        txtRePassword.setBorder(null);
        txtRePassword.setEchoChar('\u0000');
        txtRePassword.setFocusable(false);
        txtRePassword.setOpaque(false);
        txtRePassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtRePasswordMousePressed(evt);
            }
        });
        getContentPane().add(txtRePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 400, 320, 30));

        jRbtnQLKT.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jRbtnQLKT);
        jRbtnQLKT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRbtnQLKT.setForeground(new java.awt.Color(255, 255, 255));
        jRbtnQLKT.setText("Quản lý kế toán");
        jRbtnQLKT.setFocusable(false);
        jRbtnQLKT.setOpaque(false);
        getContentPane().add(jRbtnQLKT, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 450, 120, -1));

        jRbtnQuanLi.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jRbtnQuanLi);
        jRbtnQuanLi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRbtnQuanLi.setForeground(new java.awt.Color(255, 255, 255));
        jRbtnQuanLi.setText("Quản lý");
        jRbtnQuanLi.setFocusable(false);
        jRbtnQuanLi.setOpaque(false);
        getContentPane().add(jRbtnQuanLi, new org.netbeans.lib.awtextra.AbsoluteConstraints(375, 450, -1, -1));

        jRbtnQLBH.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jRbtnQLBH);
        jRbtnQLBH.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRbtnQLBH.setForeground(new java.awt.Color(255, 255, 255));
        jRbtnQLBH.setText("Quản lý bán hàng");
        jRbtnQLBH.setFocusable(false);
        jRbtnQLBH.setOpaque(false);
        getContentPane().add(jRbtnQLBH, new org.netbeans.lib.awtextra.AbsoluteConstraints(451, 450, -1, -1));

        jRbtnQLK.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jRbtnQLK);
        jRbtnQLK.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRbtnQLK.setForeground(new java.awt.Color(255, 255, 255));
        jRbtnQLK.setText("Quản lý kho");
        jRbtnQLK.setFocusable(false);
        jRbtnQLK.setOpaque(false);
        getContentPane().add(jRbtnQLK, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 450, 100, -1));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/navigation.png"))); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 70, -1));

        jLabel14.setFont(new java.awt.Font("Chiller", 1, 36)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Get Managed");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, -1, 30));

        jLabel15.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Support Management");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, -1, -1));

        jLabel19.setFont(new java.awt.Font("Baskerville Old Face", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Design, Create By Closer Group");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 600, -1, 30));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Sign Up Here");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 230, -1));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Admin  Sign Up Panel");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 340, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 340, -1));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 340, -1));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 340, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Name_48px.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 50, 50));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_User_48px_1.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 50, 50));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Password_48px.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 50, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Staff_50px_1.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 60, 40));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Password_48px.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 50, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 580, 410));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setFocusable(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 640));

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/pexels-photo-374074.jpeg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

     public void setColor(JPanel pn){
        pn.setSize(pn.getWidth()+1, pn.getHeight()+1);
        pn.setBackground(new Color(60,209,127,50));
    }
    public void resetColor(JPanel pn){
        pn.setSize(pn.getWidth()-1, pn.getHeight()-1);
        pn.setBackground(new Color(236,236,236,150));
    }
    
    private void jBtnTaoTKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnTaoTKMouseEntered
        // TODO add your handling code here:
        setColor(jBtnTaoTK);
    }//GEN-LAST:event_jBtnTaoTKMouseEntered

    private void jBtnTaoTKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnTaoTKMouseExited
        // TODO add your handling code here:
        resetColor(jBtnTaoTK);
    }//GEN-LAST:event_jBtnTaoTKMouseExited

    private void jtxtHotenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtHotenMousePressed
        // TODO add your handling code here:
        if(!jtxtHoten.isFocusable()){
        jtxtHoten.setFocusable(true);
        jtxtHoten.setText("");
        jtxtHoten.requestFocus();
        }
    }//GEN-LAST:event_jtxtHotenMousePressed

    private void jtxtTenDangNhapMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtTenDangNhapMousePressed
        // TODO add your handling code here:
        if(!jtxtTenDangNhap.isFocusable()){
        jtxtTenDangNhap.setFocusable(true);
        jtxtTenDangNhap.setText("");
        jtxtTenDangNhap.requestFocus();
        }
    }//GEN-LAST:event_jtxtTenDangNhapMousePressed

    private void txtPasswordMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPasswordMousePressed
        // TODO add your handling code here:
        if(!txtPassword.isFocusable()){
        txtPassword.setFocusable(true);
        txtPassword.setText("");
        txtPassword.requestFocus();
        txtPassword.setEchoChar('\u2022');}
    }//GEN-LAST:event_txtPasswordMousePressed
    
    int xx,yy;
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xx,y-yy);
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        xx=evt.getX();
        yy=evt.getY();

    }//GEN-LAST:event_formMousePressed

    private void txtRePasswordMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRePasswordMousePressed
        // TODO add your handling code here:
        if(!txtRePassword.isFocusable()){
        txtRePassword.setFocusable(true);
        txtRePassword.setText("");
        txtRePassword.requestFocus();
        txtRePassword.setEchoChar('\u2022');}
    }//GEN-LAST:event_txtRePasswordMousePressed

    private void jBtnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnBackMouseClicked
        // TODO add your handling code here:
        if(flag==1) new FormQuanLiUser().setVisible(true);
        else new MainForm(ObjTK1).setVisible(true);
        this.setVisible(false);
        this.dispose();    
    }//GEN-LAST:event_jBtnBackMouseClicked

    private void jBtnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnBackMouseEntered
        // TODO add your handling code here:
        setColor(jBtnBack);
    }//GEN-LAST:event_jBtnBackMouseEntered

    private void jBtnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnBackMouseExited
        // TODO add your handling code here:
        resetColor(jBtnBack);
    }//GEN-LAST:event_jBtnBackMouseExited

    private void jBtnBackMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnBackMousePressed
        // TODO add your handling code here:
        resetColor(jBtnBack);
    }//GEN-LAST:event_jBtnBackMousePressed

    private void jBtnBackMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnBackMouseReleased
        // TODO add your handling code here:
        setColor(jBtnBack);
    }//GEN-LAST:event_jBtnBackMouseReleased

    private void jBtnTaoTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnTaoTKMouseClicked
        // TODO add your handling code here:
        String phanloai = null;
        if(jRbtnQuanLi.isSelected())
        {
            phanloai=jRbtnQuanLi.getText();
        }else if(jRbtnQLBH.isSelected())
        {
            phanloai=jRbtnQLBH.getText();
        }else if(jRbtnQLK.isSelected())
        {
            phanloai=jRbtnQLK.getText();
        }else if(jRbtnQLKT.isSelected())
        {
            phanloai=jRbtnQLKT.getText();
        }
        if(phanloai!=null)
        {
            ObjTK = new ObjTaiKhoan(jtxtTenDangNhap.getText(), txtPassword.getText(), jtxtHoten.getText(), phanloai);
        }else JOptionPane.showMessageDialog(this, "Vui lòng chọn phân loại", "Thông báo", JOptionPane.ERROR_MESSAGE);
        if(!jtxtHoten.getText().equals("")&&!jtxtTenDangNhap.getText().equals("")&&!txtPassword.getText().equals("")&&!txtRePassword.getText().equals(""))
        {
            if(jtxtHoten.getText().equals("Nhập họ tên...")||jtxtTenDangNhap.getText().equals("Nhập tên đăng nhập...")||txtPassword.getText().equals("Nhập mật khẩu...")||txtRePassword.getText().equals("Xác nhận mật khẩu..."))
            {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }else{
                if(txtPassword.getText().equals(txtRePassword.getText()))
                {
                    if(ModTK.Insert(ObjTK))
                    {
                        JOptionPane.showMessageDialog(this, "Đã đăng kí thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }else JOptionPane.showMessageDialog(this, "Đăng kí thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }else 
                {
                    JOptionPane.showMessageDialog(this, "Mật khẩu không trùng khớp", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jBtnTaoTKMouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormDangKi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDangKi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDangKi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDangKi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDangKi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jBtnBack;
    private javax.swing.JPanel jBtnTaoTK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRbtnQLBH;
    private javax.swing.JRadioButton jRbtnQLK;
    private javax.swing.JRadioButton jRbtnQLKT;
    private javax.swing.JRadioButton jRbtnQuanLi;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jtxtHoten;
    private javax.swing.JTextField jtxtTenDangNhap;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtRePassword;
    // End of variables declaration//GEN-END:variables
}

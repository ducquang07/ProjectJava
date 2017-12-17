/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Edit.Edit;
import Control.CtrlLapHoaDonLe;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import Object.ObjSanPham;
import Object.ObjLoaiSanPham;
import Object.ObjNhaCungCap;
import Object.ObjChiTietHDL;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
/**
 *
 * @author ThaiNguyen
 */
public class FormLapHoaDonLe extends javax.swing.JFrame {

    boolean ChinhSua=false;
    int xx=0,yy=0;
    Edit editFrm= new Edit();
    ArrayList<ObjSanPham> listSP = new ArrayList<>();
    ArrayList<String>listComboboxLSP = new ArrayList<>();
    ArrayList<String>listComboboxNCC=new ArrayList<>();
    ArrayList<ObjChiTietHDL>ListGioHang = new ArrayList<>();
    ArrayList<Integer> ListSoLuongTon = new ArrayList<>();
    CtrlLapHoaDonLe CtrlHDL = new CtrlLapHoaDonLe();
    FormDuyetHoaDonLe frmDuyetHDL;
    /**
     * Creates new form FormLapHoaDonLe
     */
    public FormLapHoaDonLe(){
        initComponents();
        this.setLocationRelativeTo(null);
        
        JPanel ListPn[]=new JPanel[]{jPanel1,jPanel2,jPanel3,jPanel4,jPanel7};
        editFrm.MakeTransparentPanel(ListPn);
        
        JPanel ListTitle[]=new JPanel[]{jPnDSSP,jPnGioHang,jPnThongtinHD,jPnThongtinSP,jPnTimkiemSP};
        editFrm.MakeTransparentTitle(ListTitle);
        
        JPanel ListButton[]=new JPanel[]{jbtnDuyetGioHang,jBtnBack,jBtnHuy,jBtnLamMoi,jBtnThem,jBtnTimKiem,jBtnXoa,jBtnChinhSua};
        editFrm.MakeTransparentButton(ListButton);
        
        jPanel5.setBackground(new Color(0,0,0,0));
        
        jtxtTenSP.setLineWrap(true);
        jtxtTenNCC.setLineWrap(true);
        
        editFrm.MakeTransparentTable(jScrGioHang, jtbGioHang);
        editFrm.MakeTransparentTable(jScrDSSP, jtbDSSP);       
        
        jSpSoLuong.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                  int dongia = Integer.parseInt(jtxtDonGia.getText().replace(",",""));
                  int soluong = Integer.parseInt(jSpSoLuong.getValue()+"");
                  jtxtThanhTien.setText(String.valueOf(dongia*soluong));
            }
        });
        jtxtSoHDL.setText(CtrlHDL.LaySoHDL());
        LoadForm();
    }

    public FormLapHoaDonLe(String SoHDL,String TenKH,ArrayList<ObjChiTietHDL> ListCT,Date NgayLap){
        initComponents();
        this.setLocationRelativeTo(null);
        
        JPanel ListPn[]=new JPanel[]{jPanel1,jPanel2,jPanel3,jPanel4,jPanel7};
        editFrm.MakeTransparentPanel(ListPn);
        
        JPanel ListTitle[]=new JPanel[]{jPnDSSP,jPnGioHang,jPnThongtinHD,jPnThongtinSP,jPnTimkiemSP};
        editFrm.MakeTransparentTitle(ListTitle);
        
        JPanel ListButton[]=new JPanel[]{jbtnDuyetGioHang,jBtnBack,jBtnHuy,jBtnLamMoi,jBtnThem,jBtnTimKiem,jBtnXoa};
        editFrm.MakeTransparentButton(ListButton);
        
        jPanel5.setBackground(new Color(0,0,0,0));
        
        jtxtTenSP.setLineWrap(true);
        jtxtTenNCC.setLineWrap(true);
        jtxtSoHDL.setText(SoHDL);
        
        editFrm.MakeTransparentTable(jScrGioHang, jtbGioHang);
        editFrm.MakeTransparentTable(jScrDSSP, jtbDSSP); 
        
//        jtbGioHang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                jtxtTongTien.setText(String.format("%,d",TinhTongTienGioHang()));
//            }
//        });
        
        jSpSoLuong.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                  int dongia = Integer.parseInt(jtxtDonGia.getText().replace(",",""));
                  int soluong = Integer.parseInt(jSpSoLuong.getValue()+"");
                  jtxtThanhTien.setText(String.valueOf(dongia*soluong));
            }
        });
        
       LoadForm();
       jtxtTenKH.setText(TenKH);
       jDateNgayLap.setDate(NgayLap);
       try{        
            DefaultTableModel Model = (DefaultTableModel) jtbGioHang.getModel();
            for(int i = 0;i<ListCT.size();i++){
                 this.ListGioHang.add(ListCT.get(i));
                 Vector v = new Vector();
                 v.add(ListCT.get(i).getMaSP());
                 v.add(ListCT.get(i).getTenSP());
                 v.add(ListCT.get(i).getDVT());
                 v.add(ListCT.get(i).getSoLuong());
                 v.add(String.format("%,d",ListCT.get(i).getDonGia()));
                 v.add(String.format("%,d",ListCT.get(i).getThanhTien()));
                 Model.addRow(v);    
            }
            jtxtTongTien.setText(String.format("%,d",TinhTongTienGioHang()));
       }
       catch(Exception ex){
           System.out.println("Ngoại lệ tại FormLapHoaDonSi():"+ex.getMessage());
       }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxtSoHDL = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jDateNgayLap = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jtxtTenKH = new javax.swing.JTextField();
        jtxtTongTien = new javax.swing.JTextField();
        jPnThongtinHD = new javax.swing.JPanel();
        jBtnTimKiem = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jBtnLamMoi = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jBtnChinhSua = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jScrGioHang = new javax.swing.JScrollPane();
        jtbGioHang = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPnGioHang = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jCbbTimTheo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jCbbTenNCC = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jCbbLoaiSP = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jtxtTimKiem = new javax.swing.JTextField();
        jPnTimkiemSP = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrDSSP = new javax.swing.JScrollPane();
        jtbDSSP = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPnDSSP = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSpSoLuong = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        jtxtDVT = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jtxtDonGia = new javax.swing.JTextField();
        jPnThongtinSP = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtTenSP = new javax.swing.JTextArea();
        jLabel27 = new javax.swing.JLabel();
        jtxtTenLoaiSP = new javax.swing.JTextField();
        jtxtThanhTien = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtxtTenNCC = new javax.swing.JTextArea();
        jBtnBack = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jbtnDuyetGioHang = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jBtnHuy = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jBtnThem = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jBtnXoa = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Thông tin hóa đơn :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, -1, 20));

        jLabel2.setText("Số hóa đơn  :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 54, -1, -1));

        jLabel3.setText("Khách hàng :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 101, -1, -1));

        jtxtSoHDL.setEditable(false);
        jtxtSoHDL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtSoHDLActionPerformed(evt);
            }
        });
        jPanel1.add(jtxtSoHDL, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 51, 207, -1));

        jLabel4.setText("Ngày lập :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 54, -1, -1));

        jDateNgayLap.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(jDateNgayLap, new org.netbeans.lib.awtextra.AbsoluteConstraints(469, 51, 190, -1));

        jLabel5.setText("Tổng tiền :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 101, -1, -1));
        jPanel1.add(jtxtTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 98, 207, -1));

        jtxtTongTien.setEditable(false);
        jtxtTongTien.setText("0");
        jPanel1.add(jtxtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(469, 98, 190, -1));

        jPnThongtinHD.setBackground(new java.awt.Color(0, 204, 204));

        javax.swing.GroupLayout jPnThongtinHDLayout = new javax.swing.GroupLayout(jPnThongtinHD);
        jPnThongtinHD.setLayout(jPnThongtinHDLayout);
        jPnThongtinHDLayout.setHorizontalGroup(
            jPnThongtinHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 689, Short.MAX_VALUE)
        );
        jPnThongtinHDLayout.setVerticalGroup(
            jPnThongtinHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPnThongtinHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 689, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 689, 150));

        jBtnTimKiem.setPreferredSize(new java.awt.Dimension(102, 50));
        jBtnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnTimKiemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnTimKiemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnTimKiemMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnTimKiemMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBtnTimKiemMouseReleased(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Search_45px.png"))); // NOI18N

        javax.swing.GroupLayout jBtnTimKiemLayout = new javax.swing.GroupLayout(jBtnTimKiem);
        jBtnTimKiem.setLayout(jBtnTimKiemLayout);
        jBtnTimKiemLayout.setHorizontalGroup(
            jBtnTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnTimKiemLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel12)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jBtnTimKiemLayout.setVerticalGroup(
            jBtnTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1068, 124, 110, 65));

        jBtnLamMoi.setPreferredSize(new java.awt.Dimension(105, 50));
        jBtnLamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnLamMoiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnLamMoiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnLamMoiMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnLamMoiMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBtnLamMoiMouseReleased(evt);
            }
        });

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Refresh_45px.png"))); // NOI18N

        javax.swing.GroupLayout jBtnLamMoiLayout = new javax.swing.GroupLayout(jBtnLamMoi);
        jBtnLamMoi.setLayout(jBtnLamMoiLayout);
        jBtnLamMoiLayout.setHorizontalGroup(
            jBtnLamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jBtnLamMoiLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jBtnLamMoiLayout.setVerticalGroup(
            jBtnLamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 124, 112, 65));

        jBtnChinhSua.setBackground(new java.awt.Color(204, 204, 204));
        jBtnChinhSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnChinhSuaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnChinhSuaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnChinhSuaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnChinhSuaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBtnChinhSuaMouseReleased(evt);
            }
        });

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Edit_20px_1.png"))); // NOI18N

        javax.swing.GroupLayout jBtnChinhSuaLayout = new javax.swing.GroupLayout(jBtnChinhSua);
        jBtnChinhSua.setLayout(jBtnChinhSuaLayout);
        jBtnChinhSuaLayout.setHorizontalGroup(
            jBtnChinhSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnChinhSuaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jBtnChinhSuaLayout.setVerticalGroup(
            jBtnChinhSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnChinhSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 220, 69, 30));

        jtbGioHang.setAutoCreateRowSorter(true);
        jtbGioHang.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jtbGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Sản phẩm", "ĐVT", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbGioHang.setFocusable(false);
        jtbGioHang.setRowHeight(25);
        jtbGioHang.setSelectionBackground(new java.awt.Color(218, 223, 225));
        jtbGioHang.setSelectionForeground(new java.awt.Color(255, 51, 0));
        jtbGioHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtbGioHang.setShowHorizontalLines(false);
        jtbGioHang.getTableHeader().setReorderingAllowed(false);
        jScrGioHang.setViewportView(jtbGioHang);
        if (jtbGioHang.getColumnModel().getColumnCount() > 0) {
            jtbGioHang.getColumnModel().getColumn(0).setPreferredWidth(80);
            jtbGioHang.getColumnModel().getColumn(1).setPreferredWidth(300);
            jtbGioHang.getColumnModel().getColumn(2).setPreferredWidth(80);
            jtbGioHang.getColumnModel().getColumn(3).setPreferredWidth(80);
            jtbGioHang.getColumnModel().getColumn(4).setPreferredWidth(100);
            jtbGioHang.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        getContentPane().add(jScrGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 255, 679, 400));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Giỏ hàng :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, 20));

        jPnGioHang.setBackground(new java.awt.Color(0, 204, 204));

        javax.swing.GroupLayout jPnGioHangLayout = new javax.swing.GroupLayout(jPnGioHang);
        jPnGioHang.setLayout(jPnGioHangLayout);
        jPnGioHangLayout.setHorizontalGroup(
            jPnGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        jPnGioHangLayout.setVerticalGroup(
            jPnGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2.add(jPnGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 689, 440));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jLabel8.setText("Tìm theo :");

        jCbbTimTheo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên sản phẩm", "Mã sản phẩm" }));
        jCbbTimTheo.setFocusable(false);
        jCbbTimTheo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbbTimTheoActionPerformed(evt);
            }
        });

        jLabel9.setText("Nhà cung cấp :");

        jCbbTenNCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn nhà cung cấp---" }));
        jCbbTenNCC.setAutoscrolls(true);
        jCbbTenNCC.setFocusable(false);

        jLabel10.setText("Loại sản phẩm :");

        jCbbLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn loại sản phẩm---" }));
        jCbbLoaiSP.setAutoscrolls(true);
        jCbbLoaiSP.setFocusable(false);

        jLabel11.setText("Từ tìm kiếm :");

        jtxtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtTimKiemKeyPressed(evt);
            }
        });

        jPnTimkiemSP.setBackground(new java.awt.Color(0, 204, 204));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Tìm kiếm sản phẩm :");

        javax.swing.GroupLayout jPnTimkiemSPLayout = new javax.swing.GroupLayout(jPnTimkiemSP);
        jPnTimkiemSP.setLayout(jPnTimkiemSPLayout);
        jPnTimkiemSPLayout.setHorizontalGroup(
            jPnTimkiemSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTimkiemSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(466, Short.MAX_VALUE))
        );
        jPnTimkiemSPLayout.setVerticalGroup(
            jPnTimkiemSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPnTimkiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(38, 38, 38)
                        .addComponent(jCbbTimTheo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addGap(10, 10, 10)
                        .addComponent(jtxtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(10, 10, 10)
                            .addComponent(jCbbLoaiSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(14, 14, 14)
                            .addComponent(jCbbTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPnTimkiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCbbTimTheo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel9))
                    .addComponent(jCbbTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel10))
                    .addComponent(jCbbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 600, 150));

        jtbDSSP.setAutoCreateRowSorter(true);
        jtbDSSP.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jtbDSSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbDSSP.setFocusable(false);
        jtbDSSP.setRowHeight(25);
        jtbDSSP.setSelectionBackground(new java.awt.Color(218, 223, 225));
        jtbDSSP.setSelectionForeground(new java.awt.Color(255, 51, 0));
        jtbDSSP.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtbDSSP.setShowHorizontalLines(false);
        jtbDSSP.setShowVerticalLines(false);
        jtbDSSP.getTableHeader().setReorderingAllowed(false);
        jtbDSSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtbDSSPMousePressed(evt);
            }
        });
        jScrDSSP.setViewportView(jtbDSSP);
        if (jtbDSSP.getColumnModel().getColumnCount() > 0) {
            jtbDSSP.getColumnModel().getColumn(0).setPreferredWidth(100);
            jtbDSSP.getColumnModel().getColumn(1).setPreferredWidth(500);
        }

        getContentPane().add(jScrDSSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(725, 255, 590, 230));

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Danh sách sản phẩm :");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, -1, 20));

        jPnDSSP.setBackground(new java.awt.Color(0, 204, 204));

        javax.swing.GroupLayout jPnDSSPLayout = new javax.swing.GroupLayout(jPnDSSP);
        jPnDSSP.setLayout(jPnDSSPLayout);
        jPnDSSPLayout.setHorizontalGroup(
            jPnDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        jPnDSSPLayout.setVerticalGroup(
            jPnDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel4.add(jPnDSSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 30));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 220, 600, 270));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setText("Tên sản phẩm :");
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel17.setText("Số lượng :");
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, 20));

        jSpSoLuong.setName(""); // NOI18N
        jSpSoLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSpSoLuongMouseClicked(evt);
            }
        });
        jPanel7.add(jSpSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 130, 190, -1));

        jLabel18.setText("Đơn giá :");
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, -1, 20));

        jtxtDVT.setEditable(false);
        jPanel7.add(jtxtDVT, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 73, 178, 20));

        jLabel19.setText("Thành tiền :");
        jPanel7.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, -1, -1));

        jtxtDonGia.setEditable(false);
        jtxtDonGia.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jtxtDonGiaInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jPanel7.add(jtxtDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 178, -1));

        jPnThongtinSP.setBackground(new java.awt.Color(0, 204, 204));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Thông tin sản phẩm : ");

        javax.swing.GroupLayout jPnThongtinSPLayout = new javax.swing.GroupLayout(jPnThongtinSP);
        jPnThongtinSP.setLayout(jPnThongtinSPLayout);
        jPnThongtinSPLayout.setHorizontalGroup(
            jPnThongtinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnThongtinSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPnThongtinSPLayout.setVerticalGroup(
            jPnThongtinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel7.add(jPnThongtinSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 30));

        jLabel25.setText("Nhà cung cấp :");
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setFocusable(false);

        jtxtTenSP.setEditable(false);
        jtxtTenSP.setColumns(1);
        jtxtTenSP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jtxtTenSP.setRows(5);
        jScrollPane1.setViewportView(jtxtTenSP);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 88, 194, 30));

        jLabel27.setText("Loại :");
        jPanel7.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));

        jtxtTenLoaiSP.setEditable(false);
        jPanel7.add(jtxtTenLoaiSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 47, 178, -1));

        jtxtThanhTien.setEditable(false);
        jtxtThanhTien.setText("0");
        jPanel7.add(jtxtThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 127, 178, -1));

        jLabel28.setText("ĐVT :");
        jPanel7.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 76, -1, -1));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jtxtTenNCC.setEditable(false);
        jtxtTenNCC.setColumns(20);
        jtxtTenNCC.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jtxtTenNCC.setRows(5);
        jScrollPane2.setViewportView(jtxtTenNCC);

        jPanel7.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 38, 194, 40));

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 500, 600, 161));

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
            .addGroup(jBtnBackLayout.createSequentialGroup()
                .addComponent(jLabel21)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jBtnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jbtnDuyetGioHang.setBackground(new java.awt.Color(204, 204, 204));
        jbtnDuyetGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnDuyetGioHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnDuyetGioHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnDuyetGioHangMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jbtnDuyetGioHangMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jbtnDuyetGioHangMouseReleased(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Tick_Box_40px.png"))); // NOI18N
        jLabel22.setText("Duyệt giỏ hàng");

        javax.swing.GroupLayout jbtnDuyetGioHangLayout = new javax.swing.GroupLayout(jbtnDuyetGioHang);
        jbtnDuyetGioHang.setLayout(jbtnDuyetGioHangLayout);
        jbtnDuyetGioHangLayout.setHorizontalGroup(
            jbtnDuyetGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jbtnDuyetGioHangLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jbtnDuyetGioHangLayout.setVerticalGroup(
            jbtnDuyetGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(jbtnDuyetGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 670, 190, 50));

        jBtnHuy.setBackground(new java.awt.Color(204, 204, 204));
        jBtnHuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnHuyMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnHuyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnHuyMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnHuyMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBtnHuyMouseReleased(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Close_Window_40px.png"))); // NOI18N
        jLabel23.setText("Hủy giỏ hàng");

        javax.swing.GroupLayout jBtnHuyLayout = new javax.swing.GroupLayout(jBtnHuy);
        jBtnHuy.setLayout(jBtnHuyLayout);
        jBtnHuyLayout.setHorizontalGroup(
            jBtnHuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnHuyLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jBtnHuyLayout.setVerticalGroup(
            jBtnHuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 670, 190, 50));

        jBtnThem.setBackground(new java.awt.Color(204, 204, 204));
        jBtnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnThemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnThemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnThemMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnThemMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBtnThemMouseReleased(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Add_Shopping_Cart_40px.png"))); // NOI18N
        jLabel24.setText("Thêm vào giỏ");

        javax.swing.GroupLayout jBtnThemLayout = new javax.swing.GroupLayout(jBtnThem);
        jBtnThem.setLayout(jBtnThemLayout);
        jBtnThemLayout.setHorizontalGroup(
            jBtnThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnThemLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel24)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jBtnThemLayout.setVerticalGroup(
            jBtnThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 670, 190, 50));

        jBtnXoa.setBackground(new java.awt.Color(204, 204, 204));
        jBtnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnXoaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnXoaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnXoaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnXoaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBtnXoaMouseReleased(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Clear_Shopping_Cart_40px.png"))); // NOI18N
        jLabel26.setText("Xóa khỏi giỏ");

        javax.swing.GroupLayout jBtnXoaLayout = new javax.swing.GroupLayout(jBtnXoa);
        jBtnXoa.setLayout(jBtnXoaLayout);
        jBtnXoaLayout.setHorizontalGroup(
            jBtnXoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnXoaLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jBtnXoaLayout.setVerticalGroup(
            jBtnXoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 670, 190, 50));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1330, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 730));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/pexels-photo-530024.jpeg"))); // NOI18N
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, 730));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void LoadForm(){
        HienThiDanhSachSanPham(CtrlHDL.LayDanhSachSanPham());
        LoadComboboxLoaiSP();
        LoadComboboxNhaCungCap();
        Binding();
        jDateNgayLap.setDate(new Date());
        jSpSoLuong.setValue(1);
    }
    
    public void HienThiDanhSachSanPham(ResultSet rs){
        listSP.clear();
        DefaultTableModel model;
        model=(DefaultTableModel) jtbDSSP.getModel();
        model.getDataVector().removeAllElements(); // remove data in table
        model.fireTableDataChanged();
        try{
            while(rs.next()){
                ObjSanPham itemSP;
                itemSP=new ObjSanPham(rs.getString("MaSP"),rs.getString("TenSP"),rs.getString("MaLoaiSP"),rs.getString("TenLoaiSP"),(int) Double.parseDouble(rs.getString("GiaLe")),(int) Double.parseDouble(rs.getString("GiaSi")),rs.getString("DVT"), (int) Double.parseDouble(rs.getString("SoLuong")),rs.getString("MaNCC"),rs.getString("TenNCC"));
                listSP.add(itemSP);
                Vector v = new Vector();
                v.add(itemSP.getMaSP());
                v.add(itemSP.getTenSP());
                v.add(itemSP.getSoLuong());
                model.addRow(v);
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại FormLapHoaDonLe.HienThiDanhSachSanPham: "+ex.getMessage());
        }
        finally{
            CtrlHDL.CloseConnection();
        }
        jtbDSSP.changeSelection(0,0,false,false);
    }
    
    public void LoadComboboxLoaiSP(){
        listComboboxLSP.clear();
        jCbbLoaiSP.removeAllItems();
        jCbbLoaiSP.addItem("---Chọn loại sản phẩm---");
        listComboboxLSP.add("");
        ResultSet rs=CtrlHDL.LayDanhSachLoaiSanPham();
        try{
            while(rs.next()){
                jCbbLoaiSP.addItem(rs.getString("TenLoaiSP"));
                listComboboxLSP.add(rs.getString("MaLoaiSP"));
            }
        }
        catch(SQLException ex){
            System.out.println("Ngoại lệ tại FormLapHoaDonLe.LoadComboboxLoaiSP: "+ex.getMessage());
        }
        finally{
            CtrlHDL.CloseConnection();
        }
    }
    
    public void LoadComboboxNhaCungCap(){
        listComboboxNCC.clear();
        jCbbTenNCC.removeAllItems();
        jCbbTenNCC.addItem("---Chọn loại sản phẩm---");
        listComboboxNCC.add("");
        ResultSet rs=CtrlHDL.LayDanhSachNhaCungCap();
        try{
            while(rs.next()){
                jCbbTenNCC.addItem(rs.getString("TenNCC"));
                listComboboxNCC.add(rs.getString("MaNCC"));
            }
        }
        catch(SQLException ex){
            System.out.println("Ngoại lệ tại FormLapHoaDonLe.LoadComboboxLoaiSP: "+ex.getMessage());
        }
        finally{
            CtrlHDL.CloseConnection();
        }
    }
    
    public void ReloadForm(){
        HienThiDanhSachSanPham(CtrlHDL.LayDanhSachSanPham());
        ListGioHang.clear();
        ListSoLuongTon.clear();
        LoadComboboxLoaiSP();
        LoadComboboxNhaCungCap();
        jtxtTimKiem.setText("");
        jtxtTongTien.setText("0");
        jtxtTenKH.setText("");
        jtbGioHang.removeAll();
        jtxtSoHDL.setText(CtrlHDL.LaySoHDL());
        jDateNgayLap.setDate(new Date());
        DefaultTableModel model;
        model=(DefaultTableModel) jtbGioHang.getModel();
        model.getDataVector().removeAllElements(); 
        model.fireTableDataChanged();
    }
    
    public void Binding(){
        TableModel model =jtbDSSP.getModel();
        try{
            int viewRow = jtbDSSP.getSelectedRow();
            int modelRow= jtbDSSP.convertRowIndexToModel(viewRow);
             if(viewRow>-1){
                  jtxtTenSP.setText(listSP.get(modelRow).getTenSP());
                  jtxtDVT.setText(listSP.get(modelRow).getDVT());
                  jtxtDonGia.setText(String.format("%,d",listSP.get(modelRow).getGiaLe()));
                  jtxtTenSP.setText(listSP.get(modelRow).getTenSP());
                  jtxtTenLoaiSP.setText(listSP.get(modelRow).getTenLoaiSP());
                  jtxtTenNCC.setText(listSP.get(modelRow).getTenNCC());
                  int dongia = Integer.parseInt(jtxtDonGia.getText().replace(",",""));
                  int soluong = Integer.parseInt(jSpSoLuong.getValue()+"");
                  jtxtThanhTien.setText(String.format("%,d",dongia*soluong));
             }
        }
        catch(Exception ex){
            System.out.println("Ngoại lệ tại FormLapHoaDonLe.Binding: "+ex.getMessage());
        }
    }

    public int TinhTongTienGioHang(){
        int Tong=0;
        for(int i = 0 ;i<ListGioHang.size();i++){
            Tong+=ListGioHang.get(i).getThanhTien();
        }
        return Tong;
    }
    
    
    private void jBtnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnTimKiemMouseClicked
        // TODO add your handling code here:
        if(jCbbTimTheo.getSelectedIndex()==0)
            HienThiDanhSachSanPham(CtrlHDL.SearchSanPhamByName(jtxtTimKiem.getText(),listComboboxLSP.get(jCbbLoaiSP.getSelectedIndex()).toString(),listComboboxNCC.get(jCbbTenNCC.getSelectedIndex()).toString()));   
        else
            HienThiDanhSachSanPham(CtrlHDL.SearchSanPhamByID(jtxtTimKiem.getText(),listComboboxLSP.get(jCbbLoaiSP.getSelectedIndex()).toString(),listComboboxNCC.get(jCbbTenNCC.getSelectedIndex()).toString()));   
        Binding();
    }//GEN-LAST:event_jBtnTimKiemMouseClicked

    private void jBtnTimKiemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnTimKiemMouseEntered
        // TODO add your handling code here:
        setColor(jBtnTimKiem);
    }//GEN-LAST:event_jBtnTimKiemMouseEntered

    private void jBtnTimKiemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnTimKiemMouseExited
        // TODO add your handling code here:
        resetColor(jBtnTimKiem);
    }//GEN-LAST:event_jBtnTimKiemMouseExited

    private void jBtnTimKiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnTimKiemMousePressed
        // TODO add your handling code here:
        resetColor(jBtnTimKiem);
    }//GEN-LAST:event_jBtnTimKiemMousePressed

    private void jBtnTimKiemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnTimKiemMouseReleased
        // TODO add your handling code here:
        setColor(jBtnTimKiem);
    }//GEN-LAST:event_jBtnTimKiemMouseReleased

    private void jBtnLamMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLamMoiMouseClicked
        // TODO add your handling code here:
        HienThiDanhSachSanPham(CtrlHDL.LayDanhSachSanPham());
        LoadComboboxLoaiSP();
        LoadComboboxNhaCungCap();
        jtxtTimKiem.setText("");
    }//GEN-LAST:event_jBtnLamMoiMouseClicked

    private void jBtnLamMoiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLamMoiMouseEntered
        // TODO add your handling code here:
        setColor(jBtnLamMoi);
    }//GEN-LAST:event_jBtnLamMoiMouseEntered

    private void jBtnLamMoiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLamMoiMouseExited
        // TODO add your handling code here:
        resetColor(jBtnLamMoi);
    }//GEN-LAST:event_jBtnLamMoiMouseExited

    private void jBtnLamMoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLamMoiMousePressed
        // TODO add your handling code here:
        resetColor(jBtnLamMoi);
    }//GEN-LAST:event_jBtnLamMoiMousePressed

    private void jBtnLamMoiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLamMoiMouseReleased
        // TODO add your handling code here:
        setColor(jBtnLamMoi);
    }//GEN-LAST:event_jBtnLamMoiMouseReleased

    private void jtxtSoHDLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtSoHDLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtSoHDLActionPerformed

    private void jBtnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXoaMouseClicked
        // TODO add your handling code here
        TableModel model =jtbGioHang.getModel();
        DefaultTableModel Model=(DefaultTableModel) jtbGioHang.getModel() ;
        try{
        int viewRow = jtbGioHang.getSelectedRow();
        int modelRow= jtbGioHang.convertRowIndexToModel(viewRow);
        ListGioHang.remove(modelRow);
        Model.removeRow(modelRow);
        jtxtTongTien.setText(String.format("%,d",TinhTongTienGioHang()));
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(this,"Không có sản phẩm được chọn.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } 
    }//GEN-LAST:event_jBtnXoaMouseClicked

    private void jBtnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnBackMouseClicked
        // TODO add your handling code here:
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

    private void jbtnDuyetGioHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnDuyetGioHangMouseEntered
        // TODO add your handling code here:
        setColor(jbtnDuyetGioHang);
    }//GEN-LAST:event_jbtnDuyetGioHangMouseEntered

    private void jbtnDuyetGioHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnDuyetGioHangMouseExited
        // TODO add your handling code here:
        resetColor(jbtnDuyetGioHang);
    }//GEN-LAST:event_jbtnDuyetGioHangMouseExited

    private void jbtnDuyetGioHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnDuyetGioHangMousePressed
        // TODO add your handling code here:
        resetColor(jbtnDuyetGioHang);
    }//GEN-LAST:event_jbtnDuyetGioHangMousePressed

    private void jbtnDuyetGioHangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnDuyetGioHangMouseReleased
        // TODO add your handling code here:
        setColor(jbtnDuyetGioHang);
    }//GEN-LAST:event_jbtnDuyetGioHangMouseReleased

    private void jBtnHuyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnHuyMouseEntered
        // TODO add your handling code here:
        setColor(jBtnHuy);
    }//GEN-LAST:event_jBtnHuyMouseEntered

    private void jBtnHuyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnHuyMouseExited
        // TODO add your handling code here:
        resetColor(jBtnHuy);
    }//GEN-LAST:event_jBtnHuyMouseExited

    private void jBtnHuyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnHuyMousePressed
        // TODO add your handling code here:
        resetColor(jBtnHuy);
    }//GEN-LAST:event_jBtnHuyMousePressed

    private void jBtnHuyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnHuyMouseReleased
        // TODO add your handling code here:
        setColor(jBtnHuy);
    }//GEN-LAST:event_jBtnHuyMouseReleased

    private void jBtnThemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnThemMouseEntered
        // TODO add your handling code here:
        setColor(jBtnThem);
    }//GEN-LAST:event_jBtnThemMouseEntered

    private void jBtnThemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnThemMouseExited
        // TODO add your handling code here:
        resetColor(jBtnThem);
    }//GEN-LAST:event_jBtnThemMouseExited

    private void jBtnThemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnThemMousePressed
        // TODO add your handling code here:
        resetColor(jBtnThem);
    }//GEN-LAST:event_jBtnThemMousePressed

    private void jBtnThemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnThemMouseReleased
        // TODO add your handling code here:
        setColor(jBtnThem);
    }//GEN-LAST:event_jBtnThemMouseReleased

    private void jBtnXoaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXoaMouseEntered
        // TODO add your handling code here:
        setColor(jBtnXoa);
    }//GEN-LAST:event_jBtnXoaMouseEntered

    private void jBtnXoaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXoaMouseExited
        // TODO add your handling code here:
        resetColor(jBtnXoa);
    }//GEN-LAST:event_jBtnXoaMouseExited

    private void jBtnXoaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXoaMousePressed
        // TODO add your handling code here:
        resetColor(jBtnXoa);
    }//GEN-LAST:event_jBtnXoaMousePressed

    private void jBtnXoaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXoaMouseReleased
        // TODO add your handling code here:
        setColor(jBtnXoa);
    }//GEN-LAST:event_jBtnXoaMouseReleased

    private void jtbDSSPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbDSSPMousePressed
        // TODO add your handling code here:
        Binding();
    }//GEN-LAST:event_jtbDSSPMousePressed

    
    
    private void jBtnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnThemMouseClicked
        // TODO add your handling code here:
        boolean exist=false;
        
        DefaultTableModel Model;
        int viewRow = jtbDSSP.getSelectedRow();
        int modelRow= jtbDSSP.convertRowIndexToModel(viewRow);
        Model =(DefaultTableModel) jtbGioHang.getModel();
        if(Integer.parseInt(jSpSoLuong.getValue()+"")<=listSP.get(modelRow).getSoLuong()){
            ObjChiTietHDL itemGioHang=new ObjChiTietHDL(jtxtSoHDL.getText(),listSP.get(modelRow).getMaSP(),listSP.get(modelRow).getTenSP(),listSP.get(modelRow).getDVT(),Integer.parseInt(jSpSoLuong.getValue()+""),listSP.get(modelRow).getGiaLe(),Integer.parseInt(jtxtThanhTien.getText().replace(",","")));
            try{ 
            for(int i = 0 ;i<Model.getRowCount();i++){
                if(Model.getValueAt(i,0).toString().equals(itemGioHang.getMaSP())){
                    exist=true;
                    int SL =itemGioHang.getSoLuong()+Integer.parseInt(Model.getValueAt(i,3).toString());
                    if(SL<=listSP.get(modelRow).getSoLuong()){
                          int ThanhTien = SL*Integer.parseInt(Model.getValueAt(i,4).toString().replace(",",""));
                          Model.setValueAt(SL,i,3);
                          Model.setValueAt(String.format("%,d",ThanhTien),i,5);
                          ListGioHang.get(i).setSoLuong(SL);
                          ListGioHang.get(i).setThanhTien(ThanhTien);
                          break;
                    }
                    else {
                          JOptionPane.showMessageDialog(this,"Số lượng không đủ.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                          break;
                    }
                }
            }
            if(!exist){
                ListGioHang.add(itemGioHang);
                ListSoLuongTon.add(listSP.get(modelRow).getSoLuong());
                Vector v = new Vector();
                v.add(itemGioHang.getMaSP());
                v.add(itemGioHang.getTenSP());
                v.add(itemGioHang.getDVT());
                v.add(itemGioHang.getSoLuong());
                v.add(String.format("%,d",itemGioHang.getDonGia()));
                v.add(String.format("%,d",itemGioHang.getThanhTien()));
                Model.addRow(v);
            }
            jtxtTongTien.setText(String.format("%,d",TinhTongTienGioHang()));
            }
            catch(NumberFormatException ex){
                 System.out.println("Ngoại lệ tại FormLapHoaDonLe.Binding: "+ex.getMessage());
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Số lượng không đủ.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jBtnThemMouseClicked

    private void jtxtDonGiaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jtxtDonGiaInputMethodTextChanged
        // TODO add your handling code here:
 
    }//GEN-LAST:event_jtxtDonGiaInputMethodTextChanged

    private void jSpSoLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSpSoLuongMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jSpSoLuongMouseClicked

    private void jCbbTimTheoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbbTimTheoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbbTimTheoActionPerformed

    private void jbtnDuyetGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnDuyetGioHangMouseClicked
        // TODO add your handling code here:
        try{
            if(ListGioHang.size()>0){
                frmDuyetHDL = new FormDuyetHoaDonLe(jtxtSoHDL.getText(),jtxtTenKH.getText(), jDateNgayLap.getDate(),jtxtTongTien.getText(),ListGioHang);
                if(ChinhSua) frmDuyetHDL.ChinhSua=true;
                frmDuyetHDL.setVisible(true);
                this.setVisible(false);
                this.dispose();
            }
            else
                JOptionPane.showMessageDialog(this, "Giỏ hàng trống.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception ex){
            System.out.println("Ngoại lệ tại FormLapHoaDonLe.jbtnDuyetGioHangMouseClicked: "+ex.getMessage());
        }
    }//GEN-LAST:event_jbtnDuyetGioHangMouseClicked

    private void jtxtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtTimKiemKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            jBtnTimKiemMouseClicked(null);
    }//GEN-LAST:event_jtxtTimKiemKeyPressed

    private void jBtnHuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnHuyMouseClicked
        // TODO add your handling code here:
        ReloadForm();
    }//GEN-LAST:event_jBtnHuyMouseClicked

    private void jBtnChinhSuaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnChinhSuaMouseEntered
        // TODO add your handling code here:
        setColor(jBtnChinhSua);
    }//GEN-LAST:event_jBtnChinhSuaMouseEntered

    private void jBtnChinhSuaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnChinhSuaMouseExited
        // TODO add your handling code here:
        resetColor(jBtnChinhSua);
    }//GEN-LAST:event_jBtnChinhSuaMouseExited

    private void jBtnChinhSuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnChinhSuaMousePressed
        // TODO add your handling code here:
        resetColor(jBtnChinhSua);
    }//GEN-LAST:event_jBtnChinhSuaMousePressed

    private void jBtnChinhSuaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnChinhSuaMouseReleased
        // TODO add your handling code here:
        setColor(jBtnChinhSua);
    }//GEN-LAST:event_jBtnChinhSuaMouseReleased

    private void jBtnChinhSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnChinhSuaMouseClicked
        // TODO add your handling code here:
        if (jtbGioHang.getSelectedRow() >= 0) {
            DefaultTableModel Model = (DefaultTableModel) jtbGioHang.getModel();
            int viewRow = jtbGioHang.getSelectedRow();
            int modelRow = jtbGioHang.convertRowIndexToModel(viewRow);
            int soluongcu = Integer.parseInt(Model.getValueAt(modelRow, 3).toString());
            int dongia = Integer.parseInt(Model.getValueAt(modelRow, 4).toString().replace(",", ""));
            String inputdialog = JOptionPane.showInputDialog(this, "Nhập số lượng:", "");
            int soluongtonkho = CtrlHDL.LaySoLuongSanPham(Model.getValueAt(modelRow, 0).toString());
            if (inputdialog != null) {
                try {
                    int SL = Integer.parseInt(inputdialog);
                    if (!inputdialog.equals("0")) {
                        if (!ChinhSua) {
                            if (SL <= soluongtonkho) {
                                Model.setValueAt(SL, modelRow, 3);
                                Model.setValueAt(String.format("%,d", dongia * SL), modelRow, 5);
                            } else {
                                JOptionPane.showMessageDialog(this, "Số lượng không đủ.Hiện chỉ còn " + soluongtonkho + ".", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            if (SL <= soluongtonkho + soluongcu) {
                                Model.setValueAt(SL, modelRow, 3);
                                Model.setValueAt(String.format("%,d", dongia * SL), modelRow, 5);
                            } else {
                                JOptionPane.showMessageDialog(this, "Số lượng không đủ.Hiện chỉ còn " + (soluongtonkho + soluongcu) + ".", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    } else {
                        jBtnXoaMouseClicked(null);
                    }
                    jtxtTongTien.setText(String.format("%,d", TinhTongTienGioHang()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Số lượng nhập không hợp lệ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jBtnChinhSuaMouseClicked


    public void setColor(JPanel pn){
        if(pn.isEnabled()){
        pn.setSize(pn.getWidth()+1, pn.getHeight()+1);
        pn.setBackground(new Color(60,209,127,50));
        }
    }
    public void resetColor(JPanel pn){
        if(pn.isEnabled()){
        pn.setSize(pn.getWidth()-1, pn.getHeight()-1);
        pn.setBackground(new Color(153,153,153,180));
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormLapHoaDonLe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormLapHoaDonLe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormLapHoaDonLe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormLapHoaDonLe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new FormLapHoaDonLe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jBtnBack;
    private javax.swing.JPanel jBtnChinhSua;
    private javax.swing.JPanel jBtnHuy;
    private javax.swing.JPanel jBtnLamMoi;
    private javax.swing.JPanel jBtnThem;
    private javax.swing.JPanel jBtnTimKiem;
    private javax.swing.JPanel jBtnXoa;
    private javax.swing.JComboBox<String> jCbbLoaiSP;
    private javax.swing.JComboBox<String> jCbbTenNCC;
    private javax.swing.JComboBox<String> jCbbTimTheo;
    private com.toedter.calendar.JDateChooser jDateNgayLap;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPnDSSP;
    private javax.swing.JPanel jPnGioHang;
    private javax.swing.JPanel jPnThongtinHD;
    private javax.swing.JPanel jPnThongtinSP;
    private javax.swing.JPanel jPnTimkiemSP;
    private javax.swing.JScrollPane jScrDSSP;
    private javax.swing.JScrollPane jScrGioHang;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpSoLuong;
    private javax.swing.JPanel jbtnDuyetGioHang;
    private javax.swing.JTable jtbDSSP;
    private javax.swing.JTable jtbGioHang;
    private javax.swing.JTextField jtxtDVT;
    private javax.swing.JTextField jtxtDonGia;
    private javax.swing.JTextField jtxtSoHDL;
    private javax.swing.JTextField jtxtTenKH;
    private javax.swing.JTextField jtxtTenLoaiSP;
    private javax.swing.JTextArea jtxtTenNCC;
    private javax.swing.JTextArea jtxtTenSP;
    private javax.swing.JTextField jtxtThanhTien;
    private javax.swing.JTextField jtxtTimKiem;
    private javax.swing.JTextField jtxtTongTien;
    // End of variables declaration//GEN-END:variables
}

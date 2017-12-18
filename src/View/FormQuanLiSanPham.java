/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Control.CtrlQuanLiSanPham;
import Edit.Edit;
import Model.ModSanPham;
import Object.ObjSanPham;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ColdBlood
 */
public class FormQuanLiSanPham extends javax.swing.JFrame {

    Edit editFrm=new Edit();
    ArrayList<ObjSanPham> ListSP = new ArrayList<>();
    ObjSanPham ObjSP = new ObjSanPham();
    CtrlQuanLiSanPham CtrlQLSP = new CtrlQuanLiSanPham();
    ModSanPham ModSP = new ModSanPham();
    ArrayList<String> ListComboboxNCC = new ArrayList();
    ArrayList<String> ListComboboxLoaiSP = new ArrayList();
    private int flag=0;
    /**
     * Creates new form FormQuanLiSanPham
     */
    public FormQuanLiSanPham() {
        initComponents();
        setLocationRelativeTo(null);
        
        jPanel9.setBackground((new Color(0,0,0,0)));
        JPanel ListPanel[]=new JPanel[]{jPanel3,jPanel5,jPanel8};
        editFrm.MakeTransparentPanel(ListPanel);
        
        JPanel ListTitle[]=new JPanel[]{jPnDSSP,jPnThongtinSP,jPnTracuuSP};
        editFrm.MakeTransparentTitle(ListTitle);
        
        JPanel ListButton[]=new JPanel[]{jBtnBack,jBtnHuy,jBtnLamMoi,jBtnLuu,jBtnSua,jBtnThem,jBtnTimKiem,jBtnXoa};
        editFrm.MakeTransparentButton(ListButton);
        
        editFrm.MakeTransparentTable(jScrDSSP, jTbDSSP);
        LoadForm();
    }
    public void HienThiDanhSachSanPham(ResultSet rs){
        ListSP.clear();
        DefaultTableModel model;
        model=(DefaultTableModel) jTbDSSP.getModel();
        model.getDataVector().removeAllElements(); 
        try{
            while(rs.next()){
                ObjSanPham itemSP;
                itemSP =new ObjSanPham(rs.getString("MaSP"),rs.getString("TenSP"),rs.getString("MaLoaiSP"),rs.getString("TenLoaiSP"),(int) Double.parseDouble(rs.getString("GiaLe")),(int) Double.parseDouble(rs.getString("GiaSi")),rs.getString("DVT"), (int) Double.parseDouble(rs.getString("SoLuong")),rs.getString("MaNCC"),rs.getString("TenNCC"),rs.getString("MoTa"),(double) Double.parseDouble(rs.getString("LoiNhuanBien")));
                ListSP.add(itemSP);
                Vector v = new Vector();
                v.add(itemSP.getMaSP());
                v.add(itemSP.getTenSP());
                v.add(itemSP.getTenNCC());
                v.add(itemSP.getTenLoaiSP());
                model.addRow(v);
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại FormQuanLiDonDatHang.HienThiDanhSachDonDatHang: "+ex.getMessage());
        }
        finally{
            CtrlQLSP.CloseConnection();
        }
        jTbDSSP.changeSelection(0,0,false,false);
    }
    public void LoadComboboxNhaCungCap(){
        ListComboboxNCC.clear();
        jComboBox2.removeAllItems();
        jComboBox2.addItem("---Chọn nhà cung cấp---");
        ListComboboxNCC.add("");
        ResultSet rs=CtrlQLSP.LayDanhSachNhaCungCap();
        try{
            while(rs.next()){
                jComboBox2.addItem(rs.getString("TenNCC"));
                ListComboboxNCC.add(rs.getString("MaNCC"));
            }
        }
        catch(SQLException ex){
            System.out.println("Ngoại lệ tại FormQuanLiSanPham.LoadComboboxNhaCungCap: "+ex.getMessage());
        }
        finally{
            CtrlQLSP.CloseConnection();
        }
    }
    public void LoadComboboxLoaiSP(){
        ListComboboxLoaiSP.clear();
        jComboBox1.removeAllItems();
        jComboBox1.addItem("---Chọn loại sản phẩm---");
        ListComboboxLoaiSP.add("");
        ResultSet rs=CtrlQLSP.LayDanhSachLoaiSanPham();
        try{
            while(rs.next()){
                jComboBox1.addItem(rs.getString("TenLoaiSP"));
                ListComboboxLoaiSP.add(rs.getString("MaLoaiSP"));
            }
        }
        catch(SQLException ex){
            System.out.println("Ngoại lệ tại FormQuanLiSanPham.LoadComboboxLoaiSP: "+ex.getMessage());
        }
        finally{
            CtrlQLSP.CloseConnection();
        }
    }
    public void Binding(){
        TableModel model =jTbDSSP.getModel();
        try{
            int viewRow = jTbDSSP.getSelectedRow();
            int modelRow= jTbDSSP.convertRowIndexToModel(viewRow);
             if(viewRow>-1){
                jTextField2.setText(ListSP.get(modelRow).getMaSP());
                jTextField1.setText(ListSP.get(modelRow).getTenSP());
                jTextField3.setText(String.format("%,d",ListSP.get(modelRow).getGiaSi()));
                jTextField4.setText(String.format("%,d",ListSP.get(modelRow).getGiaLe()));   
                jTextField5.setText(String.format("%,d",ListSP.get(modelRow).getSoLuong()));
                jTextField6.setText(ListSP.get(modelRow).getDVT());
                jTextField7.setText(String.format("%,f",ListSP.get(modelRow).getLoiNhuanBien()));
                jTextField8.setText(ListSP.get(modelRow).getMoTa());
                jComboBox2.setSelectedItem(ListSP.get(modelRow).getTenNCC());
                jComboBox1.setSelectedItem(ListSP.get(modelRow).getTenLoaiSP());
             }
        }
        catch(Exception ex){
            System.out.println("Ngoại lệ tại FormQuanLiDonDatHang.Binding: "+ex.getMessage());
        }
    }
    public void LoadForm()
    {
        HienThiDanhSachSanPham(CtrlQLSP.LayDSSanPham());
        Binding();
        LoadComboboxNhaCungCap();
        LoadComboboxLoaiSP();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBtnBack = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jBtnLamMoi = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jBtnTimKiem = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jBtnThem = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jBtnXoa = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jBtnSua = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jBtnLuu = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jBtnHuy = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jPnThongtinSP = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrDSSP = new javax.swing.JScrollPane();
        jTbDSSP = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPnDSSP = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jPnTracuuSP = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
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

        jBtnLamMoi.setBackground(new java.awt.Color(153, 153, 153));
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

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Refresh_45px.png"))); // NOI18N

        javax.swing.GroupLayout jBtnLamMoiLayout = new javax.swing.GroupLayout(jBtnLamMoi);
        jBtnLamMoi.setLayout(jBtnLamMoiLayout);
        jBtnLamMoiLayout.setHorizontalGroup(
            jBtnLamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jBtnLamMoiLayout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(37, 37, 37))
        );
        jBtnLamMoiLayout.setVerticalGroup(
            jBtnLamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 107, 120, 65));

        jBtnTimKiem.setBackground(new java.awt.Color(153, 153, 153));
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

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Search_45px.png"))); // NOI18N

        javax.swing.GroupLayout jBtnTimKiemLayout = new javax.swing.GroupLayout(jBtnTimKiem);
        jBtnTimKiem.setLayout(jBtnTimKiemLayout);
        jBtnTimKiemLayout.setHorizontalGroup(
            jBtnTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnTimKiemLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jBtnTimKiemLayout.setVerticalGroup(
            jBtnTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 107, 120, 65));

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

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Plus_Math_55px.png"))); // NOI18N

        javax.swing.GroupLayout jBtnThemLayout = new javax.swing.GroupLayout(jBtnThem);
        jBtnThem.setLayout(jBtnThemLayout);
        jBtnThemLayout.setHorizontalGroup(
            jBtnThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnThemLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel24)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jBtnThemLayout.setVerticalGroup(
            jBtnThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 650, -1, -1));

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

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Subtract_55px.png"))); // NOI18N

        javax.swing.GroupLayout jBtnXoaLayout = new javax.swing.GroupLayout(jBtnXoa);
        jBtnXoa.setLayout(jBtnXoaLayout);
        jBtnXoaLayout.setHorizontalGroup(
            jBtnXoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnXoaLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel25)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jBtnXoaLayout.setVerticalGroup(
            jBtnXoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnXoaLayout.createSequentialGroup()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        getContentPane().add(jBtnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 650, -1, -1));

        jBtnSua.setBackground(new java.awt.Color(204, 204, 204));
        jBtnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnSuaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnSuaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnSuaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBtnSuaMouseReleased(evt);
            }
        });

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Edit_48px_1.png"))); // NOI18N

        javax.swing.GroupLayout jBtnSuaLayout = new javax.swing.GroupLayout(jBtnSua);
        jBtnSua.setLayout(jBtnSuaLayout);
        jBtnSuaLayout.setHorizontalGroup(
            jBtnSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnSuaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jBtnSuaLayout.setVerticalGroup(
            jBtnSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnSuaLayout.createSequentialGroup()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        getContentPane().add(jBtnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 650, -1, -1));

        jBtnLuu.setBackground(new java.awt.Color(204, 204, 204));
        jBtnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnLuuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnLuuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnLuuMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBtnLuuMouseReleased(evt);
            }
        });

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Save_55px.png"))); // NOI18N

        javax.swing.GroupLayout jBtnLuuLayout = new javax.swing.GroupLayout(jBtnLuu);
        jBtnLuu.setLayout(jBtnLuuLayout);
        jBtnLuuLayout.setHorizontalGroup(
            jBtnLuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnLuuLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel27)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jBtnLuuLayout.setVerticalGroup(
            jBtnLuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnLuuLayout.createSequentialGroup()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        getContentPane().add(jBtnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 650, -1, -1));

        jBtnHuy.setBackground(new java.awt.Color(204, 204, 204));
        jBtnHuy.addMouseListener(new java.awt.event.MouseAdapter() {
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

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Reply_Arrow_64px_1.png"))); // NOI18N

        javax.swing.GroupLayout jBtnHuyLayout = new javax.swing.GroupLayout(jBtnHuy);
        jBtnHuy.setLayout(jBtnHuyLayout);
        jBtnHuyLayout.setHorizontalGroup(
            jBtnHuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnHuyLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jBtnHuyLayout.setVerticalGroup(
            jBtnHuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnHuyLayout.createSequentialGroup()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        getContentPane().add(jBtnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 650, -1, -1));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setText("Tên sản phẩm");

        jLabel3.setText("Mã sản phẩm");

        jLabel4.setText("Giá sĩ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("VND");

        jLabel8.setText("Số lượng");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel9.setText("Đơn vị tính");

        jLabel10.setText("Phân loại");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setFocusable(false);

        jLabel11.setText("Nhà sản xuất");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setFocusable(false);

        jLabel12.setText("Lợi nhuận biên");

        jLabel13.setText("Mô tả");

        jPnThongtinSP.setBackground(new java.awt.Color(0, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Thông tin sản phẩm:");

        javax.swing.GroupLayout jPnThongtinSPLayout = new javax.swing.GroupLayout(jPnThongtinSP);
        jPnThongtinSP.setLayout(jPnThongtinSPLayout);
        jPnThongtinSPLayout.setHorizontalGroup(
            jPnThongtinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnThongtinSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPnThongtinSPLayout.setVerticalGroup(
            jPnThongtinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnThongtinSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel5.setText("Giá lẻ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("VND");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPnThongtinSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox2, 0, 356, Short.MAX_VALUE)
                                .addComponent(jTextField7)))))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPnThongtinSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 50, 540, 590));

        jTbDSSP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTbDSSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Nhà sản xuất", "Loại sản phẩm"
            }
        ));
        jTbDSSP.setRowHeight(25);
        jTbDSSP.setSelectionForeground(new java.awt.Color(255, 51, 0));
        jTbDSSP.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTbDSSP.getTableHeader().setReorderingAllowed(false);
        jTbDSSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTbDSSPMouseClicked(evt);
            }
        });
        jScrDSSP.setViewportView(jTbDSSP);
        if (jTbDSSP.getColumnModel().getColumnCount() > 0) {
            jTbDSSP.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTbDSSP.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTbDSSP.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTbDSSP.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        getContentPane().add(jScrDSSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 295, 770, 420));

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        jPnDSSP.setBackground(new java.awt.Color(0, 204, 204));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Danh sách sản phẩm:");

        javax.swing.GroupLayout jPnDSSPLayout = new javax.swing.GroupLayout(jPnDSSP);
        jPnDSSP.setLayout(jPnDSSPLayout);
        jPnDSSPLayout.setHorizontalGroup(
            jPnDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnDSSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addContainerGap(640, Short.MAX_VALUE))
        );
        jPnDSSPLayout.setVerticalGroup(
            jPnDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnDSSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPnDSSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPnDSSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(434, 434, 434))
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 250, 780, 470));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel15.setText("Nhà cung cấp:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setFocusable(false);

        jLabel16.setText("Loại sản phẩm:");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.setFocusable(false);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.setFocusable(false);

        jLabel17.setText("Tìm theo:");

        jPnTracuuSP.setBackground(new java.awt.Color(0, 204, 204));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Tra cứu sản phẩm:");

        javax.swing.GroupLayout jPnTracuuSPLayout = new javax.swing.GroupLayout(jPnTracuuSP);
        jPnTracuuSP.setLayout(jPnTracuuSPLayout);
        jPnTracuuSPLayout.setHorizontalGroup(
            jPnTracuuSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTracuuSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(657, Short.MAX_VALUE))
        );
        jPnTracuuSPLayout.setVerticalGroup(
            jPnTracuuSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTracuuSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel18.setText("Từ tìm kiếm:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jPnTracuuSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPnTracuuSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 780, 190));

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 728));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/pexels-photo-530024.jpeg"))); // NOI18N
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 728));

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

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

    private void jBtnLamMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLamMoiMouseClicked
        // TODO add your handling code here:
        //        try {
            //            // TODO add your handling code here:
            //            ResultSet rs =BSP.searchByPropertiesWithFulltext(jtxtTimKiemMaSP.getText(),jtxtTimKiemTenSP.getText(),jCbbLoaiSP.getSelectedItem().toString());
            //            if(rs.getRow()==0){
                //                rs =BSP.searchByPropertiesNormal(jtxtTimKiemMaSP.getText(),jtxtTimKiemTenSP.getText(),jCbbLoaiSP.getSelectedItem().toString());
                //            }
            //            displayData(rs);
            //            Binding();
            //        } catch (SQLException ex) {
            //            Logger.getLogger(FrmQuanLiSanPham.class.getName()).log(Level.SEVERE, null, ex);
            //        }
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

    private void jBtnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnTimKiemMouseClicked

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

    private void jBtnSuaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSuaMouseEntered
        // TODO add your handling code here:
        setColor(jBtnSua);
    }//GEN-LAST:event_jBtnSuaMouseEntered

    private void jBtnSuaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSuaMouseExited
        // TODO add your handling code here:
        resetColor(jBtnSua);
    }//GEN-LAST:event_jBtnSuaMouseExited

    private void jBtnSuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSuaMousePressed
        // TODO add your handling code here:
        resetColor(jBtnSua);
    }//GEN-LAST:event_jBtnSuaMousePressed

    private void jBtnSuaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSuaMouseReleased
        // TODO add your handling code here:
        setColor(jBtnSua);
    }//GEN-LAST:event_jBtnSuaMouseReleased

    private void jBtnLuuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLuuMouseEntered
        // TODO add your handling code here:
        setColor(jBtnLuu);
    }//GEN-LAST:event_jBtnLuuMouseEntered

    private void jBtnLuuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLuuMouseExited
        // TODO add your handling code here:
        resetColor(jBtnLuu);
    }//GEN-LAST:event_jBtnLuuMouseExited

    private void jBtnLuuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLuuMousePressed
        // TODO add your handling code here:
        resetColor(jBtnLuu);
    }//GEN-LAST:event_jBtnLuuMousePressed

    private void jBtnLuuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLuuMouseReleased
        // TODO add your handling code here:
        setColor(jBtnLuu);
    }//GEN-LAST:event_jBtnLuuMouseReleased

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

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTbDSSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTbDSSPMouseClicked
        // TODO add your handling code here:
        if(jTbDSSP.getSelectedRow()>=0)
            Binding();
    }//GEN-LAST:event_jTbDSSPMouseClicked

    private void jBtnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnThemMouseClicked
        if(jBtnThem.isEnabled()){
            jTextField2.setText(CtrlQLSP.LayMaSP());
            jTextField1.setText("");
            jTextField3.setText("");
            jTextField4.setText("");   
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField7.setText("");
            jTextField8.setText("");
            jComboBox2.setSelectedIndex(0);
            jComboBox1.setSelectedIndex(0);
            
            flag=1;
        }                 // TODO add your handling code here:
    }//GEN-LAST:event_jBtnThemMouseClicked

    private void jBtnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXoaMouseClicked
        if(jBtnXoa.isEnabled()){
            //ObjSP = new ObjSanPham(jTextField2.getText(),jTextField1.getText(),jTextField3.toString(),(int)Double.parseDouble(jTextField4.getText()),(int)Double.parseDouble(jTextField5.getText()),jTextField6.getText(),jComboBox1.toString(),jComboBox2.toString(),jTextField8.getText(),(double)Double.parseDouble(jTextField7.getText()));
            int i = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa sản phẩm \"" + ObjSP.getTenSP() + "\"", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(i==0){
                try{
                if(ModSP.Delete(ObjSP.getMaSP()))
                    {
                        //EnableComponent(false);
                        JOptionPane.showMessageDialog(this, "Xóa sản phẩm \"" + ObjSP.getTenSP() + "\" thành công." , "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        HienThiDanhSachSanPham(CtrlQLSP.LayDSSanPham());
                    }

                }catch(Exception e){
                    JOptionPane.showMessageDialog(this, "Xóa loại sản phẩm \"" + ObjSP.getTenLoaiSP() + "\" thất bại. Mã lỗi: " + e.getMessage(), "Thông báo ", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jBtnXoaMouseClicked

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
            java.util.logging.Logger.getLogger(FormQuanLiSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormQuanLiSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormQuanLiSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormQuanLiSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new FormQuanLiSanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jBtnBack;
    private javax.swing.JPanel jBtnHuy;
    private javax.swing.JPanel jBtnLamMoi;
    private javax.swing.JPanel jBtnLuu;
    private javax.swing.JPanel jBtnSua;
    private javax.swing.JPanel jBtnThem;
    private javax.swing.JPanel jBtnTimKiem;
    private javax.swing.JPanel jBtnXoa;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPnDSSP;
    private javax.swing.JPanel jPnThongtinSP;
    private javax.swing.JPanel jPnTracuuSP;
    private javax.swing.JScrollPane jScrDSSP;
    private javax.swing.JTable jTbDSSP;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}

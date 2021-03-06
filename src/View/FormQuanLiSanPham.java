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
import java.awt.event.KeyEvent;
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
        
        JPanel ListButton[]=new JPanel[]{jBtnBack,jBtnHuy,jBtnLamMoi,jBtnLuu,jBtnSua,jBtnThem,jBtnTimKiem,jBtnXoa,jBtnXemDSSPDuoiMucTK};
        editFrm.MakeTransparentButton(ListButton);
        
        editFrm.MakeTransparentTable(jScrDSSP, jTbDSSP);
        jtxtMoTa.setLineWrap(true);
        LoadForm();
    }
    public void HienThiDanhSachSanPham(ResultSet rs){
        ListSP.clear();
        DefaultTableModel model;
        model=(DefaultTableModel) jTbDSSP.getModel();
        model.getDataVector().removeAllElements(); 
        model.fireTableDataChanged();
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
        jcbbNCC.removeAllItems();
        jcbbTimTheoNCC.removeAllItems();
        jcbbNCC.addItem("---Chọn nhà cung cấp---");
        jcbbTimTheoNCC.addItem("---Chọn nhà cung cấp---");
        ListComboboxNCC.add("");
        ResultSet rs=CtrlQLSP.LayDanhSachNhaCungCap();
        try{
            while(rs.next()){
                jcbbNCC.addItem(rs.getString("TenNCC"));
                jcbbTimTheoNCC.addItem(rs.getString("TenNCC"));
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
        jcbbTimTheoLoaiSP.removeAllItems();
        jcbbPhanLoai.removeAllItems();
        jcbbTimTheoLoaiSP.addItem("---Chọn loại sản phẩm---");
        jcbbPhanLoai.addItem("---Chọn loại sản phẩm---");
        ListComboboxLoaiSP.add("");
        ResultSet rs=CtrlQLSP.LayDanhSachLoaiSanPham();
        try{
            while(rs.next()){
                jcbbTimTheoLoaiSP.addItem(rs.getString("TenLoaiSP"));
                jcbbPhanLoai.addItem(rs.getString("TenLoaiSP"));
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
                jtxtMaSP.setText(ListSP.get(modelRow).getMaSP());
                jtxtTenSP.setText(ListSP.get(modelRow).getTenSP());
                jtxtGiaSi.setText(String.format("%,d",ListSP.get(modelRow).getGiaSi()));
                jtxtGiaLe.setText(String.format("%,d",ListSP.get(modelRow).getGiaLe()));   
                jtxtSoLuong.setText(String.format("%,d",ListSP.get(modelRow).getSoLuong()));
                jtxtDVT.setText(ListSP.get(modelRow).getDVT());
                jtxtLoiNhuanBien.setText(String.format("%,f",ListSP.get(modelRow).getLoiNhuanBien()));
                jtxtMoTa.setText(ListSP.get(modelRow).getMoTa());
                jcbbNCC.setSelectedItem(ListSP.get(modelRow).getTenNCC());
                jcbbPhanLoai.setSelectedItem(ListSP.get(modelRow).getTenLoaiSP());
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
        SetVisibleButton(true);
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
        jBtnXemDSSPDuoiMucTK = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
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
        jBtnThem1 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jBtnXoa1 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jBtnSua1 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jBtnLuu1 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jBtnHuy1 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtxtTenSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtxtMaSP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtxtGiaSi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtxtSoLuong = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jtxtDVT = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jcbbPhanLoai = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jcbbNCC = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jtxtLoiNhuanBien = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPnThongtinSP = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtxtGiaLe = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtMoTa = new javax.swing.JTextArea();
        jScrDSSP = new javax.swing.JScrollPane();
        jTbDSSP = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPnDSSP = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jcbbTimTheoNCC = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jcbbTimTheoLoaiSP = new javax.swing.JComboBox<>();
        jcbbTimtheo = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jtxtTimKiem = new javax.swing.JTextField();
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

        jBtnXemDSSPDuoiMucTK.setBackground(new java.awt.Color(204, 204, 204));
        jBtnXemDSSPDuoiMucTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnXemDSSPDuoiMucTKMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnXemDSSPDuoiMucTKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnXemDSSPDuoiMucTKMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnXemDSSPDuoiMucTKMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBtnXemDSSPDuoiMucTKMouseReleased(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Xem danh sach sản phẩm dưới mức tồn");

        javax.swing.GroupLayout jBtnXemDSSPDuoiMucTKLayout = new javax.swing.GroupLayout(jBtnXemDSSPDuoiMucTK);
        jBtnXemDSSPDuoiMucTK.setLayout(jBtnXemDSSPDuoiMucTKLayout);
        jBtnXemDSSPDuoiMucTKLayout.setHorizontalGroup(
            jBtnXemDSSPDuoiMucTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jBtnXemDSSPDuoiMucTKLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jBtnXemDSSPDuoiMucTKLayout.setVerticalGroup(
            jBtnXemDSSPDuoiMucTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnXemDSSPDuoiMucTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 250, 260, 40));

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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnSuaMouseClicked(evt);
            }
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnLuuMouseClicked(evt);
            }
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

        jBtnThem1.setBackground(new java.awt.Color(204, 204, 204));
        jBtnThem1.setOpaque(false);
        jBtnThem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnThem1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnThem1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnThem1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnThem1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBtnThem1MouseReleased(evt);
            }
        });

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Plus_Math_55px.png"))); // NOI18N
        jLabel29.setEnabled(false);

        javax.swing.GroupLayout jBtnThem1Layout = new javax.swing.GroupLayout(jBtnThem1);
        jBtnThem1.setLayout(jBtnThem1Layout);
        jBtnThem1Layout.setHorizontalGroup(
            jBtnThem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnThem1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel29)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jBtnThem1Layout.setVerticalGroup(
            jBtnThem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnThem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 650, -1, -1));

        jBtnXoa1.setBackground(new java.awt.Color(204, 204, 204));
        jBtnXoa1.setOpaque(false);
        jBtnXoa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnXoa1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnXoa1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnXoa1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnXoa1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBtnXoa1MouseReleased(evt);
            }
        });

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Subtract_55px.png"))); // NOI18N
        jLabel30.setEnabled(false);

        javax.swing.GroupLayout jBtnXoa1Layout = new javax.swing.GroupLayout(jBtnXoa1);
        jBtnXoa1.setLayout(jBtnXoa1Layout);
        jBtnXoa1Layout.setHorizontalGroup(
            jBtnXoa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnXoa1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel30)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jBtnXoa1Layout.setVerticalGroup(
            jBtnXoa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnXoa1Layout.createSequentialGroup()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        getContentPane().add(jBtnXoa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 650, -1, -1));

        jBtnSua1.setBackground(new java.awt.Color(204, 204, 204));
        jBtnSua1.setOpaque(false);
        jBtnSua1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnSua1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnSua1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnSua1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBtnSua1MouseReleased(evt);
            }
        });

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Edit_48px_1.png"))); // NOI18N
        jLabel31.setEnabled(false);

        javax.swing.GroupLayout jBtnSua1Layout = new javax.swing.GroupLayout(jBtnSua1);
        jBtnSua1.setLayout(jBtnSua1Layout);
        jBtnSua1Layout.setHorizontalGroup(
            jBtnSua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnSua1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jBtnSua1Layout.setVerticalGroup(
            jBtnSua1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnSua1Layout.createSequentialGroup()
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        getContentPane().add(jBtnSua1, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 650, -1, -1));

        jBtnLuu1.setBackground(new java.awt.Color(204, 204, 204));
        jBtnLuu1.setOpaque(false);
        jBtnLuu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnLuu1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnLuu1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnLuu1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBtnLuu1MouseReleased(evt);
            }
        });

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Save_55px.png"))); // NOI18N
        jLabel32.setEnabled(false);

        javax.swing.GroupLayout jBtnLuu1Layout = new javax.swing.GroupLayout(jBtnLuu1);
        jBtnLuu1.setLayout(jBtnLuu1Layout);
        jBtnLuu1Layout.setHorizontalGroup(
            jBtnLuu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnLuu1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel32)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jBtnLuu1Layout.setVerticalGroup(
            jBtnLuu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnLuu1Layout.createSequentialGroup()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        getContentPane().add(jBtnLuu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 650, -1, -1));

        jBtnHuy1.setBackground(new java.awt.Color(204, 204, 204));
        jBtnHuy1.setOpaque(false);
        jBtnHuy1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnHuy1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnHuy1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnHuy1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBtnHuy1MouseReleased(evt);
            }
        });

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Reply_Arrow_64px_1.png"))); // NOI18N
        jLabel33.setEnabled(false);

        javax.swing.GroupLayout jBtnHuy1Layout = new javax.swing.GroupLayout(jBtnHuy1);
        jBtnHuy1.setLayout(jBtnHuy1Layout);
        jBtnHuy1Layout.setHorizontalGroup(
            jBtnHuy1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnHuy1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jBtnHuy1Layout.setVerticalGroup(
            jBtnHuy1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnHuy1Layout.createSequentialGroup()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        getContentPane().add(jBtnHuy1, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 650, -1, -1));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setText("Tên sản phẩm");

        jtxtTenSP.setEditable(false);

        jLabel3.setText("Mã sản phẩm");

        jtxtMaSP.setEditable(false);

        jLabel4.setText("Giá sĩ");

        jtxtGiaSi.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("VND");

        jLabel8.setText("Số lượng");

        jtxtSoLuong.setEditable(false);
        jtxtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtSoLuongActionPerformed(evt);
            }
        });

        jLabel9.setText("Đơn vị tính");

        jtxtDVT.setEditable(false);

        jLabel10.setText("Phân loại");

        jcbbPhanLoai.setEditable(true);
        jcbbPhanLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbbPhanLoai.setEnabled(false);
        jcbbPhanLoai.setFocusable(false);

        jLabel11.setText("Nhà sản xuất");

        jcbbNCC.setEditable(true);
        jcbbNCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbbNCC.setEnabled(false);
        jcbbNCC.setFocusable(false);

        jLabel12.setText("Lợi nhuận biên");

        jtxtLoiNhuanBien.setEditable(false);

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

        jtxtGiaLe.setEditable(false);
        jtxtGiaLe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtGiaLeActionPerformed(evt);
            }
        });

        jLabel5.setText("Giá lẻ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("VND");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jtxtMoTa.setColumns(20);
        jtxtMoTa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jtxtMoTa.setRows(5);
        jScrollPane1.setViewportView(jtxtMoTa);

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
                            .addComponent(jtxtTenSP)
                            .addComponent(jtxtMaSP)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jtxtGiaLe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                    .addComponent(jtxtGiaSi, javax.swing.GroupLayout.Alignment.LEADING))
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
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtxtDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbbPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbbNCC, 0, 356, Short.MAX_VALUE)
                            .addComponent(jtxtLoiNhuanBien)
                            .addComponent(jScrollPane1))))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPnThongtinSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtGiaSi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtxtGiaLe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jtxtDVT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jcbbPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jcbbNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jtxtLoiNhuanBien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 50, 540, 590));

        jTbDSSP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTbDSSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Nhà sản xuất", "Loại sản phẩm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTbDSSP.setFocusable(false);
        jTbDSSP.setRowHeight(25);
        jTbDSSP.setSelectionForeground(new java.awt.Color(255, 51, 0));
        jTbDSSP.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
                .addContainerGap(381, Short.MAX_VALUE))
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
                .addGap(0, 259, Short.MAX_VALUE))
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

        jcbbTimTheoNCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbbTimTheoNCC.setFocusable(false);

        jLabel16.setText("Loại sản phẩm:");

        jcbbTimTheoLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbbTimTheoLoaiSP.setFocusable(false);

        jcbbTimtheo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên sản phẩm", "Mã sản phẩm" }));
        jcbbTimtheo.setFocusable(false);

        jLabel17.setText("Tìm theo:");

        jtxtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtTimKiemKeyPressed(evt);
            }
        });

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
                        .addComponent(jtxtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbbTimTheoNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbbTimTheoLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbbTimtheo, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jcbbTimTheoNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jcbbTimTheoLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbbTimtheo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 780, 190));

        jPanel9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel9MouseDragged(evt);
            }
        });
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel9MousePressed(evt);
            }
        });
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 728));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/pexels-photo-530024.jpeg"))); // NOI18N
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 728));

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    private void jtxtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtSoLuongActionPerformed

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
        jcbbTimTheoLoaiSP.setSelectedIndex(0);
        jcbbTimTheoNCC.setSelectedIndex(0);
        jtxtTimKiem.setText("");
        HienThiDanhSachSanPham(CtrlQLSP.LayDSSanPham());
        Binding();
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

        if (jcbbTimtheo.getSelectedIndex() == 1) {
            HienThiDanhSachSanPham(CtrlQLSP.SearchByID(jtxtTimKiem.getText(), ListComboboxNCC.get(jcbbTimTheoNCC.getSelectedIndex()), ListComboboxLoaiSP.get(jcbbTimTheoLoaiSP.getSelectedIndex())));
        } else {
            HienThiDanhSachSanPham(CtrlQLSP.SearchByName(jtxtTimKiem.getText(), ListComboboxNCC.get(jcbbTimTheoNCC.getSelectedIndex()), ListComboboxLoaiSP.get(jcbbTimTheoLoaiSP.getSelectedIndex())));
        }
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

    private void jtxtGiaLeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtGiaLeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtGiaLeActionPerformed

    private void jTbDSSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTbDSSPMouseClicked
        // TODO add your handling code here:
        if(jTbDSSP.getSelectedRow()>=0&&jTbDSSP.isEnabled())
            Binding();
    }//GEN-LAST:event_jTbDSSPMouseClicked

    public void Reset()
    {
        jtxtMaSP.setText(CtrlQLSP.LayMaSP());
        jtxtTenSP.setText("");
        jtxtGiaSi.setText("");
        jtxtGiaLe.setText("");
        jtxtSoLuong.setText("");
        jtxtDVT.setText("");
        jtxtLoiNhuanBien.setText("");
        jtxtMoTa.setText("");
        jcbbNCC.setSelectedIndex(0);
        jcbbPhanLoai.setSelectedIndex(0);
    }    
    
    public void Edit(boolean active){
        jtxtMaSP.setEditable(active);
        jtxtTenSP.setEditable(active);
        jtxtGiaSi.setEditable(active);
        jtxtGiaLe.setEditable(active);
        jtxtSoLuong.setEditable(active);
        jtxtDVT.setEditable(active);
        jtxtLoiNhuanBien.setEditable(active);
        jtxtMoTa.setEditable(active);
        jcbbNCC.setEnabled(active);
        jcbbPhanLoai.setEnabled(active);
        jTbDSSP.setEnabled(!active);
    }
    
    public void SetVisibleButton(boolean active){
        jBtnThem.setVisible(active);
        jBtnXoa.setVisible(active);
        jBtnSua.setVisible(active);
        jBtnLuu.setVisible(!active);
        jBtnHuy.setVisible(!active);
        
    }
    
    private void jBtnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnThemMouseClicked
        if(jBtnThem.isEnabled()){
            flag=1;
            Reset();
            Edit(true);
            SetVisibleButton(false); 
        }                 // TODO add your handling code here:
    }//GEN-LAST:event_jBtnThemMouseClicked

    private void jBtnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXoaMouseClicked
        if(jBtnXoa.isEnabled()){
            ObjSP = new ObjSanPham(jtxtMaSP.getText(),jtxtTenSP.getText());
            int i = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa sản phẩm \"" + ObjSP.getTenSP() + "\"", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(i==0){
                try{
                if(ModSP.Delete(ObjSP.getMaSP()))
                    {
                        JOptionPane.showMessageDialog(this, "Xóa sản phẩm \"" + ObjSP.getTenSP() + "\" thành công." , "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        HienThiDanhSachSanPham(CtrlQLSP.LayDSSanPham());
                    }

                }catch(Exception e){
                    JOptionPane.showMessageDialog(this, "Xóa sản phẩm \"" + ObjSP.getTenSP() + "\" thất bại. Mã lỗi: " + e.getMessage(), "Thông báo ", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jBtnXoaMouseClicked

    private void jBtnThem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnThem1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnThem1MouseClicked

    private void jBtnThem1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnThem1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnThem1MouseEntered

    private void jBtnThem1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnThem1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnThem1MouseExited

    private void jBtnThem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnThem1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnThem1MousePressed

    private void jBtnThem1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnThem1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnThem1MouseReleased

    private void jBtnXoa1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXoa1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnXoa1MouseClicked

    private void jBtnXoa1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXoa1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnXoa1MouseEntered

    private void jBtnXoa1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXoa1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnXoa1MouseExited

    private void jBtnXoa1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXoa1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnXoa1MousePressed

    private void jBtnXoa1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXoa1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnXoa1MouseReleased

    private void jBtnSua1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSua1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnSua1MouseEntered

    private void jBtnSua1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSua1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnSua1MouseExited

    private void jBtnSua1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSua1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnSua1MousePressed

    private void jBtnSua1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSua1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnSua1MouseReleased

    private void jBtnLuu1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLuu1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnLuu1MouseEntered

    private void jBtnLuu1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLuu1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnLuu1MouseExited

    private void jBtnLuu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLuu1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnLuu1MousePressed

    private void jBtnLuu1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLuu1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnLuu1MouseReleased

    private void jBtnHuy1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnHuy1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnHuy1MouseEntered

    private void jBtnHuy1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnHuy1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnHuy1MouseExited

    private void jBtnHuy1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnHuy1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnHuy1MousePressed

    private void jBtnHuy1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnHuy1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnHuy1MouseReleased

    private void jBtnHuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnHuyMouseClicked
        // TODO add your handling code here:
        Edit(false);
        SetVisibleButton(true);
        Binding();
    }//GEN-LAST:event_jBtnHuyMouseClicked

    
    public void Input(ObjSanPham ObjSP){
        ObjSP.setMaSP(jtxtMaSP.getText());
        ObjSP.setTenSP(jtxtTenSP.getText());
        ObjSP.setDVT(jtxtDVT.getText());
        ObjSP.setGiaLe(Integer.parseInt(jtxtGiaLe.getText().replace(",", "")));
        ObjSP.setGiaSi(Integer.parseInt(jtxtGiaSi.getText().replace(",","")));
        ObjSP.setMaNCC(ListComboboxNCC.get(jcbbNCC.getSelectedIndex()));
        ObjSP.setSoLuong(Integer.parseInt(jtxtSoLuong.getText()));
        ObjSP.setMaLoaiSP(ListComboboxLoaiSP.get(jcbbPhanLoai.getSelectedIndex()));
        ObjSP.setLoiNhuanBien(Double.parseDouble(jtxtLoiNhuanBien.getText()));
        ObjSP.setMoTa(jtxtMoTa.getText());
    }
    
    private void jBtnLuuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLuuMouseClicked
        // TODO add your handling code here:
        if (flag == 1) {
            if (jtxtTenSP.getText().equals("") || jcbbNCC.getSelectedIndex() == 0 || jcbbPhanLoai.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin bao gồm : Tên sản phẩm, Nhà cung cấp, Loại sản phẩm.", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Input(ObjSP);
                try {
                    ModSP.Insert(ObjSP);
                    JOptionPane.showMessageDialog(this, "Thêm  sản phẩm \"" + ObjSP.getTenSP() + "\" thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Edit(false);
                    SetVisibleButton(true);
                    HienThiDanhSachSanPham(CtrlQLSP.LayDSSanPham());
                    Binding();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Thêm  sản phẩm \"" + ObjSP.getTenSP() + "\" thất bại. Lỗi: " + ex.getMessage(), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            if (jtxtTenSP.getText().equals("") || jcbbNCC.getSelectedIndex() == 0 || jcbbPhanLoai.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin bao gồm : Tên sản phẩm, Nhà cung cấp, Loại sản phẩm.", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Input(ObjSP);
                try {
                    ModSP.Update(ObjSP);
                    JOptionPane.showMessageDialog(this, "Sản phẩm có mã \"" + ObjSP.getMaSP() + "\"cập nhật thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Edit(false);
                    SetVisibleButton(true);
                    HienThiDanhSachSanPham(CtrlQLSP.LayDSSanPham());
                    Binding();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Sản phẩm có mã \"" + ObjSP.getMaSP() + "\"cập nhật thất bại. Lỗi :" + ex.getMessage(), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jBtnLuuMouseClicked

    private void jBtnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSuaMouseClicked
        // TODO add your handling code here:
        flag=0;
        Edit(true);
        SetVisibleButton(false);
    }//GEN-LAST:event_jBtnSuaMouseClicked

    private void jtxtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtTimKiemKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            jBtnTimKiemMouseClicked(null);
    }//GEN-LAST:event_jtxtTimKiemKeyPressed

    private void jBtnXemDSSPDuoiMucTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXemDSSPDuoiMucTKMouseClicked
        // TODO add your handling code here:
        HienThiDanhSachSanPham(CtrlQLSP.LayDSSanPhamDuoiMucTon());
    }//GEN-LAST:event_jBtnXemDSSPDuoiMucTKMouseClicked

    private void jBtnXemDSSPDuoiMucTKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXemDSSPDuoiMucTKMouseEntered
        // TODO add your handling code here:
        setColor(jBtnXemDSSPDuoiMucTK);
    }//GEN-LAST:event_jBtnXemDSSPDuoiMucTKMouseEntered

    private void jBtnXemDSSPDuoiMucTKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXemDSSPDuoiMucTKMouseExited
        // TODO add your handling code here:
        resetColor(jBtnXemDSSPDuoiMucTK);
    }//GEN-LAST:event_jBtnXemDSSPDuoiMucTKMouseExited

    private void jBtnXemDSSPDuoiMucTKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXemDSSPDuoiMucTKMousePressed
        // TODO add your handling code here:
        resetColor(jBtnXemDSSPDuoiMucTK);
    }//GEN-LAST:event_jBtnXemDSSPDuoiMucTKMousePressed

    private void jBtnXemDSSPDuoiMucTKMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXemDSSPDuoiMucTKMouseReleased
        // TODO add your handling code here:
        setColor(jBtnXemDSSPDuoiMucTK);
    }//GEN-LAST:event_jBtnXemDSSPDuoiMucTKMouseReleased

    int xx,yy;
    
    private void jPanel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MousePressed
        // TODO add your handling code here:
        xx = evt.getX();
        yy = evt.getY();
    }//GEN-LAST:event_jPanel9MousePressed

    private void jPanel9MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - yy);
    }//GEN-LAST:event_jPanel9MouseDragged

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
    private javax.swing.JPanel jBtnHuy1;
    private javax.swing.JPanel jBtnLamMoi;
    private javax.swing.JPanel jBtnLuu;
    private javax.swing.JPanel jBtnLuu1;
    private javax.swing.JPanel jBtnSua;
    private javax.swing.JPanel jBtnSua1;
    private javax.swing.JPanel jBtnThem;
    private javax.swing.JPanel jBtnThem1;
    private javax.swing.JPanel jBtnTimKiem;
    private javax.swing.JPanel jBtnXemDSSPDuoiMucTK;
    private javax.swing.JPanel jBtnXoa;
    private javax.swing.JPanel jBtnXoa1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTbDSSP;
    private javax.swing.JComboBox<String> jcbbNCC;
    private javax.swing.JComboBox<String> jcbbPhanLoai;
    private javax.swing.JComboBox<String> jcbbTimTheoLoaiSP;
    private javax.swing.JComboBox<String> jcbbTimTheoNCC;
    private javax.swing.JComboBox<String> jcbbTimtheo;
    private javax.swing.JTextField jtxtDVT;
    private javax.swing.JTextField jtxtGiaLe;
    private javax.swing.JTextField jtxtGiaSi;
    private javax.swing.JTextField jtxtLoiNhuanBien;
    private javax.swing.JTextField jtxtMaSP;
    private javax.swing.JTextArea jtxtMoTa;
    private javax.swing.JTextField jtxtSoLuong;
    private javax.swing.JTextField jtxtTenSP;
    private javax.swing.JTextField jtxtTimKiem;
    // End of variables declaration//GEN-END:variables
}

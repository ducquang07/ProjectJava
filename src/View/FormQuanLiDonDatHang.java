/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Connect.Connect;
import Control.CtrlLapDonDatHang;
import Edit.Edit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import Object.ObjDonDatHang;
import Model.ModDonDatHang;
import Object.ObjChiTietDDH;
import java.util.ArrayList;
import Control.CtrlQuanLiDonDatHang;
import Model.ModChiTietDDH;
import Object.ObjSanPham;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author ColdBlood
 */
public class FormQuanLiDonDatHang extends javax.swing.JFrame {


    Edit editFrm = new Edit();
    ArrayList<ObjDonDatHang> listDDH = new ArrayList<>();
    ArrayList<ObjSanPham> listSP = new ArrayList<>();
    ObjDonDatHang ObjDDH = new ObjDonDatHang();
    ModDonDatHang ModDDH = new ModDonDatHang();
    CtrlQuanLiDonDatHang CtrlDDH = new CtrlQuanLiDonDatHang();
    ObjChiTietDDH ObjCTDDH = new ObjChiTietDDH();
    ModChiTietDDH ModCTDDH = new ModChiTietDDH();
    CtrlLapDonDatHang CtrlLapDDH = new CtrlLapDonDatHang();
    ArrayList<String> ListComboboxNCC = new ArrayList<>();
    ArrayList<ObjChiTietDDH> listCTDDH = new ArrayList<>();
    private int flag = 0;
    SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy      hh:mm:ss a");

    public FormQuanLiDonDatHang() {
        initComponents();
        setLocationRelativeTo(null);
        
        jPanel1.setBackground((new Color(0,0,0,0)));
        
        JPanel ListPanel[]=new JPanel[]{jPanel4,jPanel5,jPanel7,jPanel9};
        editFrm.MakeTransparentPanel(ListPanel);
        
        JPanel ListTitle[]=new JPanel[]{jPnCTDDH,jPnDSDDH,jPnThongtinDDH,jPnTracuuDDH};
        editFrm.MakeTransparentTitle(ListTitle);
        
        JPanel ListButton[]=new JPanel[]{jBtnLamMoi,jBtnSua,jBtnThem,jBtnTimKiem,jBtnXemPhieuIn,jBtnXoa,jBtnBack};
        editFrm.MakeTransparentButton(ListButton);
        
        editFrm.MakeTransparentTable(jScrCTDDH, jTbCTDDH);
        editFrm.MakeTransparentTable(jScrDSDDH, jTbDSDDH);
        ((DefaultTableCellRenderer) jTbDSDDH.getDefaultRenderer(Object.class)).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        
        jtxtTenNCC.setLineWrap(true);
        LoadForm();
    }
    

    
    public void SetVisibleButton(boolean active){
        jBtnThem.setVisible(active);
        jBtnXoa.setVisible(active);
        jBtnSua.setVisible(active);
        
    }
    public void LoadComboboxNhaCungCap(){
        ListComboboxNCC.clear();
        jcbbNCC.removeAllItems();
        jcbbNCC.addItem("---Chọn nhà cung cấp---");
        ListComboboxNCC.add("");
        ResultSet rs=CtrlDDH.LayDanhSachNhaCungCap();
        try{
            while(rs.next()){
                jcbbNCC.addItem(rs.getString("TenNCC"));
                ListComboboxNCC.add(rs.getString("MaNCC"));
            }
        }
        catch(SQLException ex){
            System.out.println("Ngoại lệ tại FormQuanLiDonDatHang.LoadComboboxNhaCungCap: "+ex.getMessage());
        }
        finally{
            CtrlDDH.CloseConnection();
        }
    }
    public void HienThiDanhSachDonDatHang(ResultSet rs){
        listDDH.clear();
        DefaultTableModel model;
        model=(DefaultTableModel) jTbDSDDH.getModel();
        model.getDataVector().removeAllElements(); 
        model.fireTableDataChanged();
        try{
            while(rs.next()){
                ObjDonDatHang itemDDH =new ObjDonDatHang(rs.getString("MaDDH"),rs.getString("MaNCC"), rs.getString("TenNCC"), rs.getTimestamp("NgayDatHang"), rs.getString("TrangThai"));
                listDDH.add(itemDDH);
                Vector v = new Vector();
                v.add(itemDDH.getMaDDH());
                v.add(dt.format(itemDDH.getNgayDatHang()));
                model.addRow(v);
            }
            jTbDSDDH.changeSelection(0,0,false,false);
            Binding();
            HienThiDanhSachChiTietDDH(jtxtMaDDH.getText());
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại FormQuanLiDonDatHang.HienThiDanhSachDonDatHang: "+ex.getMessage());
        }
        finally{
            CtrlDDH.CloseConnection();
        }
    }
    public void HienThiDanhSachChiTietDDH(String MaDDH){
        listCTDDH.clear();
        DefaultTableModel model;
        model=(DefaultTableModel) jTbCTDDH.getModel();
        model.getDataVector().removeAllElements(); 
        model.fireTableDataChanged();
        try{
            ResultSet rs=CtrlDDH.LayThongTinChiTietDDH(MaDDH);
            while(rs.next()){
                ObjChiTietDDH itemCTDDH;
                itemCTDDH =new ObjChiTietDDH(rs.getString("MaDDH"),rs.getString("MaSP"),rs.getString("TenSP"),Integer.parseInt(rs.getString("SoLuong")));
                listCTDDH.add(itemCTDDH);
                Vector v = new Vector();
                v.add(itemCTDDH.getMaSP());
                v.add(itemCTDDH.getTenSP());
                v.add(itemCTDDH.getSoLuong());
                model.addRow(v);
            }
            
        } catch (Exception ex) {
            System.out.println("Ngoại lệ tại FormQuanLiDonDatHang.HienThiDanhSachChiTietDDH: "+ex.getMessage());
        }
        finally{
            CtrlDDH.CloseConnection();
        }
    }
    public void Binding(){
        try {
            DefaultTableModel model =(DefaultTableModel) jTbDSDDH.getModel();
            int viewRow = jTbDSDDH.getSelectedRow();
            int modelRow = jTbDSDDH.convertRowIndexToModel(viewRow);
            jtxtMaDDH.setText(listDDH.get(modelRow).getMaDDH());
            jtxtMaNCC.setText(listDDH.get(modelRow).getMaNCC());
            jDateNgayDH.setDate(listDDH.get(modelRow).getNgayDatHang());
            jtxtTenNCC.setText(listDDH.get(modelRow).getTenNCC());
            jLbTrangThai.setText(listDDH.get(modelRow).getTrangThai());
        }
        catch(Exception ex){
            System.out.println("Ngoại lệ tại FormQuanLiDonDatHang.Binding: "+ex.getMessage());
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

        jBtnBack = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jBtnTimKiem = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jBtnLamMoi = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jBtnXemPhieuIn = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jBtnThem = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jBtnXoa = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jBtnSua = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLbTrangThai = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jCbbTrangThai = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jcbbNCC = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jtxtTimKiemMaDDH = new javax.swing.JTextField();
        jDateTuNgay = new com.toedter.calendar.JDateChooser();
        jDateDenNgay = new com.toedter.calendar.JDateChooser();
        jPnTracuuDDH = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrDSDDH = new javax.swing.JScrollPane();
        jTbDSDDH = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPnDSDDH = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jtxtMaDDH = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jDateNgayDH = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jtxtMaNCC = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPnThongtinDDH = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtTenNCC = new javax.swing.JTextArea();
        jScrCTDDH = new javax.swing.JScrollPane();
        jTbCTDDH = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPnCTDDH = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
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

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Search_45px.png"))); // NOI18N

        javax.swing.GroupLayout jBtnTimKiemLayout = new javax.swing.GroupLayout(jBtnTimKiem);
        jBtnTimKiem.setLayout(jBtnTimKiemLayout);
        jBtnTimKiemLayout.setHorizontalGroup(
            jBtnTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jBtnTimKiemLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        jBtnTimKiemLayout.setVerticalGroup(
            jBtnTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 230, 150, -1));

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

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Refresh_45px.png"))); // NOI18N

        javax.swing.GroupLayout jBtnLamMoiLayout = new javax.swing.GroupLayout(jBtnLamMoi);
        jBtnLamMoi.setLayout(jBtnLamMoiLayout);
        jBtnLamMoiLayout.setHorizontalGroup(
            jBtnLamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnLamMoiLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel16)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jBtnLamMoiLayout.setVerticalGroup(
            jBtnLamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, 150, 50));

        jBtnXemPhieuIn.setBackground(new java.awt.Color(204, 204, 204));
        jBtnXemPhieuIn.setPreferredSize(new java.awt.Dimension(102, 50));
        jBtnXemPhieuIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnXemPhieuInMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtnXemPhieuInMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtnXemPhieuInMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnXemPhieuInMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBtnXemPhieuInMouseReleased(evt);
            }
        });

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Print_60px_1.png"))); // NOI18N

        javax.swing.GroupLayout jBtnXemPhieuInLayout = new javax.swing.GroupLayout(jBtnXemPhieuIn);
        jBtnXemPhieuIn.setLayout(jBtnXemPhieuInLayout);
        jBtnXemPhieuInLayout.setHorizontalGroup(
            jBtnXemPhieuInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnXemPhieuInLayout.createSequentialGroup()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jBtnXemPhieuInLayout.setVerticalGroup(
            jBtnXemPhieuInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jBtnXemPhieuInLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addGap(21, 21, 21))
        );

        getContentPane().add(jBtnXemPhieuIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 304, 70, 100));

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

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Plus_Math_55px.png"))); // NOI18N

        javax.swing.GroupLayout jBtnThemLayout = new javax.swing.GroupLayout(jBtnThem);
        jBtnThem.setLayout(jBtnThemLayout);
        jBtnThemLayout.setHorizontalGroup(
            jBtnThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
        jBtnThemLayout.setVerticalGroup(
            jBtnThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jBtnThemLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(22, 22, 22))
        );

        getContentPane().add(jBtnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 407, 70, 100));

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

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Subtract_55px.png"))); // NOI18N

        javax.swing.GroupLayout jBtnXoaLayout = new javax.swing.GroupLayout(jBtnXoa);
        jBtnXoa.setLayout(jBtnXoaLayout);
        jBtnXoaLayout.setHorizontalGroup(
            jBtnXoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
        jBtnXoaLayout.setVerticalGroup(
            jBtnXoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jBtnXoaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jBtnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 510, 70, 100));

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

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Edit_48px_1.png"))); // NOI18N

        javax.swing.GroupLayout jBtnSuaLayout = new javax.swing.GroupLayout(jBtnSua);
        jBtnSua.setLayout(jBtnSuaLayout);
        jBtnSuaLayout.setHorizontalGroup(
            jBtnSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
        jBtnSuaLayout.setVerticalGroup(
            jBtnSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnSuaLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        getContentPane().add(jBtnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 613, 70, 100));

        jLbTrangThai.setBackground(new java.awt.Color(204, 204, 204));
        jLbTrangThai.setText("            ");
        getContentPane().add(jLbTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 233, 80, -1));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Từ ngày : ");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 48, -1, 20));

        jLabel3.setText("Đến ngày :");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 48, -1, 20));

        jLabel4.setText("Trạng thái :");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 84, -1, 16));

        jCbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chưa nhận", "Đã nhận" }));
        jCbbTrangThai.setFocusable(false);
        jPanel4.add(jCbbTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 82, 332, -1));

        jLabel5.setText("Nhà cung cấp :");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 116, -1, -1));

        jcbbNCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbbNCC.setFocusable(false);
        jPanel4.add(jcbbNCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 113, 332, -1));

        jLabel7.setText("Mã đặt hàng :");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 147, -1, -1));
        jPanel4.add(jtxtTimKiemMaDDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 144, 332, -1));

        jDateTuNgay.setDateFormatString("dd/MM/yyyy");
        jPanel4.add(jDateTuNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 48, 116, -1));

        jDateDenNgay.setDateFormatString("dd/MM/yyyy");
        jPanel4.add(jDateDenNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(336, 48, 116, -1));

        jPnTracuuDDH.setBackground(new java.awt.Color(0, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Tra cứu đơn đặt hàng:");

        javax.swing.GroupLayout jPnTracuuDDHLayout = new javax.swing.GroupLayout(jPnTracuuDDH);
        jPnTracuuDDH.setLayout(jPnTracuuDDHLayout);
        jPnTracuuDDHLayout.setHorizontalGroup(
            jPnTracuuDDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTracuuDDHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPnTracuuDDHLayout.setVerticalGroup(
            jPnTracuuDDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnTracuuDDHLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel4.add(jPnTracuuDDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 54, 560, 240));

        jTbDSDDH.setAutoCreateRowSorter(true);
        jTbDSDDH.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jTbDSDDH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã đơn đặt hàng", "Ngày đặt hàng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTbDSDDH.setFocusable(false);
        jTbDSDDH.setRowHeight(25);
        jTbDSDDH.setSelectionForeground(new java.awt.Color(255, 51, 0));
        jTbDSDDH.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTbDSDDH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTbDSDDHMouseClicked(evt);
            }
        });
        jScrDSDDH.setViewportView(jTbDSDDH);

        getContentPane().add(jScrDSDDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 338, 550, 370));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setPreferredSize(new java.awt.Dimension(500, 348));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPnDSDDH.setBackground(new java.awt.Color(0, 204, 204));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Danh sách đơn đặt hàng:");

        javax.swing.GroupLayout jPnDSDDHLayout = new javax.swing.GroupLayout(jPnDSDDH);
        jPnDSDDH.setLayout(jPnDSDDHLayout);
        jPnDSDDHLayout.setHorizontalGroup(
            jPnDSDDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnDSDDHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addContainerGap(395, Short.MAX_VALUE))
        );
        jPnDSDDHLayout.setVerticalGroup(
            jPnDSDDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel5.add(jPnDSDDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, -1));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 304, 560, 410));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtxtMaDDH.setEditable(false);
        jPanel7.add(jtxtMaDDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 87, 165, -1));

        jLabel10.setText("Ngày đặt hàng :");
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 130, -1, 20));

        jDateNgayDH.setDateFormatString("dd/MM/yyyy");
        jDateNgayDH.setEnabled(false);
        jPanel7.add(jDateNgayDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 167, -1));

        jLabel11.setText("Trạng thái :");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 173, -1, 28));

        jLabel12.setText("Mã nhà cung cấp :");
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 90, -1, -1));

        jtxtMaNCC.setEditable(false);
        jPanel7.add(jtxtMaNCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 87, 257, -1));

        jLabel13.setText("Tên nhà cung cấp :");
        jPanel7.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 130, -1, 20));

        jLabel9.setText("Mã đơn đặt hàng :");
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 87, 113, 20));

        jPnThongtinDDH.setBackground(new java.awt.Color(0, 204, 204));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Thông tin đơn đặt hàng:");

        javax.swing.GroupLayout jPnThongtinDDHLayout = new javax.swing.GroupLayout(jPnThongtinDDH);
        jPnThongtinDDH.setLayout(jPnThongtinDDHLayout);
        jPnThongtinDDHLayout.setHorizontalGroup(
            jPnThongtinDDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnThongtinDDHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(608, Short.MAX_VALUE))
        );
        jPnThongtinDDHLayout.setVerticalGroup(
            jPnThongtinDDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnThongtinDDHLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        jPanel7.add(jPnThongtinDDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jtxtTenNCC.setEditable(false);
        jtxtTenNCC.setColumns(20);
        jtxtTenNCC.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jtxtTenNCC.setRows(5);
        jScrollPane1.setViewportView(jtxtTenNCC);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 130, 257, 53));

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 54, 770, 240));

        jTbCTDDH.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jTbCTDDH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTbCTDDH.setFocusable(false);
        jTbCTDDH.setRowHeight(25);
        jTbCTDDH.setSelectionForeground(new java.awt.Color(255, 51, 0));
        jTbCTDDH.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTbCTDDH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTbCTDDHMouseClicked(evt);
            }
        });
        jScrCTDDH.setViewportView(jTbCTDDH);
        if (jTbCTDDH.getColumnModel().getColumnCount() > 0) {
            jTbCTDDH.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTbCTDDH.getColumnModel().getColumn(1).setPreferredWidth(400);
            jTbCTDDH.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

        getContentPane().add(jScrCTDDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(665, 338, 680, 370));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPnCTDDH.setBackground(new java.awt.Color(0, 204, 204));
        jPnCTDDH.setPreferredSize(new java.awt.Dimension(172, 37));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Chi tiết đơn đặt hàng:");

        javax.swing.GroupLayout jPnCTDDHLayout = new javax.swing.GroupLayout(jPnCTDDH);
        jPnCTDDH.setLayout(jPnCTDDHLayout);
        jPnCTDDHLayout.setHorizontalGroup(
            jPnCTDDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnCTDDHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addContainerGap(543, Short.MAX_VALUE))
        );
        jPnCTDDHLayout.setVerticalGroup(
            jPnCTDDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnCTDDHLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel9.add(jPnCTDDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 30));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 303, 690, 410));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 720));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/pexels-photo-530024.jpeg"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void LoadForm(){
        jDateTuNgay.setDate(new Date());
        jDateDenNgay.setDate(new Date());
        HienThiDanhSachDonDatHang(CtrlDDH.LayDSDonDatHang());
        LoadComboboxNhaCungCap();   
        SetVisibleButton(true);
    }
    

  
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
        HienThiDanhSachDonDatHang(CtrlDDH.LayDSDonDatHang());
        LoadComboboxNhaCungCap();
        jtxtTimKiemMaDDH.setText("");
        jDateTuNgay.setDate(new Date());
        jDateDenNgay.setDate(new Date());
        jCbbTrangThai.setSelectedIndex(0);
        jcbbNCC.setSelectedIndex(0);
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
        try {
            String status = "";
            if (jCbbTrangThai.getSelectedIndex() != 0) {
                status = jCbbTrangThai.getSelectedItem().toString();
            }
            HienThiDanhSachDonDatHang(CtrlDDH.TimKiemDDHByMaDDH(jtxtTimKiemMaDDH.getText(), ListComboboxNCC.get(jcbbNCC.getSelectedIndex()), status, jDateTuNgay.getDate(), jDateDenNgay.getDate()));
            Binding();
            HienThiDanhSachChiTietDDH(jtxtMaDDH.getText());
        } catch (Exception e) {
            System.out.println("Ngoại lệ tại FormQuanLiDonDatHang.TimKiem: " + e.getMessage());
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

    private void jBtnXemPhieuInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXemPhieuInMouseClicked
        // TODO add your handling code here:
        try {
            Connect con = new Connect();
            con.Connected();
            Hashtable hash = new Hashtable();
            InputStream is = null;
            is = new FileInputStream("src/Report/ReportDonDatHang.jasper");
            hash.put("MaDDH", jtxtMaDDH.getText());
            JasperPrint print = JasperFillManager.fillReport(is, hash, con.getConDB());
            JasperViewer.viewReport(print, false);
        } catch (Exception ex) {
            System.out.println("Ngoại lệ tại FormQuanLiDonDatHang.jBtnXemPhieuInMouseClicked:" + ex.getMessage());
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jBtnXemPhieuInMouseClicked

    private void jBtnXemPhieuInMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXemPhieuInMouseEntered
        // TODO add your handling code here:
        setColor(jBtnXemPhieuIn);
    }//GEN-LAST:event_jBtnXemPhieuInMouseEntered

    private void jBtnXemPhieuInMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXemPhieuInMouseExited
        // TODO add your handling code here:
        resetColor(jBtnXemPhieuIn);
    }//GEN-LAST:event_jBtnXemPhieuInMouseExited

    private void jBtnXemPhieuInMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXemPhieuInMousePressed
        // TODO add your handling code here:
        resetColor(jBtnXemPhieuIn);
    }//GEN-LAST:event_jBtnXemPhieuInMousePressed

    private void jBtnXemPhieuInMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXemPhieuInMouseReleased
        // TODO add your handling code here:
        setColor(jBtnXemPhieuIn);
    }//GEN-LAST:event_jBtnXemPhieuInMouseReleased

    private void jTbCTDDHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTbCTDDHMouseClicked
        // TODO add your handling code here:
        if(jTbCTDDH.getSelectedRow()>=0 && jTbCTDDH.isEnabled())
            Binding();
    }//GEN-LAST:event_jTbCTDDHMouseClicked

    private void jBtnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnThemMouseClicked

        new FormLapDonDatHang().setVisible(true);
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jBtnThemMouseClicked

    private void jTbDSDDHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTbDSDDHMouseClicked
        // TODO add your handling code here:
        if(jTbDSDDH.getSelectedRow()>=0){
            Binding();
            HienThiDanhSachChiTietDDH(jtxtMaDDH.getText());
        }
    }//GEN-LAST:event_jTbDSDDHMouseClicked

    private void jBtnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXoaMouseClicked
        // TODO add your handling code here:
        int dialogButton = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (dialogButton == JOptionPane.YES_OPTION) {
                if (ModDDH.Delete(jtxtMaDDH.getText()) && ModCTDDH.Delete(jtxtMaDDH.getText())) {
                    JOptionPane.showMessageDialog(this, "Đơn đặt hàng " + jtxtMaDDH.getText() + " đã xóa thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    HienThiDanhSachDonDatHang(CtrlDDH.LayDSDonDatHang());
                } else {
                    JOptionPane.showMessageDialog(this, "Đơn đặt hàng " + jtxtMaDDH.getText() + " xóa không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
    }//GEN-LAST:event_jBtnXoaMouseClicked

    private void jBtnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSuaMouseClicked
        // TODO add your handling code here:
        if (jTbDSDDH.getSelectedRow() >= 0) {
            DefaultTableModel model = (DefaultTableModel) jTbDSDDH.getModel();
            int viewRow = jTbDSDDH.getSelectedRow();
            int modelRow = jTbDSDDH.convertRowIndexToModel(viewRow);
            if (listDDH.get(modelRow).getTrangThai().equals("Chưa nhận")) {
                FormLapDonDatHang frmLapDDH = new FormLapDonDatHang(listDDH.get(modelRow), listCTDDH);
                frmLapDDH.ChinhSua = true;
                frmLapDDH.setVisible(true);
                setVisible(true);
                dispose();
            } 
            else {
                JOptionPane.showMessageDialog(this, "Đơn đặt hàng đã nhận hàng không thể chỉnh sửa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jBtnSuaMouseClicked

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
            java.util.logging.Logger.getLogger(FormQuanLiDonDatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormQuanLiDonDatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormQuanLiDonDatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormQuanLiDonDatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FormQuanLiDonDatHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jBtnBack;
    private javax.swing.JPanel jBtnLamMoi;
    private javax.swing.JPanel jBtnSua;
    private javax.swing.JPanel jBtnThem;
    private javax.swing.JPanel jBtnTimKiem;
    private javax.swing.JPanel jBtnXemPhieuIn;
    private javax.swing.JPanel jBtnXoa;
    private javax.swing.JComboBox<String> jCbbTrangThai;
    private com.toedter.calendar.JDateChooser jDateDenNgay;
    private com.toedter.calendar.JDateChooser jDateNgayDH;
    private com.toedter.calendar.JDateChooser jDateTuNgay;
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
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLbTrangThai;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPnCTDDH;
    private javax.swing.JPanel jPnDSDDH;
    private javax.swing.JPanel jPnThongtinDDH;
    private javax.swing.JPanel jPnTracuuDDH;
    private javax.swing.JScrollPane jScrCTDDH;
    private javax.swing.JScrollPane jScrDSDDH;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTbCTDDH;
    private javax.swing.JTable jTbDSDDH;
    private javax.swing.JComboBox<String> jcbbNCC;
    private javax.swing.JTextField jtxtMaDDH;
    private javax.swing.JTextField jtxtMaNCC;
    private javax.swing.JTextArea jtxtTenNCC;
    private javax.swing.JTextField jtxtTimKiemMaDDH;
    // End of variables declaration//GEN-END:variables
}

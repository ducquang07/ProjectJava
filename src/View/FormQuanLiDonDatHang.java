/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Control.CtrlChiTietDonDatHang;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


/**
 *
 * @author ColdBlood
 */
public class FormQuanLiDonDatHang extends javax.swing.JFrame {

    Edit editFrm=new Edit();
        ArrayList<ObjDonDatHang> listDDH = new ArrayList<>();
        ArrayList<ObjSanPham> listSP = new ArrayList<>();
        ObjDonDatHang ObjDDH = new ObjDonDatHang();
        ModDonDatHang ModDDH = new ModDonDatHang();
        CtrlQuanLiDonDatHang CtrlDDH = new CtrlQuanLiDonDatHang();
        ObjChiTietDDH ObjCTDDH = new ObjChiTietDDH();
        ModChiTietDDH ModCTDDH = new ModChiTietDDH();
        CtrlLapDonDatHang CtrlLapDDH = new CtrlLapDonDatHang();
        CtrlChiTietDonDatHang CtrlCTDDH = new CtrlChiTietDonDatHang();
        ArrayList<String> ListComboboxNCC = new ArrayList<>();
        ArrayList<ObjChiTietDDH> listCTDDH = new ArrayList<>();
        private int flag=0;
        
    public FormQuanLiDonDatHang() {
        initComponents();
        setLocationRelativeTo(null);
        
        jPanel1.setBackground((new Color(0,0,0,0)));
        
        JPanel ListPanel[]=new JPanel[]{jPanel4,jPanel5,jPanel7,jPanel9};
        editFrm.MakeTransparentPanel(ListPanel);
        
        JPanel ListTitle[]=new JPanel[]{jPnCTDDH,jPnDSDDH,jPnThongtinDDH,jPnTracuuDDH};
        editFrm.MakeTransparentTitle(ListTitle);
        
        JPanel ListButton[]=new JPanel[]{jBtnHuy,jBtnLamMoi,jBtnLuu,jBtnSua,jBtnThem,jBtnTimKiem,jBtnXemPhieuIn,jBtnXoa,jBtnBack};
        editFrm.MakeTransparentButton(ListButton);
        
        editFrm.MakeTransparentTable(jScrCTDDH, jTbCTDDH);
        editFrm.MakeTransparentTable(jScrDSDDH, jTbDSDDH);
        LoadForm();
    }
    
    public void LoadComboboxNhaCungCap(){
        ListComboboxNCC.clear();
        jComboBox2.removeAllItems();
        jComboBox2.addItem("---Chọn nhà cung cấp---");
        ListComboboxNCC.add("");
        ResultSet rs=CtrlDDH.LayDanhSachNhaCungCap();
        try{
            while(rs.next()){
                jComboBox2.addItem(rs.getString("TenNCC"));
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
        try{
            while(rs.next()){
                ObjDonDatHang itemDDH;
                itemDDH =new ObjDonDatHang(rs.getString("MaDDH"),rs.getString("MaNCC"),rs.getDate("NgayDatHang"),rs.getString("TrangThai"));
                listDDH.add(itemDDH);
                Vector v = new Vector();
                v.add(itemDDH.getMaDDH());
                v.add(itemDDH.getMaNCC());
                v.add(itemDDH.getNgayDatHang());
                v.add(itemDDH.getTrangThai());   
                model.addRow(v);
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại FormQuanLiDonDatHang.HienThiDanhSachDonDatHang: "+ex.getMessage());
        }
        finally{
            CtrlDDH.CloseConnection();
        }
        jTbDSDDH.changeSelection(0,0,false,false);
    }
    public void HienThiDanhSachChiTietDDH(ResultSet rs){
        listCTDDH.clear();
        DefaultTableModel model;
        model=(DefaultTableModel) jTbCTDDH.getModel();
        model.getDataVector().removeAllElements(); 
        try{
            while(rs.next()){
                ObjChiTietDDH itemCTDDH;
                itemCTDDH =new ObjChiTietDDH(rs.getString("MaDDH"),rs.getString("MaSP"),(int)Double.parseDouble(rs.getString("SoLuong")),rs.getString("MaNCC"),rs.getString("TenNCC"),rs.getDate("NgayDatHang"),rs.getString("TrangThai"));
                listCTDDH.add(itemCTDDH);
                Vector v = new Vector();
                v.add(itemCTDDH.getMaDDH());
                v.add(itemCTDDH.getMaSP());
                v.add(itemCTDDH.getSoLuong());
                model.addRow(v);
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại FormQuanLiDonDatHang.HienThiDanhSachChiTietDDH: "+ex.getMessage());
        }
        finally{
            CtrlCTDDH.CloseConnection();
        }
        jTbCTDDH.changeSelection(0,0,false,false);
    }
    public void Binding(){
        TableModel model =jTbCTDDH.getModel();
        try{
            int viewRow = jTbCTDDH.getSelectedRow();
            int modelRow= jTbCTDDH.convertRowIndexToModel(viewRow);
             if(viewRow>-1){
                jTextField2.setText(listCTDDH.get(modelRow).getMaDDH());
                jTextField3.setText(listCTDDH.get(modelRow).getMaNCC());
                jDateChooser3.setDate(listCTDDH.get(modelRow).getNgayDatHang());
                jTextField5.setText(listCTDDH.get(modelRow).getTenNCC());   
             }
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
        jBtnLuu = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jBtnHuy = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jBtnSua = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jBtnXoa = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jBtnThem = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jPnTracuuDDH = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrDSDDH = new javax.swing.JScrollPane();
        jTbDSDDH = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPnDSDDH = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPnThongtinDDH = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrCTDDH = new javax.swing.JScrollPane();
        jTbCTDDH = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPnCTDDH = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

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
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 285, 150, 65));

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
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 285, 150, 65));

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

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Print_60px_1.png"))); // NOI18N

        javax.swing.GroupLayout jBtnXemPhieuInLayout = new javax.swing.GroupLayout(jBtnXemPhieuIn);
        jBtnXemPhieuIn.setLayout(jBtnXemPhieuInLayout);
        jBtnXemPhieuInLayout.setHorizontalGroup(
            jBtnXemPhieuInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnXemPhieuInLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jBtnXemPhieuInLayout.setVerticalGroup(
            jBtnXemPhieuInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnXemPhieuIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 643, 120, 70));

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

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Save_55px.png"))); // NOI18N

        javax.swing.GroupLayout jBtnLuuLayout = new javax.swing.GroupLayout(jBtnLuu);
        jBtnLuu.setLayout(jBtnLuuLayout);
        jBtnLuuLayout.setHorizontalGroup(
            jBtnLuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnLuuLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jBtnLuuLayout.setVerticalGroup(
            jBtnLuuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnLuuLayout.createSequentialGroup()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        getContentPane().add(jBtnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 643, 120, -1));

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

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Reply_Arrow_64px_1.png"))); // NOI18N

        javax.swing.GroupLayout jBtnHuyLayout = new javax.swing.GroupLayout(jBtnHuy);
        jBtnHuy.setLayout(jBtnHuyLayout);
        jBtnHuyLayout.setHorizontalGroup(
            jBtnHuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnHuyLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jBtnHuyLayout.setVerticalGroup(
            jBtnHuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnHuyLayout.createSequentialGroup()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        getContentPane().add(jBtnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 643, 120, -1));

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

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Edit_48px_1.png"))); // NOI18N

        javax.swing.GroupLayout jBtnSuaLayout = new javax.swing.GroupLayout(jBtnSua);
        jBtnSua.setLayout(jBtnSuaLayout);
        jBtnSuaLayout.setHorizontalGroup(
            jBtnSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnSuaLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jBtnSuaLayout.setVerticalGroup(
            jBtnSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnSuaLayout.createSequentialGroup()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        getContentPane().add(jBtnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 643, 120, -1));

        jBtnXoa.setBackground(new java.awt.Color(204, 204, 204));
        jBtnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
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
            .addGroup(jBtnXoaLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jBtnXoaLayout.setVerticalGroup(
            jBtnXoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnXoaLayout.createSequentialGroup()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        getContentPane().add(jBtnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 643, 120, -1));

        jBtnThem.setBackground(new java.awt.Color(204, 204, 204));
        jBtnThem.addMouseListener(new java.awt.event.MouseAdapter() {
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
            .addGroup(jBtnThemLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jBtnThemLayout.setVerticalGroup(
            jBtnThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 643, 120, 70));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setText("Từ ngày : ");

        jLabel3.setText("Đến ngày :");

        jLabel4.setText("Trạng thái :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setFocusable(false);

        jLabel5.setText("Nhà cung cấp :");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setFocusable(false);

        jLabel7.setText("Mã đặt hàng :");

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPnTracuuDDH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPnTracuuDDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 54, 560, 310));

        jTbDSDDH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã đơn đặt hàng", "Mã nhà cung cấp", "Ngày đặt hàng", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrDSDDH.setViewportView(jTbDSDDH);

        getContentPane().add(jScrDSDDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 408, 550, 300));

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
                .addContainerGap(435, Short.MAX_VALUE))
        );
        jPnDSDDHLayout.setVerticalGroup(
            jPnDSDDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel5.add(jPnDSDDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 373, 560, 340));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel10.setText("Ngày đặt hàng :");

        jLabel11.setText("Trạng thái :");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 73, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        jLabel12.setText("Mã nhà cung cấp :");

        jLabel13.setText("Tên nhà cung cấp :");

        jLabel9.setText("Mã đơn đặt hàng :");

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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPnThongtinDDH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(2, 2, 2)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPnThongtinDDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField2)
                        .addComponent(jLabel12)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 27, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 54, 770, 190));

        jTbCTDDH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã đơn đặt hàng", "Mã sản phẩm", "Số lượng"
            }
        ));
        jScrCTDDH.setViewportView(jTbCTDDH);

        getContentPane().add(jScrCTDDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(585, 290, 760, 340));

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
                .addContainerGap(623, Short.MAX_VALUE))
        );
        jPnCTDDHLayout.setVerticalGroup(
            jPnCTDDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnCTDDHLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel9.add(jPnCTDDH, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 30));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 255, 770, 380));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 720));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/pexels-photo-530024.jpeg"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void LoadForm(){
        HienThiDanhSachDonDatHang(CtrlDDH.LayDSDonDatHang());
        HienThiDanhSachChiTietDDH(CtrlCTDDH.LayDSCTDDH());
        LoadComboboxNhaCungCap();   
        Binding();
    }
    private void jTbCTDDHMouseClicked(java.awt.event.MouseEvent evt) {                                     
            Binding();
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
        jTextField1.setText("");
        jDateChooser1.setDate(new Date());
        jDateChooser2.setDate(new Date());
        
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
       
         HienThiDanhSachDonDatHang(CtrlDDH.SearchDonDHByNhaCC(ListComboboxNCC.get(jComboBox2.getSelectedIndex()).toString()));
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
    private void jBtnThemMouseClicked(java.awt.event.MouseEvent evt) {
        if(jBtnThem.isEnabled()){
            jTextField2.setText(CtrlLapDDH.LayMaDDH());
            jTextField3.setText("");
            jTextField5.setText("");
            jDateChooser3.setDate(new Date());
        }
    }           
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

    private void jBtnXemPhieuInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXemPhieuInMouseClicked
        // TODO add your handling code here:

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
    private javax.swing.JPanel jBtnHuy;
    private javax.swing.JPanel jBtnLamMoi;
    private javax.swing.JPanel jBtnLuu;
    private javax.swing.JPanel jBtnSua;
    private javax.swing.JPanel jBtnThem;
    private javax.swing.JPanel jBtnTimKiem;
    private javax.swing.JPanel jBtnXemPhieuIn;
    private javax.swing.JPanel jBtnXoa;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
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
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPnCTDDH;
    private javax.swing.JPanel jPnDSDDH;
    private javax.swing.JPanel jPnThongtinDDH;
    private javax.swing.JPanel jPnTracuuDDH;
    private javax.swing.JScrollPane jScrCTDDH;
    private javax.swing.JScrollPane jScrDSDDH;
    private javax.swing.JTable jTbCTDDH;
    private javax.swing.JTable jTbDSDDH;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}

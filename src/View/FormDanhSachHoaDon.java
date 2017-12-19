/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.CtrlDanhSachHoaDon;
import Edit.Edit;
import Model.ModChiTietHDL;
import Model.ModChiTietHDS;
import Model.ModHoaDonLe;
import Model.ModHoaDonSi;
import Object.ObjHoaDonLe;
import Object.ObjHoaDonSi;
import Object.ObjChiTietHDL;
import Object.ObjChiTietHDS;
import Object.ObjKhachHang;
import java.awt.Color;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ThaiNguyen
 */
public class FormDanhSachHoaDon extends javax.swing.JFrame {

    boolean ChinhSua = false;
    Edit editFrm = new Edit();
    ArrayList<ObjHoaDonLe> ListHDL = new ArrayList<>();
    ArrayList<ObjHoaDonSi> ListHDS = new ArrayList<>();
    ArrayList<ObjChiTietHDL> ListCTHDL = new ArrayList<>();
    ArrayList<ObjChiTietHDS> ListCTHDS = new ArrayList<>();
    ArrayList<String> ListKH = new ArrayList<>();
    ModChiTietHDL modCTHDL = new ModChiTietHDL();
    ModChiTietHDS modCTHDS = new ModChiTietHDS();
    ModHoaDonLe modHDL = new ModHoaDonLe();
    ModHoaDonSi modHDS = new ModHoaDonSi();
    CtrlDanhSachHoaDon CtrlDSHD = new CtrlDanhSachHoaDon();
    SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy      hh:mm:ss a");

    /**
     * Creates new form FormDanhSachHoaDon
     */
    public FormDanhSachHoaDon() {
        initComponents();
        this.setLocationRelativeTo(null);

        jPanel1.setBackground(new Color(0, 0, 0, 0));

        JPanel ListButton[] = new JPanel[]{jBtnBack, jBtnLamMoi, jBtnSua, jBtnTimKiem, jBtnXemPhieuIn, jBtnXoa};
        editFrm.MakeTransparentButton(ListButton);

        editFrm.MakeTransparentTabbledPane(jTabbedPane1);

        //Tab hóa đơn lẻ
        JPanel ListTitleHDL[] = new JPanel[]{jPnTracuuthongtinHDL, jPnThongtinHDL, jPnDanhsachHDL, jPnChitietHDL};
        editFrm.MakeTransparentTitle(ListTitleHDL);

        jPnHDL.setBackground(new Color(236, 236, 236, 80));

        JPanel ListPanelHDL[] = new JPanel[]{jPnCTHDL, jPnTraCuuThongTinHDL, jPnThongTinHDL, jPnDSHDL};
        editFrm.MakeTransparentPanel(ListPanelHDL);

        editFrm.MakeTransparentTable(jScrDSHDL, jTbDSHDL);
        ((DefaultTableCellRenderer) jTbDSHDL.getDefaultRenderer(Object.class)).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        editFrm.MakeTransparentTable(jScrCTHDL, jTbCTHDL);

        //Tab hóa đơn sỉ
        JPanel ListTitleHDS[] = new JPanel[]{jPnTracuuthongtinHDS, jPnThongtinHDS, jPnDanhsachHDS, jPnChitietHDS};
        editFrm.MakeTransparentTitle(ListTitleHDS);

        jPnHDS.setBackground(new Color(236, 236, 236, 80));

        JPanel ListPanelHDS[] = new JPanel[]{jPnCTHDS, jPnTraCuuThongTinHDS, jPnThongTinHDS, jPnDSHDS};
        editFrm.MakeTransparentPanel(ListPanelHDS);

        editFrm.MakeTransparentTable(jScrCTHDS, jTbCTHDS);
        editFrm.MakeTransparentTable(jScrDSHDS, jTbDSHDS);
        ((DefaultTableCellRenderer) jTbDSHDS.getDefaultRenderer(Object.class)).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        jtxtDiaChi.setLineWrap(true);
        jlbChuaGiao.setVisible(false);
        jlbDaGiao.setVisible(false);
        
        LoadForm();

    }

    public void LoadForm() {
        jDateTuNgayHDL.setDate(new Date());
        jDateDenNgayHDL.setDate(new Date());
        jDateTuNgayHDS.setDate(new Date());
        jDateDenNgayHDS.setDate(new Date());
        HienThiDanhSachHoaDonLe(CtrlDSHD.LayDanhSachHoaDonLe());
        HienThiDanhSachHoaDonSi(CtrlDSHD.LayDanhSachHoaDonSi());
        LoadComboboxKhachHang();
    }

    public void HienThiDanhSachHoaDonLe(ResultSet rs) {
        ListHDL.clear();
        DefaultTableModel model = (DefaultTableModel) jTbDSHDL.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {

            while (rs.next()) {
                ObjHoaDonLe itemHDL = new ObjHoaDonLe(rs.getString("SoHDL"), rs.getString("TenKH"), rs.getTimestamp("NgayLap"), (int) Double.parseDouble(rs.getString("TongTien")));
                ListHDL.add(itemHDL);
                Vector v = new Vector();
                v.add(itemHDL.getSoHDL());
                v.add(dt.format(itemHDL.getNgayLap()));
                model.addRow(v);

            }
            jTbDSHDL.changeSelection(0, 0, false, false);
            BindingHDL();
            HienThiThongTinChiTietHDL(jtxtSoHDL.getText());
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại FormDanhSachHoaDon.HienThiDanhSachHoaDonLe: " + ex.getMessage());
        } finally {
            CtrlDSHD.CloseConnection();
        }
    }

    public void HienThiDanhSachHoaDonSi(ResultSet rs) {
        try {
            ListHDS.clear();
            DefaultTableModel model = (DefaultTableModel) jTbDSHDS.getModel();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            while (rs.next()) {
                ObjHoaDonSi itemHDS = new ObjHoaDonSi(rs.getString("SoHDS"), rs.getString("MaKH"), rs.getString("TenKH"), rs.getString("DiaChi"), rs.getString("SDT"), rs.getTimestamp("NgayLap"), rs.getTimestamp("NgayGiaoDuKien"), (int) Double.parseDouble(rs.getString("TongTien")), rs.getString("TinhTrangGiaoHang"));
                ListHDS.add(itemHDS);
                Vector v = new Vector();
                v.add(itemHDS.getSoHDS());
                v.add(dt.format(itemHDS.getNgayLap()));
                model.addRow(v);
            }
            jTbDSHDS.changeSelection(0, 0, false, false);
            BindingHDS();
            HienThiThongTinChiTietHDS(jtxtSoHDS.getText());
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại FormDanhSachHoaDon.HienThiDanhSachHoaDonSi: " + ex.getMessage());
        } finally {
            CtrlDSHD.CloseConnection();
        }
    }

    public void HienThiThongTinChiTietHDL(String SoHDL) {
        try {
            ResultSet rs = null;
            rs = CtrlDSHD.LayThongTinChiTietHoaDonLe(SoHDL);
            if (rs != null) {
                ListCTHDL.clear();
                DefaultTableModel model = (DefaultTableModel) jTbCTHDL.getModel();
                model.getDataVector().removeAllElements();
                model.fireTableDataChanged();
                while (rs.next()) {
                    ObjChiTietHDL itemCTHDL = new ObjChiTietHDL(rs.getString("SoHDL"), rs.getString("MaSP"), rs.getString("TenSP"), rs.getString("DVT"), (int) Double.parseDouble(rs.getString("SoLuong")), (int) Double.parseDouble(rs.getString("DonGia")), (int) Double.parseDouble(rs.getString("ThanhTien")));
                    ListCTHDL.add(itemCTHDL);
                    Vector v = new Vector();
                    v.add(itemCTHDL.getMaSP());
                    v.add(itemCTHDL.getTenSP());
                    v.add(itemCTHDL.getDVT());
                    v.add(itemCTHDL.getSoLuong());
                    v.add(String.format("%,d", itemCTHDL.getDonGia()));
                    v.add(String.format("%,d", itemCTHDL.getThanhTien()));
                    model.addRow(v);
                }
            }
        } catch (Exception ex) {
            System.out.println("Ngoại lệ tại FormDanhSachHoaDon.HienThiThongTinChiTietHDL: " + ex.getMessage());
        } finally {
            CtrlDSHD.CloseConnection();
        }
    }

    public void HienThiThongTinChiTietHDS(String SoHDS) {
        try {
            ResultSet rs = null;
            rs = CtrlDSHD.LayThongTinChiTietHoaDonSi(SoHDS);
            if (rs != null) {
                ListCTHDS.clear();
                DefaultTableModel model = (DefaultTableModel) jTbCTHDS.getModel();
                model.getDataVector().removeAllElements();
                model.fireTableDataChanged();
                while (rs.next()) {
                    ObjChiTietHDS itemCTHDS = new ObjChiTietHDS(rs.getString("SoHDS"), rs.getString("MaSP"), rs.getString("TenSP"), rs.getString("DVT"), (int) Double.parseDouble(rs.getString("SoLuong")), (int) Double.parseDouble(rs.getString("DonGia")), (int) Double.parseDouble(rs.getString("ThanhTien")));
                    ListCTHDS.add(itemCTHDS);
                    Vector v = new Vector();
                    v.add(itemCTHDS.getMaSP());
                    v.add(itemCTHDS.getTenSP());
                    v.add(itemCTHDS.getDVT());
                    v.add(itemCTHDS.getSoLuong());
                    v.add(String.format("%,d", itemCTHDS.getDonGia()));
                    v.add(String.format("%,d", itemCTHDS.getThanhTien()));
                    model.addRow(v);
                }
            }
        } catch (Exception ex) {
            System.out.println("Ngoại lệ tại FormDanhSachHoaDon.HienThiThongTinChiTietHDS: " + ex.getMessage());
        } finally {
            CtrlDSHD.CloseConnection();
        }
    }

    public void BindingHDL() {
        try {
            DefaultTableModel Model = (DefaultTableModel) jTbDSHDL.getModel();
            int viewRow = jTbDSHDL.getSelectedRow();
            int modelRow = jTbDSHDL.convertRowIndexToModel(viewRow);
            jtxtSoHDL.setText(ListHDL.get(modelRow).getSoHDL());
            jtxtTenKHHDL.setText(ListHDL.get(modelRow).getTenKH());
            jDateNgayLapHDL.setDate(ListHDL.get(modelRow).getNgayLap());
            jtxtTongTienHDL.setText(String.format("%,d", ListHDL.get(modelRow).getTongTien()));
        } catch (Exception ex) {
            System.out.println("Ngoại lệ tại FormDanhSachHoaDon.Binding: " + ex.getMessage());
        }
    }

    public void BindingHDS() {
        try {
            DefaultTableModel Model = (DefaultTableModel) jTbDSHDS.getModel();
            int viewRow = jTbDSHDS.getSelectedRow();
            int modelRow = jTbDSHDS.convertRowIndexToModel(viewRow);

            jtxtSoHDS.setText(ListHDS.get(modelRow).getSoHDS());
            jtxtTenKHHDS.setText(ListHDS.get(modelRow).getTenKH());
            jtxtMaKH.setText(ListHDS.get(modelRow).getMaKH());
            jtxtDiaChi.setText(ListHDS.get(modelRow).getDiaChi());
            jtxtSDT.setText(ListHDS.get(modelRow).getSDT());
            jDateNgayLapHDS.setDate(ListHDS.get(modelRow).getNgayLap());
            jDateNgayGiao.setDate(ListHDS.get(modelRow).getNgayGiaoDuKien());
            jtxtTongTienHDS.setText(String.format("%,d", ListHDS.get(modelRow).getTongTien()));
            if(ListHDS.get(modelRow).getTinhTrangGiaoHang().equals("Đã giao")) DaGiaoHang(true);
            else DaGiaoHang(false);
        } catch (Exception ex) {
            System.out.println("Ngoại lệ tại FormDanhSachHoaDon.Binding: " + ex.getMessage());
        }
    }

    public void LoadComboboxKhachHang() {
        ListKH.clear();
        jcbbKhachHang.removeAllItems();
        jcbbKhachHang.addItem("---Chọn khách hàng---");
        ListKH.add("");
        try {
            ResultSet rs = CtrlDSHD.LayDanhSachKhachHang();
            while (rs.next()) {
                ListKH.add(rs.getString("MaKH"));
                jcbbKhachHang.addItem(rs.getString("TenKH"));
            }
        } catch (SQLException ex) {
            System.out.println("Ngoại lệ tại FormDanhSachHoaDon.LoadComboboxKhachHang:" + ex.getMessage());
        } finally {
            CtrlDSHD.CloseConnection();
        }
    }

    public void DaGiaoHang(boolean Done){
        jlbDaGiao.setVisible(Done);
        jlbChuaGiao.setVisible(!Done);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBtnTimKiem = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jBtnLamMoi = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jBtnXemPhieuIn = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jBtnSua = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jBtnXoa = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPnHDL = new javax.swing.JPanel();
        jPnTraCuuThongTinHDL = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jDateTuNgayHDL = new com.toedter.calendar.JDateChooser();
        jDateDenNgayHDL = new com.toedter.calendar.JDateChooser();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jtxtTimKiemKhachHang = new javax.swing.JTextField();
        jPnTracuuthongtinHDL = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jtxtTimKiemSoHDL = new javax.swing.JTextField();
        jScrDSHDL = new javax.swing.JScrollPane();
        jTbDSHDL = new javax.swing.JTable();
        jPnDSHDL = new javax.swing.JPanel();
        jPnDanhsachHDL = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jPnThongTinHDL = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jtxtSoHDL = new javax.swing.JTextField();
        jtxtTenKHHDL = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jDateNgayLapHDL = new com.toedter.calendar.JDateChooser();
        jtxtTongTienHDL = new javax.swing.JTextField();
        jPnThongtinHDL = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jScrCTHDL = new javax.swing.JScrollPane();
        jTbCTHDL = new javax.swing.JTable();
        jPnCTHDL = new javax.swing.JPanel();
        jPnChitietHDL = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jPnHDS = new javax.swing.JPanel();
        jlbChuaGiao = new javax.swing.JLabel();
        jlbDaGiao = new javax.swing.JLabel();
        jPnTraCuuThongTinHDS = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDateTuNgayHDS = new com.toedter.calendar.JDateChooser();
        jDateDenNgayHDS = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jcbbKhachHang = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jtxtTimKiemSoHDS = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jcbbPhanLoai = new javax.swing.JComboBox<>();
        jPnTracuuthongtinHDS = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jScrDSHDS = new javax.swing.JScrollPane();
        jTbDSHDS = new javax.swing.JTable();
        jPnDSHDS = new javax.swing.JPanel();
        jPnDanhsachHDS = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jPnThongTinHDS = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jtxtSoHDS = new javax.swing.JTextField();
        jtxtMaKH = new javax.swing.JTextField();
        jtxtTenKHHDS = new javax.swing.JTextField();
        jtxtSDT = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jDateNgayLapHDS = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtxtDiaChi = new javax.swing.JTextArea();
        jtxtTongTienHDS = new javax.swing.JTextField();
        jDateNgayGiao = new com.toedter.calendar.JDateChooser();
        jPnThongtinHDS = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jScrCTHDS = new javax.swing.JScrollPane();
        jTbCTHDS = new javax.swing.JTable();
        jPnCTHDS = new javax.swing.JPanel();
        jPnChitietHDS = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jBtnBack = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Search_45px.png"))); // NOI18N

        javax.swing.GroupLayout jBtnTimKiemLayout = new javax.swing.GroupLayout(jBtnTimKiem);
        jBtnTimKiem.setLayout(jBtnTimKiemLayout);
        jBtnTimKiemLayout.setHorizontalGroup(
            jBtnTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnTimKiemLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jBtnTimKiemLayout.setVerticalGroup(
            jBtnTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 265, 120, 65));

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

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Refresh_45px.png"))); // NOI18N

        javax.swing.GroupLayout jBtnLamMoiLayout = new javax.swing.GroupLayout(jBtnLamMoi);
        jBtnLamMoi.setLayout(jBtnLamMoiLayout);
        jBtnLamMoiLayout.setHorizontalGroup(
            jBtnLamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jBtnLamMoiLayout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(37, 37, 37))
        );
        jBtnLamMoiLayout.setVerticalGroup(
            jBtnLamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
        );

        getContentPane().add(jBtnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 265, 120, 65));

        jBtnXemPhieuIn.setBackground(new java.awt.Color(153, 153, 153));
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
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Print_45px.png"))); // NOI18N

        javax.swing.GroupLayout jBtnXemPhieuInLayout = new javax.swing.GroupLayout(jBtnXemPhieuIn);
        jBtnXemPhieuIn.setLayout(jBtnXemPhieuInLayout);
        jBtnXemPhieuInLayout.setHorizontalGroup(
            jBtnXemPhieuInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jBtnXemPhieuInLayout.setVerticalGroup(
            jBtnXemPhieuInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jBtnXemPhieuInLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        getContentPane().add(jBtnXemPhieuIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 362, 58, 108));

        jBtnSua.setBackground(new java.awt.Color(153, 153, 153));
        jBtnSua.setPreferredSize(new java.awt.Dimension(102, 50));
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

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Edit_30px_1.png"))); // NOI18N

        javax.swing.GroupLayout jBtnSuaLayout = new javax.swing.GroupLayout(jBtnSua);
        jBtnSua.setLayout(jBtnSuaLayout);
        jBtnSuaLayout.setHorizontalGroup(
            jBtnSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnSuaLayout.createSequentialGroup()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jBtnSuaLayout.setVerticalGroup(
            jBtnSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jBtnSuaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        getContentPane().add(jBtnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 477, 58, 108));

        jBtnXoa.setBackground(new java.awt.Color(153, 153, 153));
        jBtnXoa.setPreferredSize(new java.awt.Dimension(102, 50));
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

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Trash_40px_1.png"))); // NOI18N

        javax.swing.GroupLayout jBtnXoaLayout = new javax.swing.GroupLayout(jBtnXoa);
        jBtnXoa.setLayout(jBtnXoaLayout);
        jBtnXoaLayout.setHorizontalGroup(
            jBtnXoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jBtnXoaLayout.setVerticalGroup(
            jBtnXoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBtnXoaLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jBtnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 593, 58, 108));

        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPnHDL.setOpaque(false);
        jPnHDL.setPreferredSize(new java.awt.Dimension(1297, 625));
        jPnHDL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPnTraCuuThongTinHDL.setBackground(new java.awt.Color(204, 204, 204));

        jLabel57.setText("Từ ngày :");

        jLabel58.setText("Đến ngày :");

        jDateTuNgayHDL.setDateFormatString("dd/MM/yyyy");
        jDateTuNgayHDL.setFocusable(false);

        jDateDenNgayHDL.setDateFormatString("dd/MM/yyyy");
        jDateDenNgayHDL.setFocusable(false);

        jLabel59.setText("Khách hàng :");

        jLabel60.setText("Số hóa đơn :");

        jPnTracuuthongtinHDL.setBackground(new java.awt.Color(0, 204, 204));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setText("Tra cứu thông tin :");

        javax.swing.GroupLayout jPnTracuuthongtinHDLLayout = new javax.swing.GroupLayout(jPnTracuuthongtinHDL);
        jPnTracuuthongtinHDL.setLayout(jPnTracuuthongtinHDLLayout);
        jPnTracuuthongtinHDLLayout.setHorizontalGroup(
            jPnTracuuthongtinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTracuuthongtinHDLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel56)
                .addContainerGap(373, Short.MAX_VALUE))
        );
        jPnTracuuthongtinHDLLayout.setVerticalGroup(
            jPnTracuuthongtinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPnTraCuuThongTinHDLLayout = new javax.swing.GroupLayout(jPnTraCuuThongTinHDL);
        jPnTraCuuThongTinHDL.setLayout(jPnTraCuuThongTinHDLLayout);
        jPnTraCuuThongTinHDLLayout.setHorizontalGroup(
            jPnTraCuuThongTinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPnTracuuthongtinHDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPnTraCuuThongTinHDLLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPnTraCuuThongTinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnTraCuuThongTinHDLLayout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addGap(20, 20, 20)
                        .addComponent(jDateTuNgayHDL, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel58)
                        .addGap(18, 18, 18)
                        .addComponent(jDateDenNgayHDL, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPnTraCuuThongTinHDLLayout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtTimKiemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPnTraCuuThongTinHDLLayout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addGap(6, 6, 6)
                        .addComponent(jtxtTimKiemSoHDL, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPnTraCuuThongTinHDLLayout.setVerticalGroup(
            jPnTraCuuThongTinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTraCuuThongTinHDLLayout.createSequentialGroup()
                .addComponent(jPnTracuuthongtinHDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPnTraCuuThongTinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateTuNgayHDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateDenNgayHDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPnTraCuuThongTinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(jtxtTimKiemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPnTraCuuThongTinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnTraCuuThongTinHDLLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel60))
                    .addComponent(jtxtTimKiemSoHDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPnHDL.add(jPnTraCuuThongTinHDL, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, 257));

        jTbDSHDL.setAutoCreateRowSorter(true);
        jTbDSHDL.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jTbDSHDL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số hóa đơn", "Ngày lập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTbDSHDL.setFocusable(false);
        jTbDSHDL.setRowHeight(25);
        jTbDSHDL.setSelectionBackground(new java.awt.Color(218, 223, 225));
        jTbDSHDL.setSelectionForeground(new java.awt.Color(255, 51, 0));
        jTbDSHDL.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTbDSHDL.getTableHeader().setReorderingAllowed(false);
        jTbDSHDL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTbDSHDLMouseClicked(evt);
            }
        });
        jScrDSHDL.setViewportView(jTbDSHDL);
        if (jTbDSHDL.getColumnModel().getColumnCount() > 0) {
            jTbDSHDL.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        jPnHDL.add(jScrDSHDL, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 313, 490, 300));

        jPnDSHDL.setBackground(new java.awt.Color(204, 204, 204));
        jPnDSHDL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPnDanhsachHDL.setBackground(new java.awt.Color(0, 204, 204));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setText("Danh sách hóa đơn :");

        javax.swing.GroupLayout jPnDanhsachHDLLayout = new javax.swing.GroupLayout(jPnDanhsachHDL);
        jPnDanhsachHDL.setLayout(jPnDanhsachHDLLayout);
        jPnDanhsachHDLLayout.setHorizontalGroup(
            jPnDanhsachHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnDanhsachHDLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel62)
                .addContainerGap(364, Short.MAX_VALUE))
        );
        jPnDanhsachHDLLayout.setVerticalGroup(
            jPnDanhsachHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPnDSHDL.add(jPnDanhsachHDL, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 30));

        jPnHDL.add(jPnDSHDL, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 279, -1, 339));

        jPnThongTinHDL.setBackground(new java.awt.Color(204, 204, 204));

        jLabel64.setText("Số hóa đơn :");

        jLabel66.setText("Khách hàng :");

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel69.setText("Thông tin khách hàng :");

        jtxtSoHDL.setEditable(false);

        jtxtTenKHHDL.setEditable(false);

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel70.setText("Thông tin thanh toán :");

        jLabel71.setText("Ngày lập :");

        jLabel72.setText("Tổng tiền :");

        jDateNgayLapHDL.setDateFormatString("dd/MM/yyyy");
        jDateNgayLapHDL.setEnabled(false);

        jtxtTongTienHDL.setEditable(false);

        jPnThongtinHDL.setBackground(new java.awt.Color(0, 204, 204));

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel63.setText("Thông tin hóa đơn :");

        javax.swing.GroupLayout jPnThongtinHDLLayout = new javax.swing.GroupLayout(jPnThongtinHDL);
        jPnThongtinHDL.setLayout(jPnThongtinHDLLayout);
        jPnThongtinHDLLayout.setHorizontalGroup(
            jPnThongtinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnThongtinHDLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel63)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPnThongtinHDLLayout.setVerticalGroup(
            jPnThongtinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPnThongTinHDLLayout = new javax.swing.GroupLayout(jPnThongTinHDL);
        jPnThongTinHDL.setLayout(jPnThongTinHDLLayout);
        jPnThongTinHDLLayout.setHorizontalGroup(
            jPnThongTinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnThongTinHDLLayout.createSequentialGroup()
                .addGroup(jPnThongTinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnThongTinHDLLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPnThongTinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnThongTinHDLLayout.createSequentialGroup()
                                .addComponent(jLabel66)
                                .addGap(27, 27, 27)
                                .addComponent(jtxtTenKHHDL, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnThongTinHDLLayout.createSequentialGroup()
                                .addComponent(jLabel64)
                                .addGap(29, 29, 29)
                                .addComponent(jtxtSoHDL, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(80, 80, 80)
                        .addGroup(jPnThongTinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72)
                            .addComponent(jLabel71))
                        .addGap(48, 48, 48)
                        .addGroup(jPnThongTinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateNgayLapHDL, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtTongTienHDL, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPnThongTinHDLLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel69)
                        .addGap(288, 288, 288)
                        .addComponent(jLabel70)))
                .addContainerGap(28, Short.MAX_VALUE))
            .addComponent(jPnThongtinHDL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPnThongTinHDLLayout.setVerticalGroup(
            jPnThongTinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnThongTinHDLLayout.createSequentialGroup()
                .addComponent(jPnThongtinHDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPnThongTinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69)
                    .addComponent(jLabel70))
                .addGap(32, 32, 32)
                .addGroup(jPnThongTinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnThongTinHDLLayout.createSequentialGroup()
                        .addGroup(jPnThongTinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtSoHDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel71)
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPnThongTinHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtxtTenKHHDL)
                            .addComponent(jtxtTongTienHDL)
                            .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jDateNgayLapHDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPnHDL.add(jPnThongTinHDL, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 11, -1, 257));

        jTbCTHDL.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jTbCTHDL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên sản phẩm", "ĐVT", "SL", "Đơn giá", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTbCTHDL.setFocusable(false);
        jTbCTHDL.setRowHeight(25);
        jTbCTHDL.setSelectionBackground(new java.awt.Color(218, 223, 225));
        jTbCTHDL.setSelectionForeground(new java.awt.Color(255, 51, 0));
        jTbCTHDL.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTbCTHDL.getTableHeader().setReorderingAllowed(false);
        jScrCTHDL.setViewportView(jTbCTHDL);
        if (jTbCTHDL.getColumnModel().getColumnCount() > 0) {
            jTbCTHDL.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTbCTHDL.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTbCTHDL.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTbCTHDL.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTbCTHDL.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTbCTHDL.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        jPnHDL.add(jScrCTHDL, new org.netbeans.lib.awtextra.AbsoluteConstraints(596, 313, 690, 300));

        jPnCTHDL.setBackground(new java.awt.Color(204, 204, 204));
        jPnCTHDL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPnChitietHDL.setBackground(new java.awt.Color(0, 204, 204));

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel76.setText("Chi tiết hóa đơn :");

        javax.swing.GroupLayout jPnChitietHDLLayout = new javax.swing.GroupLayout(jPnChitietHDL);
        jPnChitietHDL.setLayout(jPnChitietHDLLayout);
        jPnChitietHDLLayout.setHorizontalGroup(
            jPnChitietHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnChitietHDLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel76)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPnChitietHDLLayout.setVerticalGroup(
            jPnChitietHDLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPnCTHDL.add(jPnChitietHDL, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, -1));

        jPnHDL.add(jPnCTHDL, new org.netbeans.lib.awtextra.AbsoluteConstraints(591, 279, -1, 339));

        jTabbedPane1.addTab("Danh sách hóa đơn lẻ     ", jPnHDL);

        jPnHDS.setOpaque(false);
        jPnHDS.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbChuaGiao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Unchecked_Checkbox_25px_1.png"))); // NOI18N
        jlbChuaGiao.setText("  Chưa giao");
        jPnHDS.add(jlbChuaGiao, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 185, 90, 28));

        jlbDaGiao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Checked_Checkbox_25px_5.png"))); // NOI18N
        jlbDaGiao.setText("  Đã giao");
        jlbDaGiao.setToolTipText("");
        jPnHDS.add(jlbDaGiao, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 185, 90, 28));

        jPnTraCuuThongTinHDS.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setText("Từ ngày :");

        jLabel6.setText("Đến ngày :");

        jDateTuNgayHDS.setDateFormatString("dd/MM/yyyy");
        jDateTuNgayHDS.setFocusable(false);

        jDateDenNgayHDS.setDateFormatString("dd/MM/yyyy");
        jDateDenNgayHDS.setFocusable(false);

        jLabel7.setText("Khách hàng :");

        jcbbKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn khách hàng---" }));
        jcbbKhachHang.setFocusable(false);

        jLabel8.setText("Số hóa đơn :");

        jLabel9.setText("Phân loại :");

        jcbbPhanLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả các hóa đơn", "Chỉ những hóa đơn đã giao", "Chỉ những hóa đơn chưa giao" }));
        jcbbPhanLoai.setFocusable(false);

        jPnTracuuthongtinHDS.setBackground(new java.awt.Color(0, 204, 204));

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel67.setText("Tra cứu thông tin :");

        javax.swing.GroupLayout jPnTracuuthongtinHDSLayout = new javax.swing.GroupLayout(jPnTracuuthongtinHDS);
        jPnTracuuthongtinHDS.setLayout(jPnTracuuthongtinHDSLayout);
        jPnTracuuthongtinHDSLayout.setHorizontalGroup(
            jPnTracuuthongtinHDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTracuuthongtinHDSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel67)
                .addContainerGap(373, Short.MAX_VALUE))
        );
        jPnTracuuthongtinHDSLayout.setVerticalGroup(
            jPnTracuuthongtinHDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPnTraCuuThongTinHDSLayout = new javax.swing.GroupLayout(jPnTraCuuThongTinHDS);
        jPnTraCuuThongTinHDS.setLayout(jPnTraCuuThongTinHDSLayout);
        jPnTraCuuThongTinHDSLayout.setHorizontalGroup(
            jPnTraCuuThongTinHDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPnTracuuthongtinHDS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPnTraCuuThongTinHDSLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPnTraCuuThongTinHDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnTraCuuThongTinHDSLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(20, 20, 20)
                        .addComponent(jDateTuNgayHDS, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateDenNgayHDS, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPnTraCuuThongTinHDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPnTraCuuThongTinHDSLayout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtxtTimKiemSoHDS))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPnTraCuuThongTinHDSLayout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18)
                            .addComponent(jcbbPhanLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPnTraCuuThongTinHDSLayout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jcbbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPnTraCuuThongTinHDSLayout.setVerticalGroup(
            jPnTraCuuThongTinHDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTraCuuThongTinHDSLayout.createSequentialGroup()
                .addComponent(jPnTracuuthongtinHDS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPnTraCuuThongTinHDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateTuNgayHDS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateDenNgayHDS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPnTraCuuThongTinHDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnTraCuuThongTinHDSLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7))
                    .addComponent(jcbbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPnTraCuuThongTinHDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnTraCuuThongTinHDSLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel9))
                    .addComponent(jcbbPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPnTraCuuThongTinHDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnTraCuuThongTinHDSLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel8))
                    .addComponent(jtxtTimKiemSoHDS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPnHDS.add(jPnTraCuuThongTinHDS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, 257));

        jTbDSHDS.setAutoCreateRowSorter(true);
        jTbDSHDS.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jTbDSHDS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số hóa đơn", "Ngày lập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTbDSHDS.setFocusable(false);
        jTbDSHDS.setRowHeight(25);
        jTbDSHDS.setSelectionBackground(new java.awt.Color(218, 223, 225));
        jTbDSHDS.setSelectionForeground(new java.awt.Color(255, 51, 0));
        jTbDSHDS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTbDSHDS.getTableHeader().setReorderingAllowed(false);
        jTbDSHDS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTbDSHDSMouseClicked(evt);
            }
        });
        jScrDSHDS.setViewportView(jTbDSHDS);
        if (jTbDSHDS.getColumnModel().getColumnCount() > 0) {
            jTbDSHDS.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        jPnHDS.add(jScrDSHDS, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 313, 490, 300));

        jPnDSHDS.setBackground(new java.awt.Color(204, 204, 204));
        jPnDSHDS.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPnDanhsachHDS.setBackground(new java.awt.Color(0, 204, 204));

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel73.setText("Danh sách hóa đơn :");

        javax.swing.GroupLayout jPnDanhsachHDSLayout = new javax.swing.GroupLayout(jPnDanhsachHDS);
        jPnDanhsachHDS.setLayout(jPnDanhsachHDSLayout);
        jPnDanhsachHDSLayout.setHorizontalGroup(
            jPnDanhsachHDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnDanhsachHDSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel73)
                .addContainerGap(364, Short.MAX_VALUE))
        );
        jPnDanhsachHDSLayout.setVerticalGroup(
            jPnDanhsachHDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPnDSHDS.add(jPnDanhsachHDS, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 30));

        jPnHDS.add(jPnDSHDS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 279, -1, 339));

        jPnThongTinHDS.setBackground(new java.awt.Color(204, 204, 204));
        jPnThongTinHDS.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setText("Số hóa đơn :");
        jPnThongTinHDS.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 74, -1, -1));

        jLabel11.setText("Mã khách hàng :");
        jPnThongTinHDS.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 105, -1, -1));

        jLabel14.setText("Khách hàng :");
        jPnThongTinHDS.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 136, -1, -1));

        jLabel15.setText("Địa chỉ :");
        jPnThongTinHDS.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 171, -1, -1));

        jLabel16.setText("Số điện thoại :");
        jPnThongTinHDS.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 214, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Thông tin khách hàng :");
        jPnThongTinHDS.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 48, -1, -1));

        jtxtSoHDS.setEditable(false);
        jPnThongTinHDS.add(jtxtSoHDS, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 71, 240, -1));

        jtxtMaKH.setEditable(false);
        jPnThongTinHDS.add(jtxtMaKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 102, 240, -1));

        jtxtTenKHHDS.setEditable(false);
        jPnThongTinHDS.add(jtxtTenKHHDS, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 133, 240, -1));

        jtxtSDT.setEditable(false);
        jPnThongTinHDS.add(jtxtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 211, 240, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Thông tin thanh toán :");
        jPnThongTinHDS.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(439, 48, -1, -1));

        jLabel19.setText("Ngày lập :");
        jPnThongTinHDS.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 74, -1, -1));

        jLabel20.setText("Tổng tiền :");
        jPnThongTinHDS.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 136, 91, -1));

        jLabel22.setText("Ngày giao dự kiến :");
        jPnThongTinHDS.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 105, 100, -1));

        jLabel23.setText("Tình trạng giao hàng :");
        jPnThongTinHDS.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 181, 120, -1));

        jDateNgayLapHDS.setDateFormatString("dd/MM/yyyy");
        jDateNgayLapHDS.setEnabled(false);
        jPnThongTinHDS.add(jDateNgayLapHDS, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 71, 190, -1));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jtxtDiaChi.setEditable(false);
        jtxtDiaChi.setColumns(20);
        jtxtDiaChi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jtxtDiaChi.setRows(5);
        jScrollPane2.setViewportView(jtxtDiaChi);

        jPnThongTinHDS.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 164, 240, 37));

        jtxtTongTienHDS.setEditable(false);
        jPnThongTinHDS.add(jtxtTongTienHDS, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 133, 190, -1));

        jDateNgayGiao.setDateFormatString("dd/MM/yyyy");
        jDateNgayGiao.setEnabled(false);
        jPnThongTinHDS.add(jDateNgayGiao, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 102, 190, -1));

        jPnThongtinHDS.setBackground(new java.awt.Color(0, 204, 204));

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel68.setText("Thông tin hóa đơn :");

        javax.swing.GroupLayout jPnThongtinHDSLayout = new javax.swing.GroupLayout(jPnThongtinHDS);
        jPnThongtinHDS.setLayout(jPnThongtinHDSLayout);
        jPnThongtinHDSLayout.setHorizontalGroup(
            jPnThongtinHDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnThongtinHDSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel68)
                .addContainerGap(635, Short.MAX_VALUE))
        );
        jPnThongtinHDSLayout.setVerticalGroup(
            jPnThongtinHDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPnThongTinHDS.add(jPnThongtinHDS, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPnHDS.add(jPnThongTinHDS, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 11, -1, 257));

        jTbCTHDS.setAutoCreateRowSorter(true);
        jTbCTHDS.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jTbCTHDS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên sản phẩm", "ĐVT", "SL", "Đơn giá", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTbCTHDS.setFocusable(false);
        jTbCTHDS.setRowHeight(25);
        jTbCTHDS.setSelectionBackground(new java.awt.Color(218, 223, 225));
        jTbCTHDS.setSelectionForeground(new java.awt.Color(255, 51, 0));
        jTbCTHDS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrCTHDS.setViewportView(jTbCTHDS);
        if (jTbCTHDS.getColumnModel().getColumnCount() > 0) {
            jTbCTHDS.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTbCTHDS.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTbCTHDS.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTbCTHDS.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTbCTHDS.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTbCTHDS.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        jPnHDS.add(jScrCTHDS, new org.netbeans.lib.awtextra.AbsoluteConstraints(596, 313, 690, 300));

        jPnCTHDS.setBackground(new java.awt.Color(204, 204, 204));
        jPnCTHDS.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPnChitietHDS.setBackground(new java.awt.Color(0, 204, 204));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Chi tiết hóa đơn :");

        javax.swing.GroupLayout jPnChitietHDSLayout = new javax.swing.GroupLayout(jPnChitietHDS);
        jPnChitietHDS.setLayout(jPnChitietHDSLayout);
        jPnChitietHDSLayout.setHorizontalGroup(
            jPnChitietHDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnChitietHDSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addContainerGap(582, Short.MAX_VALUE))
        );
        jPnChitietHDSLayout.setVerticalGroup(
            jPnChitietHDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPnCTHDS.add(jPnChitietHDS, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPnHDS.add(jPnCTHDS, new org.netbeans.lib.awtextra.AbsoluteConstraints(591, 279, -1, 339));

        jTabbedPane1.addTab("Danh sách hóa đơn sỉ      ", jPnHDS);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 1310, 665));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1330, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, 720));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/pexels-photo-530024.jpeg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jBtnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnTimKiemMouseClicked
        // TODO add your handling code here:
        try {
            if (jTabbedPane1.getSelectedIndex() == 0) {
                HienThiDanhSachHoaDonLe(CtrlDSHD.TimKiemHDL(jtxtTimKiemKhachHang.getText(), jtxtTimKiemSoHDL.getText(), jDateTuNgayHDL.getDate(), jDateDenNgayHDL.getDate()));
            } else {
                String TinhTrang = "";
                if (jcbbPhanLoai.getSelectedIndex() == 0) {
                    TinhTrang = "";
                } else if (jcbbPhanLoai.getSelectedIndex() == 1) {
                    TinhTrang = "Đã giao";
                } else {
                    TinhTrang = "Chưa giao";
                }
                HienThiDanhSachHoaDonSi(CtrlDSHD.TimKiemHDS(ListKH.get(jcbbKhachHang.getSelectedIndex()), jtxtTimKiemSoHDS.getText(), TinhTrang, jDateTuNgayHDS.getDate(), jDateDenNgayHDS.getDate()));
            }
        } catch (Exception ex) {
            System.out.println("Ngoại lệ tại FormDanhSachHoaDon.jBtnTimKiemMouseClicked: " + ex.getMessage());
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

    private void jBtnLamMoiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLamMoiMouseReleased
        // TODO add your handling code here:
        setColor(jBtnLamMoi);
    }//GEN-LAST:event_jBtnLamMoiMouseReleased

    private void jBtnLamMoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLamMoiMousePressed
        // TODO add your handling code here:
        resetColor(jBtnLamMoi);
    }//GEN-LAST:event_jBtnLamMoiMousePressed

    private void jBtnLamMoiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLamMoiMouseExited
        // TODO add your handling code here:
        resetColor(jBtnLamMoi);
    }//GEN-LAST:event_jBtnLamMoiMouseExited

    private void jBtnLamMoiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLamMoiMouseEntered
        // TODO add your handling code here:
        setColor(jBtnLamMoi);
    }//GEN-LAST:event_jBtnLamMoiMouseEntered

    private void jBtnLamMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnLamMoiMouseClicked
        // TODO add your handling code here:
        if (jTabbedPane1.getSelectedIndex() == 0) {
            jDateTuNgayHDL.setDate(new Date());
            jDateDenNgayHDL.setDate(new Date());
            jtxtTimKiemKhachHang.setText("");
            jtxtTimKiemSoHDL.setText("");
            HienThiDanhSachHoaDonLe(CtrlDSHD.LayDanhSachHoaDonLe());
        } else {
            jDateTuNgayHDS.setDate(new Date());
            jDateDenNgayHDS.setDate(new Date());            
            jtxtTimKiemSoHDS.setText("");
            HienThiDanhSachHoaDonSi(CtrlDSHD.LayDanhSachHoaDonSi());
            LoadComboboxKhachHang();
        }
    }//GEN-LAST:event_jBtnLamMoiMouseClicked

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

    private void jBtnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSuaMouseClicked
        // TODO add your handling code here:
        ChinhSua = true;
        if (jTabbedPane1.getSelectedIndex() == 0) {
            FormLapHoaDonLe FrmLapHDL = new FormLapHoaDonLe(jtxtSoHDL.getText(), jtxtTenKHHDL.getText(), ListCTHDL, jDateNgayLapHDL.getDate());
            FrmLapHDL.ChinhSua = true;
            FrmLapHDL.setVisible(true);
        } else {
            FormLapHoaDonSi FrmLapHDS = new FormLapHoaDonSi(jtxtSoHDS.getText(), jtxtTenKHHDS.getText(), ListCTHDS, jDateNgayLapHDS.getDate());
            FrmLapHDS.ChinhSua = true;
            FrmLapHDS.setVisible(true);
        }
    }//GEN-LAST:event_jBtnSuaMouseClicked

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

    private void jBtnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnXoaMouseClicked
        // TODO add your handling code here:
        int dialogButton = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (dialogButton == JOptionPane.YES_OPTION) {
            if (jTabbedPane1.getSelectedIndex() == 0) {
                if (modCTHDL.Delete(jtxtSoHDL.getText()) && modHDL.Delete(jtxtSoHDL.getText())) {
                    JOptionPane.showMessageDialog(this, "Hóa đơn" + jtxtSoHDL.getText() + " đã xóa thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    HienThiDanhSachHoaDonLe(CtrlDSHD.LayDanhSachHoaDonLe());
                } else {
                    JOptionPane.showMessageDialog(this, "Hóa đơn" + jtxtSoHDL.getText() + " xóa không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                if (modCTHDS.Delete(jtxtSoHDS.getText()) && modHDS.Delete(jtxtSoHDS.getText())) {
                    JOptionPane.showMessageDialog(this, "Hóa đơn" + jtxtSoHDS.getText() + " đã xóa thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    HienThiDanhSachHoaDonSi(CtrlDSHD.LayDanhSachHoaDonSi());
                } else {
                    JOptionPane.showMessageDialog(this, "Hóa đơn" + jtxtSoHDS.getText() + " xóa không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jBtnXoaMouseClicked

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

    private void jTbDSHDLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTbDSHDLMouseClicked
        // TODO add your handling code here:
        if (jTbDSHDL.getSelectedRow() != -1) {
            BindingHDL();
            HienThiThongTinChiTietHDL(jtxtSoHDL.getText());
        }
    }//GEN-LAST:event_jTbDSHDLMouseClicked

    private void jTbDSHDSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTbDSHDSMouseClicked
        // TODO add your handling code here:
        if (jTbDSHDS.getSelectedRow() != -1) {
            BindingHDS();
            HienThiThongTinChiTietHDS(jtxtSoHDS.getText());
        }
    }//GEN-LAST:event_jTbDSHDSMouseClicked

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        //Binding();
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        if (ChinhSua) {
            if (jTabbedPane1.getSelectedIndex() == 0) {
                HienThiDanhSachHoaDonLe(CtrlDSHD.LayDanhSachHoaDonLe());
                BindingHDL();
                HienThiThongTinChiTietHDL(jtxtSoHDL.getText());
            } else {
                HienThiDanhSachHoaDonSi(CtrlDSHD.LayDanhSachHoaDonSi());
                BindingHDS();
                HienThiThongTinChiTietHDS(jtxtSoHDS.getText());
            }
            ChinhSua = false;
        }
    }//GEN-LAST:event_formWindowActivated

    public void setColor(JPanel pn) {
        if (pn.isEnabled()) {
            pn.setSize(pn.getWidth() + 1, pn.getHeight() + 1);
            pn.setBackground(new Color(60, 209, 127, 50));
        }
    }

    public void resetColor(JPanel pn) {
        if (pn.isEnabled()) {
            pn.setSize(pn.getWidth() - 1, pn.getHeight() - 1);
            pn.setBackground(new Color(153, 153, 153, 180));
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
            java.util.logging.Logger.getLogger(FormDanhSachHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDanhSachHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDanhSachHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDanhSachHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FormDanhSachHoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jBtnBack;
    private javax.swing.JPanel jBtnLamMoi;
    private javax.swing.JPanel jBtnSua;
    private javax.swing.JPanel jBtnTimKiem;
    private javax.swing.JPanel jBtnXemPhieuIn;
    private javax.swing.JPanel jBtnXoa;
    private com.toedter.calendar.JDateChooser jDateDenNgayHDL;
    private com.toedter.calendar.JDateChooser jDateDenNgayHDS;
    private com.toedter.calendar.JDateChooser jDateNgayGiao;
    private com.toedter.calendar.JDateChooser jDateNgayLapHDL;
    private com.toedter.calendar.JDateChooser jDateNgayLapHDS;
    private com.toedter.calendar.JDateChooser jDateTuNgayHDL;
    private com.toedter.calendar.JDateChooser jDateTuNgayHDS;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPnCTHDL;
    private javax.swing.JPanel jPnCTHDS;
    private javax.swing.JPanel jPnChitietHDL;
    private javax.swing.JPanel jPnChitietHDS;
    private javax.swing.JPanel jPnDSHDL;
    private javax.swing.JPanel jPnDSHDS;
    private javax.swing.JPanel jPnDanhsachHDL;
    private javax.swing.JPanel jPnDanhsachHDS;
    private javax.swing.JPanel jPnHDL;
    private javax.swing.JPanel jPnHDS;
    private javax.swing.JPanel jPnThongTinHDL;
    private javax.swing.JPanel jPnThongTinHDS;
    private javax.swing.JPanel jPnThongtinHDL;
    private javax.swing.JPanel jPnThongtinHDS;
    private javax.swing.JPanel jPnTraCuuThongTinHDL;
    private javax.swing.JPanel jPnTraCuuThongTinHDS;
    private javax.swing.JPanel jPnTracuuthongtinHDL;
    private javax.swing.JPanel jPnTracuuthongtinHDS;
    private javax.swing.JScrollPane jScrCTHDL;
    private javax.swing.JScrollPane jScrCTHDS;
    private javax.swing.JScrollPane jScrDSHDL;
    private javax.swing.JScrollPane jScrDSHDS;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTbCTHDL;
    private javax.swing.JTable jTbCTHDS;
    private javax.swing.JTable jTbDSHDL;
    private javax.swing.JTable jTbDSHDS;
    private javax.swing.JComboBox<String> jcbbKhachHang;
    private javax.swing.JComboBox<String> jcbbPhanLoai;
    private javax.swing.JLabel jlbChuaGiao;
    private javax.swing.JLabel jlbDaGiao;
    private javax.swing.JTextArea jtxtDiaChi;
    private javax.swing.JTextField jtxtMaKH;
    private javax.swing.JTextField jtxtSDT;
    private javax.swing.JTextField jtxtSoHDL;
    private javax.swing.JTextField jtxtSoHDS;
    private javax.swing.JTextField jtxtTenKHHDL;
    private javax.swing.JTextField jtxtTenKHHDS;
    private javax.swing.JTextField jtxtTimKiemKhachHang;
    private javax.swing.JTextField jtxtTimKiemSoHDL;
    private javax.swing.JTextField jtxtTimKiemSoHDS;
    private javax.swing.JTextField jtxtTongTienHDL;
    private javax.swing.JTextField jtxtTongTienHDS;
    // End of variables declaration//GEN-END:variables
}

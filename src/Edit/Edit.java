/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Edit;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ThaiNguyen
 */
public class Edit {
    private final Color TitleColor = new Color(0,204,204,150);
    private final Color PanelColor = new Color(236,236,236,190);
    private final Color ButtonColor = new Color(153,153,153,180);
    
    public void MakeTransparentPanel(JPanel[] listPn){
        for(int i=0;i<listPn.length;i++)
            listPn[i].setBackground(PanelColor);
    }
    
    
    public void MakeTransparentTitle(JPanel[] listTitle){
        for(int i=0;i<listTitle.length;i++){
            listTitle[i].setBackground(TitleColor);
        }
    }
    
    public void MakeTransparentButton(JPanel[] ListButton){
        for(int i=0;i<ListButton.length;i++){
            ListButton[i].setBackground(ButtonColor);
        }
    }
    
    public void MakeTransparentTable(JScrollPane jscrpn,JTable jtb){
        jscrpn.setOpaque(false);
        jscrpn.getViewport().setOpaque(false);
        jtb.setOpaque(false);
        jtb.setShowGrid(true);
        jtb.setBackground(new Color(0,0,0,0));
        ((DefaultTableCellRenderer)jtb.getDefaultRenderer(Object.class)).setBackground(new Color(236,236,236,190));
        ((DefaultTableCellRenderer)jtb.getDefaultRenderer(Object.class)).setOpaque(false);
        ((DefaultTableCellRenderer)jtb.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    }
    
    public void MakeTransparentTabbledPane(JTabbedPane jTabPn){
        jTabPn.setOpaque(false); 
        jTabPn.setBackground(new Color (0,0,0,0)); // this works well, 2 tabs semi-transparents 
        jTabPn.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI(){
            protected void paintContentBorder(Graphics g,int tabPlacement,int selectedIndex){}
        });
    }
}

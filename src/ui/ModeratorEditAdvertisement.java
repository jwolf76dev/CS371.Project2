/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import db.DBManager;
import db.Record;
import db.Advertisement;
import Utilities.Utilities;
import com.mysql.jdbc.StringUtils;
import javax.swing.JOptionPane;

/**
 *
 * @author jwolf
 */
public class ModeratorEditAdvertisement extends javax.swing.JFrame {

    /**
     * Creates new form ModeratorEditAdvertisement
     */
    DBManager DB;
    int adID;
    String userID;
    ModeratorView parent;
    Advertisement advertisement = null;
    

    public ModeratorEditAdvertisement(ModeratorView parent, DBManager DB, int adID, String userID) {
        setTitle("Edit Advertisement " + adID);
        this.parent = parent;
        this.DB = DB;
        this.adID = adID;
        this.userID = userID;
        initComponents();
        populateCategories();
        populateTable(adID);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ModeratorEdit_Title_Label = new javax.swing.JLabel();
        ModeratorEdit_Title_Field = new javax.swing.JTextField();
        ModeratorEdit_Details_Label = new javax.swing.JLabel();
        ModeratorEdit_Details_Container = new javax.swing.JScrollPane();
        ModeratorEdit_Details_Field = new javax.swing.JTextArea();
        ModeratorEdit_Category_Label = new javax.swing.JLabel();
        ModeratorEdit_Category_ComboBox = new javax.swing.JComboBox<>();
        ModeratorEdit_Price_Label = new javax.swing.JLabel();
        ModeratorEdit_Price_Field = new javax.swing.JTextField();
        ModeratorEdit_Status_Label = new javax.swing.JLabel();
        ModeratorEdit_Status_ComboBox = new javax.swing.JComboBox<>();
        ModeratorEdit_Update_Button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Edit Advertisement");

        ModeratorEdit_Title_Label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ModeratorEdit_Title_Label.setLabelFor(ModeratorEdit_Title_Field);
        ModeratorEdit_Title_Label.setText("Title:");

        ModeratorEdit_Title_Field.setEditable(false);
        ModeratorEdit_Title_Field.setToolTipText("Enter a descriptive title for the item.");

        ModeratorEdit_Details_Label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ModeratorEdit_Details_Label.setLabelFor(ModeratorEdit_Details_Field);
        ModeratorEdit_Details_Label.setText("Details:");

        ModeratorEdit_Details_Container.setToolTipText("");

        ModeratorEdit_Details_Field.setEditable(false);
        ModeratorEdit_Details_Field.setColumns(20);
        ModeratorEdit_Details_Field.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ModeratorEdit_Details_Field.setLineWrap(true);
        ModeratorEdit_Details_Field.setRows(5);
        ModeratorEdit_Details_Field.setTabSize(1);
        ModeratorEdit_Details_Field.setToolTipText("Enter details about the item.");
        ModeratorEdit_Details_Field.setWrapStyleWord(true);
        ModeratorEdit_Details_Container.setViewportView(ModeratorEdit_Details_Field);

        ModeratorEdit_Category_Label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ModeratorEdit_Category_Label.setLabelFor(ModeratorEdit_Category_ComboBox);
        ModeratorEdit_Category_Label.setText("Category:");
        ModeratorEdit_Category_Label.setToolTipText("");

        ModeratorEdit_Category_ComboBox.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        ModeratorEdit_Price_Label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ModeratorEdit_Price_Label.setLabelFor(ModeratorEdit_Price_Field);
        ModeratorEdit_Price_Label.setText("Price:");

        ModeratorEdit_Price_Field.setEditable(false);
        ModeratorEdit_Price_Field.setToolTipText("Enter the item's price.");

        ModeratorEdit_Status_Label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ModeratorEdit_Status_Label.setLabelFor(ModeratorEdit_Status_ComboBox);
        ModeratorEdit_Status_Label.setText("Status:");

        ModeratorEdit_Status_ComboBox.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        ModeratorEdit_Update_Button.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ModeratorEdit_Update_Button.setText("Update Advertisement");
        ModeratorEdit_Update_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModeratorEdit_Update_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ModeratorEdit_Update_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ModeratorEdit_Category_Label)
                            .addComponent(ModeratorEdit_Details_Label)
                            .addComponent(ModeratorEdit_Price_Label)
                            .addComponent(ModeratorEdit_Title_Label)
                            .addComponent(ModeratorEdit_Status_Label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ModeratorEdit_Status_ComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ModeratorEdit_Price_Field)
                            .addComponent(ModeratorEdit_Title_Field)
                            .addComponent(ModeratorEdit_Details_Container)
                            .addComponent(ModeratorEdit_Category_ComboBox, 0, 170, Short.MAX_VALUE))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ModeratorEdit_Title_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ModeratorEdit_Title_Label))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ModeratorEdit_Details_Container, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ModeratorEdit_Details_Label))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ModeratorEdit_Category_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ModeratorEdit_Category_Label))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ModeratorEdit_Price_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ModeratorEdit_Price_Label))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ModeratorEdit_Status_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ModeratorEdit_Status_Label))
                .addGap(18, 18, 18)
                .addComponent(ModeratorEdit_Update_Button)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ModeratorEdit_Update_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModeratorEdit_Update_ButtonActionPerformed
        advertisement.setID(this.adID);
        advertisement.setTitle(this.ModeratorEdit_Title_Field.getText());
        advertisement.setDetails(this.ModeratorEdit_Details_Field.getText());
        Record category = (Record) this.ModeratorEdit_Category_ComboBox.getSelectedItem();
        advertisement.setCategoryID(category.getID());
        Record status = (Record) this.ModeratorEdit_Status_ComboBox.getSelectedItem();
        advertisement.setStatusID(status.getID());
        
        boolean result = DB.moderatorUpdateAdvertisement(advertisement);
        if (result) {
            JOptionPane.showMessageDialog(this,
                    "The advertisement was updated",
                    "Confirmation",
                    JOptionPane.INFORMATION_MESSAGE);
            parent.populateModeratorAdsTable(userID);
            this.setVisible(false);
        }
    }//GEN-LAST:event_ModeratorEdit_Update_ButtonActionPerformed

    private void populateTable(int adID) {
        advertisement = DB.getAdByID(adID);
        this.ModeratorEdit_Title_Field.setText(advertisement.getTitle());
        this.ModeratorEdit_Details_Field.setText(advertisement.getDetails());
        this.ModeratorEdit_Price_Field.setText(String.valueOf(advertisement.getPrice()));
        try {
            this.ModeratorEdit_Category_ComboBox.setSelectedItem(Utilities.findCategory(this.ModeratorEdit_Category_ComboBox, advertisement.getCategoryID()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.ModeratorEdit_Status_ComboBox.setSelectedItem(Utilities.findCategory(this.ModeratorEdit_Status_ComboBox, advertisement.getCategoryID()));
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    
    private void populateCategories() {
        this.ModeratorEdit_Category_ComboBox.removeAllItems();
        for (Record category : DB.getCategories()) {
            this.ModeratorEdit_Category_ComboBox.addItem(category);
        }
    }
    
    private void populateStatuses() {
        this.ModeratorEdit_Status_ComboBox.removeAllItems();
        for (Record status : DB.getStatuses()) {
            this.ModeratorEdit_Status_ComboBox.addItem(status);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Record> ModeratorEdit_Category_ComboBox;
    private javax.swing.JLabel ModeratorEdit_Category_Label;
    private javax.swing.JScrollPane ModeratorEdit_Details_Container;
    private javax.swing.JTextArea ModeratorEdit_Details_Field;
    private javax.swing.JLabel ModeratorEdit_Details_Label;
    private javax.swing.JTextField ModeratorEdit_Price_Field;
    private javax.swing.JLabel ModeratorEdit_Price_Label;
    private javax.swing.JComboBox<Record> ModeratorEdit_Status_ComboBox;
    private javax.swing.JLabel ModeratorEdit_Status_Label;
    private javax.swing.JTextField ModeratorEdit_Title_Field;
    private javax.swing.JLabel ModeratorEdit_Title_Label;
    private javax.swing.JButton ModeratorEdit_Update_Button;
    // End of variables declaration//GEN-END:variables
}

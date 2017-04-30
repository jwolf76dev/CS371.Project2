/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import db.DBManager;
import db.Record;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jwolf
 */
public class UserView extends javax.swing.JFrame {

    /**
     * Creates new form UserView
     */
    DBManager DB;
    String userID;
    String[] allAdsColumns
            = new String[]{"Title", "Description", "Price", "Date"};
    String[] userAdsColumns
            = new String[]{"Ad ID", "Title", "Description", "Price", "Status", "Date"};
    
    public UserView(DBManager DB, String userID) {
        this.setTitle("User: " + userID);
        this.DB = DB;
        this.userID = userID;
        initComponents();
        this.populateCategories();
        this.populateAllAdsTable();
        this.populateUserAdsTable(userID);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        User_AddAd_Button = new javax.swing.JButton();
        User_Tab_Container = new javax.swing.JTabbedPane();
        User_AllAds_Tab = new javax.swing.JPanel();
        User_Category_ComboBox = new javax.swing.JComboBox<>();
        User_Category_Label = new javax.swing.JLabel();
        User_SearchString_Field = new javax.swing.JTextField();
        User_Search_Button = new javax.swing.JButton();
        User_Period_ComboBox = new javax.swing.JComboBox<>();
        User_SearchString_Label = new javax.swing.JLabel();
        User_Period_Label = new javax.swing.JLabel();
        User_AllAdsResults_Container = new javax.swing.JScrollPane();
        User_AllAdsResults_Table = new javax.swing.JTable();
        User_MyAds_Tab = new javax.swing.JPanel();
        User_Delete_Button = new javax.swing.JButton();
        User_Edit_Button = new javax.swing.JButton();
        User_MyAdsResults_Container = new javax.swing.JScrollPane();
        User_MyAdsResults_Table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        User_AddAd_Button.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        User_AddAd_Button.setText("Add Advertisement");
        User_AddAd_Button.setToolTipText("Add a new advertisement.");
        User_AddAd_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                User_AddAd_ButtonActionPerformed(evt);
            }
        });

        User_Tab_Container.setToolTipText("");
        User_Tab_Container.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        User_AllAds_Tab.setToolTipText("");
        User_AllAds_Tab.setName(""); // NOI18N
        User_AllAds_Tab.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                User_AllAds_TabComponentShown(evt);
            }
        });

        User_Category_ComboBox.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        User_Category_ComboBox.setToolTipText("Select an item category to search.");

        User_Category_Label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        User_Category_Label.setText("Category");

        User_SearchString_Field.setToolTipText("Enter keyword(s) to search for.");

        User_Search_Button.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        User_Search_Button.setText("Search");
        User_Search_Button.setToolTipText("");

        User_Period_ComboBox.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        User_Period_ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any Date", "This Month", "Last Month", "Last 3 Months", "Last 6 Months", "Last Year" }));
        User_Period_ComboBox.setToolTipText("Select a timeframe to search within.");

        User_SearchString_Label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        User_SearchString_Label.setText("Title, Description");

        User_Period_Label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        User_Period_Label.setText("Period");

        User_AllAdsResults_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Description", "Price", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        User_AllAdsResults_Table.getTableHeader().setReorderingAllowed(false);
        User_AllAdsResults_Container.setViewportView(User_AllAdsResults_Table);

        javax.swing.GroupLayout User_AllAds_TabLayout = new javax.swing.GroupLayout(User_AllAds_Tab);
        User_AllAds_Tab.setLayout(User_AllAds_TabLayout);
        User_AllAds_TabLayout.setHorizontalGroup(
            User_AllAds_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(User_AllAds_TabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(User_AllAds_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(User_AllAdsResults_Container)
                    .addGroup(User_AllAds_TabLayout.createSequentialGroup()
                        .addGroup(User_AllAds_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(User_AllAds_TabLayout.createSequentialGroup()
                                .addGroup(User_AllAds_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(User_Category_Label)
                                    .addComponent(User_Category_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(User_AllAds_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(User_Period_Label)
                                    .addComponent(User_Period_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(User_SearchString_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(User_Search_Button))
                            .addGroup(User_AllAds_TabLayout.createSequentialGroup()
                                .addGap(300, 300, 300)
                                .addComponent(User_SearchString_Label)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        User_AllAds_TabLayout.setVerticalGroup(
            User_AllAds_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(User_AllAds_TabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(User_AllAds_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(User_Category_Label)
                    .addComponent(User_Period_Label)
                    .addComponent(User_SearchString_Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(User_AllAds_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(User_Category_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(User_Period_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(User_SearchString_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(User_Search_Button))
                .addGap(18, 18, 18)
                .addComponent(User_AllAdsResults_Container, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        User_Tab_Container.addTab("All Advertisements", null, User_AllAds_Tab, "View all available advertisements.");

        User_MyAds_Tab.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                User_MyAds_TabComponentShown(evt);
            }
        });

        User_Delete_Button.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        User_Delete_Button.setText("Delete");
        User_Delete_Button.setToolTipText("Delete the currently highlighted row.");

        User_Edit_Button.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        User_Edit_Button.setText("Edit");
        User_Edit_Button.setToolTipText("Edit the currently highlighted row.");

        User_MyAdsResults_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ad ID", "Title", "Description", "Price", "Status", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        User_MyAdsResults_Table.getTableHeader().setReorderingAllowed(false);
        User_MyAdsResults_Container.setViewportView(User_MyAdsResults_Table);

        javax.swing.GroupLayout User_MyAds_TabLayout = new javax.swing.GroupLayout(User_MyAds_Tab);
        User_MyAds_Tab.setLayout(User_MyAds_TabLayout);
        User_MyAds_TabLayout.setHorizontalGroup(
            User_MyAds_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(User_MyAds_TabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(User_MyAds_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, User_MyAds_TabLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(User_Edit_Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(User_Delete_Button))
                    .addComponent(User_MyAdsResults_Container, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE))
                .addContainerGap())
        );
        User_MyAds_TabLayout.setVerticalGroup(
            User_MyAds_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(User_MyAds_TabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(User_MyAds_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(User_Delete_Button)
                    .addComponent(User_Edit_Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(User_MyAdsResults_Container, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        User_Tab_Container.addTab("My Advertisements", null, User_MyAds_Tab, "View my advertisements.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(User_Tab_Container)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(User_AddAd_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(User_AddAd_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(User_Tab_Container, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        User_Tab_Container.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void User_AllAds_TabComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_User_AllAds_TabComponentShown
        populateAllAdsTable();
    }//GEN-LAST:event_User_AllAds_TabComponentShown

    private void User_MyAds_TabComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_User_MyAds_TabComponentShown
        populateUserAdsTable(userID);
    }//GEN-LAST:event_User_MyAds_TabComponentShown

    private void User_AddAd_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_User_AddAd_ButtonActionPerformed
        AddAdvertisement addAd = new AddAdvertisement(/*this,*/ DB, userID);
        addAd.setVisible(true);
    }//GEN-LAST:event_User_AddAd_ButtonActionPerformed
    
    private void populateCategories() {
        this.User_Category_ComboBox.removeAllItems();
        this.User_Category_ComboBox.addItem(new Record("All", "All"));
        for (Record category : DB.getCategories()) {
            this.User_Category_ComboBox.addItem(category);
        }
    }
    
    public void populateAllAdsTable() {
        Object[][] User_allAds = DB.getAllActiveAds();
        this.User_AllAdsResults_Table.setModel(new DefaultTableModel(User_allAds, allAdsColumns));
    }
    
    public void populateUserAdsTable(String userID) {
        Object[][] User_myAds = DB.getAllUsersAds(userID);
        this.User_MyAdsResults_Table.setModel(new DefaultTableModel(User_myAds, userAdsColumns));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton User_AddAd_Button;
    private javax.swing.JScrollPane User_AllAdsResults_Container;
    private javax.swing.JTable User_AllAdsResults_Table;
    private javax.swing.JPanel User_AllAds_Tab;
    private javax.swing.JComboBox<Record> User_Category_ComboBox;
    private javax.swing.JLabel User_Category_Label;
    private javax.swing.JButton User_Delete_Button;
    private javax.swing.JButton User_Edit_Button;
    private javax.swing.JScrollPane User_MyAdsResults_Container;
    private javax.swing.JTable User_MyAdsResults_Table;
    private javax.swing.JPanel User_MyAds_Tab;
    private javax.swing.JComboBox<String> User_Period_ComboBox;
    private javax.swing.JLabel User_Period_Label;
    private javax.swing.JTextField User_SearchString_Field;
    private javax.swing.JLabel User_SearchString_Label;
    private javax.swing.JButton User_Search_Button;
    private javax.swing.JTabbedPane User_Tab_Container;
    // End of variables declaration//GEN-END:variables
}

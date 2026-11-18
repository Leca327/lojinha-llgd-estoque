package GUI;

import SubGui.Cad_Forn;
import SubGui.Cad_Lote;
import SubGui.Cad_Prod;

public class TelaPrin extends javax.swing.JFrame {

    public TelaPrin() {
        initComponents();
        setLocationRelativeTo(null);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_TelaPrin = new javax.swing.JPanel();
        Btn_prod = new javax.swing.JButton();
        Btn_forn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Menu_CRUD = new javax.swing.JMenu();
        Menu_Prod = new javax.swing.JMenu();
        Menu_Prod_cad = new javax.swing.JMenuItem();
        Menu_Prod_bus = new javax.swing.JMenuItem();
        Menu_Forn = new javax.swing.JMenu();
        Menu_Forn_cad = new javax.swing.JMenuItem();
        Menu_Forn_bus = new javax.swing.JMenuItem();
        Menu_Lote = new javax.swing.JMenu();
        Menu_Lote_cad = new javax.swing.JMenuItem();
        Menu_Lote_bus = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Panel_TelaPrin.setBackground(new java.awt.Color(252, 253, 253));

        Btn_prod.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Btn_prod.setText("Produto");
        Btn_prod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_prodActionPerformed(evt);
            }
        });

        Btn_forn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Btn_forn.setText("Fornecedor");
        Btn_forn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_fornActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logo.png"))); // NOI18N

        javax.swing.GroupLayout Panel_TelaPrinLayout = new javax.swing.GroupLayout(Panel_TelaPrin);
        Panel_TelaPrin.setLayout(Panel_TelaPrinLayout);
        Panel_TelaPrinLayout.setHorizontalGroup(
            Panel_TelaPrinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_TelaPrinLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(Btn_forn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(Btn_prod, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(Panel_TelaPrinLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_TelaPrinLayout.setVerticalGroup(
            Panel_TelaPrinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_TelaPrinLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(Panel_TelaPrinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_prod, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_forn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        jMenu1.setText("Home");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        Menu.add(jMenu1);

        Menu_CRUD.setText("CRUD");

        Menu_Prod.setText("Produto");

        Menu_Prod_cad.setText("Cadastrar");
        Menu_Prod_cad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_Prod_cadActionPerformed(evt);
            }
        });
        Menu_Prod.add(Menu_Prod_cad);

        Menu_Prod_bus.setText("Buscar");
        Menu_Prod_bus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_Prod_busActionPerformed(evt);
            }
        });
        Menu_Prod.add(Menu_Prod_bus);

        Menu_CRUD.add(Menu_Prod);

        Menu_Forn.setText("Fornecedor");

        Menu_Forn_cad.setText("Cadastrar");
        Menu_Forn_cad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_Forn_cadActionPerformed(evt);
            }
        });
        Menu_Forn.add(Menu_Forn_cad);

        Menu_Forn_bus.setText("Buscar");
        Menu_Forn_bus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_Forn_busActionPerformed(evt);
            }
        });
        Menu_Forn.add(Menu_Forn_bus);

        Menu_CRUD.add(Menu_Forn);

        Menu_Lote.setText("Lote");

        Menu_Lote_cad.setText("Cadastrar");
        Menu_Lote_cad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_Lote_cadActionPerformed(evt);
            }
        });
        Menu_Lote.add(Menu_Lote_cad);

        Menu_Lote_bus.setText("Buscar");
        Menu_Lote_bus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_Lote_busActionPerformed(evt);
            }
        });
        Menu_Lote.add(Menu_Lote_bus);

        Menu_CRUD.add(Menu_Lote);

        Menu.add(Menu_CRUD);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_TelaPrin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_TelaPrin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_fornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_fornActionPerformed
        dispose();
        Fornecedor forn = new Fornecedor();
        forn.setVisible(true);
    }//GEN-LAST:event_Btn_fornActionPerformed

    private void Btn_prodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_prodActionPerformed
        dispose();
        Produto prod = new Produto();
        prod.setVisible(true);

    }//GEN-LAST:event_Btn_prodActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        TelaPrin tel = new TelaPrin();
        dispose();
        tel.setVisible(true);
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void Menu_Prod_cadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_Prod_cadActionPerformed
        Cad_Prod tel = new Cad_Prod();
        dispose();
        tel.setVisible(true);
    }//GEN-LAST:event_Menu_Prod_cadActionPerformed

    private void Menu_Prod_busActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_Prod_busActionPerformed
        Produto tel = new Produto();
        dispose();
        tel.setVisible(true);
    }//GEN-LAST:event_Menu_Prod_busActionPerformed

    private void Menu_Forn_cadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_Forn_cadActionPerformed
        Cad_Forn tel = new Cad_Forn();
        dispose();
        tel.setVisible(true);
    }//GEN-LAST:event_Menu_Forn_cadActionPerformed

    private void Menu_Forn_busActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_Forn_busActionPerformed
        Fornecedor tel = new Fornecedor();
        dispose();
        tel.setVisible(true);
    }//GEN-LAST:event_Menu_Forn_busActionPerformed

    private void Menu_Lote_cadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_Lote_cadActionPerformed
        Cad_Lote tel = new Cad_Lote();
        dispose();
        tel.setVisible(true);
    }//GEN-LAST:event_Menu_Lote_cadActionPerformed

    private void Menu_Lote_busActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_Lote_busActionPerformed
        Lote tel = new Lote();
        dispose();
        tel.setVisible(true);
    }//GEN-LAST:event_Menu_Lote_busActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_forn;
    private javax.swing.JButton Btn_prod;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenu Menu_CRUD;
    private javax.swing.JMenu Menu_Forn;
    private javax.swing.JMenuItem Menu_Forn_bus;
    private javax.swing.JMenuItem Menu_Forn_cad;
    private javax.swing.JMenu Menu_Lote;
    private javax.swing.JMenuItem Menu_Lote_bus;
    private javax.swing.JMenuItem Menu_Lote_cad;
    private javax.swing.JMenu Menu_Prod;
    private javax.swing.JMenuItem Menu_Prod_bus;
    private javax.swing.JMenuItem Menu_Prod_cad;
    private javax.swing.JPanel Panel_TelaPrin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    // End of variables declaration//GEN-END:variables
}

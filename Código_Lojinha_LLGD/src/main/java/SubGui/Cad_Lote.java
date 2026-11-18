/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package SubGui;

import GUI.Fornecedor;
import GUI.Lote;
import GUI.TelaPrin;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Cad_Lote extends javax.swing.JFrame {

    public Cad_Lote() {
        initComponents();
        setLocationRelativeTo(null);
        carregarCodigosFornecedores();
        carregarCodigosProdutos();
    }
    
    public void carregarCodigosProdutos() {
        // Limpa o ComboBox antes de adicionar novos itens
        cb_prod.removeAllItems();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("prod.txt"));
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");

                if (dados.length >= 1) {
                    // Código está na posição 0 (coluna 1)
                    String codigo = dados[0].trim();
                    cb_prod.addItem(codigo);
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Arquivo prod.txt não encontrado!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo: " + e.getMessage());
        }
    }
    
    public void carregarCodigosFornecedores() {
        // Limpa o ComboBox antes de adicionar novos itens
        cb_forn.removeAllItems();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("forn.txt"));
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");

                if (dados.length >= 2) {
                    // Formato: cnpj, codigo, nome, contato, endereco
                    String codigo = dados[1].trim(); // Código está na posição 1 (coluna 2)
                    cb_forn.addItem(codigo);
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Arquivo forn.txt não encontrado!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public void vlt() {
        Lote tel = new Lote();
        dispose();
        tel.setVisible(true);
    }

    public void limpacampos() {
        cb_forn.setSelectedItem(null);
        cb_prod.setSelectedItem(null);
        txt_val.setText("");
        spi_quant.setValue(0);
        txt_cod.setText("");

    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public Boolean validacampo() {
        //Boolean resp = false;

        if ((cb_forn.getSelectedItem() == null) || txt_cod.getText().isEmpty() || (cb_prod.getSelectedItem() == null) || txt_val.getText().equals("    -  -  ") || txt_cod.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Possui campo em branco", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            String dt = txt_val.getText();

            int year = Integer.parseInt(dt.substring(0, 4));
            int month = Integer.parseInt(dt.substring(5, 7));
            int day = Integer.parseInt(dt.substring(8, 10));
            //verifica se o mes é valido
            if (month < 1 || month > 12) {
                JOptionPane.showMessageDialog(null, "Mês Inválido", "Erro", JOptionPane.ERROR_MESSAGE);

            } else {
                //verifica se os dias existem no mes
                int[] daysInMonth = {31, (isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                if (day < 1 || day > daysInMonth[month - 1]) {
                    JOptionPane.showMessageDialog(null, "Dia Inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {

                    return true;

                }
                //return resp;

            }
        }
        return false;
    }

    public void inserirClienteArqTexto(String codigo, String val, int quant, String forn, String prod) {
        if (validacampo() == true) {
            try {
                String linha = codigo + "," + val + "," + quant + "," + forn + "," + prod;

                BufferedWriter w_forn = new BufferedWriter(new FileWriter("lote.txt", true));
                w_forn.write(linha); //grava a linha no arquivo cliente.txt
                w_forn.newLine(); //cria (pula para) nova linha
                w_forn.close();

                JOptionPane.showMessageDialog(this, "Novo Lote inserido com sucesso!");
                limpacampos();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo!" + e.getMessage());
            }
        } else {

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_cad = new SwingPerson.JbuttonArr();
        btn_cancel = new SwingPerson.JbuttonArr();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_val = new javax.swing.JFormattedTextField();
        cb_forn = new javax.swing.JComboBox<>();
        cb_prod = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        spi_quant = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        txt_cod = new javax.swing.JTextField();
        lab_cod = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(252, 253, 253));

        jPanel2.setBackground(new java.awt.Color(148, 111, 65));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cadastro Fornecedor");

        btn_cad.setForeground(new java.awt.Color(109, 165, 64));
        btn_cad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/cadastrarm.png"))); // NOI18N
        btn_cad.setToolTipText("Cadastrar Fornecedor");
        btn_cad.setPreferredSize(new java.awt.Dimension(35, 35));
        btn_cad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadActionPerformed(evt);
            }
        });

        btn_cancel.setForeground(new java.awt.Color(109, 165, 64));
        btn_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_sair.png"))); // NOI18N
        btn_cancel.setToolTipText("Cancelar Operaçao");
        btn_cancel.setPreferredSize(new java.awt.Dimension(35, 35));
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Fornecedor");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Validade");

        txt_val.setBorder(null);
        try {
            txt_val.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_val.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_val.setMinimumSize(new java.awt.Dimension(0, 0));
        txt_val.setPreferredSize(new java.awt.Dimension(200, 20));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Produto");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Quantidade");

        txt_cod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_codKeyPressed(evt);
            }
        });

        lab_cod.setForeground(new java.awt.Color(255, 255, 255));
        lab_cod.setText("Código");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lab_cod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cod, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cb_forn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cb_prod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_val, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(spi_quant))))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(cb_prod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_forn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_val, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(spi_quant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_cod)
                    .addComponent(txt_cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_cad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadActionPerformed
        inserirClienteArqTexto(txt_cod.getText(), txt_val.getText(), (int) spi_quant.getValue(), (String) cb_forn.getSelectedItem(), (String) cb_prod.getSelectedItem());
    }//GEN-LAST:event_btn_cadActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        vlt();
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void txt_codKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            inserirClienteArqTexto(txt_cod.getText(), txt_val.getText(), (int) spi_quant.getValue(), (String) cb_forn.getSelectedItem(), (String) cb_prod.getSelectedItem());
        }
    }//GEN-LAST:event_txt_codKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cad_Lote().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private SwingPerson.JbuttonArr btn_cad;
    private SwingPerson.JbuttonArr btn_cancel;
    private javax.swing.JComboBox<String> cb_forn;
    private javax.swing.JComboBox<String> cb_prod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lab_cod;
    private javax.swing.JSpinner spi_quant;
    private javax.swing.JTextField txt_cod;
    private javax.swing.JFormattedTextField txt_val;
    // End of variables declaration//GEN-END:variables
}

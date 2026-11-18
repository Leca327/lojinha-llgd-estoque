package SubGui;

import GUI.Fornecedor;
import GUI.TelaPrin;
import static SubGui.Cad_Forn.formatcnpj;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Att_Forn extends javax.swing.JFrame {

    String cnpj, cod, nome, cont, end;

    public Att_Forn(String cnpj, String cod, String nome, String cont, String end) {
        initComponents();
        setLocationRelativeTo(null);
        this.cnpj = cnpj;
        this.cod = cod;
        this.nome = nome;
        this.cont = cont;
        this.end = end;
        txt_cod.setEditable(false);
        txt_cod.setFocusable(false);
        txt_cnpj.setText(cnpj);
        txt_nome.setText(nome);
        txt_end.setText(end);
        txt_cont.setText(cont);
        txt_cod.setText(cod);
    }

    public void vlt() {
        Fornecedor tel = new Fornecedor();
        dispose();
        tel.setVisible(true);
    }

    public void limpacampos() {
        txt_cnpj.setText("");
        txt_nome.setText("");
        txt_end.setText("");
        txt_cont.setText("");
        txt_cod.setText("");

    }

    public static String formatcont(String cont) {
        String dd = cont.substring(1, 3);
        String num = cont.substring(4);
        cont = dd + num.substring(0, 5) + num.substring(6);

        return cont;
    }

    public static String formatcnpj(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", "");
        return cnpj;
    }

    public Boolean validacampo() {
        //Boolean resp = false;

        if (txt_nome.getText().isEmpty() || txt_cod.getText().isEmpty() || txt_cnpj.getText().isEmpty() || txt_cont.getText().equals("(  )     -    ") || txt_end.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Possui campo em branco", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            if (formatcnpj(txt_cnpj.getText()).length() != 14) {
                JOptionPane.showMessageDialog(null, "CNPJ deve ter 14 dígitos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                if (!formatcnpj(txt_cnpj.getText()).matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "CNPJ deve conter apenas números!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                    return true;
                }

            }

        }
        //return resp;

    }

    public void AttFornArqTexto(String codigo, String nome, String cnpj, String end, String cont) {
        if (validacampo() == true) {
            try {
                // Ler todas as linhas do arquivo
                BufferedReader reader = new BufferedReader(new FileReader("forn.txt"));
                StringBuilder conteudo = new StringBuilder();
                String linha;
                boolean encontrou = false;

                while ((linha = reader.readLine()) != null) {
                    String[] dados = linha.split(",");

                    // Verifica se é a linha do fornecedor pelo código (posição 1)
                    if (dados.length >= 2 && dados[1].equals(codigo)) {
                        // Substitui pela nova linha com os dados atualizados
                        String novaLinha = cnpj + "," + codigo + "," + nome + "," + cont + "," + end;
                        conteudo.append(novaLinha).append("\n");
                        encontrou = true;
                    } else {
                        // Mantém a linha original
                        conteudo.append(linha).append("\n");
                    }
                }
                reader.close();

                if (encontrou) {
                    // Reescreve o arquivo com o conteúdo atualizado
                    BufferedWriter writer = new BufferedWriter(new FileWriter("forn.txt"));
                    writer.write(conteudo.toString());
                    writer.close();

                    JOptionPane.showMessageDialog(this, "Fornecedor atualizado com sucesso!");
                    vlt(); // Volta para a tela de Fornecedor
                } else {
                    JOptionPane.showMessageDialog(this, "Fornecedor não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Arquivo forn.txt não encontrado!" + e.getMessage());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar o arquivo!" + e.getMessage());
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        txt_end = new javax.swing.JTextField();
        btn_att = new SwingPerson.JbuttonArr();
        btn_cancel = new SwingPerson.JbuttonArr();
        txt_cont = new javax.swing.JFormattedTextField();
        lab_cod = new javax.swing.JLabel();
        txt_cod = new javax.swing.JTextField();
        txt_cnpj = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(252, 253, 253));

        jPanel2.setBackground(new java.awt.Color(148, 111, 65));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cadastro Fornecedor");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CNPJ");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nome");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Contato");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Endereço");

        txt_nome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nomeKeyPressed(evt);
            }
        });

        txt_end.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_endKeyPressed(evt);
            }
        });

        btn_att.setForeground(new java.awt.Color(109, 165, 64));
        btn_att.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/atualizarm.png"))); // NOI18N
        btn_att.setToolTipText("Atualizar Fornecedor");
        btn_att.setPreferredSize(new java.awt.Dimension(35, 35));
        btn_att.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_attActionPerformed(evt);
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

        txt_cont.setBorder(null);
        try {
            txt_cont.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_cont.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_cont.setMinimumSize(new java.awt.Dimension(0, 0));
        txt_cont.setPreferredSize(new java.awt.Dimension(200, 20));
        txt_cont.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_contKeyPressed(evt);
            }
        });

        lab_cod.setForeground(new java.awt.Color(255, 255, 255));
        lab_cod.setText("Código");

        txt_cnpj.setBorder(null);
        try {
            txt_cnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_cnpj.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_cnpj.setPreferredSize(new java.awt.Dimension(200, 20));
        txt_cnpj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cnpjKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lab_cod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cod, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_cnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_cont, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_att, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_end))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_nome)))
                        .addGap(20, 20, 20))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_cont, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4)
                        .addComponent(txt_cnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_cod)
                    .addComponent(txt_cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_att, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btn_attActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_attActionPerformed
        AttFornArqTexto(txt_cod.getText(), txt_nome.getText(), formatcnpj(txt_cnpj.getText()), txt_end.getText(), formatcont(txt_cont.getText()));
    }//GEN-LAST:event_btn_attActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        vlt();
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void txt_cnpjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cnpjKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            AttFornArqTexto(txt_cnpj.getText(), txt_nome.getText(), formatcnpj(txt_cnpj.getText()), txt_end.getText(), formatcont(txt_cont.getText()));
        }
    }//GEN-LAST:event_txt_cnpjKeyPressed

    private void txt_contKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_contKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            AttFornArqTexto(txt_cnpj.getText(), txt_nome.getText(), formatcnpj(txt_cnpj.getText()), txt_end.getText(), formatcont(txt_cont.getText()));
        }
    }//GEN-LAST:event_txt_contKeyPressed

    private void txt_nomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nomeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            AttFornArqTexto(txt_cnpj.getText(), txt_nome.getText(), formatcnpj(txt_cnpj.getText()), txt_end.getText(), formatcont(txt_cont.getText()));
        }
    }//GEN-LAST:event_txt_nomeKeyPressed

    private void txt_endKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_endKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            AttFornArqTexto(txt_cnpj.getText(), txt_nome.getText(), formatcnpj(txt_cnpj.getText()), txt_end.getText(), formatcont(txt_cont.getText()));
        }
    }//GEN-LAST:event_txt_endKeyPressed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Att_Forn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Att_Forn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Att_Forn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Att_Forn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new Att_Forn("", "", "", "", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private SwingPerson.JbuttonArr btn_att;
    private SwingPerson.JbuttonArr btn_cancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lab_cod;
    private javax.swing.JFormattedTextField txt_cnpj;
    private javax.swing.JTextField txt_cod;
    private javax.swing.JFormattedTextField txt_cont;
    private javax.swing.JTextField txt_end;
    private javax.swing.JTextField txt_nome;
    // End of variables declaration//GEN-END:variables
}

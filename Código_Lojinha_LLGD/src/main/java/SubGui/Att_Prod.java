package SubGui;

import GUI.Fornecedor;
import GUI.Produto;
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

public class Att_Prod extends javax.swing.JFrame {

    String cod, cat, nome, preco;

    public Att_Prod(String cod, String cat, String nome, String preco) {
        initComponents();
        setLocationRelativeTo(null);
        this.cat = cat;
        this.cod = cod;
        this.nome = nome;
        this.preco = preco;
        txt_cod.setEditable(false);
        txt_cod.setFocusable(false);
        txt_preco.setText(preco);
        txt_nome.setText(nome);
        txt_cat.setText(cat);
        txt_cod.setText(cod);
    }

    public void vlt() {
        Produto tel = new Produto();
        dispose();
        tel.setVisible(true);
    }

    public void limpacampos() {

        txt_nome.setText("");
        txt_cat.setText("");
        txt_preco.setText("00000000");
        txt_cod.setText("");

    }

    public static String formatpreco(String input) {
        // Remova todos os caracteres que não sejam números, vírgulas ou pontos.
        input = input.replaceAll("[^0-9,.]", "");

        // Substitua a vírgula por um ponto.
        input = input.replace(",", ".");

        // Remova os zeros à esquerda até encontrar o primeiro número diferente de zero ou até encontrar um ponto.
        int index = 0;
        while (index < input.length() && (input.charAt(index) == '0' || input.charAt(index) == '.')) {
            index++;
        }

        // Se o primeiro número diferente de zero for após um ponto, adicione um zero à esquerda.
        if (index < input.length() && input.charAt(index) == '.') {
            input = "0" + input;
        }
        if (input.equals("R$000000.00")) {
            input = "0.00";
        }
        // Retorne o valor formatado.
        return input;
    }

    public Boolean validacampo() {
        //Boolean resp = false;

        if (txt_nome.getText().isEmpty() || txt_preco.getText().equals("R$000000.00") || txt_cat.getText().isEmpty() || txt_cod.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Possui campo em branco", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
        //return resp;

    }

    public void AttProdArqTexto(String codigo, String cat, String nome, String preco) {
        if (validacampo() == true) {
            try {
                // Ler todas as linhas do arquivo
                BufferedReader reader = new BufferedReader(new FileReader("prod.txt"));
                StringBuilder conteudo = new StringBuilder();
                String linha;
                boolean encontrou = false;

                while ((linha = reader.readLine()) != null) {
                    String[] dados = linha.split(",");

                    // Verifica se é a linha do fornecedor pelo código (posição 1)
                    if (dados.length >= 2 && dados[0].equals(codigo)) {
                        // Substitui pela nova linha com os dados atualizados
                        String novaLinha = codigo + "," + cat + "," + nome + "," + preco;
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
                    BufferedWriter writer = new BufferedWriter(new FileWriter("prod.txt"));
                    writer.write(conteudo.toString());
                    writer.close();

                    JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso!");
                    vlt(); // Volta para a tela de Fornecedor
                } else {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Arquivo prod.txt não encontrado!" + e.getMessage());
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
        jLabel5 = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        txt_cat = new javax.swing.JTextField();
        btn_att = new SwingPerson.JbuttonArr();
        btn_cancel = new SwingPerson.JbuttonArr();
        lab_cod = new javax.swing.JLabel();
        txt_cod = new javax.swing.JTextField();
        txt_preco = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(252, 253, 253));

        jPanel2.setBackground(new java.awt.Color(148, 111, 65));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cadastro Fornecedor");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Preço");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nome");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Categoria");

        txt_nome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nomeKeyPressed(evt);
            }
        });

        txt_cat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_catKeyPressed(evt);
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

        lab_cod.setForeground(new java.awt.Color(255, 255, 255));
        lab_cod.setText("Código");

        txt_cod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_codKeyPressed(evt);
            }
        });

        txt_preco.setBorder(null);
        try {
            txt_preco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("R$######.##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_preco.setText("R$000000.00");
        txt_preco.setPreferredSize(new java.awt.Dimension(65, 20));
        txt_preco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_precoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_att, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cat))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nome))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lab_cod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cod, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_preco, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_preco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab_cod)
                    .addComponent(txt_cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_cat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
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

        AttProdArqTexto(txt_cod.getText(), txt_cat.getText(), txt_nome.getText(), formatpreco(txt_preco.getText()));

    }//GEN-LAST:event_btn_attActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        vlt();
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void txt_nomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nomeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            AttProdArqTexto(txt_cod.getText(), txt_cat.getText(), txt_nome.getText(), formatpreco(txt_preco.getText()));
        }
    }//GEN-LAST:event_txt_nomeKeyPressed

    private void txt_catKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_catKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            AttProdArqTexto(txt_cod.getText(), txt_cat.getText(), txt_nome.getText(), formatpreco(txt_preco.getText()));
        }
    }//GEN-LAST:event_txt_catKeyPressed

    private void txt_precoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            AttProdArqTexto(txt_cod.getText(), txt_cat.getText(), txt_nome.getText(), formatpreco(txt_preco.getText()));
        }
    }//GEN-LAST:event_txt_precoKeyPressed

    private void txt_codKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            AttProdArqTexto(txt_cod.getText(),txt_cat.getText(), txt_nome.getText(), formatpreco(txt_preco.getText()));
        }
    }//GEN-LAST:event_txt_codKeyPressed

    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new Att_Prod("", "", "", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private SwingPerson.JbuttonArr btn_att;
    private SwingPerson.JbuttonArr btn_cancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lab_cod;
    private javax.swing.JFormattedTextField textf_preco;
    private javax.swing.JFormattedTextField textf_preco1;
    private javax.swing.JTextField txt_cat;
    private javax.swing.JTextField txt_cod;
    private javax.swing.JTextField txt_nome;
    private javax.swing.JFormattedTextField txt_preco;
    // End of variables declaration//GEN-END:variables
}

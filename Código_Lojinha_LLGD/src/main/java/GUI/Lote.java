package GUI;

import SubGui.Cad_Lote;
import SubGui.Att_Lote;
import SubGui.Cad_Forn;
import SubGui.Cad_Lote;
import SubGui.Cad_Prod;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class Lote extends javax.swing.JFrame {

    String sel;

    public Lote() {
        initComponents();
        setLocationRelativeTo(null);
        carregarFornecedores();
        textf_copiado.setEditable(false);
        textf_copiado.setFocusable(false);
        encontrado();

        tabProd.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabProd.getSelectedRow();
                int column = tabProd.getSelectedColumn();
                sel = tabProd.getValueAt(row, 2).toString();
                Object value = tabProd.getValueAt(row, column);
                String campoSelecionado;
                if (value != null && !value.toString().isEmpty()) {
                    campoSelecionado = value.toString();
                    textf_copiado.setText(campoSelecionado);

                } else {
                    campoSelecionado = "";
                    textf_copiado.setText("");
                }

            }
        });
    }

    public void enctab() {
        DefaultTableModel dtm = (DefaultTableModel) tabProd.getModel();
        int total = dtm.getRowCount();
        textf_result.setText(total + " Resultados Encontrados");
    }

    public void encontrado() {
        int numeroLinhas = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("lote.txt"));

            // Conta o número de linhas no arquivo
            while (reader.readLine() != null) {
                numeroLinhas++;
            }
            reader.close();

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo!" + e.getMessage());

        }

        textf_result.setText(numeroLinhas + " Resultados Encontrados");
    }

    public void lmp() {
        tsl();
        textf_buscar.setText("");
        textf_copiado.setText("");

        DefaultTableModel modelo = (DefaultTableModel) tabProd.getModel();
        modelo.setRowCount(0);
        textf_result.setText("0 Resultados Encontrados");

    }

    private void buscarForn(String cod) {
        DefaultTableModel dtm_cliente = (DefaultTableModel) tabProd.getModel();
        DefaultTableModel modeloFiltrado = new DefaultTableModel();

        // Define as colunas do modelo filtrado
        modeloFiltrado.addColumn("Código do Lote");
        modeloFiltrado.addColumn("Validade");
        modeloFiltrado.addColumn("Quantidade");
        modeloFiltrado.addColumn("Fornecedor");
        modeloFiltrado.addColumn("Produto");

        int resultadosEncontrados = 0;

        // Percorre as linhas da tabela para verificar o código de cada fornecedor
        for (int i = 0; i < dtm_cliente.getRowCount(); i++) {
            String codigo = dtm_cliente.getValueAt(i, 0).toString(); // Código está na coluna 1

            // Verifica se o código contém o código que se deseja pesquisar
            if (codigo.toLowerCase().contains(cod.toLowerCase())) {
                // Adiciona a linha completa ao modelo filtrado
                Object[] linha = new Object[5];
                for (int j = 0; j < 5; j++) {
                    linha[j] = dtm_cliente.getValueAt(i, j);
                }
                modeloFiltrado.addRow(linha);
                resultadosEncontrados++;
            }
        }

        // Atualiza a tabela com os resultados filtrados
        tabProd.setModel(modeloFiltrado);

        // Atualiza o label de resultados
        enctab();

        // Exibe mensagem se não encontrou nada
        if (resultadosEncontrados == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum fornecedor encontrado com o código: " + cod);
        }
    }

    public void transferirTableParaArqTexto() throws IOException {
        String linha;
        BufferedWriter w_cliente = new BufferedWriter(new FileWriter("lote.txt"));//abre o arquivo para escrita, apagando todo seu conteúdo,
        DefaultTableModel dtm_cliente = (DefaultTableModel) tabProd.getModel();
        int linhasTabela = dtm_cliente.getRowCount(); //quantidade de linhas na tabela

        for (int i = 0; i < linhasTabela; i++) {
            Object cod = dtm_cliente.getValueAt(i, 0);
            Object val = dtm_cliente.getValueAt(i, 1);
            Object quant = dtm_cliente.getValueAt(i, 2);
            Object forn = dtm_cliente.getValueAt(i, 3);
            Object prod = dtm_cliente.getValueAt(i, 4);
            linha = cod + "," + val + "," + quant + "," + forn + "," + prod;
            w_cliente.write(linha);
            w_cliente.newLine();
        }
        w_cliente.close();
    }

    public void del() {
        if (JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro?") == 0) {
            int linha = tabProd.getSelectedRow();
            DefaultTableModel dtm_cliente = (DefaultTableModel) tabProd.getModel();
            dtm_cliente.removeRow(linha);
            try {
                transferirTableParaArqTexto(); //transfere os dados da table para o arquivo texto
            } catch (IOException ex) {
                Logger.getLogger(Lote.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Lote excluído com sucesso!");
        }
    }

    public void cad() {
        Cad_Lote tel = new Cad_Lote();
        dispose();
        tel.setVisible(true);
    }

    public void tsl() {

        tabProd.clearSelection();
        textf_copiado.setText("");

    }

    public void att() {

        if (sel == null) {
            JOptionPane.showMessageDialog(null, "Campo Nao selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            int row = tabProd.getSelectedRow();

            Att_Lote tel = new Att_Lote(tabProd.getValueAt(row, 0).toString(), tabProd.getValueAt(row, 1).toString(), Integer.parseInt(tabProd.getValueAt(row, 2).toString()), tabProd.getValueAt(row, 3).toString(), tabProd.getValueAt(row, 4).toString());
            dispose();
            tel.setVisible(true);

        }
    }

    public void carregarFornecedores() {
        DefaultTableModel modelo = (DefaultTableModel) tabProd.getModel();
        modelo.setRowCount(0); // Limpa a tabela

        try {
            BufferedReader reader = new BufferedReader(new FileReader("lote.txt"));
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");

                if (dados.length >= 5) {

                    String cod = dados[0];
                    String val = dados[1];
                    String quant = dados[2];
                    String forn = dados[3];
                    String prod = dados[4];

                    modelo.addRow(new Object[]{cod, val, quant, forn, prod});
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Arquivo lote.txt não encontrado!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabProd = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Latt = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textf_buscar = new javax.swing.JTextField();
        Lbuscar = new javax.swing.JLabel();
        btn_tirarsel = new SwingPerson.JbuttonArr();
        btn_limpar = new SwingPerson.JbuttonArr();
        jLabel1 = new javax.swing.JLabel();
        textf_result = new javax.swing.JLabel();
        btn_del = new SwingPerson.JbuttonArr();
        btn_att = new javax.swing.JButton();
        btn_cad = new javax.swing.JButton();
        label_selecionado = new javax.swing.JLabel();
        textf_copiado = new javax.swing.JTextField();
        label_copy = new javax.swing.JLabel();
        btn_cancel = new SwingPerson.JbuttonArr();
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

        jPanel1.setBackground(new java.awt.Color(148, 111, 65));

        tabProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código do Lote", "Validade", "Quantidade", "Fornecedor", "Produto"
            }
        ));
        jScrollPane1.setViewportView(tabProd);

        jPanel2.setBackground(new java.awt.Color(252, 253, 253));
        jPanel2.setPreferredSize(new java.awt.Dimension(397, 200));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Buscar Lote");

        Latt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_att.png"))); // NOI18N
        Latt.setToolTipText("Atualize a Tabela");
        Latt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Latt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LattMouseClicked(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/campotexto2.jpg"))); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(363, 3));

        textf_buscar.setBackground(new java.awt.Color(252, 253, 253));
        textf_buscar.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        textf_buscar.setToolTipText("");
        textf_buscar.setBorder(null);
        textf_buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textf_buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textf_buscarMouseClicked(evt);
            }
        });
        textf_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textf_buscarKeyPressed(evt);
            }
        });

        Lbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/lupa.png"))); // NOI18N
        Lbuscar.setToolTipText("Buscar Fornecedor");
        Lbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Lbuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LbuscarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LbuscarMouseEntered(evt);
            }
        });

        btn_tirarsel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_tselect.png"))); // NOI18N
        btn_tirarsel.setToolTipText("Tirar Seleção da Tabela");
        btn_tirarsel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tirarselActionPerformed(evt);
            }
        });

        btn_limpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_apagar.png"))); // NOI18N
        btn_limpar.setToolTipText("Limpar Infomações");
        btn_limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limparActionPerformed(evt);
            }
        });

        jLabel1.setText("Código");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(textf_buscar, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Lbuscar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Latt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_limpar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_tirarsel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(7, 7, 7)
                        .addComponent(textf_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Lbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_limpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_tirarsel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Latt, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(20, 20, 20))
        );

        textf_result.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        textf_result.setForeground(new java.awt.Color(255, 253, 243));
        textf_result.setText("x Resultados Encontrados");
        textf_result.setToolTipText("Resultados Encontrados na Tabela de Admin");
        textf_result.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btn_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/deletarm.png"))); // NOI18N
        btn_del.setToolTipText("Deletar Lote Selecionado");
        btn_del.setPreferredSize(new java.awt.Dimension(35, 35));
        btn_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delActionPerformed(evt);
            }
        });

        btn_att.setBackground(new java.awt.Color(148, 111, 65));
        btn_att.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_att.setForeground(new java.awt.Color(255, 255, 255));
        btn_att.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/atualizarbr.png"))); // NOI18N
        btn_att.setToolTipText("Atualizar Lote Selecionado");
        btn_att.setBorder(null);
        btn_att.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_att.setPreferredSize(new java.awt.Dimension(35, 35));
        btn_att.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_attActionPerformed(evt);
            }
        });

        btn_cad.setBackground(new java.awt.Color(148, 111, 65));
        btn_cad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_cad.setForeground(new java.awt.Color(255, 255, 255));
        btn_cad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_cadbr.png"))); // NOI18N
        btn_cad.setToolTipText("Cadastrar Lote");
        btn_cad.setBorder(null);
        btn_cad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cad.setPreferredSize(new java.awt.Dimension(35, 35));
        btn_cad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadActionPerformed(evt);
            }
        });

        label_selecionado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_selecionado.setForeground(new java.awt.Color(242, 242, 242));
        label_selecionado.setText("Valor Selecionado");

        textf_copiado.setEditable(false);
        textf_copiado.setBackground(new java.awt.Color(252, 253, 253));
        textf_copiado.setToolTipText("Campo Selecionado na Tabela");
        textf_copiado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        textf_copiado.setMinimumSize(new java.awt.Dimension(500, 20));
        textf_copiado.setName(""); // NOI18N
        textf_copiado.setPreferredSize(new java.awt.Dimension(500, 20));

        label_copy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_copy.png"))); // NOI18N
        label_copy.setToolTipText("Copiar Informação no Campo de Seleção");
        label_copy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_copy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_copyMouseClicked(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(textf_result)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 913, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_selecionado)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textf_copiado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label_copy))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_att, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_del, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(label_selecionado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label_copy)
                            .addComponent(textf_copiado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(textf_result)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_cad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_att, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_del, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LattMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LattMouseClicked
        carregarFornecedores();
    }//GEN-LAST:event_LattMouseClicked

    private void textf_buscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textf_buscarMouseClicked
        if (textf_buscar.isEditable()) {
            if (textf_buscar.isEditable()) {
                //limpar(); // Chama o método "limpar" se o campo estiver editável
            }
        }
    }//GEN-LAST:event_textf_buscarMouseClicked

    private void textf_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textf_buscarKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String codigoBusca = textf_buscar.getText().trim();

            if (codigoBusca.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Digite um código para buscar!", "Aviso", JOptionPane.WARNING_MESSAGE);
                carregarFornecedores();
            } else {
                buscarForn(codigoBusca);
            }
        }
    }//GEN-LAST:event_textf_buscarKeyPressed

    private void LbuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LbuscarMouseClicked
        buscarForn(textf_buscar.getText());
    }//GEN-LAST:event_LbuscarMouseClicked

    private void LbuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LbuscarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_LbuscarMouseEntered

    private void btn_tirarselActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tirarselActionPerformed
        tsl();
    }//GEN-LAST:event_btn_tirarselActionPerformed

    private void btn_limparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limparActionPerformed
        lmp();
        enctab();
    }//GEN-LAST:event_btn_limparActionPerformed

    private void btn_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delActionPerformed
        del();
        tsl();
    }//GEN-LAST:event_btn_delActionPerformed

    private void btn_attActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_attActionPerformed
        att();

    }//GEN-LAST:event_btn_attActionPerformed

    private void btn_cadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadActionPerformed

//  tsl();
        cad();
    }//GEN-LAST:event_btn_cadActionPerformed

    private void label_copyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_copyMouseClicked
        Clipboard board = Toolkit.getDefaultToolkit().getSystemClipboard();
        ClipboardOwner selecao = new StringSelection(textf_copiado.getText());
        board.setContents((Transferable) selecao, selecao);
        label_copy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_copyokk.png")));

        int delay = 5000; // 10 segundos em milissegundos
        Timer timer = new Timer(delay, e -> {
            label_copy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_copy.png")));
        });
        timer.setRepeats(false); // Definir para não repetir a tarefa

        timer.start();
    }//GEN-LAST:event_label_copyMouseClicked

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        TelaPrin tel = new TelaPrin();
        dispose();
        tel.setVisible(true);
    }//GEN-LAST:event_btn_cancelActionPerformed

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
                new Lote().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Latt;
    private javax.swing.JLabel Lbuscar;
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
    private javax.swing.JButton btn_att;
    private javax.swing.JButton btn_cad;
    private SwingPerson.JbuttonArr btn_cancel;
    private SwingPerson.JbuttonArr btn_del;
    private SwingPerson.JbuttonArr btn_limpar;
    private SwingPerson.JbuttonArr btn_tirarsel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_copy;
    private javax.swing.JLabel label_selecionado;
    private javax.swing.JTable tabProd;
    private javax.swing.JTextField textf_buscar;
    private javax.swing.JTextField textf_copiado;
    private javax.swing.JLabel textf_result;
    // End of variables declaration//GEN-END:variables
}

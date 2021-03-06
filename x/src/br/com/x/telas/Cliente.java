/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.x.telas;

import br.com.x.dal.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import net.proteanit.sql.DbUtils;

/**
 * Classe responsável pela Tela de Usuario do programa
 *
 * @author Felipe Muniz, 2016, INFO-X
 */
public class Cliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form Cliente
     */
    public Cliente() {
        initComponents();
        conexao = Conexao.conector();
    }

    //Método responsavel por pesquisar clientes pelo nome com filtreo avançado
    private void Read() {

        String sql = "SELECT * FROM clientes WHERE nome LIKE ?";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtClientePesquisa.getText() + '%');
            rs = pst.executeQuery();

            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    //Método responsavel por setar os campos do cliente com a tabela
    private void setFields() {
        int setar = tblClientes.getSelectedRow();
        
        lblClienteId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
        txtClienteNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
        txtClienteEndereco.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
        txtClienteFone.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
        txtClienteEmail.setText(tblClientes.getModel().getValueAt(setar, 4).toString());
    }

    //Método responsavel pelo cadastro de clientes no banco de dados
    private void create() {

        String sql = "INSERT INTO clientes(nome,endereco,fone,email) VALUES(?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtClienteNome.getText());
            pst.setString(2, txtClienteEndereco.getText());
            pst.setString(3, txtClienteFone.getText());
            pst.setString(4, txtClienteEmail.getText());

            //Verifica se os campos obrigatórios foram preenchidos
            if (txtClienteNome.getText().isEmpty() || txtClienteFone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios! *");
            } else {

                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {

                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");

                    //Limpa os campos após o cadastro!
                    txtClienteNome.setText("");
                    txtClienteEndereco.setText("");
                    txtClienteFone.setText("");
                    txtClienteEmail.setText("");

                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    //Método responsavel pela atualização de cliente no banco de dados
    private void update() {

        String sql = "UPDATE clientes SET nome=?,endereco=?,fone=?,email=? WHERE id_cliente = ? ";

        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtClienteNome.getText());
            pst.setString(2, txtClienteEndereco.getText());
            pst.setString(3, txtClienteFone.getText());
            pst.setString(4, txtClienteEmail.getText());
            pst.setString(5, lblClienteId.getText());

            //Verifica se os campos obrigatórios foram preenchidos
            if (txtClienteNome.getText().isEmpty() || txtClienteFone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios! *");
            } else {

                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {

                    JOptionPane.showMessageDialog(null, "Cliente Atualizado com sucesso!");

                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
     //Método responsavel pela remoção de cliente no banco de dados
    private void delete() {

        int confirmacao = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja deletar o cliente " + txtClienteNome.getText() + " ?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {

            String sql = "DELETE FROM clientes WHERE id_cliente = ?";

            try {

                pst = conexao.prepareStatement(sql);
                pst.setString(1, lblClienteId.getText());

                int deletado = pst.executeUpdate();

                if (deletado > 0) {

                    JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");

                    //Limpa os campos após o delete!
                    txtClienteNome.setText("");
                    txtClienteEndereco.setText("");
                    txtClienteFone.setText("");
                    txtClienteEmail.setText("");

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtClienteNome = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtClienteEndereco = new javax.swing.JTextField();
        txtClienteFone = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnClienteCreate = new javax.swing.JButton();
        btnClienteDelete = new javax.swing.JButton();
        btnClienteUpdate = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtClienteEmail = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        txtClientePesquisa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lblClienteId = new java.awt.Label();

        setClosable(true);
        setIconifiable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(588, 654));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Campos obrigatórios *");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel2.setText("* Nome");

        txtClienteNome.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtClienteNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteNomeActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel7.setText("Endereco");

        txtClienteEndereco.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtClienteEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteEnderecoActionPerformed(evt);
            }
        });

        txtClienteFone.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtClienteFone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteFoneActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel9.setText("* Fone");

        btnClienteCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/x/icones/create.png"))); // NOI18N
        btnClienteCreate.setToolTipText("Adicionar");
        btnClienteCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClienteCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnClienteCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteCreateActionPerformed(evt);
            }
        });

        btnClienteDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/x/icones/delete.png"))); // NOI18N
        btnClienteDelete.setToolTipText("Deletar");
        btnClienteDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClienteDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnClienteDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteDeleteActionPerformed(evt);
            }
        });

        btnClienteUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/x/icones/update.png"))); // NOI18N
        btnClienteUpdate.setToolTipText("Alterar");
        btnClienteUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        btnClienteUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnClienteUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteUpdateActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel4.setText("E-mail");

        txtClienteEmail.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtClienteEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteEmailActionPerformed(evt);
            }
        });

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblClientes);

        txtClientePesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClientePesquisaKeyReleased(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/x/icones/pesquisar.png"))); // NOI18N

        lblClienteId.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtClienteFone, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtClienteEndereco)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(36, 36, 36)
                                .addComponent(txtClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(46, 46, 46)
                                .addComponent(txtClienteEmail))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtClientePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel3))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnClienteCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClienteUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClienteDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtClientePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2))
                    .addComponent(txtClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClienteEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtClienteEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClienteFone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClienteCreate, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(btnClienteUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClienteDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        setBounds(0, 0, 575, 511);
    }// </editor-fold>//GEN-END:initComponents

    private void txtClienteNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteNomeActionPerformed

    private void txtClienteEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteEnderecoActionPerformed

    private void txtClienteFoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteFoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteFoneActionPerformed

    private void btnClienteCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteCreateActionPerformed
        create();
    }//GEN-LAST:event_btnClienteCreateActionPerformed

    private void btnClienteDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteDeleteActionPerformed
        delete();
    }//GEN-LAST:event_btnClienteDeleteActionPerformed

    private void btnClienteUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteUpdateActionPerformed
        update();
    }//GEN-LAST:event_btnClienteUpdateActionPerformed

    private void txtClienteEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteEmailActionPerformed

    private void txtClientePesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClientePesquisaKeyReleased
        Read();
    }//GEN-LAST:event_txtClientePesquisaKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        setFields();
    }//GEN-LAST:event_tblClientesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClienteCreate;
    private javax.swing.JButton btnClienteDelete;
    private javax.swing.JButton btnClienteUpdate;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label lblClienteId;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtClienteEmail;
    private javax.swing.JTextField txtClienteEndereco;
    private javax.swing.JTextField txtClienteFone;
    private javax.swing.JTextField txtClienteNome;
    private javax.swing.JTextField txtClientePesquisa;
    // End of variables declaration//GEN-END:variables
}

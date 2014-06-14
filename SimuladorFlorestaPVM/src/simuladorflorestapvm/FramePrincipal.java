package simuladorflorestapvm;

import javax.swing.JOptionPane;

public class FramePrincipal extends javax.swing.JFrame {

    public FramePrincipal() {
        initComponents();

        txtLarguraTerreno.setText("100");
        txtComprimentoTerreno.setText("100");
        txtNumeroArvores.setText("1000");
        txtNumeroDias.setText("365");
        verificaNumMaxArvores();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtLarguraTerreno = new javax.swing.JTextField();
        txtComprimentoTerreno = new javax.swing.JTextField();
        txtNumeroArvores = new javax.swing.JTextField();
        txtNumeroDias = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lblMaxArvores = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Numero de Arvores:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Largura do Terreno:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Comprimento do Terreno:");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Numero de Dias:");

        txtLarguraTerreno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLarguraTerrenoFocusLost(evt);
            }
        });
        txtLarguraTerreno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLarguraTerrenoActionPerformed(evt);
            }
        });

        txtComprimentoTerreno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtComprimentoTerrenoFocusLost(evt);
            }
        });
        txtComprimentoTerreno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComprimentoTerrenoActionPerformed(evt);
            }
        });

        txtNumeroArvores.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumeroArvoresFocusGained(evt);
            }
        });

        jButton1.setText("Executar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblMaxArvores.setText("Max:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtNumeroArvores, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(txtComprimentoTerreno, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLarguraTerreno, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumeroDias))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMaxArvores)
                .addContainerGap(129, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtLarguraTerreno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtComprimentoTerreno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNumeroArvores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaxArvores))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNumeroDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            int numArvores = Integer.parseInt(txtNumeroArvores.getText());
            int larguraTerreno = Integer.parseInt(txtLarguraTerreno.getText());
            int comprimentoTerreno = Integer.parseInt(txtComprimentoTerreno.getText());
            int numDias = Integer.parseInt(txtNumeroDias.getText());
            Gerenciador ger = Gerenciador.getinstancia();
            ger.Iniciar(larguraTerreno, comprimentoTerreno, numArvores, numDias);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPane, "Valor incorreto em algum campo");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtLarguraTerrenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLarguraTerrenoActionPerformed

    }//GEN-LAST:event_txtLarguraTerrenoActionPerformed

    private void txtComprimentoTerrenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComprimentoTerrenoActionPerformed

    }//GEN-LAST:event_txtComprimentoTerrenoActionPerformed

    private void txtLarguraTerrenoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLarguraTerrenoFocusLost
        verificaNumMaxArvores();
    }//GEN-LAST:event_txtLarguraTerrenoFocusLost

    private void txtComprimentoTerrenoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtComprimentoTerrenoFocusLost
        verificaNumMaxArvores();
    }//GEN-LAST:event_txtComprimentoTerrenoFocusLost

    private void txtNumeroArvoresFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroArvoresFocusGained
        verificaNumMaxArvores();
    }//GEN-LAST:event_txtNumeroArvoresFocusGained

    private void verificaNumMaxArvores() {
        try {
            if (txtLarguraTerreno.getText().isEmpty()) {
                return;
            }
            if (txtComprimentoTerreno.getText().isEmpty()) {
                return;
            }

            int larguraTerreno = Integer.parseInt(txtLarguraTerreno.getText());
            int comprimentoTerreno = Integer.parseInt(txtComprimentoTerreno.getText());

            lblMaxArvores.setText( 
                    "Max: " + (larguraTerreno * comprimentoTerreno * Terreno.ARVORES_POR_METRO2));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPane, "Valor incorreto em algum campo");
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblMaxArvores;
    private javax.swing.JTextField txtComprimentoTerreno;
    private javax.swing.JTextField txtLarguraTerreno;
    private javax.swing.JTextField txtNumeroArvores;
    private javax.swing.JTextField txtNumeroDias;
    // End of variables declaration//GEN-END:variables
}

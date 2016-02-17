/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;

/**
 *
 * @author Edu
 */
public class Menu1 extends javax.swing.JFrame {

    /**
     * Creates new form Menu1
     */
    public Menu1() {
        initComponents();
    }

    public Menu1(Conta c1) {
        initComponents();

        this.c1 = c1;
    }

    Conta c1;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Sacar = new javax.swing.JButton();
        Extrato = new javax.swing.JButton();
        Depositar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Sacar.setText("Sacar");
        Sacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SacarActionPerformed(evt);
            }
        });

        Extrato.setText("Extrato");
        Extrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExtratoActionPerformed(evt);
            }
        });

        Depositar.setText("Depositar");
        Depositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepositarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(208, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Depositar)
                    .addComponent(Extrato)
                    .addComponent(Sacar))
                .addGap(113, 113, 113))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(Sacar)
                .addGap(34, 34, 34)
                .addComponent(Extrato)
                .addGap(40, 40, 40)
                .addComponent(Depositar)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SacarActionPerformed
        // TODO add your handling code here:
        c1.sacar();
    }//GEN-LAST:event_SacarActionPerformed

    private void ExtratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExtratoActionPerformed
        // TODO add your handling code here:
        c1.extrato();
    }//GEN-LAST:event_ExtratoActionPerformed

    private void DepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepositarActionPerformed
        // TODO add your handling code here:
        c1.depositar();
    }//GEN-LAST:event_DepositarActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Depositar;
    private javax.swing.JButton Extrato;
    private javax.swing.JButton Sacar;
    // End of variables declaration//GEN-END:variables
}

abstract class Conta {

    public String nome;
    public int conta, saques;
    public double saldo;
    String resposta;
    double saque, deposito;

    public Conta(String nome, int conta, double saldo_inicial) {
        this.nome = nome;
        this.conta = conta;
        saldo = saldo_inicial;
        saques = 0;
    }

    public void sacar() {
        resposta = JOptionPane.showInputDialog(null, "Quanto deseja sacar ?", "Saque",QUESTION_MESSAGE);
        if (!resposta.equals("")) {
            if (saques <= 4) {
                saque = Double.parseDouble(resposta);
                saldo -= saque;
                saques++;
                JOptionPane.showMessageDialog(null, "Saque efetuado com sucesso !", "Confirmação", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Limite de saques diários atingido , volte amanhã ! ;)", "Limite de saques", 0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum saque efetuado !", "Erro", 0);    //Mensagem de erro caso o campo fique vazio
        }
    }

    public void depositar() {
        resposta = JOptionPane.showInputDialog(null, "Quanto deseja depositar ?", "Depósito",QUESTION_MESSAGE);
        if (!resposta.equals("")) {
            deposito = Double.parseDouble(resposta);
            JOptionPane.showMessageDialog(null, "Depósito efetuado com sucesso !", "Confirmação", 1);
        } else {
            JOptionPane.showMessageDialog(null, "Nada depositado !", "Erro", 0);   //Mensagem de erro pro campo vazio
        }
    }

    public void extrato() {
        JOptionPane.showMessageDialog(null, "Nome : " + nome + "\n"
                + "Número da conta : " + conta + "\n"
                + "Saldo : " + saldo + "\n"
                + "Quantidade de saques realizados : " + saques + " (limite de 4 saques diários)", "Extrato", 1);
    }
}

class Poupança extends Conta {

    Poupança(String nome, int num_conta, double saldo) {
        super(nome, num_conta, saldo);
    }
    SimpleDateFormat data = new SimpleDateFormat("dd");
    private final int diaDeRendimento = 10;

    @Override
    public void sacar() {
        resposta = JOptionPane.showInputDialog(null, "Quanto deseja sacar ?", "Saque",QUESTION_MESSAGE);
        saque = Double.parseDouble(resposta);
        if (saque > 0) {
            if (saques < 4) {
                if (saldo >= saque) {
                    saldo -= saque;
                    saques++;
                    JOptionPane.showMessageDialog(null, "Saque efetuado com sucesso !", "Confirmação", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Limite da conta excedido !", "Limite excedido", 0);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Limite de saques diários atingido , volte amanhã ! ;)", "Limite de saques", 0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum saque efetuado !", "Erro", 0);    //Mensagem de erro caso o campo fique vazio
        }
    }

    @Override
    public void extrato() {
        JOptionPane.showMessageDialog(null, "Nome : " + nome + "\n"
                + "Número da conta : " + conta + "\n"
                + "Saldo : " + saldo + "\n"
                + "Quantidade de saques realizados : " + saques + " (limite de 4 saques diários)", "Extrato", 1);
    }

    void novoSaldo() {
        if (data.format(Calendar.getInstance().getTime()).equals(String.valueOf(diaDeRendimento))) {
            saldo += saldo * 0.05;
            JOptionPane.showMessageDialog(null, "Dia de rendimento, você agora possui " + saldo);
        } else {
            JOptionPane.showMessageDialog(null, "Hoje não é o dia de rendimento, seu saldo ainda é " + saldo, null, 0);
        }
    }

    @Override
    public void depositar() {
        resposta = JOptionPane.showInputDialog(null, "Quanto deseja depositar ?", "Depósito",QUESTION_MESSAGE);
        deposito = Double.parseDouble(resposta);
        if (deposito > 0) {
            saldo += deposito;
            JOptionPane.showMessageDialog(null, "Depósito efetuado com sucesso !", "Confirmação", 1);
        } else {
            JOptionPane.showMessageDialog(null, "Nada depositado !", "Erro", 0);   //Mensagem de erro pro campo vazio
        }
    }
}

class Especial extends Conta {

    Especial(String nome, int num_conta, double saldo) {
        super(nome, num_conta, saldo);
    }
    private double limite;

    void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public void sacar() {
        resposta = JOptionPane.showInputDialog(null, "Quanto deseja sacar ?", "Saque",QUESTION_MESSAGE);
        saque = Double.parseDouble(resposta);
        if (saque>0) {
            if (saldo + limite - saque >= 0) {
                saldo -= saque;
                saques++;
                JOptionPane.showMessageDialog(null, "Saque efetuado com sucesso !", "Confirmação", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Limite da conta excedido !", "Limite excedido", 0);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Nenhum saque efetuado !", "Erro", 0);    //Mensagem de erro caso o campo fique vazio
        }
    }

    @Override
    public void depositar() {
        resposta = JOptionPane.showInputDialog(null, "Quanto deseja depositar ?", "Depósito",QUESTION_MESSAGE);
        deposito = Double.parseDouble(resposta);
        if (deposito > 0) {
            saldo += deposito;
            JOptionPane.showMessageDialog(null, "Depósito efetuado com sucesso !", "Confirmação", 1);
        } else {
            JOptionPane.showMessageDialog(null, "Nada depositado !", "Erro", 0);   //Mensagem de erro pro campo vazio
        }
    }

    @Override
    public void extrato() {
        JOptionPane.showMessageDialog(null,
                "Nome : " + nome + "\n"
                + "Número da conta : " + conta + "\n"
                + "Saldo : " + saldo + "\n"
                + "Limite de débito da Conta : " + limite + "\n"
                + "Quantidade de saques realizados : " + saques + " (Sem limite de saques diários)", "Extrato", 0);
    }
}

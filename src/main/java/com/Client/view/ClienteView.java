package com.Client.view;

import com.Client.controller.ClienteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteView extends JFrame {

    private JTextField txtNome;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JTextField txtCpf;
    private JButton btnSalvar;
    private JButton btnLimpar;

    private ClienteController controller;

    public ClienteView() {
        controller = new ClienteController();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Cadastro de Cliente");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Linha 0 - Nome
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Nome:"), gbc);
        txtNome = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0;
        add(txtNome, gbc);

        // Linha 1 - Telefone
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Telefone:"), gbc);
        txtTelefone = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1;
        add(txtTelefone, gbc);

        // Linha 2 - Email
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("E-mail:"), gbc);
        txtEmail = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 2;
        add(txtEmail, gbc);

        // Linha 3 - Senha
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Senha:"), gbc);
        txtSenha = new JPasswordField(20);
        gbc.gridx = 1; gbc.gridy = 3;
        add(txtSenha, gbc);

        // Linha 4 - CPF
        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("CPF:"), gbc);
        txtCpf = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 4;
        add(txtCpf, gbc);

        // Linha 5 - Botões
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar");
        btnLimpar = new JButton("Limpar");
        panelBotoes.add(btnLimpar);
        panelBotoes.add(btnSalvar);

        gbc.gridx = 1; gbc.gridy = 5;
        add(panelBotoes, gbc);

        // Eventos
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarCliente();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
    }

    private void salvarCliente() {
        String nome = txtNome.getText();
        String telefone = txtTelefone.getText();
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());
        String cpf = txtCpf.getText();

        try {
            controller.cadastrarCliente(nome, telefone, email, senha, cpf);
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente:\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        txtCpf.setText("");
    }
}

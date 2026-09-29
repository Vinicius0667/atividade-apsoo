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

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Nome:"), gbc);
        txtNome = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0;
        add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Telefone:"), gbc);
        txtTelefone = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1;
        add(txtTelefone, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("E-mail:"), gbc);
        txtEmail = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 2;
        add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Senha:"), gbc);
        txtSenha = new JPasswordField(20);
        gbc.gridx = 1; gbc.gridy = 3;
        add(txtSenha, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("CPF:"), gbc);
        txtCpf = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 4;
        add(txtCpf, gbc);

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar");
        btnLimpar = new JButton("Limpar");
        panelBotoes.add(btnLimpar);
        panelBotoes.add(btnSalvar);

        gbc.gridx = 1; gbc.gridy = 5;
        add(panelBotoes, gbc);

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
        String nome = txtNome.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String email = txtEmail.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();
        String cpf = txtCpf.getText().trim();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome não preenchido!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fone não preenchido!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email não preenchido!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "CPF não preenchido!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!isCpfValido(cpf)) {
            JOptionPane.showMessageDialog(this, "CPF inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            controller.cadastrarCliente(nome, telefone, email, senha, cpf);

            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();

        } catch (Exception ex) {
            String mensagemErro = ex.getMessage().toLowerCase();

            if (mensagemErro.contains("já cadastrado") || mensagemErro.contains("duplicate")) {
                JOptionPane.showMessageDialog(this, "Cliente já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente:\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean isCpfValido(String cpf) {
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11 || cpf.matches("^(.)\\1{10}$")) {
            return false;
        }

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int digito1 = 11 - (soma % 11);
            if (digito1 > 9) digito1 = 0;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            int digito2 = 11 - (soma % 11);
            if (digito2 > 9) digito2 = 0;

            return digito1 == Character.getNumericValue(cpf.charAt(9)) &&
                   digito2 == Character.getNumericValue(cpf.charAt(10));
                   
        } catch (Exception e) {
            return false;
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
package com.Client;

import com.Client.view.ClienteView;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class App {
    public static void main(String[] args) {
        // Tentativa de usar o visual padrão do sistema operacional
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Iniciar a aplicação Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClienteView view = new ClienteView();
                view.setVisible(true);
            }
        });
    }
}

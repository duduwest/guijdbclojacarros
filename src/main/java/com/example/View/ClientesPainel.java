package com.example.View;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.example.Controller.ClientesControl;
import com.example.Connection.ClientesDAO;
import com.example.Model.Clientes;

public class ClientesPainel extends JPanel {


    private JButton cadastrar, apagar, editar;
    private JTextField clienteNomeField, clienteIdadeField, clienteSexoField, clienteCpfField, clienteRgField;
    private List<Clientes> clientes;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    // Construtor
    public ClientesPainel() {
        super();
        // entrada de dados
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Cadastro Clientes"));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        inputPanel.add(new JLabel("Nome"));
        clienteNomeField = new JTextField(20);
        inputPanel.add(clienteNomeField);

        inputPanel.add(new JLabel("Idade"));
        clienteIdadeField = new JTextField(20);
        inputPanel.add(clienteIdadeField);

        inputPanel.add(new JLabel("Sexo"));
        clienteSexoField = new JTextField(20);
        inputPanel.add(clienteSexoField);

        inputPanel.add(new JLabel("CPF"));
        clienteCpfField = new JTextField(20);
        inputPanel.add(clienteCpfField);

        inputPanel.add(new JLabel("RG"));
        clienteRgField = new JTextField(20);
        inputPanel.add(clienteRgField);
        add(inputPanel);

        JPanel botoes = new JPanel();
        botoes.add(cadastrar = new JButton("Cadastrar"));
        botoes.add(editar = new JButton("Editar"));
        botoes.add(apagar = new JButton("Apagar"));
        add(botoes);

        // tabela de clientes
        JScrollPane jSPane = new JScrollPane();
        add(jSPane);
        tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Idade", "Sexo", "CPF", "RG" });
        table = new JTable(tableModel);
        jSPane.setViewportView(table);

        // Criar a tabela se ela não existir
        new ClientesDAO().criaTabela();
        // Atualizar a Tabela na Abertura da Janela
        atualizarTabela();

        // Tratamento de Eventos (Construtor)
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    clienteNomeField.setText((String) table.getValueAt(linhaSelecionada, 0));
                    clienteIdadeField.setText((String) table.getValueAt(linhaSelecionada, 1));
                    clienteSexoField.setText((String) table.getValueAt(linhaSelecionada, 2));
                    clienteCpfField.setText((String) table.getValueAt(linhaSelecionada, 3));
                    clienteRgField.setText((String) table.getValueAt(linhaSelecionada, 4));
                }
            }
        });

        ClientesControl operacoes = new ClientesControl(clientes, tableModel, table);

        // Configura o método "cadastrar" do objeto operacoes com valores dos campos de
        // entrada
        cadastrar.addActionListener(e -> {

            boolean camposCadastroVazio = clienteNomeField.getText().isEmpty()
                    || clienteIdadeField.getText().isEmpty() || clienteCpfField.getText().isEmpty();

            if (!camposCadastroVazio) {
                operacoes.cadastrar(clienteNomeField.getText(), clienteIdadeField.getText(), clienteSexoField.getText(),
                        clienteCpfField.getText(), clienteRgField.getText());
                clienteNomeField.setText("");
                clienteIdadeField.setText("");
                clienteSexoField.setText("");
                clienteCpfField.setText("");
                clienteRgField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Preencha os Campos Corretamente", "Informação Inválida", 2);
            }
        });

        // Configura a ação do botão "editar"
        editar.addActionListener(e -> {

            boolean camposCadastroVazio = clienteNomeField.getText().isEmpty()
                    || clienteIdadeField.getText().isEmpty() || clienteCpfField.getText().isEmpty();

            if (!camposCadastroVazio) {
                operacoes.atualizar(clienteNomeField.getText(), clienteIdadeField.getText(), clienteSexoField.getText(),
                        clienteCpfField.getText(), clienteRgField.getText());
                // Limpa os campos de entrada após a operação de edição
                clienteNomeField.setText("");
                clienteIdadeField.setText("");
                clienteSexoField.setText("");
                clienteCpfField.setText("");
                clienteRgField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Preencha os Campos Corretamente", "Informação Inválida", 2);
            }

        });

        // Configura a ação do botão "apagar" para excluir um registro no banco de dados
        apagar.addActionListener(e -> {

            boolean camposCadastroVazio = clienteNomeField.getText().isEmpty()
                    || clienteIdadeField.getText().isEmpty() || clienteCpfField.getText().isEmpty();

            if (!camposCadastroVazio) {
                operacoes.apagar(clienteCpfField.getText());
                // Limpa os campos de entrada após a operação de exclusão
                clienteNomeField.setText("");
                clienteIdadeField.setText("");
                clienteSexoField.setText("");
                clienteCpfField.setText("");
                clienteRgField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Não é possível apagar com campos vazios", "Informação Inválida",
                        2);
            }
        });
    };

    // Método para atualizar a tabela de exibição com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        clientes = new ClientesDAO().listarTodos();
        // Obtém os clientes atualizados do banco de dados
        for (Clientes cliente : clientes) {
            // Adiciona os dados de cada cliente como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] { cliente.getNome(), cliente.getIdade(), cliente.getSexo(),
                    cliente.getCpf(), cliente.getRg() });
        }
    }
}



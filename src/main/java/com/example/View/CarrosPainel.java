package com.example.View;

import java.util.List;
import java.awt.event.MouseAdapter;

import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import com.example.Connection.CarrosDAO;
import com.example.Controller.CarrosControl;
import com.example.Model.Carros;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javafx.event.ActionEvent;

public class CarrosPainel extends JPanel {
    // Atributos
    private JButton cadastrar, apagar, editar;
    private JTextField carMarcaField, carModeloField, carAnoField, carPlacaField, carValorField;
    private List<Carros> carros;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    // Construtor
    public CarrosPainel() {
        super();
        // entrada de dados
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Cadastro Carros"));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        inputPanel.add(new JLabel("Marca"));
        carMarcaField = new JTextField(20);
        inputPanel.add(carMarcaField);

        inputPanel.add(new JLabel("Modelo"));
        carModeloField = new JTextField(20);
        inputPanel.add(carModeloField);

        inputPanel.add(new JLabel("Ano"));
        carAnoField = new JTextField(20);
        inputPanel.add(carAnoField);

        inputPanel.add(new JLabel("Placa"));
        carPlacaField = new JTextField(20);
        inputPanel.add(carPlacaField);

        inputPanel.add(new JLabel("Valor"));
        carValorField = new JTextField(20);
        inputPanel.add(carValorField);
        add(inputPanel);

        JPanel botoes = new JPanel();
        botoes.add(cadastrar = new JButton("Cadastrar"));
        botoes.add(editar = new JButton("Editar"));
        botoes.add(apagar = new JButton("Apagar"));
        add(botoes);
        // tabela de carros
        JScrollPane jSPane = new JScrollPane();
        add(jSPane);
        tableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "Marca", "Modelo", "Ano", "Placa", "Valor" });
        table = new JTable(tableModel);
        jSPane.setViewportView(table);

        // Criar a tabela se ela não existir
        new CarrosDAO().criaTabela();
        // Atualizar a Tabela na Abertura da Janela
        atualizarTabela();

        // Tratamento de Eventos (Construtor)
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    carMarcaField.setText((String) table.getValueAt(linhaSelecionada, 0));
                    carModeloField.setText((String) table.getValueAt(linhaSelecionada, 1));
                    carAnoField.setText((String) table.getValueAt(linhaSelecionada, 2));
                    carPlacaField.setText((String) table.getValueAt(linhaSelecionada, 3));
                    carValorField.setText((String) table.getValueAt(linhaSelecionada, 4));
                }
            }
        });

        CarrosControl operacoes = new CarrosControl(carros, tableModel, table);
        // Configura o metodo "cadastrar" do objeto operacoes com valores dos campos de
        // entrada

        cadastrar.addActionListener(e -> {

            String anoText = carAnoField.getText();
            String valorText = carValorField.getText();

            boolean camposCadastroVazio = carMarcaField.getText().isEmpty() || carModeloField.getText().isEmpty()
                    || carPlacaField.getText().isEmpty();

            if ((!anoText.isEmpty() && !valorText.isEmpty())) {
                try {
                    int ano = Integer.parseInt(anoText);
                    int valor = Integer.parseInt(valorText);

                    if (camposCadastroVazio) {
                        JOptionPane.showMessageDialog(null, "Preencha os Campos Corretamente", "Informação Inválida",
                                2);
                    } else {
                        operacoes.cadastrar(carMarcaField.getText(), carModeloField.getText(), carAnoField.getText(),
                                carPlacaField.getText(), carValorField.getText());
                        carMarcaField.setText("");
                        carModeloField.setText("");
                        carAnoField.setText("");
                        carPlacaField.setText("");
                        carValorField.setText("");
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Corretamente", "Informação Inválida", 2);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha os Campos Corretamente", "Informação Inválida", 2);
            }
        });

        // Configura a ação do botão editar
        editar.addActionListener(e -> {
            // Chama o metodo "editar" do objeto operacoes com os valores dos campos de
            // entrada
            String anoText = carAnoField.getText();
            String valorText = carValorField.getText();

            boolean camposCadastroVazio = carMarcaField.getText().isEmpty() || carModeloField.getText().isEmpty()
                    || carPlacaField.getText().isEmpty();

            if ((!anoText.isEmpty() && !valorText.isEmpty())) {
                try {
                    int ano = Integer.parseInt(anoText);
                    int valor = Integer.parseInt(valorText);

                    if (camposCadastroVazio) {
                        JOptionPane.showMessageDialog(null, "Preencha os Campos Corretamente", "Informação Inválida",
                                2);
                    } else {
                        operacoes.atualizar(carMarcaField.getText(), carModeloField.getText(), carAnoField.getText(),
                                carPlacaField.getText(), carValorField.getText());
                        // Limpa os campos de entrada após a operação de edição
                        carMarcaField.setText("");
                        carModeloField.setText("");
                        carAnoField.setText("");
                        carPlacaField.setText("");
                        carValorField.setText("");
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Corretamente", "Informação Inválida", 2);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha os Campos Corretamente", "Informação Inválida", 2);
            }

        });

        // Configura a ação do botão "apagar" para excluir um registro no banco de dados
        apagar.addActionListener(e -> {

            // Chama o método "apagar" do objeto operacoes com o valor do campo de entrada
            // "placa"

            boolean camposCadastroVazio = carMarcaField.getText().isEmpty() || carModeloField.getText().isEmpty()
                    || carPlacaField.getText().isEmpty();

            if (camposCadastroVazio) {
                JOptionPane.showMessageDialog(null, "Não é possível dados vazios", "Informação Inválida",
                        2);
            } else {

                operacoes.apagar(carPlacaField.getText());

                // Limpa os campos de entrada após a operação de exclusão
                carMarcaField.setText("");
                carModeloField.setText("");
                carAnoField.setText("");
                carPlacaField.setText("");
                carValorField.setText("");

            }
        });

    };

    // Métodos (Atualizar Tabela)
    // Método para atualizar a tabela de exibição com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        carros = new CarrosDAO().listarTodos();
        // Obtém os carros atualizados do banco de dados
        for (Carros carro : carros) {
            // Adiciona os dados de cada carro como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] { carro.getMarca(), carro.getModelo(),

                    carro.getAno(), carro.getPlaca(), carro.getValor() });
        }
    }
}

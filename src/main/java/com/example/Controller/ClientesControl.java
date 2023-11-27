package com.example.Controller;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.Connection.ClientesDAO;
import com.example.Model.Clientes;

public class ClientesControl {
    // Atributos
    private List<Clientes> clientes;
    private DefaultTableModel tableModel;
    private JTable table;

    // Construtor
    public ClientesControl(List<Clientes> clientes, DefaultTableModel tableModel, JTable table) {
        this.clientes = clientes;
        this.tableModel = tableModel;
        this.table = table;
    }

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

    // Método para cadastrar um novo cliente no banco de dados
    

    // Outros atributos e métodos...

    // Método para cadastrar um novo cliente no banco de dados
    public void cadastrar(String nome, String idade, String sexo, String cpf, String rg) {
        // Verificações de idade e CPF
        if (validarIdade(idade) ) {
            new ClientesDAO().cadastrar(nome, idade, sexo, cpf, rg);
            // Chama o método de cadastro no banco de dados
            atualizarTabela(); // Atualiza a tabela de exibição após o cadastro
        } else {
            JOptionPane.showMessageDialog(null, "Dados inválidos. Verifique a idade e o CPF.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Métodos de validação
    private boolean validarIdade(String idade) {
        try {
            int idadeInt = Integer.parseInt(idade);
            return idadeInt >= 18; // Verifica se a idade é maior ou igual a 18 anos
        } catch (NumberFormatException e) {
            return false; // Retorna falso se a idade não for um número válido
        }
    }

   



    // Método para atualizar os dados de um cliente no banco de dados
    public void atualizar(String nome, String idade, String sexo, String cpf, String rg) {
        new ClientesDAO().atualizar(nome, idade, sexo, cpf, rg);
        // Chama o método de atualização no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a atualização
    }

    // Método para apagar um cliente do banco de dados
    public void apagar(String cpf) {
        new ClientesDAO().apagar(cpf);
        // Chama o método de exclusão no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a exclusão
    }
}



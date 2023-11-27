package com.example.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.Model.Clientes;


   

    // construtor
    public class ClientesDAO {
        // atributo
        private Connection connection; 
        private List<Clientes> clientes;
    
        // construtor
        public ClientesDAO() {
            this.connection = ConnectionFactory.getConnection();
            criaTabela();  // Adicione esta linha para criar a tabela durante a inicialização
        }
    
        // ... outros métodos
    
        // criar Tabela
        public void criaTabela() {
            String sql = "CREATE TABLE IF NOT EXISTS clientes_tabela (NOME VARCHAR(255), IDADE VARCHAR(255), SEXO VARCHAR(255), CPF VARCHAR(255) PRIMARY KEY, RG VARCHAR(255))";
            try (Statement stmt = this.connection.createStatement()) {
                stmt.execute(sql);
                System.out.println("Tabela criada com sucesso.");
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
            } finally {
                ConnectionFactory.closeConnection(this.connection);
            }
        }

    // Listar todos os valores cadastrados
    public List<Clientes> listarTodos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        clientes = new ArrayList<>();

        try {
            stmt = connection.prepareStatement("SELECT * FROM clientes_lojaclientes");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Clientes cliente = new Clientes (
                        rs.getString("nome"),
                        rs.getString("idade"),
                        rs.getString("rg"),
                        rs.getString("cpf"),
                        rs.getString("sexo"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return clientes;
    }

    // Cadastrar Cliente no banco
    public void cadastrar(String nome, String idade, String sexo, String cpf, String rg) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para cadastrar na tabela
        String sql = "INSERT INTO clientes_tabela (nome, idade, sexo, cpf, rg) VALUES (?, ?, ?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, idade);
            stmt.setString(3, sexo);
            stmt.setString(4, cpf);
            stmt.setString(5, rg);
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Atualizar dados no banco
    public void atualizar(String nome, String idade, String rg, String cpf, String sexo) {
        PreparedStatement stmt = null;
        String sql = "UPDATE clientes_lojaclientes SET nome = ?, idade = ?, rg = ?, sexo = ? WHERE cpf = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, idade);
            stmt.setString(3, rg);
            stmt.setString(4, sexo);
            stmt.setString(5, cpf);
            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Apagar dados do banco
    public void apagar(String cpf) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM clientes_lojaclientes WHERE cpf = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            System.out.println("Dado apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
}

package com.example.Model;

public class Clientes {
    private String nome;
    private String idade;
    private String sexo;
    private String cpf;
    private String rg; 


    public Clientes(String nome, String idade, String sexo, String cpf, String rg) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.cpf = cpf;
        this.rg = rg; 

    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getIdade() {
        return idade;
    }


    public void setIdade(String idade) {
        this.idade = idade;
    }


    public String getSexo() {
        return sexo;
    }


    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


    public String getCpf() {
        return cpf;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public String getRg() {
        return rg;
    }


    public void setRg(String rg) {
        this.rg = rg;
    } 

    

 
    
}
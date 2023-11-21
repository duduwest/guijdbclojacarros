package com.example.View;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class JanelaPrincipal extends JFrame {
    // criação do tabbedPane para incluir as tabs
private JTabbedPane JTPane;
public JanelaPrincipal() {
JTPane = new JTabbedPane();
add(JTPane);
// criandos as tabs
// tab1 carros
CarrosPainel tab1 = new CarrosPainel();
JTPane.add("Carros", tab1);
setBounds(100, 100, 600, 600);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

Vendasview tab2 = new Vendasview();
JTPane.add("Vendas", tab2);
setBounds(100, 100, 600, 600);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

//ClientesPainel tab3 = new ClientesPainel(); 
//JTPane.add("Clientes", tab3);
//setBounds(100, 100, 600, 600);
//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

}  




//métodos para tornar a janela visível
public void run(){
this.setVisible(true);
} }


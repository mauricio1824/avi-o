/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import tela.manutencao.ManutencaoAviao;
import dao.DaoAviao;
import javax.swing.JOptionPane;
import modelo.Aviao;
import tela.manutencao.ManutencaoAviao;
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorAviao {
    public static void inserir(ManutencaoAviao man){
        Aviao objeto = new Aviao();
        objeto.setModelo(man.jtfModelo.getText());
        objeto.setCapacidade(Integer.parseInt(man.jtfCapacidade.getText()));
        objeto.setVolume(Double.parseDouble(man.jtfVolume.getText()));
        objeto.setData_construcao(LocalDate.parse(man.jtfData_construcao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        boolean resultado = DaoAviao.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}

    public static void alterar(ManutencaoAviao man){
        Aviao objeto = new Aviao();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
        objeto.setModelo(man.jtfModelo.getText());
        objeto.setCapacidade(Integer.parseInt(man.jtfCapacidade.getText()));
        objeto.setVolume(Double.parseDouble(man.jtfVolume.getText()));
        objeto.setData_construcao(LocalDate.parse(man.jtfData_construcao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        boolean resultado = DaoAviao.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

    public static void excluir(ManutencaoAviao man){
        Aviao objeto = new Aviao();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoAviao.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
    public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Codigo");
        modelo.addColumn("Modelo");
        modelo.addColumn("Capaidade");
        modelo.addColumn("Volume");
        modelo.addColumn("Data de Construção");
        List<Aviao> resultados = DaoAviao.consultar();
        for (Aviao objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getModelo());
            linha.add(objeto.getCapacidade());
            linha.add(objeto.getVolume());
            linha.add(objeto.getData_construcao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
    public static void atualizaCampos(ManutencaoAviao man, int pk){ 
        Aviao objeto = DaoAviao.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigo.setText(objeto.getCodigo().toString());
        man.jtfModelo.setText(objeto.getModelo());
        man.jtfCapacidade.setText(objeto.getCapacidade().toString());
        man.jtfVolume.setText(objeto.getVolume().toString());
        man.jtfData_construcao.setText(objeto.getData_construcao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jtfCodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }



}

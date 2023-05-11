/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import org.bson.Document;

/**
 *
 * @author Pedro
 */
public interface AbstractDAO<T> {
    
    
    public void salvar(Document documento);
    
    public void excluir(String nome);
    
    public boolean ehNulo();
    
    public Document buscaObjeto(String nome);
    
    public List<T> buscaTodos();
    
    public void listarLista();
    
}

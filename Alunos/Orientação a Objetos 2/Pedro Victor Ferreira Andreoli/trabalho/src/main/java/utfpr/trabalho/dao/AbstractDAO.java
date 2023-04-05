/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utfpr.trabalho.dao;

import java.util.List;

/**
 *
 * @author Pedro
 */
public interface AbstractDAO<T> {
    
    
    public void salvar(T obj);
    
    public void excluir(T obj);
    
    public void atualizar (T obj);
    
    public T buscaObjeto(Long id);
    
    public List<T> buscaTodos();
    
    public void listarLista();
    
}

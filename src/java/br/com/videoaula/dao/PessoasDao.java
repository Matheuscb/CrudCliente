/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.videoaula.dao;

import br.com.videoaula.entity.Pessoas;
import br.com.videoaula.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author matheuscruz
 */
public class PessoasDao {
    private Session sessao;
    private Transaction trans;
    private List<Pessoas> list;

    public List<Pessoas> getList() {
        //Primeiro a sessão que vai se conectar com a persistência, depois a conexão.
        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        
        //Sessão vai buscar por todas as pessoas e vai transformar a busca no tipo "pessoas" com a extensão "class" pois representa a estrutura de classe.
        Criteria cri = sessao.createCriteria(Pessoas.class);
        //A própria lista vai receber a listagem de cri.
        this.list = cri.list();
        return list;
    }

    public PessoasDao() {
    }
    
    public void addPessoa(Pessoas p){
        try {
            //Pegar a SessionFactory do HibernateUtil e abrir a sessao.
            sessao = HibernateUtil.getSessionFactory().openSession();
            //Inicia uma transação na sessão.
            trans = sessao.beginTransaction();

            Pessoas pessoa = new Pessoas();
            pessoa.setNome(p.getNome());
            pessoa.setTelefone(p.getTelefone());
            pessoa.setEmail(p.getEmail());

            sessao.save(pessoa);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessao.close();
        }
    }
    
    public void removePessoa(Pessoas p){
        try {
            //Pegar a SessionFactory do HibernateUtil e abrir a sessao.
            sessao = HibernateUtil.getSessionFactory().openSession();
            //Inicia uma transação na sessão.
            trans = sessao.beginTransaction();
            
            sessao.delete(p);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessao.close();
        }
    }
    
    public void updatePessoa(Pessoas p){
        try {
            //Pegar a SessionFactory do HibernateUtil e abrir a sessao.
            sessao = HibernateUtil.getSessionFactory().openSession();
            //Inicia uma transação na sessão.
            trans = sessao.beginTransaction();
            
            sessao.update(p);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessao.close();
        }
    }
}
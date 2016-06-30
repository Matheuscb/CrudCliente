/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.videoaula.bean;

import br.com.videoaula.dao.PessoasDao;
import br.com.videoaula.entity.Pessoas;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author matheuscruz
 */
@Named(value = "pessoasBean")
@SessionScoped
public class PessoasBean implements Serializable {

    /**
     * Creates a new instance of PessoasBean
     */
    
    private Pessoas pessoa = new Pessoas();
    private PessoasDao pessoaDao = new PessoasDao();
    private List<Pessoas> listaPessoas;

    public PessoasBean() {
    }
    
    public String adicionarPessoa(){
        pessoaDao.addPessoa(pessoa);
        pessoa.setNome(null);
        pessoa.setEmail(null);
        pessoa.setTelefone(null);
        return "index";
    }
    public String removePessoa(Pessoas p){
        this.pessoa = p;
        pessoaDao.removePessoa(this.pessoa);
        this.pessoa.setNome(null);
        this.pessoa.setEmail(null);
        this.pessoa.setTelefone(null);
        return "index";
    }
    
    public List listarPessoas(){
        //listaPessoas vai receber a lista da pessoaDao.
        listaPessoas = pessoaDao.getList();
        //Retorna essa lista(alterada) do método.
        return this.listaPessoas;
    }
    
    public String carregarPessoa(Pessoas p){
        pessoa = p;
        return "editar";
    }
    
    public String atualizarPessoa(){
        pessoaDao.updatePessoa(pessoa);
        pessoa.setId(null);
        pessoa.setNome(null);
        pessoa.setEmail(null);
        pessoa.setTelefone(null);
        return "index";
    }
    
    public Pessoas getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoas pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.pessoa);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PessoasBean other = (PessoasBean) obj;
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        return true;
    }    
}

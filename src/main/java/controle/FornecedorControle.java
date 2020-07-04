/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidade.Cidade;
import entidade.Fornecedor;
import facade.CidadeFacade;
import facade.FornecedorFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author lucas
 */
@ManagedBean
@SessionScoped
public class FornecedorControle implements Serializable {

    @EJB
    private FornecedorFacade fornecedorFacade;
    @EJB
    private CidadeFacade cidadeFacade;
    private Fornecedor forn;
    private ConverterGenerico cidadeConverter;
    private String tipoPessoa = "PessoaFisica";
    
    public Boolean isPF(){
        if(tipoPessoa.equals("PessoaFisica")){
            return true;
        }
        return false;
    }
    
    public Boolean isPJ(){
        if(tipoPessoa.equals("PessoaJuridica")){
            return true;
        }
        return false;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }
    

    public ConverterGenerico getCidadeConverter() {
        if (cidadeConverter == null) {
            cidadeConverter = new ConverterGenerico(cidadeFacade);
        }
        return cidadeConverter;
    }

    public void setCidadeConverter(ConverterGenerico cidadeConverter) {
        this.cidadeConverter = cidadeConverter;
    }

    public void novo() {
        forn = new Fornecedor();
    }

    public void salvar() {
        fornecedorFacade.save(forn);
    }

    public void excluir(Fornecedor f) {
        fornecedorFacade.remove(f);
    }

    public void editar(Fornecedor f) {
        forn = f;
    }

    public Fornecedor getForn() {
        return forn;
    }

    public void setForn(Fornecedor forn) {
        this.forn = forn;
    }

    public List<Fornecedor> getLista() {
        return fornecedorFacade.findAll();
    }

    public List<Cidade> getListaCidadesFiltrando(String filtro) {
        return cidadeFacade.listaFiltrando(filtro, "nome");
    }

}

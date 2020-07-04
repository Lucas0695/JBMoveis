/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidade.Cliente;
import entidade.Cidade;
import facade.ClienteFacade;
import facade.CidadeFacade;
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
public class ClienteControle implements Serializable {

    @EJB
    private ClienteFacade clienteFacade;
    @EJB
    private CidadeFacade cidadeFacade;
    private Cliente cli;
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
        cli = new Cliente();
    }

    public void salvar() {
        clienteFacade.save(cli);
    }

    public void excluir(Cliente e) {
        clienteFacade.remove(e);
    }

    public void editar(Cliente e) {
        cli = e;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public List<Cliente> getLista() {
        return clienteFacade.findAll();
    }

    public List<Cidade> getListaCidadesFiltrando(String filtro) {
        return cidadeFacade.listaFiltrando(filtro, "nome");
    }

}

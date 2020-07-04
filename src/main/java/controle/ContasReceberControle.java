/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.BaixaContasReceber;
import entidade.ContasReceber;
import entidade.Cliente;
import facade.ContasReceberFacade;
import facade.ClienteFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author lucas
 */
@ManagedBean
@SessionScoped
public class ContasReceberControle implements Serializable{
    @EJB
    private ContasReceberFacade contasReceberFacade;
    @EJB
    private ClienteFacade clienteFacade;
    private ContasReceber cp;
    private ConverterGenerico clienteConverter;
    private BaixaContasReceber baixaContasReceber;
    private MoneyConverter moneyConverter;
    
    public void baixar(){
        if(cp.getValor()>=(cp.getTotalBaixado()+baixaContasReceber.getValor())){
        baixaContasReceber.setContasReceber(cp);
        cp.getBaixaContasReceber().add(baixaContasReceber);  
        baixaContasReceber = new BaixaContasReceber();
        }else{
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "O valor baixado é maior que o valor do contas a receber!",
                            null));
        }
    }
    
    public void novaBaixa(ContasReceber cp){
        this.cp = cp;
        baixaContasReceber = new BaixaContasReceber();
    }
    
    public void removerBaixa(BaixaContasReceber b){
        cp.getBaixaContasReceber().remove(b);
    }
    
    public String getCorValor(){        
        if(Objects.equals(cp.getTotalBaixado(), cp.getValor())){
            return "green";
        }else{
            return "red";
        }
    }
    
    public MoneyConverter getMoneyConverter() {
        if(moneyConverter == null){
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }
    

    public BaixaContasReceber getBaixaContasReceber() {
        return baixaContasReceber;
    }

    public void setBaixaContasReceber(BaixaContasReceber baixaContasReceber) {
        this.baixaContasReceber = baixaContasReceber;
    }

    public ConverterGenerico getClienteConverter() {
        if (clienteConverter == null) {
            clienteConverter = new ConverterGenerico(clienteFacade);
        }
        return clienteConverter;
    }

    public void setClienteConverter(ConverterGenerico clienteConverter) {
        this.clienteConverter = clienteConverter;
    }

    public void novo() {
        cp = new ContasReceber();
        cp.setBaixaContasRecebers(new ArrayList<BaixaContasReceber>());
    }

    public void salvar() {
        contasReceberFacade.save(cp);
    }

    public void excluir(ContasReceber e) {
        contasReceberFacade.remove(e);
    }

    public void editar(ContasReceber e) {
        cp = e;
    }

    public ContasReceber getCp() {
        return cp;
    }

    public void setCp(ContasReceber cp) {
        this.cp = cp;
    }

    public List<ContasReceber> getLista() {
        return contasReceberFacade.findAll();
    }

    public List<Cliente> getListaClientesFiltrando(String filtro) {
        return clienteFacade.listaFiltrando(filtro, "nome");
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.GrupoProdutoFinal;
import entidade.ProdutoFinal;
import facade.GrupoProdutoFinalFacade;
import facade.ProdutoFinalFacade;
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
public class ProdutoFinalControle implements Serializable {
    @EJB
    private ProdutoFinalFacade produtoFinalFacade;
    @EJB
    private GrupoProdutoFinalFacade grupoProdutoFinalFacade;
    private ProdutoFinal pf;
    private ConverterGenerico grupoProdutoFinalConverter; 
    private MoneyConverter moneyConverter;

    public MoneyConverter getMoneyConverter() {
        if(moneyConverter == null){
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }
    

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }
    
    public ConverterGenerico getGrupoProdutoFinalConverter(){
        if(grupoProdutoFinalConverter == null){
            grupoProdutoFinalConverter= new ConverterGenerico(grupoProdutoFinalFacade);
        }
        return grupoProdutoFinalConverter;
    }
    
    public void setgrupoProdutoFinalConverter(ConverterGenerico grupoProdutoFinalConverter){
        this.grupoProdutoFinalConverter= grupoProdutoFinalConverter;
    }
    
    public void novo() {
        pf = new ProdutoFinal();
    }

    public void salvar() {
        produtoFinalFacade.save(pf);
    }

    public void excluir(ProdutoFinal p) {
        produtoFinalFacade.remove(p);
    }

    public void editar(ProdutoFinal p) {
        pf = p;
    }
    

    public ProdutoFinal getPf() {
        return pf;
    }

    public void setMp(ProdutoFinal pf) {
        this.pf = pf;
    }

    public List<ProdutoFinal> getLista() {
        return produtoFinalFacade.findAll();
    }
    public List <GrupoProdutoFinal> getListaGrupoProdutoFinalFiltrando(String filtro){
        return grupoProdutoFinalFacade.listaFiltrando(filtro,"descricao");
    }

}


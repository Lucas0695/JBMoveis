/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.Compra;
import entidade.ContasPagar;
import entidade.Fornecedor;
import entidade.ItensCompra;
import entidade.MateriaPrima;
import facade.CompraFacade;
import facade.FornecedorFacade;
import facade.MateriaPrimaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
public class CompraControle implements Serializable {

    @EJB
    private CompraFacade compraFacade;
    @EJB
    private FornecedorFacade fornecedorFacade;
    @EJB
    private MateriaPrimaFacade materiaPrimaFacade;
    private Compra comp;
    private ItensCompra itensCompra;
    private ConverterGenerico fornecedorConverter;
    private ConverterGenerico materiaPrimaConverter;
    private MoneyConverter moneyConverter;
    private Integer numeroParcelas;
    private ContasPagar contasPagar;


    public void addMateriaPrima() {
        if (itensCompra.getQuantidade() == null || itensCompra.getPreco() == null) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Preencha todos os campos!",
                            null));
        } else {
            Double estoque = itensCompra.getMateriaPrima().getEstoque();
            ItensCompra itemTemp = null;
            for (ItensCompra it : comp.getItensCompra()) {
                if (it.getMateriaPrima().equals(itensCompra.getMateriaPrima())) {
                    itemTemp = it;
                    estoque = estoque + it.getQuantidade();
                }
            }
            
                if (itemTemp == null) {
                    itensCompra.setCompra(comp);
                    comp.getItensCompra().add(itensCompra);
                } else {
                    itemTemp.setQuantidade(itemTemp.getQuantidade() + itensCompra.getQuantidade());
                }
                itensCompra = new ItensCompra();
            }
        }
    

    public void removeMatPri(ItensCompra it) {
        comp.getItensCompra().remove(it);
    }

    public void gerarParcelas() {
        comp.setContasPagars(new ArrayList<ContasPagar>());
        Double valor = comp.getValorTotal() / numeroParcelas;
        Date dataComp = comp.getDataCompra();
        for (Integer i = 1; i <= numeroParcelas; i++) {
            ContasPagar cp = new ContasPagar();
            cp.setDataLancamento(comp.getDataCompra());
            cp.setFornecedor(comp.getFornecedor());
            cp.setParcela(i.toString() + "/" + numeroParcelas.toString());
            cp.setValor(valor);
            cp.setDataVencimento(dataComp);
            cp.setCompra(comp);
            if(comp.getFornecedor() != null){
                cp.setFornecedor(comp.getFornecedor());
            }            
            comp.getContasPagars().add(cp);
            //Soma 1 mês no compcimento
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataComp);
            cal.add(Calendar.MONTH, 1);
            dataComp = cal.getTime();
        }
    }
    
    public ItensCompra getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(ItensCompra itensCompra) {
        this.itensCompra = itensCompra;
    }

    public ConverterGenerico getFornecedorConverter() {
        if (fornecedorConverter == null) {
            fornecedorConverter = new ConverterGenerico(fornecedorFacade);
        }
        return fornecedorConverter;
    }

    public void setFornecedorConverter(ConverterGenerico fornecedorConverter) {
        this.fornecedorConverter = fornecedorConverter;
    }

    public ConverterGenerico getMateriaPrimaConverter() {
        if (materiaPrimaConverter == null) {
            materiaPrimaConverter = new ConverterGenerico(materiaPrimaFacade);
        }
        return materiaPrimaConverter;
    }

    public void setMateriaPrimaConverter(ConverterGenerico materiaPrimaConverter) {
        this.materiaPrimaConverter = materiaPrimaConverter;
    }

    public MoneyConverter getMoneyConverter() {
        if (moneyConverter == null) {
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }

    public void atualizaPrecoMateriaPrima() {
        itensCompra.setPreco(itensCompra.getMateriaPrima().getPreco());
    }

    public ContasPagar getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(ContasPagar contasPagar) {
        this.contasPagar = contasPagar;
    }
    

    public void novo() {
        comp = new Compra();
        itensCompra = new ItensCompra();
        contasPagar = new ContasPagar();
        numeroParcelas = 1;
        comp.setContasPagars(new ArrayList<ContasPagar>());
    }

    public void salvar() {
        compraFacade.save(comp);
    }

    public void excluir(Compra e) {
        compraFacade.remove(e);
    }

    public void editar(Compra e) {
        comp = e;
    }

    public Compra getComp() {
        return comp;
    }

    public void setComp(Compra comp) {
        this.comp = comp;
    }

    
    public List<Compra> getLista() {
        return compraFacade.findAll();
    }

    public List<Fornecedor> getListaFornecedorsFiltrando(String filtro) {
        return fornecedorFacade.listaFiltrando(filtro, "nome");
    }

    public List<MateriaPrima> getListaMateriaPrimasFiltrando(String filtro) {
        return materiaPrimaFacade.listaFiltrando(filtro, "nome");
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }
    

}

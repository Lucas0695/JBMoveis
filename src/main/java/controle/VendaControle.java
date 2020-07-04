/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.BaixaContasReceber;
import entidade.Venda;
import entidade.ContasReceber;
import entidade.Cliente;
import entidade.ItensVenda;
import entidade.ProdutoFinal;
import facade.VendaFacade;
import facade.ClienteFacade;
import facade.ProdutoFinalFacade;
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
public class VendaControle implements Serializable {

    @EJB
    private VendaFacade vendaFacade;
    @EJB
    private ClienteFacade clienteFacade;
    @EJB
    private ProdutoFinalFacade produtoFinalFacade;
    private Venda vend;
    private ItensVenda itensVenda;
    private ConverterGenerico clienteConverter;
    private ConverterGenerico produtoFinalConverter;
    private MoneyConverter moneyConverter;
    private Integer numeroParcelas;
    private ContasReceber contasReceber;

    public void addProdutoFinal() {
        if (itensVenda.getQuantidade() == null || itensVenda.getPreco() == null) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Preencha todos os campos!",
                            null));
        } else {
            Double estoque = itensVenda.getProdutoFinal().getEstoque();
            ItensVenda itemTemp = null;
            for (ItensVenda it : vend.getItensVenda()) {
                if (it.getProdutoFinal().equals(itensVenda.getProdutoFinal())) {
                    itemTemp = it;
                    estoque = estoque - it.getQuantidade();
                }
            }
            if ((estoque - itensVenda.getQuantidade()) < 0) {
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Estoque insuficiente!",
                                "Restam apenas "
                                + estoque
                                + " unidades."));
            } else {

                if (itemTemp == null) {
                    itensVenda.setVenda(vend);
                    vend.getItensVenda().add(itensVenda);
                } else {
                    itemTemp.setQuantidade(itemTemp.getQuantidade() - itensVenda.getQuantidade());
                }
                itensVenda = new ItensVenda();
            }
        }
    }

    public void removeMatPri(ItensVenda it) {
        vend.getItensVenda().remove(it);
    }

    public void gerarParcelas() {
        vend.setContasRecebers(new ArrayList<ContasReceber>());

//        Entrada
        ContasReceber cp = new ContasReceber();
        cp.setDataLancamento(vend.getDataVenda());
        cp.setCliente(vend.getCliente());
        cp.setParcela("Entrada");
        cp.setValor(vend.getEntrada());
        cp.setDataVencimento(vend.getDataVenda());
        cp.setVenda(vend);
        if (vend.getCliente() != null) {
            cp.setCliente(vend.getCliente());
        }
        BaixaContasReceber b = new BaixaContasReceber();
        b.setContasReceber(cp);
        b.setDataBaixa(vend.getDataVenda());
        b.setValor(vend.getEntrada());
        cp.setBaixaContasRecebers(new ArrayList<BaixaContasReceber>());
        cp.getBaixaContasReceber().add(b);
        vend.getContasRecebers().add(cp);

        Double valor = (vend.getValorTotal() - vend.getEntrada()) / numeroParcelas;
        Date dataComp = vend.getDataVenda();
        for (Integer i = 1; i <= numeroParcelas; i++) {
            cp = new ContasReceber();
            cp.setDataLancamento(vend.getDataVenda());
            cp.setCliente(vend.getCliente());
            cp.setParcela(i.toString() + "/" + numeroParcelas.toString());
            cp.setValor(valor);
            cp.setDataVencimento(dataComp);
            cp.setVenda(vend);
            if (vend.getCliente() != null) {
                cp.setCliente(vend.getCliente());
            }
            vend.getContasRecebers().add(cp);
            //Soma 1 mês no vendcimento
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataComp);
            cal.add(Calendar.MONTH, 1);
            dataComp = cal.getTime();
        }
    }

    public ItensVenda getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(ItensVenda itensVenda) {
        this.itensVenda = itensVenda;
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

    public ConverterGenerico getProdutoFinalConverter() {
        if (produtoFinalConverter == null) {
            produtoFinalConverter = new ConverterGenerico(produtoFinalFacade);
        }
        return produtoFinalConverter;
    }

    public void setProdutoFinalConverter(ConverterGenerico produtoFinalConverter) {
        this.produtoFinalConverter = produtoFinalConverter;
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

    public void atualizaPrecoProdutoFinal() {
        itensVenda.setPreco(itensVenda.getProdutoFinal().getPreco());
    }

    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
    }

    public void novo() {
        vend = new Venda();
        itensVenda = new ItensVenda();
        contasReceber = new ContasReceber();
        numeroParcelas = 1;
        vend.setContasRecebers(new ArrayList<ContasReceber>());
    }

    public void salvar() {
        vendaFacade.save(vend);
    }

    public void excluir(Venda e) {
        vendaFacade.remove(e);
    }

    public void editar(Venda e) {
        vend = e;
    }

    public Venda getVend() {
        return vend;
    }

    public void setVend(Venda vend) {
        this.vend = vend;
    }

    public List<Venda> getLista() {
        return vendaFacade.findAll();
    }

    public List<Cliente> getListaClientesFiltrando(String filtro) {
        return clienteFacade.listaFiltrando(filtro, "nome");
    }

    public List<ProdutoFinal> getListaProdutoFinalsFiltrando(String filtro) {
        return produtoFinalFacade.listaFiltrando(filtro, "nome");
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

}

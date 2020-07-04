package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.Fabricacao;
import entidade.ItensFabricacao;
import entidade.MateriaPrima;
import entidade.ProdutoFinal;
import facade.FabricacaoFacade;
import facade.MateriaPrimaFacade;
import facade.ProdutoFinalFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class FabricacaoControle implements Serializable {

    @EJB
    private FabricacaoFacade fabricacaoFacade;
    @EJB
    private MateriaPrimaFacade materiaPrimaFacade;
    @EJB
    private ProdutoFinalFacade produtoFinalFacade;
    private ConverterGenerico produtoFinalConverter;
    private Fabricacao fab;
    private ProdutoFinal produtoFinal;
    private ItensFabricacao itensFabricacao;
    private ConverterGenerico materiaPrimaConverter;
    private MoneyConverter moneyConverter;

    public Boolean isAberto1(Fabricacao fab) {
        if (fab.getSituacao().equals("aberto")) {
            return true;
        }
        return false;
    }

    public Boolean isCompleta1(Fabricacao fab) {
        if (fab.getSituacao().equals("completa")) {
            return true;
        }
        return false;
    }

    public void setCalculaValorPercentual() {
        Double val = fab.getValorTotal();
        Double per = fab.getPercentual() + 100;
        Double totalpor = (val * per) / 100;
        fab.setValorfinal(totalpor);
    }
    
    public void addMateriaPrima() {
        if (itensFabricacao.getQuantidade() == null || itensFabricacao.getPreco() == null) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Preencha todos os campos!",
                            null));
        } else {
            Double estoque = itensFabricacao.getMateriaPrima().getEstoque();
            ItensFabricacao itemTemp = null;
            for (ItensFabricacao it : fab.getItensFabricacao()) {
                if (it.getMateriaPrima().equals(itensFabricacao.getMateriaPrima())) {
                    itemTemp = it;
                    estoque = estoque - it.getQuantidade();
                }
            }
             if ((estoque - itensFabricacao.getQuantidade()) < 0) {
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Estoque insuficiente!",
                                "Restam apenas "
                                + estoque
                                + " unidades."));
             }else{
            if (itemTemp == null) {
                itensFabricacao.setFabricacao(fab);
                fab.getItensFabricacao().add(itensFabricacao);
            } else {
                itemTemp.setQuantidade(itemTemp.getQuantidade() + itensFabricacao.getQuantidade());
            }
            itensFabricacao = new ItensFabricacao();
        }
        }     
    }

    public void removeProd(ItensFabricacao it) {
        fab.getItensFabricacao().remove(it);
    }

    public ItensFabricacao getItensFabricacao() {
        return itensFabricacao;
    }

    public void setItensFabricacao(ItensFabricacao itensFabricacao) {
        this.itensFabricacao = itensFabricacao;
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
        itensFabricacao.setPreco(itensFabricacao.getMateriaPrima().getPreco());
    }
    
    public void novo() {
        fab = new Fabricacao();
        fab.setSituacao("aberto");
        itensFabricacao = new ItensFabricacao();
    }

    public void salvar() {
        fabricacaoFacade.save(fab);
    }

    public void excluir(Fabricacao e) {
        fabricacaoFacade.remove(e);
    }

    public void editar(Fabricacao e) {
        fab = e;
    }

    public Fabricacao getFab() {
        return fab;
    }

    public void setFab(Fabricacao fab) {
        this.fab = fab;
    }

    public List<Fabricacao> getLista() {
        return fabricacaoFacade.findAll();
    }

    public ProdutoFinal getProdutoFinal() {
        return produtoFinal;
    }

    public void setProdutoFinal(ProdutoFinal produtoFinal) {
        this.produtoFinal = produtoFinal;
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

    public List<ProdutoFinal> getListaProdutoFinalFiltrando(String filtro) {
        return produtoFinalFacade.listaFiltrando(filtro, "nome");
    }

    public List<MateriaPrima> getListaMateriaPrimasFiltrando(String filtro) {
        return materiaPrimaFacade.listaFiltrando(filtro, "nome");
    }

}

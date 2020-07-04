/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.GrupoProdutoFinal;
import facade.GrupoProdutoFinalFacade;
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
public class GrupoProdutoFinalControle implements Serializable {
    @EJB
    private GrupoProdutoFinalFacade grupoProdutoFinalFacade;
    private GrupoProdutoFinal gpf;

    public void novo() {
        gpf = new GrupoProdutoFinal();
    }

    public void salvar() {
        grupoProdutoFinalFacade.save(gpf);
    }

    public void excluir(GrupoProdutoFinal g) {
        grupoProdutoFinalFacade.remove(g);
    }

    public void editar(GrupoProdutoFinal g) {
        gpf = g;
    }

    public GrupoProdutoFinal getGmp() {
        return gpf;
    }

    public void setGmp(GrupoProdutoFinal gpf) {
        this.gpf = gpf;
    }

    public List<GrupoProdutoFinal> getLista() {
        return grupoProdutoFinalFacade.findAll();
    }

}


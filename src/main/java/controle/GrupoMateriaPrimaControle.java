/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.GrupoMateriaPrima;
import facade.GrupoMateriaPrimaFacade;
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
public class GrupoMateriaPrimaControle implements Serializable {
    @EJB
    private GrupoMateriaPrimaFacade grupoMateriaPrimaFacade;
    private GrupoMateriaPrima gmp;

    public void novo() {
        gmp = new GrupoMateriaPrima();
    }

    public void salvar() {
        grupoMateriaPrimaFacade.save(gmp);
    }

    public void excluir(GrupoMateriaPrima g) {
        grupoMateriaPrimaFacade.remove(g);
    }

    public void editar(GrupoMateriaPrima g) {
        gmp = g;
    }

    public GrupoMateriaPrima getGmp() {
        return gmp;
    }

    public void setGmp(GrupoMateriaPrima gmp) {
        this.gmp = gmp;
    }

    public List<GrupoMateriaPrima> getLista() {
        return grupoMateriaPrimaFacade.findAll();
    }

}


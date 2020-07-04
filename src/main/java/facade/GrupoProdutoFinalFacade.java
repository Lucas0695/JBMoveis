/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.GrupoProdutoFinal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lucas
 */
@Stateless
public class GrupoProdutoFinalFacade extends AbstractFacade<GrupoProdutoFinal> {

    @PersistenceContext(unitName = "jbmoveisPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoProdutoFinalFacade() {
        super(GrupoProdutoFinal.class);
    }

    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.Fornecedor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lucas
 */
@Stateless
public class FornecedorFacade extends AbstractFacade<Fornecedor> {

    @PersistenceContext(unitName = "jbmoveisPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public FornecedorFacade() {
        super(Fornecedor.class);
    }

}

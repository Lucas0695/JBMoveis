/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.Compra;
import entidade.ItensCompra;
import entidade.MateriaPrima;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lucas
 */
@Stateless
public class CompraFacade extends AbstractFacade<Compra> {

    @PersistenceContext(unitName = "jbmoveisPU")
    private EntityManager em;

    @Override
    public List<Compra> findAll() {
        List<Compra> retorno = super.findAll();
        for(Compra c:retorno){
            c.getContasPagars().size();
            c.getItensCompra().size();
        }
        return  retorno;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Compra find(Object id) {
        Compra c = super.find(id);
        c.getContasPagars().size();
        c.getItensCompra().size();
        return  c;//To change body of generated methods, choose Tools | Templates.
    }
    
     @Override
    public void save(Compra entity) {
        for(ItensCompra it : entity.getItensCompra()){
            MateriaPrima p = it.getMateriaPrima();
            p.setEstoque(p.getEstoque() + it.getQuantidade());
            em.merge(p);
        }
        super.save(entity); 
    }

    @Override
    public void remove(Compra entity) {
        for(ItensCompra it : entity.getItensCompra()){
            MateriaPrima p = it.getMateriaPrima();
            p.setEstoque(p.getEstoque() - it.getQuantidade());
            em.merge(p);
        }
        super.remove(entity);
    }

    
    

    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraFacade() {
        super(Compra.class);
    }

}

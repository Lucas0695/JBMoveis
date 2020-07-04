/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.Venda;
import entidade.ItensVenda;
import entidade.ProdutoFinal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lucas
 */
@Stateless
public class VendaFacade extends AbstractFacade<Venda> {

    @PersistenceContext(unitName = "jbmoveisPU")
    private EntityManager em;

    @Override
    public List<Venda> findAll() {
        List<Venda> retorno = super.findAll();
        for(Venda c:retorno){
            c.getContasRecebers().size();
            c.getItensVenda().size();
        }
        return  retorno;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Venda find(Object id) {
        Venda c = super.find(id);
        c.getContasRecebers().size();
        c.getItensVenda().size();
        return  c;//To change body of generated methods, choose Tools | Templates.
    }
    
     @Override
    public void save(Venda entity) {
        for(ItensVenda it : entity.getItensVenda()){
            ProdutoFinal p = it.getProdutoFinal();
            p.setEstoque(p.getEstoque() - it.getQuantidade());
            em.merge(p);
        }
        super.save(entity); 
    }

    @Override
    public void remove(Venda entity) {
        for(ItensVenda it : entity.getItensVenda()){
            ProdutoFinal p = it.getProdutoFinal();
            p.setEstoque(p.getEstoque() + it.getQuantidade());
            em.merge(p);
        }
        super.remove(entity);
    }

    
    

    protected EntityManager getEntityManager() {
        return em;
    }

    public VendaFacade() {
        super(Venda.class);
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidade.Fabricacao;
import entidade.ItensFabricacao;
import entidade.MateriaPrima;
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
public class FabricacaoFacade extends AbstractFacade<Fabricacao> {

    @PersistenceContext(unitName = "jbmoveisPU")
    private EntityManager em;

    @Override
    public List<Fabricacao> findAll() {
        List<Fabricacao> retorno = super.findAll();
        for(Fabricacao c:retorno){
            c.getItensFabricacao().size();
        }
        return  retorno;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Fabricacao find(Object id) {
        Fabricacao c = super.find(id);
        c.getItensFabricacao().size();
        return  c;//To change body of generated methods, choose Tools | Templates.
    }
    
     @Override
    public void save(Fabricacao entity) {
        for(ItensFabricacao it : entity.getItensFabricacao()){
            MateriaPrima p = it.getMateriaPrima();
            p.setEstoque(p.getEstoque() - it.getQuantidade());
            em.merge(p);
            
        }
         ProdutoFinal pf = entity.getProdutoFinal();
         pf.setEstoque(pf.getEstoque()+entity.getQuantidadeProduto());
         pf.setPreco(entity.getValorfinal() / entity.getQuantidadeProduto());
         em.merge(pf);
        super.save(entity); 
    }

    @Override
    public void remove(Fabricacao entity) {
        for(ItensFabricacao it : entity.getItensFabricacao()){
            MateriaPrima p = it.getMateriaPrima();
            p.setEstoque(p.getEstoque() + it.getQuantidade());
            em.merge(p);
        }
        ProdutoFinal pf = entity.getProdutoFinal();
         pf.setEstoque(pf.getEstoque()-entity.getQuantidadeProduto());
         em.merge(pf);
        super.remove(entity);
    }

    
    

    protected EntityManager getEntityManager() {
        return em;
    }

    public FabricacaoFacade() {
        super(Fabricacao.class);
    }

}

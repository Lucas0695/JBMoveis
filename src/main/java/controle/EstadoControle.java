/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import entidade.Estado;
import facade.EstadoFacade;
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
@URLMappings(mappings = {
    @URLMapping(id = "novoEstado", pattern = "/estado/novo", viewId = "/faces/estado/estadoedita.xhtml")
    ,
    @URLMapping(id = "editaEstado", pattern = "/estado/edita/#{estadoControle.id}", viewId = "/faces/estado/estadoedita.xhtml")
    ,
    @URLMapping(id = "listaEstado", pattern = "/estado/listar", viewId = "/faces/estado/estadolista.xhtml")
})
public class EstadoControle implements Serializable {

    @EJB
    private EstadoFacade estadoFacade;
    private Estado est;
    private Long id;

    @URLAction(mappingId = "novoEstado", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void novo() {
        est = new Estado();
    }

    public void salvar() {
        estadoFacade.save(est);
    }

    public void excluir(Estado e) {
        estadoFacade.remove(e);
    }

    @URLAction(mappingId = "editaEstado", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    public void editarPeloId() {
        est = estadoFacade.find(id);
    }
    
    public void editar(Estado e) {
        id = e.getId();
        est = e;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estado getEst() {
        return est;
    }

    public void setEst(Estado est) {
        this.est = est;
    }

    public List<Estado> getLista() {
        return estadoFacade.findAll();
    }

}

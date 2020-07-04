/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.GrupoMateriaPrima;
import entidade.MateriaPrima;
import facade.GrupoMateriaPrimaFacade;
import facade.MateriaPrimaFacade;
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
public class MateriaPrimaControle implements Serializable {
    @EJB
    private MateriaPrimaFacade materiaPrimaFacade;
    @EJB
    private GrupoMateriaPrimaFacade grupoMateriaPrimaFacade;
    private MateriaPrima mp;
    private ConverterGenerico grupoMateriaPrimaConverter; 
    private MoneyConverter moneyConverter;

    public MoneyConverter getMoneyConverter() {
        if(moneyConverter == null){
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }
    
    public ConverterGenerico getGrupoMateriaPrimaConverter(){
        if(grupoMateriaPrimaConverter == null){
            grupoMateriaPrimaConverter= new ConverterGenerico(grupoMateriaPrimaFacade);
        }
        return grupoMateriaPrimaConverter;
    }
    
    public void setgrupoMateriaPrimaConverter(ConverterGenerico grupoMateriaPrimaConverter){
        this.grupoMateriaPrimaConverter= grupoMateriaPrimaConverter;
    }
    
    public void novo() {
        mp = new MateriaPrima();
    }

    public void salvar() {
        materiaPrimaFacade.save(mp);
    }

    public void excluir(MateriaPrima m) {
        materiaPrimaFacade.remove(m);
    }

    public void editar(MateriaPrima m) {
        mp = m;
    }
    

    public MateriaPrima getMp() {
        return mp;
    }

    public void setMp(MateriaPrima mp) {
        this.mp = mp;
    }

    public List<MateriaPrima> getLista() {
        return materiaPrimaFacade.findAll();
    }
    public List <GrupoMateriaPrima> getListaGrupoMateriaPrimaFiltrando(String filtro){
        return grupoMateriaPrimaFacade.listaFiltrando(filtro,"descricao");
    }

}


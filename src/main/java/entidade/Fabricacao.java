/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jaimedias
 */
@Entity
public class Fabricacao implements Serializable, ClassePai {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dataFabricacao;   
    private Double valorTotal;
    private Double percentual;
    private Double valorfinal;
    private Double quantidadeProduto;
    private String situacao;
     @ManyToOne  
    private ProdutoFinal produtoFinal;
    @OneToMany(cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, 
            mappedBy = "fabricacao", 
            orphanRemoval = true)
    private List<ItensFabricacao> itensFabricacao;
    

    public Fabricacao() {
        itensFabricacao = new ArrayList<ItensFabricacao>();
        dataFabricacao = new Date();
        quantidadeProduto= 0d;
    }
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPercentual() {
        return percentual;
    }

    public void setPercentual(Double percentual) {
        this.percentual = percentual;
    }

    public Double getValorfinal() {
        return valorfinal;
    }

    public void setValorfinal(Double valorfinal) {
        this.valorfinal = valorfinal;
    }

    
    
    public Date getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(Date dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public Double getValorTotal() {
        valorTotal = 0d;
        for(ItensFabricacao it : itensFabricacao){
            valorTotal = valorTotal + it.getSubTotal();
        }
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItensFabricacao> getItensFabricacao() {
        return itensFabricacao;
    }

    public void setItensFabricacao(List<ItensFabricacao> itensFabricacao) {
        this.itensFabricacao = itensFabricacao;
    }

    public ProdutoFinal getProdutoFinal() {
        return produtoFinal;
    }

    public void setProdutoFinal(ProdutoFinal produtoFinal) {
        this.produtoFinal = produtoFinal;
    }

    public Double getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(Double quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

 
    public String getSituacao() {
        return situacao ;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
  
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fabricacao)) {
            return false;
        }
        Fabricacao other = (Fabricacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Fabricacao[ id=" + id + " ]";
    }
    
}

package br.ufma.portal.entidades;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import br.ufma.portal.entidades.ContatoEgressoPk.ContatoEgressoPkBuilder;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name= "contato_egresso")
public class ContatoEgresso {
    
    @EmbeddedId
    private ContatoEgressoPk id;
    
    @ManyToOne
    @MapsId("id_egresso")
    @JoinColumn(name="egresso_id")
    private Egresso egresso_id;
    
    @ManyToOne
    @MapsId("id_contato")
    @JoinColumn(name = "contato_id")
    private Contato contato_id;

    public ContatoEgressoPk getId(){
        if(id == null){
            if(contato_id == null && egresso_id == null){
                id = null;
            }else if (contato_id.getId_contato() == null && egresso_id.getId_egresso() == null){
                id = null;
            }else{
                ContatoEgressoPkBuilder builder = ContatoEgressoPk.builder();
                if(contato_id != null)
                    if (contato_id.getId_contato() != null)
                        builder = builder.id_contato(contato_id.getId_contato()); 
                if (egresso_id != null)
                    if(egresso_id.getId_egresso() != null)
                        builder = builder.id_egresso(egresso_id.getId_egresso());
                id = builder.build();
            }
        }
        return id;
    }
    
    
    public void setContato(Contato contato){
        if(id == null){
            id = ContatoEgressoPk.builder().id_contato(contato.getId_contato()).build();
        }else{
            id.setId_contato(contato.getId_contato());
        }
    }
    
    public void setEgresso(Egresso egresso){
        if(id == null){
            id = ContatoEgressoPk.builder().id_egresso(egresso.getId_egresso()).build();
        }else{
            id.setId_egresso((egresso.getId_egresso()));
        }
    }
}

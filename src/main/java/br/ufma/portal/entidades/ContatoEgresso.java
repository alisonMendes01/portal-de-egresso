package br.ufma.portal.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor

class ContatoEgressoPk implements Serializable {  
    @Column(name= "id_egresso")  
    private Integer id_egresso;
    
    @Column(name = "id_contato")
    private Integer id_contato;
}

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
}

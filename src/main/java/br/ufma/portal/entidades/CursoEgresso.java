package br.ufma.portal.entidades;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor

class CursoEgressoPk implements Serializable {  
    @Column(name= "id_egresso")  
    private Integer id_egresso;
    
    @Column(name = "id_curso")
    private Integer id_curso;
}

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CursoEgresso {
    
    @EmbeddedId
    private CursoEgressoPk id;
    
    @ManyToOne
    @MapsId("id_egresso")
    @JoinColumn(name="egresso_id")
    private Egresso egresso_id;
    
    @ManyToOne
    @MapsId("id_curso")
    @JoinColumn(name = "curso_id")
    private Contato curso_id;
    
    @Column(name = "data_inicio")   
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate data_inicio;

    @Column(name = "data_conclusao")   
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate data_conclusao;
    
}

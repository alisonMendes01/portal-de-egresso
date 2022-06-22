package br.ufma.portal.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import br.ufma.portal.model.CursoEgressoPk.CursoEgressoPkBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Curso curso_id;
    
    @Column(name = "data_inicio")   
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate data_inicio;

    @Column(name = "data_conclusao")   
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate data_conclusao;

    public CursoEgressoPk getId(){
        if(id == null){
            if(curso_id == null && egresso_id == null){
                id = null;
            }else if (curso_id.getId_curso() == null && egresso_id.getId_egresso() == null){
                id = null;
            }else{
                CursoEgressoPkBuilder builder = CursoEgressoPk.builder();
                if(curso_id != null)
                    if (curso_id.getId_curso() != null)
                        builder = builder.id_curso(curso_id.getId_curso()); 
                if (egresso_id != null)
                    if(egresso_id.getId_egresso() != null)
                        builder = builder.id_egresso(egresso_id.getId_egresso());
                id = builder.build();
            }
        }
        return id;
    }
    
    
    public void setCurso(Curso curso){
        if(id == null){
            id = CursoEgressoPk.builder().id_curso(curso.getId_curso()).build();
        }else{
            id.setId_curso(curso.getId_curso());
        }
    }
    
    public void setEgresso(Egresso egresso){
        if(id == null){
            id = CursoEgressoPk.builder().id_egresso(egresso.getId_egresso()).build();
        }else{
            id.setId_egresso((egresso.getId_egresso()));
        }
    }
}

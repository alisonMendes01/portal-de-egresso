package br.ufma.portal.entidades;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CursoEgressoPk implements Serializable {  
    @Column(name= "id_egresso")  
    private Integer id_egresso;
    
    @Column(name = "id_curso")
    private Integer id_curso;
}

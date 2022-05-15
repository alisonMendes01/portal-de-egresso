package br.ufma.portal.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContatoEgressoPk implements Serializable{
    @Column(name= "id_egresso")  
    private Integer id_egresso;
    
    @Column(name = "id_contato")
    private Integer id_contato;
}
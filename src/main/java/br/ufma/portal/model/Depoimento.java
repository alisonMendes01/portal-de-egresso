package br.ufma.portal.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name= "depoimento")
public class Depoimento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_depoimento")
    private Integer id_depoimento;
    
    @ManyToOne
    @JoinColumn(name="egresso_id")
    private Egresso egresso_id;
    
    @Column(name="texto")
    private String texto;

    @Column(name = "data")   
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate data;
}

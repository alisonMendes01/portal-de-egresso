package br.ufma.portal.entidades;

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

@Table(name= "prof_egresso")
public class ProfessorEgresso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prof_egresso")
    private Integer id_prof_egresso;
    
    @ManyToOne
    @JoinColumn(name="egresso_id")
    private Egresso egresso_id;
    
    @ManyToOne
    @JoinColumn(name="cargo_id")
    private Cargo cargo_id;
    
    @ManyToOne
    @JoinColumn(name="faixa_salario_id")
    private FaixaSalario faixa_salario_id;

    @Column(name = "data_registro")   
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate data_registro;

    @Column(name="empresa")
    private String empresa;
    
    @Column(name="descricao")
    private String descricao;

}

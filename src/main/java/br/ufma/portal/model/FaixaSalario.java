package br.ufma.portal.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "faixa_salario")
public class FaixaSalario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_faixa_salario")
    private Integer id_faixa_salario;

    @OneToMany(mappedBy = "faixa_salario_id")
    private List<ProfEgresso> profEgresso;

    @Column(name = "descricao")
    private String descricao;

}

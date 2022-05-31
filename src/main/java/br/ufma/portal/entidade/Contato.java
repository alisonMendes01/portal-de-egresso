package br.ufma.portal.entidade;

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
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "contato")
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contato")
    private Integer id_contato;
    
    @OneToMany(mappedBy = "egresso_id")
    private List<ContatoEgresso> contatosEgressos;

    @Column(name= "nome")
    private String nome;
    
    @Column(name= "url_logo")
    private String url_logo;
}
// funcionou até agora

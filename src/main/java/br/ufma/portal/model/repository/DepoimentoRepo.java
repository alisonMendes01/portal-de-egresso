package br.ufma.portal.model.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufma.portal.model.Depoimento;
import br.ufma.portal.model.dto.EgressoDepoimentoDto;

@Repository
public interface DepoimentoRepo
        extends JpaRepository<Depoimento, Integer> {
                @Query("select new br.ufma.portal.model.dto.EgressoDepoimentoDto(d.id_depoimento, d.texto, d.data) from Depoimento d inner join  d.egresso_id e where e.id_egresso = :idEgresso")
                public List<EgressoDepoimentoDto> obterDepoimentoPorEgresso(@Param("idEgresso") Integer idEgresso);
                public List<Depoimento> findAllByOrderByDataDesc();

}

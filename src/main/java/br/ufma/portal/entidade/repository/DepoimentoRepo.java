package br.ufma.portal.entidade.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.entidade.Depoimento;

@Repository
public interface DepoimentoRepo
        extends JpaRepository<Depoimento, Integer> {
                public List<Depoimento> findAllByOrderByData();
                /*
                @Query("select new br.ufma.portal.entidades.Depoimento "+
                "from Egresso e join e.Depoimento d " +
                "where d.Egresso = :egresso"
                )
                List<Depoimento> obterDepoimentoPorEgresso(@Param("egresso") Egresso egresso);*/
}


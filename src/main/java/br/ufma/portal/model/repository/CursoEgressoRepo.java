package br.ufma.portal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.model.CursoEgresso;
import br.ufma.portal.model.CursoEgressoPk;

@Repository
public interface CursoEgressoRepo
        extends JpaRepository<CursoEgresso, CursoEgressoPk> {

}

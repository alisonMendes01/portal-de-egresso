package br.ufma.portal.entidade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.entidade.CursoEgresso;
import br.ufma.portal.entidade.CursoEgressoPk;

@Repository
public interface CursoEgressoRepo
        extends JpaRepository<CursoEgresso, CursoEgressoPk> {

}

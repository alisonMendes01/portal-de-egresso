package br.ufma.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.entidades.CursoEgresso;
import br.ufma.portal.entidades.CursoEgressoPk;

@Repository
public interface CursoEgressoRepo extends JpaRepository<CursoEgresso, CursoEgressoPk>  {
    
}

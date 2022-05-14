package br.ufma.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.entidades.CursoEgresso;

@Repository
public interface CursoEgressoRepo extends JpaRepository<CursoEgresso, Integer>  {
    
}

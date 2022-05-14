package br.ufma.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.entidades.Curso;

@Repository
public interface CursoRepo extends JpaRepository<Curso, Integer>  {
    
}
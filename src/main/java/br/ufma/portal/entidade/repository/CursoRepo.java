package br.ufma.portal.entidade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.entidade.Curso;

@Repository
public interface CursoRepo
        extends JpaRepository<Curso, Integer> {

}
package br.ufma.portal.entidade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.entidade.ProfessorEgresso;

@Repository
public interface ProfessorEgressoRepo
        extends JpaRepository<ProfessorEgresso, Integer> {

}
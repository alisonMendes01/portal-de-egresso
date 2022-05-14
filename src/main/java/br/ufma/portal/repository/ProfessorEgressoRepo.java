package br.ufma.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.entidades.ProfessorEgresso;

@Repository
public interface ProfessorEgressoRepo extends JpaRepository<ProfessorEgresso, Integer>  {
    
}
package br.ufma.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.entidades.Egresso;

@Repository
public interface EgressoRepo extends JpaRepository<Egresso, Integer> {
    
}

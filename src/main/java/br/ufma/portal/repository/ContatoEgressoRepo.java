package br.ufma.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.entidades.ContatoEgresso;

@Repository
public interface ContatoEgressoRepo extends JpaRepository<ContatoEgresso, Integer>  {
    
}

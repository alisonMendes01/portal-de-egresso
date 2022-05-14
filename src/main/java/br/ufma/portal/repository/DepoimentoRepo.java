package br.ufma.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.entidades.Depoimento;

@Repository
public interface DepoimentoRepo extends JpaRepository<Depoimento, Integer>  {
    
}
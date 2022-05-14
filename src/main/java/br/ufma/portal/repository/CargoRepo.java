package br.ufma.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.entidades.Cargo;

@Repository
public interface CargoRepo  extends JpaRepository<Cargo, Integer> {
    
}

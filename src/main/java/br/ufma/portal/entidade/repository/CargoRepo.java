package br.ufma.portal.entidade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.entidade.Cargo;

@Repository
public interface CargoRepo
        extends JpaRepository<Cargo, Integer> {

}

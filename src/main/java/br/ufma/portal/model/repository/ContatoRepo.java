package br.ufma.portal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.model.Contato;

@Repository
public interface ContatoRepo
        extends JpaRepository<Contato, Integer> {

}

package br.ufma.portal.entidade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.entidade.Contato;

@Repository
public interface ContatoRepo
        extends JpaRepository<Contato, Integer> {

}

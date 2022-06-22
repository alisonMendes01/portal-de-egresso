package br.ufma.portal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.model.ContatoEgresso;
import br.ufma.portal.model.ContatoEgressoPk;

@Repository
public interface ContatoEgressoRepo
        extends JpaRepository<ContatoEgresso, ContatoEgressoPk> {

}

package br.ufma.portal.entidade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.entidade.ContatoEgresso;
import br.ufma.portal.entidade.ContatoEgressoPk;

@Repository
public interface ContatoEgressoRepo
        extends JpaRepository<ContatoEgresso, ContatoEgressoPk> {

}

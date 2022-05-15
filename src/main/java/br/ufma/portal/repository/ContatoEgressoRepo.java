package br.ufma.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.entidades.ContatoEgresso;
import br.ufma.portal.entidades.ContatoEgressoPk;

@Repository
public interface ContatoEgressoRepo extends JpaRepository<ContatoEgresso, ContatoEgressoPk>  {

}

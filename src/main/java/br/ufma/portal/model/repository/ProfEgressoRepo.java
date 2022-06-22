package br.ufma.portal.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.model.ProfEgresso;

@Repository
public interface ProfEgressoRepo
                extends JpaRepository<ProfEgresso, Integer> {

}
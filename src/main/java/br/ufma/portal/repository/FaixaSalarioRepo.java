package br.ufma.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.portal.entidades.FaixaSalario;

@Repository
public interface FaixaSalarioRepo extends JpaRepository<FaixaSalario, Integer>  {
    
}
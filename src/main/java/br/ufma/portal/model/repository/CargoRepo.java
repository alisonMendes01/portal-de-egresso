package br.ufma.portal.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufma.portal.model.Cargo;

@Repository
public interface CargoRepo
                extends JpaRepository<Cargo, Integer> {

        @Query("SELECT c FROM Cargo c "
                + "INNER JOIN ProfEgresso pe "
                + "ON c.id_cargo = pe.cargo_id "
                + "INNER JOIN Egresso e "
                + "ON e.id_egresso = pe.egresso_id "
                + "WHERE e.id_egresso = :idEgresso")
        public List<Cargo> buscarPorEgresso(@Param("idEgresso") Integer idEgresso);
        
        @Query("SELECT COUNT(e) FROM Egresso e "
                + "INNER JOIN ProfEgresso pe "
                + "ON e.id_egresso = pe.egresso_id "
                + "INNER JOIN Cargo c "
                + "ON c.id_cargo = pe.cargo_id "
                + "WHERE c.id_cargo = :idCargo") 
        public Integer contarEgressosPorCargo(@Param("idCargo") Integer idCargo); 

}


package br.ufma.portal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufma.portal.model.FaixaSalario;

@Repository
public interface FaixaSalarioRepo
        extends JpaRepository<FaixaSalario, Integer> {

        @Query("SELECT COUNT(e) FROM Egresso e "
                + "INNER JOIN ProfEgresso pe "
                + "ON e.id_egresso = pe.egresso_id "
                + "INNER JOIN FaixaSalario fs "
                + "ON fs.id_faixa_salario = pe.faixa_salario_id "
                + "WHERE fs.id_faixa_salario = :idFaixaSalario")
        public Integer contarEgressosPorFaixaSalario(@Param("idFaixaSalario") Integer idFaixaSalario);
}
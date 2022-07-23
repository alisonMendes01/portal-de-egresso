package br.ufma.portal.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufma.portal.model.Curso;
import br.ufma.portal.model.Egresso;

@Repository
public interface CursoRepo
                extends JpaRepository<Curso, Integer> {

        @Query("SELECT e FROM Egresso e "
                +"INNER JOIN CursoEgresso ce "
                +"ON e.id_egresso = ce.egresso_id "
                +"INNER JOIN Curso c "
                +"ON c.id_curso = ce.curso_id "
                +"WHERE c.id_curso = :idCurso")
        public List<Egresso> buscarPorEgresso(@Param("idCurso") Integer idCurso);

        @Query("SELECT COUNT(e) FROM Egresso e "
                + "INNER JOIN CursoEgresso ce "
                + "ON e.id_egresso = ce.egresso_id "
                + "INNER JOIN Curso c "
                + "ON c.id_curso = ce.curso_id "
                + "WHERE c.id_curso = :idCurso")
        public Integer contarEgressosPorCurso(@Param("idCurso") Integer idCurso); // conta 
}
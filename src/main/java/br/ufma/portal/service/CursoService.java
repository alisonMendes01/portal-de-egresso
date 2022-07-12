package br.ufma.portal.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.ufma.portal.model.Curso;
import br.ufma.portal.model.Egresso;
import br.ufma.portal.model.repository.CursoRepo;
import br.ufma.portal.service.exception.RegraNegocioRunTime;

@Service
public class CursoService {

    @Autowired
    CursoRepo repo;

    public void verificaCurso(Curso curso) {
        if (curso == null)
            throw new RegraNegocioRunTime("O Curso não pode ser nulo!");

        if (curso.getNome() == null || curso.getNome().equals(""))
            throw new RegraNegocioRunTime("O Curso deve possuir um nome!");

        if (curso.getNivel() == null || curso.getNivel().isEmpty())
            throw new RegraNegocioRunTime("erro");
    }

    public void verificaId(Curso curso) {
        if ((curso == null) || (curso.getId_curso() == null))
            throw new RegraNegocioRunTime("Não foi encontrado nenhum Curso");
    }

    @Transactional
    public Curso salvar(Curso curso) {
        verificaCurso(curso);
        return repo.save(curso);
    }

    @Transactional
    public Curso editar(Curso curso) {
        verificaId(curso);
        return salvar(curso);
    }

    @Transactional
    public void remover(Curso curso) {
        verificaId(curso);
        verificaCurso(curso);
        repo.delete(curso);
    }

    @Transactional
    public void remover(Integer id) {
        Optional<Curso> curso = repo.findById(id);
        remover(curso.get());
    }

    @Transactional
    public List<Egresso> findByEgresso(Integer idCurso) {
        return repo.findByEgresso(idCurso);
    }

    @Transactional
    public Integer countEgressosByCurso(Integer idCurso) {
        return repo.countEgressosByCurso(idCurso);
    }

    @Transactional
    public List<Curso> buscar(Curso filtro) {
        Example<Curso> example = Example.of(filtro, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING));
        return repo.findAll(example);
    }
}
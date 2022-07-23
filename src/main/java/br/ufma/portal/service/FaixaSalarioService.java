package br.ufma.portal.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.ufma.portal.model.FaixaSalario;
import br.ufma.portal.model.repository.FaixaSalarioRepo;
import br.ufma.portal.service.exception.RegraNegocioRunTime;

@Service
public class FaixaSalarioService {

    @Autowired
    FaixaSalarioRepo repo;

    public void verificaFaixaSalario(FaixaSalario faixaSalario) {
        if (faixaSalario == null)
            throw new RegraNegocioRunTime("O FaixaSalario não pode ser nulo!");

        if ((faixaSalario.getDescricao() == null) || (faixaSalario.getDescricao().equals("")))
            throw new RegraNegocioRunTime("Deve haver um texto");
    }

    public void verificaId(FaixaSalario FaixaSalario) {
        if ((FaixaSalario == null) || (FaixaSalario.getId_faixa_salario() == null))
            throw new RegraNegocioRunTime("Não foi encontrado nenhum FaixaSalario");
    }

    @Transactional
    public FaixaSalario salvar(FaixaSalario faixaSalario) {
        verificaFaixaSalario(faixaSalario);
        return repo.save(faixaSalario);
    }

    @Transactional
    public FaixaSalario atualizar(FaixaSalario faixaSalario) {
        verificaId(faixaSalario);
        return salvar(faixaSalario);
    }

    @Transactional
    public void remover(FaixaSalario faixaSalario) {
        verificaId(faixaSalario);
        verificaFaixaSalario(faixaSalario);
        repo.delete(faixaSalario);
    }

    @Transactional
    public FaixaSalario remover(Integer id) {
        Optional<FaixaSalario> faixaSalario = repo.findById(id);
        remover(faixaSalario.get());
        return faixaSalario.get();
    }

    @Transactional
    public Integer contarEgressosPorFaixaSalario(Integer idFaixaSalario) {
        return repo.contarEgressosPorFaixaSalario(idFaixaSalario);
    }

    @Transactional
    public List<FaixaSalario> buscar(FaixaSalario filtro) {
        Example<FaixaSalario> example = Example.of(filtro, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING));
        return repo.findAll(example);
    }

}
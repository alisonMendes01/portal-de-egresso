package br.ufma.portal.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
    public FaixaSalario editar(FaixaSalario faixaSalario) {
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
    public void remover(Integer id) {
        Optional<FaixaSalario> faixaSalario = repo.findById(id);
        remover(faixaSalario.get());
    }

    @Transactional
    public Integer countEgressosByFaixaSalario(Integer idFaixaSalario) {
        return repo.countEgressosByFaixaSalario(idFaixaSalario);
    }

}
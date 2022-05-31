package br.ufma.portal.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import org.springframework.stereotype.Service;

import br.ufma.portal.entidade.Egresso;
import br.ufma.portal.entidade.repository.EgressoRepo;
import br.ufma.portal.service.exception.RegraNegocioRunTime;

@Service
public class EgressoService {

    @Autowired
    EgressoRepo repo;

    public void verificaEgresso(Egresso egresso){
        if (egresso == null)
            throw new RegraNegocioRunTime("O egresso não pode ser nulo!");
        
        if ((egresso.getNome() == null) || (egresso.getNome().equals("")))
            throw new RegraNegocioRunTime("O egresso deve ter um nome!");

        if ((egresso.getCpf() == null) || (egresso.getCpf().equals("")) || (egresso.getCpf().length() != 11))
            throw new RegraNegocioRunTime("O egresso deve ter um cpf!");

        if ((egresso.getEmail() == null) || (egresso.getEmail().equals("")))
            throw new RegraNegocioRunTime("O egresso deve ter um e-mail!");
            
        if ((egresso.getResumo() == null) || (egresso.getResumo().equals("")))
            throw new RegraNegocioRunTime("O egresso deve ter um nome!");

        if ((egresso.getContatosEgresso() == null) || (egresso.getContatosEgresso().isEmpty()))
            throw new RegraNegocioRunTime("O egresso deve ter um contato!");

        
    }
    public void verificaId(Egresso egresso) {
        if ((egresso == null) || (egresso.getId_egresso() == null))
            throw new RegraNegocioRunTime("Não foi encontrado nenhum egresso");
    }
    @Transactional
    public Egresso salvar(Egresso egresso) {
        verificaEgresso(egresso);
        return repo.save(egresso);
    }

    /*
    consultar egressos ordenados pelo mais recente
    consultar egressos por egresso
    */

    //repository.findAll(Sort.by(Sort.Direction.DESC, "colName"));
    @Transactional
    public List<Egresso> buscar(Egresso filtro) {
        Example<Egresso> example = 
            Example.of(filtro, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING)
        );                     
        return repo.findAll(example);
    }
    @Transactional
    public Egresso editar(Egresso egresso) {
        verificaId(egresso);
        return salvar(egresso);
    }
    @Transactional
    public void remover(Egresso egresso) {
        verificaId(egresso);
        verificaEgresso(egresso);
        repo.delete(egresso);
    }
    
    @Transactional
    public void remover(Integer id) {
        Optional<Egresso> egresso = repo.findById(id);
        remover(egresso.get());
    }
}

package br.ufma.portal.service;

import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufma.portal.entidade.Depoimento;
import br.ufma.portal.entidade.dto.EgressoDepoimentoDto;
import br.ufma.portal.entidade.repository.DepoimentoRepo;
import br.ufma.portal.service.exception.RegraNegocioRunTime;

@Service
public class DepoimentoService {

    @Autowired
    DepoimentoRepo repo;

    public void verificaDepoimento(Depoimento depoimento) {
        if (depoimento == null)
            throw new RegraNegocioRunTime("O depoimento não pode ser nulo!");
            
        if ((depoimento.getTexto() == null) || (depoimento.getTexto().equals("")))
            throw new RegraNegocioRunTime("Deve haver um texto");

        if ((depoimento.getData() == null) || (depoimento.getData().isAfter(LocalDate.now())))
            throw new RegraNegocioRunTime("A data não pode ser nula e precisa ser válida");
    }

    public void verificaId(Depoimento depoimento) {
        if ((depoimento == null) || (depoimento.getId_depoimento() == null))
            throw new RegraNegocioRunTime("Não foi encontrado nenhum depoimento");
    }

    @Transactional
    public Depoimento salvar(Depoimento depoimento) {
        verificaDepoimento(depoimento);
        return repo.save(depoimento);
    }

    /*
    consultar depoimentos ordenados pelo mais recente
    consultar depoimentos por egresso
    */
    @Transactional
    public List<Depoimento> buscarRecente() {
        return repo.findAllByOrderByDataDesc();
    }

    //repository.findAll(Sort.by(Sort.Direction.DESC, "colName"));
    @Transactional
    public List<Depoimento> buscar(Depoimento filtro) {
        Example<Depoimento> example = 
            Example.of(filtro, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING)
        );                     
        return repo.findAll(example);
    }
    @Transactional
    public Depoimento editar(Depoimento depoimento) {
        verificaId(depoimento);
        return salvar(depoimento);
    }
    @Transactional
    public void remover(Depoimento depoimento) {
        verificaId(depoimento);
        verificaDepoimento(depoimento);
        repo.delete(depoimento);
    }
    
    @Transactional
    public void remover(Integer id) {
        Optional<Depoimento> depoimento = repo.findById(id);
        remover(depoimento.get());
    }
    @Transactional
    public List<EgressoDepoimentoDto> BuscarporEgresso(Integer id){
        return repo.obterDepoimentoPorEgresso(id);
    }
}

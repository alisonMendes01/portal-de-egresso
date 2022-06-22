package br.ufma.portal.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.ufma.portal.model.Cargo;
import br.ufma.portal.model.repository.CargoRepo;
import br.ufma.portal.service.exception.RegraNegocioRunTime;

@Service
public class CargoService {

    @Autowired
    CargoRepo repo;

    public void verificaCargo(Cargo cargo) {
        if (cargo == null)
            throw new RegraNegocioRunTime("O cargo não pode ser nulo!");

        if (cargo.getNome() == null || cargo.getNome().equals(""))
            throw new RegraNegocioRunTime("O cargo deve possuir um nome!");

        if (cargo.getProfEgresso() == null || cargo.getProfEgresso().isEmpty())
            throw new RegraNegocioRunTime("erro");

        if (cargo.getDescricao() == null || cargo.getDescricao().equals(""))
            throw new RegraNegocioRunTime("O cargo deve conter uma desrição!");
    }

    public void verificaId(Cargo cargo) {
        if ((cargo == null) || (cargo.getId_cargo() == null))
            throw new RegraNegocioRunTime("Não foi encontrado nenhum cargo");
    }

    @Transactional
    public Cargo salvar(Cargo cargo) {
        verificaCargo(cargo);
        return repo.save(cargo);
    }

    @Transactional
    public Cargo editar(Cargo cargo) {
        verificaId(cargo);
        return salvar(cargo);
    }

    @Transactional
    public void remover(Cargo cargo) {
        verificaId(cargo);
        verificaCargo(cargo);
        repo.delete(cargo);
    }

    @Transactional
    public void remover(Integer id) {
        Optional<Cargo> cargo = repo.findById(id);
        remover(cargo.get());
    }

    public List<Cargo> buscar(Cargo filtro) {
        Example<Cargo> example = Example.of(filtro, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING));
        return repo.findAll(example);
    }

    @Transactional
    public List<Cargo> findByEgresso(Integer egresso){
        return repo.findByEgresso(egresso);
    }

    @Transactional
    public Integer countEgressosByCargo(Integer cargo){
        return repo.countEgressosByCargo(cargo);
    }

}

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
import br.ufma.portal.model.Contato;
import br.ufma.portal.model.Curso;
import br.ufma.portal.model.CursoEgresso;
import br.ufma.portal.model.CursoEgressoPk;
import br.ufma.portal.model.Egresso;
import br.ufma.portal.model.ProfEgresso;
import br.ufma.portal.model.FaixaSalario;
import br.ufma.portal.model.repository.CargoRepo;
import br.ufma.portal.model.repository.ProfEgressoRepo;
import br.ufma.portal.model.repository.ContatoEgressoRepo;
import br.ufma.portal.model.repository.ContatoRepo;
import br.ufma.portal.model.repository.CursoEgressoRepo;
import br.ufma.portal.model.repository.CursoRepo;
import br.ufma.portal.model.repository.EgressoRepo;
import br.ufma.portal.model.repository.FaixaSalarioRepo;
import br.ufma.portal.service.exception.RegraNegocioRunTime;

@Service
public class EgressoService {

    @Autowired
    EgressoRepo repo;

    @Autowired
    ContatoEgressoRepo repoContatoEgresso;

    @Autowired
    ContatoRepo repoContato;

    @Autowired
    FaixaSalarioRepo repoFaixaSalario;

    @Autowired
    CursoRepo repoCurso;

    @Autowired
    CargoRepo repoCargo;

    @Autowired
    CursoEgressoRepo repoCursoEgresso;

    @Autowired
    ProfEgressoRepo repoProfEgresso;

    public void verificaEgresso(Egresso egresso) {
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

    @Transactional
    public List<Egresso> buscar(Egresso filtro) {
        Example<Egresso> example = Example.of(filtro, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING));
        return repo.findAll(example);
    }

    @Transactional
    public Egresso editar(Egresso egresso) {
        verificaId(egresso);
        return salvar(egresso);
    }

    @Transactional
    public Egresso editarEgressoNome(Integer id, String nome) {
        Optional<Egresso> egresso = repo.findById(id);
        Egresso e = egresso.get();
        e.setNome(nome);
        return repo.save(e);
    }

    @Transactional
    public Contato editarEgressoContato(Integer id_egresso, Integer id_contato, Contato contato) {
        Optional<Contato> AtualizarContato = repoContato.findById(id_contato);
        Contato c = AtualizarContato.get();
        contato.setId_contato(c.getId_contato());
        return repoContato.save(contato);
    }

    @Transactional
    public Cargo editarEgressoCargo(Integer id_egresso, Integer id_cargo, Cargo cargo) {
        Optional<Cargo> AtualizarCargo = repoCargo.findById(id_cargo);
        Cargo c = AtualizarCargo.get();
        cargo.setId_cargo(c.getId_cargo());
        return repoCargo.save(cargo);
    }
    

    @Transactional
    public FaixaSalario editarEgressoFaixaSalario(Integer id_egresso, Integer id_faixa_salario, FaixaSalario faixaSalario) {
        Optional<FaixaSalario> AtualizarFaixaSalario = repoFaixaSalario.findById(id_faixa_salario);
        FaixaSalario f = AtualizarFaixaSalario.get();
        faixaSalario.setId_faixa_salario(f.getId_faixa_salario());
        return repoFaixaSalario.save(faixaSalario);
    }

    @Transactional
    public CursoEgresso editarCurso(Integer id_egresso, Integer id_curso, CursoEgresso cursoEgresso) {
        CursoEgressoPk pk = CursoEgressoPk.builder().id_egresso(id_egresso).id_curso(id_curso).build();
        Optional<CursoEgresso> AtualizarCursoEgresso = repoCursoEgresso.findById(pk);
        CursoEgresso e = AtualizarCursoEgresso.get();
        cursoEgresso.setId(e.getId());
        return repoCursoEgresso.save(cursoEgresso);
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

package br.ufma.portal.services;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.portal.model.Cargo;
import br.ufma.portal.model.Contato;
import br.ufma.portal.model.ContatoEgresso;
import br.ufma.portal.model.Curso;
import br.ufma.portal.model.CursoEgresso;
import br.ufma.portal.model.Egresso;
import br.ufma.portal.model.FaixaSalario;
import br.ufma.portal.model.ProfEgresso;
import br.ufma.portal.model.repository.CargoRepo;
import br.ufma.portal.model.repository.ContatoEgressoRepo;
import br.ufma.portal.model.repository.ContatoRepo;
import br.ufma.portal.model.repository.CursoEgressoRepo;
import br.ufma.portal.model.repository.CursoRepo;
import br.ufma.portal.model.repository.EgressoRepo;
import br.ufma.portal.model.repository.FaixaSalarioRepo;
import br.ufma.portal.model.repository.ProfEgressoRepo;
import br.ufma.portal.service.EgressoService;

@SpringBootTest
public class TesteEgressoService {

    @Autowired
    public EgressoRepo repoEgresso;

    @Autowired
    public ContatoRepo repoContato;

    @Autowired
    public ContatoEgressoRepo repoContatoEgresso;

    @Autowired
    public CursoRepo repoCurso;

    @Autowired
    public CargoRepo repoCargo;

    @Autowired
    public CursoEgressoRepo repoCursoEgresso;

    @Autowired
    public FaixaSalarioRepo repoFaixaSalario;
    
    @Autowired
    ProfEgressoRepo repoProfEgresso;

    @Autowired
    EgressoService service;

    @Test
    public void deveEditarNome() {
        Egresso novo = Egresso.builder().nome("Maria de Jesus")
                .cpf("123")
                .email("a@a.com")
                .resumo("resumo abc")
                .build();
        Egresso retornoEgresso = repoEgresso.save(novo);
        Egresso retorno = service.editarEgressoNome(retornoEgresso.getId_egresso(), "Nome editado");

        Assertions.assertNotEquals(retornoEgresso.getNome(), retorno.getNome());

    }

    @Test
    public void deveEditarContato() {
        // cenario
        Egresso novo = Egresso.builder().nome("Maria de Jesus")
                .cpf("123")
                .email("a@a.com")
                .resumo("resumo abc")
                .build();
        Egresso retornoEgresso = repoEgresso.save(novo);

        Contato novoContato = Contato.builder()
                .nome("jesus_maria")
                .url_logo("instagram.com/")
                .build();
        Contato retornoContato = repoContato.save(novoContato);

        ContatoEgresso ce = ContatoEgresso.builder()
                .egresso_id(retornoEgresso)
                .contato_id(retornoContato)
                .build();

        ContatoEgresso retornoCE = repoContatoEgresso.save(ce);

        Contato atualizarContato = Contato.builder()
                .nome("Contato Atualizado")
                .url_logo("instagram.com/contato")
                .build();
        // ação
        service.editarEgressoContato(retornoEgresso.getId_egresso(), retornoContato.getId_contato(), atualizarContato);
        // verificacao
        // Assertions.assertNotEquals(retornoEgresso.getNome(), retorno.getNome());

    }

    @Test
    public void deveEditarCurso() {
        // cenario
        Egresso novo = Egresso.builder().nome("Mario de Satan")
                .cpf("123")
                .email("a@a.com")
                .resumo("resumo abc")
                .build();
        Egresso retornoEgresso = repoEgresso.save(novo);

        Curso novoCurso = Curso.builder().nome("Calvo").nivel("Superior").build();
        Curso retornoCurso = repoCurso.save(novoCurso);

        CursoEgresso ce = CursoEgresso.builder().data_conclusao(LocalDate.of(2022, Month.MAY, 15)).data_inicio(LocalDate.of(2022, Month.MAY, 15)).build();
        ce.setCurso_id(retornoCurso);
        ce.setEgresso_id(retornoEgresso);
        repoCursoEgresso.save(ce);
        CursoEgresso ceAtt = CursoEgresso.builder().data_conclusao(LocalDate.of(2012, Month.MAY, 15)).data_inicio(LocalDate.of(2012, Month.MAY, 15)).build();
        CursoEgresso Atualizadoce = service.editarCurso(retornoEgresso.getId_egresso(), retornoCurso.getId_curso(), ceAtt);
    }
    
    @Test
    public void deveEditarFaixaSalario(){
        Egresso novo = Egresso.builder().nome("Maria de Jesus")
                .cpf("123")
                .email("a@a.com")
                .resumo("resumo abc")
                .build();
        Egresso retornoEgresso = repoEgresso.save(novo);
        
        FaixaSalario novoFaixaSalario = FaixaSalario.builder()
            .descricao("20 pau")
            .build();
        FaixaSalario retornoFaixaSalario = repoFaixaSalario.save(novoFaixaSalario);

        Cargo cargo = Cargo.builder()
                        .nome("Professor")
                        .descricao("descricao")
                        .build();
        Cargo retornoCargo = repoCargo.save(cargo);


        ProfEgresso novoProfEgresso1 = ProfEgresso.builder()
                        .empresa("UFMA")
                        .descricao("texto")
                        .data_registro(LocalDate.of(2022, Month.MAY, 15))
                        .egresso_id(retornoEgresso)
                        .cargo_id(retornoCargo)
                        .faixa_salario_id(retornoFaixaSalario)
                        .build();
        ProfEgresso retornoProfEgresso2 = repoProfEgresso.save(novoProfEgresso1);
        
        FaixaSalario atualizarFaixaSalario = FaixaSalario.builder()
                .descricao("Atualizado")
                .build();
        // ação
        service.editarEgressoFaixaSalario(retornoEgresso.getId_egresso(), retornoFaixaSalario.getId_faixa_salario() , atualizarFaixaSalario);
        
    }

    @Test
    public void deveEditarCargo() {
        Egresso egresso = Egresso.builder().nome("Maria de Jesus")
                .cpf("123")
                .email("a@a.com")
                .resumo("resumo abc")
                .build();
        Egresso retornoEgresso = repoEgresso.save(egresso);

        Cargo cargo = Cargo.builder()
                                .nome("Professor")
                                .descricao("descricao")
                                .build();
        Cargo retornoCargo = repoCargo.save(cargo);


        FaixaSalario novoFaixaSalario = FaixaSalario.builder()
            .descricao("20 pau")
            .build();
        FaixaSalario retornoFaixaSalario = repoFaixaSalario.save(novoFaixaSalario);

        ProfEgresso novoProfEgresso = ProfEgresso.builder()
                        .empresa("UFMA")
                        .descricao("texto")
                        .data_registro(LocalDate.of(2022, Month.MAY, 15))
                        .egresso_id(retornoEgresso)
                        .cargo_id(retornoCargo)
                        .faixa_salario_id(retornoFaixaSalario)
                        .build();
        ProfEgresso retornoProfEgresso = repoProfEgresso.save(novoProfEgresso);

        Cargo atualizaCargo = Cargo.builder()
                                .nome(">>Cargo Atualizado<<")
                                .descricao("descricao")
                                .build();

        service.editarEgressoCargo(retornoEgresso.getId_egresso(), retornoCargo.getId_cargo(), atualizaCargo);
    }
}

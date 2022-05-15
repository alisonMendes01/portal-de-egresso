package br.ufma.portal.testeEntidade;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.ufma.portal.entidades.Cargo;
import br.ufma.portal.entidades.Egresso;
import br.ufma.portal.entidades.FaixaSalario;
import br.ufma.portal.entidades.ProfessorEgresso;
import br.ufma.portal.repository.CargoRepo;
import br.ufma.portal.repository.EgressoRepo;
import br.ufma.portal.repository.FaixaSalarioRepo;
import br.ufma.portal.repository.ProfessorEgressoRepo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TesteProfessorEgresso {
    @Autowired
    public
    ProfessorEgressoRepo repoProfessorEgresso;
    
    @Autowired
    public
    EgressoRepo repoEgresso;

    @Autowired
    public
    CargoRepo repoCargo;

    @Autowired
    public
    FaixaSalarioRepo repoFaixaSalario;


    @Test
    public void devecriaProfessorEgresso(){
        //cenario
        
        //Egresso
        Egresso novoEgresso = Egresso.builder().nome("EgressoDeProfessorEgresso").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novoEgresso);
        
        //Cargo
        Cargo novoCargo = Cargo.builder().nome("criarCargo").descricao("descricao").build();
        Cargo retornoCargo = repoCargo.save(novoCargo);

       
        //FaixaSalario
        FaixaSalario novoFaixaSalario = FaixaSalario.builder().descricao("descricao").build();
        FaixaSalario retornoFaixaSalario  = repoFaixaSalario.save(novoFaixaSalario);
        
        //Professor
        ProfessorEgresso novoProfessorEgresso = ProfessorEgresso.builder().empresa("UFMA").descricao("texto").data_registro(LocalDate.of(2022,Month.MAY, 15)).egresso_id(retornoEgresso).cargo_id(retornoCargo).faixa_salario_id(retornoFaixaSalario).build();
        
        // acao
        ProfessorEgresso retornoProfessorEgresso = repoProfessorEgresso.save(novoProfessorEgresso);

        // verificacao
        Assertions.assertNotNull(retornoProfessorEgresso);
        
        //Rollback
        repoProfessorEgresso.delete(retornoProfessorEgresso);
        repoCargo.delete(retornoCargo);
        repoFaixaSalario.delete(retornoFaixaSalario);
        repoEgresso.delete(retornoEgresso);

    }

    @Test
    public void deveremoverProfessorEgresso(){
        //cenario
        
        //Egresso
        Egresso novoEgresso = Egresso.builder().nome("EgressoDeProfessorEgresso").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novoEgresso);
        
        //Cargo
        Cargo novoCargo = Cargo.builder().nome("criarCargo").descricao("descricao").build();
        Cargo retornoCargo = repoCargo.save(novoCargo);

       
        //FaixaSalario
        FaixaSalario novoFaixaSalario = FaixaSalario.builder().descricao("descricao").build();
        FaixaSalario retornoFaixaSalario  = repoFaixaSalario.save(novoFaixaSalario);
        
        //Professor
        ProfessorEgresso novoProfessorEgresso = ProfessorEgresso.builder().empresa("UFMA").descricao("texto").data_registro(LocalDate.of(2022,Month.MAY, 15)).egresso_id(retornoEgresso).cargo_id(retornoCargo).faixa_salario_id(retornoFaixaSalario).build();
        ProfessorEgresso retornoProfessorEgresso = repoProfessorEgresso.save(novoProfessorEgresso);

        // acao
        Integer id = retornoProfessorEgresso.getId_prof_egresso();
        repoProfessorEgresso.deleteById(id);

        // verificacao
        Optional<ProfessorEgresso> temp = repoProfessorEgresso.findById(id);  
        Assertions.assertFalse(temp.isPresent());
        
        //Rollback
        repoProfessorEgresso.delete(retornoProfessorEgresso);
        repoCargo.delete(retornoCargo);
        repoFaixaSalario.delete(retornoFaixaSalario);
        repoEgresso.delete(retornoEgresso);
    }

    @Test
    public void deveobterProfessorEgresso(){
        //cenario
        
        //Egresso
        Egresso novoEgresso = Egresso.builder().nome("EgressoDeProfessorEgresso").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novoEgresso);
        
        //Cargo
        Cargo novoCargo = Cargo.builder().nome("criarCargo").descricao("descricao").build();
        Cargo retornoCargo = repoCargo.save(novoCargo);

       
        //FaixaSalario
        FaixaSalario novoFaixaSalario = FaixaSalario.builder().descricao("descricao").build();
        FaixaSalario retornoFaixaSalario  = repoFaixaSalario.save(novoFaixaSalario);
        
        //Professor
        ProfessorEgresso novoProfessorEgresso = ProfessorEgresso.builder().empresa("UFMA").descricao("texto").data_registro(LocalDate.of(2022,Month.MAY, 15)).egresso_id(retornoEgresso).cargo_id(retornoCargo).faixa_salario_id(retornoFaixaSalario).build();
        ProfessorEgresso retornoProfessorEgresso = repoProfessorEgresso.save(novoProfessorEgresso);

        // acao
        Optional<ProfessorEgresso> temp = repoProfessorEgresso.findById(retornoProfessorEgresso.getId_prof_egresso());
    
        // verificacao
        Assertions.assertTrue(temp.isPresent());
        
        //Rollback
        repoProfessorEgresso.delete(retornoProfessorEgresso);
        repoCargo.delete(retornoCargo);
        repoFaixaSalario.delete(retornoFaixaSalario);
        repoEgresso.delete(retornoEgresso);

    }
    
}
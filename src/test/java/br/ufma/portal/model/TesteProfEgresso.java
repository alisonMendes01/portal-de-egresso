package br.ufma.portal.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.portal.model.repository.CargoRepo;
import br.ufma.portal.model.repository.EgressoRepo;
import br.ufma.portal.model.repository.FaixaSalarioRepo;
import br.ufma.portal.model.repository.ProfEgressoRepo;

@SpringBootTest
public class TesteProfEgresso {
    @Autowired
    public
    ProfEgressoRepo repoProfEgresso;
    
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
    public void devecriaProfEgresso(){
        //cenario
        
        //Egresso
        Egresso novoEgresso = Egresso.builder().nome("EgressoDeProfEgresso").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novoEgresso);
        
        //Cargo
        Cargo novoCargo = Cargo.builder().nome("criarCargo").descricao("descricao").build();
        Cargo retornoCargo = repoCargo.save(novoCargo);

       
        //FaixaSalario
        FaixaSalario novoFaixaSalario = FaixaSalario.builder().descricao("descricao").build();
        FaixaSalario retornoFaixaSalario  = repoFaixaSalario.save(novoFaixaSalario);
        
        //Prof
        ProfEgresso novoProfEgresso = ProfEgresso.builder().empresa("UFMA").descricao("texto").data_registro(LocalDate.of(2022,Month.MAY, 15)).egresso_id(retornoEgresso).cargo_id(retornoCargo).faixa_salario_id(retornoFaixaSalario).build();
        
        // acao
        ProfEgresso retornoProfEgresso = repoProfEgresso.save(novoProfEgresso);

        // verificacao
        Assertions.assertNotNull(retornoProfEgresso);
        
        //Rollback
        repoProfEgresso.delete(retornoProfEgresso);
        repoCargo.delete(retornoCargo);
        repoFaixaSalario.delete(retornoFaixaSalario);
        repoEgresso.delete(retornoEgresso);

    }

    @Test
    public void deveremoverProfEgresso(){
        //cenario
        
        //Egresso
        Egresso novoEgresso = Egresso.builder().nome("EgressoDeProfEgresso").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novoEgresso);
        
        //Cargo
        Cargo novoCargo = Cargo.builder().nome("criarCargo").descricao("descricao").build();
        Cargo retornoCargo = repoCargo.save(novoCargo);

       
        //FaixaSalario
        FaixaSalario novoFaixaSalario = FaixaSalario.builder().descricao("descricao").build();
        FaixaSalario retornoFaixaSalario  = repoFaixaSalario.save(novoFaixaSalario);
        
        //Prof
        ProfEgresso novoProfEgresso = ProfEgresso.builder().empresa("UFMA").descricao("texto").data_registro(LocalDate.of(2022,Month.MAY, 15)).egresso_id(retornoEgresso).cargo_id(retornoCargo).faixa_salario_id(retornoFaixaSalario).build();
        ProfEgresso retornoProfEgresso = repoProfEgresso.save(novoProfEgresso);

        // acao
        Integer id = retornoProfEgresso.getId_prof_egresso();
        repoProfEgresso.deleteById(id);

        // verificacao
        Optional<ProfEgresso> temp = repoProfEgresso.findById(id);  
        Assertions.assertFalse(temp.isPresent());
        
        //Rollback
        repoProfEgresso.delete(retornoProfEgresso);
        repoCargo.delete(retornoCargo);
        repoFaixaSalario.delete(retornoFaixaSalario);
        repoEgresso.delete(retornoEgresso);
    }

    @Test
    public void deveobterProfEgresso(){
        //cenario
        
        //Egresso
        Egresso novoEgresso = Egresso.builder().nome("EgressoDeProfEgresso").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novoEgresso);
        
        //Cargo
        Cargo novoCargo = Cargo.builder().nome("criarCargo").descricao("descricao").build();
        Cargo retornoCargo = repoCargo.save(novoCargo);

       
        //FaixaSalario
        FaixaSalario novoFaixaSalario = FaixaSalario.builder().descricao("descricao").build();
        FaixaSalario retornoFaixaSalario  = repoFaixaSalario.save(novoFaixaSalario);
        
        //Prof
        ProfEgresso novoProfEgresso = ProfEgresso.builder().empresa("UFMA").descricao("texto").data_registro(LocalDate.of(2022,Month.MAY, 15)).egresso_id(retornoEgresso).cargo_id(retornoCargo).faixa_salario_id(retornoFaixaSalario).build();
        ProfEgresso retornoProfEgresso = repoProfEgresso.save(novoProfEgresso);

        // acao
        Optional<ProfEgresso> temp = repoProfEgresso.findById(retornoProfEgresso.getId_prof_egresso());
    
        // verificacao
        Assertions.assertTrue(temp.isPresent());
        
        //Rollback
        repoProfEgresso.delete(retornoProfEgresso);
        repoCargo.delete(retornoCargo);
        repoFaixaSalario.delete(retornoFaixaSalario);
        repoEgresso.delete(retornoEgresso);

    }
    
}
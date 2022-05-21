package br.ufma.portal.entidade;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.ufma.portal.entidades.Depoimento;
import br.ufma.portal.entidades.Egresso;
import br.ufma.portal.repository.DepoimentoRepo;
import br.ufma.portal.repository.EgressoRepo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TesteDepoimento {
    @Autowired
    public
    DepoimentoRepo repoDepoimento;
    
    @Autowired
    public
    EgressoRepo repoEgresso;

    @Test
    public void devecriaDepoimento(){
        //cenario
        Egresso novo = Egresso.builder().nome("EgressoDeDepoimento").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novo);

        Depoimento novoDepoimento = Depoimento.builder().texto("texto").data(LocalDate.of(2022,Month.MAY, 15)).egresso_id(retornoEgresso).build();
        
        // acao
        Depoimento retornoDepoimento = repoDepoimento.save(novoDepoimento);

        // verificacao
        Assertions.assertNotNull(retornoDepoimento);
        
        //Rollback
        repoDepoimento.delete(retornoDepoimento);
        repoEgresso.delete(retornoEgresso);

    }

    @Test
    public void deveremoverDepoimento(){
        //cenario
        Egresso novo = Egresso.builder().nome("EgressoDeDepoimento").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novo);
        
        Depoimento novoDepoimento = Depoimento.builder().texto("texto").data(LocalDate.of(2022,Month.MAY, 15)).egresso_id(retornoEgresso).build();
        
        
        // acao
        Depoimento retornoDepoimento = repoDepoimento.save(novoDepoimento);
        Integer id = retornoDepoimento.getId_depoimento();
        repoDepoimento.deleteById(id);
        // verificacao
        Assertions.assertNotNull(retornoDepoimento);
        
        //Rollback
        Optional<Depoimento> temp = repoDepoimento.findById(id);  
        Assertions.assertFalse(temp.isPresent());

    }


    @Test
    public void deveobterDepoimento(){
        //cenario
        Egresso novo = Egresso.builder().nome("EgressoDeDepoimento").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novo);

        Depoimento novoDepoimento = Depoimento.builder().texto("texto").data(LocalDate.of(2022,Month.MAY, 15)).egresso_id(retornoEgresso).build();
        Depoimento retornoDepoimento = repoDepoimento.save(novoDepoimento);
        //Ação

        Optional<Depoimento> temp = repoDepoimento.findById(retornoDepoimento.getId_depoimento());
        
        Assertions.assertTrue(temp.isPresent());

        //Rollback
        repoDepoimento.delete(retornoDepoimento);
        repoEgresso.delete(retornoEgresso);


    }
    
}
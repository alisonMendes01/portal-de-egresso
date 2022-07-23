package br.ufma.portal.model;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.portal.model.repository.FaixaSalarioRepo;

@SpringBootTest
public class TesteFaixaSalario {
    @Autowired
    public FaixaSalarioRepo repo;

    @Test
    public void devecriaFaixaSalario() {
        // cenario
        FaixaSalario novo = FaixaSalario.builder().descricao("descricao").build();

        // acao
        FaixaSalario retorno = repo.save(novo);
        // verificacao
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(novo.getDescricao(), retorno.getDescricao());

        // RollBack
        repo.delete(retorno);
    }

    @Test
    public void deveremoverFaixaSalario() {
        // cenario
        FaixaSalario novo = FaixaSalario.builder().descricao("descrição").build();
        FaixaSalario retorno = repo.save(novo);

        // Ação
        Integer id = retorno.getId_faixa_salario();
        repo.deleteById(id);

        // Verificação
        Optional<FaixaSalario> temp = repo.findById(id);
        Assertions.assertFalse(temp.isPresent());

    }

    @Test
    public void deveBuscarFaixaSalario() {
        FaixaSalario novo = FaixaSalario.builder().descricao("descrição").build();
        FaixaSalario retorno = repo.save(novo);

        // Ação
        Optional<FaixaSalario> temp = repo.findById(retorno.getId_faixa_salario());

        // verificacao
        Assertions.assertTrue(temp.isPresent());

        // Rollback
        repo.delete(retorno);

    }

}
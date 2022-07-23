package br.ufma.portal.model;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.portal.model.repository.ContatoRepo;

@SpringBootTest
public class TesteContato {
    @Autowired
    public ContatoRepo repo;

    @Test
    public void devecriaContato() {
        // cenario
        Contato novo = Contato.builder().nome("CriarContato").url_logo("urlaqui").build();

        // acao
        Contato retorno = repo.save(novo);
        // verificacao
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(novo.getNome(), retorno.getNome());

        // Rollback
        repo.delete(retorno);
    }

    @Test
    public void deveRemoverContato() {
        // cenario
        Contato novo = Contato.builder().nome("RemoverContato").url_logo("urlaqui").build();
        Contato retorno = repo.save(novo);

        // acao
        Integer id = retorno.getId_contato();
        repo.deleteById(id);

        // verificação
        Optional<Contato> temp = repo.findById(id);
        Assertions.assertFalse(temp.isPresent());

    }

    @Test
    public void deveBuscarContato() {
        // cenario
        Contato novo = Contato.builder().nome("BuscarCOntato").url_logo("urlaqui").build();
        Contato retorno = repo.save(novo);

        // acao
        Optional<Contato> temp = repo.findById(retorno.getId_contato());
        // verificacao

        Assertions.assertTrue(temp.isPresent());

        // Rollback
        repo.delete(retorno);

    }
}
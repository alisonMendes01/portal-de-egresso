package br.ufma.portal.model;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.portal.model.repository.ContatoEgressoRepo;
import br.ufma.portal.model.repository.ContatoRepo;
import br.ufma.portal.model.repository.EgressoRepo;

@SpringBootTest
public class TesteContatoEgresso {

    @Autowired
    public ContatoEgressoRepo repoContatoEgresso;

    @Autowired
    public EgressoRepo repoEgresso;

    @Autowired
    public ContatoRepo repoContato;

    @Test
    public void devecriaContatoEgresso() {
        // cenario
        Egresso novoEgresso = Egresso.builder().nome("EgressoDeCursoEgresso").email("email@email.com").cpf("000")
                .resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novoEgresso);

        Contato novoContato = Contato.builder().nome("RemoverContato").url_logo("urlaqui").build();
        Contato retornoContato = repoContato.save(novoContato);

        ContatoEgresso novoContatoEgresso = ContatoEgresso.builder().egresso_id(retornoEgresso)
                .contato_id(retornoContato).build();
        // acao

        ContatoEgresso retornoContatoEgresso = repoContatoEgresso.save(novoContatoEgresso);
        // verificacao
        Assertions.assertNotNull(retornoContatoEgresso);

        // Rollback
        repoContatoEgresso.delete(retornoContatoEgresso);
        repoEgresso.delete(retornoEgresso);
        repoContato.delete(retornoContato);
    }

    @Test
    public void deveremoverContatoEgresso() {
        // cenario
        // Cria Egresso
        Egresso novoEgresso = Egresso.builder().nome("EgressoDeCursoEgresso").email("email@email.com").cpf("000")
                .resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novoEgresso);
        // Cria Contato
        Contato novoContato = Contato.builder().nome("RemoverContato").url_logo("urlaqui").build();
        Contato retornoContato = repoContato.save(novoContato);
        // Cria CursoEgresso
        ContatoEgresso novoContatoEgresso = ContatoEgresso.builder().egresso_id(retornoEgresso)
                .contato_id(retornoContato).build();
        // acao

        ContatoEgresso retornoContatoEgresso = repoContatoEgresso.save(novoContatoEgresso);

        // A????o
        ContatoEgressoPk id = retornoContatoEgresso.getId();
        repoContatoEgresso.deleteById(id);

        // Verifica????o
        Optional<ContatoEgresso> temp = repoContatoEgresso.findById(id);
        Assertions.assertFalse(temp.isPresent());

    }

    @Test
    public void deveBuscarContatoEgresso() {
        // cenario
        // Cria Egresso
        Egresso novoEgresso = Egresso.builder().nome("EgressoDeCursoEgresso").email("email@email.com").cpf("000")
                .resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novoEgresso);
        // Cria Contato
        Contato novoContato = Contato.builder().nome("RemoverContato").url_logo("urlaqui").build();
        Contato retornoContato = repoContato.save(novoContato);
        // Cria CursoEgresso
        ContatoEgresso novoContatoEgresso = ContatoEgresso.builder().egresso_id(retornoEgresso)
                .contato_id(retornoContato).build();
        // acao

        ContatoEgresso retornoContatoEgresso = repoContatoEgresso.save(novoContatoEgresso);

        // acao
        Optional<ContatoEgresso> temp = repoContatoEgresso.findById(retornoContatoEgresso.getId());

        // verificacao
        Assertions.assertTrue(temp.isPresent());

        // Rollback
        repoContatoEgresso.delete(retornoContatoEgresso);
    }

}

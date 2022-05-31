package br.ufma.portal.services;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.ufma.portal.entidade.Depoimento;
import br.ufma.portal.entidade.Egresso;
import br.ufma.portal.entidade.repository.DepoimentoRepo;
import br.ufma.portal.entidade.repository.EgressoRepo;
import br.ufma.portal.service.DepoimentoService;
import br.ufma.portal.service.exception.RegraNegocioRunTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TesteDepoimentoService {

    @Autowired
    public
    DepoimentoRepo repoDepoimento;

    @Autowired
    public
    EgressoRepo repoEgresso;

    @Autowired
    DepoimentoService service;

    @Test
    public void deveGerarErroAoTentarSalvarSemTexto() {
        Egresso novo = Egresso.builder().nome("EgressoDeDepoimento").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novo);

        Depoimento novoDepoimento = Depoimento.builder().data(LocalDate.of(2022,Month.MAY, 15)).egresso_id(retornoEgresso).build();

        RegraNegocioRunTime assertt = Assertions.assertThrows(RegraNegocioRunTime.class, 
                                  () -> service.salvar(novoDepoimento));
        System.out.println(assertt.getMessage());
    }
    public void deveGerarErroSeNaoRemover() {

    }
}

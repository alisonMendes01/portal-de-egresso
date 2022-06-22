package br.ufma.portal.services;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.portal.model.Depoimento;
import br.ufma.portal.model.Egresso;
import br.ufma.portal.model.dto.EgressoDepoimentoDto;
import br.ufma.portal.model.repository.DepoimentoRepo;
import br.ufma.portal.model.repository.EgressoRepo;
import br.ufma.portal.service.DepoimentoService;
import br.ufma.portal.service.exception.RegraNegocioRunTime;


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
        Egresso novo = Egresso.builder().nome("EgressoDeDepoimento")
            .email("email@email.com")
            .cpf("000")
            .resumo("resumo")
            .url_foto("urlaqui")
            .build();
        Egresso retornoEgresso = repoEgresso.save(novo);

        Depoimento novoDepoimento = Depoimento.builder().data(LocalDate.of(2022,Month.MAY, 15)).egresso_id(retornoEgresso).build();

        RegraNegocioRunTime assertt = Assertions.assertThrows(RegraNegocioRunTime.class, 
                                  () -> service.salvar(novoDepoimento));
        System.out.println(assertt.getMessage());
        
        repoDepoimento.delete(novoDepoimento);
        repoEgresso.delete(retornoEgresso);
        
    }

    @Test
    public void deveGerarErroAoTentarSalvarSemData() {
        Egresso novo = Egresso.builder().nome("EgressoDeDepoimento").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novo);

        Depoimento novoDepoimento = Depoimento.builder().texto("texto teste").egresso_id(retornoEgresso).build();

        RegraNegocioRunTime assertt = Assertions.assertThrows(RegraNegocioRunTime.class, 
                                  () -> service.salvar(novoDepoimento));
        System.out.println(assertt.getMessage());
        
        repoDepoimento.delete(novoDepoimento);
        repoEgresso.delete(retornoEgresso);

    }

    @Test
    public void deveSalvarDepoimento() {
        Egresso novo = Egresso.builder().nome("EgressoDeDepoimento").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novo);

        Depoimento novoDepoimento = Depoimento.builder().texto("texto teste").data(LocalDate.of(2022,Month.MAY, 15)).egresso_id(retornoEgresso).build();

        Depoimento retornoDepoimento = service.salvar(novoDepoimento);

        Assertions.assertNotNull(retornoDepoimento);
        Assertions.assertNotNull(retornoDepoimento.getId_depoimento());
        
        repoDepoimento.delete(retornoDepoimento);
        repoEgresso.delete(retornoEgresso);

    }

    @Test
    public void deveGerarErroRemoverSemId() {
        Egresso novo = Egresso.builder().nome("EgressoDeDepoimento").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novo);

        Depoimento novoDepoimento = Depoimento.builder().texto("texto teste").data(LocalDate.of(2022,Month.MAY, 15)).egresso_id(retornoEgresso).build();
        Depoimento retornoDepoimento = repoDepoimento.save(novoDepoimento);
        
        retornoDepoimento.setId_depoimento(null);

        RegraNegocioRunTime assertt = Assertions.assertThrows(RegraNegocioRunTime.class, 
            () -> service.remover(retornoDepoimento),
            "Erro no remover");

        System.out.println(assertt.getMessage());
        
    }

    @Test
    public void deveRemover() {
        Egresso novo = Egresso.builder().nome("EgressoDeDepoimento").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novo);

        Depoimento novoDepoimento = Depoimento.builder().texto("texto teste").data(LocalDate.of(2022,Month.MAY, 15)).egresso_id(retornoEgresso).build();
        Depoimento retornoDepoimento = service.salvar(novoDepoimento);
        
        service.remover(retornoDepoimento);
        
        Optional<Depoimento> temp = repoDepoimento.findById(retornoDepoimento.getId_depoimento());
        Assertions.assertFalse(temp.isPresent());
        
        repoEgresso.delete(retornoEgresso); // callback
    }
    
    @Test
    public void deveFiltraPeloRecentes() {
        Egresso novo = Egresso.builder().nome("EgressoDeDepoimento").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novo);

        Depoimento depoimento1 = Depoimento.builder().texto("texto teste").data(LocalDate.of(2022,Month.MAY, 15)).egresso_id(retornoEgresso).build();
        Depoimento depoimento2 = Depoimento.builder().texto("texto teste").data(LocalDate.of(2022,Month.MAY, 12)).egresso_id(retornoEgresso).build();
        Depoimento depoimento3 = Depoimento.builder().texto("texto teste").data(LocalDate.of(2022,Month.MAY, 17)).egresso_id(retornoEgresso).build();
        Depoimento depoimento4 = Depoimento.builder().texto("texto teste").data(LocalDate.of(2022,Month.MAY, 04)).egresso_id(retornoEgresso).build();
        
        service.salvar(depoimento1);
        service.salvar(depoimento2);
        service.salvar(depoimento3);
        service.salvar(depoimento4);


        System.out.println("Ordem");
        List<Depoimento> temp = service.buscarRecente();
        for (Depoimento dep: temp){
            System.out.println(dep.getData());
        }
        
    }

    @Test
    public void deveBucarporEgresso(){
        Egresso novo = Egresso.builder().nome("Egresso1").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso = repoEgresso.save(novo);

        Egresso novo2 = Egresso.builder().nome("Egresso2").email("email@email.com").cpf("000").resumo("resumo").url_foto("urlaqui").build();
        Egresso retornoEgresso2 = repoEgresso.save(novo2);
        
        Depoimento depoimento1 = Depoimento.builder().texto("Texto e1: pao de batata").data(LocalDate.of(2022,Month.MAY, 15)).egresso_id(retornoEgresso).build();
        Depoimento depoimento2 = Depoimento.builder().texto("Texto e1: estoi de parabuains").data(LocalDate.of(2022,Month.MAY, 12)).egresso_id(retornoEgresso).build();
    
        Depoimento depoimento3 = Depoimento.builder().texto("Texto e2").data(LocalDate.of(2022,Month.MAY, 17)).egresso_id(retornoEgresso2).build();
        Depoimento depoimento4 = Depoimento.builder().texto("Texto e2").data(LocalDate.of(2022,Month.MAY, 04)).egresso_id(retornoEgresso2).build();
    
        service.salvar(depoimento1);
        service.salvar(depoimento2);
        service.salvar(depoimento3);
        service.salvar(depoimento4);

        List<EgressoDepoimentoDto> temp = service.BuscarporEgresso(retornoEgresso.getId_egresso());
        System.out.println("Egresso 1");
        for (EgressoDepoimentoDto dto: temp){
            System.out.println(dto.getTexto());
        }

    }
    
}

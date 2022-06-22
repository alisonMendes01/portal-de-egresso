package br.ufma.portal.services;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.portal.model.Cargo;
import br.ufma.portal.model.Egresso;
import br.ufma.portal.model.FaixaSalario;
import br.ufma.portal.model.ProfEgresso;
import br.ufma.portal.model.repository.CargoRepo;
import br.ufma.portal.model.repository.EgressoRepo;
import br.ufma.portal.model.repository.FaixaSalarioRepo;
import br.ufma.portal.model.repository.ProfEgressoRepo;
import br.ufma.portal.service.CargoService;

@SpringBootTest
public class TesteCargoService {

        @Autowired
        public EgressoRepo repoEgresso;

        @Autowired
        public ProfEgressoRepo repoProfEgresso;

        @Autowired
        public CargoRepo repoCargo;

        @Autowired
        public FaixaSalarioRepo repoFaixaSalario;

        @Autowired
        CargoService service;

        @Test
        public void deveObterCargoPorEgresso() {

                Egresso novo = Egresso.builder()
                                .nome("João")
                                .email("email@email.com")
                                .cpf("000")
                                .resumo("resumo")
                                .url_foto("urlaqui")
                                .build();
                Egresso retornoEgresso = repoEgresso.save(novo);

                Cargo Cargo1 = Cargo.builder()
                                .nome("Professor")
                                .descricao("descricao")
                                .build();
                Cargo retornoCargo1 = repoCargo.save(Cargo1);

                Cargo Cargo2 = Cargo.builder()
                                .nome("Coordenador")
                                .descricao("descricao 2")
                                .build();
                Cargo retornoCargo2 = repoCargo.save(Cargo2);

                FaixaSalario novoFaixaSalario = FaixaSalario.builder()
                                .descricao("descricao")
                                .build();
                FaixaSalario retornoFaixaSalario = repoFaixaSalario.save(novoFaixaSalario);

                ProfEgresso novoProfEgresso = ProfEgresso.builder()
                                .empresa("UFMA")
                                .descricao("texto")
                                .data_registro(LocalDate.of(2022, Month.MAY, 15))
                                .egresso_id(retornoEgresso)
                                .cargo_id(retornoCargo1)
                                .faixa_salario_id(retornoFaixaSalario)
                                .build();
                ProfEgresso retornoProfEgresso = repoProfEgresso.save(novoProfEgresso);

                ProfEgresso novoProfEgresso2 = ProfEgresso.builder()
                                .empresa("UFMA")
                                .descricao("texto")
                                .data_registro(LocalDate.of(2022, Month.MAY, 15))
                                .egresso_id(retornoEgresso)
                                .cargo_id(retornoCargo2)
                                .faixa_salario_id(retornoFaixaSalario)
                                .build();
                ProfEgresso retornoProfEgresso2 = repoProfEgresso.save(novoProfEgresso2);

                List<Cargo> cargos = service.findByEgresso(retornoEgresso.getId_egresso());
                int index = 1;
                for (Cargo cargo : cargos) {
                        System.out.println("CARGO " + (index++) + ": " + cargo.getNome());
                }

                repoProfEgresso.delete(retornoProfEgresso);
                repoProfEgresso.delete(retornoProfEgresso2);
                repoFaixaSalario.delete(retornoFaixaSalario);
                repoCargo.delete(retornoCargo1);
                repoCargo.delete(retornoCargo2);
                repoEgresso.delete(retornoEgresso);

        }

        @Test
        public void deveObterNumeroEgressosByCargo() {

                Egresso egresso1 = Egresso.builder()
                                .nome("Jose")
                                .email("teste@teste.com")
                                .cpf("000")
                                .resumo("resumo")
                                .url_foto("urlaqui")
                                .build();
                Egresso retornoEgresso = repoEgresso.save(egresso1);

                Egresso egresso2 = Egresso.builder()
                                .nome("João")
                                .email("")
                                .cpf("000")
                                .resumo("resumo")
                                .url_foto("urlaqui")
                                .build();
                Egresso retornoEgresso2 = repoEgresso.save(egresso2);

                Egresso egresso3 = Egresso.builder()
                                .nome("Maria")
                                .email("")
                                .cpf("000")
                                .resumo("resumo")
                                .url_foto("urlaqui")
                                .build();
                Egresso retornoEgresso3 = repoEgresso.save(egresso3);

                Cargo cargo = Cargo.builder()
                                .nome("Professor")
                                .descricao("descricao")
                                .build();
                Cargo retornoCargo1 = repoCargo.save(cargo);

                FaixaSalario faixaSalario = FaixaSalario.builder()
                                .descricao("descricao")
                                .build();
                FaixaSalario retornoFaixaSalario = repoFaixaSalario.save(faixaSalario);

                ProfEgresso profEgresso1 = ProfEgresso.builder()
                                .empresa("UFMA")
                                .descricao("texto")
                                .data_registro(LocalDate.of(2022, Month.MAY, 15))
                                .egresso_id(retornoEgresso)
                                .cargo_id(retornoCargo1)
                                .faixa_salario_id(retornoFaixaSalario)
                                .build();
                ProfEgresso retornoProfEgresso1 = repoProfEgresso.save(profEgresso1);

                ProfEgresso profEgresso2 = ProfEgresso.builder()
                                .empresa("UFMA")
                                .descricao("texto")
                                .data_registro(LocalDate.of(2022, Month.MAY, 15))
                                .egresso_id(retornoEgresso2)
                                .cargo_id(retornoCargo1)
                                .faixa_salario_id(retornoFaixaSalario)
                                .build();
                ProfEgresso retornoProfEgresso2 = repoProfEgresso.save(profEgresso2);

                ProfEgresso profEgresso3 = ProfEgresso.builder()
                                .empresa("UFMA")
                                .descricao("texto")
                                .data_registro(LocalDate.of(2022, Month.MAY, 15))
                                .egresso_id(retornoEgresso3)
                                .cargo_id(retornoCargo1)
                                .faixa_salario_id(retornoFaixaSalario)
                                .build();
                ProfEgresso retornoProfEgresso3 = repoProfEgresso.save(profEgresso3);

                Integer numeroEgressos = service.countEgressosByCargo(cargo.getId_cargo());
                System.out.println("NUMERO DE EGRESSOS NO CARGO: "+ numeroEgressos);

                repoProfEgresso.delete(retornoProfEgresso1);
                repoProfEgresso.delete(retornoProfEgresso2);
                repoProfEgresso.delete(retornoProfEgresso3);
                repoFaixaSalario.delete(retornoFaixaSalario);
                repoCargo.delete(retornoCargo1);
                repoEgresso.delete(retornoEgresso);
                repoEgresso.delete(retornoEgresso2);
                repoEgresso.delete(retornoEgresso3);

        }

}

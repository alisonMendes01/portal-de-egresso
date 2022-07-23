package br.ufma.portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufma.portal.model.Cargo;
import br.ufma.portal.model.dto.CargoDto;
import br.ufma.portal.service.CargoService;

@ActiveProfiles("test")
@WebMvcTest(controllers = CargoController.class)
@AutoConfigureMockMvc
public class CargoControllerTeste {

    static final String API = "/api/cargo";

    @MockBean
    private CargoService service;

    @Autowired
    MockMvc mvc;

    @Test
    public void deveSalvarCargo() throws Exception {
        // cenario
        CargoDto dto = CargoDto.builder()
                .nome("Desenvolvedor")
                .descricao("Desenvolvedor de software")
                .build();
        // resposta que será mock
        Cargo cargo = Cargo.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .build();
        // mock salvar
        Mockito.when(service.salvar(Mockito.any(Cargo.class))).thenReturn(cargo);

        // converter dto para json
        String json = new ObjectMapper().writeValueAsString(dto);

        // acao
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API.concat("/salvar"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // ação e verificacao
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void deveRemoverCargo() throws Exception {
        // cenario
        Cargo cargo = Cargo.builder()
                .id_cargo(1)
                .nome("Desenvolvedor")
                .descricao("Desenvolvedor de software")
                .build();
        // mock salvar
        Mockito.when(service.salvar(Mockito.any(Cargo.class))).thenReturn(cargo);

        // mock remover
        Mockito.when(service.remover(Mockito.anyInt())).thenReturn(cargo);

        // acao
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(API.concat("/remover/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        // ação e verificacao
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveBuscarCargo() throws Exception {
        // cenario
        List<Cargo> cargos = new ArrayList<>();
        cargos.add(Cargo.builder().build());
        cargos.add(Cargo.builder().build());
        cargos.add(Cargo.builder().build());

        // mock buscar
        Mockito.when(service.buscar(Mockito.any(Cargo.class))).thenReturn(cargos);

        // acao
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API.concat("/buscar/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        // ação e verificacao
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveAtualizarCargo() throws Exception {
        // cenario
        CargoDto dto = CargoDto.builder()
                .nome("Desenvolvedor")
                .descricao("Desenvolvedor de software")
                .build();
        // mock salvar
        Mockito.when(service.salvar(Mockito.any(Cargo.class))).thenReturn(Cargo.builder().build());

        // mock atualizar
        Mockito.when(service.atualizar(Mockito.any(Cargo.class))).thenReturn(Cargo.builder().build());

        // converter dto para json
        String json = new ObjectMapper().writeValueAsString(dto);

        // acao
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(API.concat("/atualizar/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // ação e verificacao
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveBuscarCargoPorEgresso() throws Exception {
        // cenario
        List<Cargo> cargos = new ArrayList<>();
        cargos.add(Cargo.builder().build());
        cargos.add(Cargo.builder().build());
        cargos.add(Cargo.builder().build());

        // mock buscar
        Mockito.when(service.buscarPorEgresso(Mockito.anyInt())).thenReturn(cargos);

        // acao
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API.concat("/buscar-por-egresso/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        // ação e verificacao
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveContarEgressosPorCargo() throws Exception {
        // cenario
        List<Cargo> cargos = new ArrayList<>();
        cargos.add(Cargo.builder().build());
        cargos.add(Cargo.builder().build());
        cargos.add(Cargo.builder().build());

        // mock buscar
        Mockito.when(service.contarEgressosPorCargo(Mockito.anyInt())).thenReturn(Mockito.anyInt());

        // acao
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API.concat("/contar-egressos-por-cargo/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        // ação e verificacao
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
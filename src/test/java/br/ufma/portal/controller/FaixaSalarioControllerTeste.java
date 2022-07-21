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

import br.ufma.portal.model.FaixaSalario;
import br.ufma.portal.model.dto.FaixaSalarioDto;
import br.ufma.portal.service.FaixaSalarioService;

@ActiveProfiles("test")
@WebMvcTest(controllers = FaixaSalarioController.class)
@AutoConfigureMockMvc
public class FaixaSalarioControllerTeste {

    static final String API = "/api/faixa_salario";

    @Autowired
    MockMvc mvc;

    @MockBean
    FaixaSalarioService service;

    @Test
    public void deveSalvarFaixaSalario() throws Exception {
        // cenario
        FaixaSalarioDto dto = FaixaSalarioDto.builder()
                .descricao("Faixa de salario de teste")
                .build();

        // resposta que será mock
        FaixaSalario faixaSalario = FaixaSalario.builder()
                .descricao(dto.getDescricao())
                .build();

        // mock salvar
        Mockito.when(service.salvar(Mockito.any(FaixaSalario.class))).thenReturn(faixaSalario);

        // converte o dto para json
        String json = new ObjectMapper().writeValueAsString(dto);

        // ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(API.concat("/salvar"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void deveDeletarFaixaSalario() throws Exception {
        // cenario
        FaixaSalario faixaSalario = FaixaSalario.builder()
                .id_faixa_salario(1)
                .descricao("Faixa de salario de teste")
                .build();
        // mock salvar
        Mockito.when(service.salvar(Mockito.any(FaixaSalario.class))).thenReturn(faixaSalario);
        // mock deletar
        Mockito.when(service.remover(Mockito.anyInt())).thenReturn(faixaSalario);
        // ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(API.concat("/deletar/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void deveObterFaixaSalario() throws Exception {
        // cenario
        List<FaixaSalario> faixaSalario = new ArrayList<>();
        faixaSalario.add(FaixaSalario.builder()
        .id_faixa_salario(1)
        .descricao("Faixa de salario de teste")
        .build());
        // mock obter
        Mockito.when(service.buscar(Mockito.any(FaixaSalario.class))).thenReturn(faixaSalario);

        // ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(API.concat("/obter/1"))
                .accept(MediaType.APPLICATION_JSON);
        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveAtualizarFaixaSalario() throws Exception {
        // cenario
        FaixaSalarioDto dto = FaixaSalarioDto.builder()
                .id(1)
                .descricao("Faixa de salario de teste")
                .build();
        // mock salvar
        Mockito.when(service.salvar(Mockito.any(FaixaSalario.class))).thenReturn(FaixaSalario.builder().build());

        // mock atualizar
        Mockito.when(service.editar(Mockito.any(FaixaSalario.class))).thenReturn(FaixaSalario.builder().build());

        // converte o dto para json
        String json = new ObjectMapper().writeValueAsString(dto);
        // ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(API.concat("/editar/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void deveObterEgressoPorFaixaSalario() throws Exception {
        // testa o método countEgressosByFaixaSalario
        // cenario
        List<FaixaSalario> faixaSalario = new ArrayList<>();
        faixaSalario.add(FaixaSalario.builder().build());
        faixaSalario.add(FaixaSalario.builder().build());
        // mock obter
        Mockito.when(service.countEgressosByFaixaSalario(Mockito.anyInt())).thenReturn(Mockito.anyInt());

        // ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(API.concat("/egressoporfaixasalario/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
                
    }

}

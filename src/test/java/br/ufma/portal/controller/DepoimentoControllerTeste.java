package br.ufma.portal.controller;

import java.time.LocalDate;
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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.ufma.portal.model.Depoimento;
import br.ufma.portal.model.dto.DepoimentoDto;
import br.ufma.portal.model.dto.EgressoDepoimentoDto;
import br.ufma.portal.service.DepoimentoService;

@ActiveProfiles("test")
@WebMvcTest(controllers = DepoimentoController.class)
@AutoConfigureMockMvc
public class DepoimentoControllerTeste {

    static final String API = "/api/depoimentos";

    @Autowired
    MockMvc mvc;

    @MockBean
    DepoimentoService service;

    @Test
    public void deveSalvarDepoimento() throws Exception {
        // cenario
        DepoimentoDto dto = DepoimentoDto.builder()
                .texto("Depoimento de teste")
                .data(LocalDate.now())
                .build();

        // resposta que será mock
        Depoimento depoimento = Depoimento.builder()
                .texto(dto.getTexto())
                .data(dto.getData())
                .build();

        // mock salvar
        Mockito.when(service.salvar(Mockito.any(Depoimento.class))).thenReturn(depoimento);

        // converte o dto para json
        String json = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .writeValueAsString(dto);

        // ação
        MockHttpServletRequestBuilder response = MockMvcRequestBuilders.post(API.concat("/salvar"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // ação e verificação
        mvc.perform(response)
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void deveDeletarDepoimento() throws Exception {
        // cenario
        Depoimento depoimento = Depoimento.builder()
                .id_depoimento(1)
                .texto("Depoimento de teste")
                .data(LocalDate.now())
                .build();
        // mock salvar
        Mockito.when(service.salvar(Mockito.any(Depoimento.class))).thenReturn(depoimento);
        // mock deletar
        Mockito.when(service.remover(Mockito.anyInt())).thenReturn(depoimento);
        // ação
        MockHttpServletRequestBuilder response = MockMvcRequestBuilders.delete(API.concat("/deletar/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        // ação e verificação
        mvc.perform(response)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test  
    public void deveEditarDepoimento() throws Exception{
        // cenario
        DepoimentoDto dto = DepoimentoDto.builder()
        .id(1)
                .texto("Depoimento de teste")
                .data(LocalDate.now())
                .build();

        // mock salvar
        Mockito.when(service.salvar(
                Mockito.any(Depoimento.class))).thenReturn(Depoimento.builder().build());

        // mock editar
        Mockito.when(service.editar(
                Mockito.any(Depoimento.class))).thenReturn(Depoimento.builder().build());

        // converte o dto para json
        String json = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .writeValueAsString(dto);

        // ação
        MockHttpServletRequestBuilder response = MockMvcRequestBuilders.put(API.concat("/editar/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
        // ação e verificação
        mvc.perform(response)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveObterDepoimento() throws Exception {
        // cenario
        List<Depoimento> depoimentos = new ArrayList<>();
        depoimentos.add(Depoimento.builder().build());
        depoimentos.add(Depoimento.builder().build());
        depoimentos.add(Depoimento.builder().build());

        // mock buscar
        Mockito.when(service.buscar(Mockito.any(Depoimento.class))).thenReturn(depoimentos);

        // ação
        MockHttpServletRequestBuilder response = MockMvcRequestBuilders.get(API.concat("/obter/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        // ação e verificação
        mvc.perform(response)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveObterDepoimentoPorEgresso() throws Exception {
        // esse método é para obter os depoimentos de um egresso específico e não de todos os depoimentos do sistema
        // cenario
        List<EgressoDepoimentoDto> depoimentos = new ArrayList<>();
        depoimentos.add(EgressoDepoimentoDto.builder().build());
        depoimentos.add(EgressoDepoimentoDto.builder().build());
        depoimentos.add(EgressoDepoimentoDto.builder().build());

        // mock buscar
        Mockito.when(service.buscarPorEgresso(Mockito.anyInt())).thenReturn(depoimentos);

        // ação
        MockHttpServletRequestBuilder response = MockMvcRequestBuilders.get(API.concat("/obter-por-egresso/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        // ação e verificação
        mvc.perform(response)
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void deveObterDepoimentosRecentes() throws Exception {
        // cenario
        List<Depoimento> depoimentos = new ArrayList<>();
        depoimentos.add(Depoimento.builder().build());
        depoimentos.add(Depoimento.builder().build());
        depoimentos.add(Depoimento.builder().build());

        // mock buscar
        Mockito.when(service.buscarRecente()).thenReturn(depoimentos);

        // ação
        MockHttpServletRequestBuilder response = MockMvcRequestBuilders.get(API.concat("/obter-recente"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        // ação e verificação
        mvc.perform(response)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
}
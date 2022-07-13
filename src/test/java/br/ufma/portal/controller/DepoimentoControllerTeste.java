package br.ufma.portal.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufma.portal.model.Depoimento;
import br.ufma.portal.model.dto.DepoimentoDto;
import br.ufma.portal.model.dto.EgressoDepoimentoDto;
import br.ufma.portal.service.DepoimentoService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = CargoController.class)
@AutoConfigureMockMvc
public class DepoimentoControllerTeste{
    static final String API = "/api/depoimento";

    @Autowired
    MockMvc mvc;

    @MockBean
    DepoimentoService service;

    @Test
    public void deveSalvar() throws Exception {
        //Cenário
        //Dto para virar json
        DepoimentoDto dto = DepoimentoDto.builder().texto("abc").build();

        //Resposta que será mock
        Depoimento depoimento = Depoimento.builder().id_depoimento(11).texto("abc").build();

        //mock salvar
        Mockito.when(service.salvar(Mockito.any(Depoimento.class))).thenReturn(depoimento);

        //Convertendo para json
        String json = new ObjectMapper().writeValueAsString(dto);

        //Ação
        //controi requisição post
        MockHttpServletRequestBuilder request = 
                                            MockMvcRequestBuilders.post(API.concat("/salvar"))
                                            .accept(MediaType.APPLICATION_JSON)
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(json);


        //Ação e Verificação
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void deveRemover() throws Exception {
        Depoimento depoimento = Depoimento.builder().id_depoimento(1).texto("abc").build();

        Mockito.when(service.remover(Mockito.anyInt())).thenReturn(depoimento);

        MockHttpServletRequestBuilder request = 
                                            MockMvcRequestBuilders
                                            .delete(API.concat("/deletar/1"))
                                            .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deveEditar() throws Exception {
        Integer id = 1;
        DepoimentoDto dto = DepoimentoDto.builder().texto("abc").build();
        Depoimento depoimento = Depoimento.builder().id_depoimento(1).texto("abc").build();
        
        Mockito.when(service.editar(Mockito.any(Depoimento.class))).thenReturn(depoimento);

        String json = new ObjectMapper().writeValueAsString(dto);
        
        MockHttpServletRequestBuilder request = 
        MockMvcRequestBuilders.post(API.concat("/editar/1"))
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveObter() throws Exception {
        Depoimento buscadepoimento = Depoimento.builder().id_depoimento(1).texto("abc").build();
        Depoimento depoimento = Depoimento.builder().id_depoimento(1).texto("abc").build();

        List<Depoimento> resposta = Arrays.asList(depoimento);

        Mockito.when(service.buscar(Mockito.any(Depoimento.class))).thenReturn(resposta);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                                                .get(API.concat("/obter/1"))
                                                .accept(MediaType.APPLICATION_JSON);
        
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].texto").value("abc"));
    }

    @Test
    public void deveObterPorEgresso() throws Exception{
        Integer id = 1;
        EgressoDepoimentoDto e1 = EgressoDepoimentoDto.builder().id(1).texto("abc").build();
        EgressoDepoimentoDto e2 = EgressoDepoimentoDto.builder().id(2).texto("abc").build();

        List<EgressoDepoimentoDto> retorno = Arrays.asList(e1, e2);

        Mockito.when(service.BuscarporEgresso(Mockito.anyInt())).thenReturn(retorno);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                                                .get(API.concat("/obter-por-egresso/1"))
                                                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveObterRecentes() throws Exception{
        Depoimento d1 = Depoimento.builder().id_depoimento(1).texto("abc").build();
        Depoimento d2 = Depoimento.builder().id_depoimento(2).texto("abc").build();
        List<Depoimento> retorno = Arrays.asList(d1,d2);
        Mockito.when(service.buscarRecente()).thenReturn(retorno);
        
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                                                .get(API.concat("/obter-recente"))
                                                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
                                            
    }


}
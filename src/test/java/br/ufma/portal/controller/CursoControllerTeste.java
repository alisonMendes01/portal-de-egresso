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

import br.ufma.portal.model.Curso;
import br.ufma.portal.model.Egresso;
import br.ufma.portal.model.dto.CursoDto;
import br.ufma.portal.service.CursoService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = CargoController.class)
@AutoConfigureMockMvc
public class CursoControllerTeste {
    static final String API = "/api/curso";

    @Autowired
    MockMvc mvc;

    @MockBean
    CursoService service;

    @Test
    public void deveSalvarCargo() throws Exception {
        //Cenário
        //Dto para virar json
        CursoDto dto = CursoDto.builder().nome("Teste").nivel("Superior").build();

        //Resposta que será mock
        Curso curso = Curso.builder().id_curso(11).nome("Teste").nivel("Superior").build();

        //mock salvar
        Mockito.when(service.salvar(Mockito.any(Curso.class))).thenReturn(curso);

        //Convertendo para json
        String json = new ObjectMapper().writeValueAsString(dto);

        //Ação
        //controi requisição post
        MockHttpServletRequestBuilder request = 
                                            MockMvcRequestBuilders
                                            .post(API.concat("/salvar"))
                                            .accept(MediaType.APPLICATION_JSON)
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(json);


        //Ação e Verificação
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void deveRemoverCurso() throws Exception{
        
        Curso curso = Curso.builder().id_curso(11).nome("Teste").nivel("Superior").build();

        Mockito.when(service.remover(Mockito.anyInt())).thenReturn(curso);

        MockHttpServletRequestBuilder request = 
                                            MockMvcRequestBuilders
                                            .delete(API.concat("/deletar/1"))
                                            .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deveObterCurso() throws Exception  {
        Curso buscacurso = Curso.builder().id_curso(1).build();
        Curso curso = Curso.builder().id_curso(1).nome("Teste").nivel("Superior").build();

        List<Curso> resposta = Arrays.asList(curso);

        Mockito.when(service.buscar(Mockito.any(Curso.class))).thenReturn(resposta);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                                                .get(API.concat("/obter/1"))
                                                .accept(MediaType.APPLICATION_JSON);
        
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Teste"));
    }

    @Test
    public void deveEditarCurso() throws Exception {
        Integer id = 1;
        CursoDto dto = CursoDto.builder().nome("Atualiza").nivel("desc att").build();
        Curso curso = Curso.builder().id_curso(1).nome("teste").nivel("desc tste").build();
        
        Mockito.when(service.editar(Mockito.any(Curso.class))).thenReturn(curso);

        String json = new ObjectMapper().writeValueAsString(dto);
        
        MockHttpServletRequestBuilder request = 
        MockMvcRequestBuilders
        .put(API.concat("/editar/1"))
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveObterPorEgresso() throws Exception{
        Integer id = 1;
        Egresso e1 = Egresso.builder().id_egresso(1).nome("e1").build();
        Egresso e2 = Egresso.builder().id_egresso(2).nome("e2").build();

        List<Egresso> retorno = Arrays.asList(e1, e2);

        Mockito.when(service.findByEgresso(Mockito.anyInt())).thenReturn(retorno);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                                                .get(API.concat("/obter-por-egresso/1"))
                                                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test 
    public void deveContarporEgresso() throws Exception {
        Integer count = 2;

        Mockito.when(service.countEgressosByCurso(Mockito.anyInt())).thenReturn(count);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
        .get(API.concat("/contar-por-egresso/1"))
        .accept(MediaType.ALL_VALUE);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }
}

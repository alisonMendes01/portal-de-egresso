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

import br.ufma.portal.model.Curso;
import br.ufma.portal.model.Egresso;
import br.ufma.portal.model.dto.CursoDto;
import br.ufma.portal.service.CursoService;

@ActiveProfiles("test")
@WebMvcTest(controllers = CursoController.class)
@AutoConfigureMockMvc
public class CursoControllerTeste {

    static final String API = "/api/curso";

    @Autowired
    MockMvc mvc;

    @MockBean
    private CursoService service;

    @Test
    public void deveSalvarCurso() throws Exception {
        // cenario
        CursoDto dto = CursoDto.builder()
                .nome("Java")
                .nivel("Graduacao")
                .build();
        // resposta que será mock
        Curso curso = Curso.builder()
                .nome(dto.getNome())
                .nivel(dto.getNivel())
                .build();

        // mock salvar
        Mockito.when(service.salvar(Mockito.any(Curso.class))).thenReturn(curso);

        // converter dto para json
        String json = new ObjectMapper().writeValueAsString(dto);

        // acao
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API.concat("/salvar"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void deveRemoverCurso() throws Exception {
        // cenario
        Curso curso = Curso.builder()
                .nome("Java")
                .nivel("Graduacao")
                .build();

        // mock salvar
        Mockito.when(service.salvar(Mockito.any(Curso.class))).thenReturn(curso);

        // mock remover
        Mockito.when(service.remover(Mockito.anyInt())).thenReturn(curso);

        // acao
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(API.concat("/remover/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void deveBuscarCurso() throws Exception {
        // cenario
        List<Curso> cursos = new ArrayList<>();
        cursos.add(Curso.builder().build());
        cursos.add(Curso.builder().build());
        cursos.add(Curso.builder().build());

        // mock buscar
        Mockito.when(service.buscar(Mockito.any(Curso.class))).thenReturn(cursos);

        // acao
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API.concat("/buscar/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void deveAtualizarCurso() throws Exception {
        // cenario
        CursoDto dto = CursoDto.builder()
                .nome("Java")
                .nivel("Graduacao")
                .build();

        // mock salvar
        Mockito.when(service.salvar(Mockito.any(Curso.class))).thenReturn(Curso.builder().build());

        // mock atualizar
        Mockito.when(service.atualizar(Mockito.any(Curso.class))).thenReturn(Curso.builder().build());

        // converter dto para json
        String json = new ObjectMapper().writeValueAsString(dto);

        // acao
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(API.concat("/atualizar/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void deveBuscarCursoPorEgresso() throws Exception {
        // cenario
        List<Curso> cursos = new ArrayList<>();
        cursos.add(Curso.builder().build());
        cursos.add(Curso.builder().build());
        cursos.add(Curso.builder().build());

        // mock buscar por egresso
        Mockito.when(service.buscarPorEgresso(Mockito.anyInt())).thenReturn(List.of(Egresso.builder().build()));

        // acao
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API.concat("/buscar-por-egresso/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
                          
    }

    @Test
    public void deveContarCurso() throws Exception {
        // cenario
        List<Curso> cursos = new ArrayList<>();
        cursos.add(Curso.builder().build());
        cursos.add(Curso.builder().build());
        cursos.add(Curso.builder().build());
        // mock contar
        Mockito.when(service.contarEgressosPorCurso(Mockito.anyInt())).thenReturn(1);
        // acao
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API.concat("/contar-egressos-por-curso/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

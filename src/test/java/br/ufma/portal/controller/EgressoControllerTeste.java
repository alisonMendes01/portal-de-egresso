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
import br.ufma.portal.model.Contato;
import br.ufma.portal.model.Curso;
import br.ufma.portal.model.Egresso;
import br.ufma.portal.model.FaixaSalario;
import br.ufma.portal.model.dto.EgressoDto;
import br.ufma.portal.service.EgressoService;

@ActiveProfiles("test")
@WebMvcTest(controllers = EgressoController.class)
@AutoConfigureMockMvc
public class EgressoControllerTeste {

    static final String API = "/api/egresso";

    @Autowired
    MockMvc mvc;

    @MockBean
    EgressoService service;

    @Test
    public void deveSalvarEgresso() throws Exception {
        // cenário
        EgressoDto dto = EgressoDto.builder()
                .nome("Egresso Teste")
                .email("teste@teste.com")
                .cpf("123456789")
                .resumo("Resumo do Egresso Teste")
                .build();

        // resposta que será mock
        Egresso egresso = Egresso.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .resumo(dto.getResumo())
                .build();

        // mock salvar
        Mockito.when(service.salvar(
                Mockito.any(Egresso.class))).thenReturn(egresso);

        // converte dto para json
        String json = new ObjectMapper().writeValueAsString(dto);

        // ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API.concat("/salvar"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void deveRemoverEgresso() throws Exception { 
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("Egresso Teste")
                .email("teste@teste.com")
                .cpf("123456789")
                .resumo("Resumo do Egresso Teste")
                .build();
       
        // mock salvar
        Mockito.when(service.salvar(
                Mockito.any(Egresso.class))).thenReturn(egresso);

        // mock remover
        Mockito.when(service.remover(
                Mockito.anyInt())).thenReturn(egresso);

        // ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(API.concat("/remover/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveBuscarEgresso() throws Exception {
        //cenário
        List<Egresso> egressos = new ArrayList<>();
        egressos.add(Egresso.builder().build());
        egressos.add(Egresso.builder().build());
        egressos.add(Egresso.builder().build());

        // mock buscar
        Mockito.when(service.buscar(Mockito.any(Egresso.class))).thenReturn(egressos);

         //ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API.concat("/buscar/1"))
                .accept(MediaType.APPLICATION_JSON);

        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveAtualizarEgresso() throws Exception {
        // cenario
        EgressoDto dto = EgressoDto.builder()
                .nome("Egresso Teste")
                .email("teste@teste.com")
                .cpf("123456789")
                .resumo("Resumo do Egresso Teste")
                .build();

        // mock salvar
        Mockito.when(service.salvar(
                Mockito.any(Egresso.class))).thenReturn(Egresso.builder().build());

        // mock atualizar
        Mockito.when(service.atualizar(
                Mockito.any(Egresso.class))).thenReturn(Egresso.builder().build());

        // converte dto para json
        String json = new ObjectMapper().writeValueAsString(dto);

        // ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(API.concat("/atualizar/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void deveAtualizarContato() throws Exception {
        // cenario
        EgressoDto dto = EgressoDto.builder()
                .nome("Egresso Teste")
                .email("teste@teste.com")
                .cpf("123456789")
                .resumo("Resumo do Egresso Teste")
                .build();

        // mock salvar
        Mockito.when(service.salvar(
                Mockito.any(Egresso.class))).thenReturn(Egresso.builder().build());

        // mock atualizar contato egresso 
        Mockito.when(service.atualizarEgressoContato(
                Mockito.anyInt(), Mockito.anyInt(), Mockito.any(Contato.class))).thenReturn(Contato.builder().build());

        // converte dto para json
        String json = new ObjectMapper().writeValueAsString(dto);

        // ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(API.concat("/atualizar/contato/1&1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
       

    @Test
    public void deveAtualizarCargo() throws Exception {
        // cenario
        EgressoDto dto = EgressoDto.builder()
                .nome("Egresso Teste")
                .email("teste@teste.com")
                .cpf("123456789")
                .resumo("Resumo do Egresso Teste")
                .build();

        // mock salvar
        Mockito.when(service.salvar(
                Mockito.any(Egresso.class))).thenReturn(Egresso.builder().build());

        // mock atualizar cargo egresso
        Mockito.when(service.atualizarEgressoCargo(
                Mockito.anyInt(), Mockito.anyInt(), Mockito.any(Cargo.class))).thenReturn(Cargo.builder().build());

        // converte dto para json
        String json = new ObjectMapper().writeValueAsString(dto);

        // ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(API.concat("/atualizar/cargo/1&1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveAtualizarFaixaSalario() throws Exception {
        // cenario
        EgressoDto dto = EgressoDto.builder()
                .nome("Egresso Teste")
                .email("teste@teste.com")
                .cpf("123456789")
                .resumo("Resumo do Egresso Teste")
                .build();
        // mock salvar
        Mockito.when(service.salvar(
                Mockito.any(Egresso.class))).thenReturn(Egresso.builder().build());
        // mock atualizar faixa salario egresso
        Mockito.when(service.atualizarEgressoFaixaSalario(
                Mockito.anyInt(), Mockito.anyInt(), Mockito.any(FaixaSalario.class))).thenReturn(FaixaSalario.builder().build());
                
        // converte dto para json
        String json = new ObjectMapper().writeValueAsString(dto);

        // ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(API.concat("/atualizar/faixasalario/1&1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    
    }

    @Test
    public void deveAtualizarCurso() throws Exception {
        // cenario
        EgressoDto dto = EgressoDto.builder()
                .nome("Egresso Teste")
                .email("teste@teste.com")
                .cpf("123456789")
                .resumo("Resumo do Egresso Teste")
                .build();
        // mock salvar
        Mockito.when(service.salvar(
                Mockito.any(Egresso.class))).thenReturn(Egresso.builder().build());
        // mock atualizar curso egresso
        Mockito.when(service.atualizarEgressoCurso(
                Mockito.anyInt(), Mockito.anyInt(), Mockito.any(Curso.class))).thenReturn(Curso.builder().build());
                
        // converte dto para json
        String json = new ObjectMapper().writeValueAsString(dto);

        // ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(API.concat("/atualizar/curso/1&1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // ação e verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
                
    }
}

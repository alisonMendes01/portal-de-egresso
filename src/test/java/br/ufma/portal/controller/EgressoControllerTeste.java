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

import br.ufma.portal.model.Cargo;
import br.ufma.portal.model.Contato;
import br.ufma.portal.model.Curso;
import br.ufma.portal.model.CursoEgresso;
import br.ufma.portal.model.Egresso;
import br.ufma.portal.model.FaixaSalario;
import br.ufma.portal.model.dto.EgressoDto;
import br.ufma.portal.service.DepoimentoService;
import br.ufma.portal.service.EgressoService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = CargoController.class)
@AutoConfigureMockMvc
public class EgressoControllerTeste {
    static final String API = "/api/egresso";

    @Autowired
    MockMvc mvc;

    @MockBean
    EgressoService service;


    @Test
    public void deveSalvar() throws Exception{
        //Cenário
        //Dto para virar json
        EgressoDto dto = EgressoDto.builder().nome("teste1").cpf("123").build();


        //Resposta que será mock
        Egresso egresso = Egresso.builder().id_egresso(1).nome("teste1").cpf("123").build();

        //mock salvar
        Mockito.when(service.salvar(Mockito.any(Egresso.class))).thenReturn(egresso);

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
    public void deveRemover() throws Exception{
        Egresso egresso = Egresso.builder().id_egresso(1).nome("teste1").cpf("123").build();

        Mockito.when(service.remover(Mockito.anyInt())).thenReturn(egresso);

        MockHttpServletRequestBuilder request = 
                                            MockMvcRequestBuilders
                                            .delete(API.concat("/deletar/1"))
                                            .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deveEditar() throws Exception{
        Integer id = 1;
        EgressoDto dto = EgressoDto.builder().nome("teste1").cpf("123").build();
        Egresso egresso = Egresso.builder().id_egresso(1).nome("teste1").cpf("123").build();
        
        Mockito.when(service.editar(Mockito.any(Egresso.class))).thenReturn(egresso);

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
    public void deveEditarContato() throws Exception{
        Integer id = 1;
        Egresso egresso = Egresso.builder().id_egresso(1).build();
        Contato contatoretorno = Contato.builder().build();
        Contato contatoentrada = Contato.builder().build();
        
        Mockito.when(service.editarEgressoContato(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(Contato.class))).thenReturn(contatoretorno);

        String json = new ObjectMapper().writeValueAsString(contatoentrada);
        
        MockHttpServletRequestBuilder request = 
        MockMvcRequestBuilders
        .put(API.concat("/editar/contato/1&1"))
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveEditarCargo() throws Exception{
        Integer id = 1;
        Egresso egresso = Egresso.builder().id_egresso(1).build();
        Cargo cargoretorno = Cargo.builder().build();
        Cargo cargoentrada = Cargo.builder().build();
        
        Mockito.when(service.editarEgressoCargo(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(Cargo.class))).thenReturn(cargoretorno);

        String json = new ObjectMapper().writeValueAsString(cargoentrada);
        
        MockHttpServletRequestBuilder request = 
        MockMvcRequestBuilders
        .put(API.concat("/editar/cargo/1&1"))
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveEditarFaixaSalario() throws Exception{
        Integer id = 1;
        Egresso egresso = Egresso.builder().id_egresso(1).build();
        FaixaSalario faixasalarioretorno = FaixaSalario.builder().build();
        FaixaSalario faixasalariontrada = FaixaSalario.builder().build();
        
        Mockito.when(service.editarEgressoFaixaSalario(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(FaixaSalario.class))).thenReturn(faixasalarioretorno);

        String json = new ObjectMapper().writeValueAsString(faixasalariontrada);
        
        MockHttpServletRequestBuilder request = 
        MockMvcRequestBuilders
        .put(API.concat("/editar/faixasalario/1&1"))
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveEditarCurso() throws Exception{
        Integer id = 1;
        Egresso egresso = Egresso.builder().id_egresso(1).build();
        CursoEgresso cursoretorno = CursoEgresso.builder().build();
        CursoEgresso cursontrada = CursoEgresso.builder().build();
        
        Mockito.when(service.editarCurso(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(CursoEgresso.class))).thenReturn(cursoretorno);

        String json = new ObjectMapper().writeValueAsString(cursontrada);
        
        MockHttpServletRequestBuilder request = 
        MockMvcRequestBuilders
        .put(API.concat("/editar/curso/1&1"))
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveBuscar() throws Exception{
        Egresso egresso = Egresso.builder().id_egresso(1).build();

        List<Egresso> resposta = Arrays.asList(egresso);

        Mockito.when(service.buscar(Mockito.any(Egresso.class))).thenReturn(resposta);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                                                .get(API.concat("/obter/1"))
                                                .accept(MediaType.APPLICATION_JSON);
        
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }


}

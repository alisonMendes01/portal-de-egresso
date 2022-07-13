package br.ufma.portal.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufma.portal.model.Cargo;
import br.ufma.portal.model.dto.CargoDto;
import br.ufma.portal.service.CargoService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = CargoController.class)
@AutoConfigureMockMvc
public class CargoControllerTeste{
    
    static final String API = "/api/cargo";

    @Autowired
    MockMvc mvc;

    @MockBean
    CargoService service;

    @Test
    public void deveSalvarCargo() throws Exception{
        //Cenário
        //Dto para virar json
        CargoDto dto = CargoDto.builder().nome("Teste").descricao("desc teste").build();

        //Resposta que será mock
        Cargo cargo = Cargo.builder().id_cargo(11).nome("Teste").descricao("desc teste").build();

        //mock salvar
        Mockito.when(service.salvar(Mockito.any(Cargo.class))).thenReturn(cargo);

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
    
    /* 
    @Test
    public void deveDeletarCargo() throws Exception {
        Cargo cargo = Cargo.builder().id_cargo(1).nome("Teste").descricao("desc teste").build();

        Mockito.when(service.remover(Mockito.anyInt())).thenReturn(cargo);

        MockHttpServletRequestBuilder request = 
                                            MockMvcRequestBuilders
                                            .delete(API.concat("/deletar/1"))
                                            .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
    */

    @Test
    public void deveObterCargo() throws Exception{
        Integer id  = 1;
        Cargo busaca = Cargo.builder().id_cargo(1).build();
        Cargo cargo = Cargo.builder().id_cargo(1).nome("Teste").descricao("desc teste").build();
        List<Cargo> resposta = Arrays.asList(cargo);

        Mockito.when(service.buscar(Mockito.any(Cargo.class))).thenReturn(resposta);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                                                .get(API.concat("/obter/1"))
                                                .accept(MediaType.APPLICATION_JSON);
        
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Teste"));

    }

    @Test
    public void deveEditarCargo() throws Exception{
        Integer id = 1;
        CargoDto dto = CargoDto.builder().nome("Atualiza").descricao("desc att").build();
        Cargo cargo = Cargo.builder().id_cargo(1).nome("teste").descricao("desc tste").build();
        
        Mockito.when(service.editar(Mockito.any(Cargo.class))).thenReturn(cargo);

        String json = new ObjectMapper().writeValueAsString(dto);
        
        MockHttpServletRequestBuilder request = 
        MockMvcRequestBuilders.post(API.concat("/editar/1"))
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
        
    }

    @Test
    public void deveObterCargoporEgresso() throws Exception {
        Integer id = 1;
        Cargo cargo1 = Cargo.builder().nome("teste1").build();
        Cargo cargo2 = Cargo.builder().nome("Teste2").build();

        List<Cargo> retorno = Arrays.asList(cargo1, cargo2);

        Mockito.when(service.findByEgresso(Mockito.anyInt())).thenReturn(retorno);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                                                .get(API.concat("/obter-por-egresso/1"))
                                                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveContarEgressosporCargo() throws Exception {
        Integer count = 2;

        Mockito.when(service.countEgressosByCargo(Mockito.anyInt())).thenReturn(count);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
        .get(API.concat("/contar-por-egresso/1"))
        .accept(MediaType.ALL_VALUE);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());


    }

}
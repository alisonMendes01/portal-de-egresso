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
import br.ufma.portal.model.FaixaSalario;
import br.ufma.portal.model.dto.FaixaSalarioDto;
import br.ufma.portal.service.FaixaSalarioService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = CargoController.class)
@AutoConfigureMockMvc
public class FaixaSalarioControllerTeste {

    static final String API = "/api/faixasalario";

    @Autowired
    MockMvc mvc;

    @MockBean
    FaixaSalarioService service;


    @Test
    public void deveSalvar() throws Exception{
        //Cenário
        //Dto para virar json
        FaixaSalarioDto dto = FaixaSalarioDto.builder().descricao("faixa salario").build();


        //Resposta que será mock
        FaixaSalario faixa = FaixaSalario.builder().id_faixa_salario(1).descricao("faixa salario").build();

        //mock salvar
        Mockito.when(service.salvar(Mockito.any(FaixaSalario.class))).thenReturn(faixa);

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
        FaixaSalario faixa = FaixaSalario.builder().id_faixa_salario(1).descricao("fa").build();

        Mockito.when(service.remover(Mockito.anyInt())).thenReturn(faixa);

        MockHttpServletRequestBuilder request = 
                                            MockMvcRequestBuilders
                                            .delete(API.concat("/deletar/1"))
                                            .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deveEditar() throws Exception{
        Integer id = 1;
        FaixaSalarioDto dto = FaixaSalarioDto.builder().descricao("abc").build();
        FaixaSalario faixa = FaixaSalario.builder().id_faixa_salario(1).descricao("abc").build();        
        Mockito.when(service.editar(Mockito.any(FaixaSalario.class))).thenReturn(faixa);

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
    public void deveObter() throws Exception{
        FaixaSalario faixa = FaixaSalario.builder().id_faixa_salario(1).build();

        List<FaixaSalario> resposta = Arrays.asList(faixa);

        Mockito.when(service.buscar(Mockito.any(FaixaSalario.class))).thenReturn(resposta);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                                                .get(API.concat("/obter/1"))
                                                .accept(MediaType.APPLICATION_JSON);
        
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test 
    public void deveContaPorFaixadeSalario()  throws Exception{
        Integer id = 1;
        Mockito.when(service.countEgressosByFaixaSalario(Mockito.anyInt())).thenReturn(id);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                                                .get(API.concat("/obter-por-egresso/1"))
                                                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }
}

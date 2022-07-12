package br.ufma.portal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EgressoDto {

    private Integer id;
    private String nome;
    private String email;
    private String cpf;
    private String resumo;
    private String url_foto;
    
}

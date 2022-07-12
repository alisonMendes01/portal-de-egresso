package br.ufma.portal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FaixaSalarioDto {
    private Integer id;
    private String descricao;
}    
    

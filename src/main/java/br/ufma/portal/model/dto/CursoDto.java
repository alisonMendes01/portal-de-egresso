package br.ufma.portal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CursoDto {
    private Integer id;
    private String nome;
    private String nivel;
}

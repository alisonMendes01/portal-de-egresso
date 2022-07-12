package br.ufma.portal.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DepoimentoDto {
    private Integer id;
    private String texto;
    private LocalDate data;
}

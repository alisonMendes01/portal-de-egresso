package br.ufma.portal.entidade.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EgressoDepoimentoDto {
    private Integer id;
    private String texto;
    private LocalDate data;
}
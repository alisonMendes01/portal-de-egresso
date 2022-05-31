package br.ufma.portal.service;

import java.util.Comparator;

import br.ufma.portal.entidade.Depoimento;

public class DepoimentoComparator implements Comparator<Depoimento>{
    @Override
    public int compare(Depoimento a, Depoimento b){
        return a.getData().compareTo(b.getData());
    }
}

package com.br.votacao.service;

import com.br.votacao.domain.Associado;import com.br.votacao.domain.Votacao;

import java.util.List;
import java.util.Optional;

public interface VotaocaoService {
    public Votacao salvar(Votacao votacao) throws Exception;

    public Optional<Votacao> buscarPorAssociado(Associado associado);

    public List<Votacao> findAll();
}


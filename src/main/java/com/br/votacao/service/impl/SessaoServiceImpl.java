package com.br.votacao.service.impl;


import com.br.votacao.domain.Sessao;
import com.br.votacao.repository.SessaoRepository;
import com.br.votacao.service.SessaoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessaoServiceImpl implements SessaoService {

    private static final Logger log = LoggerFactory.getLogger(SessaoServiceImpl.class);

    private final SessaoRepository repository;


    @Override
    public Sessao salvar(Sessao sessao) {
        return repository.save(sessao);
    }

    @Override
    public Optional<Sessao> findById(String id) {
        log.info("Buscando pelo id: {}", id);
        return this.repository.findById(id);
    }

    @Override
    public void deletarSessao(Sessao sessao) {
        log.info("deletando sessao: {}", sessao.getId());
        this.repository.delete(sessao);
    }

    @Override
    public List<Sessao> findAll() {
        return this.repository.findAll();
    }
}

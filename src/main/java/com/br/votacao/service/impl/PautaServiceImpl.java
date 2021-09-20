package com.br.votacao.service.impl;

import com.br.votacao.domain.Pauta;
import com.br.votacao.repository.PautaRepository;
import com.br.votacao.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {

    private static final Logger log = LoggerFactory.getLogger(PautaServiceImpl.class);

    private final PautaRepository repository;

    @Override
    public Pauta salvar(Pauta pauta) {
        log.info("Salvando a pauta: {}", pauta.getNome());
        return repository.save(pauta);
    }

    @Override
    public Optional<Pauta> findById(String id) {
        log.info("Busvando a pauta pelo nome: {}", id);
        return this.repository.findById(id);
    }

    @Override
    public Optional<Pauta> buscarPorNome(String nome) {
        log.info("Busvando a pauta pelo nome: {}", nome);
        return Optional.ofNullable(this.repository.findByNome(nome));
    }

    public void deletarPauta(Pauta pauta) {
        log.info("deletar: {}", pauta.getNome());
        repository.delete(pauta);
    }

    public boolean validarPauta(Pauta pauta) {
        boolean isPautaValida = true;
        if(buscarPorNome(pauta.getNome()).isPresent()) {
            isPautaValida = false;
        }
        return isPautaValida;
    }
}

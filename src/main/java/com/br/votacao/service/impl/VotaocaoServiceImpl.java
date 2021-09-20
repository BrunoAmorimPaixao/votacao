package com.br.votacao.service.impl;

import com.br.votacao.domain.Associado;
import com.br.votacao.domain.Pauta;
import com.br.votacao.domain.Sessao;
import com.br.votacao.domain.Votacao;
import com.br.votacao.repository.AssociadoRepository;
import com.br.votacao.repository.VotacaoRepository;
import com.br.votacao.service.VotaocaoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VotaocaoServiceImpl implements VotaocaoService {

    private static final Logger log = LoggerFactory.getLogger(VotaocaoServiceImpl.class);

    private final VotacaoRepository repository;

    private final AssociadoRepository associadoRepository;

    @Override
    public Votacao salvar(Votacao votacao) throws Exception {
        log.info("Salvando votacao: {}", votacao.toString());
        if (validarAssociadoJaVotou(votacao)) {
            return repository.save(votacao);
        } else {
            throw new Exception("Associoado ".concat(votacao.getAssociado().getCpf().concat(" já votou!")) );
        }
    }

    public boolean validarAssociadoJaVotou(Votacao votacao) {
        boolean isValidarAssociadoJaVotou = true;
        Optional<Votacao> voto = buscarPorAssociado(votacao.getAssociado());
       if(voto.isPresent()) {
           if(voto.get().isVoto()){
               isValidarAssociadoJaVotou = false;
           }
       }
        return isValidarAssociadoJaVotou;
    }

   @Override
    public Optional<Votacao> buscarPorAssociado(Associado associado) {
        log.info("Buscando pelo CPF: {}", associado);
        return Optional.ofNullable(this.repository.findByAssociado(associado));
    }

    @Override
    public List<Votacao> findAll() {
        return repository.findAll();
    }

}

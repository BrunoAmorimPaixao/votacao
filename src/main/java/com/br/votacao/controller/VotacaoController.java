package com.br.votacao.controller;

import com.br.votacao.domain.Associado;
import com.br.votacao.domain.Sessao;
import com.br.votacao.domain.Votacao;
import com.br.votacao.service.AssociadoService;
import com.br.votacao.service.PautaService;
import com.br.votacao.service.SessaoService;
import com.br.votacao.service.VotaocaoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/votacao")
@CrossOrigin(origins = "*") // requisao de qq dominio
@RequiredArgsConstructor
public class VotacaoController {

    private static final Logger log = LoggerFactory.getLogger(VotacaoController.class);

    private final VotaocaoService service;

    private final SessaoService sessaoService;

    private final AssociadoService associadoService;

    private final PautaService pautaService;

    private final ModelMapper modelMapper;

    private Optional<Associado> associado;

    private Optional<Sessao> sessao;

    private Votacao votacao;

    private Map<String,String> msg = new HashMap<String, String>();

    @PutMapping("{sessaoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String,String>> votacao(@PathVariable String sessaoId,
                                           @RequestParam String cpf,
                                           @RequestParam String voto) throws Exception {

        try {
            votacao = new Votacao();

            if (validarSessao(sessaoId) && validarCpf(cpf) && validarVoto(voto) ) {

                log.info("Iniciar votacao para sessao: {}", sessaoId);
                if(sessao.isPresent()) {
                    int vt =0;
                    if("sim".equals(voto)) {
                         vt = sessao.get().getPauta().getTotalAfavor() + 1;
                        sessao.get().getPauta().setTotalAfavor(vt);
                    } else {
                        vt = sessao.get().getPauta().getTotalContra() + 1;
                        sessao.get().getPauta().setTotalContra(vt);
                    }
                }
                //incrementar o id de votacao
                int idVotacao = service.findAll().size() + 1;

                votacao.setId(Integer.toString(idVotacao));
                votacao.setSessao(sessao.get());
                boolean isVoto = voto.equals("sim") ? true : false;
                votacao.setVoto(isVoto);
                votacao = service.salvar(votacao);
                pautaService.salvar(votacao.getSessao().getPauta());

                msg.put("ok", "Voto Salvo!");
                return ResponseEntity.badRequest().body(msg);
                //return ResponseEntity.ok(votacao);
            }else {
                return ResponseEntity.badRequest().body(msg);
            }

        } catch (Exception e) {
            msg.put("ATENCAO", "Associado já votou!");
            return ResponseEntity.badRequest().body(msg);
        }
    }

    public boolean validarSessao(String id) {
        boolean validar = true;
        sessao = sessaoService.findById(id);
        if(!sessao.isPresent()) {
            msg.put("ATENCAO", "Nao existe essa sessão cadastrada!");
            validar = false;
        }
        return validar;
    }

    public boolean validarCpf(String cpf) {
        boolean validar = true;
        associado = associadoService.findByCpf(cpf);
        if(associado.isPresent()) {
            votacao.setAssociado(associado.get());
        } else {
            msg.put("ATENCAO", "Nao existe esse associado cadastrado!");
            validar = false;
        }
        return validar;
    }

    public boolean validarVoto(String voto) {
        boolean validar = false;
        if("sim".equals(voto.toLowerCase()) ||"nao".equals(voto.toLowerCase()) ) {
            validar = true;
        } else{
            msg.put("ATENCAO", "Erro no dados do voto!");
        }
        return validar;
    }



}

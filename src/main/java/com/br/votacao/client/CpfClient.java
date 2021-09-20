package com.br.votacao.client;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CpfClient {

    String cpf;

    //TODO: URL só retorno um valor mesmo passando o cpf certo (UNABLE_TO_VOTE)
    public String buscaCpfId(String doc){
        RestTemplate template = new RestTemplate();
        cpf = template.getForObject("https://user-info.herokuapp.com/users/".concat(doc), String.class);
        return cpf ;
    }
}

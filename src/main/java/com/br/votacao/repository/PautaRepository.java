package com.br.votacao.repository;

import com.br.votacao.domain.Pauta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends MongoRepository<Pauta, String> {

    Pauta findByNome(String nome);

}

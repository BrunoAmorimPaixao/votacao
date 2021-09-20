package com.br.votacao.repository;

import com.br.votacao.domain.DatabaseSequence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceGeneratorRepository extends MongoRepository<DatabaseSequence, Long> {
}

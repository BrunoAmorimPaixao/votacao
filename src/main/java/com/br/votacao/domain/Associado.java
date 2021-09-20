package com.br.votacao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "associado")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Associado {

    @Id
    private String id;
    private String cpf;

}

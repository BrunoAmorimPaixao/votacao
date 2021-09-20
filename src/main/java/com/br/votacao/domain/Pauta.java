package com.br.votacao.domain;

import com.br.votacao.dto.PautaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;

@Document(collection = "pauta")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pauta implements Serializable {

    @Id
    private String id;

    @Indexed(unique = true)
    private String nome;

    private int totalAfavor;

    private int totalContra;

    public Pauta(PautaDTO pautaDTO) {
        this.id = pautaDTO.getId();
        this.nome = pautaDTO.getNome();
    }

    public Pauta(Long id, String nome) {
    }
}

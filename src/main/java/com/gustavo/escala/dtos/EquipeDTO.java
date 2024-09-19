package com.gustavo.escala.dtos;

import com.gustavo.escala.domain.Equipe;
import com.gustavo.escala.domain.Escala;
import com.gustavo.escala.domain.Pessoa;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class EquipeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotNull(message = "O campo NOME é requerido.")
    private String nome;
    private Set<Integer> membrosIds = new HashSet<>();
    private Set<Integer> escalasIds = new HashSet<>();

    public EquipeDTO() {
        super();
    }

    public EquipeDTO(Equipe obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.membrosIds = new HashSet<>();
        this.escalasIds = new HashSet<>();

        for (Pessoa membro : obj.getMembros()) {
            this.membrosIds.add(membro.getId());
        }

        for (Escala escala : obj.getEscalas()) {
            this.escalasIds.add(escala.getId());
        }
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Integer> getEscalasIds() {
        return escalasIds;
    }

    public void setEscalasIds(Set<Integer> escalasIds) {
        this.escalasIds = escalasIds;
    }

    public Set<Integer> getMembrosIds() {
        return membrosIds;
    }

    public void setMembrosIds(Set<Integer> membrosIds) {
        this.membrosIds = membrosIds;
    }
}

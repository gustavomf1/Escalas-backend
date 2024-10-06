package com.gustavo.escala.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gustavo.escala.domain.Escala;
import com.gustavo.escala.domain.Pessoa;
import jakarta.persistence.Index;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EscalaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @NotNull(message = "O campo EQUIPE requerido")
    private Integer equipe;
    private String nomeEquipe;
    @NotNull(message = "O campo TITULO requerido")
    private String titulo;
    @NotNull(message = "O campo DESCRIÇÃO requerido")
    private String descricao;

    private Set<Integer> pessoasExtras = new HashSet<>();

    public EscalaDTO() {
        super();
    }

    public EscalaDTO(Escala obj) {
        this.id = obj.getId();
        this.data = obj.getData();
        this.equipe = obj.getEquipe().getId();
        this.nomeEquipe = obj.getEquipe().getNome();
        this.titulo = obj.getTitulo();
        this.descricao = obj.getDescricao();
        this.pessoasExtras = obj.getPessoasExtras().stream().map(x -> x.getId()).collect(Collectors.toSet());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getEquipe() {
        return equipe;
    }

    public void setEquipe(Integer equipe) {
        this.equipe = equipe;
    }

    public String getNomeEquipe() {
        return nomeEquipe;
    }

    public void setNomeEquipe(String nomeEquipe) {
        this.nomeEquipe = nomeEquipe;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Integer> getPessoasExtras() {
        return pessoasExtras;
    }

    public void setPessoasExtras(Set<Integer> pessoasExtras) {
        this.pessoasExtras = pessoasExtras;
    }
}

package com.gustavo.escala.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gustavo.escala.domain.Escala;
import com.gustavo.escala.domain.Pessoa;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EscalaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate data;

    @NotNull(message = "O campo EQUIPE é requerido")
    private Integer equipe;
    private String nomeEquipe;

    @NotNull(message = "O campo TITULO é requerido")
    private String titulo;

    @NotNull(message = "O campo DESCRIÇÃO é requerido")
    private String descricao;

    private Set<Integer> pessoasExtrasIds = new HashSet<>();
    private Set<String> pessoasExtrasNomes = new HashSet<>();

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
        this.pessoasExtrasIds = obj.getPessoasExtras().stream().map(Pessoa::getId).collect(Collectors.toSet());
        this.pessoasExtrasNomes = obj.getPessoasExtras().stream().map(Pessoa::getNome).collect(Collectors.toSet());
    }

    // Getters e Setters...
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

    public Set<Integer> getPessoasExtrasIds() {
        return pessoasExtrasIds;
    }

    public void setPessoasExtrasIds(Set<Integer> pessoasExtrasIds) {
        this.pessoasExtrasIds = pessoasExtrasIds;
    }

    public Set<String> getPessoasExtrasNomes() {
        return pessoasExtrasNomes;
    }

    public void setPessoasExtrasNomes(Set<String> pessoasExtrasNomes) {
        this.pessoasExtrasNomes = pessoasExtrasNomes;
    }
}

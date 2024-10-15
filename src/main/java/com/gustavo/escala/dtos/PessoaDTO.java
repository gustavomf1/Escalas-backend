package com.gustavo.escala.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gustavo.escala.domain.Equipe;
import com.gustavo.escala.domain.Escala;
import com.gustavo.escala.domain.Pessoa;
import com.gustavo.escala.domain.enums.Perfil;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PessoaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "O campo CPF é requerido")
    private String cpf;
    private Integer idEquipe;
    @NotNull(message = "O campo NOME é requerido")
    private String nome;
    @NotNull(message = "O campo EMAIL é requerido")
    private String email;
    @NotNull(message = "O campo SENHA é requerido")
    private String senha;

    private Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    @JsonIgnore
    private Set<Escala> escalasExtras = new HashSet<>();

    public PessoaDTO(){
        super();
        addPerfis(Perfil.FUNCIONARIO);
    }

    public PessoaDTO(Pessoa obj) {
        this.id = obj.getId();
        this.cpf = obj.getCpf();
        this.idEquipe = obj.getEquipe().getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.escalasExtras = obj.getEscalasExtras();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis(){
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfis(Perfil perfil){ this.perfis.add(perfil.getCodigo()); }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Set<Escala> getEscalasExtras() {
        return escalasExtras;
    }

    public void setEscalasExtras(Set<Escala> escalasExtras) {
        this.escalasExtras = escalasExtras;
    }

    public Integer getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Integer idEquipe) {
        this.idEquipe = idEquipe;
    }
}

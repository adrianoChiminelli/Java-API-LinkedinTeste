package com.linkedin.apilinkedin.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Usuario  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNacimento;
    private String titulo;
    private String localizacao;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Telefone> telefones;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Post> posts;

    public Usuario(String nome, Date dataNacimento, String titulo, String localizacao, List<Telefone> telefones, List<Post> posts) {
        this.nome = nome;
        this.dataNacimento = dataNacimento;
        this.titulo = titulo;
        this.localizacao = localizacao;
        this.telefones = telefones;
        this.posts = posts;
    }
}

package model;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class Usuario {

    public String nome;
    public Unidade setor;
    private String matricula;
    private String senha;

    /*public Usuario(String nome, Unidade setor, String matricula, String senha) {
        this.nome = nome;
        this.setor = setor;
        this.matricula = matricula;
        this.senha = senha;
    }*/

    public Usuario() {

    }

    public String getNome() {
        return nome;
    }

    public String setNome(String nome) {
        this.nome = nome;
        return nome;
    }

    public Unidade getSetor() {
        return setor;
    }

    public Unidade setSetor(Unidade setor) {
        this.setor = setor;
        return setor;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getSenha() {
        return senha;
    }

    public String setSenha(String senha) {
        this.senha = senha;
        return senha;
    }

    public String setMatricula(String matricula) {
        this.matricula = matricula;
        return matricula;
    }
}

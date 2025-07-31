package br.com.alura.screammatch.Model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Optional;

public class Serie {
    private String titulo;
    private String totalTemporadas;
    private Double avaliacao;
    private Categoria genero;
    private String atores;
    private String poster;
    private String sinopse;

    public Serie(DadosSerie dadosSerie) {
        this.titulo = dadosSerie.titulo();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        String avaliacaoStr = dadosSerie.avaliacao();
        this.avaliacao = Optional.ofNullable(avaliacaoStr)
                .filter(s -> !s.isBlank())
                .map(Double::valueOf)
                .orElse(0.0);
        String generoRaw = dadosSerie.genero();
        String generoPrimeiro = (generoRaw != null && !generoRaw.isBlank())
                ? generoRaw.split(",")[0].trim()
                : null;

        this.genero = Categoria.fromString(generoPrimeiro)
                .orElse(Categoria.DEFAUT); // ou null, ou lançar exceção, como preferir
        this.atores = dadosSerie.atores();
        this.poster = dadosSerie.poster();
        this.sinopse = dadosSerie.sinopse();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(String totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    @Override
    public String toString() {
        return  "titulo='" + titulo + '\'' +
                ", totalTemporadas='" + totalTemporadas + '\'' +
                ", avaliacao=" + avaliacao +
                ", genero=" + genero +
                ", atores='" + atores + '\'' +
                ", poster='" + poster + '\'' +
                ", sinopse='" + sinopse + '\'';
    }
}

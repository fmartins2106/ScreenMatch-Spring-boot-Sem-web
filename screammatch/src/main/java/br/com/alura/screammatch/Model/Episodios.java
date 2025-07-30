package br.com.alura.screammatch.Model;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Episodios {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double avaliacao;
    private LocalDate dataLancamento;

    public Episodios(Integer numeroTemporada, DadosEpisodeos dadosEpisodeos) {
        this.temporada = numeroTemporada;
        this.titulo = dadosEpisodeos.Titulo();
        this.numeroEpisodio = dadosEpisodeos.numero();
        try {
            this.avaliacao = Double.valueOf(dadosEpisodeos.avaliacao());
        }catch (NumberFormatException e){
            this.avaliacao = 0.00;
        }
        try {
            this.dataLancamento = LocalDate.parse(dadosEpisodeos.dataLancamento());
        }catch (DateTimeException e){
            this.dataLancamento = null;
        }
    }


    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }


    @Override
    public String toString() {
        return  "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", avaliacao=" + avaliacao +
                ", dataLancamento=" + dataLancamento;
    }
}

package br.com.alura.screammatch.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodeos(@JsonAlias("Title") String Titulo,
                             @JsonAlias("Released") String dataLancamento,
                             @JsonAlias("totalSeasons") String totalTemporadas,
                             @JsonAlias("imdbRating") String avaliacao,
                             @JsonAlias("imdbID") String imdbId){
}

package br.com.alura.screammatch.principal;

import br.com.alura.screammatch.Model.*;
import br.com.alura.screammatch.service.ConsumoAPI;
import br.com.alura.screammatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private static final Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=257b8762";


    public void exibirMenu(){
        System.out.println("Digite o nome da série:");
        var nomeSerie = scanner.nextLine();
        var json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);

        if (json.contains("\"Response\":\"False\"")) {
            System.out.println("Série não encontrada.");
            return;
        }

        DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
        int total = Conversores.parseTotalSeasons(dados.totalTemporadas());

        List<DadosTemporada> temporadas = new ArrayList<>();

        System.out.println("Título: " + dados.titulo());
        System.out.println("Ano: " + dados.ano());
        System.out.println("Total de Temporadas: " + total);

        for (int i = 1; i <= total; i++) {
            json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ","+")+"&Season="+ i +API_KEY);
            DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
//        temporadas.forEach(System.out::println);
//
//        temporadas.forEach(t -> t.episodeos().forEach(e -> System.out.println(e.Titulo())));

        List<DadosEpisodeos> dadosEpisodeos = temporadas.stream()
                .flatMap(temporada -> temporada.episodeos().stream())
                        .collect(Collectors.toList());

        dadosEpisodeos.stream().sorted(Comparator.comparing(DadosEpisodeos::avaliacao).reversed())
                .filter(dadosEpisodeos1 -> !"N/A".equals(dadosEpisodeos1.avaliacao()))
                .limit(5)
                .forEach(System.out::println);

        List<Episodios> episodios = temporadas.stream()
                .flatMap(t -> t.episodeos().stream()
                        .map(d -> new Episodios(t.numero(),d)))
                .collect(Collectors.toList());

        episodios.forEach(System.out::println);

    }
}

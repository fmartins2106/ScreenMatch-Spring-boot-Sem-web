package br.com.alura.screammatch.principal;

import br.com.alura.screammatch.Model.*;
import br.com.alura.screammatch.service.ConsumoAPI;
import br.com.alura.screammatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


    public void exibeMenu() {
        var menu = """
                1 - Buscar séries
                2 - Buscar episódios
                0 - Sair """;

        System.out.println(menu);
        var opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                buscarSerieWeb();
                break;
            case 2:
                buscarEpisodioPorSerie();
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida");
        }
    }
    
    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = scanner.nextLine();
        var json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
        return dados;
    }


    private void buscarEpisodioPorSerie() {
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporada> temporadas = new ArrayList<>();
        int total = Conversores.parseTotalSeasons(dadosSerie.totalTemporadas());
        for (int i = 1; i <= total; i++) {
            var json = consumoAPI.obterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }


//    public void getDadosSerie(){
//        System.out.println("Digite o nome da série:");
//        var nomeSerie = scanner.nextLine();
//        var json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
//
//        if (json.contains("\"Response\":\"False\"")) {
//            System.out.println("Série não encontrada.");
//            return;
//        }
//
//        DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
//        int total = Conversores.parseTotalSeasons(dados.totalTemporadas());
//
//        List<DadosTemporada> temporadas = new ArrayList<>();
//
//        System.out.println("Título: " + dados.titulo());
//        System.out.println("Ano: " + dados.ano());
//        System.out.println("Total de Temporadas: " + total);
//
//        for (int i = 1; i <= total; i++) {
//            json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ","+")+"&Season="+ i +API_KEY);
//            DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
//            temporadas.add(dadosTemporada);
//        }
/// /        temporadas.forEach(System.out::println);
/// /
/// /        temporadas.forEach(t -> t.episodeos().forEach(e -> System.out.println(e.Titulo())));
//
//        List<DadosEpisodeos> dadosEpisodeos = temporadas.stream()
//                .flatMap(temporada -> temporada.episodeos().stream())
//                        .collect(Collectors.toList());
//
//        dadosEpisodeos.stream().sorted(Comparator.comparing(DadosEpisodeos::avaliacao).reversed())
//                .filter(dadosEpisodeos1 -> !"N/A".equals(dadosEpisodeos1.avaliacao()))
//                .peek(e -> System.out.println("Primeiro filtro (N/A) " + e))
//                .sorted(Comparator.comparing(DadosEpisodeos::avaliacao).reversed())
//                .peek( e-> System.out.println("Ordenação "+e))
//                .limit(10)
//                .map(e -> e.Titulo().toUpperCase())
//                .peek(e -> System.out.println("Mapeamento "+e))
//                .forEach(System.out::println);
//
//        List<Episodios> episodios = temporadas.stream()
//                .flatMap(t -> t.episodeos().stream()
//                        .map(d -> new Episodios(t.numero(),d)))
//                .collect(Collectors.toList());
//
//        episodios.forEach(System.out::println);
//
//        System.out.println("A partir de qual ano você deseja ver o episódio?");
//        var ano = scanner.nextInt();
//        scanner.nextLine();
//
//        LocalDate localDate = LocalDate.of(ano,1,1);
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodios.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(localDate))
//                .forEach(episodios1 -> System.out.println(
//                        "Temporada: "+ episodios1.getTemporada()+
//                                "| Episodio "+episodios1.getTitulo()+
//                                "| Data Lançamento: "+episodios1.getDataLancamento().format(dateTimeFormatter)
//                ));
//    }
}

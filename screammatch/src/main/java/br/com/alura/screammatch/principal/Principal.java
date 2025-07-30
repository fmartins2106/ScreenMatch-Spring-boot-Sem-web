package br.com.alura.screammatch.principal;

import br.com.alura.screammatch.service.ConsumoAPI;

import java.util.Scanner;

public class Principal {
    private static final Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();


    private final String ENDERECO = "http://www.omdbapi.com/?i=";
    private final String API_KEY = "&apikey=257b8762";

    public void exibirMenu(){
        System.out.println("Digite o nome da série:");
        var nomeSerie = scanner.nextLine();
        var json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ","+"));
    }
}

package br.com.alura.screammatch;

import br.com.alura.screammatch.Model.Conversores;
import br.com.alura.screammatch.Model.DadosEpisodeos;
import br.com.alura.screammatch.Model.DadosSerie;
import br.com.alura.screammatch.Model.DadosTemporada;
import br.com.alura.screammatch.service.ConsumoAPI;
import br.com.alura.screammatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obterDados("http://www.omdbapi.com/?i=tt8245288&apikey=257b8762");
        System.out.println(json);
        ConverteDados converteDados = new ConverteDados();
		DadosSerie dados = converteDados.obterDados(json,DadosSerie.class);
        System.out.println(dados);

		json = consumoAPI.obterDados("http://www.omdbapi.com/?i=tt0108778&apikey=257b8762");
		DadosEpisodeos dadosEpisodeos = converteDados.obterDados(json,DadosEpisodeos.class);
		System.out.println(dadosEpisodeos);

		List<DadosTemporada> temporadas = new ArrayList<>();
		int total = Conversores.parseTotalSeasons(dadosEpisodeos.totalTemporadas());

		for (int i = 1; i <= total; i++) {
			json = consumoAPI.obterDados("http://www.omdbapi.com/?i=tt0108778&Season=" + i + "&apikey=257b8762");
			DadosTemporada dadosTemporada = converteDados.obterDados(json,DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);

	}
}

package br.com.alura.screammatch;

import br.com.alura.screammatch.Model.DadosSerie;
import br.com.alura.screammatch.service.ConsumoAPI;
import br.com.alura.screammatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obterDados("http://www.omdbapi.com/?i=tt0106062&apikey=257b8762");
        System.out.println(json);
        ConverteDados converteDados = new ConverteDados();
        DadosSerie dados = converteDados.obterDados(json,DadosSerie.class);
        System.out.println(dados);
	}
}

package br.com.alura.screammatch.Model;

import java.util.Arrays;
import java.util.Optional;

public enum Categoria {
    ACAO("Action"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    POLICIAL("Crime"),
    TERROR("Horror"),
    SUSPENSE("Thriller"),
    DEFAUT("");

    private final String categoriaOmdb;

    Categoria(String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
    }

    public String getCategoriaOmdb() {
        return categoriaOmdb;
    }

    //    public static Categoria fromString(String text) {
//        for (Categoria categoria : Categoria.values()) {
//            if (categoria.getCategoriaOmdb().equalsIgnoreCase(text)) {
//                return categoria;
//            }
//        }
//        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
//    }
    public static Optional<Categoria> fromString(String text) {
        if (text == null || text.isBlank()) {
            return Optional.empty();
        }
        return Arrays.stream(Categoria.values())
                .filter(c -> c.getCategoriaOmdb().equalsIgnoreCase(text.trim()))
                .findFirst();
    }

}

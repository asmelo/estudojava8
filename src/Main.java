import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class Main {

    public static void main(String[] args) {

        Pergunta pp1 = new Pergunta("Qual o melhor framework Web", "Paulo",
                Arrays.asList(new Resposta("Play Framework"),
                        new Resposta("VRaptor"),
                        new Resposta("JSF")));

        Pergunta pp2 = new Pergunta("Como ornedar com Java", "Paulo",
                Arrays.asList(new Resposta("Usando o comparator")));

        Pergunta pp3 = new Pergunta("Vai ter mais coffe break", "Rodrigo",
                Arrays.asList(new Resposta("Sim"),
                        new Resposta("Certamente")));

        List<Pergunta> perguntas = Arrays.asList(pp1, pp2, pp3);

        //ordenar perguntas

        //Java 7
        /*Collections.sort(perguntas, new Comparator<Pergunta>() {
            public int compare(Pergunta p1, Pergunta p2) {
                if (p1.getRespostas().size() < p2.getRespostas().size())
                    return 1;
                if (p1.getRespostas().size() > p2.getRespostas().size())
                    return -1;
                return 0;
            }
        });*/

        //Java 8

        //Novo método concreto implementado na Interface List

        //Usando classes anônimas
        perguntas.sort(new Comparator<Pergunta>() {
            public int compare(Pergunta p1, Pergunta p2) {
                if (p1.getRespostas().size() < p2.getRespostas().size())
                    return 1;
                if (p1.getRespostas().size() > p2.getRespostas().size())
                    return -1;
                return 0;
            }
        });

        //Usando lambda - Retira declaração das interfaces funcionais, que possuem apenas um método
        // abstrato, e a declaração do seu método abstrato
        perguntas.sort((p1, p2) -> {
            if (p1.getRespostas().size() < p2.getRespostas().size())
                return 1;
            if (p1.getRespostas().size() > p2.getRespostas().size())
                return -1;
            return 0;
        });

        //Ou
        perguntas.sort((p1, p2) ->
            Integer.compare(p1.getRespostas().size(), p2.getRespostas().size())
        );

        //Ou
        perguntas.sort(Comparator.comparing(p -> p.getRespostas().size()));

        //Ou
        perguntas.sort(Comparator.comparing(p -> p.getAutor()));

        //Ou
        perguntas.sort(comparing(Pergunta::getAutor));

        Collections.reverse(perguntas);

        System.out.println(perguntas);



        //for

        //Novo método concreto implementado na Interface Iterable

        //Usando classes anônimas
        perguntas.forEach(new Consumer<Pergunta>() {
            public void accept(Pergunta pergunta) {
                System.out.println(pergunta.getTitulo());
            }
        });

        //Usando lambda
        perguntas.forEach(p -> System.out.println(p.getTitulo()));
        //ou
        perguntas.forEach(System.out::println);


        //Threads

        //Usando classe anônimas
        Runnable r1 = new Runnable() {
            public void run() {
                System.out.println("Minha Thread está rodando");
            }
        };
        new Thread(r1).start();

        //Usando lambda
        new Thread(() -> System.out.println("Minha Thread está rodando")).start();


        //Stream
        perguntas.stream()
                .filter(p -> p.getRespostas().size() > 1)
                .map(p -> p.getRespostas().size())
                .forEach(System.out::println);

        perguntas.stream()
                .filter(p -> p.getRespostas().size() > 1)
                .filter(p -> !p.getAutor().isEmpty())
                .mapToInt(p -> p.getRespostas().size())
                .sum();


        perguntas.stream().filter(p -> p.getRespostas().size() > 1)
                .map(p -> p.getRespostas().size())
                .forEach(System.out::println);

        List<Integer> inteiros = perguntas.stream()
                .filter(p -> p.getRespostas().size() > 1)
                .filter(p -> !p.getAutor().isEmpty())
                .map(p -> p.getRespostas().size())
                .collect(Collectors.toList());

        Map<String, List<Pergunta>> map = perguntas.stream()
                .collect(Collectors.groupingBy(Pergunta::getAutor));

        System.out.println(map);


        OptionalDouble media = perguntas.stream().mapToInt(p -> p.getRespostas().size()).average();
        //OptionalDouble media = perguntas.stream().mapToInt(p -> 0).average();

        if(media.isPresent()) {
            System.out.println("Media: " + media.orElse(0.0));
        }

        Set<String> autores = perguntas.stream().collect(Collectors.groupingBy(Pergunta::getAutor)).keySet();
        System.out.println("Autores: " + autores.toString());

    }
}

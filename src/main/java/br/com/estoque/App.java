package br.com.estoque;

import java.util.Scanner;

/**
 * Menu no console para usar o controle de estoque.
 */
public class App {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("=== Controle de Estoque ===");
        System.out.print("Nome do produto: ");
        String nome = teclado.nextLine().trim();
        int inicial = lerNumero(teclado, "Quantidade inicial: ");

        Produto produto;
        try {
            produto = new Produto(nome, inicial);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " Começando com 0.");
            produto = new Produto(nome, 0);
        }

        int opcao = -1;
        while (opcao != 0) {
            System.out.println();
            System.out.println("1 - Registrar entrada");
            System.out.println("2 - Registrar saída");
            System.out.println("3 - Consultar saldo");
            System.out.println("0 - Sair");
            opcao = lerNumero(teclado, "Escolha: ");

            try {
                switch (opcao) {
                    case 1 -> {
                        produto.adicionar(lerNumero(teclado, "Quantas unidades entraram? "));
                        System.out.println("Entrada registrada. Saldo: " + produto.consultarSaldo());
                    }
                    case 2 -> {
                        produto.retirar(lerNumero(teclado, "Quantas unidades saíram? "));
                        System.out.println("Saída registrada. Saldo: " + produto.consultarSaldo());
                    }
                    case 3 -> System.out.println("Saldo de " + produto.getNome() + ": " + produto.consultarSaldo());
                    case 0 -> System.out.println("Até logo!");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Operação recusada: " + e.getMessage());
            }
        }
    }

    /** Lê um número inteiro; se a pessoa digitar outra coisa, pergunta de novo. */
    private static int lerNumero(Scanner teclado, String pergunta) {
        while (true) {
            System.out.print(pergunta);
            String texto = teclado.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro.");
            }
        }
    }
}

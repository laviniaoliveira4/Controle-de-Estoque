package br.com.estoque.bdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.estoque.Produto;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

/**
 * Liga cada frase do arquivo estoque.feature a um código Java.
 */
public class EstoqueSteps {

    private Produto produto;
    private Exception erro;

    @Dado("que o produto {string} tem {int} unidades em estoque")
    public void queOProdutoTemUnidadesEmEstoque(String nome, int quantidade) {
        produto = new Produto(nome, quantidade);
    }

    @Quando("eu adiciono {int} unidades")
    public void euAdicionoUnidades(int unidades) {
        produto.adicionar(unidades);
    }

    @Quando("eu retiro {int} unidades")
    public void euRetiroUnidades(int unidades) {
        produto.retirar(unidades);
    }

    @Quando("eu tento retirar {int} unidades")
    public void euTentoRetirarUnidades(int unidades) {
        try {
            produto.retirar(unidades);
        } catch (IllegalStateException e) {
            erro = e;
        }
    }

    @Então("o saldo do produto deve ser {int}")
    public void oSaldoDoProdutoDeveSer(int saldoEsperado) {
        assertEquals(saldoEsperado, produto.consultarSaldo());
    }

    @Então("a retirada deve ser recusada")
    public void aRetiradaDeveSerRecusada() {
        assertNotNull(erro, "A retirada deveria ter sido recusada, mas foi aceita.");
    }
}
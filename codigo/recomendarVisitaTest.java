import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class recomendarVisitaTest {

    @Test
    public void testRecomendaVisita() {
        // Criando algumas cidades
        Cidade cidadeA = new Cidade("Cidade A", 0);
        Cidade cidadeB = new Cidade("Cidade B", 1);
        Cidade cidadeC = new Cidade("Cidade C", 2);
        Cidade cidadeD = new Cidade("Cidade D", 3);

        // Adicionando estradas entre as cidades
        Estrada estrada1 = new Estrada(cidadeA, cidadeB, 100);
        Estrada estrada2 = new Estrada(cidadeA, cidadeC, 150);
        Estrada estrada3 = new Estrada(cidadeB, cidadeD, 200);
        Estrada estrada4 = new Estrada(cidadeC, cidadeD, 250);

        // Adicionando estradas às cidades
        cidadeA.adicionarEstrada(estrada1);
        cidadeA.adicionarEstrada(estrada2);
        cidadeB.adicionarEstrada(estrada3);
        cidadeC.adicionarEstrada(estrada4);

        // Executando o método de recomendação de visita a partir de uma cidade
        List<Cidade> rotaRecomendada = recomendarVisita.recomendaVisita(cidadeA);

        // Verificando se todas as cidades estão na lista de recomendação
        assertTrue(rotaRecomendada.contains(cidadeA));
        assertTrue(rotaRecomendada.contains(cidadeB));
        assertTrue(rotaRecomendada.contains(cidadeC));
        assertTrue(rotaRecomendada.contains(cidadeD));
    }
}

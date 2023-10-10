import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class RodoviariaInternacionalTest {

    @Test
    public void testRecomendarVisitaTodasCidades() {
        RodoviariaInternacional rodoviaria = new RodoviariaInternacional();

        // Criando algumas cidades
        Cidade cidadeA = new Cidade("Cidade A", 0);
        Cidade cidadeB = new Cidade("Cidade B", 1);
        Cidade cidadeC = new Cidade("Cidade C", 2);
        Cidade cidadeD = new Cidade("Cidade D", 3);

        // Adicionando cidades à rodoviária
        rodoviaria.adicionarCidade(cidadeA);
        rodoviaria.adicionarCidade(cidadeB);
        rodoviaria.adicionarCidade(cidadeC);
        rodoviaria.adicionarCidade(cidadeD);

        // Adicionando estradas entre as cidades
        Estrada estrada1 = new Estrada(cidadeA, cidadeB, 100);
        Estrada estrada2 = new Estrada(cidadeA, cidadeC, 150);
        Estrada estrada3 = new Estrada(cidadeB, cidadeD, 200);
        Estrada estrada4 = new Estrada(cidadeC, cidadeD, 250);

        // Adicionando estradas à rodoviária
        rodoviaria.adicionarEstrada(estrada1);
        rodoviaria.adicionarEstrada(estrada2);
        rodoviaria.adicionarEstrada(estrada3);
        rodoviaria.adicionarEstrada(estrada4);

        // Executando o método de recomendação de visita a todas as cidades
        List<Cidade> rotaRecomendada = rodoviaria.recomendarVisitaTodasCidades();

        // Verificando se todas as cidades estão na lista de recomendação
        assertTrue(rotaRecomendada.contains(cidadeA));
        assertTrue(rotaRecomendada.contains(cidadeB));
        assertTrue(rotaRecomendada.contains(cidadeC));
        assertTrue(rotaRecomendada.contains(cidadeD));
    }
}

import org.junit.Test;
import static org.junit.Assert.*;

public class CidadeTest {

    @Test
    public void testGetNome() {
        Cidade cidade = new Cidade("Cidade de Teste", 1);
        assertEquals("Cidade de Teste", cidade.getNome());
    }

    @Test
    public void testGetId() {
        Cidade cidade = new Cidade("Cidade de Teste", 1);
        assertEquals(1, cidade.getId());
    }

    @Test
    public void testAdicionarEstrada() {
        Cidade cidadeA = new Cidade("Cidade A", 1);
        Cidade cidadeB = new Cidade("Cidade B", 2);
        Estrada estrada = new Estrada(cidadeA, cidadeB, 100);

        cidadeA.adicionarEstrada(estrada);
        assertEquals(1, cidadeA.getEstradas().size());
        assertEquals(estrada, cidadeA.getEstradas().get(0));
    }
}

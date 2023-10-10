import org.junit.Test;
import static org.junit.Assert.*;

public class EstradaTest {

    @Test
    public void testGetOrigem() {
        Cidade cidadeA = new Cidade("Cidade A", 1);
        Cidade cidadeB = new Cidade("Cidade B", 2);
        Estrada estrada = new Estrada(cidadeA, cidadeB, 100);

        assertEquals(cidadeA, estrada.getOrigem());
    }

    @Test
    public void testGetDestino() {
        Cidade cidadeA = new Cidade("Cidade A", 1);
        Cidade cidadeB = new Cidade("Cidade B", 2);
        Estrada estrada = new Estrada(cidadeA, cidadeB, 100);

        assertEquals(cidadeB, estrada.getDestino());
    }

    @Test
    public void testGetPeso() {
        Cidade cidadeA = new Cidade("Cidade A", 1);
        Cidade cidadeB = new Cidade("Cidade B", 2);
        Estrada estrada = new Estrada(cidadeA, cidadeB, 100);

        assertEquals(100, estrada.getPeso());
    }
}

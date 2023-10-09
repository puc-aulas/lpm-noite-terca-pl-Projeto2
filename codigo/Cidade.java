import java.util.ArrayList;
import java.util.List;

public class Cidade {
    private String nome;
    private int id;
    private List<Estrada> estradas;

    public Cidade(String nome, int id) {
        this.nome = nome;
        this.id = id;
        this.estradas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }
    
    public int getId() {
        return id;
    }

    public void adicionarEstrada(Estrada estrada) {
        estradas.add(estrada);
    }

    public List<Estrada> getEstradas() {
        return estradas;
    }
}
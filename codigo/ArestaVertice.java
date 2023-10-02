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

public class Estrada {
    private Cidade origem;
    private Cidade destino;
    private int peso;

    public Estrada(Cidade origem, Cidade destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public Cidade getOrigem() {
        return origem;
    }

    public Cidade getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }
}

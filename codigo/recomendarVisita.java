import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class recomendarVisita {
	static List<Cidade> Cidades = new ArrayList<>();
	
    public static void main(String[] args) {
    	int cdDistancia;
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
    	String linhaAtual,cidadeNome;
    	String[] palavras, inicio, cd;
    	Cidade atual, destino;
    	List <String> conexoes = new ArrayList<>();
    	
    	
    	System.out.println("Repasse o caminho absoluto do arquivo: ");
    	String filename = sc.nextLine();
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
            	linhaAtual = fileScanner.nextLine();
            	palavras = linhaAtual.split(", ");
            	inicio = palavras[0].split(": ");
            	cidadeNome = inicio[0];
            	conexoes.add(inicio[1]);
            	Cidades.add(new Cidade(cidadeNome,Cidades.size() + 1));;
            	for(int i = 1; i < palavras.length; i ++) {
            		conexoes.add(palavras[i]);
            	}
            	conexoes.add("e");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
        int id = 0;
        for (String c : conexoes) {
        	  atual = Cidades.get(id);
        	  if ("e".equals(c)) {
        		  id ++;
        	  }else {
        		// erro 
        		cd = c.split(" (");	
        		// erro
        		destino = encontraDestino(cd[0]);
        		cdDistancia = Integer.parseInt(cd[1].replace(")", ""));
        		atual.adicionarEstrada(new Estrada(atual, destino, cdDistancia));
        	  }
        }
    	// Criando cidades  	
        Cidade cidadeCabo = new Cidade("Cidade do Cabo", 1);
        Cidade cidadeJoanesburgo = new Cidade("Joanesburgo", 2);
        Cidade cidadeParis = new Cidade("Paris", 3);
        Cidade cidadeLondres = new Cidade("Londres", 4);
        Cidade cidadeBerlim = new Cidade("Berlim", 5);
        Cidade cidadeAmsterda = new Cidade("Amsterda", 6);
        Cidade cidadeNairobi = new Cidade("Nairobi", 7);
        Cidade cidadeMumbai = new Cidade("Mumbai", 8);
        Cidade cidadeToquio = new Cidade("Toquio", 9);
        Cidade cidadePequim = new Cidade("Pequim", 10);
        Cidade cidadeBangcoc = new Cidade("Bangcoc", 11);

        // Criando estradas
        cidadeCabo.adicionarEstrada(new Estrada(cidadeCabo, cidadeJoanesburgo, 1270));
        cidadeCabo.adicionarEstrada(new Estrada(cidadeCabo, cidadeParis, 8900));
        cidadeCabo.adicionarEstrada(new Estrada(cidadeCabo, cidadeLondres, 8900));
        cidadeCabo.adicionarEstrada(new Estrada(cidadeCabo, cidadeNairobi, 3900));
        cidadeCabo.adicionarEstrada(new Estrada(cidadeJoanesburgo, cidadeNairobi, 4700));
        cidadeCabo.adicionarEstrada(new Estrada(cidadeJoanesburgo, cidadeBangcoc, 8800));
        cidadeCabo.adicionarEstrada(new Estrada(cidadeJoanesburgo, cidadeMumbai, 6500));
        cidadeCabo.adicionarEstrada(new Estrada(cidadeNairobi, cidadeMumbai, 4300));
        cidadeCabo.adicionarEstrada(new Estrada(cidadeMumbai, cidadePequim, 3700));
        cidadeCabo.adicionarEstrada(new Estrada(cidadeMumbai, cidadeToquio, 6800));
        cidadeCabo.adicionarEstrada(new Estrada(cidadeMumbai, cidadeBangcoc, 4300));
        cidadeCabo.adicionarEstrada(new Estrada(cidadeToquio, cidadePequim, 2400));
        cidadeCabo.adicionarEstrada(new Estrada(cidadePequim, cidadeBangcoc, 2700));
        cidadeCabo.adicionarEstrada(new Estrada(cidadeParis, cidadeBerlim, 1050));
        cidadeCabo.adicionarEstrada(new Estrada(cidadeParis, cidadeAmsterda, 430));
        cidadeCabo.adicionarEstrada(new Estrada(cidadeParis, cidadeLondres, 340));
        cidadeCabo.adicionarEstrada(new Estrada(cidadeBerlim, cidadeAmsterda, 620));
        cidadeCabo.adicionarEstrada(new Estrada(cidadeAmsterda, cidadeLondres, 320));

        // Chamando o método para recomendar a visita
        List<Cidade> rotaRecomendada = recomendaVisita(cidadeCabo);

        // Exibindo a rota recomendada
        System.out.println("Rota Recomendada:");
        for (Cidade cidade : rotaRecomendada) {
            System.out.println(cidade.getNome() + " (ID: " + cidade.getId() + ")");
        }
    }

    public static List<Cidade> recomendaVisita(Cidade cidadeInicial) {
        List<Cidade> rotaRecomendada = new ArrayList<>();
        Set<Cidade> visitadas = new HashSet<>();

        // Chamada à função de busca em profundidade para percorrer todas as cidades
        buscarTodasCidades(cidadeInicial, visitadas, rotaRecomendada);

        return rotaRecomendada;
    }
    private static Cidade encontraDestino(String NomeDestino) {
        int i;
        for (i = 0; i < Cidades.size(); i++) {
            if (NomeDestino.equals(Cidades.get(i).getNome())) {
                break;
            }
        }
        return Cidades.get(i);
    }
    
    private static void buscarTodasCidades(Cidade cidadeAtual, Set<Cidade> visitadas, List<Cidade> rota) {
        visitadas.add(cidadeAtual);
        rota.add(cidadeAtual);

        for (Estrada estrada : cidadeAtual.getEstradas()) {
            Cidade proximaCidade = estrada.getDestino();
            if (!visitadas.contains(proximaCidade)) {
                buscarTodasCidades(proximaCidade, visitadas, rota);
            }
        }
    }
}

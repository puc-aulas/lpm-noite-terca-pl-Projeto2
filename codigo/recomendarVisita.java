import java.util.ArrayList;
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
        		cd = separaTexto(c);	
        		destino = encontraDestino(cd[0]);
        		cdDistancia = Integer.parseInt(cd[1]);
        		atual.adicionarEstrada(new Estrada(atual, destino, cdDistancia));
        	  }
        }
        
        // Chamando o método para recomendar a visita
        List<Cidade> rotaRecomendada = recomendaVisita(Cidades.get(0));

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
    	for (i = 0; i < Cidades.size(); i ++) {
    		String a = Cidades.get(i).getNome();
    		if (NomeDestino.equals(Cidades.get(i).getNome())) {
    			break;    			
    		}
    	}
		return Cidades.get(i);
    }

	private static String[] separaTexto(String txt) {
    	String[] tx= {"",""};
    	for (int i = 0; i< txt.length(); i++) {
    		if(txt.charAt(i) == '(') {
    			tx[0] = txt.substring(0,i-1);
    			tx[1] = txt.substring(i+1);
    			tx[1] = tx[1].replace(")", "");
    			break;
    		}
    	}
    	return tx;
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

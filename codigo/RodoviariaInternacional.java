import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class RodoviariaInternacional {
    private static List<Cidade> Cidades;
    private List<Estrada> estradas;
    
    public RodoviariaInternacional() {
        Cidades = new ArrayList<>();
        estradas = new ArrayList<>();
    }

    public void adicionarCidade(Cidade cidade) {
        Cidades.add(cidade);
    }

    public void adicionarEstrada(Estrada estrada) {
        estradas.add(estrada);
    }

    public List<Cidade> getCidades() {
        return Cidades;
    }

    public List<Estrada> getEstradas() {
        return estradas;
    }
    public void mostrarCidades(){
        System.out.println("Lista de Cidades:");
        for (Cidade c : Cidades) {
        	System.out.println("["+c.getId()+"]"+c.getNome());
        }
    }

    // Método para obter uma recomendação de visitação em todas as Cidades (Requisito a)
    public List<Cidade> recomendarVisitaTodasCidades() {
        // Simplesmente retornar a lista de Cidades
        return Cidades;
    }

    // Método para verificar se existe estrada de qualquer cidade para qualquer cidade (Requisito b)
    public boolean existeEstradaEntreCidades(Cidade origem, Cidade destino) {
        for (Estrada estrada : estradas) {
            if (estrada.getOrigem().equals(origem) && estrada.getDestino().equals(destino)) {
                return true;
            }
        }
        return false;
    }

    // Método para identificar as Cidades que não são acessíveis via transporte terrestre (Requisito c)
    public void CidadesInacessiveis() {
        System.out.println("Cidades inacessíveis via transporte terrestre:");
        for (Cidade cidade : Cidades) {
            boolean acessivel = false;
            for (Cidade outraCidade : Cidades) {
                if (!cidade.equals(outraCidade) && existeEstradaEntreCidades(cidade, outraCidade)) {
                    acessivel = true;
                    break;
                }
            }
            if (!acessivel) {
                System.out.println(cidade.getNome());
            }
        }
    }
    
    private static Cidade encontraDestino(String NomeDestino) {
    	int i;
    	for (i = 0; i < Cidades.size(); i ++) {
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

    // Método para recomendar uma rota para um passageiro (Requisito d)
    public List<Cidade> recomendarRotaPassageiro(Cidade origem) {
        List<Cidade> rota = new ArrayList<>();
        Set<Cidade> visitadas = new HashSet<>();
        rota.add(origem);
        visitadas.add(origem);

        while (visitadas.size() < Cidades.size()) {
            Cidade cidadeAtual = rota.get(rota.size() - 1);
            Cidade cidadeMaisProxima = null;
            int menorDistancia = Integer.MAX_VALUE;

            for (Estrada estrada : cidadeAtual.getEstradas()) {
                Cidade destino = estrada.getDestino();
                if (!visitadas.contains(destino) && estrada.getPeso() < menorDistancia) {
                    cidadeMaisProxima = destino;
                    menorDistancia = estrada.getPeso();
                }
            }

            if (cidadeMaisProxima != null) {
                rota.add(cidadeMaisProxima);
                visitadas.add(cidadeMaisProxima);
            } else {
                break;
            }
        }
        rota.add(origem);
        return rota;
    }

    public static void main(String[] args) {
        RodoviariaInternacional rodoviaria = new RodoviariaInternacional();
    	int cdDistancia;
    	Scanner sc = new Scanner(System.in);
    	String linhaAtual,cidadeNome;
    	String[] palavras, inicio, cd;
    	Cidade atual, destino, c1;
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
            	c1 = new Cidade(cidadeNome,Cidades.size() + 1);
            	Cidades.add(c1);
            	for(int i = 1; i < palavras.length; i ++) {
            		conexoes.add(palavras[i]);
            	}
            	conexoes.add("e");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
        int id = 0;
        Estrada es;
        for (String c : conexoes) {
        	  atual = Cidades.get(id);
        	  if ("e".equals(c)) {
        		  id ++;
        	  }else {
        		cd = separaTexto(c);	
        		destino = encontraDestino(cd[0]);
        		cdDistancia = Integer.parseInt(cd[1]);
        		es = new Estrada(atual, destino, cdDistancia);
        		atual.adicionarEstrada(es);
        		rodoviaria.adicionarEstrada(es);
        	  }
        }
        
        while (true) {
            System.out.print("------------------------------------------------------ \n"
            		+ "Opções: \n"
            		+ "1. Recomendar Visitação em Todas as Cidades \n"
            		+ "2. Verificar Existência de Estrada entre Cidades \n"
            		+ "3. Identificar Cidades Inacessíveis \n"
            		+ "4. Recomendar Rota para Passageiro \n"
            		+ "5. Sair \n"
            		+ "Escolha uma opção: \n"
            		+ "> ");

            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                	System.out.print("------------------------------------------------------ \n");
                    rodoviaria.mostrarCidades();
                   // rodoviaria.recomendarVisitaTodasCidades();
                    break;
                case 2:
                	System.out.print("------------------------------------------------------ \n");
                    int idDestino=0,idOrigem =0;
                    do {
                    	System.out.print("Digite o ID da cidade de origem: ");
                    	idOrigem = sc.nextInt();
                    	idOrigem--;
                    	System.out.print("Digite o ID da cidade de destino: ");
                    	idDestino = sc.nextInt();
                       	idDestino--;
                    } while (idOrigem == idDestino);
                    
                    Cidade origem = rodoviaria.getCidades().get(idOrigem);
                    destino = rodoviaria.getCidades().get(idDestino);
                    System.out.print("\n");
                    if (rodoviaria.existeEstradaEntreCidades(origem, destino)) {
                        System.out.println(">> Existe uma estrada entre as Cidades.");
                    } else {
                        System.out.println(">> Não existe uma estrada entre as Cidades.");
                    }
                    break;
                case 3:
                	System.out.print("------------------------------------------------------ \n");
                    rodoviaria.CidadesInacessiveis();
                    break;
                case 4:
                	System.out.print("------------------------------------------------------ \n");
                    //System.out.print("Digite o ID da cidade de origem para o passageiro: ");
                    int idOrigemPassageiro = 0; // Começa da 1ª cidade
                    Cidade origemPassageiro = rodoviaria.getCidades().get(idOrigemPassageiro);
                    List<Cidade> rotaPassageiro = rodoviaria.recomendarRotaPassageiro(origemPassageiro);

                    System.out.println("-- Recomendação de rota para o passageiro:");
                    for (Cidade cidade : rotaPassageiro) {
                        System.out.println(cidade.getNome());
                    }
                    break;
                case 5:
                	System.out.print("------------------------------------------------------ \n");
                    System.out.println("Saindo da aplicação.");
                    sc.close();
                    System.exit(0);
                default:
                	System.out.print("------------------------------------------------------ \n");
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
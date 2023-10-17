import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class RodoviariaInternacional {
    private List<Cidade> cidades;
    private List<Estrada> estradas;

    public RodoviariaInternacional() {
        cidades = new ArrayList<>();
        estradas = new ArrayList<>();
    }

    public void adicionarCidade(Cidade cidade) {
        cidades.add(cidade);
    }

    public void adicionarEstrada(Estrada estrada) {
        estradas.add(estrada);
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public List<Estrada> getEstradas() {
        return estradas;
    }
    public void mostrarCidades(){
        System.out.println("Lista de cidades:");
        System.out.println(cidades);
    }

    // Método para obter uma recomendação de visitação em todas as cidades (Requisito a)
    public List<Cidade> recomendarVisitaTodasCidades() {
        // Simplesmente retornar a lista de cidades
        return cidades;
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

    // Método para identificar as cidades que não são acessíveis via transporte terrestre (Requisito c)
    public void cidadesInacessiveis() {
        System.out.println("Cidades inacessíveis via transporte terrestre:");
        for (Cidade cidade : cidades) {
            boolean acessivel = false;
            for (Cidade outraCidade : cidades) {
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

    // Método para recomendar uma rota para um passageiro (Requisito d)
    public List<Cidade> recomendarRotaPassageiro(Cidade origem) {
        List<Cidade> rota = new ArrayList<>();
        Set<Cidade> visitadas = new HashSet<>();
        rota.add(origem);
        visitadas.add(origem);

        while (visitadas.size() < cidades.size()) {
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

        return rota;
    }

    public static void main(String[] args) {
        RodoviariaInternacional rodoviaria = new RodoviariaInternacional();

        Cidade cidadeDoCabo = new Cidade("Cidade do Cabo", 0);
        Cidade joanesburgo = new Cidade("Joanesburgo", 1);
        Cidade nairobi = new Cidade("Nairobi", 2);
        Cidade paris = new Cidade("Paris", 3);

        Estrada estrada1 = new Estrada(cidadeDoCabo, joanesburgo, 1270);
        Estrada estrada2 = new Estrada(cidadeDoCabo, nairobi, 3900);
        Estrada estrada3 = new Estrada(joanesburgo, nairobi, 4700);
        Estrada estrada4 = new Estrada(paris, cidadeDoCabo, 9000);

        cidadeDoCabo.adicionarEstrada(estrada1);
        cidadeDoCabo.adicionarEstrada(estrada2);
        joanesburgo.adicionarEstrada(estrada1);
        joanesburgo.adicionarEstrada(estrada3);
        nairobi.adicionarEstrada(estrada2);
        nairobi.adicionarEstrada(estrada3);
        paris.adicionarEstrada(estrada4);

        rodoviaria.adicionarCidade(cidadeDoCabo);
        rodoviaria.adicionarCidade(joanesburgo);
        rodoviaria.adicionarCidade(nairobi);
        rodoviaria.adicionarCidade(paris);

        rodoviaria.adicionarEstrada(estrada1);
        rodoviaria.adicionarEstrada(estrada2);
        rodoviaria.adicionarEstrada(estrada3);
        rodoviaria.adicionarEstrada(estrada4);
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOpções:");
            System.out.println("1. Recomendar Visitação em Todas as Cidades");
            System.out.println("2. Verificar Existência de Estrada entre Cidades");
            System.out.println("3. Identificar Cidades Inacessíveis");
            System.out.println("4. Recomendar Rota para Passageiro");
            System.out.println("5. Sair");
            System.out.println("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    rodoviaria.mostrarCidades();
                    rodoviaria.recomendarVisitaTodasCidades();
                    break;
                case 2:
                    System.out.print("Digite o ID da cidade de origem: ");
                    int idOrigem = scanner.nextInt();
                    System.out.print("Digite o ID da cidade de destino: ");
                    int idDestino = scanner.nextInt();

                    Cidade origem = rodoviaria.getCidades().get(idOrigem);
                    Cidade destino = rodoviaria.getCidades().get(idDestino);

                    if (rodoviaria.existeEstradaEntreCidades(origem, destino)) {
                        System.out.println("Existe uma estrada entre as cidades.");
                    } else {
                        System.out.println("Não existe uma estrada entre as cidades.");
                    }
                    break;
                case 3:
                    rodoviaria.cidadesInacessiveis();
                    break;
                case 4:
                        System.out.print("Digite o ID da cidade de origem para o passageiro: ");
                    int idOrigemPassageiro = scanner.nextInt();

                    Cidade origemPassageiro = rodoviaria.getCidades().get(idOrigemPassageiro);
                    List<Cidade> rotaPassageiro = rodoviaria.recomendarRotaPassageiro(origemPassageiro);

                    System.out.println("Recomendação de rota para o passageiro:");
                    for (Cidade cidade : rotaPassageiro) {
                        System.out.println(cidade.getNome());
                    }
                    break;
                case 5:
                    System.out.println("Saindo da aplicação.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}

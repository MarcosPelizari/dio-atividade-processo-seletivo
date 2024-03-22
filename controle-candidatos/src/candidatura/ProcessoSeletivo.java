package candidatura;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ProcessoSeletivo {

    private static List<String> candidatosSelecionadosSalario = new ArrayList<>();
    private static List<String> candidatosAtenderamLigacao = new ArrayList<>();

    public static void main(String[] args) {
        selecaoCandidatos();
        entrandoEmContato(String.valueOf(candidatosSelecionadosSalario));
        imprimirSelecionados();

    }

    static void imprimirSelecionados() {

        if (candidatosAtenderamLigacao.isEmpty()) {
            System.out.println("Nenhum dos candidatos atenderam a ligação.");
        } else {
            System.out.println("Imprimindo a lista de candidatos selecionados:");

            for (String candidato : candidatosAtenderamLigacao) {
                System.out.println(candidato);
            }
        }
    }

    static boolean atender() {
        return new Random().nextInt(3) == 1;
    }

    static void entrandoEmContato(String candidato) {
        int tentativasRealizadas = 1;
        boolean continuarTentando = true;
        boolean atendeu = false;

        do {
            atendeu = atender();
            continuarTentando = !atendeu;

            if (continuarTentando) {
                tentativasRealizadas++;
            } else {
                System.out.println("TENTATIVA REALIZADA COM SUCESSO.");
                candidatosAtenderamLigacao.add(candidato);
            }

        } while (continuarTentando && tentativasRealizadas < 3);

        if (atendeu) {
            System.out.println("Conseguimos contato com o candidato " + candidato + " na " + tentativasRealizadas + " tentativa.");
        } else {
            System.out.println("Não conseguimos contato com o candidato " + candidato + " número máximo de tentativas realizdas.");
        }
    }

    static void selecaoCandidatos() {
        String[] candidatos = {"FELIPE", "MARCIA", "JULIA", "PAULO", "AUGUSTO", "MONICA", "FABRICIO", "MIRELA", "DANIELA", "JORGE"};

        int candidatosSelecionados = 0;
        int candidatoAtual = 0;
        double salarioBase = 2000.0;

        while (candidatosSelecionados < 5 && candidatoAtual < candidatos.length) {
            String candidato = candidatos[candidatoAtual];
            double salarioPretendido = valorPretendido();

            System.out.printf("O candidato %s solicitou este valor de salário R$ %.2f.", candidato, salarioPretendido);
            System.out.println();
            if (salarioBase >= salarioPretendido) {
                System.out.println("O candidato " + candidato + " foi selecionado para a vaga.");
                candidatosSelecionadosSalario.add(candidato);
                analisarCandidato(salarioPretendido);
                candidatosSelecionados++;
            }
            candidatoAtual++;
        }
    }

    static void analisarCandidato(double salarioPretendido) {
        double salarioBase = 2000.0;

        if (salarioBase > salarioPretendido) {
            System.out.println("LIGAR PARA O CANDIDATO");
        } else if (salarioBase == salarioPretendido) {
            System.out.println("LIGAR PARA O CANDIDATO COM CONTRA PROPOSTA");
        } else {
            System.out.println("AGUARDAR DEMAIS CANDIDATOS");
        }
    }

    static double valorPretendido() {
        return ThreadLocalRandom.current().nextDouble(1800, 2200);
    }

}
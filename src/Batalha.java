import java.lang.String;
import java.util.Random;
import java.util.Scanner;

public class Batalha {

    public static void main(String[] args) {
        int[][] tabuleiro = new int[5][5];
        int[][] navio = new int[3][2];
        int[] tiro = new int[2];
        int acertos = 0;
        int tentativas = 0;

        iniciaTabuleiro(tabuleiro);
        posicionaNavios(navio);

        System.out.println("Vamos jogar batalha naval! Existem 3 navios escondidos que você deve destruir.");
        System.out.println("ATENÇÃO! O jogo só termina quando todos os navios forem destruídos.");

        do {
            mostraTabuleiro(tabuleiro);
            atirar(tiro);
            tentativas++;


                if (acertou(tiro, navio)) {
                    acertos++;
                    alteraTabuleiro(tiro, navio, tabuleiro);
                } else {
                    alteraTabuleiro(tiro, navio, tabuleiro);
                    resumo(tiro, navio, tentativas);
                }

        } while (acertos < 3);
        System.out.println("Parabéns, Você venceu! Acertou os 3 navios com " + tentativas + " tentativas.");
    }

    public static void mostraTabuleiro(int[][] tabuleiro) {
        System.out.println("\t1 \t2 \t3 \t4 \t5");

        for (int linha = 0; linha < 5; linha++) {
            System.out.print((linha + 1) + "\t");// imprime o número e o espaço antes de cada linha
            for (int coluna = 0; coluna < 5; coluna++) {
                if (tabuleiro[linha][coluna] == -1) {
                    System.out.print("░" + "░░░");
                } else if (tabuleiro[linha][coluna] == 0) {
                    System.out.print("█" + "░░░");
                } else if (tabuleiro[linha][coluna] == 1) {
                    System.out.print("X" + "░░░");
                }

            }
            System.out.println(); //pula para a próxima linha
        }
    }


    public static void atirar(int[] posTiro) {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o Número da Linha: ");
        posTiro[0] = input.nextInt();
        posTiro[0]--;

        System.out.print("Digite o Número da Coluna: ");
        posTiro[1] = input.nextInt();
        posTiro[1]--;

    }

    public static void iniciaTabuleiro(int[][] tabuleiro) {
        for (int linha = 0; linha < 5; linha++) {
            for (int coluna = 0; coluna < 5; coluna++) {
                tabuleiro[linha][coluna] = -1;
            }
        }
    }

    public static void posicionaNavios(int[][] navios) {
        Random sorteio = new Random();

        for (int posNavio = 0; posNavio < 3; posNavio++) {
            navios[posNavio][0] = sorteio.nextInt(5);
            navios[posNavio][1] = sorteio.nextInt(5);

            //checar se esse par não foi sorteado
            //se foi, so sai do do...while enquanto sortear um diferente
            for (int posAnterior = 0; posAnterior < posNavio; posAnterior++) {
                if ((navios[posNavio][0] == navios[posAnterior][0]) && (navios[posNavio][1] == navios[posAnterior][1]))
                    do {
                        navios[posNavio][0] = sorteio.nextInt(5);
                        navios[posNavio][1] = sorteio.nextInt(5);
                    } while ((navios[posNavio][0] == navios[posAnterior][0]) && (navios[posNavio][1] == navios[posAnterior][1]));
            }
            System.out.println(navios[posNavio][0] + "," + navios[posNavio][1]);//usar para verificar os pares sorteados
        }

    }

    public static boolean acertou(int[] posTiro, int[][] navios) {

        for (int posNavio = 0; posNavio < navios.length; posNavio++) {
            if (posTiro[0] == navios[posNavio][0] && posTiro[1] == navios[posNavio][1]) {
                System.out.printf("Você acertou o Navio na posição (%s,%s)!!!\n", posTiro[0] + 1, posTiro[1] + 1);
                return true;
            }
        }
        System.out.println("Você errou! Tente de Novo!");
        return false;
    }

    public static void resumo(int[] tiro, int[][] navios, int tentativa) {
        int linha = 0,
                coluna = 0;

        for (int fila = 0; fila < navios.length; fila++) {
            if (navios[fila][0] == tiro[0])
                linha++;
            if (navios[fila][1] == tiro[1])
                coluna++;
        }
        System.out.printf("\nJá foram %d tentativas! Dica:\nNa linha %d -> tem %d navio(s)\n" + "Na coluna %d -> tem %d navio(s)\n", tentativa, tiro[0] + 1, linha, tiro[1] + 1, coluna);
    }

    public static void alteraTabuleiro(int[] tiro, int[][] navios, int[][] tabuleiro) {
        if (acertou(tiro, navios))
            tabuleiro[tiro[0]][tiro[1]] = 1;
        else
            tabuleiro[tiro[0]][tiro[1]] = 0;
    }
}
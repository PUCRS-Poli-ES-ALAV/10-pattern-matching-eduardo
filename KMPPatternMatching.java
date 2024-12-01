public class KMPPatternMatching {

    public static void main(String[] args) {
        String s1 = "ABCDCBDCBDACBDABDCBADF";
        String s2 = "ADF";

        // Testa o algoritmo com strings pequenas
        int posicao = kmpBuscarPadrao(s1, s2);
        System.out.println("Posição da primeira ocorrência: " + (posicao != -1 ? posicao : "Não encontrada"));

        // Testa com strings grandes
        String grande1 = "A".repeat(500_000) + "B";
        String grande2 = "AB";
        int posicaoGrande = kmpBuscarPadrao(grande1, grande2);
        System.out.println("Posição da primeira ocorrência em strings grandes: " + posicaoGrande);
    }

    /**
     * Implementa o algoritmo KMP para encontrar a primeira ocorrência de s2 em s1.
     *
     * @param s1 String maior onde será feita a busca.
     * @param s2 Padrão a ser encontrado.
     * @return Posição inicial da primeira ocorrência ou -1 se não encontrada.
     */
    public static int kmpBuscarPadrao(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // Calcula o array de prefixos para o padrão (s2)
        int[] lps = calcularLPS(s2);

        int i = 0; // Índice para s1
        int j = 0; // Índice para s2

        while (i < n) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    return i - j; // Retorna a posição inicial da correspondência
                }
            } else {
                if (j > 0) {
                    j = lps[j - 1]; // Volta para o próximo maior prefixo-sufixo
                } else {
                    i++; // Avança no texto se j == 0
                }
            }
        }
        return -1; // Padrão não encontrado
    }

    /**
     * Calcula o array de prefixos-sufixos (LPS) para o padrão.
     *
     * @param s2 Padrão cujo LPS será calculado.
     * @return Array LPS.
     */
    public static int[] calcularLPS(String s2) {
        int m = s2.length();
        int[] lps = new int[m];
        int j = 0; // Comprimento do maior prefixo que também é sufixo
        int i = 1;

        while (i < m) {
            if (s2.charAt(i) == s2.charAt(j)) {
                lps[i] = j + 1;
                j++;
                i++;
            } else {
                if (j > 0) {
                    j = lps[j - 1]; // Volta para o próximo maior prefixo-sufixo
                } else {
                    lps[i] = 0; // Nenhum prefixo-sufixo para esta posição
                    i++;
                }
            }
        }
        return lps;
    }
}

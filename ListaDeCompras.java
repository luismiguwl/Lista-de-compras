package listadecompras;

import java.util.Scanner;

public class ListaDeCompras {

    public static void solicitarItens() {

        boolean itemJaExistirNaLista = false; // change this variable name
        double quantidadeInserida = 0;
        char resposta = ' ';
        String itemInserido;

        int posicaoVazia = encontrarPosicaoVazia();

        insercaoDeItens:
        for (int i = posicaoVazia; i < itensDaLista.length; i++) {
            System.out.println(">>>> " + (i + 1) + "º ITEM <<<<");

            System.out.print("Descrição: ");
            itemInserido = scan.next();

            if (i > 0) {

                itemJaExistirNaLista = verificarSeItemJaExisteNaLista(itemInserido);

                if (itemJaExistirNaLista) {
                    System.out.println("\n'" + itemInserido + "' já existe na lista. O produto não foi adicionado\n");
                    solicitarItens();
                    break;
                    // recursividade
                }

            }

            itensDaLista[i] = itemInserido;

            System.out.print("Unidade de medida: ");
            unidadeDeMedida[i] = scan.next();

            while (quantidadeInserida <= 0) {
                System.out.print("Quantos " + unidadeDeMedida[i] + " de " + itemInserido + ": ");
                quantidadeInserida = scan.nextDouble();

                if (quantidadeInserida <= 0) {
                    System.out.println("\nA quantidadeInserida não pode menor ou igual a 0\n");
                }
            }

            unidadesPorItem[i] = quantidadeInserida;
            quantidadeInserida = 0;

            verificadorDeContinuidade:
            while (resposta != 's' || resposta != 'n') {
                System.out.print("\nDeseja continuar inserindo itens (S/N): ");
                resposta = scan.next().charAt(0);

                resposta = Character.toLowerCase(resposta);

                switch (resposta) {
                    case 'n':

                        break insercaoDeItens;
                    case 's':
                        System.out.println();
                        break verificadorDeContinuidade;
                    default:
                        System.out.println("\n'" + resposta + "' não é válido. Informe S ou N como resposta!");
                }

            }

        }

    }

    public static boolean verificarSeItemJaExisteNaLista(String item) {
        boolean itemExisteNaLista = false;

        // A partir daqui, as string se tornam minúscula para realizar uma busca
        // mais precisa (devido ao case sensitive)
        item = item.toLowerCase();

        int quantidadeDeItens = quantificarItensNaLista();

        for (int i = 0; i < quantidadeDeItens; i++) {
            itensDaLista[i] = itensDaLista[i].toLowerCase();
        }

        for (int i = 0; i < quantidadeDeItens; i++) {
            if (item.equals(itensDaLista[i])) {
                itemExisteNaLista = true;
                break;
            }
        }

        return itemExisteNaLista;
    }

    public static void ordenar(String tipoDeOrdenacao) {

        int quantidadeDeItensNaLista = 0;
        String auxiliarString;
        double auxiliarDouble;

        quantidadeDeItensNaLista = quantificarItensNaLista();

        for (int i = 0; i < quantidadeDeItensNaLista; i++) {
            for (int j = 0; j < quantidadeDeItensNaLista; j++) {

                if (tipoDeOrdenacao.equals("Alfabeticamente")) {

                    int retorno = itensDaLista[i].compareTo(itensDaLista[j]);

                    if (retorno < 0) {

                        auxiliarString = itensDaLista[i];
                        itensDaLista[i] = itensDaLista[j];
                        itensDaLista[j] = auxiliarString;

                        auxiliarDouble = unidadesPorItem[i];
                        unidadesPorItem[i] = unidadesPorItem[j];
                        unidadesPorItem[j] = auxiliarDouble;

                        auxiliarString = unidadeDeMedida[i];
                        unidadeDeMedida[i] = unidadeDeMedida[j];
                        unidadeDeMedida[j] = auxiliarString;

                    }

                } else if (tipoDeOrdenacao.equals("Por quantidade de itens")) {

                    if (unidadesPorItem[i] > unidadesPorItem[j]) {

                        auxiliarDouble = unidadesPorItem[i];
                        unidadesPorItem[i] = unidadesPorItem[j];
                        unidadesPorItem[j] = auxiliarDouble;

                        auxiliarString = itensDaLista[i];
                        itensDaLista[i] = itensDaLista[j];
                        itensDaLista[j] = auxiliarString;

                        auxiliarString = unidadeDeMedida[i];
                        unidadeDeMedida[i] = unidadeDeMedida[j];
                        unidadeDeMedida[j] = auxiliarString;

                    }

                }

            }
        }

    }

    public static void removerItem() {
        int quantidadeDeItensNaLista = 0;
        int posicaoDoItemQueSeraExcluido = 0;

        if (itensDaLista[1].equals("")) {
            // Se torna verdade caso só exista um item na lista
            System.out.println(itensDaLista[0] + " é o único item da lista. Você não pode removê-lo");
        } else {

            // Contabiliza os itens inseridos na lista
            quantidadeDeItensNaLista = quantificarItensNaLista();

            for (int i = 0; i < quantidadeDeItensNaLista; i++) {
                System.out.println((i + 1) + ". " + itensDaLista[i]);
            }

            while (posicaoDoItemQueSeraExcluido <= 0 || posicaoDoItemQueSeraExcluido > quantidadeDeItensNaLista) {
                System.out.print("\nInforme o número do item que será excluido: ");
                posicaoDoItemQueSeraExcluido = scan.nextInt();

                if (posicaoDoItemQueSeraExcluido <= 0 || posicaoDoItemQueSeraExcluido > quantidadeDeItensNaLista) {
                    System.out.print("\n'" + posicaoDoItemQueSeraExcluido + "' não é válido. ");
                    System.out.println("Informe um número entre 1 e " + quantidadeDeItensNaLista + "\n");
                }

            }

            posicaoDoItemQueSeraExcluido--;

            System.out.print("\n" + itensDaLista[posicaoDoItemQueSeraExcluido]);

            itensDaLista[posicaoDoItemQueSeraExcluido] = "";
            unidadeDeMedida[posicaoDoItemQueSeraExcluido] = "";
            unidadesPorItem[posicaoDoItemQueSeraExcluido] = 0;

            System.out.println(" removido(a) da lista!\n");

            empurrarPosicaoVazia();
        }

    }

    public static void empurrarPosicaoVazia() {

        int posicaoVazia = encontrarPosicaoVazia();

        for (int i = posicaoVazia; i < itensDaLista.length; i++) {

            itensDaLista[i] = itensDaLista[i + 1];
            itensDaLista[i + 1] = itensDaLista[i];

            unidadeDeMedida[i] = unidadeDeMedida[i + 1];
            unidadeDeMedida[i + 1] = unidadeDeMedida[i];

            unidadesPorItem[i] = unidadesPorItem[i + 1];
            unidadesPorItem[i + 1] = unidadesPorItem[i];

            if (itensDaLista[i + 1].equals("")) {
                break;
            }
        }

    }

    public static int encontrarPosicaoVazia() {
        int posicaoVazia = 0;

        for (int i = 0; i < itensDaLista.length; i++) {
            if (itensDaLista[i].equals("")) {
                posicaoVazia = i;
                break;
            }
        }

        return posicaoVazia;
    }

    public static void alterarQuantidadeDeUmItem() {

        int posicaoDoItemQueSeraAlterado = 0;
        int quantidadeDeItensNaLista = 0;
        String itemQueSeraAlterado = "";
        double novaQuantidade = 0;

        quantidadeDeItensNaLista = quantificarItensNaLista();

        for (int i = 0; i < quantidadeDeItensNaLista; i++) {
            System.out.println((i + 1) + ". " + itensDaLista[i]);
        }

        while (posicaoDoItemQueSeraAlterado <= 0 || posicaoDoItemQueSeraAlterado > quantidadeDeItensNaLista) {
            System.out.print("\nInforme o número do item que deseja alterar: ");
            posicaoDoItemQueSeraAlterado = scan.nextInt();

            if (posicaoDoItemQueSeraAlterado <= 0 || posicaoDoItemQueSeraAlterado > quantidadeDeItensNaLista) {
                System.out.print("\n'" + posicaoDoItemQueSeraAlterado + "' é inválido. ");
                System.out.println("Selecione um número entre 1 e " + quantidadeDeItensNaLista + "\n");
            }
        }

        posicaoDoItemQueSeraAlterado--;

        itemQueSeraAlterado = itensDaLista[posicaoDoItemQueSeraAlterado];

        System.out.print("\nQuantidade atual de " + itemQueSeraAlterado + ": ");
        System.out.print(unidadesPorItem[posicaoDoItemQueSeraAlterado] + " ");
        System.out.println(unidadeDeMedida[posicaoDoItemQueSeraAlterado] + "\n");

        while (novaQuantidade <= 0) {
            System.out.print("Nova quantidade de " + itemQueSeraAlterado + ": ");
            novaQuantidade = scan.nextDouble();

            if (novaQuantidade <= 0) {
                System.out.println("\nA nova quantidade deve ser maior do que 0\n");
            }
        }

        unidadesPorItem[posicaoDoItemQueSeraAlterado] = novaQuantidade;

        System.out.println("\n---------- QUANTIDADE ALTERADA! -----------");

    }

    public static void main(String[] args) {

        // Declarações
        boolean solicitarParada = false;
        int escolhaDoUsuario = 0;
        int quantidadeDeItensNaLista = 0;

        String[] menuDeOpcoes = {"Adicionar item", "Remover item", "Alterar quantidade de um item",
            "Listar alfabeticamente", "Listar por unidades", "Limpar lista", "Interromper aplicação"};

        System.out.println(">>> LISTA DE COMPRAS <<<\n");
        inicializarVetores();

        // Coleta de dados
        solicitarItens();

        while (!solicitarParada) {
            System.out.println("\n---------- MENU DE OPÇÕES ----------");

            for (int i = 0; i < menuDeOpcoes.length; i++) {
                System.out.println((i + 1) + ". " + menuDeOpcoes[i]);
            }

            escolhaDoUsuario = solicitarTarefa(menuDeOpcoes);

            System.out.println("\n>>>>> " + menuDeOpcoes[escolhaDoUsuario].toUpperCase() + "");

            switch (escolhaDoUsuario) {
                case 0:
                    solicitarItens();
                    break;
                case 1:
                    removerItem();
                    break;
                case 2:
                    alterarQuantidadeDeUmItem();
                    break;
                case 3:
                    ordenar("Alfabeticamente");
                    break;
                case 4:
                    ordenar("Por quantidade de itens");
                    break;
                case 5:
                    limparListaDeCompras();
                    solicitarItens();
                    break;
                case 6:
                    solicitarParada = true;
                    System.out.println("Obrigado por utilizar!");
                    continue;
            }

            System.out.println("\nSua lista atual possui\n");

            quantidadeDeItensNaLista = quantificarItensNaLista();

            for (int i = 0; i < quantidadeDeItensNaLista; i++) {
                System.out.println(unidadesPorItem[i] + " " + unidadeDeMedida[i] + " de " + itensDaLista[i]);
            }

        }

    }

    public static int solicitarTarefa(String[] menuDeOpcoes) {

        int escolhaDoUsuario = 0;

        while (escolhaDoUsuario <= 0 || escolhaDoUsuario > menuDeOpcoes.length) {
            System.out.print("\nDigite o número da opção para selecioná-la: ");
            escolhaDoUsuario = scan.nextInt();

            if (escolhaDoUsuario <= 0 || escolhaDoUsuario > menuDeOpcoes.length) {
                System.out.print("\n'" + escolhaDoUsuario + "' não é válido. ");
                System.out.println("Informe um número entre 1 e " + menuDeOpcoes.length);
            }

        }

        escolhaDoUsuario--;

        return escolhaDoUsuario;

    }

    public static void inicializarVetores() {
        for (int i = 0; i < posicoes; i++) {
            unidadeDeMedida[i] = "";
            unidadesPorItem[i] = 0;
            itensDaLista[i] = "";
        }
    }

    public static String converterPrimeiraLetraEmMaiuscula(String elemento) {
        char[] vetorManipulado = elemento.toCharArray(); // cria um vetor de char baseado no argumento

        vetorManipulado[0] = Character.toLowerCase(vetorManipulado[0]); // transforma a 1a posição do vetor em maiúscula

        elemento = new String(vetorManipulado); // o elemento recebe o vetor de char que foi manipulado

        return elemento;
    }

    public static void limparListaDeCompras() {

        int quantidadeDeItensNaLista = quantificarItensNaLista();

        for (int i = 0; i < quantidadeDeItensNaLista; i++) {
            unidadeDeMedida[i] = "";
            unidadesPorItem[i] = 0;
            itensDaLista[i] = "";
        }

        System.out.println("SUA LISTA FOI RESETADA COM SUCESSO\n");

    }

    public static int quantificarItensNaLista() {

         /*
         * A função "varre" cada posição do vetor em busca de uma posição vazia
         * Se não encontrar, incrementa a variável que contabiliza quantos itens existem na lista 
         * Se encontrar, interrompe a varredura e retorna a quantidade de itens
        
         * JUSTIFICATIVA: ao fazer isso, tu não precisa iterar a mesma estrutura em cada parte em que precise exibir/procurar algo no vetor 
         * Evita usar breaks e if/else, deixando o código mais limpo e legível
         */
        int quantidadeDeItensNaLista = 0;

        for (int i = 0; i < itensDaLista.length; i++) {
            if (itensDaLista[i] != "") {
                quantidadeDeItensNaLista++;
            } else {
                break;
            }

        }

        return quantidadeDeItensNaLista;
    }

    // Variáveis globais
    public static Scanner scan = new Scanner(System.in);

    public static int posicoes = 100;
    public static double[] unidadesPorItem = new double[posicoes];
    public static String[] itensDaLista = new String[posicoes];
    public static String[] unidadeDeMedida = new String[posicoes];

}
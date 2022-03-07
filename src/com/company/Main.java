package com.company;
import Classes.*;
import java.util.List;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


//Desenvolvido por Ana Silva e Bianca Oliveira - 2022

public class Main {

    //grava informações dos testes em arquivo(s)
    public static  void gravar(int x, List<No> caminho, long diff, int tipo){
        File arquivo;
        int i;
        //cria um novo arquivo para cada teste executado
        //se a pasta de testes não for limpa a cada execução dos testes
        //os arquivos já existentes recebem as novas informações sem sobreescrever as antigas (grava sempre no final)
        arquivo = new File("testes/testes"+  x +".txt");

        try {
            if (!arquivo.exists()) {
                //cria um arquivo (vazio)
                arquivo.createNewFile();
            }

            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);


            if(tipo == 1){//busca por profundidade limitada
                bw.write( "Busca por profundidade limitada \n");
            }else{
                bw.write( " Busca por Hill Climbing \n");
            }

            if(caminho != null){
                for( i = 0; i<caminho.size(); i++){
                    caminho.get(i).getEstado().printArq(arquivo);
                }
            }

            bw.write( "Tempo de execução: "+ diff  + "\n");
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void menuBusca(Tabuleiro estadoInicial) {
        int op= -1, limite, i;
        long init, end, diff;
        Scanner ler = new Scanner(System.in);
        Buscas busca = new Buscas(estadoInicial);
        List<No> caminho;

        System.out.println("-------- Estado inicial --------");
        estadoInicial.print();
        System.out.println();

        System.out.println("Escolha a opção");
        System.out.println("1 - Busca por profundidade limitada");
        System.out.println("2 - Busca por Hill Climbing");
        System.out.println("3 - Ambos");
        System.out.println("0 - Voltar");
        System.out.print("Opção = ");
        op= ler.nextInt();
        switch (op) {
            case 1:
                System.out.print("Informe a profundidade maxima:");
                limite= ler.nextInt();

                init = System.nanoTime();//Tempo inicial
                caminho = busca.buscaPronfundidadeLimitada(limite);
                end = System.nanoTime();//Tempo final
                diff = end - init;//Tempo de Execução

                if(caminho!=null){// testa se o caminho não esta vazio e imprime ele
                    System.out.println("------------- Busca por profundidade limitada -------------");
                    for( i = 0; i<caminho.size(); i++){
                        caminho.get(i).getEstado().print();
                    }
                }
                System.out.println("Tempo de execução: " + diff + "nanosegundos");
                break;
            case 2:
                init = System.nanoTime();//Tempo inicial
                caminho = busca.buscaHillClimbing();
                end = System.nanoTime();//Tempo final
                diff = end - init;//Tempo de Execução

                if(caminho!=null){// testa se o caminho não esta vazio e imprime ele
                    System.out.println("------------- Busca por Hill Climbing -------------");
                    for( i = 0; i<caminho.size(); i++){
                        caminho.get(i).getEstado().print();
                    }
                }
                System.out.println("Tempo de execução: " + diff + "nanosegundos");
                break;
            case 3:
                System.out.print("Informe a profundidade maxima:");
                limite= ler.nextInt();

                init = System.nanoTime();//Tempo inicial
                caminho = busca.buscaPronfundidadeLimitada(limite);
                end = System.nanoTime();//Tempo final
                diff = end - init;//Tempo de Execução

                if(caminho!=null){// testa se o caminho não esta vazio e imprime ele
                    System.out.println("------------- Busca por profundidade limitada -------------");
                    for( i = 0; i<caminho.size(); i++){
                        caminho.get(i).getEstado().print();
                    }
                }
                System.out.println("Tempo de execução: " + diff + "nanosegundos");

                init = System.nanoTime();//Tempo inicial
                caminho = busca.buscaHillClimbing();
                end = System.nanoTime();//Tempo final
                diff = end - init;//Tempo de Execução

                if(caminho!=null){// testa se o caminho não esta vazio e imprime ele
                    System.out.println("------------- Busca por Hill Climbing -------------");
                    for( i = 0; i<caminho.size(); i++){
                        caminho.get(i).getEstado().print();
                    }
                }
                System.out.println("Tempo de execução: " + diff + "nanosegundos");
                break;
            case 0: break;
            default: System.out.println("Opção incorreta");
        }
    }

    public static void testes( String[][] tabuleiro){
        Tabuleiro estadoInicial = new Tabuleiro();
        Random aleatorio = new Random();
        List<No> caminho;
        long init;
        long end;
        long diff;
        int x, y, j;


        for(int i = 0; i < 50; i++){//numero de vezes que roda o teste

            //gera randomicamente a posição da primeira rainha
            x = aleatorio.nextInt(7);//linha
            y = aleatorio.nextInt(7);//coluna
            estadoInicial.novoTabuleiro(tabuleiro, x, y);//Atualiza tabuleiro com a nova rainha

            Buscas busca = new Buscas(estadoInicial);
            System.out.println("------------- Busca por profundidade limitada -------------");
            init = System.nanoTime();//Tempo inicial
            caminho = busca.buscaPronfundidadeLimitada(7);//limite igual a 7 pois queremos achar a solução otima
            end = System.nanoTime();//Tempo final
            diff = end - init;//Tempo de Execução
            gravar(i,caminho,diff,1);
//            for( j = 0; j<caminho.size(); j++){
//                caminho.get(i).getEstado().print();
//            }
            System.out.println("Tempo de execução: " + diff + "nanosegundos");

            System.out.println("------------- Busca por Hill Climbing -------------");
            init = System.nanoTime();//Tempo inicial
            caminho = busca.buscaHillClimbing();
            end = System.nanoTime();//Tempo final
            diff = end - init;//Tempo de Execução
            gravar(i,caminho,diff,0);
//            for( j = 0; j<caminho.size(); j++){
//                caminho.get(i).getEstado().print();
//            }
            System.out.println("Tempo de execução: " + diff + "nanosegundos");
        }
    }

    public static void main(String[] args) {
        String[][] tabuleiro  = new String[8][8];
        Random aleatorio = new Random();
        Tabuleiro estadoInicial;

        int x, y;//linha e coluna da minha primeira rainha
        int i;
        int op= -1;

        //cria novo tabuleiro vazio
        for( i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                tabuleiro[i][j] = "[ ]";//vazio
            }
        }
        //Inicia tabuleiro
        estadoInicial = new Tabuleiro();

        Scanner ler = new Scanner(System.in);
        while (op != 0) {
            System.out.println("\nEscolha a opção");
            System.out.println("1 - Gerar primeira rainha em uma posição aleatória");
            System.out.println("2 - Gerar primeira rainha em uma posição especifica");
            System.out.println("3 - Rodar testes");
            System.out.println("0 - Sair");
            System.out.print("Opção = ");
            op = ler.nextInt();

            switch (op) {
                case 1:
                    //gera randomicamente a posição da primeira rainha
                    x = aleatorio.nextInt(7);//linha
                    y = aleatorio.nextInt(7);//coluna
                    estadoInicial.novoTabuleiro(tabuleiro, x, y);//Atualiza tabuleiro com a nova rainha
                    menuBusca(estadoInicial);

                    break;
                case 2:
                    System.out.print("Informe uma linha (0 á 7):");
                    x = ler.nextInt();
                    System.out.print("Informe uma coluna (0 á 7):");
                    y = ler.nextInt();
                    estadoInicial.novoTabuleiro(tabuleiro, x, y);//Atualiza tabuleiro com a nova rainha
                    menuBusca(estadoInicial);
                    break;
                case 3:
                    testes(tabuleiro);
                case 0:
                    break;
                default:
                    System.out.println("Opção incorreta");
            }

        }
    }
}

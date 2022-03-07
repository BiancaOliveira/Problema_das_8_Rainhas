package Classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Tabuleiro {
    String[][] tabuleiro ;

    public Tabuleiro(){
        tabuleiro = new String[8][8];
    }

    public String[][] getTabuleiro() {
        return tabuleiro;
    }

    //adiciona uma nova rainha no tabuleiro
    public void novoTabuleiro (String[][] tabuleiro, int x, int y){

        copia(tabuleiro);
        for(int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                if(i == x){
                    this.tabuleiro[i][j] = "[-]";
                }
                if(j == y){
                    this.tabuleiro[i][j] = "[-]";
                }
                if(i-j == x-y){//diagonal principal
                    this.tabuleiro[i][j] = "[-]";
                }
                if(i+j == x+y){//diagonal secundaria
                    this.tabuleiro[i][j] = "[-]";
                }
            }
            this.tabuleiro[x][y]= "[Q]";
        }
    }

    public void copia(String[][] tabuleiro){
        int i, j;
        for( i = 0; i < 8; i ++){
            for( j = 0; j < 8; j ++){
                this.tabuleiro[i][j] = tabuleiro[i][j];
            }
        }
    }

    //imprime tabuleiro
    public void print(){
        for(int i = 0; i < 8; i ++){
            for(int j = 0; j < 8; j ++){
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //salva tabuleiro em um arquivo
    public void printArq(File arquivo){

        try {
            if (!arquivo.exists()) {
                //cria um arquivo (vazio)
                arquivo.createNewFile();
            }
            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i = 0; i < 8; i ++){
                for(int j = 0; j < 8; j ++){
                    bw.write(tabuleiro[i][j] + " ");
                }
                bw.newLine();
            }
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //conta quantos espaços vazios tem no tabuleiro
    public int contaVazio(){
        int cont = 0;
        for(int i = 0; i < 8; i ++){
            for(int j = 0; j < 8; j ++){
                if(tabuleiro[i][j]=="[ ]"){
                    cont ++;
                }
            }
        }
        return  cont;
    }

}

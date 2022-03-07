package Classes;

import java.util.ArrayList;
import java.util.List;

public class No {
    Tabuleiro estado; //Estado atual do tabuleiro
    int altura; //altura do nó
    No pai;
    boolean status;//mostra se meu nó foi visitado ou não
    List<No> filhos;//lista de filhos

    public No(Tabuleiro estado,No pai){
        this.estado = estado;
        this.pai = pai;
        filhos = new ArrayList<>();
    }

    public List<No> getFilhos() {
        return filhos;
    }

    public void setFilhos(List<No> filhos) {
        this.filhos = filhos;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getAltura() {
        return altura;
    }

    public No getPai() {
        return pai;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Tabuleiro getEstado() {
        return estado;
    }

}

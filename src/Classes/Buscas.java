package Classes;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Buscas {
    Tabuleiro estadoInicial;

    public Buscas(Tabuleiro estadoInicial){
        this.estadoInicial = estadoInicial;
    }


    //busca e retorna o caminho para um nó
    private List<No> caminho (No no){
        List<No> caminho = new ArrayList<>();
        while(no != null){
            caminho.add(0, no);
            no = no.getPai();
        }
        return caminho;
    }

    //busca melhor no concorrente HillClambing
    private No melhor (List<No> concorrentes){
        No melhor;
        int aux1, aux2;

        aux1 = concorrentes.get(0).getEstado().contaVazio();
        melhor = concorrentes.get(0);
        //Percorre minha lista de concorrentes para achar o melhor concorrente. Para isso aplica a seguinte heuristica:
        //Meu melhor concorrente será aquele que possue mais espaços vazios em seu tabuleiro.
        //Se mais de um tabuleiro apresentar a mesma quantidade de espaços vazios escolhece o primeiro que achar

        for(int i = 1; i< concorrentes.size()-1; i++){
            aux2 = concorrentes.get(i).getEstado().contaVazio();//chama a heuristica
            if(aux1 < aux2){
                melhor = concorrentes.get(i);
                aux1 = aux2;
            }
        }
        return melhor;
    }

    //busca os filhos de um nó caso exista
    private List<No> abrirNo(No pai){
        List<No> filhos = new ArrayList<>();
        String[][] aux = pai.getEstado().getTabuleiro();

        for(int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                if (aux[i][j]=="[ ]"){//busca espaço disponivel para aloacar filho

                    //cria novo estado do tabuleiro
                    Tabuleiro  aux2= new Tabuleiro();//cria um novo tabuleiro
                    aux2.novoTabuleiro(aux,i,j);//atualiza o novo tabuleiro

                    //cria no filho com o tabuleiro
                    No novo = new No(aux2,pai);
                    novo.setAltura(pai.getAltura()+1);

                    //adiciona filho na lista de filhos
                    filhos.add(novo);
                }
            }
        }
        return filhos;
    }

    //realiza a busca por profundidade passando o limite para o nivel de busca
    //o nivel icicia em 0 (estado inicoal)
    public List<No> buscaPronfundidadeLimitada(int limite){
        Stack<No> pilha = new Stack<>();//pilha com os nos a ser avaliados
        No root = new No(estadoInicial,null);//inicia raiz da arvore
        root.setAltura(0);//set autura do no

        root.setStatus(true);
        pilha.push(root);

        while (true){
            if(pilha.empty()){//verifica se minha lista de nós a ser avaliados esta vazia
                System.out.println("Não foi atingido neste limite");
                return  null;
            }
           // System.out.println(pilha.peek().getAltura());
            if(pilha.peek().getAltura() == 7){//verifica se meu estado esperado foi alcançado
                return caminho(pilha.pop());// retorta o caminho para o estado final
            }else{
                if(pilha.peek().getAltura()<limite){//verifica se a minha altura é menor que o limite
                    if((abrirNo(pilha.peek()).size() != 0)) {//verifica se meu no tem filhos
                        if ((pilha.peek().getFilhos().size() == 0)){//verifica se os filhos eles já foram inicializados
                            pilha.peek().setFilhos(abrirNo(pilha.peek())); //cria meus filhos
                        }
                        int i = 0;
                        if (!pilha.peek().getFilhos().get(pilha.peek().getFilhos().size() - 1).isStatus()) {//verifica se meu ultimo filho do meu nó já foi visitado
                            while (i < pilha.peek().getFilhos().size()) {//busca o meu filho mais a esquerda que ainda não foi visitado
                                if (!pilha.peek().getFilhos().get(i).isStatus()) {//se meu filho não foi visitado
                                    pilha.peek().getFilhos().get(i).setStatus(true);//atualiza status
                                    pilha.push(pilha.peek().getFilhos().get(i));//adiciona filho a pilha
                                    break;
                                }
                                i++;
                            }
                        }else{
                            pilha.pop();
                        }
                    }else{
                        pilha.pop();
                    }
                }else{
                    pilha.pop();
                }
            }
        }
    }

    public List<No> buscaHillClimbing(){

        List<No> filhos;
        No estado;

        No root = new No(estadoInicial,null);//inicia raiz da arvore
        root.setAltura(0);//set altura do no
        estado = root;

        while (true){
            if (abrirNo(estado).size() != 0){//vejo se meu no tem vizinhos
                filhos = abrirNo(estado); // abre meu no
                estado =  melhor(filhos);//atuliza meu novo estado inicial com o melhor concorrente

            }else{
                return caminho(estado);
            }
        }
    }

}

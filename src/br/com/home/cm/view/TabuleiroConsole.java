package br.com.home.cm.view;

import br.com.home.cm.exception.ExplosaoException;
import br.com.home.cm.exception.SairDoJogoException;
import br.com.home.cm.model.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {

    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro){
        this.tabuleiro = tabuleiro;

        executarJogo();
    }

    public void executarJogo(){
        try{
            boolean continuar = true;

            while(continuar){

                System.out.println("Você que um novo jogo (S/n)");
                String resposta = entrada.nextLine();

                if("n".equalsIgnoreCase(resposta)){
                    continuar = false;
                }else{
                    tabuleiro.reiniciar();
                }
            }
        }catch (SairDoJogoException e){
            System.out.println("Tchau!!!");
        }finally {
            entrada.close();
        }
    }

    private void cicloDoJogo() {
        try{
            while(!tabuleiro.obejetivoAlcancado()){
                System.out.println(tabuleiro);

                String digitado = entradaConsole("Digite o valor de (x,y): ");

                Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                        .map(e -> Integer.parseInt(e)).iterator();

                digitado = entradaConsole("1- Abrir | 2- Des(Marcar)");
                if("1".equals(digitado)){
                    tabuleiro.abrirCampo(xy.next(), xy.next());
                }else if("2".equals(digitado)){
                    tabuleiro.alternarMarcacao(xy.next(), xy.next());
                }

            }
            System.out.println("Você ganhou!");
        }catch (ExplosaoException e){
            System.out.println("Você perdeu!");
            System.out.println(tabuleiro);
        }
    }

    private String entradaConsole(String texto){
        System.out.print(texto);
        String digitado = entrada.nextLine();
        return digitado;
    }

}

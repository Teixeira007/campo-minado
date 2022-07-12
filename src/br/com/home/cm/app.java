package br.com.home.cm;

import br.com.home.cm.model.Tabuleiro;
import br.com.home.cm.view.TabuleiroConsole;

public class app {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(10,10,15);

        new TabuleiroConsole(tabuleiro);




    }
}

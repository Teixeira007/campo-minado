package br.com.home.cm.model;

import br.com.home.cm.exception.ExplosaoException;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    private final int coluna;
    private final int linha;

    private boolean minado;
    private boolean marcado;
    private boolean aberto;

    private List<Campo> vizinhos = new ArrayList<>();

    public Campo(int linha, int coluna) {
        this.coluna = coluna;
        this.linha = linha;
    }

    public boolean adicionarVizinho(Campo vizinho) {
        boolean linhaDifirente = this.linha != vizinho.linha;
        boolean colunaDiferente = this.coluna != vizinho.coluna;
        boolean diagonal = linhaDifirente && colunaDiferente;

        int deltaLinha = Math.abs(this.linha - vizinho.linha);
        int deltaColuna = Math.abs(this.coluna - vizinho.coluna);
        int deltaGeral = deltaLinha + deltaColuna;

        if (deltaGeral == 1 && !diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else if (deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else {
            return false;
        }
    }

    void alternarMarcacao() {
        if (!aberto) {
            marcado = !marcado;
        }
    }

    boolean abrirCampo() {
        if (!aberto && !marcado) {
            aberto = true;

            if (minado) {
                throw new ExplosaoException();
            }

            if (vizinhancaSegura()) {
                vizinhos.forEach(v -> v.abrirCampo());
            }

            return true;
        } else {
            return false;
        }
    }

    boolean vizinhancaSegura() {
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public boolean isMinado() {
        return minado;
    }

    public boolean isMarcado() {
        return marcado;
    }

    void minar() {
        minado = true;
    }

    public boolean isAberto(){
        return aberto;
    }

    public boolean isFechado(){
        return !aberto;
    }


}

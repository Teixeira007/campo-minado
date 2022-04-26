package br.com.home.cm.model;

import br.com.home.cm.exception.ExplosaoException;
import br.com.home.cm.model.Campo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CampoTest {

    Campo campo;
    @BeforeEach
    void Campo(){
        campo = new Campo(3, 3);
    }

    @Test
    void adicionarVizinhosEmCimaDistancia1(){
        Campo vizinho = new Campo(2,3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void adicionarVizinhosEmBaixoDistancia1(){
        Campo vizinho = new Campo(4,3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void adicionarVizinhosNaDireitaDistancia1(){
        Campo vizinho = new Campo(3,4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void adicionarVizinhosNaEsquerdaDistancia1(){
        Campo vizinho = new Campo(3,2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void adicionarVizinhosDiagonalSuperiorDireitaDistancia2(){
        Campo vizinho = new Campo(2, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void adicionarVizinhosDiagonalSuperiorEsquerdaDistancia2(){
        Campo vizinho = new Campo(2, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void adicionarVizinhosDiagonalInferiorDireitaDistancia2(){
        Campo vizinho = new Campo(4, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void adicionarVizinhosDiagonalInferiorEsquerdaDistancia2(){
        Campo vizinho = new Campo(4, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void adicionarNaoVizinho(){
        Campo vizinho = new Campo(1, 1);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }

    @Test
    void testValorPadraoMarcado(){
        assertFalse(campo.isMarcado());
    }

    @Test
    void testCampoMinado(){
        campo.minar();
        assertTrue(campo.isMinado());
    }
    @Test
    void testAlternarMarcacao(){
        campo.alternarMarcacao();;
        assertTrue(campo.isMarcado());
    }

    @Test
    void testAlternarMarcacaoChamadaDupla(){
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testAbrirCampoNaoMinadoNaoMarcado(){
        assertTrue(campo.abrirCampo());
    }

    @Test
    void testAbrirCampoNaoMinadoMarcado(){
        campo.alternarMarcacao();
        assertFalse(campo.abrirCampo());
    }

    @Test
    void testAbrirCampoMinadoNaoMarcado(){
        campo.minar();
        assertThrows(ExplosaoException.class, ()->{
            campo.abrirCampo();
        });
    }

    @Test
    void testAbrirCampoMinadoMarcado(){
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrirCampo());
    }

    @Test
    void testAbrirComVizinhos(){
        Campo campo11 = new Campo(1,1);
        Campo campo22 = new Campo(2,2);

        campo22.adicionarVizinho(campo11);
        campo.adicionarVizinho(campo22);

        campo.abrirCampo();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void testAbrirComVizinhosMinados(){
        Campo campo11 = new Campo(1,1);
        Campo campo22 = new Campo(2,2);

        campo22.adicionarVizinho(campo11);
        campo.adicionarVizinho(campo22);
        campo11.minar();

        campo.abrirCampo();

        assertTrue(campo22.isAberto() && campo11.isFechado());
    }







}

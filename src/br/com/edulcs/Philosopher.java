/*
 *
 *   =====================================================
 *  |    Copyright (c) 2021. Lucas Eduardo da Silva Bispo |
 *  |    GitHub: github.com/edulcs                        |
 *  |    Linkedin: linkedin.com/in/lucaseduardobispo      |
 *  |                                                     |
 *  |        ***********************************          |
 *  |        *         Apache License           *         |
 *  |        *     Version 2.0, January 2004    *         |
 *  |        *  http://www.apache.org/licenses/ *         |
 *  |        *                                  *         |
 *  |        ************************************         |
 *  |                  10/03/2021 18:10	                  |
 *   =====================================================
 *
 */

package br.com.edulcs;

/*    PSEUDOCÓDIGO

      Aqui eu suponho que cada filósofo tenha 10 refeições e
      logo depois que todos comerem todas as refeições, o processo será interrompido.
      Caso contrário, este processo será interminável.

        refeicoes=10;

        Enquanto(refeicoes > 0){
           pensando();
           pegarGarfoEsquerdo();
           pegarGarfoDireito();

           comer();
           refeicoes--;
           largarGarfoEsquerdo();
           largarGarfoDireito();
        }

        Primeiro cada filósofo está pensando e depois de certo tempo eles ficarão com fome.
        Então, eles pegam o garfo esquerdo primeiro e verificam se o garfo certo está disponível ou não.
        Se estiver disponível, ele comerá e, assim que terminar de comer, ele colocará os dois garfos na mesa.
        Se não estiver disponível, ele também largará o outro garfo.


*/

import java.time.LocalTime;

// A classe Philosopher implementa a interface Runnable para poder executar cada objeto Philosopher como threads separados.
// Cada filósofo tem acesso a dois garfos em seus lados esquerdo e direito:
public class Philosopher implements Runnable{
    private Object leftFork;
    private Object rightFork;
    private int foodLeft = 10;

    public Philosopher(Object leftFork,Object rightFork){
        this.leftFork= leftFork;
        this.rightFork= rightFork;
    }

//  Método que instrui um filósofo a realizar uma ação - comer, pensar ou adquirir garfos na preparação para comer:
    public void doAction(String action) throws InterruptedException{
        System.out.println(
                Thread.currentThread().getName()+" "+action);
        Thread.sleep((int)(Math.random()*100));

    }


//  Cada ação é simulada suspendendo o encadeamento de chamada por um período de tempo aleatório,
//  de forma que a ordem de execução não seja imposta apenas pelo tempo.


//  Pra simular a aquisição de um fork, precisamos travá-lo para que dois threads filósofos não o adquiram ao mesmo tempo.
//  Pra conseguir isso, usamos a palavra-chave synchronized para adquirir o monitor interno do objeto fork
//  e evitar que outros threads façam o mesmo.
    @Override
    public void run() {
        try{
            while(foodLeft>0){
                // Pensando
                doAction(LocalTime.now() + "ms: PENSANDO..." );
                synchronized (leftFork){
                    doAction(LocalTime.now()
                            + "ms: Pegou o garfo da ESQUERDA");
                    synchronized (rightFork){
                        // Comendo
                        doAction(LocalTime.now()
                                + "ms: Pegou o garfo da DIREITA e começou a comer.");
                                doAction(LocalTime.now()
                                        + "ms: Largou o garfo da mão DIREITA.");
                        this.foodLeft-=1;
                    }
                    doAction(LocalTime.now()
                            + "ms: Largou o garfo da mão ESQUERDA e começou PENSAR...");
                }
            }
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
            return;
        }
    }

//  Esse esquema implementa exatamente o descrito anteriormente: um filósofo pensa um pouco e depois decide comer.

//  Depois disso, ele adquire os garfos à esquerda e à direita e começa a comer. Quando terminar, ele abaixa os garfos.
//  Também é adicionamos logs de tempo paraa cada ação, o que nos ajuda a entender a ordem em que os eventos ocorrem.

}

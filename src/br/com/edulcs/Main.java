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
 *  |                  10/03/2021 19:43	                  |
 *   =====================================================
 *
 */

package br.com.edulcs;

public class Main {

    public static void main(String[] args) {

        // Para iniciar o processo, escrevemos um cliente que cria 5 Philosophers como threads e inicia todos eles.

        // Modelamos cada um dos garfos como objetos Java genéricos e fazemos tantos deles quanto filósofos.
        // Passamos a cada Filósofo seus garfos esquerdo e direito que ele tenta travar usando a palavra - chave synchronized.

        Philosopher philosopher[] = new Philosopher[5];
        Object fork[] = new Object[5];

        for (int i = 0; i < philosopher.length; i++) {
            fork[i] = new Object();
        }

        for (int i = 0; i < philosopher.length; i++) {
            Object leftFork = fork[i];
            Object rightFork = fork[(i + 1) % 5];



            // O principal motivo de um deadlock é a condição de espera circular, em que cada processo aguarda um recurso
            // que está sendo mantido por algum outro processo. Portanto, para evitar uma situação de conflito,
            // precisamos nos certificar de que a condição de espera circular seja interrompida.
            // Existem várias maneiras de fazer isso, sendo a mais simples a seguinte:

            // Todos os Filósofos alcançam primeiro o garfo esquerdo, exceto aquele que primeiro alcança o garfo direito.

            // Isso quebra a condição de espera circular e podemos evitar o impasse.



            // Condição para evitar o Deadlock.
            // O último filósofo pega o garfo direito primeiro.
            if (i == philosopher.length - 1) {
                philosopher[i] = new
                        Philosopher(leftFork, rightFork);
            } else {
                philosopher[i] = new
                        Philosopher(rightFork, leftFork);
            }
            Thread t = new Thread(
                    philosopher[i], " Filósofo " + (i + 1));
            t.start();

        }
    }
}

# The-Dining-Philosophers
Solução do jantar dos filósofos em java.

No programa, a mesa de jantar é representada por um conjunto de caracteres.

- " | " representa 1 garfo.
- " - " representa um filósofo sem garfos.
- " = " representa o filósofo com 1 garfo
= " o " representa o filósofo com 2 garfos

Existem 5 threads que representam os 5 filósofos. 

Cada thread faz uma pausa por algum tempo antes de tentar sincronizar com o lock associado ao garfo do lado esquerdo.

Depois da primeira sincronização, a thread vai pegar o garfo do lado esquerdo e vai tentar sincronizar com o lock que está associado ao garfo do lado direito.

Depois da  segunda sincronização, a thread vai pegar o garfo do lado direito e irá segurar os dois garfos por um momento. 

Por fim, a thread vai devolver os dois garfos e liberar os dois locks.
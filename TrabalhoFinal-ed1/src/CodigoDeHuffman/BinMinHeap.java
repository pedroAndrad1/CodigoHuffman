package CodigoDeHuffman;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class BinMinHeap
{
	private int n; /* Numero de elementos no heap */
	private int tam; /* Tamanho do heap */
	private	Arvbin[] vetor; /* Vetor com elementos */
	private ArrayList<Character> simbolos = new ArrayList<>(); //Elementos inseridos na Heap
	
	/* Constrói heap vazio a partir dos elementos (caracteres). */
	public BinMinHeap(int tamanho)
	{
		vetor = new Arvbin[tamanho + 1]; //o +1 é pra construir um vetor no qual eu posso desconsiderar o zero e ter os elementos
										 //desejados.
		tam = tamanho;
	}
	
	/* Testa se a heap está vazia. */
	public boolean vazia()
	{
		if( n == 0)
			return  true;
		else
			return false;
	}
	
	/* Refaz a condição de heap (heapify). */
	private void refaz(int i)
	{	
		Arvbin arvX = vetor[i];
		int x = vetor[i].getFrequencia();
		
		
		while(2*i <= n)
		{
			int filhoEsq, filhoDir, menorFilho;
			
			filhoEsq = 2*i;
			filhoDir = 2*i + 1;
			
			/* Por enquanto, o menor filho é o da esquerda. */
			menorFilho = filhoEsq;
			
			/* Verifica se o filho direito existe e, caso positivo, verifica
			 * se é menor que o filho esquerdo. */
			if((filhoEsq != n) && (vetor[ filhoDir ].getFrequencia() < vetor[ filhoEsq ].getFrequencia() ))
				menorFilho = filhoDir;

			if(vetor[ menorFilho ].getFrequencia() < x)
				vetor [ i ] = vetor[ menorFilho ];
			else
				break;
			
			/* Como o elemento x que estava na posição "i" desceu para o nível de baixo, a próxima
			 * iteração vai verificar a possibilidade de trocar esse elemento x (agora na 
			 * posição "menorFilho") com um de seus novos filhos. */
			i = menorFilho;
		}
		
		vetor[ i ] = arvX;
	}

	/* Constrói a heap (build heap). */
	private void constroiHeap()
	{
		for( int i = n / 2; i > 0; i-- )
			refaz(i);
	}

	/* Lê dados via teclado e insere na heap. */
	public void carregaDados()
	{
		Scanner sc = new Scanner(System.in);
		
		int quant = this.tam;
		
		char simbolo;
		int frequencia;
		
		
		for(int i = 0; i < quant; i++) {
			System.out.println("Digite o símbolo (Se for passado mais de uma letra, apenas a primeira letra será considerada");
			simbolo = sc.nextLine().charAt(0);
			System.out.println("Digite a frequência");
			frequencia = Integer.parseInt(sc.nextLine());
			
			//simbolos.add(simbolo);
			
			Arvbin arv = new Arvbin(simbolo,frequencia);
			
			this.insere(arv);
			
		}
		
		sc.close();
	}

	/* Executa o algoritmo de Huffman. */
	public void aplicaHuffman()
	{	
		//O código termina quando tiver apenas um elemento na heap.
		while(this.n > 1) {
			
			Arvbin min1 = this.removeMin();//Remove o primeiro caracter com menos frequencia.
			Arvbin min2 = this.removeMin();//Remove o segundo caracter com menos frequencia.
			
			/*
			 * Cria o novo no com as frequencias somadas e coloca min1 e min2 como seus filhos.
			 */
			Arvbin nova = new Arvbin(' ',min1.getFrequencia() + min2.getFrequencia(), min1, min2);
			
			this.insere(nova);
			
		}
		//No final, restará apenas um elemento na heap e esse element será a raiz da árvore de Huffman.
	}
	
	/* Chama o método mostra códigos da classe Arvbin. */
	
	public void mostraCodigos() {
		vetor[1].mostraCodigo();
	}
	/*public void mostraCodigos()
	{	
		Map<Character, String> tabela = vetor[1].mostraCodigo();
		
		for(int i = 0; i < simbolos.size(); i++) {
			System.out.println("Símbolo: " + simbolos.get(i) + "           Decodificação: " + tabela.get(simbolos.get(i)));
		}
	}*/
	/* Insere x na heap. */
	public void insere(Arvbin x)
	{
		if (tam == n){
			throw new RuntimeException("Fila cheia");
		}

		/* O elemento é inicialmente inserido na primeira posição disponível
		 * na árvore, considerando de cima para baixo, da esquerda para a direita. */
		n++;
		int pos = n;
		
		/* Sentinela utilizada para tratar o caso do pai do nó raiz (de índice 1). */
		vetor[0] = x;

		/* Refaz heap (sobe elemento). */
		while(x.getFrequencia() < vetor[pos/2].getFrequencia()){
			vetor[pos] = vetor[ pos/2 ];
			pos /= 2;
		}
		
		vetor[pos] = x;
	}
	
	/* Remove e retorna o menor item. */
	public Arvbin removeMin()
	{
		Arvbin min;
			
			if(this.vazia()){
				throw new RuntimeException("A arvore está vazia");
			}
	
			min = vetor[1];
			vetor[1] = vetor[n]; //Tenho certeza que terá um elemento no índice n pois a heap foi completamento preencheida no
								 //metodo carregaCodigo().
			n--;
			refaz(1);
			
			return min;
	}
	
	/* Imprime o conteúdo da heap. */
	public void imprime()
	{
		vetor[1].mostra();
		
	}
	
}

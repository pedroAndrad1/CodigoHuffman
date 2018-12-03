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
	
	/* Constr�i heap vazio a partir dos elementos (caracteres). */
	public BinMinHeap(int tamanho)
	{
		vetor = new Arvbin[tamanho + 1]; //o +1 � pra construir um vetor no qual eu posso desconsiderar o zero e ter os elementos
										 //desejados.
		tam = tamanho;
	}
	/**
	 * Teste e so n�mero de elmentos da Heap � igual a zero. Se sim, a Heap est� vazia.
	 * @return
	 */
	/* Testa se a heap est� vazia. */
	public boolean vazia()
	{
		if( n == 0)
			return  true;
		else
			return false;
	}
	/**
	 * Reogarniza os elementos de uma Heap para manter sua estrutura. Este m�todo � chamado assim que � removido o menor valor 
	 * contido na Heap.
	 * @param i
	 */
	/* Refaz a condi��o de heap (heapify). */
	private void refaz(int i)
	{	
		Arvbin arvX = vetor[i];
		int x = vetor[i].getFrequencia();
		
		
		while(2*i <= n)
		{
			int filhoEsq, filhoDir, menorFilho;
			
			filhoEsq = 2*i;
			filhoDir = 2*i + 1;
			
			/* Por enquanto, o menor filho � o da esquerda. */
			menorFilho = filhoEsq;
			
			/* Verifica se o filho direito existe e, caso positivo, verifica
			 * se � menor que o filho esquerdo. */
			if((filhoEsq != n) && (vetor[ filhoDir ].getFrequencia() < vetor[ filhoEsq ].getFrequencia() ))
				menorFilho = filhoDir;

			if(vetor[ menorFilho ].getFrequencia() < x)
				vetor [ i ] = vetor[ menorFilho ];
			else
				break;
			
			/* Como o elemento x que estava na posi��o "i" desceu para o n�vel de baixo, a pr�xima
			 * itera��o vai verificar a possibilidade de trocar esse elemento x (agora na 
			 * posi��o "menorFilho") com um de seus novos filhos. */
			i = menorFilho;
		}
		
		vetor[ i ] = arvX;
	}

	/* Constr�i a heap (build heap). */
	private void constroiHeap()
	{
		for( int i = n / 2; i > 0; i-- )
			refaz(i);
	}
	/**
	 * Uma interface com o us�rio para ele inserir os elementos da Heap, suas frequ�ncias e seus simbolos. Esse m�todo se
	 * certica que a Heap ser� totalmente preechida.
	 */
	/* L� dados via teclado e insere na heap. */
	public void carregaDados()
	{
		Scanner sc = new Scanner(System.in);
		
		int quant = this.tam;
		
		char simbolo;
		int frequencia;
		
		
		for(int i = 0; i < quant; i++) {
			System.out.println("Digite o s�mbolo (Se for passado mais de uma letra, apenas a primeira letra ser� considerada");
			simbolo = sc.nextLine().charAt(0);
			System.out.println("Digite a frequ�ncia");
			frequencia = Integer.parseInt(sc.nextLine());
			
			//simbolos.add(simbolo);
			
			Arvbin arv = new Arvbin(simbolo,frequencia);
			
			this.insere(arv);
			
		}
		
		sc.close();
	}

	/* Executa o algoritmo de Huffman. */
	/**
	 * Aplica o algoritmo de Huffman aos elementos contidos na Heap. Ao final deste m�todo, a Heap conter� apenas um elemento
	 * e este elemento ser� a raiz da �rovore de Huffman. A qual usaremos para achar o c�digo de cada elemento originalmente 
	 * inserido na Heap..
	 */
	public void aplicaHuffman()
	{	
		//O c�digo termina quando tiver apenas um elemento na heap.
		while(this.n > 1) {
			
			Arvbin min1 = this.removeMin();//Remove o primeiro caracter com menos frequencia.
			Arvbin min2 = this.removeMin();//Remove o segundo caracter com menos frequencia.
			
			/*
			 * Cria o novo no com as frequencias somadas e coloca min1 e min2 como seus filhos.
			 */
			Arvbin nova = new Arvbin(' ',min1.getFrequencia() + min2.getFrequencia(), min1, min2);
			
			this.insere(nova);
			
		}
		//No final, restar� apenas um elemento na heap e esse element ser� a raiz da �rvore de Huffman.
	}
	
	/* Chama o m�todo mostra c�digos da classe Arvbin. */
	/**
	 * Mostra o c�digo dos elementos contidos na Heap. Chamando a fun��o de decodifica��o da raiz da �rvore de Huffman.
	 */
	public void mostraCodigos() {
		vetor[1].mostraCodigo();
	}
	/* Insere x na heap. */
	/**
	 * Insere um elemento na Heap e reorganiza os elementos para manter a estrutura.
	 * @param x
	 */
	public void insere(Arvbin x)
	{
		if (tam == n){
			throw new RuntimeException("Fila cheia");
		}

		/* O elemento � inicialmente inserido na primeira posi��o dispon�vel
		 * na �rvore, considerando de cima para baixo, da esquerda para a direita. */
		n++;
		int pos = n;
		
		/* Sentinela utilizada para tratar o caso do pai do n� raiz (de �ndice 1). */
		vetor[0] = x;

		/* Refaz heap (sobe elemento). */
		while(x.getFrequencia() < vetor[pos/2].getFrequencia()){
			vetor[pos] = vetor[ pos/2 ];
			pos /= 2;
		}
		
		vetor[pos] = x;
	}
	
	/* Remove e retorna o menor item. */
	/**
	 * Remove o menor elemento da Heap, que por constru��o ser� sempre o primeiro, e chama o m�todo refaz() para reorganizar os
	 * elementos e manter a estrutura da Heap.
	 * 
	 * @return Arvbin
	 */
	public Arvbin removeMin()
	{
		Arvbin min;
			
			if(this.vazia()){
				throw new RuntimeException("A arvore est� vazia");
			}
	
			min = vetor[1];
			vetor[1] = vetor[n]; //Tenho certeza que ter� um elemento no �ndice n pois a heap foi completamento preencheida no
								 //metodo carregaCodigo().
			n--;
			refaz(1);
			
			return min;
	}
	
	/* Imprime o conte�do da heap. */
	/**
	 * Imprime o cont�udo da Heap no formato da �rvore a qual ela representa.
	 */
	public void imprime()
	{
		vetor[1].mostra();
		
	}
	
}

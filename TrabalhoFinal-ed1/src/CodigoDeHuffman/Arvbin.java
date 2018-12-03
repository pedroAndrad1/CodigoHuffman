package CodigoDeHuffman;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/* Nessa implementa��o, os conceitos de "n�" e "�rvore" se misturam. */
public class Arvbin
{
	private char simbolo; /* S�mbolo armazenado na raiz. */
	private int frequencia; /* Frequ�ncia do s�mbolo armazenado na raiz. */
	private Arvbin esq, dir; /* Refer�ncias para sub�rvores esquerda e direita. */
	
	/* Construtor de �rvore sem sub�rvores (dir = esq = null). S�o fornecidos
	 * apenas o s�mbolo e a frequ�ncia da raiz. */
	public Arvbin(char simbolo, int frequencia)
	{
		this.simbolo = simbolo;
		this.frequencia = frequencia;
	}
	
	/* Construtor de �rvore com sub�rvores. Al�m de s�mbolo e frequ�ncia da raiz,
	 * s�o fornecidas as sub�rvores, que devem ter sido constru�das previamente. */
	public Arvbin(char simbolo, int frequencia, Arvbin esq, Arvbin dir)
	{
		this.simbolo = simbolo;
		this.frequencia = frequencia;
		this.esq = esq;
		this.dir = dir;
	}
	
	/* Imprime o conte�do da �rvore em pr�-ordem. */
	/**
	 * Imprime o conte�do da �rovore em pr�-ordem
	 */
	public void mostra()
	{
		System.out.print("( " + this.frequencia + " " + this.simbolo);
		
		if(this.esq != null) 
			this.esq.mostra();
		
		if(this.dir != null)
			this.dir.mostra();
		
		System.out.print(")");
	}
	/* Novo m�todo para imprimir os c�digos de Huffman de cada s�mbolo na �rvore. */
	/**
	 * Chama o m�todo recursivo chama c�digos. Que � privado e inv�sivel ao us�rio, j� que ele n�o deve saber dessa Classe.
	 * Tamb�m passa a uma String para costruir os c�digos de Huffman na recurs�o.
	 */
	public void mostraCodigo() {
		mostraCodigos("");
	}
	/**
	 * Recebe uma String e cada passo recursivo concatena 0 ou 1 a String que representa o c�digo.
	 * Quando a recurs�o chega numa folha, � impresso o simbolo contido na folha e seu c�digo.
	 * @param codigo
	 */
	private void mostraCodigos(String codigo) {
		
		if(esq == null && dir == null) {
			System.out.println("S�mbolo: " + this.simbolo + "            C�digo: " + codigo);
		}
		
		if(esq != null)
			esq.mostraCodigos(codigo + "0");
		
		if(dir != null)
			dir.mostraCodigos(codigo + "1");
	}
	/**
	 * Verifica se o arvore � uma folha, true para sim e false para n�o.
	 * @return
	 */
	private boolean folha() {
		
		if(esq == null && dir == null) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Retorna o atributo frequencia 
	 * @return
	 */
	public int getFrequencia() {
		return frequencia;
	}
	/**
	 * Retorna o atributo simbolo.
	 * @return
	 */
	public char getSimbolo() {
		return simbolo;
	}
	/* Caso necess�rio, o grupo pode definir novos m�todos. */
}
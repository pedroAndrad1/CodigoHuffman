package CodigoDeHuffman;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/* Nessa implementação, os conceitos de "nó" e "árvore" se misturam. */
public class Arvbin
{
	private char simbolo; /* Símbolo armazenado na raiz. */
	private int frequencia; /* Frequência do símbolo armazenado na raiz. */
	private Arvbin esq, dir; /* Referências para subárvores esquerda e direita. */
	
	/* Construtor de árvore sem subárvores (dir = esq = null). São fornecidos
	 * apenas o símbolo e a frequência da raiz. */
	public Arvbin(char simbolo, int frequencia)
	{
		this.simbolo = simbolo;
		this.frequencia = frequencia;
	}
	
	/* Construtor de árvore com subárvores. Além de símbolo e frequência da raiz,
	 * são fornecidas as subárvores, que devem ter sido construídas previamente. */
	public Arvbin(char simbolo, int frequencia, Arvbin esq, Arvbin dir)
	{
		this.simbolo = simbolo;
		this.frequencia = frequencia;
		this.esq = esq;
		this.dir = dir;
	}
	
	/* Imprime o conteúdo da árvore em pré-ordem. */
	/**
	 * Imprime o conteúdo da árovore em pré-ordem
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
	/* Novo método para imprimir os códigos de Huffman de cada símbolo na árvore. */
	/**
	 * Chama o método recursivo chama códigos. Que é privado e invísivel ao usário, já que ele não deve saber dessa Classe.
	 * Também passa a uma String para costruir os códigos de Huffman na recursão.
	 */
	public void mostraCodigo() {
		mostraCodigos("");
	}
	/**
	 * Recebe uma String e cada passo recursivo concatena 0 ou 1 a String que representa o código.
	 * Quando a recursão chega numa folha, é impresso o simbolo contido na folha e seu código.
	 * @param codigo
	 */
	private void mostraCodigos(String codigo) {
		
		if(esq == null && dir == null) {
			System.out.println("Símbolo: " + this.simbolo + "            Código: " + codigo);
		}
		
		if(esq != null)
			esq.mostraCodigos(codigo + "0");
		
		if(dir != null)
			dir.mostraCodigos(codigo + "1");
	}
	/**
	 * Verifica se o arvore é uma folha, true para sim e false para não.
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
	/* Caso necessário, o grupo pode definir novos métodos. */
}
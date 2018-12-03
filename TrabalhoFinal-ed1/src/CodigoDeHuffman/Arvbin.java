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
	public void mostraCodigo() {
		mostraCodigos(this,"");//Passando o nó raiz como parâmetro.
	}
	private void mostraCodigos(Arvbin no, String codigo) {
		
		if(esq == null && dir == null) {
			System.out.println("Símbolo: " + no.simbolo + "            Código: " + codigo);
		}
		
		char c = no.simbolo;
		
		if(esq != null)
			mostraCodigos(this.esq, codigo + "0");
		
		if(dir != null)
			mostraCodigos(this.dir, codigo + "1");
	}
	
	/*public Map<Character,String> mostraCodigo()
	{
		return criaTabela(this);
	}/
	
	private void preencheTabela(Map<Character, String> tabela,String codigo) {
		if(this.folha()) {
			tabela.put(this.simbolo, codigo);
		}
		if(esq != null)
			esq.preencheTabela(tabela, codigo + "0");
		
		if(dir != null)
			dir.preencheTabela(tabela, codigo + "1");
	}
	
	private Map<Character, String> criaTabela(Arvbin raiz){
		Map<Character, String> resultado = new TreeMap();
		
		raiz.preencheTabela(resultado, "");
		
		return resultado;
		
	} */
	private boolean folha() {
		
		if(esq == null && dir == null) {
			return true;
		}
		else {
			return false;
		}
	}
	public int getFrequencia() {
		return frequencia;
	}
	public char getSimbolo() {
		return simbolo;
	}
	public Arvbin getEsq() {
		return esq;
	}
	public Arvbin getDir() {
		return dir;
	}
	/* Caso necessário, o grupo pode definir novos métodos. */
}
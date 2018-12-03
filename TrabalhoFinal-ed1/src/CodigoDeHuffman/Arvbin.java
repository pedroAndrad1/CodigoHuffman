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
	public void mostraCodigo() {
		mostraCodigos("");
	}
	private void mostraCodigos(String codigo) {
		
		if(esq == null && dir == null) {
			System.out.println("S�mbolo: " + this.simbolo + "            C�digo: " + codigo);
		}
		
		if(esq != null)
			esq.mostraCodigos(codigo + "0");
		
		if(dir != null)
			dir.mostraCodigos(codigo + "1");
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
	/* Caso necess�rio, o grupo pode definir novos m�todos. */
}
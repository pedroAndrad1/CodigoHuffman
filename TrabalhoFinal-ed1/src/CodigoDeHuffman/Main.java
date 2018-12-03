package CodigoDeHuffman;

import java.util.Scanner;

public class Main
{
	public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);
		int n;
		
		System.out.println("Quantos simbolos?");
		n = scanner.nextInt();
		
		BinMinHeap heap = new BinMinHeap(n);
		heap.carregaDados();
		heap.aplicaHuffman();
		heap.imprime();
		System.out.println("");
		heap.mostraCodigos();
		
		
		scanner.close();
	}
}
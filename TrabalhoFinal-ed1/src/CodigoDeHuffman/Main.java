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
		long t0 = System.currentTimeMillis();
		heap.aplicaHuffman();
		heap.imprime();
		System.out.println("");
		heap.mostraCodigos();
		
	
	
	System.out.println("tempo =  " + (System.currentTimeMillis() - t0)) ;
		scanner.close();
	}
}
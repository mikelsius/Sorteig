package net.miguelgarcia.Sorteig;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
	public static final int MAXJUGADORS = 11;
	
    public static void main( String[] args )
    {
		Scanner lector = new Scanner(System.in);
		int numjugadors = 0;
		boolean esEnter = false;
		while (!esEnter) {
			System.out.println("Quants jugadors sereu?");
			if (lector.hasNextInt()) {
				numjugadors = lector.nextInt();
				if (0 < numjugadors && numjugadors < MAXJUGADORS) {
					esEnter = true;
				} else {
					System.out.println("ha de ser un numero entre 1 i 10");
				}
			} else {
				lector.next();
			}
		}
		Joc joc1 = new Joc(numjugadors);
		while (!joc1.shaAcabat()) {
			joc1.jugarMa();
		}
    }
}

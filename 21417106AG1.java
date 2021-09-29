/*
 * GRUPO 1:
 * Carlos moreno silvestre
 * Mohamed Oussama Farhat
 * Eddy Lucandy Z. S. Alexandre
 * Daniel Gutiérrez Torres
 */

import java.util.concurrent.Semaphore;

public class PhilosopherProblem {
	
	//poner un semaforo mutuamente excluyente para que solo 4 filosofos pueden comer y se queda uno
	public static void main(String[] args) {
		
		Fork forks = new Fork();
		
		Philosoper p0 = new Philosoper(forks,0);
		Philosoper p1 = new Philosoper(forks,1);
		Philosoper p2 = new Philosoper(forks,2);
		Philosoper p3 = new Philosoper(forks,3);
		Philosoper p4 = new Philosoper(forks,4);
		
		p0.start();
		p1.start();
		p2.start();
		p3.start();
		p4.start();
	}
		
}
	
	class Fork{
		private Semaphore[] forks = new Semaphore[] {
		new Semaphore(1),new Semaphore(1),new Semaphore(1),new Semaphore(1),new Semaphore(1)};		
		
		// la tecnica de recoger el tenedor
		public void get(int fork_no){
		try {
			forks[fork_no].acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
		
		// la tecnica de bajar el tenedor
		public void put(int fork_no) {
			forks[fork_no].release();
		}
		
		// Obtener el número de bifurcaciones
		public int getForkCount() {
			return forks.length;
		}
	 }		
		class Philosoper extends Thread{
			private int no;
			private Fork forks;
			public Philosoper(Fork forks, int no) {
				this.no = no;
				this.forks = forks;
			}
			public void run() {
				while(true) {
					
					
					//  ir pensando en el problema
					System.out.println("filósofo"+no+"Pensando en un problema ...");
					//comer
					int left = no;
					int right = (no+1)%forks.getForkCount();
					
					// Si el número de la bifurcación derecha es mayor que el número de la bifurcación izquierda , intercambia el orden 
					//(num bifurcación der > num bifurcación izq ==>se intercambia el orden)
					if(right > left) {
						int temp = left;
						left = right;
						right = temp;
					}
			/*
			 * LAS ETAPAS PARA EMPEZAR A COMER
			 */
					// 1. coger la bifurcación izquierda
					forks.get(left);
					System.out.println("\t \t \tfilósofo"+no+"Toma el tenedor de la izquierda");
					
					// 2. Coger la bifurcación correcta
					forks.get(right);
					System.out.println("\t\t \tfilósofo"+no+"Toma la bifurcación correcta");
					
					// 3. Comer
					System.out.println("\t\t\t\t\t\t"+"filósofo"+no+"comiendo...");
					
					// 4. Bajar la bifurcación izquierda
					forks.put(left);
					System.out.println("\t \t \tfilósofo"+no+"Baja la bifurcación izquierda");
					
					// 5. Colocar la bifurcación derecha
					forks.put(right);
					System.out.println("\t \t \tfilósofo"+no+"Baje la bifurcación derecha");
					
					/*
					PROBLEMA RESUELTO AL FINAL
				*/
				}
			}
			
		}


package juego;

import java.awt.*;

import entorno.Entorno;

public class Pisos { 
	private Rectangle pisoA;
	private Rectangle pisoB; 
	private Color c;
	private int[] pisos;
	private int piso;

	public Pisos() {	
		this.pisoA = new Rectangle(350, 0, 700, 10);
		this.pisoB = new Rectangle(450, 0, 700, 10);
		this.c = Color.gray;
		this.pisos = new int[] { 600, 500, 400, 300, 200, 100 };
		this.piso = 5;
	}

	public void DibujarPisos(Entorno e) {		//DIBUJA LOS PISOS
		for (int i = 1; i < pisos.length; i++) {
			if (i % 2 == 0)
				e.dibujarRectangulo(pisoB.x, pisos[i], pisoB.width, pisoB.height, 0, c);
			else
				e.dibujarRectangulo(pisoA.x, pisos[i], pisoA.width, pisoA.height, 0, c);
		}
	}

	public int PisoActual(int y, int piso) {		//RETORNA EL PISO ACTUAL
		if (piso == 0) {
			return 0;
		} else if (y > pisos[piso]) {
			return PisoActual(y, piso - 1);
		}
		return piso;

	}

	public int TopeBajar(int yPersonaje, int xPersonaje, int ancho, int alto) {  //DEFINE EL TOPE MINIMO DE CADA PISO
		if (PisoActual(yPersonaje, piso) == 0)
			return pisos[0] - (alto / 2);
		if (PisoActual(yPersonaje, piso) % 2 == 0) {
			if (xPersonaje + ancho / 2 < pisoB.x - pisoB.width / 2) {
				return (pisos[PisoActual(yPersonaje, piso)] - (alto / 2) - (pisoA.height / 2) + 100);
			}
		} else {
			if (xPersonaje - ancho / 2 > pisoA.x + pisoA.width / 2) {
				return (pisos[PisoActual(yPersonaje, piso)] - (alto / 2) - (pisoA.height / 2) + 100);
			}
		}
		return (pisos[PisoActual(yPersonaje, piso)] - (alto / 2) - (pisoA.height / 2));
	}

	public int TopeSubir(int yPersonaje, int xPersonaje, int ancho, int alto) { // DEFINE EL TOPE MAXIMO DE CADA PISO
		if (PisoActual(yPersonaje, piso) == 5)
			return pisos[5] + (alto / 2) - 100;
		if (PisoActual(yPersonaje, piso) % 2 == 0) {
			if (xPersonaje - ancho / 2 > pisoA.x + pisoA.width / 2) {
				return (pisos[PisoActual(yPersonaje, piso)] + (alto / 2) + (pisoA.height / 2) - 200);
			}
		} else {
			if (xPersonaje + ancho / 2 < pisoB.x - pisoB.width / 2) {
				return (pisos[PisoActual(yPersonaje, piso)] + (alto / 2) + (pisoA.height / 2) - 200);
			}
		}
		return (pisos[PisoActual(yPersonaje, piso)] + (alto / 2) + (pisoA.height / 2) - 100);
	}

}
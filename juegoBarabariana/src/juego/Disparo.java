package juego;

import java.awt.Rectangle;

public class Disparo {
	Rectangle cuerpo;
	int velocidad;
	int limiteDisparo;

	Disparo(int x, int y, int ancho, int alto, int velocidad, int limiteDisparo) {
		this.cuerpo = new Rectangle(x, y, ancho, alto);
		this.velocidad = velocidad;
		this.limiteDisparo = limiteDisparo;

	}

	public Rectangle cuerpoDisparo() {		//DEVUELVE EL CUERPO DEL DISPARO

		return this.cuerpo;

	}

	public void recorridoDisparo() {		// INCREMENTA O DECREMENTA EN X

		this.cuerpo.translate(this.velocidad, 0);

	}

	public boolean colisiona(Velociraptor otro) {		//DETECTA SI HAY COLISION ENTRE EL DISPARO Y VELOCIRAPTOR
		boolean sinColisionArriba = this.cuerpo.y - this.cuerpo.height / 2 > otro.getY() + otro.getAltura() / 2;
		boolean sinColisionAbajo = this.cuerpo.y + this.cuerpo.height / 2 < otro.getY() - otro.getAltura() / 2;
		boolean sinColisionIzquierda = this.cuerpo.x - this.cuerpo.width / 2 > otro.getX() + otro.getAncho() / 2;
		boolean sinColisionDerecha = this.cuerpo.x + this.cuerpo.width / 2 < otro.getX() - otro.getAncho() / 2;

		return !(sinColisionArriba || sinColisionAbajo || sinColisionDerecha || sinColisionIzquierda);
	}

}
package juego;

import java.awt.*;

import entorno.Entorno;
import entorno.Herramientas;

public class Barbariana {
	private int x;
	private int y;
	private int alto;
	private int ancho;
	private Pisos pisos;
	private boolean direccionD;
	private boolean barbEstaAgachada;

	boolean bajando;
	boolean subiendo;
	private int limiteUp;
	private int limiteUpU;
	int numeroPiso;

	Image barbDerecha;
	Image barbIzquierda;
	Image martillo;
	Image martilloInvertido;
	Image barbAgachadaDerecha;
	Image barbAgachadaIzquierda;

	public Barbariana() {
		this.alto = 70;
		this.ancho = 26;
		this.x = ancho / 2;
		this.y = 600 - alto / 2;
		pisos = new Pisos();
		this.direccionD = true;
		this.barbEstaAgachada = false;
		barbDerecha = Herramientas.cargarImagen("barb_derecha1.png");
		barbIzquierda = Herramientas.cargarImagen("barb_izquierda1.png");
		barbAgachadaDerecha = Herramientas.cargarImagen("barbAgachadaDerecha.png");
		barbAgachadaIzquierda = Herramientas.cargarImagen("barbAgachadaIzquierda.png");
		martillo = Herramientas.cargarImagen("thor.png");
		martilloInvertido = Herramientas.cargarImagen("thor2.png");

	}

	public void empezarSalto() { // EMPIEZA EL SALTO

		this.subiendo = true;
		this.bajando = false;

	}

	public boolean tierra() { // COMPRUEBA CUANDO BARBARIANA ESTA EN EL PISO
		if (this.y == pisos.TopeBajar(y, x, ancho, alto)) {
			return true;
		}

		else
			return false;
	}

	public int piso() { // Usado para limiteSubida()

		this.numeroPiso = 5;

		if (pisos.TopeBajar(y, x, ancho, alto) == 575) {
			this.numeroPiso = 5;
		}
		if (pisos.TopeBajar(y, x, ancho, alto) == 470) {
			this.numeroPiso = 4;
		}
		if (pisos.TopeBajar(y, x, ancho, alto) == 370) {
			this.numeroPiso = 3;
		}
		if (pisos.TopeBajar(y, x, ancho, alto) == 270) {
			this.numeroPiso = 2;
		}
		if (pisos.TopeBajar(y, x, ancho, alto) == 170) {
			this.numeroPiso = 1;
		}
		if (pisos.TopeBajar(y, x, ancho, alto) == 70) {
			this.numeroPiso = 0;
		}

		return numeroPiso;

	}

	private int limiteSubida() { // OBTIENE LOS DISTINTOS LIMITES SEGUN EN QUE PISO ESTA
		if (this.piso() == 5)
			this.limiteUp = 530;
		if (this.piso() == 4)
			this.limiteUp = 430;
		if (this.piso() == 3)
			this.limiteUp = 330;
		if (this.piso() == 2)
			this.limiteUp = 230;
		if (this.piso() == 1)
			this.limiteUp = 130;
		if (this.piso() == 0)
			this.limiteUp = 25;

		return this.limiteUp;
	}

	public boolean alLimite(boolean letra) { // Si esta al limite ("Techo" o limite de capacidad de salto), baja

		if (!letra && this.y < this.limiteSubida() || this.y == pisos.TopeSubir(y, x, ancho, alto)) {
			this.subiendo = false;
			this.bajando = true;
			return true;
		} else if (letra && this.y < this.limiteSubidaU() || this.y == pisos.TopeSubir(y, x, ancho, alto)) {
			this.subiendo = false;
			this.bajando = true;
		}
		return false;
	}

	public void corrector() { // Cuando vuelve al piso, bajando vuelve a ser false
		this.bajando = false;
	}

	public boolean bajoHueco() { // COMPRUEBA CUANDO ESTA BAJO EL HUECO
		if (pisos.TopeSubir(y, x, ancho, alto) < this.limiteSubida())
			return true;
		else
			return false;
	}

	private int limiteSubidaU() { // DEPENDE EL PISO EN EL QUE ESTA BARBARIANA EL LIMITE DE SUBIDA VA A SER EL
									// TECHO QUE TENGA ARRIBA

		if (this.piso() == 5)
			this.limiteUpU = 530;
		if (this.piso() == 4)
			this.limiteUpU = 430;
		if (this.piso() == 3)
			this.limiteUpU = 330;
		if (this.piso() == 2)
			this.limiteUpU = 230;
		if (this.piso() == 1)
			this.limiteUpU = 130;
		if (this.piso() == 0)
			this.limiteUpU = 25;

		return this.limiteUpU - 70;
	}

	public Disparo Disparo() { // CREA Y RETORNA OBJETOS DE TIPO "Disparo"
		int velocidad;
		int direccion;
		int limiteDisparo = 5;
		if (this.direccionD) {
			velocidad = 15;
			direccion = this.ancho + 15;
		} else {
			velocidad = -15;
			direccion = -this.ancho - 15;
		}
		return new Disparo(this.getX() + direccion, this.getY() - 13, 30, 3, velocidad, limiteDisparo);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public void moverDerecha() { // VELOCIDAD Y TOPES DE LOS LATERAL DERECHO DE LA PANTALLA
		x = x + 4;
		direccionD = true;
		if (x > 800 - (ancho / 2)) {
			x = 800 - (ancho / 2);

		}

	}

	public void moverIzquierda() { // VELOCIDAD Y TOPES DE LOS LATERAL IZQUIERDO DE LA PANTALLA
		x = x - 4;
		direccionD = false;
		if (x < ancho / 2) {
			x = ancho / 2;
		}
	}

	public void moverArriba() { // VELOCIDAD Y TOPES CON LOS QUE BARBARIANA SUBE DE PISO
		y = y - 5;
		if (y < pisos.TopeSubir(y, x, ancho, alto)) {
			y = pisos.TopeSubir(y, x, ancho, alto);
		}
	}

	public void bajar() { // VELOCIDAD Y TOPES CON LOS QUE BARBARIANA BAJA DE PISO
		y = y + 5;
		if (y > pisos.TopeBajar(y, x, ancho, alto)) {
			y = pisos.TopeBajar(y, x, ancho, alto);
		}
	}

	public void agacharse() { // MODIFICA EL ALTO CUANDO BARBARIANA SE AGACHA
		alto = 20;
		this.barbEstaAgachada = true;

	}

	public void levantarse() { // MODIFICA EL ALTO CUANDO BARBARIANA SE LEVANTA

		alto = 50;
		this.barbEstaAgachada = false;

	}

	public void Dibujar(Entorno e) { // DIBUJA A BARBARIANA Y EL MARTILLO EN SUS DIFERENTES POSICIONES

		if (this.direccionD && !barbEstaAgachada) {
			e.dibujarImagen(martillo, getX() + 15, getY()-12, getAlto());
			e.dibujarImagen(barbDerecha, x, y, 0, 0.21);
		} 
		if (!this.direccionD && !barbEstaAgachada) {
			e.dibujarImagen(martilloInvertido, getX() - 15, getY() - 12, 0.3);
			e.dibujarImagen(barbIzquierda, x, y, 0, 0.21);
		}
		if (barbEstaAgachada && this.direccionD) {
			e.dibujarImagen(barbAgachadaDerecha, x, y, 0, 0.21);
			e.dibujarImagen(martillo, getX() + 15, getY() , 0);
		}
		if (barbEstaAgachada && !this.direccionD) {
			e.dibujarImagen(barbAgachadaIzquierda, x, y, 0, 0.21);
			e.dibujarImagen(martilloInvertido, getX() - 15, getY(), 0);

		}

	}

	public boolean colisiona(Item otro) { // CONTROLA CUANDO CARBARIANA COLISIONA CON EL ITEM
		boolean sinColisionArriba = this.y - this.alto / 2 > otro.getY() + otro.getAlto() / 2;
		boolean sinColisionAbajo = this.y + this.alto / 2 < otro.getY() - otro.getAlto() / 2;
		boolean sinColisionIzquierda = this.x - this.ancho / 2 > otro.getX() + otro.getAncho() / 2;
		boolean sinColisionDerecha = this.x + this.ancho / 2 < otro.getX() - otro.getAncho() / 2;

		return !(sinColisionArriba || sinColisionAbajo || sinColisionDerecha || sinColisionIzquierda);
	}
}
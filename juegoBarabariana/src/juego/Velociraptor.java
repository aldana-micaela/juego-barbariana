package juego;

import java.awt.*;

import entorno.Entorno;
import entorno.Herramientas;

public class Velociraptor {
	private int x;
	private int y;
	private int alto;
	private int ancho;
	private int velocidadDisparo;
	private int numero;
	private Color c;
	private Pisos pisos;
	private boolean cambiarDireccion;
	private boolean ultimoMovimientoDerecha;
	private Point[] punto;
	Image veloDerecha;
	Image veloIzquierda;

	public Velociraptor(Color color, int numVelo) {
		this.punto = new Point[] { new Point(787, 575), new Point(13, 470), new Point(787, 370), new Point(13, 270),
				new Point(787, 170), new Point(13, 70) };
		this.alto = 50;
		this.ancho = 26;
		this.x = punto[numVelo].x;
		this.y = punto[numVelo].y;
		this.numero = numVelo;
		this.c = color;
		this.cambiarDireccion = true;
		pisos = new Pisos();
		this.velocidadDisparo = 10;
		this.ultimoMovimientoDerecha = true;
		veloDerecha = Herramientas.cargarImagen("veloDerecha.png");
		veloIzquierda = Herramientas.cargarImagen("veloIzquierda.png");
	}

	public void bajar() { // BAJAN DE PISO

		y = y + 1;
		if (y > pisos.TopeBajar(y, x, ancho, alto)) {
			y = pisos.TopeBajar(y, x, ancho, alto);
		}
	}

	public void moverDerecha() { //INCREMENTA X EN 2
		x = x + 2;
		if (x > 800 - (ancho / 2)) {
			x = 800 - (ancho / 2);

		}
	}

	public void moverIzquierda() { // DECREMENTA X EN 2
		x = x - 2;
		this.ultimoMovimientoDerecha = false;
	}

	public void mover() { //MUEVEN LOS VELOCIRAPTOR EN LA DIRECCION CORRESPONDIENTE
		this.bajar();
		if (this.cambiarDireccion && this.x <= 800) {
			this.moverDerecha();
			this.ultimoMovimientoDerecha = true;
		}
		if (this.x == 787) {
			this.cambiarDireccion = false;
		}
		if (!this.cambiarDireccion && this.x <= 800) {
			this.moverIzquierda();
			this.ultimoMovimientoDerecha = false;

		}
		if (this.x == 13) {
			this.cambiarDireccion = true;
		}

	}

	public void aPiso1() { //PISO DE INICIO
		this.x = 13;
		this.y = 70;
	}

	public void Dibujar(Entorno e) {	//DIBUJA LA IMAGEN DE LOS VELOCIRAPTOR
		if (this.ultimoMovimientoDerecha) {
			e.dibujarImagen(veloDerecha, x, y - 10, 0, 1);
		} else {
			e.dibujarImagen(veloIzquierda, x, y - 10, 0, 1);
		}
	}

	public Rayolaser disparar() {	//CREA EL OBJETO DISPARO
		int velocidad = this.ultimoMovimientoDerecha ? this.velocidadDisparo : -this.velocidadDisparo;
		return new Rayolaser(this.x, this.y, 30, 5, velocidad);
	}

	public int getAltura() {
		return alto;
	}

	public int getAncho() {
		return ancho;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getNumero() {
		return numero;
	}
 
	public boolean colisiona(Barbariana otro) {	//DETECTA SI HUBO UNA COLISION ENTRE EL VELOCIRAPTOR Y BARBARIANA
		boolean sinColisionArriba = this.y - this.alto / 2 > otro.getY() + otro.getAlto() / 2;
		boolean sinColisionAbajo = this.y + this.alto / 2 < otro.getY() - otro.getAlto() / 2;
		boolean sinColisionIzquierda = this.x - this.ancho / 2 > otro.getX() + otro.getAncho() / 2;
		boolean sinColisionDerecha = this.x + this.ancho / 2 < otro.getX() - otro.getAncho() / 2;

		return !(sinColisionArriba || sinColisionAbajo || sinColisionDerecha || sinColisionIzquierda);
	}

	public boolean colisionadis(Disparo otro) { //DEECTA SI HUBO UNA COLISION ENTE EL VELOCIRAPTOR Y EL DISPARO
		boolean sinColisionArriba = this.y - this.alto / 2 > otro.cuerpoDisparo().y + otro.cuerpoDisparo().height / 2;
		boolean sinColisionAbajo = this.y + this.alto / 2 < otro.cuerpoDisparo().y - otro.cuerpoDisparo().height / 2;
		boolean sinColisionIzquierda = this.x - this.ancho / 2 > otro.cuerpoDisparo().x
				+ otro.cuerpoDisparo().width / 2;
		boolean sinColisionDerecha = this.x + this.ancho / 2 < otro.cuerpoDisparo().x - otro.cuerpoDisparo().width / 2;

		return !(sinColisionArriba || sinColisionAbajo || sinColisionDerecha || sinColisionIzquierda);

	}

}
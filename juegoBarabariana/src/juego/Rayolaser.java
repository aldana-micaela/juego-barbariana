package juego;

import java.awt.Color;

import entorno.Entorno;

public class Rayolaser {
	private double x;
	private double y;
	private double velocidad;
	private double angulo;
	private double alto;
	private double ancho;

	Rayolaser(double x, double y, double ancho, double alto, int velocidad) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.velocidad = velocidad;
		this.angulo = 0;
	}

	public void moverDisparo() {	//INCREMENTA O DECREMENTA EN X EL RAYOLASER
		this.x = this.x + velocidad;
	}

	public void dibujar(Entorno e) { // DIBUJA EL RAYOLASER
		e.dibujarRectangulo(this.x, this.y - 10, this.ancho, this.alto, this.angulo, Color.RED);

	}

	public double getAngulo() {
		return angulo;
	}

	public double getAlto() {
		return alto;
	}

	public double getAncho() {
		return ancho;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean colisiona(Barbariana otro) {	//DETECTA SI HUBO UNA COLISION CON BARBARIANA
		boolean sinColisionArriba = this.y - this.alto / 2 > otro.getY() + otro.getAlto() / 2;
		boolean sinColisionAbajo = this.y + this.alto / 2 < otro.getY() - otro.getAlto() / 2;
		boolean sinColisionIzquierda = this.x - this.ancho / 2 > otro.getX() + otro.getAncho() / 2;
		boolean sinColisionDerecha = this.x + this.ancho / 2 < otro.getX() - otro.getAncho() / 2;

		return !(sinColisionArriba || sinColisionAbajo || sinColisionDerecha || sinColisionIzquierda);
	}

}

package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Item {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	Image item;

	public Item(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.item = Herramientas.cargarImagen("item.png");
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void dibujar(Entorno e) {		//DIBUJA LA IMAGEN DEL COMPUTADOR
		e.dibujarImagen(item, x, y, 0, 0.30);
	}

}

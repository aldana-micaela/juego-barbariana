package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.LinkedList;

import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	private Entorno entorno;
	private Barbariana barb;
	private Pisos pisos;
	private LinkedList<Velociraptor> listaVelo;
	private int cont;
	LinkedList<Rayolaser> disparos;
	private boolean limite;
	private int vida;
	LinkedList<Disparo> tiros;
	boolean disparoPermitido;
	private int numeroPiso;
	private int pisoActual;
	private Item computador;
	private boolean gano;
	Clip sonidoFondo;
	String sonidoDisparo;
	boolean letra;
	int velosEliminados;
	Image fondo;

	// Variables y métodos propios de cada grupo

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Juego barbariana", 800, 600);
		// Inicializar lo que haga falta para el juego
		// ...
		cont = 0;
		this.fondo = Herramientas.cargarImagen("fondo.png");
		sonidoDisparo = "disparo.wav";
		this.sonidoFondo = Herramientas.cargarSonido("musicaFondo2.wav");
		barb = new Barbariana();
		pisos = new Pisos();
		listaVelo = new LinkedList<Velociraptor>();
		disparos = new LinkedList<Rayolaser>();
		limite = false;
		computador = new Item(400, 50, 40, 20);
		vida = 3;
		this.tiros = new LinkedList<Disparo>();
		gano = false;

		this.entorno.iniciar();
	}

	public void tick() {

		this.entorno.dibujarImagen(fondo, 400, 300, 0);
		this.entorno.cambiarFont("verdana", 15, Color.pink);
		this.entorno.escribirTexto("Vidas: " + vida, 10, 25);
		this.entorno.cambiarFont("verdana", 15, Color.CYAN);
		this.entorno.escribirTexto("Velociraptor eliminados: " + velosEliminados, 580, 25);
		sonidoFondo.loop(1);

		if (vida > 0 && !gano) {
			procesarEventos();
			VelocrearColision();
			procesarRayolaser();
			Dibujar();
			cambioPiso();
			dibujarDisparo();
			//contadorDisparo();
		} else if (vida == 0 && !gano)
			dibujarGameOver();

		else {
			dibujaFelicitaciones();
		}
	}

	private void procesarEventos() { // PROCESA LOS EVENTOS DEL TECLADO Y VERIFICA CUANDO BARBARIANA GANO EL JUEGO

		if (this.entorno.estaPresionada('d')) {
			barb.moverDerecha();
		}
		if (this.entorno.estaPresionada('a')) {
			barb.moverIzquierda();
		}

		if (this.entorno.estaPresionada('s') || this.entorno.sePresiono('s')) {
			barb.agacharse();

		} else {
			barb.levantarse();
		}

		if (this.entorno.sePresiono('w') && !barb.bajando) {
			barb.empezarSalto();
			letra = false;
		}

		if (this.entorno.sePresiono('u') && !barb.bajando && barb.bajoHueco()) {
			barb.empezarSalto();
			letra = true;
		}

		if (barb.subiendo == true) {
			barb.moverArriba();

			if (barb.alLimite(letra))
				barb.bajar();
		}

		else
			barb.bajar();

		if (barb.tierra())
			barb.corrector();

		if (entorno.sePresiono(entorno.TECLA_ESPACIO) && disparoPermitido) {
			Herramientas.play(sonidoDisparo);
			if (tiros.size() != barb.Disparo().limiteDisparo) {
				this.tiros.add(this.barb.Disparo());
			}
		}
		if (barb.colisiona(computador)) {
			gano = true;

		}

	}

	private void VelocrearColision() { // A PARTIR DE ACA SE DIBUJAN LOS VELOCIRAPTOR Y SE DEFINE EN QUE MOMENTO
										// DISPARAN.
										// Y LA COLISION DE LOS DISPAROS DE BARBARIANA CON LOS VELOCIRAPTOR
		LinkedList<Disparo> disparosAEliminar = new LinkedList<Disparo>();

		cont++;
		for (int numerosVelo = 0; numerosVelo < 6; numerosVelo++) {
			listaVelo.add(new Velociraptor(Color.BLUE, numerosVelo));
		} 
		limite = false;
		for (int numero = 0; numero < 6; numero++) {
			if (listaVelo.get(numero) != null) {
				listaVelo.get(numero).Dibujar(this.entorno);
				listaVelo.get(numero).mover();

				if (cont % 100 == 0) {
					this.disparos.add(listaVelo.get(numero).disparar());
				}
				if (listaVelo.get(numero).getY() == 575 && listaVelo.get(numero).getX() == 13) {
					listaVelo.get(numero).aPiso1();
				}
				if (listaVelo.get(numero).colisiona(barb)) {
					listaVelo.set(numero, null);
					vida--;
				}
				if (barb.Disparo().limiteDisparo <= 5)

					for (Disparo disparo : this.tiros) {
						if (listaVelo.get(numero) != null)
							if (listaVelo.get(numero).colisionadis(disparo)) {
								listaVelo.set(numero, null);
								disparosAEliminar.add(disparo);
								this.velosEliminados++;

							}
					}
				if (barb.Disparo().limiteDisparo <= 5)
					for (Disparo disparo : disparosAEliminar) {
						this.tiros.remove(disparo);

					}

			}

			else if (limite == false && cont % 200 == 0) {
				listaVelo.set(numero, new Velociraptor(Color.BLUE, numero));
				listaVelo.get(numero).aPiso1();
				limite = true;

			}

		}
	}

	private void procesarRayolaser() { // CREA LOS RAYOLASER Y CUANDO DESAPARECE DE LA PANTALLA LOS MUEVE A OTRA LISTA
										// PARA ELMINIARLOS
										// Y TAMBIEN CONTROLA CUANDO COLISIONA CON BARBARIANA PARA MOVERLOS A OTRA LISTA
										// Y ELIMINARLOS

		LinkedList<Rayolaser> disparosEliminados = new LinkedList<Rayolaser>();
		LinkedList<Rayolaser> disparosAEliminar = new LinkedList<Rayolaser>();
		for (Rayolaser rayo : this.disparos) {
			rayo.moverDisparo();
			if (rayo.getX() > 0 && rayo.getX() < 800)
				rayo.dibujar(entorno);
			else
				disparosEliminados.add(rayo);

		}

		for (Rayolaser disparo : disparosEliminados) {
			this.disparos.remove(disparo);
		}
		for (Rayolaser disparo : this.disparos)
			if (disparo.colisiona(barb)) {
				disparosAEliminar.add(disparo);
				vida--;
			}
		for (Rayolaser disparoAEliminar : disparosAEliminar) {
			this.disparos.remove(disparoAEliminar);
		}

	}

	private void contadorDisparo() { // CUENTA LA LISTA DE LOS DISPAROS

		if (tiros.size() <= barb.Disparo().limiteDisparo / 2) {
			this.entorno.cambiarFont("verdana", 15, Color.green);
			this.entorno.escribirTexto("Disparo: " + tiros.size() + "/" + barb.Disparo().limiteDisparo, 650, 25);

		}

		if (tiros.size() > barb.Disparo().limiteDisparo / 2) {
			this.entorno.cambiarFont("verdana", 15, Color.RED);
			this.entorno.escribirTexto("Disparo: " + tiros.size() + "/" + barb.Disparo().limiteDisparo, 650, 25);

		}
	}

	private int cambioPiso() { // ESTE METODO NOS DICE CUANDO BARBARIANA CAMBIA DE PISO
		this.numeroPiso = 5;

		if (pisos.TopeBajar(barb.getY(), barb.getX(), barb.getAncho(), barb.getAlto()) == 575) {
			this.numeroPiso = 5;
		}
		if (pisos.TopeBajar(barb.getY(), barb.getX(), barb.getAncho(), barb.getAlto()) == 470) {
			this.numeroPiso = 4;
		}
		if (pisos.TopeBajar(barb.getY(), barb.getX(), barb.getAncho(), barb.getAlto()) == 370) {
			this.numeroPiso = 3;
		}
		if (pisos.TopeBajar(barb.getY(), barb.getX(), barb.getAncho(), barb.getAlto()) == 270) {
			this.numeroPiso = 2;
		}
		if (pisos.TopeBajar(barb.getY(), barb.getX(), barb.getAncho(), barb.getAlto()) == 170) {
			this.numeroPiso = 1;
		}
		if (pisos.TopeBajar(barb.getY(), barb.getX(), barb.getAncho(), barb.getAlto()) == 70) {
			this.numeroPiso = 0;
		}

		return numeroPiso;
	}

	private void restaurarLista() { // ESTE METODO RESTAURA LA LISTA DE DISPAROS CUANDO DESAPARECEN DE LA PANTALLA

		for (int i = tiros.size() - 1; i >= 0; i--) {
			if (tiros.get(i).cuerpo.x > 800 || tiros.get(i).cuerpo.x < 0) {
				tiros.remove(i);
			}

		}

		disparoPermitido = true;
	}

	private void dibujarDisparo() { // ESTE METODO SE ENCARGA DE DIBUJAR LOS DISPAROS Y LIMITAR LOS DISPAROS

		if (tiros.size() <= barb.Disparo().limiteDisparo) {
			disparoPermitido = true;

			for (Disparo disparo : this.tiros) {
				this.entorno.dibujarRectangulo(disparo.cuerpoDisparo().getX(), disparo.cuerpoDisparo().getY(),
						disparo.cuerpoDisparo().getWidth(), disparo.cuerpoDisparo().getHeight(), 0, Color.MAGENTA);
				disparo.recorridoDisparo();
			}

		}
		if (tiros.size() == barb.Disparo().limiteDisparo) {
			disparoPermitido = false;
		}
		if (numeroPiso != pisoActual) {
			pisoActual = numeroPiso;
			restaurarLista();
		}

	}

	private void Dibujar() { // ESTE METODO DIBUJA A BARBARIANA, LOS PISOS Y EL ITEM
		barb.Dibujar(this.entorno);
		pisos.DibujarPisos(entorno);
		computador.dibujar(entorno);
	}

	private void dibujarGameOver() { // ESTE METODO ESCRIBE EN LA PANTALLA "PERDISTE"
		this.entorno.cambiarFont("Arial", 30, Color.red);
		this.entorno.escribirTexto("Perdiste :(", 350, 300);

	}

	private void dibujaFelicitaciones() { // ESTE METODO ESCRIBE EN LA PANTALLA "GANASTE"
		this.entorno.cambiarFont("Arial", 30, Color.red);
		this.entorno.escribirTexto("Ganaste :)", 350, 300);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
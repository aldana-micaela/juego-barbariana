# 🗡️ Barbariana - Juego tipo Mario Bros

**Barbariana** es un juego de plataformas desarrollado en Java, donde la protagonista debe recorrer un escenario, enfrentarse a enemigos y recolectar objetos hasta llegar al final para cumplir su misión. Inspirado en juegos clásicos como Super Mario Bros, este proyecto fue desarrollado con fines académicos, aplicando lógica de colisiones, sonidos y programación orientada a objetos.

## 🎮 Características

- Protagonista animada con movimientos y acciones personalizadas.
- Sistema de enemigos y objetos para recolectar.
- Verificación de colisiones y detección de impactos.
- Música de fondo y efectos de sonido (`.wav`).
- Recursos gráficos propios (`.png`) para personajes y entorno.

## 🛠️ Tecnologías utilizadas

- **Java** – Lenguaje base del proyecto.
- **Programación Orientada a Objetos (POO)** – Herencia, métodos, encapsulamiento.
- **Manejo de audio** – Sonidos de disparo, fondo y efectos usando `AudioSystem`.
- **Control de colisiones** – Lógica para interacción entre entidades.
- **Eclipse IDE** – Proyecto estructurado con carpetas estándar.

## 🧠 Tecnologías y conceptos aplicados
- Java (POO) – El código está bien estructurado en clases con uso de herencia, atributos y métodos.
- Manipulación de audio – Se usan efectos de sonido (.wav) y música de fondo con AudioSystem y Clip.
- Lógica de colisiones – Se implementa verificación de contacto entre personajes, enemigos y objetos.
- Imágenes y sprites – El juego incluye múltiples imágenes (.png) para los personajes y fondos.
- Estructura Eclipse – Incluye .classpath, .project, bin/, e src/ para usar directamente en Eclipse.

## 📂 Estructura del proyecto

```text
juego-barbariana/
│
├── src/                           # Código fuente
│   └── juego/
│       ├── Barbariana.java        # Lógica principal del personaje
│       ├── Disparo.java           # Lógica de disparos
│       └── ...
├── bin/                           # Recursos compilados e imágenes
│   ├── *.png                      # Sprites y fondos
│   ├── *.wav                      # Sonidos
│   └── entorno.jar                # Librería externa
├── .project, .classpath           # Archivos de Eclipse
└── README.md
```

## 🔧 Cosas por mejorar
- Incorporar un sistema de niveles o múltiples pantallas.
- Añadir un menú de inicio y pantalla de fin.
- Usar Thread para animaciones más fluidas.
- Migrar a una librería como JavaFX o LibGDX para mejorar gráficos.
- Agregar pruebas unitarias con JUnit para lógica de juego.

## 👩‍💻 Autoría

Aldana Micaela Filiberto, estudiante de Licenciatura en Sistemas.

---

Este proyecto fue desarrollado como parte de una actividad académica para practicar conceptos de lógica, programación de videojuegos y estructuras en Java.

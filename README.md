# ⚽ Liga Chad - Sistema de Gestión de Liga de Fútbol

Este proyecto en Java permite gestionar una liga de fútbol amateur llamada **Liga Chad**, facilitando el manejo de jugadores, equipos, partidos, estadísticas, reportes y exportación de datos.

## 📦 Estructura del Proyecto

Proyecto Java desarrollado con Maven, organizado según buenas prácticas de programación orientada a objetos.

```text
ligachad/
├── pom.xml
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── ligachad/
    │               ├── app/
    │               │   ├── Main.java
    │               │   └── MenuConsola.java
    │               └── model/
    │                   ├── Jugador.java
    │                   ├── Titular.java
    │                   ├── Suplente.java
    │                   ├── Equipo.java
    │                   ├── Partido.java
    │                   └── Liga.java
    └── test/
        └── java/
            └── com/
                └── ligachad/
                    └── AppTest.java

##🧠 Tecnologías Utilizadas

- Java 21
- Maven 3.8.6
- Programación Orientada a Objetos (POO)
- Consola interactiva
- Exportación a CSV

---

##🚀 Funcionalidades

- Registrar jugadores titulares y suplentes
- Crear equipos e incorporar jugadores
- Registrar partidos y asignar goles
- Mostrar goleador de la liga
- Promedio de goles por equipo
- Ranking por goles anotados
- Transferencia de jugadores entre equipos
- Suplentes que nunca ingresaron
- Titular con más minutos jugados
- Exportar jugadores a `.csv`
- Generar reportes generales y por equipo
- Mostrar todos los jugadores con su tipo


---

## 🛠️ Instalación y Ejecución

### 📋 Requisitos

- JDK 21 instalado
- Apache Maven instalado y agregado al PATH
- Editor recomendado: Visual Studio Code o IntelliJ IDEA

### 📥 Clonar o descargar el proyecto

bash
git clone https://github.com/tu-usuario/ligachad.git
cd ligachad

▶️ Compilar y ejecutar

1. Compilar

mvn clean compile

2. Ejecutar

mvn exec:java -Dexec.mainClass="com.ligachad.app.Main"

📌 Detalles de diseño
Uso de herencia entre Jugador, Titular y Suplente

Polimorfismo para métodos como esTitular()

Composición: equipos contienen listas de jugadores

Reportes e interacciones implementadas desde MenuConsola

Separación clara de responsabilidades y paquetes


💡 Ideas futuras
Persistencia de datos en archivo o base de datos

Spring Framework o

Spring Boot

👨‍💻 Autor
Desarrollado por Abel González





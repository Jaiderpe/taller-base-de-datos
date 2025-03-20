
# 🌟 **Proyecto Gestión de Equipos de Fútbol** 🌟

![Fútbol](https://media.giphy.com/media/3oEjI6SIIHBdRx5uG8/giphy.gif)

Bienvenido al proyecto **"Gestión de Equipos de Fútbol"**, una aplicación de consola en Java diseñada para administrar equipos, jugadores y entrenadores de manera eficiente. Este proyecto es ideal para quienes deseen aprender sobre bases de datos relacionales, programación orientada a objetos y manipulación de JSON.

---

## 📋 **Descripción del Proyecto**

Este proyecto permite gestionar la información de equipos de fútbol, incluyendo sus jugadores y entrenadores. Ofrece operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para cada entidad y una interfaz de consola interactiva que facilita su uso.

### 🎯 **Características Principales**
- **Registro de Equipos**: Agrega nuevos equipos y asocia entrenadores. ⚽
- **Gestión de Jugadores**: Registra jugadores con detalles como dorsal, nacionalidad, posición y estadísticas físicas. 🏃‍♂️
- **Gestión de Entrenadores**: Administra la información de los entrenadores. 👨‍🏫
- **Validaciones Automáticas**: Verifica la existencia de equipos antes de registrar jugadores. 🔍
- **Importación desde JSON**: Importa datos iniciales desde un archivo JSON predefinido. 📂
- **Interfaz de Consola Dinámica**: Menú interactivo con opciones claras y fáciles de usar. 💻

---

## 🛠️ **Requisitos del Sistema**

Para ejecutar este proyecto, necesitarás lo siguiente:

1. **Java Development Kit (JDK)**: Versión 8 o superior. [Descargar JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
2. **MySQL**: Base de datos MySQL configurada con las tablas necesarias (`equipos`, `jugadores`, `entrenadores`). [Descargar MySQL](https://dev.mysql.com/downloads/)
3. **Dependencias**:
   - Biblioteca Jackson para manejo de JSON: `com.fasterxml.jackson.databind`.
   - Conector JDBC para MySQL: `mysql-connector-java`.

---

## 🚀 **Configuración del Proyecto**

### 1. **Configuración de la Base de Datos**
Antes de ejecutar el programa, asegúrate de tener una base de datos MySQL configurada. Usa el siguiente script SQL para crear las tablas necesarias:

```sql
CREATE DATABASE futbol;

USE futbol;

CREATE TABLE entrenadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    nationality VARCHAR(100),
    age INT
);

CREATE TABLE equipos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    yearfoundation INT,
    coach_id INT,
    FOREIGN KEY (coach_id) REFERENCES entrenadores(id)
);

CREATE TABLE jugadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    equipo_id INT,
    dorsal INT,
    name VARCHAR(100),
    nationality VARCHAR(100),
    age INT,
    height INT,
    weight INT,
    position VARCHAR(50),
    FOREIGN KEY (equipo_id) REFERENCES equipos(id)
);
```

### 2. **Configuración del Conector MySQL**
Asegúrate de agregar el conector MySQL (`mysql-connector-java`) a tu proyecto. Si usas Maven, añade la siguiente dependencia en tu archivo `pom.xml`:

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

Si no usas Maven, descarga el `.jar` del conector MySQL y añádelo manualmente a tu proyecto.

### 3. **Archivo JSON**
Coloca el archivo `Equipos.json` en la carpeta `src/main/resources`. Este archivo contiene los datos iniciales de equipos, jugadores y entrenadores.

---

## 🧩 **Estructura del Proyecto**

El proyecto está organizado de la siguiente manera:

```
/src
  ├── main
  │     ├── java
  │     │      └── com.hexagonaljava
  │     │             ├── Main.java              # Clase principal con el menú interactivo
  │     │             ├── infrastructure
  │     │             │      ├── database        # DAOs para interactuar con la base de datos
  │     │             │      │      ├── EquipoDAO.java
  │     │             │      │      ├── JugadorDAO.java
  │     │             │      │      └── EntrenadorDAO.java
  │     │             │      └── persistence     # Clases de entidad
  │     │             │             ├── Equipo.java
  │     │             │             ├── Jugador.java
  │     │             │             └── Entrenador.java
  │     └── resources
  │            └── Equipos.json                  # Archivo JSON con datos iniciales
  └── test                                        # Pruebas unitarias (opcional)
```

---

## ▶️ **Cómo Ejecutar el Proyecto**

1. **Clonar el Repositorio**:
   ```bash
   git clone https://github.com/tu-usuario/proyecto-futbol.git
   cd proyecto-futbol
   ```

2. **Configurar la Base de Datos**:
   - Crea la base de datos `futbol` y ejecuta el script SQL proporcionado.

3. **Compilar el Proyecto**:
   Si usas Maven:
   ```bash
   mvn clean install
   ```

4. **Ejecutar el Programa**:
   ```bash
   java -cp target/proyecto-futbol.jar com.hexagonaljava.Main
   ```

---

## 📋 **Menú de Opciones**

Al ejecutar el programa, verás el siguiente menú interactivo:

```
⚽ Menú Principal ⚽
1. Registrar equipo
2. Actualizar equipo
3. Eliminar equipo
4. Listar equipos
5. Registrar jugador
6. Actualizar jugador
7. Eliminar jugador
8. Listar jugadores
9. Registrar entrenador
10. Actualizar entrenador
11. Eliminar entrenador
12. Listar entrenadores
13. Salir
14. Insertar datos desde JSON
```

Sigue las instrucciones en pantalla para realizar las operaciones deseadas. ¡Es muy intuitivo! 😊

---

## 🤝 **Contribuciones**

Si deseas contribuir al proyecto, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -m "✨ Añadir nueva funcionalidad ✨"`).
4. Sube tus cambios (`git push origin feature/nueva-funcionalidad`).
5. Envía un Pull Request.

---

## 📜 **Licencia**

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

---

## 📧 **Contacto**

Si tienes preguntas o sugerencias, no dudes en contactarme:

- **Nombre**: Jaider Adrian Perez Vega 
- **Correo Electrónico**: tu-correo@ejemplo.com
- **GitHub**: [https://github.com/tu-usuario](https://github.com/tu-usuario)

---

## 🙌 **Agradecimientos**

Gracias por visitar este repositorio. ¡Espero que encuentres útil este proyecto y disfrutes trabajando con él! Si te gusta, no olvides darle una ⭐️. 😄

![Gracias](https://media.giphy.com/media/3ohzdIuqJoo8QdKlnW/giphy.gif)

---

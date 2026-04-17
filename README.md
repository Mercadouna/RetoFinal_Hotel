# Proyecto: Reto Final - Hotel DAMI

## 📝 Descripción
Aplicación de escritorio para la gestión hotelera que permite administrar clientes, habitaciones, reservas y servicios extra. El proyecto cuenta con un sistema de login y un menú principal con interfaces gráficas, conectado a una base de datos relacional para la persistencia de los datos.

## 🧰 Tecnologías utilizadas
- Java (Swing para las interfaces gráficas)
- 🗄️ MySQL
- 🔌 JDBC (conector MySQL)
- Mockito (testing con JARs)
- Herramientas: NetBeans / Eclipse / VS Code / IntelliJ IDEA

## 📚 Dependencias
Este proyecto utiliza las siguientes librerías externas:
- Conector JDBC MySQL 
- Mockito 

**Asegúrate de añadir los .jar al proyecto:**
- **En Eclipse:** Click derecho al proyecto → Build Path → Configure Build Path → Libraries → Add JARs...
- **En NetBeans:** Properties → Libraries → Add JAR/Folder
- **En IntelliJ:** File → Project Structure → Modules → Dependencies

## 🗄️ Base de datos
El proyecto incluye el script de base de datos: 

### ▶️ Cómo usarlo
1. Crear una base de datos en MySQL.
2. Importar el fichero 
   a. Desde consola: `mysql -u usuario -p nombre_bd < bd.sql`
   b. O usando una herramienta gráfica como MySQL Workbench, phpMyAdmin o DBeaver.

## 📦 Instalación

### 📥 Clonar el repositorio
```bash
git clone https://github.com/usuario/repositorio.git
```

### 📁 Importar el proyecto
- Abrir el proyecto localmente en tu IDE preferido (Eclipse / IntelliJ / VS Code / NetBeans).

### ➕ Añadir dependencias manualmente
1. Descargar / Localizar:
   a. Conector JDBC de MySQL (dentro del proyecto).
   b. Librerías de Mockito.
2. Añadir los archivos `.jar` al classpath/build path del proyecto (ver sección *"Dependencias"*).

## ▶️ Ejecución
1. Configurar la conexión a la base de datos MySQL en el proyecto (puedes verificar credenciales y base de datos en archivos como `src/configClase.properties` o en tus clases de modelo de base de datos).
2. Ejecutar la aplicación desde el main, iniciando desde la clase:
   `src/principal/ProgramaPrincipal.java`

## Tests
El proyecto incluye pruebas unitarias usando Mockito (en el paquete `test`). Para ejecutarlas:
- Desde el IDE → Botón derecho en la clase de test o carpeta `test` → Ejecutar tests (Run as JUnit Test).
- Asegúrate de que los JAR de Mockito (y JUnit) están correctamente añadidos al build path antes de comenzar.

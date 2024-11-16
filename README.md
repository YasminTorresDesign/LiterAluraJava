# LiterAlura: Catálogo de Libros 📚✨  

---

## Descripción del Proyecto  
**LiterAlura** es un catálogo interactivo de libros diseñado para permitir la búsqueda, registro y consulta de información literaria mediante la API de **Gutendex**. Proporciona funcionalidades avanzadas para manipular datos JSON, almacenarlos en una base de datos y realizar consultas a través de una interfaz de consola.

---

## Funcionalidades del Menú Principal  
### **1. Buscar y registrar libro**  
Realiza búsquedas de libros por nombre a través de la API de Gutendex y permite su registro junto con la información del autor.  

### **2. Listar libros registrados**  
Muestra todos los libros almacenados en la base de datos.  

### **3. Listar autores registrados**  
Permite visualizar a todos los autores almacenados en el sistema.  

### **4. Listar autores vivos en un determinado año**  
Filtra los autores que estaban vivos en un año específico basándose en sus fechas de nacimiento y muerte.  

### **5. Listar libros por idioma**  
Permite listar libros filtrados según el idioma en el que fueron escritos.  

### **6. Estadística de libros registrados**  
Muestra estadísticas generales: 
- **Cantidad media de descargas**
- **Cantidad maxima de descargas**
- **Cantidad minima de descargas**
- **Cantidad de registros evaluados para calcular las estadisticas**
 
### **7. Top 10 libros más descargados**  
Lista los diez libros más descargados en el catálogo.  

### **8. Buscar autor registrado**  
Realiza una búsqueda específica de un autor registrado para verificar si existe en la base de datos.  

### **0. Salir**  
Finaliza la ejecución del programa.

---

## Tecnologías Utilizadas  
- **Lenguaje:** Java  
- **Framework:** Spring Boot  
- **Base de Datos:** H2 (o cualquier base compatible con JPA)  
- **Consumo de API:** Gutendex ([https://gutendex.com/](https://gutendex.com/))  
- **Gestión de Dependencias:** Maven  
- **Herramienta de Persistencia:** Hibernate (JPA)  
- **Entorno de Desarrollo:** IntelliJ IDEA / Eclipse  

---
 # Empezando 🚀
 
## Configuración y Ejecución del Proyecto

### 1. Clona el repositorio  
```bash
git clone https://github.com/YasminTorresDesign/LiterAluraJava.git
cd LiterAluraJava
```
### 2. Configurar las dependencias
Asegúrate de que todas las dependencias estén instaladas correctamente ejecutando:
```bash
mvn clean install
```
### 3. Configura la base de datos:
Asegúrate de tener PostgreSQL instalado y configurado localmente.
Crea una base de datos llamada desafio_literalura en tu servidor PostgreSQL.

### 4. Actualiza la configuración:
Abre el archivo application.properties en src/main/resources.
Configura las propiedades de conexión a tu base de datos:
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/desafio_literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
### 5. Ejecutar la aplicación
Inicia la aplicación desde tu IDE o ejecuta el siguiente comando:
```bash
mvn spring-boot:run
```
### 6. Interactúa con la aplicación
Utiliza la consola para interactuar con las funcionalidades implementadas.
---
### Author ✒️
Yasmin Torres Mora - https://github.com/YasminTorresDesign


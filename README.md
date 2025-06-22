# 🧠 Trabajo Práctico – Programación sobre Redes: Flujo de Datos

## 👤 Datos del Alumno

- **Nombre y Apellido:** Roman Abalos Ishida
- **Correo Electrónico:** romanabalosishidaet32@gmail.com
- **Curso y División:** 6.1C

## 👨‍🏫 Docente

- **Nombre y Apellido:** Gonzalo Nicolás Consorti

## 🧾 Materia

**Programación sobre Redes**  
Este trabajo práctico se centra en el manejo de datos desde diferentes fuentes y destinos, haciendo uso de memoria volátil (vectores) y no volátil (archivos), además del tratamiento de excepciones comunes como `ArithmeticException` y `NullPointerException`.

---

## 📁 Estructura del Proyecto

El proyecto está compuesto por 4 clases principales:

- `Main.java`: punto de entrada, donde se definen vectores y se invocan procesos.
- `EntradaDatos.java`: permite la carga de datos desde consola usando `Reader`.
- `ManejadorArchivo.java`: se encarga de leer y escribir archivos de texto.
- `Procesos.java`: contiene la lógica principal de procesamiento y manejo de errores.

---

## 📌 Consigna

Desarrollar un programa que cumpla con los siguientes puntos:

### 1. Entrada y almacenamiento

- Ingresar números desde dos orígenes distintos:
  - Consola: utilizando `BufferedReader` (sin `Scanner`).
  - Código fuente: vector hardcodeado en `Main.java`.
- Los datos deben guardarse en:
  - Memoria volátil: vector de 5 elementos (mínimo 2 ceros).
  - Memoria no volátil: archivo de texto (`datos.txt`, un número por línea).

### 2. Procesamiento de vector en memoria

- Leer el vector cargado por consola.
- Dividir cada elemento por el siguiente.
- Guardar:
  - Resultados válidos en `resultados.txt`.
  - Errores (por división por cero o acceso inválido) en `error.txt`.

### 3. Lectura desde archivo y procesamiento adicional

- Leer el vector guardado en `datos.txt`.
- Realizar una división de cada elemento entre 3.
- Repetir este proceso también para el vector en memoria.
- Guardar nuevamente:
  - Resultados en `resultados.txt`.
  - Errores en `error.txt`.

---

## ❗ Comentarios y Complicaciones

- Se desarrolló una clase de entrada personalizada para cumplir con la restricción de no usar `Scanner`.
- Se manejaron errores comunes como:
  - División por cero (`ArithmeticException`)
  - Índice fuera de rango (`ArrayIndexOutOfBoundsException`)
  - Valor nulo (`NullPointerException`)
- El proyecto fue desarrollado inicialmente en Spring Tools Suite y luego migrado a IntelliJ IDEA, lo cual requirió ajustes en la configuración del entorno y del JDK.

---

## ✅ Estado del Proyecto

El programa cumple con todos los puntos del enunciado y maneja correctamente los errores.  
Los resultados y errores se guardan en archivos separados como lo indica la consigna.  
La entrada y salida de datos respeta los métodos permitidos (`Reader` y `PrintStream`).

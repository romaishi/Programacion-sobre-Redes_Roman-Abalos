# 🧠 Trabajo Práctico – Inventario de Productos: Flujo de Datos

## 👤 Datos del Alumno

- **Nombre y Apellido:** Roman Abalos Ishida  
- **Correo Electrónico:** romanabalosishidaet32@gmail.com  
- **Curso y División:** 6.1C

## 👨‍🏫 Docente

- **Nombre y Apellido:** Gonzalo Nicolás Consorti

## 🧾 Materia

**Programación sobre Redes**

Este trabajo práctico se centra en la gestión de un sistema de inventario a través de entrada de datos por consola, almacenamiento en archivos y operaciones CRUD básicas (Crear, Leer, Actualizar, Eliminar), combinando el uso de memoria volátil y no volátil.

---

## 📁 Estructura del Proyecto

El proyecto está organizado en distintas clases y paquetes para promover una arquitectura modular:

- `Main.java`: punto de entrada, ejecuta el menú principal y redirige las acciones.
- `Producto.java`: representa el modelo de datos con los atributos: nombre, precio de compra, precio de venta y stock.
- `InventarioFunciones.java`: contiene la lógica de negocio para agregar, mostrar, editar y eliminar productos.
- `ArchivoFunciones.java`: gestiona la lectura y escritura de productos en el archivo de texto.
- `Consola.java`: maneja la entrada de datos desde la consola usando `BufferedReader`, sin `Scanner`.
- `Colors.java`: define constantes de colores ANSI para una mejor presentación en consola.

---

## 📌 Consigna

Desarrollar un programa que cumpla con los siguientes puntos:

### 1. Menú de opciones

Crear un menú infinito en consola con las siguientes opciones:

- Agregar producto  
- Mostrar inventario  
- Editar producto  
- Eliminar producto  
- Salir  

> Todos los mensajes y respuestas deben estar prolijamente presentados, usando colores, tabulaciones y saltos de línea.

### 2. Entrada de datos y validaciones

- Leer texto desde consola usando `BufferedReader` (sin `Scanner`).
- Validar si la entrada es:
  - No numérica  
  - Número entero  
  - Número decimal (coma o punto)
- Convertir los textos numéricos a sus respectivos tipos (`int` o `float`).

### 3. Registro de productos

Solicitar al usuario los siguientes campos:

- Nombre del producto  
- Precio de compra (`float`)  
- Precio de venta (`float`)  
- Stock (`int`)

> Estos datos se almacenan en:
- **Memoria volátil:** lista de productos.  
- **Memoria no volátil:** archivo `Inventario.dat` con formato plano (una línea por producto).  

Ejemplo de línea en el archivo:

manzana;50.6;100.7;240



### 4. Operaciones sobre archivo

Se deben implementar los siguientes métodos:

- Crear archivo si no existe.
- Leer productos del archivo (`BufferedReader`).
- Agregar nuevos productos al archivo (`BufferedWriter`, modo append).
- Mostrar el contenido del archivo en consola de forma ordenada.
- Eliminar un producto según su índice.
- Editar un producto existente, solicitando los nuevos datos al usuario.
- Guardar los cambios sobrescribiendo el archivo original.

---

## ⚠️ Consideraciones Técnicas

- Se respetó la restricción de no utilizar `Scanner`, y en su lugar se usó `BufferedReader`.
- Los errores de entrada inválida se manejan con validaciones personalizadas y mensajes en rojo.
- La presentación de datos en consola utiliza códigos de colores ANSI definidos en la clase `Colors`.

---

## ✅ Estado del Proyecto

El programa se encuentra completamente funcional:

- Permite cargar productos correctamente.
- Muestra el inventario en forma legible.
- Valida correctamente la entrada del usuario.
- Guarda los datos persistentes en archivo plano (`Inventario.dat`).
- Permite editar y eliminar productos del archivo.
- Ofrece una experiencia prolija y visualmente clara desde la consola.

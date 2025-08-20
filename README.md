# Servidor HTTP en Java
Servidor HTTP en Java puro que muestra archivos estáticos y expone un endpoint sencillo

## Arquitectura del prototipo

El prototipo implementa un servidor HTTP básico en Java con la siguiente arquitectura:

- **Servidor HTTP (HttpServer.java)**
    Maneja las conexiones entrantes mediante ServerSocket.
    Procesa peticiones de clientes utilizando solo el método GET.
    Devuelve archivos estáticos y respuestas JSON en rutas específicas.

- **Módulo de servicios (/app)**
    Expone endpoints simples (ejemplo: /app/hello) que responden en formato JSON.

- **Módulo de archivos estáticos (/www)**
    Contiene los recursos que el servidor puede entregar directamente:
        index.html → Página principal
        404.html → Página de error cuando no se encuentra un recurso
        Archivos CSS, JS e imágenes.

## Comenzando

Sigue las siguiente instrucciones para que te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local.

- **Prerrequisitos**

Necesitas tener instalado:

    Java 8 o superior
    Maven

## Instalación

Pasos para ejecutar el servidor en tu entorno local:

- **Clonar el repositorio**
    git clone https://github.com/TU_USUARIO/TU_REPO.git
    cd TU_REPO

- **Compilar el proyecto**
    mvn clean install

- **Ejecutar el servidor**
    mvn exec:java

## Estructura del proyecto

├───src
│   ├───main
│   │   └───java
│   │        └───Escuelaing
│   │               └───edu
│   │                   └───co
│   │                           AppServer.java
│   │
│   └───test
│       └───java
│           └───Escuelaing
│                   └───edu
│                       └───co
│                               AppServerTest.java
│
└───www
    │   404.html
    │   index.html
    │   script.js
    │   style.css
    │
    └───images
            fondo.avif
            fondo.jpg


## Endpoints disponibles

    http://localhost:35000/ → Sirve index.html desde la carpeta /www.

    http://localhost:35000/app/hello?name=TuNombre → Devuelve un JSON con un saludo.

    http://localhost:36000/[archivo] → Sirve cualquier recurso dentro de /www.

## Ejecución de pruebas
    
    mvn test

## Autor
    Juan David Rodriguez Rodriguez
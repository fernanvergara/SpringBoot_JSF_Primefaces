# Ejemplo de Integración Spring Boot - JSF - PrimeFaces

Este proyecto es un ejemplo práctico que demuestra la integración de **Spring Boot** con **JSF (JavaServer Faces)**, utilizando el framework de componentes **PrimeFaces** y facilitando esta unión a través de **JoinFaces**. Incluye una página de login y una página de inicio, tomadas de un proyecto público existente, para ilustrar el flujo básico de autenticación y navegación.

## 🚀 Funcionalidades Principales

* **Autenticación de Usuario:** Permite a los usuarios iniciar sesión con un usuario y contraseña.
* **Página de Inicio:** Tras una autenticación exitosa, el usuario es redirigido a una página de inicio.
* **Cierre de Sesión:** Los usuarios pueden cerrar sesión accediendo a la ruta por defecto de Spring Security: `/logout`.

## 🛠️ Tecnologías Utilizadas

* **Spring Boot:** Framework para el desarrollo rápido de aplicaciones Java.
* **JSF (JavaServer Faces):** Framework para construir interfaces de usuario basadas en componentes.
* **PrimeFaces:** Biblioteca de componentes UI rica para JSF.
* **JoinFaces:** Integración de JSF y PrimeFaces con Spring Boot.
* **Spring Security:** Para la gestión de la autenticación y autorización.
* **Lombok:** Para reducir el código repetitivo (getters, setters, constructores).
* **Bases de Datos:** Soporte para H2 Database (en memoria, para desarrollo).
* **Springdoc OpenAPI:** Para generar documentación de API (Swagger UI).

## 🏁 Cómo Ejecutar el Proyecto

Sigue estos pasos para poner en marcha el proyecto en tu entorno local:

### 1. Clonar el Repositorio

Primero, clona el repositorio de GitHub donde tienes alojado el proyecto:

```bash
git clone https://github.com/fernanvergara/SpringBoot_JSF_Primefaces.git
cd SpringBoot_JSF_Primefaces
```

### 2. Requisitos Previos

Asegúrate de tener instalado lo siguiente:

* **Java Development Kit (JDK) 21 o superior** (según la configuración en `pom.xml`)
* **Maven** (generalmente incluido en IDEs como IntelliJ IDEA o Eclipse)

### 3. Configuración de la Base de Datos (Opcional)

Por defecto, Spring Boot puede configurarse para usar una base de datos en memoria como H2. Si deseas usar otra, asegúrate de tener una instancia en ejecución y configura las propiedades de conexión en `src/main/resources/application.properties` (o `application.yml`).

### 4. Construir el Proyecto

Navega hasta el directorio raíz del proyecto (donde se encuentra el `pom.xml`) y ejecuta el siguiente comando para construir el proyecto:

```bash
mvn clean install
```

### 5. Ejecutar la Aplicación

Una vez que el proyecto se haya construido exitosamente, puedes ejecutar la aplicación Spring Boot:

```bash
mvn spring-boot:run
```

Alternativamente, si estás usando un IDE como IntelliJ IDEA o Eclipse, puedes ejecutar la clase principal de Spring Boot (`Application.java` o similar) directamente.

### 6. Acceder a la Aplicación

Una vez que la aplicación esté en funcionamiento (generalmente en `http://localhost:8080`), abre tu navegador web y navega a la URL base para acceder a la página de login:

```
http://localhost:8080
```

Utiliza las credenciales configuradas por defecto en el proyecto para iniciar sesión.
```
user: admin
passwor: 1234567890
```

## 📂 Estructura del Proyecto

La estructura del proyecto sigue las convenciones estándar de Spring Boot y Maven, con las siguientes carpetas clave:

```
src/main/java/com/sti/example/
├── service/                # Lógica de negocio
├── repository/             # Acceso a datos
└── ...
src/main/resources/
├── application.properties  # Configuración de Spring Boot
└── META-INF/               # Archivos JSF (XHTML)
    └── resources/          # Archivos estáticos (CSS, JS, imágenes)
```

## 🔐 Notas de Seguridad

* El cierre de sesión se realiza visitando la URL `/logout`.
* Las credenciales de usuario y la configuración de seguridad se gestionan a través de Spring Security.

---

¡Espero que este `README.md` te sea de gran utilidad! Si quieres modificar o añadir más secciones, no dudes en decírmelo, si es para hacerlo crecer, bienvenido sea.

# 🗺️ Tablero de Tareas: PokeMatch MVP

## 📦 Fase 1: El Corazón Backend & Integración (Días 1-4)
- [x] Inicializar el proyecto en Spring Initializr (Adaptado a Java 21 y Spring Boot 4).
- [x] Configurar el archivo pom.xml con MapStruct, WebFlux y orden de compilación de Lombok.
- [x] Crear una clase de configuración para instanciar el WebClient.
- [x] Configurar el archivo application.yml con las propiedades de la base de datos y puertos.
- [x] Crear el archivo docker-compose.yml base para levantar PostgreSQL.

### 🔌 Capa de Integración (PokeAPI)
- [x] Diseñar los DTOs internos para mapear la respuesta JSON nativa de la PokeAPI.
- [x] Implementar el cliente HTTP (PokeApiClient) para consumir /pokemon/{name_or_id}.

### 🧠 Capa de Negocio Básica & Mapeo
- [x] Crear el DTO de salida limpio que consumirá Angular (PokemonDto).
- [x] Configurar MapStruct para transformar los DTOs de la PokeAPI al PokemonDto.
- [x] Crear el PokemonService para gestionar las búsquedas.

### ⚡ Optimización (Caché)
- [x] Habilitar el soporte de caché en la clase principal con @EnableCaching.
- [x] Anotar el método de búsqueda en el servicio con @Cacheable("pokemons").

### 🚦 Exposición Inicial & Puentes (¡NUEVO!)
- [x] Crear el controlador PokemonController con el endpoint GET `/api/v1/pokemon/{nameOrId}`.
- [x] Configurar una clase WebMvcConfigurer para habilitar CORS (permitir peticiones desde localhost:4200).

---

## ⚔️ Fase 2: El Motor de Batalla & SOLID (Días 5-7)
### 🧩 Diseño del Patrón Strategy
- [x] Crear la interfaz DamageCalculatorStrategy con el método calculateMultiplier.
- [x] Crear un Enum o estructura de datos estática con la tabla de efectividad de tipos.
- [x] Implementar la clase TypeAdvantageStrategy que resuelva el multiplicador por tipos.

### ⚙️ Servicio de Simulación
- [x] Crear el BattleSimulatorService.
- [x] Inyectar la estrategia en el servicio y crear la lógica de comparación de estadísticas.
- [x] Crear el controlador BattleController con el endpoint `/api/v1/battle/simulate`.

### 🧪 Calidad de Código (Testing)
- [ ] Escribir tests unitarios para TypeAdvantageStrategy (ej: Charizard vs Venusaur).
- [ ] Escribir tests unitarios para BattleSimulatorService usando Mockito.

---

## 🔒 Fase 3: Seguridad, Persistencia y Excepciones (Días 8-10)
### ⚠️ Manejo de Errores
- [ ] Crear un @ControllerAdvice para capturar excepciones globales.
- [ ] Implementar control para 404 Not Found usando el estándar Problem Details.

### 💾 Persistencia (Base de Datos)
- [ ] Crear la entidad espejo PokemonEntity para persistencia local (¡NUEVO!).
- [ ] Crear la entidad User y la entidad FavoriteMatch.
- [ ] Crear los repositorios de JPA correspondientes (UserRepository, FavoriteMatchRepository).
- [ ] Crear el controlador y servicio para `/api/v1/favorites` (guardar y listar).

### 🛡️ Seguridad (Spring Security + JWT)
- [ ] Agregar las dependencias de Spring Security y JWT al pom.xml.
- [ ] Configurar la cadena de filtros de seguridad (SecurityFilterChain).
- [ ] Permitir acceso público a búsquedas/simulación y restringir favoritos a usuarios autenticados.
- [ ] Implementar endpoints de Registro y Login (`/api/v1/auth/`).

---

## 🎨 Fase 4: La Interfaz en Angular (Días 11-13)
### 🏗️ Estructura Inicial Frontend
- [ ] Crear un nuevo proyecto Angular (`ng new pokematch-frontend`).
- [ ] Instalar y configurar TailwindCSS o Angular Material.
- [ ] Configurar el HttpClient y crear los servicios Angular (PokemonService, BattleService, AuthService).

### 🔍 Componente de Búsqueda y Dashboard
- [ ] Crear la interfaz del buscador.
- [ ] Implementar el operador debounceTime en el input de búsqueda.
- [ ] Diseñar la vista de comparación: dos columnas con barras de progreso de colores.

### 🎮 Integración de Simulación
- [ ] Añadir el botón "Simular Enfrentamiento" que envíe ambos IDs al backend.
- [ ] Mostrar de forma visual el veredicto del motor de daño.

---

## 📝 Fase 5: Documentación & Despliegue Local (Día 14)
- [ ] Verificación de Docker (Levantar PostgreSQL de manera aislada).
- [ ] Smoke Test (Probar clonando el repositorio en una carpeta limpia).
- [ ] Documentación Técnica (README en inglés con tecnologías, Strategy, caché y ejecución).
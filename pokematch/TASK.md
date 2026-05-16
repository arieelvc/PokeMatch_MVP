# 🗺️ Tablero de Tareas: PokeMatch MVP

## 📦 Fase 1: El Corazón Backend & Integración (Días 1-4)
- [x] Inicializar el proyecto en Spring Initializr (Adaptado a Java 21 y Spring Boot 4).
- [x] Configurar el archivo pom.xml con MapStruct, WebFlux y orden de compilación de Lombok.
- [x] Crear una clase de configuración para instanciar el WebClient.
- [ ] Configurar el archivo application.yml (o application.properties) con las propiedades de la base de datos y puertos.
- [ ] Crear el archivo docker-compose.yml base para levantar PostgreSQL.

### 🔌 Capa de Integración (PokeAPI)
- [ ] Diseñar los DTOs internos para mapear la respuesta JSON nativa de la PokeAPI (solo los campos necesarios: id, name, stats, types, sprites).
- [ ] Implementar el cliente HTTP (PokeApiClient) para consumir /pokemon/{name_or_id}.

### 🧠 Capa de Negocio Básica & Mapeo
- [ ] Crear el DTO de salida limpio que consumirá Angular (PokemonDto).
- [ ] Configurar MapStruct para transformar los DTOs de la PokeAPI al PokemonDto.
- [ ] Crear el PokemonService para gestionar las búsquedas.

### ⚡ Optimización (Caché)
- [ ] Habilitar el soporte de caché en la clase principal con @EnableCaching.
- [ ] Anotar el método de búsqueda en el servicio con @Cacheable("pokemons").

---

## ⚔️ Fase 2: El Motor de Batalla & SOLID (Días 5-7)
### 🧩 Diseño del Patrón Strategy
- [ ] Crear la interfaz DamageCalculatorStrategy con el método calculateMultiplier.
- [ ] Crear un Enum o estructura de datos estática con la tabla de efectividad de tipos (Fuego, Agua, Planta, etc.).
- [ ] Implementar la clase TypeAdvantageStrategy que herede de la interfaz y resuelva el multiplicador por tipos.

### ⚙️ Servicio de Simulación
- [ ] Crear el BattleSimulatorService.
- [ ] Inyectar la estrategia en el servicio y crear la lógica para comparar las estadísticas base de ambos Pokémon y aplicar el multiplicador.
- [ ] Crear el controlador BattleController con el endpoint /api/v1/battle/simulate.

### 🧪 Calidad de Código (Testing)
- [ ] Escribir tests unitarios para TypeAdvantageStrategy con escenarios clave (ej: Charizard vs Venusaur debe dar x2).
- [ ] Escribir tests unitarios para BattleSimulatorService usando Mockito para aislar el cliente de la PokeAPI.

---

## 🔒 Fase 3: Seguridad, Persistencia y Excepciones (Días 8-10)
### ⚠️ Manejo de Errores
- [ ] Crear un @ControllerAdvice para capturar excepciones globales.
- [ ] Implementar control para cuando un Pokémon no existe (404 Not Found) usando el estándar Problem Details.

### 💾 Persistencia (Base de Datos)
- [ ] Crear la entidad User y la entidad FavoriteMatch.
- [ ] Crear los repositorios de JPA correspondientes (UserRepository, FavoriteMatchRepository).
- [ ] Crear el controlador y servicio para /api/v1/favorites (guardar y listar).

### 🛡️ Seguridad (Spring Security + JWT)
- [ ] Agregar las dependencias de Spring Security y JWT al pom.xml.
- [ ] Configurar la cadena de filtros de seguridad (SecurityFilterChain).
- [ ] Permitir acceso público a las búsquedas y simulación; restringir /api/v1/favorites solo a usuarios autenticados.
- [ ] Implementar endpoints de Registro y Login (/api/v1/auth/).

---

## 🎨 Fase 4: La Interfaz en Angular (Días 11-13)
### 🏗️ Estructura Inicial Frontend
- [ ] Crear un nuevo proyecto Angular (ng new pokematch-frontend).
- [ ] Instalar y configurar TailwindCSS o Angular Material para los estilos.
- [ ] Configurar el HttpClientModule y crear los servicios Angular para conectar con el backend (PokemonService, BattleService, AuthService).

### 🔍 Componente de Búsqueda y Dashboard
- [ ] Crear la interfaz del buscador.
- [ ] Implementar el operador debounceTime en el input de búsqueda para controlar las peticiones HTTP.
- [ ] Diseñar la vista de comparación: dos columnas (Jugador vs Rival) que muestren las estadísticas en barras de progreso de colores.

### 🎮 Integración de Simulación
- [ ] Añadir el botón "Simular Enfrentamiento" que envíe ambos IDs al backend.
- [ ] Mostrar de forma visual el veredicto del motor de daño (multiplicadores de ventaja, quién tiene más velocidad, etc.).

---

## 📝 Fase 5: Documentación & Despliegue Local (Día 14)
- [ ] **Verificación de Docker:** Asegurarse de que el archivo docker-compose.yml levanta la base de datos de manera aislada y limpia.
- [ ] **Smoke Test:** Probar la aplicación desde cero clonando el repositorio en una carpeta limpia.
- [ ] **Documentación Técnica (README):** Escribir la descripción del proyecto en inglés detallando tecnologías, patrón Strategy, uso de caché y cómo ejecutar todo localmente.
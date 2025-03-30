# El Archivo de las Tormentas - Juego 2D

Un juego de plataformas 2D basado en la serie de libros "El Archivo de las Tormentas" de Brandon Sanderson, desarrollado con libGDX.

## Descripción

En este juego, controlarás a Kaladin, un personaje principal de la serie, mientras lucha contra los Parshendi y utiliza sus poderes de Surgebinding. El juego combina elementos de plataformas con mecánicas de combate y habilidades especiales.

## Características

- Control de Kaladin con movimientos fluidos
- Sistema de combate con poderes de Surgebinding
- Enemigos Parshendi con diferentes formas y comportamientos
- NPCs Spren que ayudan al jugador
- Múltiples niveles inspirados en el mundo de Roshar
- Sistema de guardado de progreso
- Efectos de sonido y música temática

## Requisitos

- Java 8 o superior
- Gradle 7.0 o superior

## Instalación

1. Clona el repositorio:
```bash
git clone https://github.com/tu-usuario/el-archivo-de-las-tormentas.git
```

2. Navega al directorio del proyecto:
```bash
cd el-archivo-de-las-tormentas
```

3. Compila el proyecto:
```bash
./gradlew build
```

4. Ejecuta el juego:
```bash
./gradlew desktop:run
```

## Controles

- **Flechas izquierda/derecha**: Movimiento horizontal
- **Espacio**: Salto
- **L**: Activar Lash (habilidad especial)
- **E**: Interactuar con NPCs
- **ESC**: Menú de pausa

## Estructura del Proyecto

```
el-archivo-de-las-tormentas/
├── core/                    # Código principal del juego
│   └── src/main/java/
│       └── mijuego/
│           ├── characters/  # Clases de personajes
│           ├── manager/     # Gestores del juego
│           ├── screen/      # Pantallas del juego
│           └── util/        # Utilidades
├── desktop/                # Lanzador para escritorio
└── core/src/main/resources/
    ├── textures/          # Texturas del juego
    ├── animations/        # Animaciones
    ├── sounds/           # Efectos de sonido
    ├── music/            # Música
    ├── skin/             # Estilos de UI
    └── maps/             # Mapeos de niveles
```

## Contribuir

1. Haz un fork del repositorio
2. Crea una rama para tu característica (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

### Guías de Contribución

- Sigue las convenciones de código Java
- Añade comentarios explicativos en el código
- Actualiza la documentación cuando sea necesario
- Asegúrate de que el código compila sin errores
- Prueba tus cambios antes de enviar un PR

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## Créditos

- Basado en "El Archivo de las Tormentas" de Brandon Sanderson
- Desarrollado con libGDX
- Música y efectos de sonido: [Créditos de audio]
- Arte y animaciones: [Créditos de arte]

## Link del Proyecto: (https://github.com/rungod95/JuegoAA)

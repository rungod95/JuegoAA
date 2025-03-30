package mijuego;

import java.lang.reflect.Method;

public class StartupHelper {
    public static void startNewJvmIfRequired() {
        String version = System.getProperty("java.version");
        if (version != null) {
            try {
                float versionNumber = Float.parseFloat(version.substring(0, 3));
                if (versionNumber < 8) {
                    System.out.println("Se requiere Java 8 o superior para ejecutar el juego.");
                    try {
                        Method exit = Runtime.class.getMethod("exit", int.class);
                        exit.invoke(Runtime.getRuntime(), 1);
                    } catch (Exception e) {
                        System.exit(1);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Error al verificar la versión de Java.");
            }
        }
    }
} 
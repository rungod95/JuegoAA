package com.svalero.mijuego;

import java.lang.reflect.Method;

public class StartupHelper {
    public static boolean startNewJvmIfRequired() {
        String version = System.getProperty("java.version");
        if (version == null) {
            return false;
        }
        if (version.startsWith("1.")) {
            version = version.substring(2, 3);
        } else {
            version = version.substring(0, version.indexOf("."));
        }
        if (Integer.parseInt(version) < 8) {
            System.out.println("Java 8 or higher is required. Found: " + System.getProperty("java.version"));
            try {
                Method exit = Runtime.class.getMethod("exit", int.class);
                exit.invoke(null, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
} 
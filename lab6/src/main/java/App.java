
import annotations.Column;
import annotations.Entity;
import graph.GraphModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class App {
    // java path to package for scanning
    public static final String PATH = "model";

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String BOLD = "\u001B[01m";
    public static final String ITALIC = "\u001B[03m";

    public static void main(String[] args) {
        System.out.println(CYAN + BOLD + "\t\t------  Найденные классы  ------\n" + RESET);
        List<Class<?>> classList = find();
        for (Class<?> aClass : classList) {
            System.out.println(RED + aClass.getName() + RESET);
        }

        System.out.println(CYAN + BOLD + "\n\n\t\t-- == ===  Поля Классов  === == —-" + RESET);
        for (Class<?> aClass : classList) {
            System.out.println("\n" + RED + aClass.getName() + RESET);
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                System.out.println("\t" + YELLOW + ITALIC + declaredField.getName() + RESET);
            }
        }

        System.out.println(CYAN + BOLD + "\n\n\t\t-- == ===  Методы классов  === == —-" + RESET);
        for (Class<?> aClass : classList) {
            System.out.println("\n" + RED + aClass.getName() + RESET);
            Method[] declaredMethods = aClass.getDeclaredMethods();
            for (Method method : declaredMethods) {
                System.out.println("\t" + PURPLE + ITALIC + method.getName() + RESET);
            }
        }

        System.out.println(CYAN + BOLD + "\n\n\t\t-- == ===  Сущности  === == —-\n" + RESET);
        for (Class<?> aClass : classList) {
            Annotation[] annotations = aClass.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(Entity.class)) {
                    System.out.println("\t" + YELLOW + ITALIC + aClass.getName() + RESET);
                }
            }
        }

        System.out.println(CYAN + BOLD + "\n\n\t\t-- == ===  Аттрибуты сущностей  === == —-" + RESET);
        for (Class<?> aClass : classList) {
            if (aClass.isAnnotationPresent(Entity.class)) {
                System.out.println("\n" + RED + aClass.getName() + RESET);
                for (Field field : aClass.getDeclaredFields()) {
                    for (Annotation a : field.getAnnotations()) {
                        if (a.annotationType().equals(Column.class)) {
                            System.out.println("\t" + BLUE + ITALIC + field.getName() + RESET +
                                    " : " + YELLOW + field.getType().getName() + RESET);
                        }
                    }
                }
            }
        }

        System.out.println(CYAN + BOLD + "\n\n\t\t-- == ===  GraphML  === == —-" + RESET);
        String graphXML = export(classList);
        System.out.println(graphXML);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("out.graphml")));
            writer.write(graphXML);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String export(List<Class<?>> classList) {
        GraphModel graphModel = new GraphModel();
        graphModel.fetchEntities(classList);
        graphModel.fetchEdges();
        return graphModel.toString();
    }

    public static List<Class<?>> find() {
        String scannedPath = App.PATH; //.replace(".", "/");
        URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
        if (scannedUrl == null) {
            throw new IllegalArgumentException("Bad package " + App.PATH);
        }
        File scannedDir = new File(scannedUrl.getFile());
        List<Class<?>> classes = new ArrayList<>();
        for (File file : scannedDir.listFiles()) {
            classes.addAll(find(file, App.PATH));
        }
        return classes;
    }

    private static List<Class<?>> find(File file, String path) {
        List<Class<?>> classes = new ArrayList<>();
        String resource = path + "." + file.getName();
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                classes.addAll(find(child, resource));
            }
        } else if (resource.endsWith(".class")) {
            String className = resource.substring(0, resource.length() - 6);
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException ignore) {
            }
        }
        return classes;
    }
}

package Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PathScan {

    public static List<Class<?>> find(String pathToScan){
        String scannedPath = pathToScan.replace('.', '/');
        URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);

        if (scannedPath == null){
            throw new IllegalArgumentException("Invalid package: " + pathToScan);
        }
        File scannedDir = new File(scannedUrl.getFile());
        List<Class<?>> classes = new ArrayList<>();
        for (File file : scannedDir.listFiles()){
            classes.addAll(find(file, pathToScan));
        }
        return classes;
    }

    private static List<Class<?>> find(File file, String scannedPackage){
        List<Class<?>> classes = new ArrayList<>();
        String resource = scannedPackage + "." + file.getName();

        if (file.isDirectory()){
            for (File fil : file.listFiles()){
                classes.addAll(find(fil, resource));
            }
        }else if (resource.endsWith(".class")){
            String className = resource.substring(0, resource.length()-6);

            try {
                classes.add(Class.forName(className));
            }catch (ClassNotFoundException exception){

            }
        }
        return classes;
    }

}

package net.kwright02.wattage.util;

import com.google.common.collect.ImmutableSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class ApiVersionChecker {

    private static final Logger LOGGER = LogManager.getLogger("Wattage API");
    private static final Set<String> oldClasses;

    static {
        /*
         * No old classes exist to check against yet
         */
        ImmutableSet.Builder builder = ImmutableSet.builder();
        oldClasses = builder.build();
    }

    public static void check(){
        boolean ignoreOld = false;
        boolean crashOnOld = true;
        if(!ignoreOld){
            HashSet<String> repackedClasses = new HashSet<>();
            for(String clazz : oldClasses){
                if(!classExists(clazz)) continue;
                repackedClasses.add(clazz);
            }
            if (!repackedClasses.isEmpty()) {
                StringBuilder builder = new StringBuilder();
                builder.append("\nWattage is detecting that some old classes are being repacked!\n");
                for (String clazz : repackedClasses) {
                    builder.append("    ").append(clazz);
                    builder.append(", loaded from: ").append(getLoadPath(clazz));
                    builder.append("\n");
                }
                builder.append("\n");
                if (crashOnOld) {
                    throw new RuntimeException(builder.toString());
                }
                LOGGER.fatal(builder.toString());
            }
        }
    }

    public static String getLoadPath(String clazz) {
        String loadPath = "<unknown>";
        try {
            String name;
            URL resource;
            Class<?> c = Class.forName(clazz);
            ClassLoader loader = c.getClassLoader();
            if (loader == null) {
                for (loader = ClassLoader.getSystemClassLoader(); loader != null && loader.getParent() != null; loader = loader.getParent()) {
                }
            }
            if (loader != null && (resource = loader.getResource((name = c.getCanonicalName()).replace(".", "/") + ".class")) != null) {
                loadPath = resource.toString();
                int lastBang = loadPath.lastIndexOf("!");
                loadPath = loadPath.substring(0, lastBang);
                int lastSlash = loadPath.lastIndexOf("/");
                loadPath = loadPath.substring(lastSlash + 1);
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return loadPath;
    }

    private static boolean classExists(String className) {
        try {
            Class.forName(className);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

}

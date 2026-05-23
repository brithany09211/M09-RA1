import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Part3_Pipe {

   public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Sistema operatiu detectat: " + SO.nomSO());
        System.out.println("=== Fitxers .java trobats ===");

        ProcessBuilder llistar = new ProcessBuilder(SO.llistarFitxers());
 
        llistar.directory(new File("./src"));

        ProcessBuilder filtrar = new ProcessBuilder(SO.filtrar(".java"));

        llistar.redirectErrorStream(true);
        filtrar.redirectErrorStream(true);

        List<ProcessBuilder> builders = Arrays.asList(llistar, filtrar);
        List<Process> pipeline = ProcessBuilder.startPipeline(builders);

        Process ultimProces = pipeline.get(pipeline.size() - 1);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(ultimProces.getInputStream()))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                System.out.println(linia);
            }
        }

        for (Process proces : pipeline) {
            proces.waitFor();
        }

        System.out.println();
        System.out.println("Pipeline completat.");
    }
}
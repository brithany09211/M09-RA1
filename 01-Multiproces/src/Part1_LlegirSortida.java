import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Part1_LlegirSortida {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Sistema operatiu detectat: " + SO.nomSO());
        System.out.println("=== Contingut del directori ===");
        ProcessBuilder pb = new ProcessBuilder(SO.llistarFitxers());
        
        pb.directory(new File(".")); 

        pb.redirectErrorStream(true);

        Process proces = pb.start();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(proces.getInputStream()))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                System.out.println(linia);
            }
        }

        int result = proces.waitFor();
        System.out.println();
        System.out.println("El procés ha acabat amb codi: " + result);
    }
}

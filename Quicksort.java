import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Quicksort {

    public static void main(String[] args) {
        Path archivoEntrada = Paths.get("C:/quick/entradaq.txt");
        Path archivoSalida = Paths.get("C:/quick/salidaq.txt");

        
        try {
            String contenido = leerDatos(archivoEntrada);
            int[] numeros = parsearNumeros(contenido);
            String original = Arrays.toString(numeros);
            quickSort(numeros, 0, numeros.length - 1);
            String ordenado = Arrays.toString(numeros);
            String resultado = "Array Original: " + original + "\n" + "Array Ordenado: " + ordenado;
            escribirDatos(archivoSalida, resultado);
            System.out.println("¡Proceso QuickSort completado! Revisa 'output.txt'");
        } catch (IOException e) {
            System.err.println("Error al manejar archivos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al procesar los números: " + e.getMessage());
        }
    }

    private static void quickSort(int[] arr, int bajo, int alto) {
        if (bajo < alto) {
            int pi = particion(arr, bajo, alto);
            quickSort(arr, bajo, pi - 1);
            quickSort(arr, pi + 1, alto);
        }
    }
    private static int particion(int[] arr, int bajo, int alto) {

        int pivote = arr[alto];
        int i = (bajo - 1); 

        for (int j = bajo; j < alto; j++) {
            if (arr[j] <= pivote) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[alto];
        arr[alto] = temp;
        return i + 1;
    }
    private static String leerDatos(Path archivo) throws IOException {
        return new String(Files.readAllBytes(archivo)).trim();
    }
    private static void escribirDatos(Path archivo, String contenido) throws IOException {
        Files.write(archivo, contenido.getBytes());
    }
    private static int[] parsearNumeros(String str) {
        if (str.isEmpty()) {
            // Maneja el caso de archivo vacío
            return new int[0];
        } 
        String[] partes = str.split("\\s+");
        if (str.isEmpty()) {
            return new int[0];
        }    
        
        int[] numeros = new int[partes.length];
        for (int i = 0; i < partes.length; i++) {
            String parteLimpia = partes[i].trim();
            if (parteLimpia.isEmpty()) {
                // Maneja comas dobles como "5,,3"
                throw new NumberFormatException("Se encontró un valor vacío entre comas.");
            }
            numeros[i] = Integer.parseInt(parteLimpia);
        }
        return numeros;
    }
}
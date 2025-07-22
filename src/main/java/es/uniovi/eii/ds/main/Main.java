package es.uniovi.eii.ds.main;

import java.io.IOException;
import java.util.*;

import es.uniovi.eii.ds.options.OptionsSelector;

public class Main {

    public static void main(String[] args) {

        List<String> grupos = Arrays.asList(
                "01",
                "02",
                "i02",
                "06",
                "07",
                "i09",
                "10",
                "12");

        List<String> soluciones = Arrays.asList(
                "01. Temperatura y Videoclub",
                "02. Intérprete",
                "03. Editor I",
                "04. Validaciones",
                "05. Editor II. Undo",
                "06. Mapa",
                "07. Encriptar",
                "08. Encuestas",
                "09. Visitor",
                "10. Comparativa de Patrones",
                "11. Simulacro de Examen",
                "12. JHotDraw",
                "XX. Iterator");

        try {
            System.out.println("Elige un grupo:");
            int selectedGroupIndex = OptionsSelector.showOptions(grupos);

            System.out.println("Elige una solución:");
            int selectedSolutionIndex = OptionsSelector.showOptions(soluciones);

        } catch (IOException e) {
            System.err.println("Error al interactuar con la consola: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

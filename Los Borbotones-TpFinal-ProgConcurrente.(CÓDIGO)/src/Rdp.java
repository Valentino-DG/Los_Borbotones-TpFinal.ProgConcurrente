


import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Rdp {

    private Integer [][] incidenciaReal = new Integer[14][10];                  // incidencia positiva - incidencia negativa
    private Integer [][] incidenciaRealPositiva = new Integer[14][10];
    private Integer [][] incidenciaRealNegativa = new Integer[14][10];
    private Integer []   marcaInicial = new Integer[14];
    private Integer []   marcaActual  = new Integer[14];


    private int columnas;
    private int filas;


    public Rdp(){
        try {
            cargarMatriz("forward"); //Cargamos matriz forward
            cargarMatriz("backward"); //Cargamos matriz backward
            incidenciaReal = restarMatrices();
            cargarMarcaInicial();
            marcaActual = marcaInicial;

            System.out.println(marcaActual);

            disparar(2);
            disparar(3);
            disparar(1);

            for(int i=0; i<14; i++) {
                System.out.print(marcaActual[i]);
            }


        }catch(FileNotFoundException e){
        System.out.println("No hay archivo");
        e.printStackTrace();

        }

    }


    private void cargarMatriz(String tipoMatriz) throws FileNotFoundException {

        Scanner input = null;

        if(tipoMatriz.equals("forward")) {
            input = new Scanner(new File("src/ForwardsIncidence.txt"));
        }else{
            input = new Scanner(new File("src/BackwardsIncidence.txt"));
        }

        Integer [][] auxiliar = new Integer[14][10];


        for (int i = 0; i < 14; ++i) {
            for (int j = 0; j < 10; ++j) {

                if (input.hasNextInt()) {

                    auxiliar[i][j] = input.nextInt();

                }
            }
        }
        input.close();

        if(tipoMatriz.equals("forward")) {

            incidenciaRealPositiva = auxiliar;
        }else{
            incidenciaRealNegativa = auxiliar;
        }

    }

    private Integer[][] restarMatrices() {

        Integer[][] result = new Integer[14][10];
            for (int i = 0; i < 14; i++) {
                for (int j = 0; j < 10; j++) {
                    result[i][j] = incidenciaRealPositiva[i][j] - incidenciaRealNegativa[i][j];
                }
            }
            return result;
        }


    private void cargarMarcaInicial() throws FileNotFoundException{

        Scanner input = null;
        input = new Scanner(new File("src/MarcaInicial.txt"));


        for (int i = 0; i < 14; i++) {

            if (input.hasNextInt()) {

                marcaInicial[i] = input.nextInt();

            }
        }

        input.close();


    }

    private void disparar(int transicion){

        if(estaSensibilizada(transicion)){
            Integer []   marcaDeDisparo  = new Integer[10];
            for(int i=0; i<10; i++){

                if(i == (transicion-1)){
                    marcaDeDisparo[i] = 1;
                }else{
                    marcaDeDisparo[i] = 0;
                }
            }


            Integer [] aux = new Integer [14];

            aux = multiplicarMatrices(incidenciaReal, marcaDeDisparo);
            marcaActual = sumarMatrices(aux,marcaActual);

        }else{

            System.out.println("No puede realizar el disparo, la transicion " + transicion+ " no está sensibilizada");
            return;
        }




    }


    private boolean estaSensibilizada(int transicion){
        ArrayList<Integer> plazasSensibilizantes = new ArrayList<Integer>();


            for (int j = 0; j < 10; j++) {

                if(incidenciaRealNegativa[j][transicion-1]  == 1){

                    plazasSensibilizantes.add(j);
                }

            }

        for (Integer iterador : plazasSensibilizantes) {
            if(marcaActual[iterador] == 0){
                return false;
            }

        }
        return true;


    }


    private Integer[] multiplicarMatrices(Integer[][] a, Integer[] b) {

        Integer[] result = new Integer[14];

        for (int i = 0; i < 14; i++) {
            result[i] = 0;
            for (int k = 0; k < 10; k++) {
                    result[i] += a[i][k] * b[k];
            }

        }

        return result;
    }

    private Integer[] sumarMatrices(Integer[] a, Integer[] b) {

        Integer[] result = new Integer[14];

        for (int i = 0; i < 14; i++) {
                result[i] = a[i] + b[i];

        }

        return result;
    }
}



import java.io.FileWriter;
import java.io.IOException;

public class Log {
    private FileWriter myWriter;


    public Log(){

        try {
            myWriter = new FileWriter("Logeo.txt");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void logear(Integer transicion){



        if(Main.invariante1y2 + Main.invariante3 < 1000) {

            if (transicion == 6) {
                Main.invariante1y2++;

            } else if (transicion == 10) {

                Main.invariante3++;

                try {
                    myWriter.write("Se disparó el invariante 3; invariante3 = " + Main.invariante3 + "\n");
                }catch(IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            } else if(transicion == 4){
                Main.invariante1 ++;

                try {
                    myWriter.write("Se disparó el invariante 1; invariante1 = " + Main.invariante1+ "\n");
                }catch(IOException e){
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                    return;

                }

            }else if(transicion == 5){

                Main.invariante2 ++;

                try {
                    myWriter.write("Se disparó el invariante 2; invariante2 = " + Main.invariante2+ "\n");
                }catch(IOException e){
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                    return;

                }
            }

        }else{
                Main.terminado = true;
        }

    }

    public void cerrarArchivo(){
        try {
            myWriter.write("\n\n\ninvariante1: " + Main.invariante1 + "\ninvariante2: " + Main.invariante2 + "\ninvariante3: " + Main.invariante3);
            myWriter.write("\nSe ejecutó un total de: " + (Main.invariante1 + Main.invariante2 + Main.invariante3 ) +" invariantes.");
            myWriter.close();
        }catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();

        }

    }

}

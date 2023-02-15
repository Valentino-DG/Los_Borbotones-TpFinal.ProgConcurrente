import java.util.logging.*;


public class Main {
    protected static Integer invariante1y2 = 0, invariante1 = 0, invariante2 = 0, invariante3 = 0;
    protected static boolean terminado = false;

    public static void main(String[] args) {
        Log log = new Log();

        Monitor monitor = new Monitor(log);

        Shockroom shockroom = new Shockroom(monitor);
        TerapiaAndSC terapiaAndSC = new TerapiaAndSC(monitor);
        PediatriaAndSC pediatriaAndSC = new PediatriaAndSC(monitor);
        TerminarTurno terminarTurno = new TerminarTurno(monitor);
        SalaComunAndTT salaComunAndTT = new SalaComunAndTT(monitor);

        Thread doctor1 = new Thread(shockroom);
        Thread doctor2 = new Thread(terapiaAndSC);
        Thread doctor3 = new Thread(pediatriaAndSC);
        Thread doctor4 = new Thread(terminarTurno);
        Thread kinesiologo1 = new Thread(salaComunAndTT);
        Thread kinesiologo2 = new Thread(salaComunAndTT);

        doctor1.start();
        doctor2.start();
        doctor3.start();
        doctor4.start();
        kinesiologo1.start();
        kinesiologo2.start();


        while(!terminado){

            System.out.println("");

        }

        doctor1.stop();
        doctor2.stop();
        doctor3.stop();
        doctor4.stop();
        kinesiologo1.stop();
        kinesiologo2.stop();

        log.cerrarArchivo();

        System.out.println("No me rompi nada.");
        System.out.println("Cantidad de disparos de invariante1y2: " + invariante1y2);
        System.out.println("Cantidad de disparos de invariante3: " + invariante3);

    }
}

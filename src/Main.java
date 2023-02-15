mport java.util.logging.*;


public class Main {
    protected static Integer invariante1y2 = 0;
    protected static Integer invariante3 = 0;
    public static void main(String[] args) {

        Monitor monitor = new Monitor();
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
        
        while((invariante1y2 + invariante3) < 1000){
           try{ Thread.sleep(1);
            System.out.println("invariante1y2 + invariante3: " + (invariante1y2 + invariante3));
           }catch(Exception e){


           }
        }

        System.out.println("No me rompi nada.");
        System.out.println("Cantidad de disparos de invariante1y2: " + invariante1y2);
        System.out.println("Cantidad de disparos de invariante3: " + invariante3);


        //Rdp rdp = new Rdp();

    }
}

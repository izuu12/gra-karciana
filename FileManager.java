import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Scanner;
/**
 *
 * @author Izabela Szefler
 */



public class FileManager {

    File calibrationFile;


    public FileManager(String nazwapliku) {

        calibrationFile = new File(nazwapliku);
        try {
            calibrationFile.createNewFile();
        } catch (IOException ex) {
            System.out.println("blad tworzenia pliku: " + nazwapliku);
        }

    }

    /**
     * metoda ktora czyta oststnie wpisane slowo w pliku
     * ta metode wykorzystamy do odszukania po tym
     *  slowie  wynik na poziomie0 albo na poziomie1
     */
/**@param word slowo szukane w pliku.*/
/**@return zwraca szukane slowo */

    public String czytanieOstatniejLinijkiZSlowem(String word) {


        String returnSentence = "";
        String tempSentence = "";

        try (Scanner in = new Scanner(calibrationFile);) {
            do {
                try {
                    tempSentence = in.nextLine();
                } catch (java.util.NoSuchElementException ex) {
                    return "";
                }
                if (tempSentence.contains(word)) {
                    returnSentence = tempSentence;
                }
            } while (in.hasNext());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage().toString()+" == Nie ma takiego pliku kalibracyjnego");
        }
      //  System.out.println(returnSentence);
        return returnSentence;
    }

    /**
     * metoda ktora doda slowo do pliku
     * po tym slowie mozemy odszukac wynik na poziomie0 albo na poziomie1
     */

    /**@param line wpisane sowo do pliku.*/

    public void wpiszslowodopliku(String line) {

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(calibrationFile, true))) {
            printWriter.println(line);
            //System.out.println("Zapisano do pliku: "+line);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}

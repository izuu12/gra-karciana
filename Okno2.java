import javafx.scene.Scene;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.awt.Toolkit;


/**
 *
 * @author Izabela Szefler
 */

public class Okno2 extends JFrame implements ActionListener {

    /**
     *
     * nrPoziomu zmienna dzieki ktorej rozrozniamy poziomy, wybierany przy kliknieciu buttona okreslajacego trudnosc poziomu
     */

    int nrPoziomu;
    /** zmienna liczbaDobKlikniec zwieksza się po kliknieciu w karte=kart_wzor
     * umozliwia okreslenie w ktorej rundzie/poziomie jest gracz*/
    int liczbaDobKlikiec=0;
    /** ilosc klikniec w rundzie, zerowana po przejsciu do kolejnej */
    int nrKlikniecia;
    int procentowyWynikZGry;
    /** zmienna zwieksza sie po przejsciu okreslonej ilosci rund z ktorej sklada sie poziom */
    int obecnyPoziom;
    boolean wygranaZloto;
    int iloscPlikow;

/**zmienna numerKartyWzorcowej zawiera 1  pseudolosowa licze wybrana z listy 'karty' ktora
 * zawiera 6 liczb  */
    int numKartyWzorcowej;

    static Random random = new Random();
    /** lista z pseudolosowymi liczbami, zdjecia sa ponumerowane i zapisujemy tu jakie zdjecie zostanie przypisane do przycisku*/
    private ArrayList karty;

    JButton przyciski[] = new JButton[7];
    FileManager filemanager;
    FileManager filemanager1;
    /** ilosc zdjec w pliku z ktorego bedziemy wczytywac zdjecia, nazwy obrazkow to cyfry od 0-38 */

    int LICZBA_ZDJEC = 39;
    int LICZBA_WYSW_KART=6;
    int LICZBA_ROZDAN =1;
    int LICZBA_POZIOMOW = 3;
    int LICZBA_WSZYSTKICH_DOBRYCH_KLIKNIEC = LICZBA_ROZDAN * LICZBA_POZIOMOW;


    int LEVEL_BRAZ = 50;
    int LEVEL_SREBRO = 70;
    int LEVEL_ZLOTO = 90;

/** mnoznik wyniku */
    int NAGRODA = 5;
    int LICZBA_MAX_WYNIK = LICZBA_ROZDAN* NAGRODA*LICZBA_POZIOMOW ;


    JLabel napisPomocy;
    JLabel ktoraRunda;


    JLabel background1;
    JLabel background2;
    JLabel background3;
    JLabel background4;
    ImageIcon tlo1 = new ImageIcon("zdjecia/zdjtla/tlo2.jpg");
    ImageIcon tlo2 = new ImageIcon("zdjecia/zdjtla/tlo3.jpg");
    ImageIcon tlo3 = new ImageIcon("zdjecia/zdjtla/tlo4.jpg");



    static JButton karta_0;
    static JButton karta_1;
    static JButton karta_2;
    static JButton karta_3;
    static JButton karta_4;
    static JButton karta_5;
    static JButton karta_wzor;
    static  JButton przyciskZacznijOdNowa;

    JButton guzikPomoc;


    ImageIcon zle = new ImageIcon("zdjecia/zdjikonki/zakaz.png");

    ImageIcon wroc = new ImageIcon("zdjecia/puchary/7.jpg");

    /**zmienna wynik zwieksza się po opasowaniu karty do karty_wzor */
    int wynik;

    static int wysokos_kar = 160, szerokosc_kar = 200;
    String tlo;
    String tekst1 = "Wybierz kartę pasującą do wzorcowej";
    String tekst2 = "Wybrałeś złą kartę, spróbój wybrać inną";
    String uzyskanyWynik = "uzyskane punkty : ";
    /** tekst pobrany z klasy FileManager, zawiera poprzedni uzysany wynik na poziomie latwym*/
    String poprzedniWynikPoz0;
    /** tekst pobrany z klasy FileManager, zawiera poprzedni uzysany wynik na poziomie trudnym*/
    String poprzedniWynikPoz1;

/** dodanie buttons i labels do okna, wyswietlenie 1. rundy*/
    Okno2(String nazwa, int nrPoziomu) {

        super(nazwa);//metoda super wywołuje konstruktor nadklasy
        setSize(1024, 576);
        this.setLocation(250,135);
       // this.setLocationByPlatform(true);
        setResizable(false);
        setLayout(null);


        this.validate();
        wynik = 0;

        filemanager = new FileManager("wynik.txt");
        filemanager1 = new FileManager("sciezka.txt");
        System.out.println("ODCZYTANA LINIJKA= "+filemanager.czytanieOstatniejLinijkiZSlowem("poziom0"));
        System.out.println("ODCZYTANA LINIJKA= "+filemanager.czytanieOstatniejLinijkiZSlowem("poziom1"));
        poprzedniWynikPoz0 =(filemanager.czytanieOstatniejLinijkiZSlowem("poziom0"));
        poprzedniWynikPoz1 =(filemanager.czytanieOstatniejLinijkiZSlowem("poziom1"));
        this.nrPoziomu = nrPoziomu;

        karta_0 =  new JButton();
        karta_1 =  new JButton();
        karta_2 =  new JButton();
        karta_3 =  new JButton();
        karta_4 =  new JButton();
        karta_5 =  new JButton();
        karta_wzor =  new JButton();
        guzikPomoc = new JButton(zle);

        przyciskZacznijOdNowa = new JButton(wroc);
        napisPomocy= new JLabel(tekst1);
        ktoraRunda = new JLabel(1+"/"+LICZBA_ROZDAN);




        przyciski[0] = karta_0;
        przyciski [1]= karta_1;
        przyciski [2]= karta_2;
        przyciski [3]= karta_3;
        przyciski [4]= karta_4;
        przyciski[5]= karta_5;
        przyciski[6]= karta_wzor;


        karta_0.setBounds(20,20,200,160);
        karta_1.setBounds(20,192,szerokosc_kar,wysokos_kar);
        karta_2.setBounds(20,364,szerokosc_kar,wysokos_kar);
        karta_3.setBounds(784,20,szerokosc_kar,wysokos_kar);
        karta_4.setBounds(784,192,szerokosc_kar,wysokos_kar);
        karta_5.setBounds(784,364,szerokosc_kar,wysokos_kar);
        karta_wzor.setBounds(300,200,szerokosc_kar,wysokos_kar);

        przyciskZacznijOdNowa.setBounds(720,475,50,50);
        guzikPomoc.setBounds(300,40,80,80);


        napisPomocy.setBounds(250,100,500,100);
        ktoraRunda.setBounds(480,10,300,100);



        przyciskZacznijOdNowa.addActionListener(this);
        guzikPomoc.addActionListener(this);


        for(int i=0; i<przyciski.length-1;i++)
        {
            przyciski[i].addActionListener(this);
            add(przyciski[i]);

        }
        add(karta_wzor);


        add(guzikPomoc);add(przyciskZacznijOdNowa);
        add(ktoraRunda);add(napisPomocy);


       guzikPomoc.setVisible(false);

        ktoraRunda.setFont(new Font("SansSerif",Font.BOLD,40));
        ktoraRunda.setForeground(Color.orange);
        napisPomocy.setForeground(Color.orange);
        /**zmiana napisu po zlym pliknieciu na napis po dobrym kliknieciu */

        zmianaNapisowPodobrymKliknieciu();
        /** wczytanie na buttons nowych zdjec */
        wczytywanieKart();
        /** wyswietlenie odpowiedniej instrukcji po kliknieciu poziomu latwego albo trudnego */
        wyswietlInstrukcje();

    }

/** wpisanie zdjec na buttons, rozroznianie rundy, wybranie karty wzorciewj sposrod 6 wpisanych zdjec na buttons  */
    private void wczytywanieKart(){

       // System.out.println("ODCZYTANA LINIJKA ILOSC PLIKOW gdy wygral= "+filemanager1.czytanieOstatniejLinijkiZSlowem(" "));

        wpisanieLiczbPseudolowowychDoListy();

        numKartyWzorcowej = (int)karty.get(random.nextInt(LICZBA_WYSW_KART));
     //   System.out.println("NUMER KARTY wZORCOWEJ "+numKartyWzorcowej);
/**wpisanie kart na buttons, korzysta z listy pseudolosowych liczb */
        wpisanieKartNaButton();
/**label ktory wyswietla obecna runde w poziomie  */
        wyswietlKtoraRunda();

    }

/** rozroznia ktory button zostal klikniety, przypisanie wartosci do buttons
 * zwieksza nr klikniecia przy wybraniu zlej karty */
    @Override
    public void actionPerformed(ActionEvent e) {
        int nrButtona =0;

        Object zrodlo = e.getSource();

        for(int i=0; i<przyciski.length-1;i++)
        {
            if(zrodlo==przyciski[i]){
                nrButtona = i;
                nrKlikniecia++;
            }
        }
            if(zrodlo == przyciskZacznijOdNowa){
                this.dispose();

            }


            if (karty.get(nrButtona).equals(numKartyWzorcowej)){


                System.out.println("SUPER WYBRALES");
                System.out.println(nrKlikniecia+" numer klikniecia");

                algorytmObliczaniaWyniku();
                zmianaNapisowPodobrymKliknieciu();

                nrKlikniecia = 0;
                liczbaDobKlikiec++;

                guzikPomoc.setVisible(false);
                napisPomocy.setText(tekst1);
                //warunek konczacy gre
                if(liczbaDobKlikiec ==LICZBA_WSZYSTKICH_DOBRYCH_KLIKNIEC) {
                    filemanager.wpiszslowodopliku("poziom"+nrPoziomu+"," + wynik);
                // System.out.println("ODCZYTANA LINIJKA ILOSC PLIKOW gdy wygral= "+filemanager1.czytanieOstatniejLinijkiZSlowem(" "));
                    analizaWyniku();


                  //  this.dispose();
                }
                else{
                    napisPomocy.setText(tekst1);
                    wczytywanieKart();}

            }
            else {
                if(liczbaDobKlikiec<LICZBA_WSZYSTKICH_DOBRYCH_KLIKNIEC){
                System.out.println("ZLE");
                guzikPomoc.setVisible(true);
                zmianaNapisowPoZlymKliknieciu();
                napisPomocy.setText(tekst2);}


            }
        }
        /**pseudolosowanie liczb - liczby to nazwy zdjec, warunek sprawdzenie czy wyloswany nr zostalo juz dodane do listy*/
        private void wpisanieLiczbPseudolowowychDoListy() {

            karty = new ArrayList(LICZBA_WYSW_KART);
            /**zmienna do ktorej zapisujemy pseudolosowa wylosowane 1 cyfre z 39 */
            int randomNb = 0;
            randomNb = random.nextInt(LICZBA_ZDJEC );
            for (int i=0; i< LICZBA_WYSW_KART; i++){

// zapobiega wylosowaniu takich samych kart w rundzie
                //powtarza az wylosuje inna cyfre
                while(karty.contains(randomNb)) {

                    randomNb = random.nextInt(LICZBA_ZDJEC);

                         System.out.println("LOSUJE "+randomNb);
                }
                karty.add(randomNb);
        //         System.out.println("DODAJE "+randomNb);
            }
//        for (int i =0; i<karty.size(); i++){
//            System.out.println("Tab["+i+"] = "+karty.get(i));
//        }
        }
        /** wpisanie obrazka z cyfra zapisanea w liscie 'karty'. nazwy obrazkow to cyfry od 1-38*/
        private void wpisanieKartNaButton(){
            System.out.println(karty);
            if(liczbaDobKlikiec<LICZBA_ROZDAN ){

                // WRZUCENIE OBRAZKOW NA BUTTONY
                for(int i=0; i<LICZBA_WYSW_KART; i++)
                {
                    przyciski[i].setIcon(new ImageIcon("zdjecia/zdjZwi/"+karty.get(i)+".jpg"));

                }
                przyciski[6].setIcon(new ImageIcon("zdjecia/zdjZwi/"+numKartyWzorcowej+".jpg"));
                this.obecnyPoziom = 1;
                background1=new JLabel(tlo1);
                background1.setBounds(0,0,1024,576);
                add(background1);
                background1.setVisible(true);



            }
            if( liczbaDobKlikiec>=LICZBA_ROZDAN && liczbaDobKlikiec<LICZBA_ROZDAN*2)
            {this.obecnyPoziom =2;

                for(int i=0; i<LICZBA_WYSW_KART; i++)
                {
                    przyciski[i].setIcon(new ImageIcon("zdjecia/zdjliter/"+karty.get(i)+".jpg"));

                }
                przyciski[6].setIcon(new ImageIcon("zdjecia/zdjliter/"+numKartyWzorcowej+".jpg"));
                background1.setVisible(false);
                background2=new JLabel(tlo2);
                background2.setBounds(0,0,1024,576);
                add(this.background2);



            }
            if(liczbaDobKlikiec>=LICZBA_ROZDAN*2 && liczbaDobKlikiec<=LICZBA_WSZYSTKICH_DOBRYCH_KLIKNIEC && nrPoziomu==0 ){
                this.obecnyPoziom = 3;
                for(int i=0; i<LICZBA_WYSW_KART; i++)
                {
                    przyciski[i].setIcon(new ImageIcon("zdjecia/ksztalty/"+karty.get(i)+".jpg"));

                }
                przyciski[6].setIcon(new ImageIcon("zdjecia/ksztalty/"+numKartyWzorcowej+".jpg"));
                background3=new JLabel(tlo3);
                background3.setBounds(0,0,1024,576);
                add(this.background3);
                this.background2.setVisible(false);
                this.background1.setVisible(false);
                this.background3.setVisible(true);

            }
            if(liczbaDobKlikiec>=LICZBA_ROZDAN*2 && liczbaDobKlikiec<=LICZBA_WSZYSTKICH_DOBRYCH_KLIKNIEC && nrPoziomu==1 ){
                obecnyPoziom =3;
                for(int i=0; i<LICZBA_WYSW_KART; i++)
                {
                    przyciski[i].setIcon(new ImageIcon("zdjecia/zdjksztaltyTru/"+karty.get(i)+".jpg"));

                }
                przyciski[6].setIcon(new ImageIcon("zdjecia/zdjksztaltyTru/"+numKartyWzorcowej+".jpg"));
                background3=new JLabel(tlo3);
                background3.setBounds(0,0,1024,576);
                add(this.background3);
                this.background2.setVisible(false);
                this.background3.setVisible(true);
                this.background1.setVisible(false);


            }
            /** zmienia napis wyswietlany w label po zlym kliknieciu */
        }
        private void zmianaNapisowPoZlymKliknieciu(){
            napisPomocy.setForeground(Color.red);
            napisPomocy.setFont(new Font("SansSerif",Font.BOLD,20));
        }
        /** zmienia naspis wyswietlany w label po dobrym kliknieciu*/
    private void zmianaNapisowPodobrymKliknieciu(){
        napisPomocy.setFont(new Font("SansSerif",Font.BOLD,28));
        napisPomocy.setForeground(Color.black);
    }

    /** w zaleznosci od poziomu o*/

      private void algorytmObliczaniaWyniku (){
          /** zmienna ktora po zlym kliknieciu na poziomie latwym zmniejsza nagrode o liczbe klikniec*/
            int wartoscDodana;

            if (nrPoziomu == 0){
                wartoscDodana = NAGRODA - nrKlikniecia +1;
                if (wartoscDodana<0) {
                    wartoscDodana = 0;
                }
                this.wynik = this.wynik + wartoscDodana;
            }

            if (nrPoziomu == 1 && nrKlikniecia == 1 ){
                this.wynik = this.wynik + 5;
          }
            System.out.println("PO ROZDANIU TWOJ WYNIK TO:  "+ wynik);
        }

/** wyswietla w label ktora runda w danym poziomie*/
    private void wyswietlKtoraRunda(){
        this.liczbaDobKlikiec=liczbaDobKlikiec;
        /** ilosc klikniec w danym poziomie ktora jest wiekszana po dobrym kliknieciu*/
        int dobreKlikWPoz = 1;

        if(obecnyPoziom==1){
            dobreKlikWPoz = liczbaDobKlikiec+1;
        }

        else if(obecnyPoziom==2){
                dobreKlikWPoz = liczbaDobKlikiec+1 - LICZBA_ROZDAN;
            }
        if(obecnyPoziom==3){
            dobreKlikWPoz = liczbaDobKlikiec+1 - LICZBA_ROZDAN*2;
        }

        if(liczbaDobKlikiec<LICZBA_WSZYSTKICH_DOBRYCH_KLIKNIEC) {
            ktoraRunda.setText(obecnyPoziom+"poziom"+","+dobreKlikWPoz + "/" + LICZBA_ROZDAN);
        }

    }



/** zmiana napisow label , wyswietlenie poprzedniego wyniku, przedstawienie wyniku w procentach,
 * wyswietlenie pucharu o odpowiednim kolorze w zal od wyniiku */

    private void analizaWyniku () {


        napisPomocy.setFont(new Font("SansSerif",Font.BOLD,17));
        napisPomocy.setForeground(Color.black);
        ktoraRunda.setBounds(460,10,400,100);
        ktoraRunda.setFont(new Font("SansSerif",Font.BOLD,20));

if(nrPoziomu==0)
       ktoraRunda.setText("<html>Poprzedni uzyskany wynik:<br><br> "+poprzedniWynikPoz0+ "/"+LICZBA_MAX_WYNIK +"<html>");
if (nrPoziomu==1)
{
    ktoraRunda.setText("<html>Poprzedni uzyskany wynik:<br><br> "+poprzedniWynikPoz1+ "/"+LICZBA_MAX_WYNIK +"<html>");
}
/** zmienna zawiera wynik przedstawiony w procentach */
       int  procentowyWynikZGry = (wynik *100)/LICZBA_MAX_WYNIK;


            if (procentowyWynikZGry > LEVEL_ZLOTO) {
                System.out.println("***ZLOTO*** " + procentowyWynikZGry);
                wygranaZloto=true;
                 napisPomocy.setText("Usyskałeś: " + wynik + " na " + LICZBA_MAX_WYNIK + "punktów. Prawidłowe wybory w " + procentowyWynikZGry + "%");
                for (int i = 0; i < przyciski.length - 1; i++) {
                     przyciski[i].setIcon(new ImageIcon("zdjecia/puchary/4.jpg"));
                     przyciski[6].setIcon(new ImageIcon("zdjecia/puchary/1.jpg"));
                     przyciski[i].setEnabled(false);
                     karta_wzor.setBounds(350, 170, 280, 360);
                     napisPomocy.setText("Usyskałeś: " + wynik + " na " + LICZBA_MAX_WYNIK + "punktów. Prawidłowe wybory w " + procentowyWynikZGry + "%");
                     guzikPomoc.setVisible(false);


                }
                tlo = "zdjecia/zdjtla/tlo5.jpg";
                background3.setVisible(false);

                this.background1.setText("zdjecia/zdjtla/tlo5.jpg");
                background2.setVisible(false);
                ImageIcon icon = new ImageIcon("zdjecia/zdjtla/tlo3.jpg");
                background4.setIcon(icon);
                //getContentPane().setBackground(Color.decode("#bdb76b"));;


            }   else if (procentowyWynikZGry > LEVEL_SREBRO) {
                         System.out.println("***SREBRO*** " + procentowyWynikZGry);
                wygranaZloto=false;
                     napisPomocy.setText("Usyskałeś: " + wynik + " na " + LICZBA_MAX_WYNIK + "punktów. Prawidłowe wybory w " + procentowyWynikZGry + "%");
                      for (int i = 0; i < przyciski.length - 1; i++) {
                        przyciski[i].setIcon(new ImageIcon("zdjecia/puchary/6.jpg"));
                        przyciski[i].setEnabled(false);
                          getContentPane().setBackground(Color.decode("#bdb76b"));
                }

                karta_wzor.setIcon(new ImageIcon("zdjecia/puchary/2.jpg"));
                karta_wzor.setBounds(382, 170, 260, 320);
                tlo = "zdjecia/zdjtla/tlo6.jpg";
                background3.setVisible(false);
                wczytywanietla();


        }
             else {
                System.out.println("***BRAZ*** " + procentowyWynikZGry);
                wygranaZloto=false;
                przyciski[6].setIcon(new ImageIcon("zdjecia/puchary/3.jpg"));
                napisPomocy.setText("Usyskałeś: " + wynik + " na " + LICZBA_MAX_WYNIK + "punktów. Prawidłowe wybory w " + procentowyWynikZGry + "%");
                for (int i = 0; i < przyciski.length - 1; i++) {
                przyciski[i].setIcon(new ImageIcon("zdjecia/puchary/5.jpg"));
                przyciski[i].setEnabled(false);
                karta_wzor.setBounds(402, 170, 220, 300);
                }
                background3.setVisible(false);
                tlo = "zdjecia/zdjtla/tlo7.jpg";
                wczytywanietla();

            }
        powiekszeniePrzciskow();





    }
    /** powiekszenie przyciskuw po wygranie */
    public void powiekszeniePrzciskow(){
        karta_0.setBounds(20,0,szerokosc_kar+10,wysokos_kar+40);
        karta_1.setBounds(20,192,szerokosc_kar+10,wysokos_kar+20);
        karta_2.setBounds(20,364,szerokosc_kar+10,wysokos_kar+20);
        karta_3.setBounds(784,0,szerokosc_kar+10,wysokos_kar+40);
        karta_4.setBounds(784,192,szerokosc_kar+10,wysokos_kar+20);
        karta_5.setBounds(784,364,szerokosc_kar+10,wysokos_kar+20);

    }
    /** pojawienie sie okienka z instrukcja po kliknieciu przycisku z poziomem*/
    private void wyswietlInstrukcje(){
        String instrukcjalat = ("<html>Witej graczu! <br><br> Zasady gry są niezwykle proste, zapoznaj się z nimi przed grą : <br><br> * Spośród 6 kart należy dopasować kartę wzorcową, która wyświetlona jest na środku ekranu." +
                "<br>* Należy przesunąć kursor myszki na pasującą kartę i kliknąć ją lewym przyciskiem myszy. <br> * Prawidłowe kliknięcie zwiększy Twój wynik o nagrodę w wysokości 5 punktów." +
                " <br>* Wraz z kliknięciem nieprawidłowej karty Nagroda zmniejsza się o 1. <br>* Rozgrywka składa się z trzech poziomów. <br>* W każdym z poziomów następuje 6 rozdań." +
                "jeśli je przejdziesz zostanie wyświetlony wynik.<br><br>POWODZENIA ! <html>");
        String instrukcjatru = ("<html>Witej graczu! <br><br> Zasady gry są niezwykle proste, zapoznaj się z nimi przed grą : <br><br> * Spośród 6 kart należy dopasować kartę wzorcową, która wyświetlona jest na środku ekranu." +
                "<br>* Należy przesunąć kursor myszki na pasującą kartę i kliknąć ją lewym przyciskiem myszy. <br> * Prawidłowe kliknięcie zwiększy Twój wynik o nagrodę w wysokości 5 punktów." +
                " <br>* Wraz z kliknięciem nieprawidłowej karty tracisz możliwość uzyskania nagrody. <br>* Rozgrywka składa się z trzech poziomów. <br>* W każdym z poziomów następuje 6 rozdań." +
                "jeśli je przejdziesz zostanie wyświetlony wynik.<br><br>POWODZENIA ! <html>");
        String instrukcjaWczytaniaZdj =  ("<html>Gratuluję wygranej!!! <br><br> Aby wczytać zdjęcia postępuj zgodnie z instrujcją:<br><br>*zmień nazwę zdjęciom które chesz dodać do gry<br><br>" +
                "*nazwa zdjęca ma być liczbą całkowitą<br>pierwsze dodane zdjęcie ma mieć nazwę 0<br>kolejne zjęcie ma być liczbą całkowitą większą od nazwy poprzedniego zdjęcia<br><html>");
        if(liczbaDobKlikiec==LICZBA_WSZYSTKICH_DOBRYCH_KLIKNIEC)
        {
            JOptionPane.showMessageDialog(null,new JLabel(instrukcjaWczytaniaZdj,JLabel.CENTER),"Instrukcja gry ",JOptionPane.PLAIN_MESSAGE);
        }
        if(nrPoziomu == 0 && liczbaDobKlikiec<LICZBA_WSZYSTKICH_DOBRYCH_KLIKNIEC){
            JOptionPane.showMessageDialog(null,new JLabel(instrukcjalat,JLabel.CENTER),"Instrukcja gry ",JOptionPane.PLAIN_MESSAGE);
        }
        else if(nrPoziomu==1&& liczbaDobKlikiec<LICZBA_WSZYSTKICH_DOBRYCH_KLIKNIEC)
            JOptionPane.showMessageDialog(null,new JLabel(instrukcjatru,JLabel.CENTER),"Instrukcja gry ",JOptionPane.PLAIN_MESSAGE);


        }
        //** wczytanie tla po przejsciu gry */

    public void wczytywanietla()
    {
        this.background4=new JLabel(new ImageIcon(this.tlo));
        this.background4.setBounds(0,0,1024,576);
        add(this.background4);






    }




}

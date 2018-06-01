import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Izabela Szefler
 */

/** Klasa tworzaca okno z przyciskami umozliwiajacymi przejscie do wybranych poziomow dziedziczy po klasie JFrame */
class Okno extends JFrame {
/** konstruktor klasy okno*/
    Okno(String nazwa){

        super(nazwa);//metoda super wywołuje konstruktor nadklasy
        setSize(1024,576);
        this.setLocation(250,135);
        setResizable(false);
        setLayout(null);

        ImageIcon icon = new ImageIcon("zdjecia/zdjikonki/pozlat.png");
        JLabel background=new JLabel(new ImageIcon("zdjecia/zdjtla/tlo1.jpg"));



    //     image= icon.getImage();
        ImageIcon trudnosc1 = new ImageIcon("zdjecia/zdjikonki/pozlat.png");
        ImageIcon trudnosc2 = new ImageIcon("zdjecia/zdjikonki/poztru.png");

        JButton button1 = new JButton(trudnosc1);
        JButton button2= new JButton(trudnosc2);
        JLabel tekst_lat1 = new JLabel("Wybierz poziom trudności gry :");







        button1.setBounds(230,200,250,200);
        tekst_lat1.setBounds(200,80,750,150);
        button2.setBounds(520,200,250,200);
        tekst_lat1.setForeground(Color.BLUE);

        tekst_lat1.setFont(new Font("SansSerif",Font.BOLD,45));
        add(button1);
        add(button2);
        add(tekst_lat1);

        background.setBounds(0,0,1024,576);
        add(background);
        /** wywolanie klasy B1 ktora imozliwia przejscie do nastepnego poziomu po kliknieciu buttona*/
        button1.addActionListener(new B1());
        button2.addActionListener(new B2());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    /**
     * tworzy obiekt klasy Okno2 ktora zawiera mechaniz z gra
     * klasa ktora po kliknieciu przycisku z poziomem latwym otwiera okno z gra na poziomie 0 (latwy)
     */

    class B1 implements ActionListener {
       public void actionPerformed(ActionEvent e) {

           /**
            *
            * Stworzznie obiektu klasy Okno2 ktora umozliwi przejscie do gry
            */
           Okno2 okno2 = new Okno2("Bystrzak",0);
           okno2.setVisible(true);


       }
   }
    /**
     * klasa ktora po kliknieciu przycisku z poziomem trudnym otwiera okno z gra na poziomie 1 (trudny)
     */

    class B2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            /**
             *
             * Stworzznie obiektu klasy Okno2 ktora umozliwi przejscie do gry na poziomie trudnosci trudny
             */
            Okno2 okno2 = new Okno2("Bystrzak",1);
            okno2.setVisible(true);


        }
    }

}


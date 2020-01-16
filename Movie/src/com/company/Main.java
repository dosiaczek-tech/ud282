package com.company;
import java.util.Scanner;
import java.io.File;

/** pseudo code
 * 1) Inicjalizuje wybranie pliku z tytułami
 * 2) Inicjalizuje tabice z tytułmi
 * 3) Ustawiam funkcje Math.random zeby wybral randomowy tytul z tabicy o i-liczbie tytulow
 * 4) Licze poprzez funkcje .length ile tytuł ma liter i wypisuje System.out
 *    Your are guessing: i dodatkowo liczbe "_"
 * 5) Robie loopa bo uzytkownik ma tylko 10 szans i na
 *    samym poczatko wypisuje System.out You have guessed (liczba) wrong letters:
 * 6) Prosze uzytkownika zeby wpisal pierwsza litere poprzedzajac tekstem System.out
 *    You are guessing: -> prosze o input uzytkownika
 * 7) If String indexOf() method sie zgodzi z tym co jest w tablicy charow tego tytułu to wypisz
 *    te litere, reszte zostaw jako "_"
 * 8) Jezeli sie nie zgodzi to odejmij od loopa 1 bo ma tylko 10 szans na wpisanie dobrej
 * 9) Jezeli wszystkie sa ok to wypisz ze wygral, jezeli nie sa ok to wypisz, ze przegral
 */
public class Main {

    public static void main(String[] args) throws Exception {
        File file = new File("movies.txt");
        LetterSet wrongLetters = new LetterSet();
        Scanner scanner = new Scanner(System.in);
        Scanner scannerFile = new Scanner(file);
        char guess;

        //Initialise the array of titles
        String[] titles = new String [25];

        for ( int i = 0; i < 25; i++){
            //Pobiera nastepna linijke i zapisuje w zmiennej line
            String line = scannerFile.nextLine();
            titles[i] = line;
        }

        //Randomly picks the title from the array of titles
        int randomNumber = (int) (Math.random() * titles.length);
        String randomTitle = titles[randomNumber];
        //System.out.println(randomTitle);

        //Check the randomTitle and print the number of letters in that randomTitle
        int numberOfLetters = randomTitle.length();
        System.out.println(numberOfLetters);

        //Tutaj zaczyna sie gra
        System.out.println("Your are guessing: ");

        //Wypisujemy podkreslniki w zaleznosci od liczby liter w tytutle
        for (int i = 0; i < numberOfLetters; i++) {
            System.out.print("_ ");
        }
        //Printout enter
        System.out.println();

        //Tablica typu boolean, ktora dla kazdej pozycji trzyma czy jest ona odgadnieta
        boolean [] guessedPositions = new boolean [numberOfLetters];
        //Na poczatku wszystkie pozycje sie nieodganiete
        for (int p = 0; p < numberOfLetters; p++) {
            guessedPositions[p] = false;
        }
        //Glowny petla gry, gdzie wywoluje funkcje countLetters z obiektu wrongLetters. Mozna zgadnac zle tylko 10 roznych literek
        while (wrongLetters.countLetters() < 10) {
            //Czy wszystkie guestedPositions rownaja sie true. Skrot myslowy: Jezeli tablica jest pusta to jest true
            boolean allTrue = true;
            for (int n = 0; n < numberOfLetters; n++) {
                //
                if (guessedPositions[n] == false) {
                    allTrue = false;
                    break;
                }
            }
            if (allTrue){
                System.out.println("You won");
                break;
            }

            System.out.print("You have guessed " + "(" + wrongLetters.countLetters() + ") wrong letters: ");
            wrongLetters.printLetters();
            System.out.println();
            System.out.println("Guess a letter");
            //I'm asking a user for an input
            guess = scanner.next().charAt(0);
            System.out.println("You have entered: " + guess);

            //Ten boolean mowi o tym, czy znalazlam te konkretna litere z tytulu. Ona jest false bo jeszcze nic nie przeszukalam nic w stringu. Zaczyna sie od false.
            boolean foundLetter = false;
            for (int n = 0; n < numberOfLetters; n++) {
                //Szukam czy char z randomTitle rowna sie charowi podanemu przez uzytkownika jezeli tak to foundLetter jest true i wychodze z petli
                if (randomTitle.charAt(n) == guess) {
                    foundLetter = true;
                    break;
                }
            }
            /*By definition do ifa wchodze jak jest true.
             *Sa trzy przypadki:
             * 1) Czlowiek zgadl dobrze
             * 2) Czlowiek zgadl zle, ale juz zgadywal te literke
             * 3) Czlowiek zgadl zle i to jest nowa literka - wczesniej nie zgadywal
             */
            if (foundLetter) {
                //This means that is a right char i wypisz
                for (int s = 0; s < numberOfLetters; s++) {
                    //Jezeli nasz char guess rowna sie char z tytulu lub obecna pozycja (bo s) jest juz odgadnieta
                    if (randomTitle.charAt(s) == guess || guessedPositions[s] == true) {
                        //Tutaj zapamimetujemy ze pozycja jest odgadnieta
                        guessedPositions[s] = true;
                        //Wypisujemy ten char
                        System.out.print(randomTitle.charAt(s));
                        //Wypisujemy spacje
                        System.out.print(" ");
                    //Jezeli nie jest nasz prawidlowy z tytulu char i nie jest odgadniety to wypisz "_"
                    } else {
                        System.out.print("_ ");
                    }
                }
                //Wypisz enter
                System.out.println();
            //Drugi przypadek. Czlowiek zle zgadl i juz zgadywal te literke
            } else if (wrongLetters.contains(guess) == true){
            //Trzeci przypadek, gdzie czlowiek wpisal literke i jest zla. To jest literka, ktorej wczesnie nie zgadywal
            } else {
                wrongLetters.add(guess);
            }
        }
    }
}
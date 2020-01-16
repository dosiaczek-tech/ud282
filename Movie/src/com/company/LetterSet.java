    package com.company;

    //Klasa co przechowuje zbior liter
    public class LetterSet {
        //Dwie zmienne tablice charow o nazwie set i ile liter tam trzymamy (uniwersalnych liter - do dobrych i zlych)
        char [] set;
        int letterCount;

        //Constructor
        LetterSet (){
            //Inicjalizujemy tablice charow, zakladamy, ze 100 bedzie wystarczajaco
            this.set = new char [100];
            //Zaczynamy nasz letter Count od zero bo zbior jest pusty
            this.letterCount = 0;
        }
        //Funkcja, ktora zwraca boolean i przyjmuje jako parametr char, Sprawdza czy w zbiorze jest literka
        boolean contains (char letter) {
            for (int i = 0; i < letterCount; i++) {
                if (set[i] == letter) {
                    return true;
                }
            }
            return false;
        }
        //Funkcja, ktora dodaje literke do zbioru. Ta funkcja zaklada, ze tej literki nie ma w zbiorze
        void add (char addLetter){
            set[letterCount] = addLetter;
            letterCount++;
        }
        //Funkcja, ktora zwraca ilosc literek w zbiorze
        int countLetters (){
           return letterCount;
        }
        //Funkcja, ktora wypisuje na ekran literki ze zbioru oddzielone spacja
        void printLetters(){
            for(int i = 0; i < letterCount; i++)
            {
                System.out.print(set [i] + " ");
            }
        }
    }

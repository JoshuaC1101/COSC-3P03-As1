package decryptga;
import java.util.*;
import java.io.*;

public class GA {

    int mutorations;
    int popsize = 1000;
    int crate;
    int Generationsize;
    int GenLevel;
    int chromelength = 26;
    Random Rand = new Random(800853);
    String encrypt;
    Scanner Data;

    char[][] Primary = new char[popsize][chromelength];
    char[][] temp;
    char[] Best;

    public void OpenData(){
        try {
            //Set the Data to the File
            Data = new Scanner(new FileInputStream("Data1.txt"));
            //Catch the end of File Error
        } catch (FileNotFoundException e) {
            System.out.print("Data not found");
        }
    }
    public void ReadData(){
        chromelength = Data.nextInt();
        try {
            while (Data.hasNextLine()) {
                encrypt = Data.nextLine();
            }
        } catch (Exception e) {
        }
    }

    public void generatechom(int length){
        char Letter;
        for(int j = 0; j < popsize; j++){
            for(int i = 0; i < length; i++){
                int RandoNumber = (char)(Rand.nextInt(27));
                Letter = (char)(RandoNumber + ('a'));
                if(Letter == '{'){
                 Letter = '-';
                }
                Primary[j][i] = Letter;
//                System.out.print(Primary[j][i]);
            }
//            System.out.println("");
        }
    }

    public void bestGa(){
        double best = Evaluation.fitness(String.copyValueOf(Primary[0]),encrypt);
        double Average;
        Average = best;
//        System.out.print("Starting Best");
//        System.out.println(best);
        for(int i = 0; i < popsize; i++ ){
            double currnet = Evaluation.fitness(String.copyValueOf(Primary[i]),encrypt);
//            System.out.println(currnet);
            Average = Average + currnet;
            if(best < currnet){
                best = Evaluation.fitness(String.copyValueOf(Primary[i]),encrypt);
                Best = Primary[i];
//                System.out.println(best);
//                System.out.print(Best);
            }
        }
        Average = (Average/popsize);
        System.out.println(Average);
//        System.out.print(Best);

    }

    public void TwoCrossover(){
    }

    //Swap 50/50 to take betweem Dad or Mom
    public void Uniformcrossover(){
    }

    public void tournmentSelection(){
    }

    public void RepMuatio(){
    }
    //End of Code
}

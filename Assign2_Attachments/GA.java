package decryptga;
import java.util.*;
import java.io.*;

public class GA {

    int mutartionpercent;
    int popsize = 100;
    int crate;
    int generationsize = 2;
    int genLevel ;
    int chromelength = 26;
    Random Rand = new Random(800853);
    String encrypt;
    Scanner Data;
    int K = 2;
    int globalindex = 0;

    char[][] Primary = new char[popsize][chromelength];
    char[][] temp = new char[popsize][chromelength];
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
//            System.out.print(currnet);
//            System.out.print(" ");
//            System.out.println(Primary[i]);
            Average = Average + currnet;
            if(best < currnet){
                best = Evaluation.fitness(String.copyValueOf(Primary[i]),encrypt);
                Best = Primary[i];
//                System.out.println(best);
//                System.out.print(Best);
            }
        }
        Average = (Average/popsize);
//        System.out.println("Average" + Average);
//        System.out.println(Best);
    }

    public void SingleCrossover() {
        char[] childA;
        char[] childB;
        char[] parentA;
        char[] parentB;
        int ParentindexA = Rand.nextInt(popsize);
        int ParentindexB = Rand.nextInt(popsize);
        int ParentCross = Rand.nextInt(chromelength);

        while (ParentindexA == ParentindexB) {
            ParentindexB = Rand.nextInt(popsize);
        }
        parentA = Primary[ParentindexB];
        parentB = Primary[ParentindexA];




    }

    public void Uniformcrossover(){
        char[] childA = new char[chromelength];
        char[] childB = new char[chromelength];
        char[] parentA;
        char[] parentB;
        int ParentindexA;
        int ParentindexB;
        int[] mask = new int[chromelength];
        int binary;
        int keep;

        for(int x = 0; x < popsize/2; x++) {
            ParentindexA = Rand.nextInt(popsize);
            ParentindexB = Rand.nextInt(popsize);
            //Creating the int array of 1's or 0's
            while (ParentindexA == ParentindexB) {
                ParentindexB = Rand.nextInt(popsize);
            }
            parentA = Primary[ParentindexB];
            parentB = Primary[ParentindexA];

            //Mask Creation
            for (int i = 0; i < chromelength; i++) {
                binary = Rand.nextInt(2);
                mask[i] = binary;
            }
            //uniformCrossOver Start
            for (int j = 0; j < chromelength; j++) {
                keep = mask[j];
                if (keep == 1) {
                    childA[j] = parentA[j];
                    childB[j] = parentB[j];
                } else if (keep == 0) {
                    childB[j] = parentA[j];
                    childA[j] = parentB[j];
                }
            }
            temp[x] = childB;
            temp[x+1] = childA;
            overwrite();
        }
    }

    public void tournmentSelection(){
        //Temp Varablies used For Tournment Select
        char Temp[][] = new char[K][];
        int tournmentpick;
        //tourment selection will more forward a winner until the New pop is the same size as OG pop
        for(int i = 0; i < popsize; i++){
            //Will do the Tournment Selection Based on Size K
            for(int j = 0; j < K; j++){
                tournmentpick = Rand.nextInt(popsize);
                Temp[j] = Primary[tournmentpick];
            }
            //Sorting Change
            int index = 0 ;
            for(int k = 0; k < K; k++){
                if(Evaluation.fitness(String.copyValueOf(Temp[k]),encrypt) < Evaluation.fitness(String.copyValueOf(Temp[index]),encrypt) ){
                    index = k;
                }
            }
            temp[globalindex] = Temp[index];
            globalindex++;
        }
        overwrite();
    }

    //Insertion Mutation
    public void Mutation(int mutartionpercent){
         int change = Rand.nextInt(100);
         int indexswap1 = Rand.nextInt(chromelength);
         int indexswap2 = Rand.nextInt(chromelength);
         char yimp[];
         char simp;
         for(int i = 0; i < popsize; i++){
             System.out.println(Arrays.toString(Primary[i]));
             yimp = Primary[i];
             simp = yimp[indexswap1];
             yimp[indexswap1] = yimp[indexswap2];
             yimp[indexswap2] = simp;
             Primary[i]=yimp;
             System.out.println(Arrays.toString(Primary[i]));
         }
    }
    public void Generation(int generationsize){
        for(int i = 0; i < generationsize; i++){

        }

    }

    //Override Method to Keep Primary as Main Gen and temp is the New gen, switch will be moved into primary
    public void overwrite(){
        for(int i = 0; i < popsize; i++){
            Primary[i]=temp[i];
        }
    }
    //End of Code
}

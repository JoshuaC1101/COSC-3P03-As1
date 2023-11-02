package decryptga;

import java.io.*;
import java.util.*;

public class main {
    public static void main(String []args)
    {
        GA Ga = new GA();
        Ga.OpenData();
        Ga.ReadData();
        Ga.generatechom(26);
        Ga.bestGa();
        Ga.tournmentSelection();
        Ga.Mutation(0);
    }

}

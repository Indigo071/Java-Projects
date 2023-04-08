import java.util.Scanner;
import java.util.HashMap;

public class Divers{
    String name;
    String[] dives = new String[6];
    int boardHeight;
    double score = 0.0;
    static int diverCount;
    static HashMap<String, Double> divesANDscores = new HashMap<>();

    public Divers(String name, String[] dives, int boardHeight){
        this.name = name;
        this.dives = dives;
        this.boardHeight = boardHeight;
    }

    public static String[] setDiveList(){
        Scanner scan = new Scanner(System.in);
        String[] diveList = new String[6];
        for(int i = 0; i < diveList.length; i++){
            int index = i + 1;
            System.out.println("Enter Dive " + index + " :");
            diveList[i] = scan.next();
        }
        return diveList;
    }

    public static int setBoardHeight(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Board Height: ");
        int boardHeight = scan.nextInt();
        return boardHeight;

    }

    public static void printResults(Divers[] divers){
        System.out.println("--------------------------");
        for(int i = 0; i < diverCount; i++){
            System.out.print(divers[i].name + " Individual Dive Scores: " + divers[i].getHashMap() + " ");
            System.out.print("Total Score: ");
            System.out.printf("%.2f", divers[i].score);
            System.out.println();
            System.out.println("--------------------------");
        }
    }

    public static HashMap<String, Double> getHashMap(){
        return divesANDscores;
    }
    

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);


        System.out.println("Enter Number of Divers: ");
        int numDivers = scan.nextInt();
        diverCount = numDivers;
    

        
        Divers[] divers = new Divers[numDivers];
        for(int i = 0; i < numDivers; i++){
            System.out.println("Enter Diver: ");
            divers[i] = new Divers(scan.next(), setDiveList(), setBoardHeight());
        }

       //loop though dive list

        for(int i = 0; i < 6; i ++){
            for(int j = 0; j < numDivers; j++){
                System.out.print("Enter Score for " + divers[j].name + " for dive " +divers[j].dives[i] + " : ");
                double judgeScore = scan.nextDouble();
                double calculatedScore = getDD(divers[j].dives[i], divers[j].boardHeight) * (judgeScore * 3);
                divers[j].getHashMap().put(divers[j].dives[i],calculatedScore);
                divers[j].score += calculatedScore;

            }
        }
        printResults(divers);
    }


    public static double getDD(String dive, int boardHeight){
        HashMap<String, Double> oneMeter = new HashMap<>();
        HashMap<String, Double> threeMeter = new HashMap<>();
        //list of dd

	oneMeter.put("103c", 1.6);
	oneMeter.put("103b",1.7);
        oneMeter.put("104c",2.2);
        oneMeter.put("104b",2.3);
        oneMeter.put("105c",2.4);
        oneMeter.put("105b",2.6);

        threeMeter.put("103c",1.5);
        threeMeter.put("103b",1.6);
        threeMeter.put("105c",2.2);
        threeMeter.put("105b",2.4);
        threeMeter.put("107c",2.8);

        oneMeter.put("201c",1.5);
        oneMeter.put("201b",1.6);
        oneMeter.put("203c",2.0);
        oneMeter.put("203b",2.3);
        oneMeter.put("204c",2.2);
        oneMeter.put("204b",2.5);
		
        threeMeter.put("201c",1.7);
        threeMeter.put("201b", 1.8);
        threeMeter.put("203c", 1.9);
        threeMeter.put("203b", 2.2);
        threeMeter.put("205c", 2.8);
        threeMeter.put("205b",3.0);

        oneMeter.put("301c", 1.6);
        oneMeter.put("301b", 1.7);
        oneMeter.put("302c", 1.6);
        oneMeter.put("302b", 1.7);
        oneMeter.put("303c", 2.1);
        oneMeter.put("303b", 2.4);
        oneMeter.put("304c", 2.3);
        oneMeter.put("305c", 3.0);

        threeMeter.put("301a",2.0);
        threeMeter.put("301c", 1.8);
        threeMeter.put("301b", 1.9);
        threeMeter.put("303c", 2.0);
        threeMeter.put("303b", 2.3);
        threeMeter.put("305c", 2.8);

        oneMeter.put("401c", 1.4);
        oneMeter.put("401b", 1.5);
        oneMeter.put("403c", 2.2);
        oneMeter.put("403b", 2.4);
        oneMeter.put("404c", 2.8);
        oneMeter.put("405c", 3.1);

        threeMeter.put("401c", 1.3);
        threeMeter.put("401b", 1.4);
        threeMeter.put("403c", 1.9);
        threeMeter.put("403b", 2.1);
        threeMeter.put("404c", 2.4);
        threeMeter.put("405c", 2.7);

        oneMeter.put("5132d", 2.2);
        oneMeter.put("5134d", 2.6);
        oneMeter.put("5221d", 1.7);
        oneMeter.put("5231d", 2.1);
        oneMeter.put("5233", 2.5);
        oneMeter.put("5331d",2.2);
        oneMeter.put("5333d",2.5);

        threeMeter.put("5132d", 2.1);
        threeMeter.put("5134d", 2.5);
        threeMeter.put("5152b", 3.0);
        threeMeter.put("5231d", 2.0);
        threeMeter.put("5233d", 2.4);
        threeMeter.put("5331d", 2.1);
        threeMeter.put("5333d", 2.5);
	
	
        if(boardHeight == 1)
            return oneMeter.get(dive);
        else if(boardHeight == 3)
            return threeMeter.get(dive);
        return 0.0;
    }
}

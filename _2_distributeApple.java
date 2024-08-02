import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
public class _2_distributeApple
{
    public static void print_result(List<Integer> ramBucket, List<Integer> shamBucket, List<Integer> rahimBucket) {
        //print result 
        System.out.println();
        System.out.println("Distribution Result:");
        
        System.out.print("Ram: ");
        for(int i = 0; i < ramBucket.size(); i++) {
            if(i == ramBucket.size() - 1) {
                System.out.print(ramBucket.get(i));
                break;
            }
            System.out.print(ramBucket.get(i)+", ");
        }
        System.out.println();
        
        System.out.print("Sham: ");
        for(int i = 0; i < shamBucket.size(); i++) {
            if(i == shamBucket.size() - 1) {
                System.out.print(shamBucket.get(i));
                break;
            }
            System.out.print(shamBucket.get(i)+", ");
        }
        System.out.println();
        
        System.out.print("Rahim: ");
        for(int i = 0; i < rahimBucket.size(); i++) {
            if(i == rahimBucket.size() - 1) {
                System.out.print(rahimBucket.get(i));
                break;
            }
            System.out.print(rahimBucket.get(i)+", ");
        }
    }
    
    public static void distribute_apple(PriorityQueue<Integer> q, int total_weight) {
        int ramCurrWt = (int)(0.5 * total_weight); // 50/100 = 0.5 
        int shamCurrWt = (int)(0.3 * total_weight); // 30/100 = 0.3
        int rahimCurrWt = (int)(0.2 * total_weight); // 20/100 = 0.2
        
        List<Integer> ramBucket = new ArrayList<>();
        List<Integer> shamBucket = new ArrayList<>();
        List<Integer> rahimBucket = new ArrayList<>();
        while(!q.isEmpty()) {
            int currWt = (int)q.poll();
            if(ramCurrWt >= shamCurrWt && ramCurrWt >= rahimCurrWt) {
                ramBucket.add(currWt);
                ramCurrWt -= currWt;
            } else if(shamCurrWt >= rahimCurrWt) {
                shamBucket.add(currWt);
                shamCurrWt -= currWt;
            } else {
                rahimBucket.add(currWt);
                rahimCurrWt -= currWt;
            }
        }
        
        print_result(ramBucket, shamBucket, rahimBucket);
    }
    
    public static void apple_input() {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b-a);
        int total_weight = 0;
        while(true) {
            System.out.print("Enter apple weight in gram (-1 to stop ) : ");
            int weight = sc.nextInt();
            if(weight == -1) {
                distribute_apple(q, total_weight);
                break;
            }
            q.add(weight);
            total_weight += weight;
        }
    }
	public static void main(String[] args) {
		apple_input();
	}
}
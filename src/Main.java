import java.util.*;

public class Main {
static int[] iterations = {100, 1000, 10000, 100000};

    public static void main(String[] args) {
        int numProcesses = 8;
//        Process p1 = new Process(1, 0, 36);
//        Process p2 = new Process(2, 16, 20);
//        Process p3 = new Process(3, 20, 12);
//
//        // Creating an ArrayList to store the processes
//        ArrayList<Process> p = new ArrayList<>();
//        p.add(p1);
//        p.add(p2);
//        p.add(p3);
//
//        // Running the Multilevel Feedback Queue algorithm
//        double[] result = MLFQ.ExecuteMLFQ(p);

        // Displaying the results
//        System.out.println("Multilevel Feedback Queue Average Turnaround Time: " + result[0]);
//        System.out.println("Multilevel Feedback Queue Average Waiting Time: " + result[1]);

        // Print table header
        System.out.printf("%-15s%-15s%-15s%-15s%n", "Iterations", "Algorithm", "ATT", "AWT");
//
//        // Simulate FCFS for all iterations
        System.out.println("-------------------");
        System.out.println("Simulation for FCFS");
        System.out.println("-------------------");
        for (int i : iterations) {
            ArrayList<Process> processes = generateProcesses(numProcesses);
            Collections.sort(processes, Comparator.comparingInt(Process::getArrivalTime));
            double[] results = FCFS.ExecuteFCFS(processes);
            System.out.printf("%-15d%-15s%-15.2f%-15.2f%n", i, "FcFS", results[0], results[1]);

        }

        // Simulate RR for all iterations
        System.out.println("-------------------");
        System.out.println("Simulation for RR");
        System.out.println("-------------------");
        for (int i : iterations) {
            ArrayList<Process> processes = generateProcesses(numProcesses);
            Collections.sort(processes, Comparator.comparingInt(Process::getArrivalTime));
            double[] results = RR.ExecuteRR(processes);
            System.out.printf("%-15d%-15s%-15.2f%-15.2f%n", i, "RR", results[0], results[1]);

        }
        // Simulate MLFQ for all iterations
        System.out.println("---------------------");
        System.out.println("Simulation for MLFQ");
        System.out.println("---------------------");
        for (int i : iterations) {
            ArrayList<Process> processes = generateProcesses(numProcesses);
            Collections.sort(processes, Comparator.comparingInt(Process::getArrivalTime));
            double[] results = MLFQ.ExecuteMLFQ(processes);
            System.out.printf("%-15d%-15s%-15.2f%-15.2f%n", i, "MLFQ", results[0], results[1]);

        }
        // Simulate STF for all iterations
        System.out.println("-------------------");
        System.out.println("Simulation for STF with preemption");
        System.out.println("-------------------");

        for (int i : iterations) {
            ArrayList<Process> processes = generateProcesses(numProcesses);
            Collections.sort(processes, Comparator.comparingInt(Process::getArrivalTime));
            double[] results = STF_P.ExecuteSJF(processes);
            System.out.printf("%-15d%-15s%-15.2f%-15.2f%n", i, "STF", results[0], results[1]);

        }
    }


    private static ArrayList<Process> generateProcesses(int numberOfProcesses) {//create new process with a random CPU-burst
        ArrayList<Process> processes = new ArrayList<>();
        Random rand = new Random();

        for (int i = 1; i <= numberOfProcesses; i++) {
            int arrivalTime = rand.nextInt(100);
            int burstTime = rand.nextInt(96) + 5; // Random CPU burst between 5 and 100 time units
            processes.add(new Process(i, arrivalTime, burstTime));
        }

        return processes;
    }

}

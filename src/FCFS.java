import java.util.ArrayList;


public class FCFS {


public static double[] ExecuteFCFS(ArrayList<Process> processes) {
    int time = 0;// A time counter
    double ATT = 0;// Average Turnaround Time
    double AWT = 0; // Average Waiting Time

//Repeat the procedures to  simulate execution.
    for (Process process : processes) {
        if (time < process.getArrivalTime()) {// fast-forward to the process' arrival time if the current time is earlier than its arrival.
            time = process.getArrivalTime();
        }

        int turnaroundTime = time - process.getArrivalTime() + process.getBurstTime();// calculate turnaround time for the current process.
        ATT += turnaroundTime;//add the turn around time to avarage turn araound time
        AWT += turnaroundTime - process.getBurstTime();// calculate the  waiting time

        time += process.getBurstTime();// add process's burst time. to current time
    }
// Calculate average turnaround and waiting times.
    float Totalturnaroundtime = (float) (ATT / processes.size());
    float Totalwaitingtime = (float) (AWT / processes.size());

//    System.out.println("Average Waiting Time: " + Totalwaitingtime);
//    System.out.println("Average Turnaround Time: " + Totalturnaroundtime);
    return new double[]{Totalturnaroundtime, Totalwaitingtime};// return  average turnaround and waiting times.
}
}

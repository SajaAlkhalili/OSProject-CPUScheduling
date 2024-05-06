import java.util.*;

public class MLFQ {
public static double[] ExecuteMLFQ(ArrayList<Process> processes) {
    Queue<Process> queue1 = new LinkedList<>();// the queue1 to store RR with 10 units processes
    Queue<Process> queue2 = new LinkedList<>();// the queue2 to store RR with 50 units processes
    Queue<Process> queue3 = new LinkedList<>();// the queue3 to store FCFS processes

    for (Process process : processes) {
        queue1.offer(process);
    }

    int time = 0;// A time counter
    float ATT = 0;// Average Turnaround Time
    float AWT = 0;// Average Waiting Time

    while (!queue1.isEmpty() || !queue2.isEmpty() || !queue3.isEmpty()) {//
        if (!queue1.isEmpty()) {
            Process currentProcess = queue1.poll();
            int burstTime = Math.min(currentProcess.getRemainingTime(),10); // Time slice for queue1
            time += burstTime;
            currentProcess.updateRemainingBurstTime(burstTime);

            if (currentProcess.getRemainingTime() > 0) {
                queue2.offer(currentProcess); // Move to queue2
            } else {
                int turnaroundTime = time - currentProcess.getArrivalTime();// calculate turnaround time for the current process.
                AWT += turnaroundTime - currentProcess.getBurstTime();// calculate the  waiting time
                ATT+=turnaroundTime;//add the turn around time to avarage turn araound time
            }
        } else if (!queue2.isEmpty()) {
            Process currentProcess = queue2.poll();
            int burstTime = Math.min(currentProcess.getRemainingTime(), 50); // Time slice for queue2
            time += burstTime;
            currentProcess.updateRemainingBurstTime(burstTime);

            if (currentProcess.getRemainingTime() > 0) {
                queue3.offer(currentProcess); // Move to queue3 (FCFS)
            } else {
                int turnaroundTime = time - currentProcess.getArrivalTime();// calculate turnaround time for the current process.
                AWT += turnaroundTime - currentProcess.getBurstTime();// calculate the  waiting time
                ATT+=turnaroundTime;//add the turn around time to avarage turn araound time
            }
        } else if (!queue3.isEmpty()) {
            Process currentProcess = queue3.poll();
            time += currentProcess.getRemainingTime();
            int turnaroundTime = time - currentProcess.getArrivalTime();// calculate turnaround time for the current process.
            AWT += turnaroundTime - currentProcess.getBurstTime();// calculate the  waiting time
            ATT+=turnaroundTime;
        }
    }

    float Totalturnaroundtime = ATT / processes.size();
    float Totalwaitingtime = AWT / processes.size();
    return new double[]{Totalturnaroundtime, Totalwaitingtime};
//    System.out.println("Multilevel Feedback Queue Average Turnaround Time: " + Totalturnaroundtime);
//    System.out.println("Multilevel Feedback Queue Average Waiting Time: " + TotalWaitingTime);
}

}

import java.util.*;

public class STF_P {


public static double[] ExecuteSJF(ArrayList<Process> processes) {
    Queue<Process> queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.remainingTime));// Create a priority queue and arrange processes in it according to remaining time.
    ArrayList<Process> completedProcesses = new ArrayList<>();

    int Time = 0;// A time counter
    float ATT = 0;// Average Turnaround Time
    float AWT = 0; // Average Waiting Time


    while (!processes.isEmpty() || !queue.isEmpty()) {// Keep processing until every process has been completed.
        while (!processes.isEmpty() && processes.get(0).getArrivalTime() <= Time) {// Put procedures onto the priority queue that have arrived by the current time.
            queue.add(processes.remove(0));
        }

        if (!queue.isEmpty()) {// If there are processes in the queue, process the one with the shortest remaining time.
            Process currentProcess = queue.poll();// get the highest priority process
            int burstTime = Math.min(currentProcess.getRemainingTime(), processes.isEmpty() ? Integer.MAX_VALUE : processes.get(0).getArrivalTime() - Time);// calculate the burst time of the current process. If no more processes arrive, the maximum integer value is used.
            Time += burstTime;//add the time to burst time

            currentProcess.remainingTime =currentProcess.remainingTime- burstTime;//reduce the remaining time of the current process by the burst time.

            if (currentProcess.remainingTime == 0) { // If the current process is completed
                int turnaroundTime = Time - currentProcess.getArrivalTime();// calculate turnaround time for the current process.
                AWT += turnaroundTime - currentProcess.getBurstTime();// calculate the  waiting time
                ATT+=turnaroundTime;//add the turn around time to avarage turn araound time
                completedProcesses.add(currentProcess);
            } else {
                queue.add(currentProcess);
            }
        } else {
            Time = processes.get(0).getArrivalTime();
        }
    }
// Calculate average turnaround and waiting times.
    float Totalturnaroundtime = ATT / completedProcesses.size();
    float TotalWaitingTime = AWT / completedProcesses.size();

//    System.out.println("SRTF Average Turnaround Time: " + Totalturnaroundtime);
//    System.out.println("SRTF Average Waiting Time: " + TotalWaitingTime);
   return new double[]{Totalturnaroundtime, TotalWaitingTime};
}





}


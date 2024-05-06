import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RR {
    static int quantum = 20;


    public static double[] ExecuteRR(ArrayList<Process> processes) {
        int Time = 0;// A time counter
        int ATT = 0;// Average Turnaround Time
        int AWT = 0;// Average Waiting Time
        int completedProcesses = 0;

        Queue<Process> Queue = new LinkedList<>();// the queue to store processes

        while (!processes.isEmpty() || !Queue.isEmpty()) {// Keep processing until every process has been completed.
            while (!processes.isEmpty() && processes.get(0).getArrivalTime() <= Time) {// While as there are processes waiting in line and the first process' arrival time is either the same as or less than the current time
                Queue.add(processes.remove(0));
            }

            if (Queue.isEmpty() && processes.isEmpty()) {//if the queue and process empty ,break out of the loop
                break;
            }
//if the queue is empty select next process form the array list precess
            if (Queue.isEmpty()) {
                Process currentProcess = processes.remove(0);
                Time = currentProcess.getArrivalTime();
                Queue.add(currentProcess);
            }

            while (!Queue.isEmpty()) {//while ,if the queue is not empty.
                Process currentProcess = Queue.poll();//remove the first process from the queue
                int burstTime = Math.min(quantum, currentProcess.getRemainingTime());// Calculate the current process's burst time (limited by the quantum or remaining time)
                currentProcess.updateRemainingBurstTime(burstTime);// update the current process's remaining burst time.
                Time += burstTime;//add burst time to current time

                if (currentProcess.getRemainingTime() > 0) {//if the current process is still time  to execute
                    Queue.add(currentProcess);//add the process to the queue for future execution
                } else {
                    int turnaroundTime = Time - currentProcess.getArrivalTime();// calculate turnaround time for the current process.
                    AWT += turnaroundTime - currentProcess.getBurstTime();// calculate the  waiting time
                     ATT+=turnaroundTime;//add the turn around time to avarage turn araound time
                    completedProcesses++;
                }
            }

            while (!processes.isEmpty() && processes.get(0).getArrivalTime() <= Time) {// While as there are processes waiting in line and the first process' arrival time is either the same as or less than the current time
                Queue.add(processes.remove(0));//add the process to queue
            }
        }

        double TotalTurnaroundTime = completedProcesses > 0 ? (double) ATT / completedProcesses : 0;
        double TotalWaitingTime = completedProcesses > 0 ? (double) AWT / completedProcesses : 0;
//        System.out.println("RR Average Turnaround Time: " + TotalTurnaroundTime);
//        System.out.println("RR Average Waiting Time: " + TotalWaitingTime);
        return new double[]{TotalTurnaroundTime, TotalWaitingTime};
    }


}

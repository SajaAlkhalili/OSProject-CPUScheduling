public class Process {

        private  int pid;
    private int arrivalTime;
    private int burstTime;

    public int remainingTime;

        public Process(int pid, int arrivalTime, int burstTime) {
            this.pid = pid;
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.remainingTime = burstTime;
        }
        public int getPid() {
            return pid;
        }
    public int getArrivalTime() {
            return arrivalTime;
        }
        public int getBurstTime() {
            return burstTime;
        }
    public int getRemainingTime() {
            return remainingTime;
        }
    @Override
        public String toString() {
            return "Process{" +
                    "pid=" + pid +
                    ", arrivalTime=" + arrivalTime +
                    ", burstTime=" + burstTime +
                    ", remainingTime=" + remainingTime +
                    '}';
        }
    public void updateRemainingBurstTime(int Time) {

            remainingTime -= Time;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }
}



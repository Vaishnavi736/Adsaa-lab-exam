import java.util.Arrays;

class Job {
    int id;
    int deadline, profit;

    Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobScheduling {

    public static void scheduleJobs(Job[] jobs) {
        // Step 1: Sort jobs by profit (highest first)
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        int n = jobs.length;
        boolean[] slots = new boolean[n]; // Track occupied slots
        int[] result = new int[n];        // Store job sequence
        int totalProfit = 0;              // Track total profit

        // Step 2: Assign jobs to available slots
        for (Job job : jobs) {
            // Check from the last possible slot before deadline
            for (int j = job.deadline - 1; j >= 0; j--) {
                if (!slots[j]) { // If slot is free, assign the job
                    slots[j] = true;
                    result[j] = job.id;
                    totalProfit += job.profit; // Add profit of scheduled job
                    break;
                }
            }
        }

        // Step 3: Print the job sequence and total profit
        System.out.print("Selected Jobs: ");
        for (int jobId : result) {
            if (jobId != 0) {
                System.out.print(jobId + " ");
            }
        }
        System.out.println("\nTotal Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        Job[] jobs = {
            new Job(1, 2, 100),
            new Job(2, 1, 19),
            new Job(3, 2, 27),
            new Job(4, 1, 25),
            new Job(5, 3, 15)
        };

        scheduleJobs(jobs);
    }
}

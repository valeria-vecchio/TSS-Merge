import java.util.*;
;

public class MergeIntervals {

    /**
     * method to merge overlapping intervals
     *
     * @param givenIntervals
     * @return stack of merged intervals
     */
    private static ArrayList<Interval> merge(ArrayList<Interval> givenIntervals) {
        // Create an empty stack of intervals
        ArrayList<Interval> stack = new ArrayList<Interval>();

        // check if the given data has at least one interval
        if (givenIntervals.size() <= 1) {
            return stack;
        }

        // sort the given list of intervals in ascending order of start time
        Collections.sort(givenIntervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        // push first interval to the stack
        stack.add(givenIntervals.get(0));

        // to increase performance set size of givenIntervals to a variable
        int intervalsSize = givenIntervals.size();

        // start with i = 1 to start with the second interval
        // after the first is already in the stack
        for (int i = 1; i < intervalsSize; i++) {
            // get interval from stack top
            Interval top = stack.get(stack.size() - 1);

            // if current interval is not overlapping with stack top,
            // push it to the stack
            if (top.end < givenIntervals.get(i).start)
                stack.add(givenIntervals.get(i));

                // Otherwise update the ending time of top if ending of current
                // interval is more
            else if (top.end < givenIntervals.get(i).end) {
                top.end = givenIntervals.get(i).end;
                stack.remove(stack.size() - 1);
                stack.add(top);
            }
        }
        return stack;
    }

    /**
     * create a random set of intervals for easier scaling to proof perfomance
     *
     * @return intervals
     */
    private static ArrayList<Interval> generateRandomIntervals() {
        ArrayList<Interval> intervals = new ArrayList<Interval>();
        Interval interval;

        System.out.println("Starting Intervals: ");

        // generate e.g. 5 intervals with random values
        for (int i = 0; i < 5; i++) {

            // generate a random number between [0 - 40].
            int random1 = (int) (30.0 * Math.random());
            int random2 = (int) (30.0 * Math.random());

            // check whether which random generated number is smaller
            if (random1 < random2) {
                interval = new Interval(random1, random2);
            } else {
                interval = new Interval(random2, random1);
            }

            intervals.add(interval);
            System.out.println("[" + interval.start + "," + interval.end + "]");
        }

        return intervals;
    }


    public static void main(String args[]) {
        // measure time
        final long timeStart = System.currentTimeMillis();

        //create the given set from the example
        ArrayList<Interval> intervals = new ArrayList<Interval>();
        Interval i1 = new Interval(25,30);
        Interval i2 = new Interval(2,19);
        Interval i3 = new Interval(14,23);
        Interval i4 = new Interval(4,8);

        //======================================================================
        //create a set of negative intervals
        /*ArrayList<Interval> intervals = new ArrayList<Interval>();
        Interval i1 = new Interval(-5,-3);
        Interval i2 = new Interval(-4,-2);
        Interval i3 = new Interval(-15,-7);
        Interval i4 = new Interval(-1,5);*/

        //======================================================================
        // create a random set of intervals and merge them
        //ArrayList<Interval> intervals = generateRandomIntervals();

        intervals.add(i1);
        intervals.add(i2);
        intervals.add(i3);
        intervals.add(i4);

        ArrayList<Interval> result = merge(intervals);

        // print input
        int inputLength = intervals.size();
        for (int i = 0; i < inputLength; i++) {
            System.out.println("[" + intervals.get(i).start + "," + intervals.get(i).end + "]");
        }

        // print result intervals
        System.out.println("\nResult: ");
        int resultLength = result.size();
        for (int i = 0; i < resultLength; i++) {
            System.out.println("[" + result.get(i).start + "," + result.get(i).end + "]");
        }

        // measure time and print final runtime
        final long timeEnd = System.currentTimeMillis();
        System.out.println("\nRuntime: " + (timeEnd - timeStart) + " millisec.");

        // print memory usage
        Runtime runtime = Runtime.getRuntime();
        int mb = 1024 * 1024;

        double availableMemory = runtime.totalMemory() / mb;
        double freeMemory = runtime.freeMemory() / mb;
        double usedMemory = availableMemory - freeMemory;

        System.out.println("\nTotal Memory: " + availableMemory);
        System.out.println("Free Memory: " + freeMemory);
        System.out.println("Used Memory: " + usedMemory);
    }
}

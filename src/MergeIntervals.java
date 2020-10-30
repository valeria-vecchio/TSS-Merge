import java.util.*;
;

public class MergeIntervals {

    /**
     * method to merge overlapping intervals
     * @param givenIntervals
     * @return stack of merged intervals
     */
    private static Stack<Interval> merge (ArrayList<Interval> givenIntervals){
        // Create an empty stack of intervals
        Stack<Interval> stack = new Stack<>();

        // check if the given data has at least one interval
        if(givenIntervals.isEmpty()) {
            return stack;
        }

        // sort the given list of intervals in ascending order of start time
        Collections.sort(givenIntervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        // push first interval to the stack
        stack.push(givenIntervals.get(0));

        // to increase performance set size of givenIntervals to a variable
        int intervalsSize = givenIntervals.size();

        // start with i = 1 to start with the second interval
        // after the first is already in the stack
        for (int i = 1 ; i < intervalsSize; i++)
        {
            // get interval from stack top
            Interval top = stack.peek();

            // if current interval is not overlapping with stack top,
            // push it to the stack
            if (top.end < givenIntervals.get(i).start)
                stack.push(givenIntervals.get(i));

                // Otherwise update the ending time of top if ending of current
                // interval is more
            else if (top.end < givenIntervals.get(i).end)
            {
                top.end = givenIntervals.get(i).end;
                stack.pop();
                stack.push(top);
            }
        }
        return stack;
    }

    /**
     * create a random set of intervals for easier scaling to proof perfomance
     * @return intervals
     */
    private static ArrayList<Interval> generateRandomIntervals(){
        ArrayList<Interval> intervals = new ArrayList<Interval>();
        Interval interval;

        System.out.println("Starting Intervals: ");

        //generate e.g. 5 intervals with random values
        for(int i = 0; i < 5; i++) {

            // generate a random number between [0 - 40].
            int random1 = (int)(30.0 * Math.random());
            int random2 = (int)(30.0 * Math.random());

            //check whether which random generated number is smaller
            if(random1 < random2) {
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
        // create a random set of intervals
        ArrayList<Interval> randomIntervals = generateRandomIntervals();

        Stack<Interval> result;
        result = merge(randomIntervals);

        System.out.println("\nResult: ");

        // print result intervals
        int arrayLength = result.size();
        for(int i = 0; i < arrayLength; i++) {
            System.out.println("[" + result.get(i).start + "," + result.get(i).end + "]");
        }
    }
}

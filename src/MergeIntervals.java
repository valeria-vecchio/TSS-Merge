import java.util.*;
;

public class MergeIntervals {

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

    public static void main(String args[]) {
        // create a set of intervals
        ArrayList<Interval> intervals = new ArrayList<Interval>();

        Interval i1 = new Interval(25,30);
        Interval i2 = new Interval(2,19);
        Interval i3 = new Interval(14,23);
        Interval i4 = new Interval(4,8);

        intervals.add(i1);
        intervals.add(i2);
        intervals.add(i3);
        intervals.add(i4);

        Stack<Interval> result;
        result = merge(intervals);

        // print result intervals
        int arrayLength = result.size();
        for(int i = 0; i < arrayLength; i++) {
            System.out.println("[" + result.get(i).start + "," + result.get(i).end + "]");
        }
    }
}

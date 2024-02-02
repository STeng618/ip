public class Event extends Task {
    private final String start;
    private final String end;

    public Event(String taskName, String start, String end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    public Event(EventArguments eventArguments) {
        super(eventArguments.taskName);
        this.start = eventArguments.start;
        this.end = eventArguments.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
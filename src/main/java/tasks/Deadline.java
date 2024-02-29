package tasks;

import exceptions.EmptyTaskDescription;
import exceptions.InvalidTaskArguments;
import ui.Keywords;

public class Deadline extends Task {
    private final String by;

    public Deadline(String taskName, String by, boolean isCompleted) {
        super(taskName, isCompleted);
        this.by = by;
    }

    public static String getSampleDeadline() {
        return Keywords.DEADLINE + " <task name>" + Keywords.BY + "<deadline>" + System.lineSeparator()
                + "For example, " + Keywords.DEADLINE + " return book" + Keywords.BY + "June 6th";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String getStringRepresentation() {
        return Keywords.DEADLINE + " "+ taskName + Keywords.BY + this.by + getIsCompletedString();
    }

    /**
     * Parses the input to get the task name and deadline.
     * @param currentInput the input to be parsed
     * @return a Deadline task
     * @throws EmptyTaskDescription
     * @throws InvalidTaskArguments
     */
    public static Deadline getTask (String currentInput)
        throws EmptyTaskDescription, InvalidTaskArguments {
        try {
            boolean isCompleted = currentInput.contains(Task.IS_COMPLETED_STRING);
            currentInput = currentInput.replaceAll(Task.IS_COMPLETED_STRING, "");

            int idxOfDeadline = currentInput.indexOf(Keywords.BY);
            boolean hasDeadline = idxOfDeadline != -1;
            if (!hasDeadline) {
                throw new InvalidTaskArguments(getSampleDeadline());
            }

            String taskName = currentInput.substring(Keywords.DEADLINE.length(), idxOfDeadline);
            taskName = taskName.trim();
            if (taskName.isEmpty()) {
                throw new EmptyTaskDescription();
            }

            String by = currentInput.substring(idxOfDeadline + Keywords.BY.length());
            by = by.trim();

            return new Deadline(taskName, by, isCompleted);

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskArguments(getSampleDeadline());
        }
    }
}

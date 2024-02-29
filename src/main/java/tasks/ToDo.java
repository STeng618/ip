package tasks;

import exceptions.EmptyTaskDescription;
import exceptions.InvalidTaskArguments;
import ui.Keywords;

public class ToDo extends Task {
    public ToDo(String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
    }

    public static String getSampleToDo() {
        return Keywords.TODO + " <task name>" + System.lineSeparator() 
                + "For example, " + Keywords.TODO + " read book";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getStringRepresentation() {
        return Keywords.TODO + " " + taskName + getIsCompletedString();
    }

    /**
     * Parses the input to get the task name.
     * @param currentInput the input to be parsed
     * @return a ToDo task
     * @throws EmptyTaskDescription
     * @throws InvalidTaskArguments
     */
    public static ToDo getTask(String currentInput)
        throws EmptyTaskDescription, InvalidTaskArguments {
        try {
            boolean isCompleted = currentInput.contains(Task.IS_COMPLETED_STRING);
            currentInput = currentInput.replaceAll(Task.IS_COMPLETED_STRING, "");

            String taskName = currentInput.substring(Keywords.TODO.length());
            taskName = taskName.trim();
            if (taskName.isEmpty()) {
                throw new EmptyTaskDescription();
            }

            return new ToDo(taskName, isCompleted);

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskArguments(getSampleToDo());
        }

    }
}

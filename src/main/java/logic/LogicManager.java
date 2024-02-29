package logic;

import exceptions.IllegalNumberOfArguments;
import exceptions.Confusion;
import exceptions.EmptyTaskDescription;
import exceptions.InvalidTaskArguments;
import exceptions.InvalidTaskIndex;
import tasks.Deadline;
import tasks.Event;
import tasks.ListKeeper;
import tasks.ToDo;
import ui.Keywords;

import java.util.Arrays;

public class LogicManager {
    private final ListKeeper listKeeper;

    public LogicManager(ListKeeper listKeeper) {
        this.listKeeper = listKeeper;
    }

    private void executeList() {
        this.listKeeper.printList();
    }

    private int getTaskIndex (String[] words) throws InvalidTaskIndex {
        String taskIndexString = words[1];
        int taskIndex;

        try {
            taskIndex = Integer.parseInt(taskIndexString);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndex();
        }

        if (!this.listKeeper.isValidTaskIndex(taskIndex)) {
            throw new InvalidTaskIndex();
        }

        return taskIndex;
    }

    private void executeMark (String[] words) throws InvalidTaskIndex {
        int taskIndex = getTaskIndex(words);
        boolean isCompleted = words[0].equals(Keywords.MARK);
        this.listKeeper.processMark(taskIndex, isCompleted);
    }

    private void executeDelete (String[] words) throws InvalidTaskIndex {
        int taskIndex = getTaskIndex(words);
        this.listKeeper.deleteTask(taskIndex);
    }

    private void executeToDo(String currentInput) throws EmptyTaskDescription, InvalidTaskArguments {
        ToDo todo = ToDo.getTask(currentInput);
        this.listKeeper.addToList(todo);
    }

    private void executeDeadline(String currentInput) throws EmptyTaskDescription, InvalidTaskArguments {
        Deadline deadline = Deadline.getTask(currentInput);
        this.listKeeper.addToList(deadline);
    }

    private void executeEvent(String currentInput) throws EmptyTaskDescription, InvalidTaskArguments {
        Event event = Event.getTask(currentInput);
        this.listKeeper.addToList(event);
    }

    private void executeFind(String[] words) {
        String[] keywordsToFind = Arrays.copyOfRange(words, 1, words.length);
        this.listKeeper.printMatchingTasks(keywordsToFind);
    }

    private void executeCommand (String currentInput)
        throws IllegalNumberOfArguments, InvalidTaskIndex,
            EmptyTaskDescription, InvalidTaskArguments, Confusion {

        String[] words = currentInput.split(" ");
        if (!Keywords.doosInputHaveCorrectNumOfArguments(words)) {
            throw new IllegalNumberOfArguments();
        }

        String commandType = words[0];
        switch (commandType) {
        case Keywords.LIST:
            executeList();
            break;

        case Keywords.MARK:
        case Keywords.UNMARK:
            executeMark(words);
            break;

        case Keywords.DELETE:
            executeDelete(words);
            break;

        case Keywords.TODO:
            executeToDo(currentInput);
            break;

        case Keywords.DEADLINE:
            executeDeadline(currentInput);
            break;

        case Keywords.EVENT:
            executeEvent(currentInput);
            break;

        case Keywords.FIND:
            executeFind(words);
            break;

        default:
            throw new Confusion();
        }
    }

    /**
     * Processes the input command and executes the corresponding action
     * @param currentInput the input command
     */
    public void processCommand(String currentInput) {
        if (currentInput.isEmpty()) {
            return;
        }
        try {
            executeCommand(currentInput);
        } catch (IllegalNumberOfArguments e) {
            System.out.println("You provided too few or too many arguments");
        } catch (InvalidTaskIndex e) {
            System.out.println("Task specified does not exist");
        } catch (EmptyTaskDescription e) {
            System.out.println("Task description cannot be empty");
        } catch (InvalidTaskArguments e) {
            System.out.println("Not a valid task");
        } catch (Confusion e) {
            System.out.println("I'm very very confused~~~");
        }
    }
}

public abstract class Task {
    protected final String taskName;
    protected boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[X] " + this.taskName;
        }
        return "[] " + this.taskName;
    }

    public void mark(boolean isCompleted) {
        this.isCompleted = isCompleted;
        if (this.isCompleted) {
            System.out.println("Nice! I've marked this task as done: ");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + this);
    }

    public abstract String getStringRepresentation();
}

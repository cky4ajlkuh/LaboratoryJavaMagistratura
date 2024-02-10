package exams.task26;

public class Writer implements Runnable {

    private final Helper helper;

    public Writer(Helper helper) {
        this.helper = helper;
    }

    @Override
    public void run() {
        helper.write();
    }
}

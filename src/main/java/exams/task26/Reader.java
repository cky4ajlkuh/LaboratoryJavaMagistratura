package exams.task26;

public class Reader implements Runnable {

    private final Helper helper;

    public Reader(Helper helper) {
        this.helper = helper;
    }

    @Override
    public void run() {
        helper.read();
    }
}

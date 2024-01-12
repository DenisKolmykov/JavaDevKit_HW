package hw05;

import java.util.concurrent.atomic.AtomicBoolean;

public class Fork {
    private int forkNum;
    AtomicBoolean status = new AtomicBoolean(true); // занято/свободно

    public Fork (int num){
        this.forkNum = num;
    }

    public void setForkStatus(boolean status) {
        this.status.set(status);
    }
    public AtomicBoolean getForkStatus(){
        return status;
    }

    public int getForkNum() {
        return forkNum;
    }
}

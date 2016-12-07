/**
 * Created by Piotr Lasek on 07.12.2016.
 */
public class Bucket {

    private boolean full = false;
    private int id;

    public Bucket(int id, boolean isBucketFull) {
        this.id = id;
        setFull(isBucketFull);
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public String state() {
        if (isFull()) {
            return "pełne(" + id + ")" ;
        } else {
            return "puste(" + id + ")";
        }
    }
}

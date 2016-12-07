import java.util.concurrent.Exchanger;

/**
 * Created by Piotr Lasek on 07.12.2016.
 */
public class Fireman implements Runnable {

    int id;
    Exchanger<Bucket> el, er;
    Bucket b;

    /**
     *
     * @param id
     * @param el
     * @param er
     */
    public Fireman(int id, Exchanger<Bucket> el, Exchanger<Bucket> er, Bucket b) {
        this.id = id;
        this.el = el;
        this.er = er;
        this.b = b;
    }

    /**
     *
     */
    @Override
    public void run() {
        try {
            while (true) {
                if (el == null) { // most left fireman
                    if (b.isFull()) {
                        passingToTheRight();
                        b = er.exchange(b);
                        passedToTheRight();
                    } else {
                        refillBucket();
                        b.setFull(true);
                    }
                } else if (er == null) { // most right fireman
                    if (!b.isFull()) {
                        passingToTheLeft();
                        b = el.exchange(b);
                        passedToTheLeft();
                    } else {
                        emptyBucket();
                        b.setFull(false);
                    }
                } else { // firemen in the middle
                    if (b.isFull()) {
                        passingToTheRight();
                        b = er.exchange(b);
                        passedToTheRight();
                    } else {
                        passingToTheLeft();
                        b = el.exchange(b);
                        passedToTheLeft();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void passingToTheRight() {
        System.out.println(id + " przekazuje wiadro " + b.state() + " w prawo.");
        sleep();
    }

    private void passedToTheRight() {
        System.out.println(id + " odebrał od prawego wiadro " + b.state());
        sleep();
    }

    private void refillBucket() {
        System.out.println(id + " napełnia wiadro " + b.state() + ". [NAPELNIA]");
        sleep();
    }

    /**
     *
     */
    private void passedToTheLeft() {
        System.out.println(id + " odebrał od lewego wiadro " + b.state());
        sleep();
    }

    /**
     *
     */
    private void passingToTheLeft() {
        System.out.println(id + " przekazuje wiadro " + b.state() + " w lewo.");
        sleep();
    }

    /**
     *
     */
    private void emptyBucket() {
        System.out.println(id + " opróżnia wiadro " + b.state() + ". [GASI POZAR]");
        sleep();
    }

    /**
     *
     */
    private void sleep() {
        try {
            // Thread.sleep(1000 + (long)(Math.random() * 1000));
            Thread.sleep((long)(Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

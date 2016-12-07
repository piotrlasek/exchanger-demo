import java.util.concurrent.Exchanger;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Bucket b1 = new Bucket(0, false);
        Bucket b2 = new Bucket(1, false);
        Bucket b3 = new Bucket(2, true);

        Exchanger<Bucket> e1 = new Exchanger<Bucket>();
        Exchanger<Bucket> e2 = new Exchanger<Bucket>();

        Fireman f1 = new Fireman(0, null, e1, b1);
        Fireman f2 = new Fireman(1,   e1, e2, b2);
        Fireman f3 = new Fireman(2, e2, null, b3);

        new Thread(f1).start();
        new Thread(f2).start();
        new Thread(f3).start();
    }
}

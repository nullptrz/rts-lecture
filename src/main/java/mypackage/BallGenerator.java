package mypackage;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class BallGenerator {
    // TODO: Fix some bugs and add count of red balls in removedballs list
    // TODO: maybe need to change some callables to just runnables?

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ConcurrentLinkedQueue<Ball> conveyorBelt = new ConcurrentLinkedQueue<>();
        ArrayList<Ball> removedBalls = new ArrayList<>();
        ArrayList<Ball> kickedBalls = new ArrayList<>();

        ExecutorService es = Executors.newFixedThreadPool(5);
        int n = 1;
        while(n <= 20) {
            Future<Ball> b = es.submit(new BallGen());
            Future<Ball> addBall = es.submit(new BallAdder(conveyorBelt, b.get()));
            Future<Ball> scanBall = es.submit(new Scanner(conveyorBelt));
            Future<Ball> paddleBall = es.submit(new Paddle(conveyorBelt, scanBall.get(), kickedBalls));
            Future<Ball> removeBall = es.submit(new BallRemover(conveyorBelt, removedBalls));
            n++;
        }

    }
}

class BallGen implements Callable<Ball> {
    Random rnd = new Random();

    @Override
    public Ball call() throws Exception {
        while (true) {
            int random_num = rnd.nextInt(1000);
            Ball b = new Ball();
            if (random_num < 500) {
                b.setColor(Ball.COLOR.RED);
            } else {
                b.setColor(Ball.COLOR.BLUE);
            }
            System.out.println("Generated ball with Color: " + b.getColor());
            return b;
        }
    }
}

class BallAdder implements Callable<Ball> {
    ConcurrentLinkedQueue<Ball> ballQueue = new ConcurrentLinkedQueue<>();
    Ball ball;
    BallAdder(ConcurrentLinkedQueue<Ball> queue, Ball ball) {
        this.ball = ball;
        this.ballQueue = queue;
    }

    @Override
    public Ball call() throws Exception {
        ballQueue.add(ball);
        System.out.println("Added ball to Conveyor Belt");
        return null;
    }
}

class BallRemover implements Callable<Ball> {
    ConcurrentLinkedQueue<Ball> queue = new ConcurrentLinkedQueue<>();
    ArrayList<Ball> arrayList = new ArrayList<>();
    BallRemover(ConcurrentLinkedQueue<Ball> queue, ArrayList<Ball> list) {
        this.queue = queue;
        this.arrayList = list;
    }

    @Override
    public Ball call() throws Exception {
        arrayList.add(queue.poll());
        System.out.println("Removed ball from Conveyor Belt");
        return null;
    }
}

class Scanner implements Callable<Ball> {
    ConcurrentLinkedQueue<Ball> queue = new ConcurrentLinkedQueue<>();
    Scanner(ConcurrentLinkedQueue<Ball> queue) {
        this.queue = queue;
    }

    @Override
    public Ball call() throws Exception {
        Ball b = queue.peek();

        if(b != null && b.getColor() == Ball.COLOR.RED){
            System.out.println("Detected Red Color mypackage.Ball, Activating mypackage.Paddle");
            return b;
        }
        return null;
    }
}

class Paddle implements Callable<Ball> {
    ArrayList<Ball> arrayList = new ArrayList<>();
    ConcurrentLinkedQueue<Ball> queue = new ConcurrentLinkedQueue<>();
    Ball ball;

    Paddle(ConcurrentLinkedQueue<Ball> queue, Ball b, ArrayList<Ball> list) {
        this.ball = b;
        this.queue = queue;
        this.arrayList = list;
    }

    @Override
    public Ball call() throws Exception {

        if(ball != null) {
            queue.remove(ball);
            arrayList.add(ball);
            System.out.println("Removed Red mypackage.Ball by mypackage.Paddle");
        }
        return null;
    }
}

class Ball {
    enum COLOR  {BLUE, RED}
    COLOR color;

    void setColor(COLOR color) {
        this.color = color;
    }

    void printColor() {
        System.out.println("mypackage.Ball Color: " + color);
    }

    COLOR getColor(){
        return color;
    }
}
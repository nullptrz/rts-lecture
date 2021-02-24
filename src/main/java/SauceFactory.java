import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SauceFactory {
    public static void main(String[] args) {
        ExecutorService TomatoSauceFactory = Executors.newCachedThreadPool();
        int bottleID = 1;
        while(true) {
            if (bottleID != 100) {
                TomatoSauceBottle bottle = new TomatoSauceBottle(bottleID);
                try {
                    Future<TomatoSauceBottle> cleanedBottle = TomatoSauceFactory.submit(new Cleaning(bottle));
                    Future<TomatoSauceBottle> filledCan = TomatoSauceFactory.submit(new Filling(cleanedBottle.get()));
                    Future<TomatoSauceBottle> sealedCan = TomatoSauceFactory.submit(new Sealing(filledCan.get()));
                    Future<TomatoSauceBottle> labelledCan = TomatoSauceFactory.submit(new Labelling(sealedCan.get()));

                    TomatoSauceBottle finalCan = labelledCan.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ++bottleID;
            } else {
                break;
            }
        }
    }
}

class TomatoSauceBottle {
    private int id;
    private boolean isCleaned;
    private boolean isFilled;
    private boolean isSealed;
    private boolean isLabelled;

    TomatoSauceBottle(int id){
        this.id = id;
        this.isFilled = false;
        this.isSealed = false;
        this.isLabelled = false;
        this.isCleaned = false;
    }

    public void setCleaned(boolean flag) {
        this.isCleaned = flag;
    }

    public void setFilled(boolean flag) {
        this.isFilled = flag;
    }

    public void setSealed(boolean flag) {
        this.isSealed = flag;
    }

    public void setLabelled(boolean flag) {
        this.isLabelled = flag;
    }
}

class Cleaning implements Callable<TomatoSauceBottle> {
    private TomatoSauceBottle tomatoSauceBottle;
    Cleaning(TomatoSauceBottle tomatoSauceBottle) {
        this.tomatoSauceBottle = tomatoSauceBottle;
    }
    @Override
    public TomatoSauceBottle call() throws Exception {
        System.out.println("Got the Tomato Sauce Bottle for Processing....Cleaning the Tomato Sauce Bottle.....");
        Thread.sleep(4000);
        tomatoSauceBottle.setFilled(true);
        System.out.println("Tomato Sauce Bottle is Cleaned. Passing the Bottle to the next Stage");
        return tomatoSauceBottle;
    }
}

class Filling implements Callable<TomatoSauceBottle> {
    private TomatoSauceBottle tomatoSauceBottle;
    Filling(TomatoSauceBottle tomatoSauceBottle) {
        this.tomatoSauceBottle = tomatoSauceBottle;
    }
    @Override
    public TomatoSauceBottle call() throws Exception {
        System.out.println("Got the Tomato Sauce Bottle for Processing....Filling with Tomato Sauce.....");
        Thread.sleep(3000);
        tomatoSauceBottle.setFilled(true);
        System.out.println("Can is Filled. Passing the Bottle to the next Stage");
        return tomatoSauceBottle;
    }
}

class Sealing implements Callable<TomatoSauceBottle> {
    private TomatoSauceBottle tomatoSauceBottle;
    Sealing(TomatoSauceBottle tomatoSauceBottle) {
        this.tomatoSauceBottle = tomatoSauceBottle;
    }
    @Override
    public TomatoSauceBottle call() throws Exception {
        System.out.println("Got the Tomato Sauce Bottle for Processing....Sealing the Tomato Sauce Bottle.....");
        Thread.sleep(1500);
        tomatoSauceBottle.setFilled(true);
        System.out.println("Tomato Sauce Bottle is Sealed. Passing the Bottle to the next Stage");
        return tomatoSauceBottle;
    }
}

class Labelling implements Callable<TomatoSauceBottle> {
    private TomatoSauceBottle tomatoSauceBottle;
    Labelling(TomatoSauceBottle tomatoSauceBottle) {
        this.tomatoSauceBottle = tomatoSauceBottle;
    }
    @Override
    public TomatoSauceBottle call() throws Exception {
        System.out.println("Got the Tomato Sauce Bottle for Processing....Labelling the Tomato Sauce Bottle.....");
        Thread.sleep(1000);
        tomatoSauceBottle.setFilled(true);
        System.out.println("Tomato Sauce Bottle is Labelled. Passing the Bottle to the next Stage");
        return tomatoSauceBottle;
    }
}
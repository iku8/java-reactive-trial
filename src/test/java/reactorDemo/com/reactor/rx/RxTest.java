package reactorDemo.com.reactor.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import static java.lang.Thread.sleep;

public class RxTest {

    @Test
    public void nonBlockingTest() throws InterruptedException {
        System.out.println("start");
        sleep(5000);
        Observable.zip(this.nonBlockingText1(), this.nonBlockingText2(), (l1, l2) -> l1 + " " + l2)
            .subscribe(str -> System.out.println(str));

        System.out.println("end");
        sleep(20000);
    }

    @Test
    public void test() throws InterruptedException {
        sleep(5000);
        StopWatch sw = new StopWatch();
        sw.start();
        System.out.println("start");
        this.nonBlockingText1().flatMap(res -> {
            return Observable.zip(this.nonBlockingText3(res), this.nonBlockingText2(), (l1, l2) -> l1 + " " + l2);
//            return this.nonBlockingText3(res);
        }).subscribe(str -> {
            System.out.println(str);
            sw.stop();
            System.out.println(sw.getTotalTimeSeconds());
        });

        System.out.println("end");
        sleep(20000);
    }

    public Observable<String> nonBlockingText1() {
        return Observable.create((ObservableEmitter<String> e) -> {
            sleep(5000);
            e.onNext("hellooooooooo");
            e.onComplete();
        }).subscribeOn(Schedulers.io());
    }

    public Observable<String> nonBlockingText2() {
        return Observable.create((ObservableEmitter<String> e) -> {
            sleep(5000);
            e.onNext("woooooooorld");
            e.onComplete();
        }).subscribeOn(Schedulers.io());
    }

    public Observable<String> nonBlockingText3(String str) {
        return Observable.create((ObservableEmitter<String> e) -> {
            sleep(5000);
            e.onNext("string is " + str);
            e.onComplete();
        }).subscribeOn(Schedulers.io());
    }

    @Test
    public void blockingTest() throws InterruptedException {
        this.blockingText1();
        this.blockingText2();
    }

    public String blockingText1() throws InterruptedException {
        sleep(4000);
        return "hellooooooooo";
    }
    public String blockingText2() throws InterruptedException {
        sleep(4000);
        return "woooooooorld";
    }
}


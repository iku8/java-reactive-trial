package reactorDemo.com.reactor.rx;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ObservableFactoryTest {

    @Test
    public void fromFactoryTest() {
        Observable<List<String>> list = Observable.fromArray(Arrays.asList("a", "b", "c"));

        list.subscribe(System.out::println);
    }

    @Test
    public void createFactoryTest() {
        Observable<Integer> list = Observable.create(subscriber -> {
            subscriber.onNext(1);
            subscriber.onNext(2);
            subscriber.onNext(3);
            subscriber.onComplete();
        });

        list.subscribe(System.out::println);
    }

    @Test
    public void rangeFactoryTest() {
        Observable<Integer> list = Observable.range(1, 100);

        list.subscribe(System.out::println);
    }

    @Test
    public void justFactoryTest() {
        Observable<Integer> list = Observable.just(1, 2, 3);

        list.subscribe(System.out::println);
    }

    @Test
    public void errorFactoryTest() {
        Observable<Integer> list = Observable.error(new RuntimeException("error"));

        list
            .doOnError(e -> System.out.println("this is " + e.getMessage()))
            .subscribe(System.out::println);
    }

    @Test
    public void mergeTest() {
        Observable.merge(Observable.range(10, 20), Observable.range(0, 9))
            .subscribe(System.out::println);
    }

    @Test
    public void zipTest() {
        Observable<Integer> o1 = Observable.just(1, 3, 5);
        Observable<String> o2 = Observable.just("a", "b");
        Observable
            .zip(o1, o2, (Integer d1, String d2) -> d1.toString() + d2)
            .subscribe(System.out::println);
    }

    @Test
    public void newThreadTest() {
        Observable<Integer> o1 = Observable.just(1, 3).subscribeOn(Schedulers.newThread()).map(e -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(e*2);
            return e*2;
        });;
        Observable<String> o2 = Observable.just("a", "b");
        Observable
            .zip(o1, o2, (Integer d1, String d2) -> d1.toString() + d2)
            .observeOn(Schedulers.computation())
            .subscribe(x -> {
                System.out.println(Thread.currentThread().getName());
                System.out.println(x);
            });
    }
}

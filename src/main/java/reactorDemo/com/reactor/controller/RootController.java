package reactorDemo.com.reactor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static java.lang.Thread.sleep;

@RequestMapping("api")
@RestController
public class RootController {

    @GetMapping("flux")
    public Mono<String> flux() {
        return Mono.defer(() -> {
//            int i = 0;
//            while (i < 1) {
//                i = i + 1;
//                System.out.println(i);
//                try {
//                    sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            return Mono.just("hello mono2");
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @GetMapping("mvc")
    public String mvc() {
//        int i = 0;
//        while (i < 1) {
//            i = i + 1;
//            System.out.println(i);
//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        return "hello nomal";
    }

    @GetMapping("test")
    public Flux test() {
        return Flux.defer(() -> {
            return Flux.range(1, 10)
                .filter(e -> e % 2 == 0)
                .map(e -> e * 10);
        }).subscribeOn(Schedulers.boundedElastic());
    }
}

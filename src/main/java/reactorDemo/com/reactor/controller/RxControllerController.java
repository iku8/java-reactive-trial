package reactorDemo.com.reactor.controller;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactorDemo.com.reactor.domain.Post;
import reactorDemo.com.reactor.service.rx.FollowerService;
import reactorDemo.com.reactor.service.rx.PostService;
import reactorDemo.com.reactor.service.rx.UserService;

import java.util.List;

@RestController
@RequestMapping("api/rx")
@RequiredArgsConstructor
public class RxControllerController {

    private final UserService userService;
    private final PostService postService;
    private final FollowerService followerService;

    @GetMapping
    public Observable<Pair<List<Post>, Long>> index() throws InterruptedException {
        StopWatch sw = new StopWatch();
        sw.start();
        return userService.findByUserId(1L)
            .flatMap(user -> {
                return Observable.zip(
                    postService.findAllByUserId(user.getUserId()).subscribeOn(Schedulers.io()),
                    followerService.getByUserId(user.getUserId()).subscribeOn(Schedulers.io()),
                    (posts, followers) -> Pair.of(posts, followers.stream().count()));
            })
            .doOnComplete(() -> {
                sw.stop();
                System.out.println(sw.getTotalTimeSeconds());
            });
    }

    @GetMapping("single")
    public Single<String> single() {
        return Single.just("hello rxjava");
    }
}

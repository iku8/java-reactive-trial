package reactorDemo.com.reactor.service.rx;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.stereotype.Service;
import reactorDemo.com.reactor.domain.Follower;
import reactorDemo.com.reactor.domain.Post;

import java.util.Arrays;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Override
    public Observable<List<Post>> findAllByUserId(long userId) {
        return Observable.fromArray(
            Arrays.asList(
                Post.builder()
                    .postId(1L)
                    .title("test title1")
                    .body("test body1")
                    .build(),
                Post.builder()
                    .postId(1L)
                    .title("test title1")
                    .body("test body1")
                    .build()
            )
        ).map(e -> {
            Thread.sleep(3000);
            return e;
        });
    }
}

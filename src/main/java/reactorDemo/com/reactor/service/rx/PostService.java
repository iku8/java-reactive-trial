package reactorDemo.com.reactor.service.rx;

import io.reactivex.Observable;
import reactorDemo.com.reactor.domain.Post;

import java.util.List;

public interface PostService {
    Observable<List<Post>> findAllByUserId(long userId) throws InterruptedException;
}

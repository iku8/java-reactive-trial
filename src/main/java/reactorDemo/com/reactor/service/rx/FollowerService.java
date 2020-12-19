package reactorDemo.com.reactor.service.rx;

import io.reactivex.Observable;
import reactorDemo.com.reactor.domain.Follower;

import java.util.List;

public interface FollowerService {
    Observable<List<Follower>> getByUserId(long userId) throws InterruptedException;
}

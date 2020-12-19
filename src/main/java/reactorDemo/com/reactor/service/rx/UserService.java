package reactorDemo.com.reactor.service.rx;

import io.reactivex.Observable;
import reactorDemo.com.reactor.domain.User;

public interface UserService {
    Observable<User> findByUserId(long userId) throws InterruptedException;
}

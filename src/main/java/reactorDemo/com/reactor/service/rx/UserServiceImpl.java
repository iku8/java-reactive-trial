package reactorDemo.com.reactor.service.rx;

import io.reactivex.Observable;
import org.springframework.stereotype.Service;
import reactorDemo.com.reactor.domain.User;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public Observable<User> findByUserId(long userId) {
        return Observable.just(User.builder()
            .userId(userId)
            .name("test name")
            .email("test@example.com")
            .build());
    }
}

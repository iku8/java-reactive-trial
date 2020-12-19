package reactorDemo.com.reactor.service.rx;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.stereotype.Service;
import reactorDemo.com.reactor.domain.Follower;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class FollowerServiceImpl implements FollowerService {

    @Override
    public Observable<List<Follower>> getByUserId(long userId) {
        return Observable.fromArray(
            Arrays.asList(
                Follower.builder()
                    .userId(1L)
                    .build(),
                Follower.builder()
                    .userId(2L)
                    .build(),
                Follower.builder()
                    .userId(3L)
                    .build()
            ))
            .map(e -> {
                long randomNumber = new Random().nextInt(6);

                if(3 != randomNumber) {
                    throw new RuntimeException("fail get followers");
                }

                Thread.sleep(3000);
                return e;
            })
            .retry(1)
            .onErrorReturn(e -> {
                return Collections.emptyList();
            });
    }
}

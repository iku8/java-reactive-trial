package reactorDemo.com.reactor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReactorServiceImpl implements ReactorService {

    @Override
    public void exec() {
        log.debug("hello reactor");
    }
}

package reactorDemo.com.reactor.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.concurrent.RecursiveAction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReactorServiceImplTest {

    @InjectMocks
    ReactorService reactorService;

    @Test
    public void test() {
        reactorService.exec();
    }
}

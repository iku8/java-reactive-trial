package reactorDemo.com.reactor.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    private long userId;
    private String name;
    private String email;
}

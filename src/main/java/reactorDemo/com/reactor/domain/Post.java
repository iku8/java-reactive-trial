package reactorDemo.com.reactor.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Post {
    private long postId;
    private String title;
    private String body;
}

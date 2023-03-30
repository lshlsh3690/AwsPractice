package AwsProject.Aws.web.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    //junit4에서는 @After 어노테이션을 쓰지만
    //junit5에서는 @AfterEach로 써야 한다.
    @AfterEach
    public void cleanup(){
        this.postsRepository.deleteAll();
    }

    @Test
    void loadPost(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        this.postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jojoldu@gmailcom")
                .build());

        //when
        List<Posts> postsList = this.postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
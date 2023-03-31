package AwsProject.Aws.web.domain;

import AwsProject.Aws.domain.posts.Posts;
import AwsProject.Aws.domain.posts.PostsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
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

    @Test
    void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        this.postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = this.postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        System.out.println(">>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }
}
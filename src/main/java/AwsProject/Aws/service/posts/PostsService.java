package AwsProject.Aws.service.posts;

import AwsProject.Aws.domain.posts.Posts;
import AwsProject.Aws.domain.posts.PostsRepository;
import AwsProject.Aws.web.dto.PostsListResponseDto;
import AwsProject.Aws.web.dto.PostsResponseDto;
import AwsProject.Aws.web.dto.PostsSaveRequestDto;
import AwsProject.Aws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;
    public Long save(PostsSaveRequestDto requestDto) {
        return this.postsRepository.save(requestDto.toEntity()).getId();
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = this.postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }


    //영속성 컨텍스트 때문에 jpa 쿼리를 날리지 않아도 값이 변경되면
    //트랜잭션이 끝나는 시점에 해당 테이블에 변경을 반영한다.
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = this.postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }


    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return this.postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = this.postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        this.postsRepository.delete(posts);
    }


}

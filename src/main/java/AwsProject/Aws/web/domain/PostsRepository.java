package AwsProject.Aws.web.domain;

import AwsProject.Aws.web.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}

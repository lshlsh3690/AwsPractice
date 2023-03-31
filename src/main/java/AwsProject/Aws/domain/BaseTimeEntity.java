package AwsProject.Aws.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass//Jpa entity 클래스들이 BaseTime Entity를 상속할 경우 필드들도 칼럼으로 인식하게 합니다.
@EntityListeners(AuditingEntityListener.class) //basetimeentity 클래스에 auditing 기능을 포합시킵니다. springbootapllication 클래스에 @EnableJpaAuditing 어노테이션을 붙여줘야한다.
public class BaseTimeEntity {
    @CreatedDate//Entity가 생성되어 저장할때 시간이 자동저장됩니다.
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

}

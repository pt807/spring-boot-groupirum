package com.green.groupirum.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//Main함수가 있는 클래스에 @EnableJpaAuditing 추가해야 사용가능
@Getter
@MappedSuperclass // 해당 어노테이션이 붙은 클래스를 상속한 경우 해당 클래스의 필드를 컬럼으로 인식하게 한다.
@EntityListeners(AuditingEntityListener.class) //해당 어노테이션이 붙은 클래스에 Auditing 기능을 포함시킨다.
public class TimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

}

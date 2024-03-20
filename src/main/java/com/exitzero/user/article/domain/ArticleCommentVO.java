package com.exitzero.user.article.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleCommentVO {
    private Long articleCommentId;
    private Long articleId;
    private String content;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}

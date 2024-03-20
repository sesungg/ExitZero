package com.exitzero.user.article.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArticleVO {
    private Long id;
    private String title;
    private String content;
    private String category;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    private List<ArticleCommentVO> articleCommentList;

    public ArticleVO(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = LocalDateTime.now();
        this.createdBy = "testUser";
    }
}

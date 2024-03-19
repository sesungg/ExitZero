package exitzero_user.article.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class Article {
    private Long id;
    private String title;
    private String content;
    private String category;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    private Article(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = LocalDateTime.now();
        this.createdBy = "testUser";
    }

    public static Article of(String title, String content, String category) {
        return new Article(title, content, category);
    }
}

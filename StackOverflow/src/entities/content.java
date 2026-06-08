package entities;

import java.time.LocalDateTime;

public abstract class content {
    String contentId;
    String contentBody;
    user author;
    LocalDateTime creationDate;

    public content(String contentId, String contentBody, user user) {
        this.contentId = contentId;
        this.contentBody = contentBody;
        this.author = user;
        this.creationDate = LocalDateTime.now();
    }

    public String getContentId() {
        return contentId;
    }

    public String getContentBody() {
        return contentBody;
    }

    public user getAuthor() {
        return author;
    }
}
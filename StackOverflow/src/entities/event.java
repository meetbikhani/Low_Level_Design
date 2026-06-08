package entities;

public class event {

    public enum EventType {
        UPVOTE_QUESTION,
        DOWNVOTE_QUESTION,
        UPVOTE_ANSWER,
        DOWNVOTE_ANSWER
    }

    EventType eventType;
    post post;

    public event(EventType eventType, post post) {
        this.eventType = eventType;
        this.post = post;
    }

    public EventType getEventType() {
        return eventType;
    }

    public post getPost() {
        return post;
    }
}

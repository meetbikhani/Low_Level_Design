package observer;
import entities.*;

public class reputationManager implements postObserver {
    @Override
    public void onPost(event event) {
        event.EventType type = event.getEventType();
        post post = event.getPost();
        switch (type) {
            case UPVOTE_QUESTION:
                post.getAuthor().updateVoteCount(2);
                break;
            case DOWNVOTE_QUESTION:
                post.getAuthor().updateVoteCount(-2);
                break;
            case UPVOTE_ANSWER:
                post.getAuthor().updateVoteCount(1);
                break;
            case DOWNVOTE_ANSWER:
                post.getAuthor().updateVoteCount(-1);
                break;
        }
    }
}
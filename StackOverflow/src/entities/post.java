package entities;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import observer.*;

public abstract class post extends content {
    List<comment> comments = new ArrayList<>();
    List<postObserver> observers = new ArrayList<>();
    AtomicInteger voteCount = new AtomicInteger(0);
    Map<String, event.EventType> votes = new HashMap<>();


    public post(String contentId, String contentBody, user user) {
        super(contentId, contentBody, user);
    }

    public void addObserver(postObserver observer) {
        observers.add(observer);
    }

    public void addComment(comment comment) {
        comments.add(comment);
    }

    public void notifyAllObservers(event event) {
        for (postObserver observer : observers) {
            observer.onPost(event);
        }
    }

    private int getVoteValue(event.EventType voteType) {
        switch (voteType) {
            case UPVOTE_QUESTION:
                return 2;
            case DOWNVOTE_QUESTION:
                return -2;
            case UPVOTE_ANSWER:
                return 1;
            case DOWNVOTE_ANSWER:
                return -1;
            default:
                return 0;
        }
    }

    public synchronized void vote(user user, event.EventType newVote) {
        String userId = user.getUserId();
        event.EventType oldVote = votes.get(userId);
        if (oldVote == newVote) {
            return;
        }
        int scoreChange = 0;
        if (oldVote != null) {
            scoreChange -= getVoteValue(oldVote);
        }
        scoreChange += getVoteValue(newVote);
        votes.put(userId, newVote);
        voteCount.addAndGet(scoreChange);
        notifyAllObservers(new event(newVote, this));
    }
}
package entities;

import java.util.concurrent.atomic.AtomicInteger;

public class user {
    String userName;
    String userId;
    AtomicInteger voteCount;
    String email;

    public user(String userName, String userId, String email) {
        this.userName = userName;
        this.userId = userId;
        this.voteCount = new AtomicInteger(0);
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public int getVoteCount() {
        return voteCount.get();
    }

    public void updateVoteCount(int delta) {
        voteCount.addAndGet(delta);
    }
}
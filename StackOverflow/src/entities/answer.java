package entities;

public class answer extends post {
    Boolean isAccepted = false;

    public answer(String contentId, String contentBody, user user) {
        super(contentId, contentBody, user);
    }

    public Boolean getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }
}
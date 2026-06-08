package observer;

import entities.*;

public class EmailService implements postObserver {
    @Override
    public void onPost(event event) {
        String email = event.getPost().getAuthor().getEmail();
        String post = event.getPost().getContentId();
        System.out.println("New vote received for post " + post);
        System.out.println("Email sent to " + email);
    }
}
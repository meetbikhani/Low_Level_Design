package entities;
import java.util.*;

public class question extends post {
    List<tags> tags = new ArrayList<>();
    List<answer> answers = new ArrayList<>();
    answer acceptedAnswer;

    public question(String contentId, String contentBody, user user, List<String> tags) {
        super(contentId, contentBody, user);
        for (String tag : tags) {
            // System.out.println("Tag: " + tag);
            this.tags.add(new tags(tag));
        }
        // createTags(tags);
    }

    // private void createTags(List<String> tags) {
    //     for (String tag : tags) {
    //         this.tags.add(new tags(tag));
    //     }
    // }

    public void addTag(tags tag) {
        tags.add(tag);
    }

    public void addAnswer(answer answer) {
        answers.add(answer);
    }

    public void setAcceptedAnswer(answer answer) {
        acceptedAnswer = answer;
    }

    public List<tags> getTags() {
        // System.out.println("i am coming from question");
        // System.out.println("Tags: " + tags);
        // for (tags tag : tags) {
        //     System.out.println("Tag: " + tag.getTagName());
        // }
        return tags;
    }
}
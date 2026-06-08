import entities.*;
import service.*;
import java.util.*;

public class StackOverflowDemo {
    public static void main(String[] args) {
        StackOverflow stackOverflow = new StackOverflow();

        user user1 = stackOverflow.createUser("John", "1", "john@gmail.com");   
        user user2 = stackOverflow.createUser("Mary", "2", "mary@gmail.com");
        user user3 = stackOverflow.createUser("Bob", "3", "bob@gmail.com");
        user user4 = stackOverflow.createUser("Alice", "4", "alice@gmail.com");

        question question1 = stackOverflow.createQuestion("1", "What is the meaning of life?", user1, List.of("java", "programming", "stackoverflow"));

        System.out.println("Question 1 content id: " + question1.getContentId());
        // System.out.println("Question 1 tags: " + question1.getTags());

        answer answer1 = stackOverflow.createAnswer("1", "42", user2, question1);
        System.out.println("Answer 1 content id: " + answer1.getContentId());
        answer answer2 = stackOverflow.createAnswer("1", "42", user3, question1);
        System.out.println("Answer 2 content id: " + answer2.getContentId());

        stackOverflow.acceptAnswer(question1, answer2);
        Boolean isAccepted2 = answer2.getIsAccepted();
        System.out.println("Is answer accepted? " + isAccepted2);
        Boolean isAccepted1 = answer1.getIsAccepted();
        System.out.println("Is answer accepted? " + isAccepted1);

        List<question> questionsOnTag = stackOverflow.getQuestionsOnTag(List.of("java"));
        if (questionsOnTag.size() == 0) {
            System.out.println("No questions found");
        }
        for (question question : questionsOnTag) {
            System.out.println("Question content id: " + question.getContentId());
        }

        int voteCount = user1.getVoteCount();
        System.out.println(voteCount);
        voteCount = user2.getVoteCount();
        System.out.println(voteCount);
        voteCount = user3.getVoteCount();
        System.out.println(voteCount);
        voteCount = user4.getVoteCount();
        System.out.println(voteCount);

        question1.vote(user4, event.EventType.UPVOTE_QUESTION);
        voteCount = user1.getVoteCount();
        System.out.println(voteCount);
        voteCount = user2.getVoteCount();
        System.out.println(voteCount);
        voteCount = user3.getVoteCount();
        System.out.println(voteCount);
        voteCount = user4.getVoteCount();
        System.out.println(voteCount);
    }
}
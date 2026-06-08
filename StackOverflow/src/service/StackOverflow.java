package service;

import entities.*;
import observer.*;
import java.util.*;

public class StackOverflow {
    List<user> users = new ArrayList<>();
    List<question> questions = new ArrayList<>();
    postObserver reputationManager = new reputationManager();
    postObserver emailService = new EmailService();

    public user createUser(String userName, String userId, String email) {
        user user = new user(userName, userId, email);
        users.add(user);
        return user;
    }

    public question createQuestion(String contentId, String contentBody, user user, List<String> tags) {
        for (String tag : tags) {
            System.out.println("Tag: " + tag);
        }
        question question = new question(contentId, contentBody, user, tags);
        questions.add(question);
        question.addObserver(reputationManager);
        question.addObserver(emailService);
        return question;
    }

    public answer createAnswer(String contentId, String contentBody, user user, question question) {
        answer answer = new answer(contentId, contentBody, user);
        question.addAnswer(answer);
        answer.addObserver(reputationManager);
        answer.addObserver(emailService);
        return answer;
    }
    
    public void acceptAnswer(question question, answer answer) {
        question.setAcceptedAnswer(answer);
        answer.setIsAccepted(true);
    }

    public List<question> getQuestionsOnTag(List<String> tags) {
        System.out.println("i am searcing tag");
        for (String tag : tags) {
            System.out.println("Tag: " + tag);
        }
        List<question> questionsOnTag = new ArrayList<>();
        for (question question : questions) {
            List<tags> questionTags = question.getTags();
            for (tags questionTag : questionTags) {
                System.out.println("Question tag: " + questionTag.getTagName());
                if (tags.contains(questionTag.getTagName())) {
                    questionsOnTag.add(question);
                }
            }
        }
        return questionsOnTag;
    }
}
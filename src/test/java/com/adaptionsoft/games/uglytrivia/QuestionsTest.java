package com.adaptionsoft.games.uglytrivia;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class QuestionsTest {

    private Questions questions = new Questions();

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailOnUnknownCategory() {
        questions.nextFor("unknown category");
    }

    @Test
    @Parameters(method = "categoriesAndQuestions")
    public void shouldAskQuestion(Object category, String question) {
        assertThat(questions.nextFor(category), is(question));
    }

    @SuppressWarnings("unused")
    private Object[] categoriesAndQuestions() {
        return $($(Category.POP, "Pop Question 0"),
                $(Category.SCIENCE, "Science Question 0"),
                $("Sports", "Sports Question 0"),
                $("Rock", "Rock Question 0"));
    }

    @Test
    @Parameters(method = "categoriesAndSecondQuestions")
    public void shouldAskDifferentQuestionOnSecondCall(Object category, String question) {
        questions.nextFor(category);

        assertThat(questions.nextFor(category), is(question));
    }

    @SuppressWarnings("unused")
    private Object[] categoriesAndSecondQuestions() {
        return $($(Category.POP, "Pop Question 1"),
                $(Category.SCIENCE, "Science Question 1"),
                $("Sports", "Sports Question 1"),
                $("Rock", "Rock Question 1"));
    }

}

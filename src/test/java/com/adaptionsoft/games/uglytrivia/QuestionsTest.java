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
        questions.nextFor(null);
    }

    @Test
    @Parameters(method = "categoriesAndQuestions")
    public void shouldAskQuestion(Category category, String question) {
        assertThat(questions.nextFor(category), is(question));
    }

    @SuppressWarnings("unused")
    private Object[] categoriesAndQuestions() {
        return $($(Category.POP, "Pop Question 0"),
                $(Category.SCIENCE, "Science Question 0"),
                $(Category.SPORTS, "Sports Question 0"),
                $(Category.ROCK, "Rock Question 0"));
    }

    @Test
    @Parameters(method = "categoriesAndSecondQuestions")
    public void shouldAskDifferentQuestionOnSecondCall(Category category, String question) {
        questions.nextFor(category);

        assertThat(questions.nextFor(category), is(question));
    }

    @SuppressWarnings("unused")
    private Object[] categoriesAndSecondQuestions() {
        return $($(Category.POP, "Pop Question 1"),
                $(Category.SCIENCE, "Science Question 1"),
                $(Category.SPORTS, "Sports Question 1"),
                $(Category.ROCK, "Rock Question 1"));
    }

}

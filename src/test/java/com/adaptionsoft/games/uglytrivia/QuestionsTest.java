package com.adaptionsoft.games.uglytrivia;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(JUnitParamsRunner.class)
public class QuestionsTest {

    private Questions questions = new Questions();

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailOnUnknownCategory() {
        questions.nextFor("unknown category");
    }

    @Test
    @Parameters(method = "categoriesAndQuestions")
    public void shouldAskQuestion(String category, String question) {
        assertThat(questions.nextFor(category), is(question));
    }

    @Test
    public void shouldAskDifferentPopQuestionOnSecondCall() {
        questions.nextFor("Pop");

        assertThat(questions.nextFor("Pop"), is("Pop Question 1"));
    }

    @SuppressWarnings("unused")
    private Object[] categoriesAndQuestions() {
        return $($("Pop", "Pop Question 0"),
                $("Science", "Science Question 0"),
                $("Sports", "Sports Question 0"),
                $("Rock", "Rock Question 0"));
    }

    @Test
    public void shouldAskDifferentQuestionOnSecondCall() {
        fail("add test that the 4 categories do not ask same question twice");
    }
}

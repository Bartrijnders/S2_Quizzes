package gameplay;

import com.rijnders.dao.Dao;
import com.rijnders.dao.QuizDao;
import com.rijnders.entities.StandardQuestion;
import com.rijnders.entities.StandardTurn;
import com.rijnders.entityinterfaces.*;
import factories.QuizFactory;
import factories.QuizFactoryAble;
import sevices.RandomQuestionService;
import sevices.RandomQuestionServiceAble;
import sevices.ScoreService;
import sevices.ScoreServiceAble;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizGame implements QuizGameAble {

    private final User user;
    private final Questionnaire chosenQuestionnaire;
    private final List<StandardQuestion> questions;
    private Quiz quiz;
    private int turnNumber;
    private Turn activeTurn;

    public QuizGame(User user, Questionnaire questionnaire) throws SQLException {
        turnNumber = 0;
        this.user = user;
        this.chosenQuestionnaire = questionnaire;
        questions = new ArrayList<>(chosenQuestionnaire.getQuestions());
        initQuiz();
    }

    private void initQuiz() throws SQLException {
        QuizFactoryAble quizFactory = new QuizFactory();
        this.quiz = quizFactory.Create(user, chosenQuestionnaire);
        getNextTurn();
    }


    @Override
    public void getNextTurn() throws SQLException {
        if (!checkGameEnd()) {
            turnNumber++;
            StandardQuestion question;
            RandomQuestionServiceAble randomQuestionService = new RandomQuestionService();
            question = randomQuestionService.getRandomQuestionFromList(questions);
            questions.remove(question);
            this.activeTurn = new StandardTurn(turnNumber, question, quiz.getId());
            quiz.getTurns().add(activeTurn);
        }
    }

    @Override
    public boolean answerOpenQuestion(String chosenAnswer) throws SQLException {
        Answer answer;
        activeTurn.setCorrect(activeTurn.getQuestion().getCorrectAnswer().getAnswerLine().equals(chosenAnswer));
        activeTurn.setChosenAnswer(chosenAnswer);
        boolean checkEnd = checkGameEnd();
        if (!checkEnd) {
            getNextTurn();
        }
        return checkEnd;
    }


    @Override
    public boolean answerMultipleChoiceQuestion(String chosenAnswer) throws SQLException {
        activeTurn.setChosenAnswer(chosenAnswer);
        activeTurn.setCorrect(activeTurn.getQuestion().getCorrectAnswer().getAnswerLine().equals(chosenAnswer));
        boolean checkEnd = checkGameEnd();
        if (!checkEnd) {
            getNextTurn();
        }
        return checkEnd;
    }

    @Override
    public Quiz getQuiz() {
        return quiz;
    }

    @Override
    public Turn getActiveTurn() {
        return activeTurn;
    }

    public boolean checkGameEnd() throws SQLException {
        boolean end = questions.isEmpty();
        if (end) {
            Dao<Quiz> quizDao = new QuizDao();
            ScoreServiceAble scoreService = new ScoreService();
            quiz.setScore(scoreService.calcScore(quiz.getTurns()));
            quizDao.save(quiz);
        }
        return end;
    }

}



package fr.gamedev.question;

import fr.gamedev.question.answer.Answer;
import fr.gamedev.question.question.Question;
import fr.gamedev.question.user.User;
import fr.gamedev.question.useranswer.UserAnswer;
import fr.gamedev.question.answer.AnswerRepository;
import fr.gamedev.question.question.QuestionRepository;
import fr.gamedev.question.useranswer.UserAnswerRepository;
import fr.gamedev.question.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author RayaneKettana
 */
@RestController
public class ResponseController {
  /**
   * Answer Repository.
   */
  @Autowired
  private AnswerRepository answerRepo;

  /**
   * User Answer repository.
   */
  @Autowired
  private UserAnswerRepository userAnswerRepo;

  /**
   * Question repository.
   */
  @Autowired
  private QuestionRepository questionRepo;

  /**
   * User repository.
   */
  @Autowired
  private UserRepository userRepo;

  /**
   * User answer and process validation.
   *
   * @param questionId the id of the question.
   * @param answer     the Answer.
   * @param userId     the userId.
   * @return Indication about correctness of the answer provided.
   */
  @SuppressWarnings({"checkstyle:NeedBraces", "checkstyle:AvoidInlineConditionals", "checkstyle:MagicNumber"})
  @GetMapping("/response")
  public String answer(
    @RequestParam final long questionId,
    @RequestParam final Boolean answer,
    @RequestParam final long userId
  ) {

    String response = "Erreur pour trouver l'utilisateur";

    Optional<Question> question = questionRepo.findById(questionId);

    if (!question.isPresent()) return response;

    Optional<Answer> expectedAnswer = answerRepo.findByQuestion(question.get());
    response = "Erreur pour trouver la question";

    if (!expectedAnswer.isPresent()) return response;

    UserAnswer userAnswer = new UserAnswer();

    Optional<User> user = userRepo.findById(userId);

    if (!user.isPresent()) return response;

    List<UserAnswer> anwsered = userAnswerRepo.getAlreadyAnsweredCorrect(user.get(), question.get());
    System.out.println(anwsered);
    userAnswer.setUser(user.get());
    userAnswer.setAnswer(expectedAnswer.get());

    userAnswer.setPoints(expectedAnswer.get().getCorrectAnswer() == answer
      ? 5 - anwsered.size()
      : 0
    );

    userAnswerRepo.save(userAnswer);

    return responseFromPoints((int) userAnswer.getPoints());
  }

  /**
   * Get response from point.
   *
   * @param point point
   * @return (String)
   */
  @SuppressWarnings({"checkstyle:FinalParameters", "checkstyle:MagicNumber"})
  protected String responseFromPoints(Integer point) {
    return new HashMap<Integer, String>() {{
      put(0, "Réponse incorrecte");
      put(5, "Réponse correcte");
    }}.get(point);
  }
}
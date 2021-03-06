package com.example.multimodule.questionapplication.answer;

import com.example.multimodule.questionapplication.question.Question;
import com.example.multimodule.library.IdentifiedEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * @author RayaneKettana
 */
@Entity
public class Answer implements IdentifiedEntity {

  /**
   * The id.
   */
  @GeneratedValue(generator = "seq_gen_answer")
  @GenericGenerator(
    name = "seq_gen_answer",
    strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
    parameters = {
      @Parameter(name = "sequence_name", value = "seq_answer"),
      @Parameter(name = "initial_value", value = "0"),
      @Parameter(name = "increment_size", value = "1")
    }
  )
  @Id
  private long id;
  /**
   * The question.
   */
  @OneToOne(cascade = {CascadeType.ALL})
  private Question question;
  /**
   * Is answer correct.
   */
  private Boolean correctAnswer;

  /**
   * Get id.
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Set id.
   * @param newId the id to set
   */
  public void setId(final long newId) {
    this.id = newId;
  }

  /**
   * @return the question
   */
  public Question getQuestion() {
    return question;
  }

  /**
   * @param newQuestion the question to set
   */
  public void setQuestion(final Question newQuestion) {
    this.question = newQuestion;
  }

  /**
   * @return the correctAnswer
   */
  public Boolean getCorrectAnswer() {
    return correctAnswer;
  }

  /**
   * @param newCorrectAnswer the correctAnswer to set
   */
  public void setCorrectAnswer(final Boolean newCorrectAnswer) {
    this.correctAnswer = newCorrectAnswer;
  }

}

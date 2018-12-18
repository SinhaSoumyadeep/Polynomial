package polynomial;

/**
 * This class represents the polynomial term.
 */
public class Term {

  private Integer coefficient;
  private Integer power;

  /**
   * This method is used to get the power of the term.
   *
   * @return the power.
   */
  public Integer getPower() {
    return power;
  }

  /**
   * This method is used to get the coefficient of the term.
   *
   * @return the coefficient.
   */
  public Integer getCoefficient() {
    return coefficient;
  }

  /**
   * This method is used to set the coefficient of the Term.
   *
   * @param coefficient coefficient as integer.
   */
  public void setCoefficient(Integer coefficient) {
    this.coefficient = coefficient;
  }

  /**
   * This method is used to set the power of the Term.
   *
   * @param power power as integer.
   */
  public void setPower(Integer power) {
    this.power = power;
  }

  /**
   * This is a parameterized constructor that is used to create a term object.
   *
   * @param coefficient coefficient as integer.
   * @param power       power as integer.
   */
  public Term(Integer coefficient, Integer power) {

    this.coefficient = coefficient;
    this.power = power;
  }


}

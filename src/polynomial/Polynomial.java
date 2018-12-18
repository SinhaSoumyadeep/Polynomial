package polynomial;

/**
 * A polynomial is made of several terms each term having a coefficient and a variable raised to a
 * power. An example of such a polynomial is 𝑓(𝑥)=3𝑥4−5𝑥3+2𝑥−4. This Interface provides a set
 * of methods that can be used to build a polynomial and perform some operations on the created
 * polynomial.
 */
public interface Polynomial {


  /**
   * The addition is performed by combining all the terms and adding the coefficients of the terms
   * with the same power. For example (3𝑥4−5𝑥3+2𝑥−4) + (2𝑥3+2𝑥2+4) = 3𝑥4−3𝑥3+2𝑥2+2𝑥. The
   * degree of the sum is the maximum of the degrees of the two polynomials.
   *
   * @param coefficient this is the coefficient of the term.
   * @param power       this is the power of the term.
   * @throws IllegalArgumentException is thrown when an Illegal argument is added.
   */
  public void addTerm(int coefficient, int power) throws IllegalArgumentException;


  /**
   * A method getCoefficient that takes a power and returns the coefficient for the term with that
   * power.
   *
   * @param power this is the power of the term
   * @return the coefficient of the term.
   */
  public int getCoefficient(int power);


  /**
   * A method getDegree that returns the degree of this polynomial.
   *
   * @return the degree of the polynomial.
   */
  public int getDegree();

  /**
   * A method evaluate that takes a double-precision decimal number and returns a double-precision
   * result which is the evaluation of this polynomial using this argument’s value.
   *
   * @param x this method takes in a variable.
   * @return it returns the evaluated result.
   */
  public double evaluate(double x);

  /**
   * A method add that takes another Polynomial object and returns the polynomial obtained by adding
   * the two polynomials. Any implementation should ensure that this method does not mutate either
   * polynomial.The addition is performed by combining all the terms and adding the coefficients of
   * the terms with the same power. For example (3x^4−5x^3+2x−4) + (2x^3+2x^2+4) =
   * 3x^4−3x^3+2x^2+2x. The degree of the sum is the maximum of the degrees of the two polynomials.
   *
   * @param p2 this method takes another Polynomial as an argument.
   * @return the result of addition of two polynomial.
   */
  public Polynomial add(Polynomial p2);

  /**
   * A method derivative that takes no parameters and returns the polynomial obtained by
   * differentiating this polynomial.The derivative of a term 𝑎𝑥𝑏 is a term with coefficient ab
   * with the variable raised to the power b−1. The derivative of a constant term (x^0) is 0. For
   * example the derivative of 3x^4−5x^3+2x−4 is 12x^3−15x^2+2.
   *
   * @return the polynomial after performing derivative.
   */
  public Polynomial derivative();


}

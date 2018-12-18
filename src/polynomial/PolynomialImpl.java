package polynomial;

import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the concrete implementation of the Polynomial interface. This class provides the
 * implementation of the set of methods that are used to create and perform operations over a
 * polynomial.
 */
public class PolynomialImpl implements Polynomial {

  private PolynomialNode head;

  /**
   * This the default constructor that is used to initialize the polynomial.
   */
  public PolynomialImpl() {
    head = new EmptyNode();
  }

  /**
   * This constructor takes a polynomial as a string, parses it and creates the polynomial
   * accordingly. The string contains the polynomial, with each term separated by a space.
   *
   * @param polynomial this method takes a polynomial in the form of string.
   */
  public PolynomialImpl(String polynomial) throws IllegalArgumentException {
    this();

    String ph = returnVariable(polynomial);

    if (polynomial.matches("^[0-9](.)*")) {
      polynomial = "+" + polynomial;
    }

    StringTokenizer token = new StringTokenizer(polynomial, " ");
    while (token.hasMoreTokens()) {
      String term = preprocessTerms(token.nextToken(), ph);

      Pattern pat = Pattern.compile("^[+\\-]+[0-9]+" + ph + "[\\^]{1}[0-9]+$");
      Matcher mat = pat.matcher(term);

      if (mat.find()) {

        Integer coeff = Integer.parseInt(term.substring(0, term.indexOf(ph)));
        Integer power = Integer.parseInt(term.substring(term.indexOf('^') + 1));

        this.addTerm(coeff, power);
      } else {
        throw new IllegalArgumentException("The polynomial is incorrect!");
      }


    }

  }


  /**
   * This is a private constructor that is used to encapsulate the returning head node into a
   * PolynomialImpl object.
   *
   * @param head takes a returning head.
   */
  private PolynomialImpl(PolynomialNode head) {
    this.head = head;
  }

  /**
   * The addition is performed by combining all the terms and adding the coefficients of the terms
   * with the same power. For example (3𝑥4−5𝑥3+2𝑥−4) + (2𝑥3+2𝑥2+4) = 3𝑥4−3𝑥3+2𝑥2+2𝑥. The
   * degree of the sum is the maximum of the degrees of the two polynomials.
   *
   * @param coefficient this is the coefficient of the term.
   * @param power       this is the power of the term.
   * @throws IllegalArgumentException is thrown when an Illegal argument is added.
   */
  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {

    if (coefficient == 0) {
      return;
    }

    if (power < 0) {
      throw new IllegalArgumentException("power cannot be less than zero!!");
    }


    if (this.getCoefficient(power) != 0) {
      PolynomialNode term = head.filter(e -> {

        TermNode t = (TermNode) e;

        return t.getData().getPower() == power;

      });

      TermNode t = (TermNode) term;

      if (t.getData().getCoefficient() + coefficient == 0) {
        this.removeTerm(t.getData().getCoefficient(), t.getData().getPower());
      } else {
        t.getData().setCoefficient(t.getData().getCoefficient() + coefficient);
      }

    } else {
      Term polyTerm = new Term(coefficient, power);
      head = head.addToBack(polyTerm);
    }


  }

  /**
   * A method getCoefficient that takes a power and returns the coefficient for the term with that
   * power.
   *
   * @param power this is the power of the term
   * @return the coefficient of the term.
   */
  @Override
  public int getCoefficient(int power) {


    PolynomialNode term = head.filter(e -> {

      TermNode t = (TermNode) e;

      return t.getData().getPower() == power;

    });

    if (!(term instanceof EmptyNode)) {

      TermNode a = (TermNode) term;

      return a.getData().getCoefficient();
    } else {
      return 0;
    }

  }


  /**
   * A method getDegree that returns the degree of this polynomial.
   *
   * @return the degree of the polynomial.
   */
  @Override
  public int getDegree() {

    PolynomialNode iterator = head;
    return iterator.getDegree();
  }

  /**
   * A method evaluate that takes a double-precision decimal number and returns a double-precision
   * result which is the evaluation of this polynomial using this argument’s value.
   *
   * @param x this method takes in a variable.
   * @return it returns the evaluated result.
   */
  @Override
  public double evaluate(double x) {

    double result = head.reduce((c, p) -> {


      double res = (Integer) c * Math.pow(x, (Integer) p);

      return res;

    });

    return result;

  }

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
  @Override
  public Polynomial add(Polynomial p2) {

    String polynomial1 = preprocessForAddition(this.toString());

    String polynomial2 = preprocessForAddition(p2.toString());

    String polynomialcom = polynomial1 + polynomial2;

    return new PolynomialImpl(polynomialcom);

  }


  /**
   * A method derivative that takes no parameters and returns the polynomial obtained by
   * differentiating this polynomial.The derivative of a term 𝑎𝑥𝑏 is a term with coefficient ab
   * with the variable raised to the power b−1. The derivative of a constant term (x^0) is 0. For
   * example the derivative of 3x^4−5x^3+2x−4 is 12x^3−15x^2+2.
   *
   * @return the polynomial after performing derivative.
   */
  @Override
  public Polynomial derivative() {
    return filter(x -> {
      TermNode t = (TermNode) x;

      return t.getData().getPower() != 0;

    }).map(e -> {
      Term term = (Term) e;

      Term newterm = new Term(term.getCoefficient() * term.getPower(), term.getPower() - 1);

      return newterm;
    });
  }


  /**
   * The toString method is overridden to return the polynomial in sorted order in terms of power.
   *
   * @return the polynomial in the form of string.
   */
  @Override
  public String toString() {


    PolynomialNode iterator = head.sortByPower();


    String poly = iterator.printSelf().trim();


    if (poly.equals("")) {
      return "0";
    }

    if (poly.charAt(0) == '+') {
      return poly.substring(1);
    }

    return poly;

  }


  /**
   * This method take in as argument a polynomial and evaluates if both the polynomials are same.
   *
   * @param p2 this method takes in a polynomial as an argument.
   * @return true if the polynomials are same else return false.
   */
  @Override
  public boolean equals(Object p2) {


    Polynomial p1sorted = this.sortByPower();


    if (p2 instanceof Polynomial) {

      PolynomialImpl p21 = (PolynomialImpl) p2;
      Polynomial p2sorted = p21.sortByPower();

      if (p1sorted.toString().equals(p2sorted.toString())) {
        return true;
      }
    }

    return false;

  }

  /**
   * Any two polynomial that compare equal using the equals() method must produce the same
   * hashCode.
   *
   * @return the hashcode of the polynomial.
   */
  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }


  /**
   * A map is a higher-order function that converts a list that contains data of one type, into a
   * list of identical structure that contains data of another type. Each element in the resulting
   * list can be thought of as a conversion of the corresponding element in the original list.
   *
   * @param converter the function that is to be applied to all the terms.
   * @return the polynomial after the conversion.
   */
  private PolynomialImpl map(Function converter) {
    return new PolynomialImpl(head.map(converter));
  }


  /**
   * A filter is a higher-order function that filters a list according to some predicate and returns
   * a smaller list according to the predicate test.
   *
   * @param testfunc the predicate that will be tested on each term of the polynomial.
   * @return filtered polynomial.
   */
  private PolynomialImpl filter(Predicate testfunc) {
    return new PolynomialImpl(head.filter(testfunc));
  }

  /**
   * This method sorts the polynomial according to the power of each term.
   *
   * @return polynomial.
   */
  private PolynomialImpl sortByPower() {
    return new PolynomialImpl(head);
  }

  /**
   * This method is used to remove  terms from a polynomial.
   *
   * @param coefficient this method takes as one of the arguments a coefficient.
   * @param power       this method takes as one of the arguments a power.
   */
  private void removeTerm(int coefficient, int power) {

    Term term = new Term(coefficient, power);
    head = head.remove(term);
  }

  /**
   * This method is used to preprocess the  terms into a uniform string.
   *
   * @param term     this method takes in a term as one of its arguments.
   * @param variable this method takes in a variable as one of its arguments.
   * @return the processed uniform term.
   */
  private String preprocessTerms(String term, String variable) {
    term = term.trim();


    if (term.contains(variable) && !term.contains("^")) {
      term = term + "^1";
    }
    if (!term.contains(variable)) {
      term = term + variable + "^0";
    }

    return term;
  }

  /**
   * This method is used to preprocess polynomial before Addition.
   *
   * @param polynomial this method takes in a polynomial as a String.
   * @return the processed polynomial as String.
   */
  private String preprocessForAddition(String polynomial) {

    if (polynomial.matches("^[0-9](.*)")) {
      polynomial = "+" + polynomial;
    }

    polynomial = polynomial.replace("+", " +");
    polynomial = polynomial.replace("-", " -");

    return polynomial;
  }


  /**
   * This method checks if all the variables in the polynomial are uniform.
   *
   * @param polynomial takes in as an argument a polynomial in String format.
   * @return the variable in string format.
   * @throws IllegalArgumentException the variables are not uniform.
   */
  private String returnVariable(String polynomial) throws IllegalArgumentException {
    String variable = "";
    Pattern pattern = Pattern.compile("[+\\-]*[0-9]+([A-Za-z]*)[\\^]*[0-9]*[ ]{1}");
    Matcher matcher = pattern.matcher(polynomial + " ");
    while (matcher.find()) {

      if (!matcher.group(1).equals("")) {

        variable = variable + matcher.group(1);

      }
    }

    if (variable.equals("")) {
      variable = "x";
    }


    String ph = variable.charAt(0) + "";

    for (int i = 0; i < variable.length(); i++) {

      String place = variable.charAt(i) + "";
      if (!place.equals(ph)) {
        throw new IllegalArgumentException("the place holders are different");
      }
    }

    return ph;

  }


}

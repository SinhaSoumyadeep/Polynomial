package polynomial;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This is an interface that is used to represent each polynomial Node of Polynomial ADT.
 */
public interface PolynomialNode {

  /**
   * This method is used to add each term to the first of the Polynomial list.
   *
   * @param term this method takes in as an argument a term and adds it to the list.
   * @return it returns a new polynomial node after adding to the list.
   */
  public PolynomialNode addFront(Term term);

  /**
   * This method is used to add each term to the back of the Polynomial list.
   *
   * @param term this method takes in as an argument a term and adds it to the list.
   * @return it returns a new polynomial node after adding to the list.
   */
  public PolynomialNode addToBack(Term term);

  /**
   * This method removes the Term passed as an argument from the polynomial list.
   *
   * @param term this method takes a term as an argument.
   * @return this method returns the list after removing the term.
   */
  public PolynomialNode remove(Term term);

  /**
   * This method recursively prints the term.
   *
   * @return the term in string format.
   */
  public String printSelf();

  /**
   * A map is a higher-order function that converts a list that contains data of one type, into a
   * list of identical structure that contains data of another type. Each element in the resulting
   * list can be thought of as a conversion of the corresponding element in the original list.
   *
   * @param converter the function that is to be applied to all the terms.
   * @return the polynomialNode after the conversion.
   */
  public PolynomialNode map(Function converter);

  /**
   * A filter is a higher-order function that reduces a list according to some BinaryOperation and
   * returns a reduced object according to the binary test.
   *
   * @param test this method takes a BinryOperator test and applies it to all the term.
   * @return the reduced value is returned as a double.
   */
  public Double reduce(BinaryOperator test);

  /**
   * A filter is a higher-order function that filters a list according to some predicate and returns
   * a smaller list according to the predicate test.
   *
   * @param testFunc the predicate that will be tested on each term of the polynomial node.
   * @return filtered polynomial node.
   */
  public PolynomialNode filter(Predicate testFunc);

  /**
   * This method sorts the polynomial according to the power of each term.
   *
   * @return polynomial.
   */
  public PolynomialNode sortByPower();

  /**
   * This method adds the term in sorted order.
   *
   * @param term this method takes the term and adds it in sorted fashion.
   * @return the polynomial node.
   */
  public PolynomialNode addSorted(Term term);

  /**
   * This is a getter method that is used to get the term data.
   *
   * @return a polynomial term.
   */
  public Term getData();

  /**
   * This method gives the degree of a polynomial.
   *
   * @return the degree of the polynomial.
   */
  public int getDegree();


}

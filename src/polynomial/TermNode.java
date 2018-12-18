package polynomial;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This class is an implementation of the term node.
 */
public class TermNode implements PolynomialNode {
  private Term data;
  private PolynomialNode restTerm;



  /**
   *This a constructor that is used to create the Polynomial Node.
   *
   * @param data this is the current Term
   * @param restTerm this is rest of the Terms
   */
  public TermNode(Term data, PolynomialNode restTerm) {
    this.data = data;
    this.restTerm = restTerm;
  }

  /**
   * This method is used to add each term to the first of the Polynomial list.
   *
   * @param term this method takes in as an argument a term and adds it to the list.
   * @return it returns a new polynomial node after adding to the list.
   */
  @Override
  public PolynomialNode addFront(Term term) {
    return new TermNode(term, this);
  }

  /**
   * This method is used to add each term to the back of the Polynomial list.
   *
   * @param term this method takes in as an argument a term and adds it to the list.
   * @return it returns a new polynomial node after adding to the list.
   */
  @Override
  public PolynomialNode addToBack(Term term) {
    this.restTerm = this.restTerm.addToBack(term);
    return this;
  }

  /**
   * This method adds the term in sorted order.
   *
   * @param term this method takes the term and adds it in sorted fashion.
   * @return the polynomial node.
   */
  public PolynomialNode addSorted(Term term) {
    if (this.data.getPower() > term.getPower()) {
      //insert the book into the rest part of the list
      return new TermNode(this.data, this.restTerm.addSorted(term));
    } else {
      //prepend this list with this book
      return new TermNode(term, this);
    }
  }

  /**
   * This method removes the Term passed as an argument from the polynomial list.
   *
   * @param term this method takes a term as an argument.
   * @return this method returns the list after removing the term.
   */
  @Override
  public PolynomialNode remove(Term term) {
    if (this.data.getCoefficient() == term.getCoefficient()
            && this.data.getPower() == term.getPower()) {
      return this.restTerm; //return the rest of the list as the result of deletion
    } else {
      this.restTerm = this.restTerm.remove(term); //the rest is whatever the
      //result of removing it from the rest will be
      return this; //this is now the list as a result of deletion, so return it
    }
  }

  /**
   * This method recursively prints the term.
   *
   * @return the term in string format.
   */
  @Override
  public String printSelf() {

    if (this.data.getCoefficient() > 0) {

      if (this.data.getPower() == 0) {
        return "+" + this.data.getCoefficient() + "" + this.restTerm.printSelf();
      } else {
        return "+" + this.data.getCoefficient() + "x^" + this.data.getPower() +
                this.restTerm.printSelf();
      }

    } else {

      if (this.data.getCoefficient() == 0) {
        if (this.data.getPower() == 0) {
          return this.data.getCoefficient() + "" + this.restTerm.printSelf();
        } else {
          return this.data.getCoefficient() + "x^" + this.data.getPower() +
                  this.restTerm.printSelf();
        }
      }

      if (this.data.getCoefficient() < 0) {

        if (this.data.getPower() == 0) {
          return this.data.getCoefficient() + "" + this.restTerm.printSelf();
        } else {
          return this.data.getCoefficient() + "x^" + this.data.getPower() +
                  this.restTerm.printSelf();
        }
      }
    }


    return "";

  }

  /**
   * A map is a higher-order function that converts a list that contains data of one type, into a
   * list of identical structure that contains data of another type. Each element in the resulting
   * list can be thought of as a conversion of the corresponding element in the original list.
   *
   * @param converter the function that is to be applied to all the terms.
   * @return the polynomialNode after the conversion.
   */
  @Override
  public PolynomialNode map(Function converter) {
    return new TermNode((Term) converter.apply(this.data), this.restTerm.map(converter));
  }

  /**
   * A filter is a higher-order function that reduces a list according to some BinaryOperation and
   * returns a reduced object according to the binary test.
   *
   * @param test this method takes a BinryOperator test and applies it to all the term.
   * @return the reduced value is returned as a double.
   */
  @Override
  public Double reduce(BinaryOperator test) {
    return (Double) test.apply(this.data.getCoefficient(), this.data.getPower()) +
            this.restTerm.reduce(test);

  }

  /**
   * A filter is a higher-order function that filters a list according to some predicate and returns
   * a smaller list according to the predicate test.
   *
   * @param testFunc the predicate that will be tested on each term of the polynomial node.
   * @return filtered polynomial node.
   */
  @Override
  public PolynomialNode filter(Predicate testFunc) {
    if (testFunc.test(this)) {
      return new TermNode(this.data, this.restTerm.filter(testFunc));
    } else {
      return this.restTerm.filter(testFunc);
    }

  }

  /**
   * This method sorts the polynomial according to the power of each term.
   *
   * @return polynomial.
   */
  @Override
  public PolynomialNode sortByPower() {
    return this.restTerm.sortByPower().addSorted(this.data);
  }

  /**
   * This is a getter method that is used to get the term data.
   *
   * @return a polynomial term.
   */
  @Override
  public Term getData() {
    return data;
  }

  /**
   * This method gives the degree of a polynomial.
   *
   * @return the degree of the polynomial.
   */
  @Override
  public int getDegree() {
    return Math.max(this.data.getPower(),this.restTerm.getDegree());
  }


}

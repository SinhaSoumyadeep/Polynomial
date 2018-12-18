import org.junit.Test;

import polynomial.Polynomial;
import polynomial.PolynomialImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class tests the implementation of the Polynomial Interface.
 */
public class PolynomialTest {


  /**
   * This method tests derivatives of a polynomial.
   */
  @Test
  public void testDerivative() {

    Polynomial p = new PolynomialImpl("2");
    Polynomial pDerivative = p.derivative();
    assertEquals("0", pDerivative.toString());

    Polynomial p1 = new PolynomialImpl("2x^1 +5");
    Polynomial p1Derivative = p1.derivative();
    assertEquals("2", p1Derivative.toString());

    Polynomial p2 = new PolynomialImpl("4x^2");
    Polynomial p2Derivative = p2.derivative();
    assertEquals("8x^1", p2Derivative.toString());

    Polynomial p3 = new PolynomialImpl("4x^2 +2x^1");
    Polynomial p3Derivative = p3.derivative();
    assertEquals("8x^1+2", p3Derivative.toString());

    Polynomial p4 = new PolynomialImpl("15x^3");
    Polynomial p4Derivative = p4.derivative();
    assertEquals("45x^2", p4Derivative.toString());

    Polynomial p5 = new PolynomialImpl();
    Polynomial p5Derivative = p5.derivative();
    assertEquals("0", p5Derivative.toString());

    Polynomial p6 = new PolynomialImpl("4x^4 +5x^5 +6x^6 +7x^7");
    Polynomial p6Derivative = p6.derivative();
    Polynomial p6DoubleDerivative = p6Derivative.derivative();
    assertEquals("294x^5+180x^4+100x^3+48x^2", p6DoubleDerivative.toString());


  }

  /**
   * This method tests polynomial creation by string.
   */
  @Test
  public void testInvalidPolynomialCreationByString() {
    Polynomial p = new PolynomialImpl("2");
    assertEquals("2", p.toString());

    Polynomial pzero = new PolynomialImpl("");
    assertEquals("0", pzero.toString());

    Polynomial p3 = new PolynomialImpl("3x^2 +5x^2");
    assertEquals("8x^2", p3.toString());

    Polynomial p7 = new PolynomialImpl("3x^2 +5 +1x^1");
    assertEquals("3x^2+1x^1+5", p7.toString());

    Polynomial p8 = new PolynomialImpl("3x^2 +5 +4x^2");
    assertEquals("7x^2+5", p8.toString());

    Polynomial p9 = new PolynomialImpl("3x^2 +0 +4x^2");
    assertEquals("7x^2", p9.toString());

    Polynomial p10 = new PolynomialImpl("3x^2 +8x^100 +4x^2");
    assertEquals("8x^100+7x^2", p10.toString());

    Polynomial p11 = new PolynomialImpl("+3x^4 -2x^5 -5 -2x^4 +11x^1");
    assertEquals("-2x^5+1x^4+11x^1-5", p11.toString());

    Polynomial p13 = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    assertEquals("-2x^5-3x^4+11x^1-5", p13.toString());

    Polynomial p16 = new PolynomialImpl("0x^2");
    assertEquals("0", p16.toString());

    try {
      Polynomial p1 = new PolynomialImpl("a");
      fail();
    } catch (Exception e) {
      assertEquals("The polynomial is incorrect!", e.getMessage());
    }

    try {
      Polynomial p2 = new PolynomialImpl("3x^2 +a^2");
      fail();
    } catch (Exception e) {
      assertEquals("The polynomial is incorrect!", e.getMessage());
    }


    try {
      Polynomial p4 = new PolynomialImpl("3x^2 5x^2");
      fail();
    } catch (Exception e) {
      assertEquals("The polynomial is incorrect!", e.getMessage());
    }


    try {
      Polynomial p5 = new PolynomialImpl("3x^2 +5 2x^2");
      fail();
    } catch (Exception e) {
      assertEquals("The polynomial is incorrect!", e.getMessage());
    }

    try {
      Polynomial p6 = new PolynomialImpl("3x^2 +5 +x^2");
      fail();
    } catch (Exception e) {
      assertEquals("The polynomial is incorrect!", e.getMessage());
    }


    try {
      Polynomial p14 = new PolynomialImpl("3x^2 + +2x^2");
      fail();
    } catch (Exception e) {
      assertEquals("The polynomial is incorrect!", e.getMessage());
    }

    try {
      Polynomial p15 = new PolynomialImpl("3x^2 +2x^-2");
      fail();
    } catch (Exception e) {
      assertEquals("The polynomial is incorrect!", e.getMessage());
    }

    try {
      Polynomial p17 = new PolynomialImpl("3x^2 +1y^1");
      fail();
    } catch (Exception e) {
      assertEquals("the place holders are different", e.getMessage());
    }

    try {
      Polynomial p18 = new PolynomialImpl("2x^2+3x^3");
      fail();
    } catch (Exception e) {
      assertEquals("The polynomial is incorrect!", e.getMessage());
    }


  }

  /**
   * This method tests the add polynomial.
   */
  @Test
  public void testAddPolynomials() {

    Polynomial p1 = new PolynomialImpl("");
    Polynomial p2 = new PolynomialImpl("");
    Polynomial p1addp2 = p1.add(p2);
    assertEquals("0", p1addp2.toString());
    assertEquals("0", p1.toString());
    assertEquals("0", p2.toString());

    Polynomial p3 = new PolynomialImpl("3x^2");
    Polynomial p4 = new PolynomialImpl("2x^2");
    Polynomial p3addp4 = p3.add(p4);
    assertEquals("5x^2", p3addp4.toString());
    assertEquals("3x^2", p3.toString());
    assertEquals("2x^2", p4.toString());

    Polynomial p5 = new PolynomialImpl("3x^2");
    Polynomial p6 = new PolynomialImpl("1");
    Polynomial p5addp6 = p5.add(p6);
    assertEquals("3x^2+1", p5addp6.toString());
    assertEquals("3x^2", p5.toString());
    assertEquals("1", p6.toString());

    Polynomial p7 = new PolynomialImpl("3x^2 +5x^3 +6");
    Polynomial p8 = new PolynomialImpl("2x^3 +4x^4 +2");
    Polynomial p7addp8 = p7.add(p8);
    assertEquals("4x^4+7x^3+3x^2+8", p7addp8.toString());
    assertEquals("5x^3+3x^2+6", p7.toString());
    assertEquals("4x^4+2x^3+2", p8.toString());

    Polynomial p9 = new PolynomialImpl("3x^2 +5x^3 +6");
    Polynomial p10 = new PolynomialImpl("-3x^2 +4x^4 +2");
    Polynomial p9addp10 = p9.add(p10);
    assertEquals("4x^4+5x^3+8", p9addp10.toString());
    assertEquals("5x^3+3x^2+6", p9.toString());
    assertEquals("4x^4-3x^2+2", p10.toString());

    Polynomial p11 = new PolynomialImpl("3x^2 +5x^3 +6");
    Polynomial p12 = new PolynomialImpl("-13x^2 +4x^4 +2");
    Polynomial p11addp12 = p11.add(p12);
    assertEquals("4x^4+5x^3-10x^2+8", p11addp12.toString());
    assertEquals("5x^3+3x^2+6", p11.toString());
    assertEquals("4x^4-13x^2+2", p12.toString());

    Polynomial p13 = new PolynomialImpl("3x^2 +5x^3");
    Polynomial p14 = new PolynomialImpl("-13x^5 +4x^4 +2");
    Polynomial p13addp14 = p13.add(p14);
    assertEquals("-13x^5+4x^4+5x^3+3x^2+2", p13addp14.toString());
    assertEquals("5x^3+3x^2", p13.toString());
    assertEquals("-13x^5+4x^4+2", p14.toString());

    Polynomial p15 = new PolynomialImpl("3x^2 +5x^3");
    Polynomial p16 = new PolynomialImpl("-3x^2 -5x^3");
    Polynomial p15addp16 = p15.add(p16);
    assertEquals("0", p15addp16.toString());
    assertEquals("5x^3+3x^2", p15.toString());
    assertEquals("-5x^3-3x^2", p16.toString());


  }

  /**
   * This method tests the creation of polynomial by term.
   */
  @Test
  public void testValidPolynomialCreationByTerm() {

    Polynomial p = new PolynomialImpl();
    p.addTerm(2, 0);
    assertEquals("2", p.toString());

    Polynomial p3 = new PolynomialImpl();
    p3.addTerm(3, 2);
    p3.addTerm(5, 2);
    assertEquals("8x^2", p3.toString());

    Polynomial p7 = new PolynomialImpl();
    p7.addTerm(3, 2);
    p7.addTerm(5, 0);
    p7.addTerm(1, 1);
    assertEquals("3x^2+1x^1+5", p7.toString());

    Polynomial p8 = new PolynomialImpl();
    p8.addTerm(3, 2);
    p8.addTerm(5, 0);
    p8.addTerm(4, 2);
    assertEquals("7x^2+5", p8.toString());


    Polynomial p9 = new PolynomialImpl();
    p9.addTerm(3, 2);
    p9.addTerm(0, 0);
    p9.addTerm(4, 2);
    assertEquals("7x^2", p9.toString());


    Polynomial p10 = new PolynomialImpl();
    p10.addTerm(3, 2);
    p10.addTerm(8, 100);
    p10.addTerm(4, 2);
    assertEquals("8x^100+7x^2", p10.toString());


    Polynomial p11 = new PolynomialImpl();
    p11.addTerm(3, 4);
    p11.addTerm(-2, 5);
    p11.addTerm(-5, 0);
    p11.addTerm(-2, 4);
    p11.addTerm(11, 1);
    assertEquals("-2x^5+1x^4+11x^1-5", p11.toString());


    Polynomial p13 = new PolynomialImpl();
    p13.addTerm(-3, 4);
    p13.addTerm(-2, 5);
    p13.addTerm(-5, 0);
    p13.addTerm(11, 1);
    assertEquals("-2x^5-3x^4+11x^1-5", p13.toString());

    Polynomial p16 = new PolynomialImpl();
    p16.addTerm(0, 2);
    assertEquals("0", p16.toString());

    Polynomial p17 = new PolynomialImpl();
    assertEquals("0", p17.toString());


    try {
      Polynomial p15 = new PolynomialImpl();
      p15.addTerm(3, 2);
      p15.addTerm(2, -2);
      fail();
    } catch (Exception e) {
      assertEquals("power cannot be less than zero!!", e.getMessage());
    }


    Polynomial p18 = new PolynomialImpl();
    p18.addTerm(3, 2);
    p18.addTerm(0, -2);
    assertEquals("3x^2", p18.toString());


  }

  /**
   * This method tests the evaluation of polynomial.
   */
  @Test
  public void testEvaluate() {

    Polynomial p = new PolynomialImpl("+3x^4 -2x^5 -5 -2x^4 +11x^1");
    assertEquals(-31.0, p.evaluate(2.0), 0.01);
    assertEquals(-5.0, p.evaluate(0.0), 0.01);

    Polynomial p1 = new PolynomialImpl("-19x^456");
    assertEquals(-1775.415658345827, p1.evaluate(1.01), 0.01);

    Polynomial p2 = new PolynomialImpl("2x^3 +3x^1 -7");
    assertEquals(2209.3540000000007, p2.evaluate(10.3), 0.01);

    Polynomial p3 = new PolynomialImpl("-90");
    assertEquals(-90.0, p3.evaluate(12.5), 0.01);

    Polynomial p4 = new PolynomialImpl("");
    assertEquals(0.0, p4.evaluate(17.5), 0.01);

    Polynomial p5 = new PolynomialImpl("2x^3 +3x^1");
    assertEquals(0.0, p5.evaluate(0.0), 0.01);

  }

  /**
   * This method tests the degree of polynomial.
   */
  @Test
  public void testDegree() {
    Polynomial p = new PolynomialImpl("2x^2 +3x^3");
    assertEquals(3, p.getDegree());

    p = new PolynomialImpl("9x^17 +3x^23 +4x^1 +26x^1890");
    assertEquals(1890, p.getDegree());

    p = new PolynomialImpl();
    assertEquals(0, p.getDegree());

    Polynomial p15 = new PolynomialImpl("3x^2 +5x^3");
    Polynomial p16 = new PolynomialImpl("-3x^2 -5x^3");
    Polynomial p15addp16 = p15.add(p16);
    assertEquals(0, p15addp16.getDegree());


  }

  /**
   * This method tests Coefficient of polynomial.
   */
  @Test
  public void testGetCoefficient() {

    Polynomial p1 = new PolynomialImpl("3x^3 +4x^4");
    assertEquals(4, p1.getCoefficient(4));

    Polynomial p = new PolynomialImpl();
    assertEquals(0, p.getCoefficient(0));

  }


  /**
   * This method tests if two polynomials are equal.
   */
  @Test
  public void testEquals() {
    Polynomial p = new PolynomialImpl("2x^2 +3x^3");
    Polynomial p1 = new PolynomialImpl("2x^2 +3x^3");

    assertEquals(true, p.equals(p1));
    assertEquals(true, p1.equals(p));

    Polynomial p2 = new PolynomialImpl("2x^2 +3x^3");
    Polynomial p3 = new PolynomialImpl("2x^2");

    assertEquals(false, p2.equals(p3));
    assertEquals(false, p3.equals(p2));

    Polynomial p4 = new PolynomialImpl("");
    Polynomial p5 = new PolynomialImpl("");

    assertEquals(true, p4.equals(p5));
    assertEquals(true, p5.equals(p4));
  }

  /**
   * This method tests polynomial string.
   */
  @Test
  public void testPolynomialString() {

    Polynomial p = new PolynomialImpl("5x^2 +4x^1 -2");
    assertEquals("5x^2+4x^1-2", p.toString());

    p = new PolynomialImpl("2x^2 +0x^3");
    assertEquals("2x^2", p.toString());

    p = new PolynomialImpl("2x^2 +4x^3 +3x^5");
    assertEquals("3x^5+4x^3+2x^2", p.toString());

    p = new PolynomialImpl("");
    assertEquals("0", p.toString());

    p = new PolynomialImpl();
    p.addTerm(-30, 3);
    p.addTerm(12, 2);
    p.addTerm(3, 0);

    assertEquals("-30x^3+12x^2+3", p.toString());

    p = new PolynomialImpl();
    p.addTerm(42, 1);
    p.addTerm(12, 5);
    p.addTerm(-34, 2);
    p.addTerm(-10, 0);
    assertEquals("12x^5-34x^2+42x^1-10", p.toString());

    p = new PolynomialImpl();
    p.addTerm(32, 2);
    p.addTerm(21, 1);
    p.addTerm(-1243, 0);
    p.addTerm(21, 1);
    p.addTerm(130, 0);
    assertEquals("32x^2+42x^1-1113", p.toString());


  }


}
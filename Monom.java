
package myMath;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	
	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	public Monom(String s) {
		String [] S = s.split("x");
		if(S.length <= 1) {
			this._power = 0;
			if(S.length == 0)
				this._coefficient = 0;
			else if(S[0].charAt(0) == '-') {
					this._coefficient = -1;
					this._power = 1;
			}
			else {
			this._coefficient = Double.parseDouble(S[0]);
			}
		}
		else {
			String s2 = S[1];
			s2 = s2.substring(1);
			if(!S[0].isEmpty() && S[0].charAt(0) == '-') {
				if(S[0] == "-")
					this._coefficient = -1;
				this._power = Integer.parseInt(s2);
			}
			if(S[0].isEmpty())
				this._coefficient = 0;
			else {
				this._coefficient = Double.parseDouble(S[0]);
				this._power = Integer.parseInt(s2);
			}
		}	
	}

	
	public void add(Monom m) {
		if(m.get_power() != this._power) {
			throw new RuntimeException("can't add two different powers of monoms");
		}
		this._coefficient = m._coefficient + this._coefficient;
		}
	
	public void multipy(Monom d) {
		this._coefficient = d._coefficient*this._coefficient;
		this._power = d._power + this._power;
	}
	
	public String toString() {
		if (this._coefficient == 0)
			return 0 + "";
		if (this._power == 0)
			return this._coefficient + "";
		if(this._power == 1)
			return this._coefficient + "x";
		String ans = this._coefficient + "x" + "^" + this._power;
		return ans;
	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************
	

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	
	public static void main(String[] args) {
		Monom m = new Monom("-3.2x^2");
		String s = m.toString();
		m = new Monom(s);
		double fx = m.f(2);
		System.out.println(2+") "+m +"    \tisZero: "+m.isZero()+"\t f("+2+") = "+fx);
	}
}
	
	



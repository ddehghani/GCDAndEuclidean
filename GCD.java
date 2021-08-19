import java.util.*;

public class GCD
{
	public static void main(String[] args)
	{	
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter two numbers with a space in between: ");
		int numA = scan.nextInt(), numB = scan.nextInt();
		while(numA != 0 && numB != 0)
		{
			System.out.println(euclidean(numA,numB));
			System.out.print("Enter two numbers with a space in between: ");
			numA = scan.nextInt(); 
			numB = scan.nextInt();
		}
	}

	public static String euclidean(int numA, int numB)
	{	
		ArrayList<Equation> equList = new ArrayList<Equation>();
		int a = Math.max(numA,numB), b = Math.min(numA,numB), q, r = -1;
		String result = "";
		do
		{
			q = a/b;
			r = a - (b*q);
			Equation e = new Equation(a,b,q,r);
			equList.add(e);
			result += e + "\n";
			if (r==0)
				result += "\nThe GCD is: " + b + "\n\n";
			a = b;
			b = r;
		} while (r != 0);

		Equation currEqu = null;
		ReverseEquation currReverse = null;
		for (int i = equList.size() - 2; i >= 0; i--)
		{
			currEqu = equList.get(i);
			if (currReverse == null)
			{
				currReverse = currEqu.toReversedEquation();
				result += currReverse + "\n";
			}
			else
			{	
				ReverseEquation newRev = currEqu.toReversedEquation();
				if (currEqu.r == currReverse.a)
				{
					result += "  = (" +  currReverse.t + ")(" + newRev.toStringNoRem() + ") + (" + currReverse.s + ")(" + currReverse.b + ")";
					currReverse = new ReverseEquation(currReverse.r, currReverse.t * newRev.t, newRev.a, currReverse.s + currReverse.t * newRev.s, currReverse.b);
					result += " = " + currReverse.toStringNoRem() + "\n";
				}
				else
				{
					result += "  = (" +  currReverse.t + ")(" + currReverse.a + ") + (" + currReverse.s + ")(" + newRev.toStringNoRem() + ")";
					currReverse = new ReverseEquation(currReverse.r, currReverse.t + currReverse.s * newRev.s, currReverse.a, newRev.t * currReverse.s, newRev.a);
					result += " = " + currReverse.toStringNoRem() + "\n";
				}
			}
		}

		return result;
	}
}

class Equation
{
	public int a,b,q,r;

	public Equation(int a, int b, int q, int r)
	{
		this.a = a;
		this.b = b;
		this.q = q;
		this.r = r;
	}

	public String toString()
	{
		return a + " = " + b + " x " + q + " + " + r;
	}

	public ReverseEquation toReversedEquation()
	{
		return new ReverseEquation(r, 1, a, -1*q, b);
	}
}

class ReverseEquation
{
	public int t,a,s,b,r;

	public ReverseEquation(int  r, int t, int a, int s, int b)
	{
		this.a = a;
		this.b = b;
		this.t = t;
		this.s = s;
		this.r = r;
	}

	public String toString()
	{
		return r + " = (" +  t + ")(" + a + ") + (" + s + ")(" + b + ")";
	}

	public String toStringNoRem()
	{
		return "(" +  t + ")(" + a + ") + (" + s + ")(" + b + ")";
	}
}
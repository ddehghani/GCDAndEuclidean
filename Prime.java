import java.util.Scanner;

public class Prime
{
	public static void main(String[] args)
	{	
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter a number: ");
		int input = scan.nextInt();
		while(input != 0)
		{
			if (isPrime(input))
				System.out.println(input + " is prime!");
			else
				System.out.println(input + " is NOT prime!\n" + primeComposite(input));
			System.out.print("Enter the number: ");
			input = scan.nextInt();
		}
	}

	private static boolean isPrime (int num)
	{
		boolean isPrime = true;
		for (int i = 2; i <= (int) Math.sqrt(num); i++)
			if (num%i == 0)
				isPrime = false;
		return isPrime;
	}
	public static String primeComposite(int num)
	{	
		String result = num + " = ";
		int currPrime = 0, currPower = 1;

		while (num != 1)
		{
			for (int i = 2; i <= num; i++)
				if (num % i == 0)
				{
					if (currPrime == 0)
						currPrime = i;
					else if (currPrime == i)
						currPower++;
					else
					{
						result += currPrime + "^" + currPower + " x ";
						currPower = 1;
						currPrime = i;
					}
					num /= i;
					break;
				}
		}
		return result + currPrime + "^" + currPower;
	}
}
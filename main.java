public class Main 
{
    public static void main(java.lang.String[]args)
    {
        java.util.Scanner inputUser = new java.util.Scanner(java.lang.System.in);

        int a, b, hasil;

        char operator;

        try {

			while(true) {

				java.lang.System.out.print("nilai pertama a : ");
				a = inputUser.nextInt();

				java.lang.System.out.print("operator + x - / : ");
				operator = inputUser.next().charAt(0);

				java.lang.System.out.print("nilan kedua b : ");
				b = inputUser.nextInt();

				java.lang.System.out.println("input user: " + a + " " + operator + " " + b);

				switch(operator)
				{

					case '+':
						hasil = a + b;
						java.lang.System.out.println("hasil = " + hasil
													 );
						break;
					case 'x':
						hasil = a * b;
						java.lang.System.out.println("hasil = " + hasil
													 );
						break;
					case '-':
						hasil = a - b;
						java.lang.System.out.println("hasil = " + hasil
													 );
						break;
					case '/':
						hasil = a / b;
						java.lang.System.out.println("hasil = " + hasil
													 );
						break;
					case '%':
						float hasilf = (float) a % b;
						java.lang.System.out.println("hasil = " + hasilf
													 );
						break;
					default:
						java.lang.System.out.println("hasil [" + operator + "] tidak ada di operator, mohon masukkan operator dengan benar");

				}

			}

        } catch (java.lang.Exception e) {

			java.lang.System.out.println("anda salah memasukkan salah satu int a sama b!");
//			e.printStackTrace();
			main(null);

        }

    }

}
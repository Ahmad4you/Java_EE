package wolkenag.view;


public class IBANPruefziffer {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		long blz = 21050170;
//		long ktNr = 425;

//		long blz = 21050170;
//		long ktNr = 12345678;
//
//		
//		System.out.println("Pr�freZiffer: " + berechnePZ(blz, ktNr));

//		System.out.println(1);
//		System.out.println(2);
//		System.out.println(3);
//		System.out.println(4);
//		System.out.println(5);
//		System.out.println(6);
//		System.out.println(7);

//		for(int i= 1; i <= 7; i++) {
//			System.out.println(i);
//		}
//		
//		
//		int i= 5;
//		while( i <= 10) {
//			System.out.println(i);
//			i++;
//		}
//		
//		do {
//			System.out.println(1);
//			System.out.println(2);
//			System.out.println(3);
//		} while (i <= 7);

//		switch (i) {
//		case 1: {
//			System.out.println("i = 1");
//			break;
//		}
//		case 2: {
//			System.out.println("i = 2");
//			break;
//		}
//		case 3: {
//			System.out.println("i = 3");
//			break;
//		}
//		
//		
//		default:
//			System.out.println("i not found!!!!!!!!!");;
//		}

//		String s1 = "001880023UPDATECOUNT       10026BEZEICHNUNG       1ert0022MULTIPLIKATOR     0022RECHENVERFAHREN   0022LANGBEZE          0027CHANGEUSER        AHMAD0041CHANGEDATETIME";
//		String s3 = "001050023UPDATECOUNT       90006100029BEZEICHNUNG       6789sdf0010neubez0025MULTIPLIKATOR     9990007777   ";
//		String s2 = "000950023UPDATECOUNT       1000520026KAPITALERTRAGST   4.00000580026SOLIDARITAETZ     1.0000055      ";
		String s4 = "000930023UPDATECOUNT       8000590025MULTIPLIKATOR     85200079990023RECHENVERFAHREN   M0005G";
		
		holefeldname(s4);

	}

	public static void holefeldname(String key) throws StringIndexOutOfBoundsException {
		String[] parts = key.split("   ");
		
			for (int i = 0; i <= parts.length - 1; i++) {
				if (!parts[i].equals("")) {
					String feldname = parts[i].trim().substring(parts[i].indexOf("002") + 3);
					System.out.println(feldname  + "      " + parts[i].substring(0,parts[i].length()-feldname.length()));
//					String data = parts[i].trim().substring(parts[i].indexOf(" ") +1, parts[i].indexOf("002") -1 );
//					System.out.println( "   " + parts[i]);
					
				}
			}
		
	}

	public static long berechnePZ(long blz, long ktNr) {
		while (blz >= 97) {
			blz = blz - 97;
		}
		ktNr = ktNr * 1000000;
		ktNr = ktNr + 131400;

		while (ktNr >= 88529281) {
			ktNr = ktNr - 88529281;
		}
		while (ktNr >= 97) {
			ktNr = ktNr - 97;
		}

		long prufeziffer = blz * 62 + ktNr;
		while (prufeziffer >= 97) {
			prufeziffer = prufeziffer - 97;
		}

		prufeziffer = 98 - prufeziffer;
		return prufeziffer;
	}

}
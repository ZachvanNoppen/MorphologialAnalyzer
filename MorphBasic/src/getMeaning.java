import applications.Decompose;
import data.LinguisticDataAbstract;

public class getMeaning {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinguisticDataAbstract.init();
		//GET THE INPUT FROM THE USER
		String word = "qipaluaq";
		System.out.println("decomposeToMultilineString(\"saqqitaujuq\"):");
		String decompositions = Decompose.decomposeToMultilineString(word);
		System.out.println(decompositions);
		System.out.println();
		System.out.println("decomposeToArrayOfStrings(\"saqqitaujuq\"):");
		String decs[] = Decompose.decomposeToArrayOfStrings(word);
		for(int i=0; i<decs.length; i++)
			System.out.println(decs[i]);
		
		System.out.println();
		System.out.println();
		System.out.println();
		//String dec = "{saqqi:saqqik/1v}{ta:jaq/1vn}{u:u/1nv}{juq:juq/1vn}";
		String mngs[] = Decompose.getMeaningsInArrayOfStrings(decompositions, "en", true, true);
		for(int i=0; i<mngs.length; i++)
			System.out.println(mngs[i]);
		System.out.println();
		
	}

}

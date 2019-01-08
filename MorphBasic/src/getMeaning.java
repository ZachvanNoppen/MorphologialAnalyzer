import applications.Decompose;
import data.LinguisticDataAbstract;
import java.util.ArrayList;
import java.util.List;

public class getMeaning {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinguisticDataAbstract.init();
		//GET THE INPUT FROM THE USER
		String word = "pigiaqtitsivunga";
		//System.out.println("decomposeToMultilineString(\"qipaluaq\"):");
		//String decompositions = Decompose.decomposeToMultilineString(word);
		//System.out.println(decompositions);
		//System.out.println();
		//System.out.println("decomposeToArrayOfStrings(\"qipaluaq\"):");
		
		//String dec = "{saqqi:saqqik/1v}{ta:jaq/1vn}{u:u/1nv}{juq:juq/1vn}";
		/*
		String mngs[] = Decompose.getMeaningsInArrayOfStrings(decompositions, "en", true, true);
		for(int i=0; i<mngs.length; i++) {
			System.out.println(mngs[i]);
		}
		System.out.println();*/
		splitArray(word);
		
	}
	public static String[][] splitArray(String word) {
		
		String decs[] = Decompose.decomposeToArrayOfStrings(word);
		for(int i=0; i<decs.length; i++) {
			System.out.println(decs[i]);
		}
		//Change this to something that is not a set size
		String[][] breakdown = new String[100][20];
			
		//GETTING THE INDEXES TO GRAB THE SUBSTRINGS
		List<int[]> indArr = new ArrayList<>();
		int[] posArr = new int[] {0};
		
		for(int i = 0; i < decs.length; i++) {
			int pos = 0;
			int j = 1;
			posArr = new int[] {0};
			
			while (pos < decs[i].length()) {
				int[] tempArr = new int[posArr.length+1];
				System.arraycopy(posArr, 0, tempArr, 0, posArr.length);
				tempArr[j]= decs[i].indexOf("}",posArr[j-1]+1);
				
				pos = tempArr[j]+1;
				j++;
				posArr = tempArr;
			}
			indArr.add(posArr);
		}
		
		//SPLITTING OUT THE DIFFERENTS MORPHOLOGIES
		for(int i = 0; i < decs.length; i++) {
			//for each word
			for(int j= 0; j < indArr.get(i).length-1; j++) {
				breakdown[i][j] = decs[i].substring(indArr.get(i)[j], indArr.get(i)[j+1]);
				breakdown[i][j] = breakdown[i][j].replace("{", "");
				breakdown[i][j] = breakdown[i][j].replace("}", "");
			}
		}
		
		//PRINTING THE SPLIT WORDS
		for(int i = 0; i < decs.length; i++) {
			//for each word
			for(int j= 0; j < indArr.get(i).length-1; j++) {
				System.out.println(breakdown[i][j]);
				
			}
			//System.out.println(indArr.get(i).length);
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		//return breakdown;
		
		//SORTING THE DATA
		int tempNum = 0;
		
		for(int i = 1; i < breakdown.length; i++) {
			int valid = 0;
			
			for(int j = 0; j < breakdown[i].length; j++) {
				//Test the value in front
				System.out.println(breakdown[i-1][j]);
				int locP = breakdown[i-1][j].indexOf(":");
				int locS = breakdown[i][j].indexOf(":");
				
				if(breakdown[i-1][j].substring(0,locP).equals(breakdown[i][j].substring(0,locS))) {
					//The morph in front of the column is the same and should be same list
					valid++;
				}
			}
			
			//Testing if all of the pre-colon morphs match
			if(valid == breakdown[i].length) {
				//This means all will be in the same column
				tempNum++;
			}
		}
		System.out.println(tempNum);
		
		
		return breakdown;
	}

}

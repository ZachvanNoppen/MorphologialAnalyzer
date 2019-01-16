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
	public static void splitArray(String word) {
		
		String decs[] = Decompose.decomposeToArrayOfStrings(word);
		for(int i=0; i<decs.length; i++) {
			System.out.println(decs[i]);
		}
		
		//GETTING THE INDEXES TO GRAB THE SUBSTRINGS
		List<int[]> indArr = new ArrayList<>();
		List<String[]> breakDown = new ArrayList<>();
		
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
			//Putting each set of words into an array
			String[] splitWord = new String[decs.length+1];
			for(int j= 0; j < indArr.get(i).length-1; j++) {
				
				splitWord[j] = decs[i].substring(indArr.get(i)[j], indArr.get(i)[j+1]);
				splitWord[j] = splitWord[j].replace("{", "");
				splitWord[j] = splitWord[j].replace("}", "");
			}
			//Making a 2D array of the words
			breakDown.add(splitWord);
		}
		
		//PRINTING THE SPLIT WORDS
		for(int i = 0; i < decs.length; i++) {
			//for each word
			for(int j= 0; j < indArr.get(i).length-1; j++) {
				System.out.println(breakDown.get(i)[j]);
			}
			//System.out.println(indArr.get(i).length);
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		//return breakdown;
		
		//SORTING THE DATA
		//What is happening right now is that you are only looking for x and x+1
		//SHould be x  vs x+1, x vs x+2 ..... x vsx+n
		//Think searching algorithm
		int tempNum = 0;
		System.out.println("HI");
		System.out.println(decs.length);
		System.out.println(breakDown.get(0).length);
		
		for(int i = 1; i < breakDown.size(); i++) {
			int valid = 0;
			
			for(int j = 0; j < breakDown.get(0).length; j++) {
				//Test the value in front
				//System.out.println(breakDown.get(i-1)[j]);
				int locP = breakDown.get(i-1)[j].indexOf(":");
				int locS = breakDown.get(i-1)[j].indexOf(":");
				System.out.println(breakDown.get(i-1)[j].substring(0,locP));
				System.out.println(breakDown.get(i)[j].substring(0,locP));
				if(breakDown.get(i-1)[j].substring(0,locP).equals(breakDown.get(i)[j].substring(0,locS))) {
					//The morph in front of the column is the same and should be same list
					valid++;
					
				}
			}
			
			//Testing if all of the pre-colon morphs match
			if(valid == breakDown.get(i).length) {
				//This means all will be in the same column
				tempNum++;
			}
		}
		System.out.println(tempNum);
		
		
		//return breakDown;
	}

}

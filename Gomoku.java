package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DuplicateChar {
	public void find(String st) {
		Map <Character, Integer> baseMap = new HashMap<Character, Integer>();
		char[] charArray = st.toCharArray();
		for(Character ch : charArray) {
			if (baseMap.containsKey(ch)) {
				baseMap.put(ch, baseMap.get(ch)+1);
			}else {
				baseMap.put(ch, 1);
			}
		}
		Set<Character> keys = baseMap.keySet();
		for(Character key : keys) {
			if (baseMap.get(key)>1){
				System.out.println(key+"is"+baseMap.get(key));
			}
		}
	}
	
	public static void main(String[] args) {
		String st = "asdasdaffffafasfasd";
		DuplicateChar dp = new DuplicateChar();
		dp.find(st);
		
	}

}

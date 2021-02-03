
public class test {
	public static void main(String[] args) {
		String utter="sf 검색해줘";
		if(utter.contains("찾아")) {
        	utter=utter.substring(0,utter.indexOf("찾아"));
        }
        if(utter.contains("검색")) {
        	utter=utter.substring(0,utter.indexOf("검색"));
        }
        System.out.println(utter);
	}

}

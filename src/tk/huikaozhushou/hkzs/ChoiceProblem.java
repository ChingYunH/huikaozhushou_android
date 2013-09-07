package tk.huikaozhushou.hkzs;

public class ChoiceProblem extends Problem {
	private String[] Choice = new String[5] ;

	public void setOption(String Txt,int ChoiceId) {
		Choice[ChoiceId] = new String(Txt);		
	}
	public String getOption(int optionID) {
		return Choice[optionID];
	}
	
	public String getStringAns() {
		
		switch(rightAns) {
		case 0:
			return "A";
		case 1:
			return "B";
		case 2:
			return "C";
		case 3:
			return "D";
		default :
			return "error";
		}
		
	}
	
	public String getLongString() {
		return Description +
		"\n\n" + Choice[0] +
		"\n" + Choice[1] +
		"\n" + Choice[2] +
		"\n" + Choice[3] +
		"\n" ;
	}
}

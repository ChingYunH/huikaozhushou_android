package tk.huikaozhushou.hkzs;

public class MyData {
//	public static final int TFproblemArraySize = 200;
//	public static final int ChoiceproblemLine = 2038;
//	public static final int TFproblemLine = 345;
//	public static TFProblem[] TFproblem = new TFProblem[TFproblemArraySize];
	
	public static final int ChoiceproblemArraySize = 500, ReviewArraySize = 5000;
	public static final int SubjectNum = 4, SubjectRNum = 3;
	public static ChoiceProblem[][] Choiceproblem = new ChoiceProblem[SubjectNum][ChoiceproblemArraySize];
	public static int TFNum = 0; 
	public static int subject = 0; public static int subjectR = 0;
	public static int[] ChoiceNum = new int[SubjectNum]; //0 - base
	public static ReviewMaterial[][] Review = new ReviewMaterial[SubjectNum][ReviewArraySize]; 
	public static int[] ReviewNum = new int[SubjectRNum];
	public static int biologyreview = 2, historyreview = 0, chemistryreview = 1;
	public static int biologychoice = 0, historychoice = 1, infomaticschoice = 2, geographychoice=3;
	public static boolean dataLoaded = false ;
}

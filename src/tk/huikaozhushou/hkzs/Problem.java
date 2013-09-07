package tk.huikaozhushou.hkzs;

public abstract class Problem {
	protected String Description = null;
	protected int rightAns = -1;
	public void setDescription(String description) {
		Description = description;
	}

	public String getDescription() {
		return Description;
	}
	public void setRightAns(int newAns) {
		rightAns = newAns;
	}
	public boolean judge(int yourAns) {
		if(yourAns == rightAns) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getAns() {
		return rightAns;
	}
}

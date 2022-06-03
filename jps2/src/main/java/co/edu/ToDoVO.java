package co.edu;

public class ToDoVO {
	private int no;
	private String title;
	private int checked;
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
	@Override
	public String toString() {
		return "ToDoVO [no=" + no + ", title=" + title + ", checked=" + checked + "]";
	}

	
	
	
}

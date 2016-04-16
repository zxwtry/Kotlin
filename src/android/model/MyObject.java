package android.model;

import java.io.Serializable;

public class MyObject implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private boolean sex_isMale;
	enum english_skills {
		READ, WRITE, LISTEN
	}
	enum program_skills {
		JAVA, C, CPP, Python, Ruby, ASM, Basic
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSex_isMale() {
		return sex_isMale;
	}
	public void setSex_isMale(boolean sex_isMale) {
		this.sex_isMale = sex_isMale;
	}
	public MyObject(int id, String name, boolean sex_isMale) {
		super();
		this.id = id;
		this.name = name;
		this.sex_isMale = sex_isMale;
	}
	public MyObject() {
		
	}
	public MyObject(int id, String name) {
		this.id = id;
		this.name = name;
	}
}

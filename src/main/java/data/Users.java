package data;

//pojo - plain old java object
public class Users{
	
	String name;
	String job;
	String id;
	String createdAt;
	
	public Users() {
		
	}
	
	public Users(String name, String job) {
		this.name=name;									//Use of this keyword for global and local
		this.job=job;									//Telusko java video
		this.id=id;
		this.createdAt=createdAt;
	}
	
	
	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
	
}

public abstract class Menu {
	String title;
	Command command;
	
	public Menu() {}
	
	public Menu(String title, Command command) {
		super();
		this.title = title;
		this.command = command;
	}
	
	public String getTitle() {
		return title;
	}
	
	public abstract void execute() throws Exception;
}

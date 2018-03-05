
public class MenuItem extends Menu {

	public MenuItem() {}

	public MenuItem(String title, Command command) {
		super(title, command);
	}

	@Override
	public void execute() throws Exception {
		if(command!=null) {
			command.execute();
		}

	}

}

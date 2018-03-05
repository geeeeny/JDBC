
public class Application {
	MenuBar mainMenu;
	
	public Application() {}
	
	public void init() {
		createMenu();
	}
	
	public void createMenu() {
		/*	
		//�޴� ����
		mainMenu = new MenuBar();
		
		//file �޴��׷�
		MenuGroup fileMenu = new MenuGroup("File");
		fileMenu.add(new MenuItem("Open", service::open)); //���ٽ�(�Լ��� ������ �����ϴ� ��, ���������δ� �͸� ���� ��ü ����)
		fileMenu.add(new MenuItem("Save", service::save));
		fileMenu.add(new MenuItem("Exit", service::exit));
		mainMenu.add(fileMenu);

		//edit �޴��׷�
		MenuGroup editMenu = new MenuGroup("Edit");
		editMenu.add(new MenuItem("Copy", this::copy));
		editMenu.add(new MenuItem("Paste", this::paste));
		editMenu.add(new MenuItem("Cut", new Command() { //�͸� ���� ��ü�� ����
			
			@Override
			public void execute() throws Exception {
				System.out.println("�߶󳻱�");
			}
		}));
		
		mainMenu.add(editMenu);
		mainMenu.add(new MenuItem("Help", this::help));
		mainMenu.add(new MenuItem("About", this::about));
		*/
	}
	
	public void run() {
		while(true) {
			try {
				mainMenu.execute();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void exit() {
		System.out.println("�����մϴ�.");
		System.exit(0);
	}
	/*	
	//�޴� �ڵ鷯 �޼��� ����
	public void about() {
		System.out.println("�޴� ���� ���ø����̼�");
	}
	
	public void help() {
		System.out.println("�����Դϴ�.");
	}
	
	public void copy() {
		System.out.println("����");
	}
	
	public void paste() {
		System.out.println("�ٿ��ֱ�");
	}
	*/
/*	public void cut() {
		System.out.println("�߶󳻱�");
	}*/
}

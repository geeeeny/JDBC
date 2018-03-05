
public class MenuTestApp extends Application {
	FileService service;

	public MenuTestApp() {
		service = new FileService();
	}

	@Override
	public void createMenu() {
		// �޴� ����
		mainMenu = new MenuBar();

		// file �޴��׷�
		MenuGroup fileMenu = new MenuGroup("File");
		fileMenu.add(new MenuItem("Open", service::open)); // ���ٽ�(�Լ��� ������ �����ϴ� ��, ���������δ� �͸� ���� ��ü ����)
		fileMenu.add(new MenuItem("Save", service::save));
		fileMenu.add(new MenuItem("Exit", this::exit));
		mainMenu.add(fileMenu);

		// edit �޴��׷�
		MenuGroup editMenu = new MenuGroup("Edit");
		editMenu.add(new MenuItem("Copy", this::copy));
		editMenu.add(new MenuItem("Paste", this::paste));
		editMenu.add(new MenuItem("Cut", new Command() { // �͸� ���� ��ü�� ����

			@Override
			public void execute() throws Exception {
				System.out.println("�߶󳻱�");
			}
		}));

		mainMenu.add(editMenu);
		mainMenu.add(new MenuItem("Help", this::help));
		mainMenu.add(new MenuItem("About", this::about));
	}

	// �޴� �ڵ鷯 �޼��� ����
	public void about() {
		System.out.println("�޴� ���� ���ø����̼�");
		System.out.println("Version 1.0");
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

	@Override
	public void exit() {
		View view = View.getInstance();
		String response = view.getString("���� �����Ͻðڽ��ϱ�? �����Ϸ��� y, �������� �������� n�� �Է�\n>");
		if(response.equals("y")) {
			super.exit();
		}
	}
	
	
}

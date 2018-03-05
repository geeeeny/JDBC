
public class MenuTestApp extends Application {
	FileService service;

	public MenuTestApp() {
		service = new FileService();
	}

	@Override
	public void createMenu() {
		// 메뉴 생성
		mainMenu = new MenuBar();

		// file 메뉴그룹
		MenuGroup fileMenu = new MenuGroup("File");
		fileMenu.add(new MenuItem("Open", service::open)); // 람다식(함수의 참조를 전달하는 것, 내부적으로는 익명 구현 객체 생성)
		fileMenu.add(new MenuItem("Save", service::save));
		fileMenu.add(new MenuItem("Exit", this::exit));
		mainMenu.add(fileMenu);

		// edit 메뉴그룹
		MenuGroup editMenu = new MenuGroup("Edit");
		editMenu.add(new MenuItem("Copy", this::copy));
		editMenu.add(new MenuItem("Paste", this::paste));
		editMenu.add(new MenuItem("Cut", new Command() { // 익명 구현 객체를 전달

			@Override
			public void execute() throws Exception {
				System.out.println("잘라내기");
			}
		}));

		mainMenu.add(editMenu);
		mainMenu.add(new MenuItem("Help", this::help));
		mainMenu.add(new MenuItem("About", this::about));
	}

	// 메뉴 핸들러 메서드 정의
	public void about() {
		System.out.println("메뉴 연습 애플리케이션");
		System.out.println("Version 1.0");
	}

	public void help() {
		System.out.println("도움말입니다.");
	}

	public void copy() {
		System.out.println("복사");
	}

	public void paste() {
		System.out.println("붙여넣기");
	}

	@Override
	public void exit() {
		View view = View.getInstance();
		String response = view.getString("정말 종료하시겠습니까? 종료하려면 y, 종료하지 않으려면 n을 입력\n>");
		if(response.equals("y")) {
			super.exit();
		}
	}
	
	
}


public class Application {
	MenuBar mainMenu;
	
	public Application() {}
	
	public void init() {
		createMenu();
	}
	
	public void createMenu() {
		/*	
		//메뉴 생성
		mainMenu = new MenuBar();
		
		//file 메뉴그룹
		MenuGroup fileMenu = new MenuGroup("File");
		fileMenu.add(new MenuItem("Open", service::open)); //람다식(함수의 참조를 전달하는 것, 내부적으로는 익명 구현 객체 생성)
		fileMenu.add(new MenuItem("Save", service::save));
		fileMenu.add(new MenuItem("Exit", service::exit));
		mainMenu.add(fileMenu);

		//edit 메뉴그룹
		MenuGroup editMenu = new MenuGroup("Edit");
		editMenu.add(new MenuItem("Copy", this::copy));
		editMenu.add(new MenuItem("Paste", this::paste));
		editMenu.add(new MenuItem("Cut", new Command() { //익명 구현 객체를 전달
			
			@Override
			public void execute() throws Exception {
				System.out.println("잘라내기");
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
		System.out.println("종료합니다.");
		System.exit(0);
	}
	/*	
	//메뉴 핸들러 메서드 정의
	public void about() {
		System.out.println("메뉴 연습 애플리케이션");
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
	*/
/*	public void cut() {
		System.out.println("잘라내기");
	}*/
}

import java.util.ArrayList;
import java.util.List;

public class MenuBar {
	List<Menu> menuList;
	
	public MenuBar() {
		menuList = new ArrayList<>();
	}

	public void add(Menu menu) {
		menuList.add(menu);
	}

	public void printMenu() {
		for(int i=0; i<menuList.size(); i++) {
			Menu menu = menuList.get(i);
			System.out.printf("%d) %s ", i, menu.getTitle());
		}
		System.out.println();
	}
	
	public void execute() throws Exception {
		printMenu();
		View view = View.getInstance();
		int ix = view.getInt("선택 : ");
		if(ix>=0 && ix<menuList.size()) {
			menuList.get(ix).execute();
		}else {
			System.out.println("잘못된 메뉴 선택입니다.");
		}
	}
}

import java.util.ArrayList;
import java.util.List;

public class MenuGroup extends Menu {
	List<Menu> menuList;
	
	
	public MenuGroup() {
		this(null);	//�ڱ� �ڽ��� �ٸ� �����ڸ� ȣ��(�Ű������� null�� ����)
	}


	public MenuGroup(String title) {
		super(title, null);
		menuList = new ArrayList<>();
	}

	public void add(Menu menu) {
		menuList.add(menu);
	}

	public void printMenu() {
		System.out.printf("[%s] ", title);
		for(int i=0; i<menuList.size(); i++) {
			Menu menu = menuList.get(i);
			System.out.printf("%d) %s ", i, menu.getTitle());
		}
		
		// ����޴� ������ �޴�
		System.out.printf("%d) ������", menuList.size());
		System.out.println();
	}
	@Override
	public void execute() throws Exception {
		View view = View.getInstance();
		while(true) {
			printMenu();
			int ix = view.getInt("���� : ");
			if(ix>=0 && ix<menuList.size()) {
				menuList.get(ix).execute();
			}else if(ix == menuList.size()) {	// ����޴� ������
				break;
			}else {
				System.out.println("�߸��� �޴� �����Դϴ�.");
			}
		}
	}

}

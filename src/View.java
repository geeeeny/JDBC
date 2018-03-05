import java.util.List;
import java.util.Scanner;

public class View {
	Scanner scanner;

	// �̱���(Singletone) ����
	private View() {
		scanner = new Scanner(System.in);
	}

	private static View view = new View();

	public static View getInstance() {
		return view;
	}
	
	public String getString(String msg) {
		System.out.print(msg);
		String line = scanner.nextLine();
		return line;
	}
	
	public int getInt(String msg) {
		System.out.print(msg);
		int input = scanner.nextInt();
		scanner.nextLine(); //���� ���� ����
		return input;
	}
	
	/*�߰��� GoodInfo�� �����Ͽ� ����*/
	public GoodInfo getGoodInfo() {
		String code = getString("��ǰ�ڵ� :");
		String name = getString("��ǰ�� :");
		int price = getInt("���� :");
		String maker = getString("������ :");
		
		 return new GoodInfo(code, name, price, maker);
	}
	
	/*������ GoodInfo�� �޾� ������ GoodInfo ����*/
	public GoodInfo getGoodInfo(GoodInfo g) {
		String prompt;
		System.out.printf("��ǰ�ڵ�[%s] ����%n", g.getCode());
		
		prompt = String.format("��ǰ��[%s] : ", g.getName());
		String name = getString(prompt);
		if(name.isEmpty()) { //name ���� ����(�׳� enter���� ��)
			name = g.getName(); //������ ���� �־���
		}
		
		prompt = String.format("����[%d] : ", g.getPrice());
		int price = getInt(prompt);
		if(price == -1) { //price ���� ����(�׳� enter���� ��)
			price = g.getPrice();
		}
		
		prompt = String.format("������[%s] : ", g.getMaker());
		String maker = getString(prompt);
		if(maker.isEmpty()) { //maker ���� ����(�׳� enter���� ��)
			maker = g.getMaker();
		}
		
		return new GoodInfo(g.getCode(), name, price, maker);
	}
	
	public void printList(List<GoodInfo> list) {
		System.out.println("��ǰ�ڵ� ��ǰ�� \t\t���� ������");
		System.out.println("----------------------------------------------");
		for(GoodInfo g:list) {
			System.out.printf("%8s %s \t%12d %s%n",
							g.getCode(), g.getName(), g.getPrice(), g.getMaker());
		}
		System.out.println("----------------------------------------------");
	}
	
	public void printPage(List<GoodInfo> list, int start, int page, int totalPage, int total) {
		System.out.println("��ǰ�ڵ� ��ǰ�� \t\t���� ������");
		System.out.println("----------------------------------------------");
		for(GoodInfo g:list) {
			System.out.printf("%d) %8s %s \t%12d %s%n",
							start++ ,g.getCode(), g.getName(), g.getPrice(), g.getMaker());
		}
		System.out.println("----------------------------------------------");
		System.out.printf("%d/%d(�� %d��)\n", page, totalPage, total);
	}
}

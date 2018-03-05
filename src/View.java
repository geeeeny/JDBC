import java.util.List;
import java.util.Scanner;

public class View {
	Scanner scanner;

	// 싱글톤(Singletone) 패턴
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
		scanner.nextLine(); //개행 문자 제거
		return input;
	}
	
	/*추가할 GoodInfo를 생성하여 리턴*/
	public GoodInfo getGoodInfo() {
		String code = getString("상품코드 :");
		String name = getString("상품명 :");
		int price = getInt("가격 :");
		String maker = getString("제조사 :");
		
		 return new GoodInfo(code, name, price, maker);
	}
	
	/*수정할 GoodInfo를 받아 수정된 GoodInfo 리턴*/
	public GoodInfo getGoodInfo(GoodInfo g) {
		String prompt;
		System.out.printf("제품코드[%s] 수정%n", g.getCode());
		
		prompt = String.format("제품명[%s] : ", g.getName());
		String name = getString(prompt);
		if(name.isEmpty()) { //name 수정 없음(그냥 enter쳤을 때)
			name = g.getName(); //기존의 값을 넣어줌
		}
		
		prompt = String.format("가격[%d] : ", g.getPrice());
		int price = getInt(prompt);
		if(price == -1) { //price 수정 없음(그냥 enter쳤을 때)
			price = g.getPrice();
		}
		
		prompt = String.format("제조사[%s] : ", g.getMaker());
		String maker = getString(prompt);
		if(maker.isEmpty()) { //maker 수정 없음(그냥 enter쳤을 때)
			maker = g.getMaker();
		}
		
		return new GoodInfo(g.getCode(), name, price, maker);
	}
	
	public void printList(List<GoodInfo> list) {
		System.out.println("상품코드 상품명 \t\t가격 제조사");
		System.out.println("----------------------------------------------");
		for(GoodInfo g:list) {
			System.out.printf("%8s %s \t%12d %s%n",
							g.getCode(), g.getName(), g.getPrice(), g.getMaker());
		}
		System.out.println("----------------------------------------------");
	}
	
	public void printPage(List<GoodInfo> list, int start, int page, int totalPage, int total) {
		System.out.println("상품코드 상품명 \t\t가격 제조사");
		System.out.println("----------------------------------------------");
		for(GoodInfo g:list) {
			System.out.printf("%d) %8s %s \t%12d %s%n",
							start++ ,g.getCode(), g.getName(), g.getPrice(), g.getMaker());
		}
		System.out.println("----------------------------------------------");
		System.out.printf("%d/%d(총 %d건)\n", page, totalPage, total);
	}
}

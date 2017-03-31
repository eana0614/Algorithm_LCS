import java.util.Scanner;

public class LCS {
	
	public static void main(String[] args) {
		
		String seq1 = "";
		String seq2 = "";
		int mode = 0;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("!] 번호를 입력하세요.");
		System.out.println("1. LCS 시작 \t 2. 종료");
		
		mode = scanner.nextInt();
		scanner.nextLine();
		
		while(mode == 1){
			
			System.out.println("!] LCS를 시작합니다.");
			System.out.println("!] 첫번째 문장 입력 ");
			
			seq1 = scanner.nextLine();
			
			System.out.println("!] 두번째 문장 입력 : ");
			
			seq2 = scanner.nextLine();
			
			int[][] c = initArray(seq1.length()+1, seq2.length()+1);
			
			c = lcsLength(seq1, seq2, c);
			
			
			System.out.println("===== RESULT =====");
			printLcs(c, seq1, seq1.length(), seq2.length());
			System.out.println("\n==================");
			
			System.out.println("!] 번호를 입력하세요.");
			System.out.println("1. LCS 시작 \t 2. 종료");
			
			mode = scanner.nextInt();
		}
		
		System.out.println("!] LCS 종료");

	}
	
	private static void printLcs(int[][] c, String x, int i, int j){
		if(i == 0 || j == 0){
			return ;
		}
		
		if(c[i][j]-1 == c[i-1][j-1] && c[i][j]-1== c[i][j-1] && c[i][j]-1 == c[i-1][j]){
			printLcs(c, x, i-1, j-1);
			System.out.print(x.charAt(i-1));
		}else if(c[i][j] == c[i-1][j]){
			printLcs(c, x, i-1, j);
		}else{
			printLcs(c, x, i, j-1);
		}
	}
	
	private static int[][] lcsLength(String x, String y, int[][] c){
		int m = x.length();
		int n = y.length();
		
		for(int i=1 ; i<m+1; i++){
			for(int j=1; j<n+1; j++){
				if(x.charAt(i-1) == y.charAt(j-1)){
					c[i][j] = c[i-1][j] +1;
				}else if(c[i-1][j] >= c[i][j-1]){
					c[i][j] = c[i-1][j];
				}else{
					c[i][j] = c[i][j-1];
				}
			}
		}
		
		return c;
		
	}
	
	private static int[][] initArray(int x, int y){
		int[][] result = new int[x][y];
		
		for(int i=0; i<x; i++){
			for(int j=0; j<y; j++){
				if(i == 0 || j == 0){
					result[i][j] = 0;
				}else{
					result[i][j] = -1;
				}
			}
		}
		
		return result;
	}

}

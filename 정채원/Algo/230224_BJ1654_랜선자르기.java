import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static List<Long> bars;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		bars = new ArrayList<>();
		for(int i=0; i<K; i++) {
			bars.add(Long.parseLong(br.readLine()));
		}
		bars.sort((a, b) -> (int)(b-a)); // 내림차순 정렬 // index 0 최댓값 가져오려고
		
		// 이분탐색
		long s = 0, e = bars.get(0);
		while(s < e) {
			long m = (s + e +1) >> 1;
			if(bin(m, N)) s = m;
			else e = m-1;
		}
		System.out.println(s);
	}
	
	// 길이가 m인 랜선을 잘랐을 때, N개 이상이 나오나?
	static boolean bin(long m, int N) {
		int sum = 0;
		for(long bar : bars) {
			sum += bar / m;
		}
		return sum >= N;
	}
}
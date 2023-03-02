class Solution {
    static long MAX = 1_000_000_000L * 1_000_000L; // 대충 어림잡아 계산한 MAX값
    public long solution(int n, int[] times) {
        long s = 0, e = MAX;
        while(s < e){
            long mid = (s + e ) >> 1;
            if(!f(n, times, mid)) s = mid + 1; // 심사하지 못하면 -> 시간을 더 늘림
            else e = mid; // 심사 성공하면 -> 시간을 더 줄임
        }
        
        long answer = s;
        return answer;
    }
    public boolean f(int n, int[] times, long time){
        // time 이하일 때, 각 심사대가 인원을 최대로 맡을 수 있는 수: time / times[i]
        // (이걸 모두 더한 수 >= n) 이면 n명을 time시간 이내에 모두 검사할 수 있다는 것
        long sum = 0;
        for(int i=0; i<times.length; i++){
            sum += time / times[i];
        }
        return sum >= n ? true : false;
    }
}
import java.util.*;

/**
- (전체학생수 - 체육복 없는 사람)으로 빼갈거임
- reserve, lost 한명 가리킴 (둘 다 오름차순 정렬)
- 자기자신한테 빌려주는 개념 못함... => 중복되는 거 미리 빼야 한다.

- lost == reserve-1 또는 reserve 또는 reserve+1 => 빌려줌

- (lost < reserve-1) => lost[idx]는 체육복 못 빌림 => 전체학생수-- && lost[idx++]
- reserve[idx]가 빌려줬거나, (lost > reserve[idx]+1) 인 경우 => reserve[idx++]

- reserve가 끝까지 도달하면 => 남은 lost만큼 전체학생수--
- lost가 끝까지 도달하면 => 끝
*/
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        // 오름차순 정렬
        Arrays.sort(lost);
        Arrays.sort(reserve);
        // 중복되는 숫자 빼줌
        int sameCnt = 0;
        for(int i=0, r=0; i<lost.length && r<reserve.length;){
            if(reserve[r] < lost[i]) r++;
            else if(reserve[r] > lost[i]) i++;
            else {
                // dummy로 채움
                reserve[r++] = -2; 
                lost[i++] = -5;
                sameCnt++;
            }
        }
        Arrays.sort(reserve); // dummy값이 앞으로 가도록 수정
        Arrays.sort(lost);
        
        // index 각각 하나씩 늘려나가며 그리디 탐색
        int lIdx = sameCnt;
        int rIdx = sameCnt;
        while(lIdx < lost.length && rIdx < reserve.length){
            int rnum = reserve[rIdx];
            int lnum = lost[lIdx];
            
            // 빌려줄 수 있는지 확인
            if(lnum == rnum || lnum == rnum-1 || lnum == rnum+1){
                lIdx++;
                rIdx++;
                continue;
            }
            
            // 빌려줄 수 없다면
            if(lnum < rnum-1){ // lost < reserve-1 이면 lost를 증가
                lIdx++;
                n--; // 못 빌린 사람--
            }
            else{ // lost > reserve+1 이면 reserve를 증가
                rIdx++;
            }
        }
        if(lIdx < lost.length){
            n -= (lost.length - lIdx);
        }
        
        return n;
    }
}
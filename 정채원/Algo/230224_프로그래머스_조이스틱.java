import java.util.*;

/**
- 위아래 움직이는 횟수 더함 : min(char - 'A', 'Z' - char + 1)

- 옆으로 움직이는 횟수 최솟값 구하기
    아래 경우 중 최솟값 구하기
    1. 왼쪽으로 all
    2. 오른쪽으로 all
    3. 왼쪽 -> 오른쪽 : 왼쪽*2 + 오른쪽
    4. 오른쪽 -> 왼쪽 : 왼쪽 + 오른쪽*2
*/
class Solution {
    public int solution(String name) {
        int moveCnt = 0;
        for(int i=0; i<name.length(); i++){
            if(name.charAt(i) > 'A'){
                moveCnt += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            }
        }
        
        // 1. 왼쪽으로 all : ex) J A A Z C => J > C > Z 까지 가고 A A까지 안가도 됨.
        int aCnt = 0;
        for(int i=1; i<name.length(); i++){ 
            if(name.charAt(i) == 'A') aCnt++;
            else break;
        }
        int lMove = name.length()-1 - aCnt;
        
        // 2. 오른쪽으로 all : ex ) J Z C A A => J > C > Z 까지 가고 A A까지 안가도 됨.
        aCnt = 0;
        for(int i=name.length()-1; i>0; i--){
            if(name.charAt(i) == 'A') aCnt++;
            else break;
        }
        int rMove = name.length() - 1 - aCnt;
        
        // 3. 왼쪽으로 갔다가 오른쪽으로 이동 : ex ) J C A A Z P => P > Z > (오른쪽) P > J > C, A A까지 안가도 됨
        int ltorMove = 1000;
        // 4. 오른쪽으로 갔다가 왼쪽으로 이동
        int rtolMove = 1000;
        for(int piv=1; piv<name.length()-1; piv++){
            // 3의 경우, 
						// piv 이후까지 왼쪽으로 이동, 되돌아가서 piv까지 오른쪽으로 이동: ex) piv=1이면, 앞 예시처럼 움직임
            // piv이후에 연속된 A는 왼쪽으로 안가도 됨
            aCnt = 0;
            for(int a=piv+1; a<name.length(); a++){
                if(name.charAt(a) == 'A') aCnt++;
                else break;
            }
						// 3
            int left = (name.length()-1 -piv -aCnt) << 1;
            ltorMove = Math.min(left + piv, ltorMove);

            // 4
            int right = piv << 1;
            rtolMove = Math.min(right + (name.length()-1-piv-aCnt), rtolMove);
        }
				
				// 위아래 + 옆으로 움직이는 횟수
        moveCnt += Math.min(Math.min(lMove, rMove), Math.min(ltorMove, rtolMove));
        return moveCnt;
    }
}
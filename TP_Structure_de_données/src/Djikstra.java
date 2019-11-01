import java.util.*;

public class Djikstra {
    static HashMap<Character, Integer[]> map = new HashMap<Character,Integer[]>();
    static Character[] index = {'A','B','C','D','E','F','G','H','I','J'};
    static ArrayDeque<Character> queue = new ArrayDeque<Character>();
    static ArrayDeque<Character> seen = new ArrayDeque<>();
    static TreeMap<Character,ArrayDeque<Character>> shorterPath = new TreeMap<Character,ArrayDeque<Character>>();
    static TreeMap<Character,Integer> distance = new TreeMap<Character,Integer>();

    public static void main(String[] args){
        gen_map();
        queue.addFirst('A');
        while (queue.size()!=0){
            djikstra(queue);
        }
        System.out.println(shorterPath.get('J'));
    }

    static void gen_map(){
        map.put('A', new Integer[]{-1,85,217,-1,173,-1,-1,-1,-1,-1});
        map.put('B', new Integer[]{85,-1,-1,-1,-1,80,-1,-1,-1,-1});
        map.put('C', new Integer[]{217,-1,-1,-1,-1,-1,186,103,-1,-1});
        map.put('D', new Integer[]{-1,-1,-1,-1,-1,-1,-1,183,-1,-1});
        map.put('E', new Integer[]{173,-1,-1,-1,-1,-1,-1,-1,-1,502});
        map.put('F', new Integer[]{-1,80,-1,-1,-1,-1,-1,-1,250,-1});
        map.put('G', new Integer[]{-1,-1,186,-1,-1,-1,-1,-1,-1,-1});
        map.put('H', new Integer[]{-1,-1,103,183,-1,-1,-1,-1,-1,167});
        map.put('I', new Integer[]{-1,-1,-1,-1,-1,250,-1,-1,-1,84});
        map.put('J', new Integer[]{-1,-1,-1,-1,502,-1,-1,167,84,-1});
        shorterPath.put('A', new ArrayDeque<Character>(Collections.singletonList('A')));
        shorterPath.put('B',new ArrayDeque<Character>());
        shorterPath.put('C',new ArrayDeque<Character>());
        shorterPath.put('D',new ArrayDeque<Character>());
        shorterPath.put('E',new ArrayDeque<Character>());
        shorterPath.put('F',new ArrayDeque<Character>());
        shorterPath.put('G',new ArrayDeque<Character>());
        shorterPath.put('H',new ArrayDeque<Character>());
        shorterPath.put('I',new ArrayDeque<Character>());
        shorterPath.put('J',new ArrayDeque<Character>());
        distance.put('A',0);
        distance.put('B', Integer.MAX_VALUE);
        distance.put('C', Integer.MAX_VALUE);
        distance.put('D', Integer.MAX_VALUE);
        distance.put('E', Integer.MAX_VALUE);
        distance.put('F', Integer.MAX_VALUE);
        distance.put('G', Integer.MAX_VALUE);
        distance.put('H', Integer.MAX_VALUE);
        distance.put('I', Integer.MAX_VALUE);
        distance.put('J', Integer.MAX_VALUE);
    }

    static int getIndex (Character x){
        for (int i = 0; true; i++){
            if (index[i]== x) {
                return i;
            }
        }
    }

    static ArrayDeque<Character> findVoisins (Character node){
        Integer[] dist = map.get(node);
        ArrayDeque<Character> voisins = new ArrayDeque<>();
        for(int i = 0; i< 10;i ++){
            if (dist[i]>0) {
                voisins.addFirst(index[i]);
            }
        }
        return voisins;
    }

    static Integer findDistance (Character nodeA, Character nodeB){
        int index = getIndex(nodeB);
        return map.get(nodeA)[index];
    }

    static void tri () {
        Character[] triage = new Character[queue.size()];
        for (int i = 0; i < triage.length; i++) {
            triage[i] = queue.removeFirst();
        }
        for (int i = 0; i <triage.length-1;i++) {
            int indiceMin = i;
            for (int j = i; j < triage.length; j++) {
                if (distance.get(triage[j]) < distance.get(triage[indiceMin])) {
                    indiceMin = j;
                }
            }
            Character swap = triage[i];
            triage[i] = triage[indiceMin];
            triage[indiceMin] = swap;
        }
        for (int i=0; i<triage.length;i++){
            queue.addLast(triage[i]);
        }
    }

    static void djikstra (ArrayDeque<Character> queue) {
        Character node = queue.removeFirst();
        ArrayDeque<Character> voisins = findVoisins(node);
        seen.addFirst(node);
        for (Character voisin: voisins){
            Integer dist = findDistance(node, voisin);
            if (distance.get(voisin)>distance.get(node)+dist){
                distance.put(voisin, distance.get(node)+dist);
                ArrayDeque<Character> newPath = shorterPath.get(node).clone();
                newPath.addLast(voisin);
                shorterPath.put(voisin, newPath);
            }
            if (!seen.contains(voisin) && !queue.contains(voisin)){
                queue.addFirst(voisin);
            }
        }
        tri();
    }
}


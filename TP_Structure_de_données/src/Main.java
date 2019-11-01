import java.util.*;


public class Main {
    static char [] [] plateau = new char[8][8];
    static int [] position = new int[2];
    static char direction ='E';
    static TreeMap<Integer, Integer[]> map = new TreeMap<Integer, Integer[]>();
    static Scanner scanner = new Scanner(System.in);

    static void initialisation() {
        for(int i=0; i<8;i++){
            for(int j=0; j<8;j++){
                plateau[i][j] = ' ';
            }
        }
        position = new int[]{7, 0};
    }

    static ArrayDeque<String> creationFile(){
        ArrayDeque<String> file = new ArrayDeque<String>();
        while(file.size()<5){
        String act = scanner.nextLine();
        if(act.equals("A") || act.equals("G") || act.equals("D")){
            file.addFirst(act);
            }
        }
        return file;
    }

    static void deplacement(ArrayDeque<String> instructions){
        while(instructions.size()!=0){
            String act = instructions.removeLast();
            if(act.equals("A")){
                if(direction == 'N'){
                    if(position[0] != 0){
                        position[0]--;
                    }
                }
                if(direction == 'S'){
                    if(position[0] != 7){
                        position[0]++;
                    }
                }
                if(direction == 'O'){
                    if(position[1] !=0){
                        position[1]--;
                    }
                }
                if(direction == 'E'){
                    if(position[1] !=7){
                        position[1]--;
                    }
                }
            }
            if(act.equals("G")){
                if(direction == 'N'){
                    direction = 'O';
                    }
                if(direction == 'O'){
                    direction = 'S';
                }
                if(direction == 'S'){
                    direction = 'E';
                }
                if(direction == 'E'){
                    direction = 'N';
                }
            }
            if(act.equals("D")){
                if(direction == 'N'){
                    direction = 'E';
                }
                if(direction == 'O'){
                    direction = 'N';
                }
                if(direction == 'S'){
                    direction = 'O';
                }
                if(direction == 'E'){
                    direction = 'S';
                }
            }
        }
    }

    public static void main(String[] args){
/*
        initialisation();
        while(true){
            ArrayDeque<String> file = creationFile();
            deplacement(file);
            System.out.println(position);
            System.out.println(direction);
        }
*/
    gen_map();
    breadthFirstSearch(map, 13);
    depthFirstSearch(map, 13);
    }

    static void gen_map(){
        map.put(13, new Integer[]{8, 17});
        map.put(8, new Integer[]{1, 11});
        map.put(1, new Integer[]{6});
        map.put(6, new Integer[]{});
        map.put(11, new Integer[]{});
        map.put(17, new Integer[]{15, 25});
        map.put(15, new Integer[]{});
        map.put(25, new Integer[]{22, 27});
        map.put(22, new Integer[]{});
        map.put(27, new Integer[]{});
    }

    static void breadthFirstSearch(TreeMap<Integer, Integer[]> graphe, Integer depart){
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        HashSet<Integer> discovered = new HashSet<>();
        ArrayList<Integer> path = new ArrayList<>();
        queue.addFirst(depart);
        while(queue.size()>0){
            Integer node = queue.removeLast();
            if(!discovered.contains(node)){
                discovered.add(node);
                path.add(node);
                for(Integer voisin : graphe.get(node)){
                    queue.addFirst(voisin);
                }
            }
        }
        System.out.println(path);
    }

    static void depthFirstSearch(TreeMap<Integer, Integer[]> graphe, Integer depart){
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        HashSet<Integer> discovered = new HashSet<>();
        ArrayList<Integer> path = new ArrayList<>();
        stack.addFirst(depart);
        while(stack.size()>0){
            Integer node = stack.removeFirst();
            if(!discovered.contains(node)){
                discovered.add(node);
                path.add(node);
                for(Integer voisin : graphe.get(node)){
                    stack.addFirst(voisin);
                }
            }
        }
        System.out.println(path);
    }
}


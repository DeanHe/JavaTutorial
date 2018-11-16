package Networking;

import java.util.*;

public class test {
	HashMap<Integer, ArrayList<Node>> map = new HashMap<>();
	HashMap<Integer, Integer> map2 = new HashMap<>();
public static void main(String[] args) throws Exception {
    
	ArrayList<Node> list = new ArrayList<>();
	Scanner input = new Scanner(System.in);
	int total = Integer.valueOf(input.nextLine());
	for(int i = 0; i < total; i++){
		String nodestr = input.nextLine();
		String[] arr = nodestr.split(",");
		int id1 = Integer.valueOf(arr[0]);
		int id2 = Integer.valueOf(arr[1]);
		Node n = new Node(id1, id2);
		list.add(n);
	}
	test t = new test();
	t.group(list);
	t.output();
}
public void group(ArrayList<Node> list){
	int root = 0;
	for(Node node : list){
		if(map.isEmpty()){
			map.put(root, new ArrayList<Node>());
			map.get(root).add(node);
			map2.put(node.id1, root);
			map2.put(node.id2, root);
		} else {
			if(map2.containsKey(node.id1)){
				int parent = map2.get(node.id1);
				map.get(parent).add(node);
				if(!map2.containsKey(node.id2)){
					map2.put(node.id2, parent);
				}
				continue;
			}
			if(map2.containsKey(node.id2)){
				int parent = map2.get(node.id2);
				map.get(parent).add(node);
				if(!map2.containsKey(node.id1)){
					map2.put(node.id1, parent);
				}
				continue;
			}
			root++;
			map2.put(node.id1, root);
			map2.put(node.id2, root);
			map.put(root, new ArrayList<>());
			map.get(root).add(node);
		}
	}
}
public void output(){
	for(ArrayList<Node> ls : map.values()){
		for(Node node : ls){
			System.out.print(node.id1 + "," + node.id2 + " ");
		}
		System.out.println();
	}
}
}
class Node {
	int root = -1, id1, id2;
	Node(int id1, int id2){
		this.id1 = id1;
		this.id2 = id2;
	}
}

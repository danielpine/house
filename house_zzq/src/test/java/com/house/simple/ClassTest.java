package com.house.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.junit.Test;

public class ClassTest {

    @Test
    public void test() {
	char[][] grid1 = {
		{ '1', '0', '1', '1', '1', '1', '1', '0' },
		{ '0', '0', '1', '1', '1', '0', '1', '0' },
		{ '1', '1', '0', '0', '1', '0', '1', '0' },
		{ '1', '1', '0', '1', '1', '0', '1', '0' },
		{ '0', '0', '1', '1', '0', '1', '0', '1' },
		{ '1', '0', '1', '1', '1', '0', '1', '1' },
		{ '0', '0', '0', '0', '1', '1', '1', '1' },
	};
	System.out.println(new Solution().numIslands(grid1));
    }

}

class Solution {
    int numIslands(char[][] grid) {
	List<Island> islands = new ArrayList<>();
	Node[][] nodes = new Node[grid.length][grid[0].length];
	for (int i = 0; i < grid.length; i++) {
	    char[] col = grid[i];
	    for (int j = 0; j < col.length; j++) {
		Node node = new Node();
		node.state = col[j];
		node.x = i;
		node.maxx = grid.length;
		node.y = j;
		node.maxy = col.length;
		node.computed = false;
		node.nodes = nodes;
		nodes[i][j] = node;
	    }
	}
	for (int i = 0; i < nodes.length; i++) {
	    Node[] col = nodes[i];
	    for (int j = 0; j < col.length; j++) {
		Node node = col[j];
		if (node.state == '1' && node.computed == false) {
		    Island island = new Island();
		    islands.add(island);
		    compute(node, island);
		}
	    }
	}
	return islands.size();
    }

    public void compute(Node node, Island island) {
	if (node.state == '0' || node.computed) {
	    return;
	}
	island.nodes.add(node);
	node.computed = true;
	search(node::up, island);
	search(node::down, island);
	search(node::left, island);
	search(node::right, island);

    }

    public void search(Supplier<Node> supplier, Island island) {
	Node node = supplier.get();
	if (node != null && node.state == '1') {
	    compute(node, island);
	}
    }

    class Island {
	List<Node> nodes = new ArrayList<>();

	int size() {
	    return nodes.size();
	}
    }

    class Node {
	char state;
	int x;
	int maxx;
	int y;
	int maxy;
	Node[][] nodes;
	boolean computed;

	public Node up() {
	    int nextX = x + 1;
	    int nextY = y;
	    return get(nextX, nextY);
	}

	public Node down() {
	    int nextX = x - 1;
	    int nextY = y;
	    return get(nextX, nextY);
	}

	public Node left() {
	    int nextX = x;
	    int nextY = y - 1;
	    return get(nextX, nextY);
	}

	public Node right() {
	    int nextX = x;
	    int nextY = y + 1;
	    return get(nextX, nextY);
	}

	private Node get(int nextX, int nextY) {
	    if (nextX < 0 || nextX >= maxx) {
		return null;
	    }
	    if (nextY < 0 || nextY >= maxy) {
		return null;
	    }
	    return nodes[nextX][nextY];
	}
    }
}

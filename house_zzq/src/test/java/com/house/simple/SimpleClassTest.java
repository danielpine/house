package com.house.simple;

import java.util.List;
import java.util.function.Supplier;

import org.assertj.core.util.Lists;
import org.junit.Test;

public class SimpleClassTest {

    @Test
    public void test() {
	int[][] grid1 = {
		{ 1, 0, 1, 1, 1, 1, 1, 0 },
		{ 0, 0, 1, 1, 1, 0, 1, 0 },
		{ 1, 1, 0, 0, 1, 0, 1, 0 },
		{ 1, 1, 0, 1, 1, 0, 1, 0 },
		{ 0, 0, 1, 1, 0, 1, 0, 1 },
		{ 1, 0, 1, 1, 1, 0, 1, 1 },
		{ 0, 0, 0, 0, 1, 1, 1, 1 },
	};
	int[][] grid2 = {
		{ 0, 0, 0, 0, 0, 0, 0, 0 }
	};
	System.out.println(solution(grid2));
    }

    public int solution(int[][] grid) {
	List<Island> islands = Lists.newArrayList();
	Node[][] nodes = new Node[grid.length][grid[0].length];
	for (int i = 0; i < grid.length; i++) {
	    int[] col = grid[i];
	    for (int j = 0; j < col.length; j++) {
		Node node = new SimpleClassTest.Node();
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
		if (node.state == 1 && node.computed == false) {
		    Island island = new SimpleClassTest.Island();
		    islands.add(island);
		    compute(node, island);
		}
	    }
	}
	return islands.stream().map(Island::size).max((a, b) -> a - b).orElse(0);
    }

    public void compute(Node node, Island island) {
	if (node.state == 0 || node.computed) {
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
	if (node != null && node.state == 1) {
	    compute(node, island);
	}
    }

    class Island {
	List<Node> nodes = Lists.newArrayList();

	int size() {
	    return nodes.size();
	}
    }

    class Node {
	int state;
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

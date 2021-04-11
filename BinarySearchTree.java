import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BinarySearchTree
{
	private BinaryNode tree;
	private int numNodes = getNumNodes();
	
	public BinarySearchTree()
	{
		tree = null;
	}
	
	public void add(BinaryNode val)
	{
		tree = insert(val.getValue(), tree);
	}
	
	public BinaryNode insert(String val, BinaryNode node)
	{
		if(node == null)
		{
			return new BinaryNode(val, null, null);
		}
		
		int compareVal = val.compareTo(node.getValue());
		if(compareVal < 0)
			node.setLeft(insert(val, node.getLeft()));
		else if(compareVal >= 0)
			node.setRight(insert(val, node.getRight()));
		return node;
	}
	
	public String preOrder()
	{
		return preOrder(tree);
	}
	
	public String preOrder(BinaryNode node)
	{
		if(node != null)
			return node.getValue() + " " + preOrder(node.getLeft())  +  preOrder(node.getRight());
		return "";
	}
	
	public String postOrder()
	{
		return postOrder(tree);
	}
	
	public String postOrder(BinaryNode node)
	{
		if(node != null)
			return postOrder(node.getLeft()) +  postOrder(node.getRight()) + node.getValue() + " ";
		return "";
	}
	
	public String inOrder()
	{
		return inOrder(tree);
	}
	
	public String inOrder(BinaryNode node)
	{
		if(node != null)
			return inOrder(node.getLeft()) + node.getValue() + " " + inOrder(node.getRight());
		return "";
	}
	
	public String reverseOrder()
	{
		return reverseOrder(tree);
	}
	
	public String reverseOrder(BinaryNode node)
	{
		if(node != null)
			return reverseOrder(node.getRight()) +node.getValue() + " " + reverseOrder(node.getLeft());
		return "";
	}
	
	public BinaryNode getLargest()
	{
		return getLargest(tree);
	}
	
	public BinaryNode getLargest(BinaryNode node)
	{
		BinaryNode largest = node;
		if(node == null)
			return null;
		while(largest.getRight() != null)
			largest = largest.getRight();
		
		return largest;
	}
	
	public BinaryNode getSmallest()
	{
		return getSmallest(tree);
	}
	
	public BinaryNode getSmallest(BinaryNode node)
	{
		BinaryNode smallest = node;
		if(node == null)
			return null;
		while(smallest.getLeft() != null)
			smallest = smallest.getLeft();
		return smallest;
	}
	
	public int getNumLeaves()
	{
		return getNumLeaves(tree);
	}
	
	public int getNumLeaves(BinaryNode node)
	{
		if(node == null)
			return 0;
		else if(node.getLeft() == null && node.getRight() == null)
			return 1;
		return getNumLeaves(node.getRight()) + getNumLeaves(node.getLeft());
	}
	
	public int getNumLevels()
	{
		return getNumLevels(tree);
	}
	
	public int getNumLevels(BinaryNode node)
	{
        if(node == null)
            return 0;
        return 1 + Math.max(getNumLevels(node.getLeft()), getNumLevels(node.getRight()));
	}
	
	
	public int getHeight()
	{
		return getNumLevels(tree) - 1;
	}
	
	
	public int getNumNodes()
	{
		return getNumNodes(tree);
	}
	
	public int getNumNodes(BinaryNode node)
	{
		if(node == null)
			return 0;

		return 1 + getNumNodes(node.getLeft()) + getNumNodes(node.getRight());
	}
	
	public int getWidth()
	{
		return getWidth(tree);
	}
	
    int ans;
    Map<Integer, Integer> left;
    
    public int getWidth(BinaryNode root)
    {
    	if(root == null)
            return 0;
        if(root.getLeft() == null && root.getRight() == null)
            return 1;
        int w = 1 + getNumLevels(root.getLeft()) + getNumLevels(root.getRight());
        return Math.max(Math.max(w, getWidth(root.getLeft())), getWidth(root.getRight()));
    }
	
	
	public boolean isFull()
	{
		if(getNumNodes() == Math.pow(2, getNumLevels()))
			return true;
		return false;
	}
	
	public boolean contains(String str)
	{
		return contains(tree, str);
	}
	
	public boolean contains(BinaryNode node, String str)
	{
		if(node == null)
			return false;
		else
		{
			int compareVal = str.compareTo(node.getValue());
			if(compareVal > 0)
				return contains(node.getRight(), str);
			else if(compareVal < 0)
				return contains(node.getLeft(), str);
			return true;
		}
	}
	
	public Queue<Comparable> levelOrder()
	{
		Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
		Queue<Comparable> q= new LinkedList<Comparable>();
		queue.add(tree);
		while(!queue.isEmpty())
		{
			BinaryNode tempNode = queue.poll();
			 q.add(tempNode.getValue()); 
			if(tempNode.getLeft() != null)
				queue.add(tempNode.getLeft());
			if(tempNode.getRight() != null)
				queue.add(tempNode.getRight());
		}
		return q;
	}
	
	public String fullLevelOrder()
	{
		return fullLevelOrder(Math.min(getNumLevels(),6));
	}
	
	public String fullLevelOrder(int lol)
	{
		String str = "";
		for(int i = 0; i < lol; i++)
			str += printFullLevel(i, tree) +  "\n";
		return str;
	}
	
	public void printFullTree(String levels, int level)
	{
		System.out.println(fullLevelOrder(level));
	}
	
	public String printFullLevel(int level, BinaryNode node)
	{
		if(node == null)
		{
			if(level > 0)
				return printFullLevel(level-1, null) + printFullLevel(level-1, null);
			return "null|";
		}
		if(level == 0)
			return node.getValue() + "|";
		return printFullLevel(level-1, node.getLeft()) + printFullLevel(level-1, node.getRight()); 
		
	}
	
	public BinaryNode remove(String rem)
	{
		if(!contains(rem))
            return null;
        numNodes--;
        BinaryNode iter = tree;
        BinaryNode prev = null;
        boolean right = false;
        while(iter.getValue().compareTo(rem) != 0)
        {
            prev = iter;
            int dir = rem.compareTo(iter.getValue());
            if(dir < 0)
            {
                iter = iter.getLeft();
                right = false;
            }
            else if(dir > 0)
            {
                iter = iter.getRight();
                right = true;
            }
        }
        if(prev == null)
        {
            BinaryNode temp = tree.getRight();
            tree = tree.getLeft();
            BinaryNode iter2 = tree;
            while(iter2.getRight() != null)
                iter2 = iter2.getRight();
            iter2.setRight(temp);
        }
        else if(iter.getRight() == null)
        {
            if(!right)
                prev.setLeft(iter.getLeft());
            else
                prev.setRight(iter.getLeft());
        }
        else if(iter.getLeft() == null)
        {
            if(!right)
                prev.setLeft(iter.getRight());
            else
                prev.setRight(iter.getRight());
        }
        else
        {
            BinaryNode temp = iter.getRight();
            if(!right)
                prev.setLeft(iter.getLeft());
            else
                prev.setRight(iter.getLeft());
            BinaryNode iter2 = iter.getLeft();
            while(iter2.getRight() != null)
                iter2 = iter2.getRight();
            iter2.setRight(temp);
        }
        return new BinaryNode(rem, null, null);

	}
	
	public String toString()
	{
		return inOrder();
	}
	

}

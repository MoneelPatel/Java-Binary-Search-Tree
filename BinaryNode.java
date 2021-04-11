
public class BinaryNode 
{
	private String value;
	private BinaryNode left;   
	private BinaryNode right;
	
	 public BinaryNode()
	 {      
		 value = null;      
		 left = null;      
	 	 right = null;   
	 }
	
	public BinaryNode(String val)
	{
		value = val;
		left = null;
		right = null;
	}
	
	public BinaryNode(String val, BinaryNode lef, BinaryNode righ)
	{
		value = val;
		left = lef;
		right = righ;
	}
	
	public BinaryNode getLeft()
	{
		return left;
	}
	
	public BinaryNode getRight()
	{
		return right;
	}
	
	public void setLeft(BinaryNode lef)
	{
		left = lef;
	}
	
	public void setRight(BinaryNode righ)
	{
		right = righ;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public String toString()
	{
		return "Value: " + value + ", Left: " + getLeft() + ", Right: " + getRight();
	}
	
}

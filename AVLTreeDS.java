import org.apache.log4j.Logger;

public class AVLTreeDS extends BinaryDS
{
    final static Logger logger = Logger.getLogger(AVLTreeDS.class);

	private Node root;

    /*
	 * AVL Tree Notes:
	 * 	- Efficiency : O(log2n)
     *  - Definition : Binary tree that is either empty or consists of two AVL subtrees. 
     *      (TL and TR) whoes heighs differ by no more than 1. | HL - HR | <= 1
     *  - LH = Left High (-1): EH = Even High (0): RH = Right High (1)
     *  - When a something is inserted/deleted, the tree may become unbalanced and we can rotate
     *      left or right to balance the tree
     *  - https://www.geeksforgeeks.org/avl-tree-set-1-insertion/
	 * 		(NOTE: Book had C++ implmentation only)
	 */ 

	// A utility function to get the height of the tree
	public int getHeight(Node N)
	{
		logger.trace(getCurrentMethodName() + " Entering ");

		if(N == null)
		{
			logger.debug(getCurrentMethodName() + " Node is null. Returning : " + 0);
			logger.trace(getCurrentMethodName() + " Exiting ");
			return 0;
		}
		else
		{
			logger.debug(getCurrentMethodName() + " Node is NOT null. Returning : " + N.getHeight());
			logger.trace(getCurrentMethodName() + " Exiting ");
			return N.getHeight();
		}

	}

	// A utility function to get maximum of two integers
	private int max(int a, int b) 
	{
		/*if(a > b)
		{
			return a;
		}
		else
		{
			return b;
		}*/
		logger.trace(getCurrentMethodName() + " Entering ");
		logger.trace(getCurrentMethodName() + " Exiting ");
		return (a > b) ? a : b;
	}

	private Node rightRotate(Node y) 
	{
		logger.trace(getCurrentMethodName() + " Entering ");
        Node x = y.getLeftSubTree();
        Node T2 = x.getRightSubTree();
 
        // Perform rotation
        x.setRightSubTree(y);
        y.setRightSubTree(T2);
 
        // Update heights
        y.setHeight(max(getHeight(y.getLeftSubTree()), getHeight(y.getRightSubTree())) + 1);
        x.setHeight(max(getHeight(x.getLeftSubTree()), getHeight(x.getRightSubTree())) + 1);
 
		logger.debug(getCurrentMethodName() + " Returning Node : " + x);
		logger.trace(getCurrentMethodName() + " Exiting ");
        // Return new root
        return x;
    }

    class Node
	{
		private Node leftSubTree;
		private Node rightSubTree;
		//private Object data;
		private int key;
		private int height;
		
		
		public Node(int key)
		{
			logger.trace(getCurrentMethodName() + " Entering ");
			this.leftSubTree = null;
			this.rightSubTree = null;
			this.key = key;
			logger.trace(getCurrentMethodName() + " Exiting ");
		}

		public int getHeight()
		{
			logger.trace(getCurrentMethodName() + " Entering ");
			logger.debug(getCurrentMethodName() + " Returning  : " + this.height);
			logger.trace(getCurrentMethodName() + " Exiting ");
			return this.height;
		}
		
		public void setHeight(int newHeight)
		{
			logger.trace(getCurrentMethodName() + " Entering ");
			this.height = newHeight;
			logger.trace(getCurrentMethodName() + " Exiting ");
		}

		public Node getLeftSubTree() 
		{
            logger.trace(getCurrentMethodName() + " Entering ");
            logger.debug(getCurrentMethodName() + " Returning Left Tree Node : " + leftSubTree);
            logger.trace(getCurrentMethodName() + " Exiting ");
			return this.leftSubTree;
		}

		public void setLeftSubTree(Node leftSubTree) {
            logger.trace(getCurrentMethodName() + " Entering ");
            logger.debug(getCurrentMethodName() + " Setting left node to : " + leftSubTree);
            logger.trace(getCurrentMethodName() + " Exiting ");
			this.leftSubTree = leftSubTree;
		}

		public Node getRightSubTree() {
            logger.trace(getCurrentMethodName() + " Entering ");
            logger.debug(getCurrentMethodName() + " Returning Right Tree Node : " + rightSubTree);
            logger.trace(getCurrentMethodName() + " Exiting ");
			return this.rightSubTree;
		}

		public void setRightSubTree(Node rightSubTree) {
            logger.trace(getCurrentMethodName() + " Entering ");
            logger.debug(getCurrentMethodName() + " Setting right node to : " + rightSubTree);
            logger.trace(getCurrentMethodName() + " Exiting ");
			this.rightSubTree = rightSubTree;
		}

		/*
		Use this if tree supports data and keys. Right now it just supports keys as data.
		public Object getData() 
		{
			logger.trace(getCurrentMethodName() + " Entering ");
			logger.trace(getCurrentMethodName() + " Exiting ");
			return this.data;
		}
		*/

		/*
		Use this if tree supports data and keys. Right now it just supports keys as data.
		public boolean setData(Object data) 
		{
			logger.trace(getCurrentMethodName() + " Entering ");
			this.data = data;
			if(this.data == data)
			{
				logger.trace(getCurrentMethodName() + " Exiting ");
				return true;
			}
			else
			{
				logger.trace(getCurrentMethodName() + " Exiting ");
				return false;
			}
			
		}*/
	}

    /*---------------------------------------------------------------------
    |  Method getCurrentMethodName()
    |
    |  Author: sgyoung
    |  
    |  Purpose:  Gets the method name for debugging
    |
    |  Pre-condition:  None.
    |
    |  Post-condition: None.
    |
    |  Returns: Method Name.
    *-------------------------------------------------------------------*/
	private static String getCurrentMethodName() 
    { 
        StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace(); 
        
        return 	stackTraceElements[1].toString().replaceFirst(stackTraceElements[1].toString().split("\\.")[0]+"\\.", "");
    }
}

import javax.lang.model.util.ElementScanner6;

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

	AVLTreeDS()
	{
		logger.info(getCurrentMethodName() + " Creating AVL Tree. Setting root to null.");
		this.root = null;
	}

	public void AVLInsert(Node root, Object data)
	{
		AVLInsertHelper(root, new Node(data), true);
	}

	private <T extends Comparable<T>> Node AVLInsertHelper(Node root, Node newNode, boolean taller)
	{
		logger.trace(getCurrentMethodName() + " Entering ");

		if(this.root == null)
		{
			logger.info(getCurrentMethodName() + " root is null. Inserting root for tree.");

			this.root = newNode; 
			taller = true;

            logger.trace(getCurrentMethodName() + " Exiting ");
			return this.root;
		}

		if(((Comparable<T>) newNode.getData()).compareTo((T) this.root.getData()) < 0)
		{
			logger.info(getCurrentMethodName() + " Node being inserted is less than current node. Setting left subtree.");
			root.setLeftSubTree(AVLInsertHelper(root.getLeftSubTree(), newNode, taller));

			if(taller == true)
			{
				logger.info(getCurrentMethodName() + " Left subtree is taller. ");
				//- LH = Left High (-1): EH = Even High (0): RH = Right High (1)
					if (root.getBal() == -1)
					{
						logger.info(getCurrentMethodName() + " Tree is left high (-1).  Running leftBalance algorithm on current root. ");
						//root = leftBalance(root, taller);
					}
					else if(root.getBal() == 0)
					{
						logger.info(getCurrentMethodName() + " Tree is even high. Setting balance to left high (-1) ");
						root.setBal(-1);
					}
					else
					{
						logger.info(getCurrentMethodName() + " Tree was right high (1) --now even high. (0) ");
						root.setBal(0);
						taller = false;
					}
			}

			logger.trace(getCurrentMethodName() + " Exiting ");
			return newNode;
		}
		else
		{
			logger.info(getCurrentMethodName() + " Node being inserted is greater than or equal to current node. Setting right subtree. ");
			root.setRightSubTree(AVLInsertHelper(root.getRightSubTree(), newNode, taller));

			if(taller == true)
			{
				logger.info(getCurrentMethodName() + " Right subtree is taller. ");
				//- LH = Left High (-1): EH = Even High (0): RH = Right High (1)
				
				if( root.getBal() == -1 )
				{
					logger.info(getCurrentMethodName() + " Tree was left high (-1), tree is now even high (0).");
					root.setBal(0);
					taller = false;
				}
				else if(root.getBal() == 0)
				{
					logger.info(getCurrentMethodName() + " Tree is even high. Setting balance to right high (1) ");
					root.setBal(1);
				}
				else 
				{
					logger.info(getCurrentMethodName() + " Tree is right high (1). Running rightBalance algorithm on current root. ");
					//root = rightBalance (root, taller);
				}
			}

			return newNode;
		}		
	}

    class Node
	{
		private Node leftSubTree;
		private Node rightSubTree;
		private Object data;
		int bal;
		
		public Node(Object data)
		{
			logger.trace(getCurrentMethodName() + " Entering ");
			this.leftSubTree = null;
			this.rightSubTree = null;
			this.data = data;
			logger.trace(getCurrentMethodName() + " Exiting ");
		}
		
		public int getBal()
		{
			return this.bal;
		}

		public boolean setBal(int newBal)
		{
			this.bal = newBal;
			return true;
		}

		public Object getData() 
		{
			logger.trace(getCurrentMethodName() + " Entering ");
			logger.trace(getCurrentMethodName() + " Exiting ");
			return this.data;
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
	protected static String getCurrentMethodName() 
    { 
        StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace(); 
        
        return 	stackTraceElements[1].toString().replaceFirst(stackTraceElements[1].toString().split("\\.")[0]+"\\.", "");
    }
}

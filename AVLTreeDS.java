import javax.lang.model.util.ElementScanner6;

import org.apache.log4j.Logger;

public class AVLTreeDS //extends BinaryDS
{
    final static Logger logger = Logger.getLogger(AVLTreeDS.class);

	private Node rootNode;

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
		this.rootNode = null;
	}

	public void AVLInsert(Node root, Object data)
	{
		logger.trace(getCurrentMethodName() + " Entering ");

		if(this.rootNode == null)
		{
			logger.info(getCurrentMethodName() + " root is null. Inserting root for tree.");

			this.rootNode = new Node(data); 

            logger.trace(getCurrentMethodName() + " Exiting ");
		}

		AVLInsertHelper(this.rootNode, new Node(data), true);
		
		logger.trace(getCurrentMethodName() + " Exiting ");
	}

	private <T extends Comparable<T>> Node AVLInsertHelper(Node root, Node newNode, boolean taller)
	{
		logger.trace(getCurrentMethodName() + " Entering ");

		if(root == null)
		{
			logger.info(getCurrentMethodName() + " root is null. Inserting root for tree.");

			this.rootNode = newNode; 
			taller = true;

            logger.trace(getCurrentMethodName() + " Exiting ");
			return this.rootNode;
		}

		if(((Comparable<T>) newNode.getData()).compareTo((T) root.getData()) < 0)
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
						root = leftBalance(root, taller);
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

	private <T extends Comparable<T>> Node leftBalance(Node root, boolean taller)
	{
		logger.trace(getCurrentMethodName() + " Entering ");

		Node leftTree = root.getLeftSubTree();
		
		//- LH = Left High (-1): EH = Even High (0): RH = Right High (1)
		if(leftTree.getBal() == -1)
		{
			logger.info(getCurrentMethodName() + " Case 1: Left of left. Single rotation right.");
			logger.info(getCurrentMethodName() + " left node of root : " + leftTree + " is left high (-1)");
			logger.info(getCurrentMethodName() + " Rotating root " + root + " node right.");

			//rotateRight(root);
			logger.info(getCurrentMethodName() + " Setting root : " + root + " to even high.");
			root.setBal(0);
			logger.info(getCurrentMethodName() + " Setting roots left node : " + leftTree + " to even high.");
			leftTree.setBal(0); 
			logger.info(getCurrentMethodName() + " Setting taller to false.");
			taller = false;
		}
		else
		{
			logger.info(getCurrentMethodName() + " Note: even balance factor is impossible.");
			logger.info(getCurrentMethodName() + " Case 2: Right of left. Double Rotation Required.");

			Node rightTree = root.getRightSubTree();
			logger.info(getCurrentMethodName() + " Adjust the balance factor.");

				if(rightTree.getBal() == -1)
				{
					logger.info(getCurrentMethodName() + " right node of root : " + rightTree + " is left high.");
					logger.info(getCurrentMethodName() + " Setting root " + root + " balance to right high (1)");
					root.setBal(1);
					logger.info(getCurrentMethodName() + " Setting left node  " + leftTree + " balance to even high (0)");
					leftTree.setBal(0);
				}
				else if (rightTree.getBal() == 0)
				{
					logger.info(getCurrentMethodName() + " right node of root : " + rightTree + " even high.");
					logger.info(getCurrentMethodName() + " Setting left node balance : " + leftTree + " to even high.");
					leftTree.setBal(0);
				}
				else
				{
					logger.info(getCurrentMethodName() + " rightTree balance is right-high (1)");
					//- LH = Left High (-1): EH = Even High (0): RH = Right High (1)
					logger.info(getCurrentMethodName() + " Setting root : " + root + " to Even High (0)");
					root.setBal(0);
					logger.info(getCurrentMethodName() + " Setting left node  " + leftTree + " balance to left high (-1)");
					leftTree.setBal(-1);
				}

				logger.info(getCurrentMethodName() + " Setting right node  " + rightTree + " balance to even high (0)");
				rightTree.setBal(0);
				logger.info(getCurrentMethodName() + " Setting Left Subtree " + leftTree + " to rotateLeft");
				root.setLeftSubTree(rotateLeft(leftTree));

				logger.info(getCurrentMethodName() + " Setting root " + root + " to rotateRight");
				root = rotateRight(root);

				logger.info(getCurrentMethodName() + " Setting root taller to false");
				taller = false;
		}
		
		logger.trace(getCurrentMethodName() + " Exiting ");

		return root;
	}

	private <T extends Comparable<T>> Node rotateRight(Node root)
	{
	    logger.info(getCurrentMethodName() + " root node passed in : " + root);
		logger.info(getCurrentMethodName() + " Setting tempPtr to " + root + " (root) to left node " + root.getLeftSubTree());
		Node tempPtr = root.getLeftSubTree();

		root.setLeftSubTree(tempPtr.getRightSubTree());
		tempPtr.setRightSubTree(root);

		logger.trace(getCurrentMethodName() + " Exiting " + tempPtr);
		return tempPtr;
	}

	private <T extends Comparable<T>> Node rotateLeft(Node root)
	{
		Node tempPtr = root.getRightSubTree();
		root.setRightSubTree(tempPtr.getLeftSubTree());
		tempPtr.setLeftSubTree(root);
		return tempPtr;
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

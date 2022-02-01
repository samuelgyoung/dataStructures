import org.apache.log4j.Logger;

public class AVLTreeDS 
{
    final static Logger logger = Logger.getLogger(AVLTreeDS.class);

    /*
	 * AVL Tree Notes:
	 * 	- Efficiency : O(log2n)
     *  - Definition : Binary tree that is either empty or consists of two AVL subtrees. 
     *      (TL and TR) whoes heighs differ by no more than 1. | HL - HR | <= 1
     *  - LH = Left High : EH = Even High : RH = Right High
     *  - When a something is inserted/deleted, the tree may become unbalanced and we can rotate
     *      left or right to balance the tree
     *  
	 */ 

    class Node
	{
		private Node leftSubTree;
		private Node rightSubTree;
		private Object data;
		
		public Node(Object data)
		{
			logger.trace(getCurrentMethodName() + " Entering ");

            logger.debug(getCurrentMethodName() + " Creating new node with data " + data );

			this.leftSubTree = null;
			this.rightSubTree = null;
			this.data = data;

			logger.trace(getCurrentMethodName() + " Exiting ");
		}
		
		public Node()
		{
			logger.trace(getCurrentMethodName() + " Entering ");
			this.leftSubTree = null;
			this.rightSubTree = null;
			logger.trace(getCurrentMethodName() + " Exiting ");
		}
		
		public Node getLeftSubTree() {
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

		public Object getData() 
		{
			logger.trace(getCurrentMethodName() + " Entering ");
			logger.trace(getCurrentMethodName() + " Exiting ");
			return this.data;
		}

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
	private static String getCurrentMethodName() 
    { 
        StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace(); 
        
        return 	stackTraceElements[1].toString().replaceFirst(stackTraceElements[1].toString().split("\\.")[0]+"\\.", "");
    }
}

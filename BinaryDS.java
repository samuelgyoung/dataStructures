import javax.lang.model.util.ElementScanner6;

import org.apache.log4j.Logger;

public class BinaryDS 
{
	final static Logger logger = Logger.getLogger(BinaryDS.class);
	
	/*
	 * Binary Tree Notes:
	 * 	- No node can have more than two subtrees
	 *  - The max height of a binary tree is: N nodes = max height
	 *  - The min height of a binary tree is: [log2 N] + 1
	 *  - Given the Height of a binary tree (H), the minimum and maximum number of nodes in the tree:
	 *  	- N(min) = H
	 *  	- N(max) = 2^H-1
	 *  - The shorter the tree, the easier it is to find the desired node
	 *  	- Thus, you want a balanced b-tree
	 *  	- Balance Factor is the difference between the left and right subtrees
	 *  		- If we determine the height of the left subtree (HL) and the height of the
	 *  			right subtree (HR), the balance factor (B) is determined by : B = HL - HR
	 *  	- Generally, a tree is balanced if the height of its subtrees differenes by no more than
	 *  		one (-1,0,1) and it's subtrees are also balanced
	 *  	- This definition was created by Adelson-Veskii and Landis in the def of AVL trees
	 *   - A complete tree has the max number of entries for its height.
	 *   - The max number if reached when the last level is full
	 *   - A tree is nearly complete if it has a min height for it's nodes (H(min)) and all nodes in the last level
	 *   	are found on the left
	 *   - Tree traversals require each node be processed once and only once.
	 *   	- depth-first approach: process all of the descendents of a child before going to the next child
	 *   		- There are 3 traversals (in liturature, 6 total)
	 *   			- Preorder : root processed first, then left subtree, then right subtree
	 *   			- Inorder : processes the left side first, then root, then right
	 *   			- Postorder : processes the left side first, then right, then root
	 *   	- breadth first approach: each level is completely processed before the next level is started
	 */ 
	
     //USED FOR BREADTHFIRST BINARY TREE PROCESSING
	private QueueDS QueueDS_001;
	private QueueDS.Queue queue_001;
	
    private String convertToParenString;

	Node rootNode;
	
	public BinaryDS()
	{
        logger.trace(getCurrentMethodName() + " Entering ");

        logger.info(getCurrentMethodName() + " Creating Binary Search Tree (BST). Childeren to the left are less than the root, and greater on the right.");

		this.QueueDS_001 = new QueueDS();
		this.queue_001 = QueueDS_001.createQueue();

        this.convertToParenString = "";

        logger.trace(getCurrentMethodName() + " Exiting ");
	}	
	
	public BinaryDS(Object data)
	{
        logger.trace(getCurrentMethodName() + " Entering ");

        logger.info(getCurrentMethodName() + " Creating Binary Search Tree (BST). Childeren to the left are less than the root, and greater on the right.");

		this.QueueDS_001 = new QueueDS();
		this.queue_001 = QueueDS_001.createQueue();
		
        this.convertToParenString = "";

		this.rootNode = new Node(data);
        logger.trace(getCurrentMethodName() + " Exiting ");
	}	


    public void printBSTParen()
    {
        convertToParen(this.getRootNode());
        System.out.println(getconvertToParenString());
    }

    /*---------------------------------------------------------------------
    |  Method getconvertToParenString()
    |
    |  Author: sgyoung
    |  
    |  Purpose:  Return the binary tree string converted to paren
    |
    |  Pre-condition:  None.
    |
    |  Post-condition: Returns the converted string.
    |
    |  Parameters: None.
    |
    |  Returns: Tree to string in parens.
    *-------------------------------------------------------------------*/
    public String getconvertToParenString()
    {
        logger.trace(getCurrentMethodName() + " Entering ");
        logger.trace(getCurrentMethodName() + " Exiting ");
        return this.convertToParenString;
    }
	
    /*---------------------------------------------------------------------
    |  Method getRootNode()
    |
    |  Author: sgyoung
    |  
    |  Purpose:  Return the root nod of the tree.
    |
    |  Pre-condition:  None.
    |
    |  Post-condition: Returns the root node.
    |
    |  Parameters: None.
    |
    |  Returns: Root node.
    *-------------------------------------------------------------------*/
	public Node getRootNode()
	{
        logger.trace(getCurrentMethodName() + " Entering ");
        logger.debug(getCurrentMethodName() + " Returning root node:  " + rootNode);
        logger.trace(getCurrentMethodName() + " Exiting ");
		return rootNode;
	}
	
	//DEPTH FIRST TRAVERSALS ______________________________________________
	/*---------------------------------------------------------------------
    |  Method preOrder()
    |
    |  Author: sgyoung
    |  
    |  Purpose:  Traverse a binary tree in a node-left-right sequence.
    |	preOrder processes the root, then left, then right
    |
    |  Pre-condition:  Root is the entry node of a tree or subtree
    |
    |  Post-condition: each node had been processed in order
    |
    |  Parameters: root node pointer
    |
    |  Returns: 
    *-------------------------------------------------------------------*/
	public void preOrder(Node node)
	{
        logger.trace(getCurrentMethodName() + " Entering ");
		if (node != null)
		{
			//process(node.data) //DO STUFF TO THE NODE (WE ARE JUST TRAVERSING)
            logger.info(getCurrentMethodName() + " Processing node : " + node + " with data " + node.getData());
			preOrder(node.getLeftSubTree());
			preOrder(node.getRightSubTree());
		}
        logger.trace(getCurrentMethodName() + " Exiting ");
	}
	
	/*---------------------------------------------------------------------
    |  Method inOrder()
    |
    |  Author: sgyoung
    |  
    |  Purpose:  Traverse a binary tree in left-node-right squence.
    |  inOrder processes the left side first, then root, then right
    |
    |  Pre-condition:  root is the entry node of a tree or subtree
    |
    |  Post-condition: each node has been processed in order
    |
    |  Parameters: root node pointer
    |
    |  Returns: 
    *-------------------------------------------------------------------*/
	public void inOrder(Node node)
	{
        logger.trace(getCurrentMethodName() + " Entering ");
		if (node != null)
		{
			inOrder(node.getLeftSubTree());
			//process(node.data) //DO STUFF TO THE NODE (WE ARE JUST TRAVERSING)
            logger.info(getCurrentMethodName() + " Processing node : " + node + " with data " + node.getData());
			inOrder(node.getRightSubTree());
			
		}
        logger.trace(getCurrentMethodName() + " Exiting ");
	}
	
	/*---------------------------------------------------------------------
    |  Method postOrder()
    |
    |  Author: sgyoung
    |  
    |  Purpose:  Traverse a binary tree in the left-right-node sequence.
    |  postOrder processes the left side first, then right, then root
    |
    |  Pre-condition:  root is the entry node of a tree or subtree
    |
    |  Post-condition: each node has been processed in order
    |
    |  Parameters: root node pointer
    |
    |  Returns: 
    *-------------------------------------------------------------------*/
	public void postOrder(Node node)
	{
        logger.trace(getCurrentMethodName() + " Entering ");

		if (node != null)
		{
			postOrder(node.getLeftSubTree());
			postOrder(node.getRightSubTree());
			//process(node.data) //DO STUFF TO THE NODE (WE ARE JUST TRAVERSING)
            logger.info(getCurrentMethodName() + " Processing node : " + node + " with data " + node.getData());
		}
        logger.trace(getCurrentMethodName() + " Exiting ");
	}
	//END DEPTH FIRST TRAVERSALS __________________________________________

	
	//BREADTH-FIRST TRAVERSALS _____________________________________________
	/*---------------------------------------------------------------------
    |  Method  breadthFirst()
    |
    |  Author: sgyoung
    |  
    |  Purpose:  Process tree using breadthFirst-first traversal
    |  breadth-first processes each level
    |   This means each "level" of the tree is processed starting from the top.
    |
    |  Pre-condition:  root is a pointer to a tree node
    |
    |  Post-condition: tree has been processed
    |
    |  Parameters: root node pointer
    |
    |  Returns: 
    *-------------------------------------------------------------------*/
	public void breadthFirst(Node node)
	{
        logger.trace(getCurrentMethodName() + " Entering ");
		Node pointer = node;

        logger.debug(getCurrentMethodName() + " Starting with node : " + node + " with data " + node.getData());

		while (pointer != null)
		{
            //process(pointer) //DO STUFF TO THE NODE (WE ARE JUST TRAVERSING)
            logger.debug(getCurrentMethodName() + " Processing node : " + pointer + " with data " + pointer.getData());
			
			if(pointer.getLeftSubTree() != null)
			{	
                logger.debug(getCurrentMethodName() + " Left Node is not null. Adding to Queue for Processing with data " + pointer.getLeftSubTree().getData());		
				this.QueueDS_001.enqueue(pointer.getLeftSubTree(), queue_001);
			}
			else
			{
				logger.debug(getCurrentMethodName() + " Left Node not detected.");	
			}
			
			if(pointer.getRightSubTree() != null)
			{
                logger.debug(getCurrentMethodName() + " Right Node is not null. Adding to Queue for Processing with data " + pointer.getRightSubTree().getData());	
				this.QueueDS_001.enqueue(pointer.getRightSubTree(), queue_001);
			}
			else
			{
				logger.debug(getCurrentMethodName() + " Right Node not detected.");
			}
			
			//IF THE QUEUE IS NOT EMPTY
			if(this.QueueDS_001.emptyQueue(queue_001) != true)
			{
                logger.debug(getCurrentMethodName() + " Queue is not empty.");

                logger.debug(getCurrentMethodName() + " Dequeuing first in the queue. Old Pointer : " + pointer + " and data " + pointer.getData());
					
                pointer = (Node) this.QueueDS_001.queueFront(queue_001);
                this.QueueDS_001.dequeue(queue_001);
                    
                logger.debug(getCurrentMethodName() + " New Pointer : " + pointer + " and data " + pointer.getData());
			}
			else
			{
                logger.debug(getCurrentMethodName() + " Queue is empty. Setting pointer to null");
				pointer = null;
			}
		}
        logger.trace(getCurrentMethodName() + " Exiting ");
	}
	
	//END BREADTH-FIRST TRAVERSALS _________________________________________

	//TODO: INFIX,PREFIX AND POSTFIX NOTATION p.323 (NOTE, TREE DOES NOT ACCEPT MIX OF CHARACTER/INTEGER TYPES)

    public void convertToParen(Node node)
    {
        logger.trace(getCurrentMethodName() + " Entering ");

        this.convertToParenString = this.convertToParenString + node.getData() + " ";

        if((node.getRightSubTree() != null) || (node.getLeftSubTree() != null))
        {
            this.convertToParenString = this.convertToParenString + " ( ";

            if(node.getLeftSubTree() != null)
            {
                convertToParen(node.getLeftSubTree());
            }
            if(node.getRightSubTree() != null)
            {
                convertToParen(node.getRightSubTree());
            }

            this.convertToParenString = this.convertToParenString + " ) ";

        }
        logger.trace(getCurrentMethodName() + " Exiting ");
    }
	
	/*---------------------------------------------------------------------
    |  Method  findSmallestBST()
    |
    |  Author: sgyoung
    |  
    |  Purpose:  This algorithm finds the smallest node in the BST 
    |	(binary search tree).
    |
    |  Pre-condition:  root is the pointer to the nonempty BST or subtree
    |
    |  Post-condition: none.
    |
    |  Parameters: 
    |
    |  Returns: address of smallest node
    *-------------------------------------------------------------------*/
	public Object findSmallestBST(Node node)
	{
        logger.trace(getCurrentMethodName() + " Entering ");
		if(node.getLeftSubTree() == null)
		{
			logger.info(getCurrentMethodName() + " No more nodes to the left. Found the lowest value in the tree : " + node.getData());
            logger.trace(getCurrentMethodName() + " Exiting ");
			return node;
		}
		
		logger.debug(getCurrentMethodName() + " Still more nodes to the left. Checking next one... ");
        logger.trace(getCurrentMethodName() + " Exiting ");
		return findSmallestBST(node.getLeftSubTree());
	}
	
	/*---------------------------------------------------------------------
    |  Method  findLargestBST()
    |
    |  Author: sgyoung
    |  
    |  Purpose:  This algorithm finds the largest node in the BST 
    |	(binary search tree).
    |
    |  Pre-condition:  root is the pointer to the nonempty BST or subtree
    |
    |  Post-condition: none.
    |
    |  Parameters: 
    |
    |  Returns: address of the largest node
    *-------------------------------------------------------------------*/
	public Object findLargestBST(Node root)
	{
        logger.trace(getCurrentMethodName() + " Entering ");
		if(root.getRightSubTree() == null)
		{
			logger.info(getCurrentMethodName() + " No more nodes to the right. Found the highest value in the tree : " + root.getData());
            logger.trace(getCurrentMethodName() + " Exiting ");
			return root;
		}
		
		logger.debug(getCurrentMethodName() + " Still more nodes to the left. Checking next one... ");
        logger.trace(getCurrentMethodName() + " Exiting ");
		return findLargestBST(root.getRightSubTree());
	}
	
	/*---------------------------------------------------------------------
    |  Method  searchBST()
    |
    |  Author: sgyoung
    |  
    |  Purpose:  Search a BST for a given value.
    |
    |  Pre-condition:  root is the root to a binary tree or subtree argument is
    |	the key value requested.
    |
    |  Post-condition: none.
    |
    |  Parameters: 
    |
    |  Returns: the node address if the value is found
    |	null if the node is not in the tree
    *-------------------------------------------------------------------*/
	public <T extends Comparable<T>> Node searchBST(Node root, T value)
	{
		logger.trace(getCurrentMethodName() + " Entering ");
		//Using Comparable allows us to handle different data types (int, character etc...)
		
		if(root == null )
		{
			logger.debug(getCurrentMethodName() + "Node is null. Value wasn't found.");
            logger.trace(getCurrentMethodName() + " Exiting ");
			return null;
		}
		//if(value < Current Nodes Data), go left
		if(value.compareTo((T) root.getData()) < 0)
		{
			logger.debug(getCurrentMethodName() + " Moving to the left node on the tree... ");
            logger.trace(getCurrentMethodName() + " Exiting ");
			return searchBST(root.getLeftSubTree(), value);
		}
		//if(value > Current Nodes Data), go right
		else if(value.compareTo((T) root.getData()) > 0)
		{
			logger.debug(getCurrentMethodName() + " Moving to the right node on the tree... ");
            logger.trace(getCurrentMethodName() + " Exiting ");
			return searchBST(root.getRightSubTree(), value);
		}
		else
		{
			logger.debug(getCurrentMethodName() + " Found the node that contains the value: " + root);
            logger.trace(getCurrentMethodName() + " Exiting ");
			return root;
		}
        
	}

	/*---------------------------------------------------------------------
    |  Method  insertBST()
    |
    |  Author: sgyoung
    |  
    |  Purpose:  Insert node containing new node into BST using iteration.
    |
    |  Pre-condition:  root is the address of first node in BST
    |	new is address of node containing data to be inserted.
    |
    |  Post-condition: new node inserted into the tree
    |
    |  Parameters: 
    |
    |  Returns: 
    *-------------------------------------------------------------------*/
	public <T extends Comparable<T>> void insertBST(Node root, T value)
	{
        logger.trace(getCurrentMethodName() + " Entering ");

        logger.debug(getCurrentMethodName() + " Inserting data " + value + " into the tree");

		if(root == null )
		{
			logger.debug(getCurrentMethodName() + "Node is null. Inserting a new node.");
			root = new Node(value);
		}
		else
		{
			Node nodeWalk = root;
			
			while(nodeWalk != null)
			{
				Node parent = nodeWalk;

                //Value is already in tree
                if(value.compareTo((T) nodeWalk.getData()) == 0)
                {
                    logger.info(getCurrentMethodName() + " " + value + " already in tree, not adding.");
                    nodeWalk = null;
                }
                //if(value < Current Nodes Data), go left
				else if(value.compareTo((T) nodeWalk.getData()) < 0)
				{
					logger.debug(getCurrentMethodName() + " Comparing : " + value + " to " + nodeWalk.getData() + " Going left");
					nodeWalk = nodeWalk.getLeftSubTree();
                    
                    if(nodeWalk == null)
                    {
                        logger.debug(getCurrentMethodName() + " No node returned- inserting left...");
                        parent.setLeftSubTree(new Node(value));
                    }

				}
                //Go Right
				else
				{
					logger.debug(getCurrentMethodName() + " Comparing : " + value + " to " + nodeWalk.getData() + " Going right");
					nodeWalk = nodeWalk.getRightSubTree();

                    if(nodeWalk == null)
                    {
                        logger.debug(getCurrentMethodName() + " No node returned- inserting right...");
                        parent.setRightSubTree(new Node(value));
                    }
				}
			}
			logger.debug(getCurrentMethodName() + " Location of new node found for insert.");
		}
        logger.trace(getCurrentMethodName() + " Exiting ");
	}
	
    /*---------------------------------------------------------------------
    |  Method  createNode()
    |
    |  Author: sgyoung
    |  
    |  Purpose:  Returns a new node for inserts
    |
    |  Pre-condition:  N/A
    |
    |
    |  Post-condition: new node is returned
    |
    |  Parameters: data
    |
    |  Returns: 
    *-------------------------------------------------------------------*/
    public <T extends Comparable<T>>  BinaryDS.Node createNode(T data)
    {
        logger.trace(getCurrentMethodName() + " Entering ");
        Node temp = new Node(data);
        logger.debug(getCurrentMethodName() + " Returning new node : " + temp );
        logger.trace(getCurrentMethodName() + " Exiting ");
        return temp;
    }

    /*---------------------------------------------------------------------
    |  Method  addBST(ref root <pointer>, val new <pointer>)
    |
    |  Author: sgyoung
    |  
    |  Purpose:  Insert node containing new data into BST using recursion
    |
    |  Pre-condition:  - root is the address of the current node in the BST
    |                  - new is the adddress of the node containing data to
                        be inserted
    |
    |  Post-condition: new node is inserted into the tree
    |
    |  Parameters: root, new
    |
    |  Returns: null
    *-------------------------------------------------------------------*/
    public <T extends Comparable<T>> void addBST(Node root, Node newNode)
    {
        logger.trace(getCurrentMethodName() + " Entering ");

        if(this.rootNode == null )
		{
			logger.debug(getCurrentMethodName() + "Root node is null. Setting to root node.");

            this.rootNode = newNode;

            logger.trace(getCurrentMethodName() + " Exiting ");
		}

        else
        {
            T newValue = (T) newNode.getData();

            //LOCATE NULL SUBTREE FOR INSERTION
            if(newValue.compareTo((T)  root.getData()) < 0)
            {
                logger.debug(getCurrentMethodName() + " Comparing : " + newValue + " to " + root.getData() + " Going left");
                if(root.getLeftSubTree() == null)
                {
                    root.setLeftSubTree(new Node(newNode.getData()));
                }
                else
                {
                    addBST(root.getLeftSubTree(), newNode);
                }
            }
            else
            {
                logger.debug(getCurrentMethodName() + " Comparing : " + newValue + " to " + root.getData() + " Going right");
                if(root.getRightSubTree() == null)
                {
                    root.setRightSubTree(new Node(newNode.getData()));
                }
                else
                {
                    addBST(root.getRightSubTree(), newNode);
                }
            }
        }
        
        logger.trace(getCurrentMethodName() + " Exiting ");
    }

    /*---------------------------------------------------------------------
    |  Method  deleteBST(ref node, val data, ref previousNode)
    |
    |  Author: sgyoung
    |  
    |  Purpose:  This algorithm deletes a node from a BST.
    |
    |  Pre-condition:  - root is a tree containing data to be deleted
    |                  - data is the value of the data to be deleted
    |
    |  Post-condition: - node deleted and memory recycled (garbage collected)
                       - if data is not found, tree is unchanged.
    |
    |  Parameters: root, new
    |
    |  Returns: true if deleted, false if not found.
    *-------------------------------------------------------------------*/
    public <T extends Comparable<T>> boolean deleteBST(Node root, T value)
    {
        logger.trace(getCurrentMethodName() + " Entering ");

        // pointer to store the parent of the current node
        Node parent = null;
 
        // start with the root node
        Node curr = root;

        // search key in the BST and set its parent pointer
        while (curr != null && curr.data != value)
        {
            // update the parent to the current node
            parent = curr;
         
            // if the given key is less than the current node, go to the left subtree;
            // otherwise, go to the right subtree
            if(value.compareTo((T) curr.getData()) < 0) {
                logger.debug(getCurrentMethodName() + " Comparing " + value +  " to " + curr.getData() + " going left.");
                curr = curr.getLeftSubTree();
            }
            else {
                logger.debug(getCurrentMethodName() + " Comparing " + value +  " to " + curr.getData() + " going right.");
                curr = curr.getRightSubTree();
            }
        }
        // return if the key is not found in the tree
        if (curr == null) {
            logger.info(getCurrentMethodName() + " Couldn't find " + value + " to delete in tree. Returning False.");
            return false;
        }
        // Case 1: node to be deleted has no children, i.e., it is a leaf node
        if (curr.getLeftSubTree() == null && curr.getRightSubTree() == null)
        {
            logger.info(getCurrentMethodName() + " Node has no childeren. Deleteing the node with value : " + value);

            // if the node to be deleted is not a root node, then set its
            // parent left/right child to null
            if (curr != root)
            {
                logger.info(getCurrentMethodName() + " Node is not the root node, thus at the bottom of the tree. Removing node with value : " + value);
                if (parent.getLeftSubTree() == curr) {
                    parent.setLeftSubTree(null);
                }
                else {
                    parent.setRightSubTree(null); 
                }
            }
            // if the tree has only a root node, set it to null
            else {
                logger.info(getCurrentMethodName() + " Node is a root node. Removing the root node, thus no data in the tree.");
                root = null;
            }
        }

        // Case 2: node to be deleted has two children
        else if (curr.getLeftSubTree() != null && curr.getRightSubTree() != null)
        {
            logger.info(getCurrentMethodName() + " Node has two childeren.");

            // find its inorder successor node
            //Node successor = getMinimumKey(curr.getRightSubTree());
            Node successor = (BinaryDS.Node) findSmallestBST(curr.getRightSubTree());
         
            // store successor value
            T val = (T) successor.getData();
         
            // recursively delete the successor. Note that the successor
            // will have at most one child (right child)
            deleteBST(root, (T) successor.getData());
         
            // copy value of the successor to the current node
            curr.data = val;
        }

        // Case 3: node to be deleted has only one child
        else {
            // choose a child node
            Node child = (curr.getLeftSubTree() != null)? curr.getLeftSubTree(): curr.getRightSubTree();
         
            // if the node to be deleted is not a root node, set its parent
            // to its child
            if (curr != root)
            {
                if (curr == parent.getLeftSubTree()) {
                    parent.setLeftSubTree(child); 
                }
                else {
                    parent.setRightSubTree(child);
                }
            }
            // if the node to be deleted is a root node, then set the root to the child
            else {
                root = child;
            }
        }
        logger.trace(getCurrentMethodName() + " Exiting ");
        return true;
    }

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
			leftSubTree = null;
			rightSubTree = null;
			logger.trace(getCurrentMethodName() + " Exiting ");
		}
		
		public Node getLeftSubTree() {
            logger.trace(getCurrentMethodName() + " Entering ");
            logger.debug(getCurrentMethodName() + " Returning Left Tree Node : " + leftSubTree);
            logger.trace(getCurrentMethodName() + " Exiting ");
			return leftSubTree;
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
			return rightSubTree;
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
			return data;
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

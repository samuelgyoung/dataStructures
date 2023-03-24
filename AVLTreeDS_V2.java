import org.apache.log4j.Logger;

   /*
	 * AVL Tree Notes:
	 * 	- Efficiency : O(log2n)
     *  - Definition : Binary tree that is either empty or consists of two AVL subtrees. 
     *      (TL and TR) whoes heighs differ by no more than 1. | HL - HR | <= 1
     *  - LH = Left High (-1): EH = Even High (0): RH = Right High (1)
     *  - When a something is inserted/deleted, the tree may become unbalanced and we can rotate
     *      left or right to balance the tree
     * 
     * https://www.scaler.com/topics/data-structures/avl-tree/
	 */ 

class Node 
{
    int data, height;

    Node left_child, right_child;

    Node(int val)
    {
        data = val;
        height = 0;
    }
}

public class AVLTreeDS_V2 //extends BinaryDS
{
    final static Logger logger = Logger.getLogger(AVLTreeDS.class);

    Node node;

    Node rootNode; 

    AVLTreeDS_V2()
    {
        logger.info(getCurrentMethodName() + " Creating AVL Tree. Setting root to null.");
        logger.info(getCurrentMethodName() + " Big O notation is O(log2n) vs O(n) from standard binary tree.");
    }

    int get_height(Node root)
    {
        logger.trace(getCurrentMethodName() + " Entering ");

        if(root == null)
        {
            logger.debug(getCurrentMethodName() + "The node passed in is null, can't get height. Returning null");
            return -1;
        }

        logger.debug(getCurrentMethodName() + " Returning height of node " + root + " as " + root.height);

        return root.height;
    }

    int get_balance_factor(Node root)
    {
        logger.trace(getCurrentMethodName() + " Entering ");

        if(root == null)
        {
            logger.debug(getCurrentMethodName() + " The node passed in is null. Returning 0");
            return 0;
        }

        int balanceFactor = get_height(root.left_child) - get_height(root.right_child);
        logger.debug(getCurrentMethodName() + " Returning Balance Factor for node  " + root + " as " + balanceFactor);
        logger.trace(getCurrentMethodName() + " Exiting ");

        return (balanceFactor);
    }

        // Clockwise Rotation (LL)
        Node LL_rotation(Node root){
            Node child = root.left_child;
            root.left_child = child.right_child;
            child.right_child = root;
            root.height = Math.max(get_height(root.left_child), get_height(root.right_child)) + 1;
            child.height = Math.max(get_height(child.left_child), get_height(child.right_child)) + 1;
            return child;
        }
    
        // Anti-Clockwise Rotation (RL)
        Node RR_rotation(Node root){
            Node child = root.right_child;
            root.right_child = child.left_child;
            child.left_child = root;
            root.height = Math.max(get_height(root.left_child), get_height(root.right_child)) + 1;
            child.height = Math.max(get_height(child.left_child), get_height(child.right_child)) + 1;
            return child;
        }

            // Pre-order traversal of the tree
    void pre_order(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            pre_order(root.left_child);
            pre_order(root.right_child);
        }
    }

   public  Node getRootNode()
    {
        return this.rootNode;
    }


        // AVL Insertion
        Node insert(Node root, int val){

            if(rootNode == null)
            {
                this.rootNode = new Node(val);
                return this.rootNode;
            }

            // BST Insertion Logic
            if (root == null)
                return (new Node(val));
            if (val < root.data)
                root.left_child = insert(root.left_child, val);
            else if (val > root.data)
                root.right_child = insert(root.right_child, val);
            else
                return node;
    
            // Balance Factor check
            root.height = Math.max(get_height(root.left_child), get_height(root.right_child)) + 1;
            int b = get_balance_factor(root);
    
            // Checking if the node insertion results in Left heavy or Right heavy nodes.
            if(b > 1){
                // LL Rotation Case
                if(get_balance_factor(root.left_child) == 1){
                    root = LL_rotation(root);
                }
                // LR Rotation Case
                else{
                    root.left_child = RR_rotation(root.left_child);
                    root = LL_rotation(root);
                }
            }
            else if(b < -1){
                // RR Rotation Case
                if(get_balance_factor(root.right_child) == -1){
                    root = RR_rotation(root);
                }
                // RL Rotation Case
                else{
                    root.right_child = LL_rotation(root.right_child);
                    root = RR_rotation(root);
                }
            }
            return root;
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


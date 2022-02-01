import org.apache.log4j.Logger;

public class dataStructures
{
    final static Logger logger = Logger.getLogger(dataStructures.class);
    private static BinaryDS.Node createNode;

    /*---------------------------------------------------------------------
    |  Method main
    |
    |  Author: sgyoung
    |  
    |  Purpose:  Used to test various data structures.
    |
    |  Pre-condition: None.
    |
    |  Post-condition: None.
    |
    |  Parameters: None.
    |
    |  Returns:  void
    | 	
    *-------------------------------------------------------------------*/
	public static void main(String[] args) 
	{
        logger.trace(getCurrentMethodName() + " Entering ");
		
		//testArray();
        //testLinkedList();
		//testStack();
		testQueue();
		//testRecursion();
        //testBinarySearchTree();

		logger.trace(getCurrentMethodName() + " Exiting ");

    }

    public static void testBinarySearchTree()
	{
        System.out.println("Starting Binary Search Tree testing...");

        //EXAMPLE CREATING A BINARY SEARCH TREE
        System.out.println("Creating a Binary Search Tree with no root value test...");
        BinaryDS BinaryDS_001 = new BinaryDS();
        System.out.println("Getting the root node of the tree with no value test (should be null)... " + BinaryDS_001.getRootNode());
        
        //CREATE ANOTHER TREE FOR INSERT TESTING...
        System.out.println("Creating a Binary Search Tree with root value 50 for testing...");
        BinaryDS BinaryDS_002 = new BinaryDS(500);
        System.out.println("Getting the root node of the tree with no value test (should be 500)... " + BinaryDS_002.getRootNode().getData());

        System.out.println("Starting insert (using while loop) testing with random numbers between 1 - 2000...");
        //USE THIS BLOCK TO CREATE LARGE TREES OF RANDOM VALUE
        for(int i=0;i<20;i++){
            int random = (int)(Math.random()* (2000 + 1));
            
            //INSERT RANDOM VALUE FROM FOR LOOP
            BinaryDS_002.insertBST(BinaryDS_002.getRootNode(), random);

            //DEBUG
            //System.out.println ("Inserting " + random);
        }
        
        //TESTING THE addBST METHOD THAT USES RECURSION TO INSERT INSTEAD OF FOR LOOPS IN THE INSERTBST FUNCTION
        //CREATE THE NEW NODES FOR INSERTION
        System.out.println("Testing insert with recusrion (adding 4,000) instead of while loop.");
        createNode = BinaryDS_002.createNode(4000);
        //INSERT THE NODES USING RECURSION
        System.out.println("Starting insert (using recursion) testing ...");
        BinaryDS_002.addBST(BinaryDS_002.getRootNode(), createNode);

        //SEARCHING FUNCTIONS
        System.out.println("Finding largest Value in tree test ... " + BinaryDS_002.findLargestBST(BinaryDS_002.getRootNode()));
        
        System.out.println("Finding smallest Value in tree test ... " + BinaryDS_002.findSmallestBST(BinaryDS_002.getRootNode()));

        //TRAVERSING FUNCTIONS (DEPTH FIRST TRAVERSALS)
        System.out.println("Post Order Test (left, right, node) test...");
        BinaryDS_002.postOrder(BinaryDS_002.getRootNode());

        System.out.println("Pre Order Test (node, left, right) test...");
        BinaryDS_002.preOrder(BinaryDS_002.getRootNode());

        System.out.println("In Order Test (left, node, right) test...");
        BinaryDS_002.inOrder(BinaryDS_002.getRootNode());

        //TRAVERSING FUNCTIONS (BREADTH FIRST TRAVERSALS)
        System.out.println("Breadth first traversal test...");
        BinaryDS_002.breadthFirst(BinaryDS_002.getRootNode());

        System.out.println("Starting Delete testing ...");
        for(int i=0;i<1000;i++){
            int random = (int)(Math.random()* (1000 + 1));
            
            //DELETE RANDOM VALUE FROM FOR LOOP
            BinaryDS_002.deleteBST(BinaryDS_002.getRootNode(), random);

            //DEBUG
            //System.out.println ("Deleting " + random);
        }

        System.out.println("Printing Tree...");
        BinaryDS_002.printBSTParen();

        System.out.println("Finished Binary Search Tree testing.");
    }

    public static void testRecursion()
	{
		Recursion Recursion_001 = new Recursion();
		System.out.println(Recursion_001.iterativeFactorial(5));
	}

    public static void testQueue()
	{
        System.out.println("Starting Queue testing...");

        System.out.println("Creating queue...");
		QueueDS QueueDS_001 = new QueueDS();
		QueueDS.Queue queue_001 = QueueDS_001.createQueue();

        System.out.println("Adding data to queue...");
		QueueDS_001.enqueue("Test1", queue_001);
		QueueDS_001.enqueue("Test2", queue_001);
		QueueDS_001.enqueue("Test3", queue_001);
		QueueDS_001.enqueue("Test4", queue_001);
		QueueDS_001.enqueue("Test5", queue_001);

        System.out.println("Printing queue...");
		QueueDS_001.printQueue(queue_001);

        System.out.println("Deleting value from queue...");
		QueueDS_001.dequeue(queue_001);

        System.out.println("Printing queue...");
		QueueDS_001.printQueue(queue_001);

        System.out.println("Printing front of queue...");
		System.out.println(QueueDS_001.queueFront(queue_001));

        System.out.println("Finished Queue testing.");

	}

     public static void testStack()
     {
        System.out.println("Starting Stack test...");

         StackDS Stack_001 = new StackDS();
         StackDS.Stack stack_001 = Stack_001.createStack();
         
         Stack_001.printStack(stack_001);
         Stack_001.pushStack("test1", stack_001);
         System.out.println("Data in stack: " + Stack_001.stackTop(stack_001));
         Stack_001.pushStack("test2", stack_001);
         System.out.println("Data in stack: " + Stack_001.stackTop(stack_001));
         Stack_001.printStack(stack_001);
         Stack_001.printStack(stack_001);
         Stack_001.popStack(stack_001);
         System.out.println("Data in stack: " + Stack_001.stackTop(stack_001));
         Stack_001.printStack(stack_001);
         Stack_001.pushStack("test2", stack_001);
         Stack_001.pushStack("test3", stack_001);
         Stack_001.printStack(stack_001);
         
         StackDS.Stack stack_002 = Stack_001.reverseStack(stack_001);
         Stack_001.printStack(stack_002);
         
         System.out.println("Finished Stack test.");
     }

     public static void testLinkedList()
     {
         System.out.println("Starting Linked List test...");

         LinkedListDS linkedList_001 = new LinkedListDS();
         linkedList_001.printLinkedList();
         
         linkedList_001.insertNode(3, null);
         linkedList_001.printLinkedList();
         
         linkedList_001.insertFirst(2);
         linkedList_001.printLinkedList();
         
         linkedList_001.insertFirst(1);
         linkedList_001.printLinkedList();
         
         linkedList_001.insertLast(4);
         linkedList_001.printLinkedList();
         
         linkedList_001.insertFirst(0);
         linkedList_001.printLinkedList();
         
         linkedList_001.deleteNode(linkedList_001.getNode(1));
         linkedList_001.printLinkedList();
         
         linkedList_001.deleteNode(linkedList_001.getNode(3));
         linkedList_001.printLinkedList();
         
         linkedList_001.deleteNode(linkedList_001.getNode(1));
         linkedList_001.printLinkedList();
         
         
         linkedList_001.insertFirst(0);
         linkedList_001.printLinkedList();
         
         linkedList_001.insertFirst(5);
         linkedList_001.printLinkedList();
         
         linkedList_001.insertFirst(12);
         linkedList_001.printLinkedList();
         
         
         linkedList_001.deleteNode(linkedList_001.getLast());
         
         linkedList_001.printLinkedList();
         
         linkedList_001.deleteNode(linkedList_001.getLast());
         linkedList_001.printLinkedList();
         
         linkedList_001.insertLast(55);
         linkedList_001.printLinkedList();
         
         linkedList_001.deleteLast();
         linkedList_001.printLinkedList();
         
         linkedList_001.deleteLast();
         linkedList_001.printLinkedList();
         
         linkedList_001.insertLast(55);
         linkedList_001.printLinkedList();
                 
         System.out.println("Checking for 12 : " + linkedList_001.searchList(12));
         System.out.println("Found in number of checks: " + linkedList_001.getSearchCount());
         
         linkedList_001.deleteLast();
         linkedList_001.deleteLast();
         linkedList_001.deleteLast();
         
         linkedList_001.printLinkedList();
         
         linkedList_001.insertFirst(0);
         linkedList_001.printLinkedList();
         linkedList_001.deleteLast();
         
         linkedList_001.insertLast(1);
         linkedList_001.printLinkedList();
         
         
         for (int i = 2; i < 10; i++)
         {
             linkedList_001.insertLast(i);
         }
         
         linkedList_001.printLinkedList();
         linkedList_001.deleteFirst();
         linkedList_001.printLinkedList();
         
         linkedList_001.addNode(0, null);
         linkedList_001.printLinkedList();
         
         linkedList_001.addNode(0, null);
         linkedList_001.printLinkedList();
         
         linkedList_001.addNode(10,linkedList_001.getLast());
         linkedList_001.printLinkedList();
         
         System.out.println("Finished Linked List test.");
     }
      
     public static void testArray()
     {
        System.out.println("Starting array test...");

         int arraySize = 5;
         
         logger.trace(getCurrentMethodName() + " Entering ");
         logger.debug(getCurrentMethodName() + " Creating empty array. ");
         ArrayDS arrayDS_001 = new ArrayDS();
         
         logger.debug(getCurrentMethodName() + " Creating array size of " + arraySize);
         ArrayDS arrayDS_002 = new ArrayDS(arraySize);
         
         logger.debug(getCurrentMethodName() + " Testing getArraySize()...");
         logger.debug(getCurrentMethodName() + " Array Size : " +  arrayDS_001.getArraySize());
         logger.debug(getCurrentMethodName() + " Array Size : " +  arrayDS_002.getArraySize());	
         
         logger.debug(getCurrentMethodName() + " Testing getArraySize()...");
         arrayDS_001.printArray();
         arrayDS_002.printArray();
         
         arrayDS_001.addToArray(1);
         arrayDS_001.addToArray(2);
         arrayDS_001.addToArray(3);
         arrayDS_001.addToArray(4);
         arrayDS_001.addToArray(5);
         arrayDS_001.addToArray(6);
         arrayDS_001.addToArray(7);
         arrayDS_001.addToArray(8);
         arrayDS_001.addToArray(9);
         arrayDS_001.addToArray(10);
         
         System.out.println("Searching for 2 using binary search... " + arrayDS_001.binarySearch(2, 0));
         
         System.out.println("Searching for 2 using orderedListSearch search... " + arrayDS_001.orderedListSearch(2, 0));
         
         System.out.println("arrayDS_001 is now sequential");	
         arrayDS_001.printArray();
         
         System.out.println("arrayDS_001 : Running sequential search...");
         System.out.println("Searching for for sequential search for 3... " + arrayDS_001.seqSearch(3, 0));
         System.out.println("Number of Searches : " + arrayDS_001.getSearchCount());
         System.out.println("Searching for sequential search for 6... " + arrayDS_001.seqSearch(6, 0));
         System.out.println("Number of Searches : " + arrayDS_001.getSearchCount());
         
         System.out.println("Searching for 4 using probabilitiy search... " + arrayDS_001.probabilitySearch(4, 0));
         System.out.println("Searching for 4 using probabilitiy search... " + arrayDS_001.probabilitySearch(4, 0));
         System.out.println("Searching for 3 using probabilitiy search... " + arrayDS_001.probabilitySearch(3, 0));
         System.out.println("Searching for 4 using probabilitiy search... " + arrayDS_001.probabilitySearch(4, 0));
         System.out.println("Searching for 'test' using probabilitiy search... " + arrayDS_001.probabilitySearch("test", 0));
         
         arrayDS_001.printArray();
         
         System.out.println("Finished array test.");
     }

     private static String getCurrentMethodName() 
 	{ 
 		StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace(); 
 		
 		return 	stackTraceElements[1].toString().replaceFirst(stackTraceElements[1].toString().split("\\.")[0]+"\\.", "");
 	}
}
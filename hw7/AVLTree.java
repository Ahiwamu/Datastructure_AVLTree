public class AVLTree extends BTreePrinter{
    Node root;

    public AVLTree(Node root){
        this.root = root;
        root.parent = null; // Clear parent of the root (Important for spliting)
    }

        public void singleRotateFromLeft(Node y) {
            Node x = y.left;    //set x is left of y
            
            if(this.root == y){
                y.left = x.right;
                if(y.left != null){
                    y.left.parent = y;
                }
                x.right = y;    //rotation step 1   
                y.parent = x;   //rotation step 2

                this.root = x;  //update root
                x.parent = null;
            }
            else{
                Node parentY = y.parent;    //set parentY is parent of y
                y.left = x.right;   //transfer inner branch
                if(y.left != null){
                    y.left.parent = y;
                }
                x.right = y;    //rotation step 1
                y.parent = x;   //rotation step 2
                if(parentY.left == y){  //check y is left or right node
                    parentY.left = x;
                }
                else{
                    parentY.right = x;
                }
                x.parent = parentY;    
            }   
        }

        public void singleRotateFromRight(Node y) {
            Node x = y.right;   //set x is right of y

            if(this.root == y){   //if y is root
                y.right = x.left;   //transfer branch
                if(y.right != null){
                    y.right.parent = y;
                }
                x.left = y; //rotation step 1
                y.parent = x;   //rotation step 2

                this.root = x;  //update root
                x.parent = null;
            }
            else{
                Node parentY = y.parent;    //set parentY is parent of y
                y.right = x.left;   //transfer branch
                if(y.right != null){
                    y.right.parent = y;
                }
                x.left = y; //rotation step 1
                y.parent = x;   //rotation step 2

                if(parentY.left == y){  //chech y is left or right node
                    parentY.left = x;
                }
                else{
                    parentY.right = x;
                }

                x.parent = parentY;

            }
        }
        
        public void doubleRotateFromLeft(Node y) {
            Node x = y.left;    //set x is left of y
            Node z = x.right;   //set z is right of x
            
            if(this.root == y){
                //singleRotateFromLeft x
                x.right = z.left;   //transfer branch
                if(x.right != null){
                    x.right.parent = x;
                }
                z.left = x; //rotation step 1
                x.parent = z;   //rotation step 2
                y.left = z;
                z.parent = y;

                //singleRotateFormRight z
                y.left = z.right;
                if(y.left != null){
                    y.left.parent = y;
                }
                z.right = y;
                y.parent = z;

                this.root = z;
                z.parent = null;
            }
            else{
                Node parentY = y.parent;    //set parentY is parent of y
                //singleRotateFromLeft x
                x.right = z.left;   //transfer branch
                if(x.right != null){
                    x.right.parent = x;
                }
                z.left = x; //rotation step 1
                x.parent = z;   //rotation step 2
                y.left = z;
                z.parent = y;

                //singleRotateFormRight z
                y.left = z.right;
                if(y.left != null){
                    y.left.parent = y;
                }
                if(parentY.left == y){  //check y is left or right node
                    parentY.left = z;
                }
                else{
                    parentY.right = z;
                }
                z.parent = parentY;
                z.right = y;
                y.parent = z;
            }

        }

        public void doubleRotateFromRight(Node y) {
            Node x = y.right;    //set x is right of y
            Node z = x.left;   //set z is left of x
            
            if(this.root == y){
                //singlerotateFromRight x
                x.left = z.right;
                if(x.left != null){
                    x.left.parent = x;
                }
                z.right = x;
                x.parent = z;
                y.right = z;
                z.parent = y;

                //singlerotateFromLeft z
                y.right = z.left;
                if(y.right != null){
                    y.right.parent = y;
                }
                z.left = y;
                y.parent = z;

                this.root = z;  //update root
                z.parent = null;
            }
            else{
                Node parentY = y.parent;    //set parentY is parent of y

                //singlerotateFromRight x
                x.left = z.right;
                if(x.left != null){
                    x.left.parent = x;
                }
                z.right = x;
                x.parent = z;
                y.right = z;
                z.parent = y;
                
                //singlerotateFromLeft z
                y.right = z.left;
                if(y.right != null){    
                    y.right.parent = y;
                }
                if(parentY.right == y){ //check y is left or right node
                    parentY.right = z;
                }
                else{
                    parentY.left = z;
                }
                z.parent = parentY;
                z.left = y;
                y.parent = z;
                
            }
        }
        

        public static void rebalance(AVLTree tree, Node node) {
            int balanceFactor = height(node.left) - height(node.right);              // Calculate balanceFactor
            if (balanceFactor >= 2 || balanceFactor <= -2){                          // Use balanceFactor to check if unbalanced?
                if (balanceFactor > 0){                      // Use balanceFactor to check if left heavy?
                    if (height(node.left.left) < height(node.left.right)){                  // Use the grandchild to check if Outer or Inner?
                        System.out.println("Perform DoubleRotationFromLeft(Node " + node.key + ")");
                        tree.doubleRotateFromLeft(node);
                    }else{
                        System.out.println("Perform SingleRotationFromLeft(Node " + node.key + ")");
                        tree.singleRotateFromLeft(node);
                    }
                }else{
                    if (height(node.right.right) > height(node.right.left)){                  // Use the grandchild to check if Outer or Inner?
                        System.out.println("Perform SingleRotationFromRight(Node " + node.key +")");   // Fix this and call a function
                        tree.singleRotateFromRight(node);
                    }else{
                        System.out.println("Perform DoubleRotationFromRight(Node " + node.key +")");   // Fix this and call a function
                        tree.doubleRotateFromRight(node);
                    }
                }
            }
            if(node.parent != null) rebalance(tree, node.parent);
        }
    
    // This function is complete, no need to edit
    public void insertKey(int key) {
        if (root == null) {
            root = new Node(key);
        } else {
            insertKey(this, root, key);
        }
    }
    
    // Fix this function to have the rebalancing feature
    // There should be rebalance() function calling somewhere in the code
    public static void insertKey(AVLTree tree, Node node, int key) {
        if (key == node.key) {
            System.out.println("Duplicated key:" + key);
        }else if (key < node.key) {//Go left
            if (node.left == null) {
                node.left = new Node(key);
                node.left.parent = node;
            }else {
                insertKey(tree, node.left, key);
            }
        }else{  // Go right
            if (node.right == null) {
                node.right = new Node(key);
                node.right.parent = node;
            }else {
                insertKey(tree, node.right, key);
            }
        }
        rebalance(tree, node);
    }
    

    // This function is for deleting the root node
    // If the node is not the root, please call the recursive version
    public void deleteKey(int key) {
        // Pls copy the code from the previous homework
        Node found = findKey(key);
        if(root == null) System.out.println("Empty Tree!!!"); //if tree is empty
        else if(found == null) System.out.println("Key not found!!!"); //if key not found
        else{  
            deleteKey(this, found);
        }
    }
    
    // Use this function to delete non-root nodes
    // Also, fix the code to have the rebalancing feature
    public static void deleteKey(AVLTree tree, Node node) {
        // Pls copy the code from the previous homework
        // Add code segments to enable the rebalancing feature
        if(tree.root == node){  //if delete root node
            if(tree.root.right == null && tree.root.left == null){  //if don't have child
                tree.root.parent = null;
                tree.root = null;
            }
            else if(tree.root.right != null && tree.root.left == null){ //if have right node
                Node MinRight = findMin(tree.root.right);
                tree.root.key = MinRight.key;
                deleteKey(tree, MinRight);
            }
            else if(tree.root.right == null && tree.root.left != null){ //if have left node
                tree.root = tree.root.left;
                tree.root.parent = null;
            }
            else{   //if have right and left node
                Node MinRight = findMin(tree.root.right);
                tree.root.key = MinRight.key;
                deleteKey(tree, MinRight);
            }
            rebalance(tree, node);
        }
        else{   
            Node parent = node.parent;
            
            if(node.left == null && node.right == null){    //if it leaf node
                if(parent.left == node){
                    node.parent = null;
                    parent.left = null;
                }
                else {
                    node.parent = null;
                    parent.right = null;
                } 
            }
            else if(node.left != null && node.right == null){   //if it have left node
                if(parent.left == node) {
                    parent.left = node.left;
                    if(node.left != null) node.left.parent = parent;
                }
                else{
                    parent.right = node.left;
                    if(node.left != null) node.left.parent = parent;
                }
            }
            else if(node.left == null && node.right != null){   //if it have right node
                Node MinRight = findMin(node.right);
                node.key = MinRight.key;
                deleteKey(tree, MinRight);
            }
            else{   //if it have left and right node
                Node MinRight = findMin(node.right);
                node.key = MinRight.key;
                deleteKey(tree, MinRight);
            }
            rebalance(tree, parent);
        }
    }
    
    public Node findKey(int search_key) {
        // Pls copy the code from the previous problem
        return findKey(root, search_key);   //use findKey by recursive
    }

    public static Node findKey(Node node, int search_key) {
        // Pls copy the code from the previous problem
        if(node == null) return null; //if node is null return null
        if(node.key == search_key) return node; // if key is node 
        else if(search_key < node.key && node.left != null) return findKey(node.left, search_key);    //if key is less than node go to left node
        else if(search_key > node.key && node.right != null) return findKey(node.right, search_key);  //if key is more than node go to right node
        return null;
    }
    
    // This function is complete, no need to edit
    public static Node findMin(Node node) {
        // Pls copy the code from the previous problem
        if(node.left != null) return findMin(node.left);  //if left node isn't null go to left node
        return node;  //if this node is min node return it
    }
    
    public static Node findMax(Node node) {
        // Pls copy the code from the previous problem
        if(node.right != null) return findMax(node.right);    //if right node isn't null go to right node
        return node;  //if this node is max node return it
    }
    
    public static boolean isMergeable(Node r1, Node r2) {
        if(r1 == null || r2 == null) return true;   //if is null

        Node MaxR1 = findMax(r1);   //find max of r1
        Node MinR2 = findMin(r2);   //find min of r2
        
        if(MaxR1.key >= MinR2.key){ //if it can't merge
            return false;
        }
        else{
            return true;
        }
        
    }

    public static Node mergeWithRoot(Node r1, Node r2, Node t){
        if (isMergeable(r1, r2)) {
            // Fix this
            if(Math.abs(height(r1)-height(r2)) <= 1){ // same height
                t.left = r1; // set t's left child to r1
                if(r1 != null) r1.parent = t; // set r1's parent to t
                t.right = r2; // set t's right child to r2
                if(r2 != null) r2.parent = t; // set r2's parent to t
                return t;
            } else if(height(r1) > height(r2)){
                Node r = mergeWithRoot(r1.right, r2, t); // recursive
                r1.right = r; // set r1's right child to r(result of recursive)
                r.parent = r1; // set r's(result of recursive) parent to r1
                AVLTree tree = new AVLTree(r1); // create new AVLTree which root is r1 
                rebalance(tree , r1);
                return tree.root;
            } else{ // 
                Node r = mergeWithRoot(r1, r2.left, t); // recursive
                r2.left = r; // set r2's left child to r(result of recursive)
                r.parent = r2; // set r's(result of recursive) parent to r2
                AVLTree tree = new AVLTree(r2); // create new AVLTree which root is r1 
                rebalance(tree , r2); 
                return tree.root; 
            }
        } else {
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
            return null;
        }
    }


    
    public void merge(AVLTree tree2) {
        if (isMergeable(this.root, tree2.root)){
            // Do something
            Node t = findMax(this.root); // set node t (new root) to largest element of T1
            this.deleteKey(t.key); // remove largest element from T1 
            t = mergeWithRoot(this.root, tree2.root, t); // mergeWithRoot
            this.root = t; // set this tree root to t
        }else{
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
        }

    }
    
    // Fix this function
    public Node[] split(int key) {
        return split(this.root, key);
    }
    
    // Fix this function
    public static Node[] split(Node r, int key) { 
        Node[] arr = new Node[2];
        AVLTree tree = new AVLTree(r);
        // Do something
        if(r != null){  //if r is not null
            if(key < r.key){    //go to split on left
                // Node temp[] = split(r.left, key);   //keep split 
                // r.left = temp[1];   //left node is root on previous split
                // arr[0] = temp[0];   //root on current split is root on previous split
                // rebalance(tree, r); //rebalance
                // arr[1] = tree.root;  
                Node[] temp = split(r.left, key);   //keep split 
                r.left = temp[1];   //left node is root on previous split
                if (r.left != null) r.left.parent = r;
                rebalance(tree, r);
                arr[0] = temp[0];   //root on current split is root on previous split
                arr[1] = tree.root;
            }
            else if(key > r.key){   //go to split on right and do it same go to on left
                // Node temp[] = split(r.right, key);  
                // r.right = temp[0];
                // arr[1] = temp[1];
                // rebalance(tree, r);
                // arr[0] = tree.root;
                Node[] temp = split(r.right, key);
                r.right = temp[0];
                if (r.right != null) r.right.parent = r;
                rebalance(tree, r);
                arr[0] = tree.root;
                arr[1] = temp[1];
            }
            else{   //if find key
                arr[1] = r.right;
                Node t = mergeWithRoot(r.left, null, r);
                rebalance(tree, t);
                arr[0] = tree.root;
            }
        }
        else{
            arr[0] = null;
            arr[1] = null;
        }

        return arr;
    }
    
    // Use this function to check the node height
    // This function is complete, no need to edit
    public static int height(Node node) {
        if (node == null)
            return -1;
        else
            return 1 + Math.max(height(node.left), height(node.right));
    }
    
    // This function is complete, no need to edit
    public void printTree() {
        if (root == null) {
            System.out.println("Empty tree!!!");
        } else {
            super.printTree(root);
        }
    }

    public static void printNode(Node node){
        if(node == null) {
            System.out.println("Node not found!!!");
        }
        else{
            System.out.println(node.key);
        }
    }
    
    public AVLTree() {} // Dummy Constructor, no need to edit
}
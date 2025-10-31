public class BSTree extends BTreePrinter{
    Node root;
      
        public void singleRotateFromLeft(Node y) {
            Node x = y.left;    //set x is left of y
            
            if(y.parent == null){
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

            if(y.parent == null){   //if y is root
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
            
            if(y.parent == null){
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
            
            if(y.parent == null){
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
   
    public Node findKey(int search_key) {
        // Pls copy the code from the previous homework
        return findKey(root, search_key);   //use findKey by recursive
    }
    
    public static Node findKey(Node node, int search_key) {
        // Pls copy the code from the previous homework
        if(node == null) return null; //if node is null return null
        if(node.key == search_key) return node; // if key is node 
        else if(search_key < node.key && node.left != null) return findKey(node.left, search_key);    //if key is less than node go to left node
        else if(search_key > node.key && node.right != null) return findKey(node.right, search_key);  //if key is more than node go to right node
        return null;
    }

    public static Node findMin(Node node) {
        // Pls copy the code from the previous homework
        if(node.left != null) return findMin(node.left);  //if left node isn't null go to left node
        return node;  //if this node is min node return it
    }

    public static Node findMax(Node node) {
        // Pls copy the code from the previous homework
        if(node.right != null) return findMax(node.right);    //if right node isn't null go to right node
        return node;  //if this node is max node return it
    }

    public void insertKey(int key) {
        // Pls copy the code from the previous homework
        if (root == null) { //if tree is empty
            root = new Node(key);
        } else {    //if tree isn't empty
            insertKey(root, key);
        }
    }

    public static void insertKey(Node node, int key) {
        // Pls copy the code from the previous homework
        if (key == node.key) {  //if have this node in tree
            System.out.println("Duplicated key:" + key);
        }else if (key < node.key) {//Go left
            if (node.left == null) {
                node.left = new Node(key);
                node.left.parent = node;
            }else {
                insertKey(node.left, key);
            }
        }else{  // Go right
            if (node.right == null) {
                node.right = new Node(key);
                node.right.parent = node;
            }else {
                insertKey(node.right, key);
            }
        }
    }
    
    public void deleteKey(int key) {
        // Pls copy the code from the previous homework
        Node found = findKey(key);
      if(root == null) System.out.println("Empty Tree!!!"); //if tree is empty
      else if(found == null) System.out.println("Key not found!!!");    //if key not found
      else if(root.key == key){  //if key is root
          if(root.left == null && root.right == null) root = null;  //if root don't have child remove root
          else if(root.left != null && root.right == null) root = root.left; //if root have left node take left node to root
          else if(root.right != null && root.left == null) root = root.right;    //if root have right node take right node to root
          else if(root.right != null && root.left != null){ //if root have left and right node
            Node cur = findMin(root.right); //set cur is min of right node
            deleteKey(cur); //delete cur
            Node right = root.right;    //set right is right node of root
            Node left = root.left;  //set left is left node of root
            root = cur; //set root is cur 
            cur.right = right;  //set right of cur is right
            cur.left = left;    //set left of cur is left
          }
      }
      
      else{ //if key isn't root
        Node cur = findKey(key);    
        deleteKey(cur);
      }
    }
    
    public static void deleteKey(Node node) {
        // Pls copy the code from the previous homework
        if(node.left == null && node.right == null){  //if it is leaf node
        Node parent = node.parent; //set parent is parent of node
        if(parent.left == node) parent.left = null;   //if node is left of parent
        else parent.right = null;   //if node is right of parent
      }
      else if(node.left == null && node.right != null){ //if it have right node
        Node parent = node.parent;  //set parent is parent of node
        if(parent.left == node){    //if node is left of parent 
            parent.left = node.right;   
        }
        else{   //if node is right of parent
            parent.right = node.right;
            
        }
        node.right.parent = parent;
        // rebalance(tree, parent);
      }
      else if(node.left != null && node.right == null){ //if it have left node
        Node parent = node.parent;  //set parent is parent of node 
        if(parent.left == node){    //if node is left of parent
            parent.left = node.left;
        }
        else{   //if node is right of parent
            parent.right = node.left;
        }
        node.left.parent = parent;
      }
      else if(node.left != null && node.right != null){ //if it have left and right node
        Node found = findMin(node.right);   //set found is min of right node
        node.key = found.key; //set key node is found node 
        deleteKey(found);   //delete node found 
      }
    }
    
    public static boolean isMergeable(Node r1, Node r2) {
        Node MaxR1 = findMax(r1);   //find max of r1
        Node MinR2 = findMin(r2);   //find min of r2
        if(MaxR1.key < MinR2.key){ //if all r1 less than all r2
            return true;
        }
        else{
            return false;
        }

    }

    public static Node mergeWithRoot(Node r1, Node r2, Node t) {
        if (isMergeable(r1, r2)) {  //check it can merge?
            // Fix this
            t.left = r1;    //set r1 is left of t
            t.right = r2;   //set r2 is right of t
            r1.parent = t;  //set parent of r1 is t
            r2.parent = t;  //set parent of r2 is t
            return t;
        } else {
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
            return null;
        }
    }
    
    public void merge(BSTree tree2) {
        if (isMergeable(this.root, tree2.root)){    //check it can merge
            Node MaxT1 = findMax(this.root);    //find max of this root
            Node t; //create node t is root of new tree
            t = MaxT1;
            deleteKey(MaxT1);
            t.left = this.root;
            t.right = tree2.root;
            this.root = t;
        }else{
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
        }
    }

    // This function is complete, no need to edit
    public void printTree() {
        if (root == null) {
            System.out.println("Empty tree!!!");
        } else {
            super.printTree(root);
        }
    }
}
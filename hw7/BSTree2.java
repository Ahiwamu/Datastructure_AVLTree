public class BSTree2 extends BTreePrinter{
    Node root;
    
    // Implement this function using iterative method
    // Do not use recursion
    public Node findKey(int search_key) {
        Node cur = this.root;   //set cur is root node
        while(cur != null){ //go to in tree
            if(search_key < cur.key){   //go to left
                cur = cur.left;
            }
            else if(search_key > cur.key){  //go to right
                cur = cur.right;
            }
            else if(search_key == cur.key){ //if found it
                return cur;
            }
            else{   //if not found
                return null;
            }
        }
        return cur; //if tree is empty or it not found
    }

    // Implement this function using iterative method
    // Do not use recursion
    public Node findMin(Node node) {
        Node cur = node;   //set cur is node
        while(cur != null){ //go to in tree
            if(cur.left != null){   //go to min node
                cur = cur.left;
            }
            else{   //if it is min node
                return cur;
            }
        }
        return null;
    }

    // Implement this function using iterative method
    // Do not use recursion
    public Node findMax() {
       Node cur = this.root;   //set cur is root node
        while(cur != null){ //go to in tree
            if(cur.right != null){   //go to max node
                cur = cur.right;
            }
            else{   //if it is max node
                return cur;
            }
        }
        return null;
    }
    
    // Implement this function using iterative method
    // Do not use recursion
    public void insertKey(int key) {
        Node cur = this.root; //set cur is root
        if(cur == null){    //if tree is empty
            this.root = new Node(key);
        }
        else{   //if tree isn't empty
            while(cur != null){ //go to tree
                if(key < cur.key){  //if key less than cur node
                    if(cur.left != null){   //go to left
                        cur = cur.left;
                    }
                    else{   //if left is null insert new node
                        cur.left = new Node(key);
                    }
                }
                else if(key > cur.key){ //if key more than cur node
                    if(cur.right != null){  //go to right node
                        cur = cur.right;
                    }
                    else{   //if right is null insert new node
                        cur.right = new Node(key);
                    }
                }
                else{   //if found same key stop it
                    return;
                }
            }
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
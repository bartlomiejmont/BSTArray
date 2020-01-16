package com.company;

public class Main {

    static int tabSize = 50;
    static int bstTab[] = new int[tabSize];
    static int maxIndex = 0;

    public static void main(String[] args) {

        addNode(5);
        addNode(10);
        addNode(14);
        addNode(3);
        addNode(2);
        addNode(4);
        addNode(7);
//        System.out.println(parentIndex(findIndex(7)) + "\n-----------");

        printTree();

        delate(4);
        System.out.println(" ");

        printTree();

//        for (int x:bstTab) {
//            System.out.println(x);
//        }
//
    }

    public static void addNode(int node){

        int i = 0;
        if(Main.bstTab[0]==0){
            Main.bstTab[0]=node;
        }
        else {
            while(Main.bstTab[i]!=0){
                //going left
                if(Main.bstTab[i]>node){
                    i=leftIndex(i);
                }
                //going right
                else{
                    i=rightIndex(i);
                }
            }
            Main.bstTab[i] = node;
            Main.maxIndex = i;
        }
    }

    public static int findIndex(int node){

        int i = 0;
        while(Main.bstTab[i]!=node){
            //node does't exists
            if(Main.bstTab[i]==0){
                System.out.println("node does't exists");
                break;
            }
            //going left
            if(Main.bstTab[i]>node){
                i=leftIndex(i);
            }
            //going right
            else{
                i=rightIndex(i);
            }
        }
        return i;
    }

    public static void printTree()
    {
        printTreeRec(0);
    }

    // A utility function to do inorder traversal of BST
    public static void printTreeRec(int parent)
    {
        if (Main.bstTab[parent] != 0)
        {
            printTreeRec(leftIndex(parent));
            System.out.print(Main.bstTab[parent] + " ");
            printTreeRec(rightIndex(parent));
        }
    }

    public static int minValue(int x)
    {
        int index = findIndex(x);
        while (Main.bstTab[leftIndex(index)] != 0){
            x = Main.bstTab[leftIndex(index)];
            index = findIndex(x);
        }
        return x;
    }

    public static void transplant(int u, int v){
        int uIndex = findIndex(u);
        int vIndex = findIndex(v);

        if(Main.bstTab[parentIndex(uIndex)] == 0){
            Main.bstTab[uIndex]=Main.bstTab[0];
        }
        else if(u == Main.bstTab[leftIndex(parentIndex(uIndex))]){
            Main.bstTab[leftIndex(parentIndex(uIndex))] = v;
        }
        else {
            Main.bstTab[rightIndex(parentIndex(uIndex))] = v;
        }
        if(v != 0){
            Main.bstTab[(parentIndex(vIndex))] = Main.bstTab[(parentIndex(uIndex))];
        }

    }

    public static void delate(int toDelate){
        int toDelateIndex = findIndex(toDelate);

        if(Main.bstTab[leftIndex(toDelateIndex)] == 0){
            transplant(toDelate, Main.bstTab[rightIndex(toDelateIndex)]);
        }
        else if(Main.bstTab[rightIndex(toDelateIndex)] == 0){
            transplant(toDelate, Main.bstTab[leftIndex(toDelateIndex)]);
        }
        else {
            int y = minValue(Main.bstTab[rightIndex(toDelateIndex)]);
            int yIndex = findIndex(y);
            if(Main.bstTab[parentIndex(yIndex)] != toDelate){
                transplant(y,Main.bstTab[rightIndex(yIndex)]);
                Main.bstTab[rightIndex(yIndex)] = Main.bstTab[rightIndex(toDelate)];
                Main.bstTab[parentIndex(rightIndex(yIndex))] = y;
            }
            transplant(toDelate,y);
            Main.bstTab[leftIndex(yIndex)] = Main.bstTab[leftIndex(toDelateIndex)];
            Main.bstTab[parentIndex(leftIndex(yIndex))] = y;
        }

    }

    public static int leftIndex(int parent){
        return (2*parent+1);
    }
    public static int rightIndex(int parent){
        return (2*parent+2);
    }
    public static int parentIndex(int child){
        if(child%2==0){
            return ((child-1)/2);
        }
        else {
            return(child/2);
        }
    }


}

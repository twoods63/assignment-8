package com.assignment8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Family {
    ArrayList<Person> family = new ArrayList<Person>();

    public void addPerson(String name, int parent1, int parent2) {
        //This method adds a person to the tree.
        family.add(new Person(name,parent1,parent2));
    }
    public int getIDFromName(String name) {
        //This method converts a name to an id
        for(Person x : family) {
            if(x.getName()==name) {
                return x.getID();
            }
        }
        return -1;
    }
    public String getNameFromID(int id) {
        //This method converts an id to a name.
        for(Person x : family) {
            if(x.getID()==id) {
                return x.getName();
            }
        }
        return "Unknown";
    }
    public String parentRelationship(String current) {
        //This method calculates the word for a parent, grandparent, great grandparent etc.
        if(current.equals("Self")) {
            return "Parent";
        }
        else if(current.equals("Parent")) {
            return "GrandParent";
        }
        else {
            return "Great "+current;
        }
    }
    public String childRelationship(String current) {
        //This method calculates the word for a child, grandchild, great grandchild etc.
        if(current.equals("Self")) {
            return "Child";
        }
        else if(current.equals("Child")) {
            return "GrandChild";
        }
        else {
            return "Great "+current;
        }
    }
    public String everyone() {
        String returnString="";
        for(Person x : family) {
            returnString+=x.getID()+") "+x.getName()+"\n";
        }
        return returnString;
    }
    public ArrayList<Integer> getChildrenIDs(int id) {
        //For this method you should use a loop.
        //Here you will iterate through the family arraylist
        //Looking for any direct children of the passed in id
        //i.e. any Person who has a parent1 or parent 2 that matches id

        //If you find any, add them to the List.  Be sure to avoid duplicates
        ArrayList<Integer> childrenIDs = new ArrayList<>();
        for (Person person : family) {
            if (person.getParent1() == id || person.getParent2() == id) {
                id = person.getID();
                if (!childrenIDs.contains(id)) {
                    childrenIDs.add(id);
                }
            }
        }
        return childrenIDs;
    }
    public int[] getParents(int ID) {
        //For this method you should use a loop.
        //Here you'll return an array of size 2, with the 2 parents you find.
        //You'll iterate over the family arraylist, looking for parents of ID
        int[] result = new int[2];
        for (Person person : family) {
            if (person.getID() == ID) {
                result[0] = person.getParent1();
                result[1] = person.getParent2();
                break;
            }
        }
        return result;
    }
    public void printParents(int childID,String relationship) {
        //This method must be recursive.
        //You'll be past a person ID and the word "Self"
        //If the ChildID is not -1 you'll
        //  * Print the relationship passed followed by a space followed by
        //    the name of the person who's ID is childID
        //  * Figure out the relationship of this the next parent generation.
        //  * Create an array of ints, and store the childID's parents in it.
        //  * Using recursion, find the first parents parents
        //  * Using recursion, find the second parents parents
        if (childID == -1) {
            return;
        }

        System.out.println(relationship + " " + getNameFromID(childID));
        int[] parents = getParents(childID);
        printParents(parents[0], parentRelationship(relationship));
        printParents(parents[1], parentRelationship(relationship));

        int[] result = new int[]{-1,-1};
        for (Person person : family) {
            if (person.getID() == childID) {
                result[0] = person.getParent1();
                result[1] = person.getParent2();
                break;
            }
        }
    }
    public void printChildren(int parentID,String relationship) {
        //This method must be recursive.
        //You'll be past a person ID and the word "Self"
        //If the parentID is not -1 you'll
        //  * Print the relationship passed followed by a space followed by
        //    the name of the person who's ID is parentID
        //  * Figure out the relationship of this the next childs generation.
        //  * Create a list of ints, and store the parentID's children in it.
        //  * Using recursion, recurse over each child
        if (parentID == -1) {
            return;
        }
        System.out.println(relationship + " " + getNameFromID(parentID));

        ArrayList<Integer> childrenIDs = getChildrenIDs(parentID);
        for (int childID : childrenIDs) {
            printChildren(childID, childRelationship(relationship));
        }
    }
}

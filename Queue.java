
public interface Queue <Item> {

    Item delete(); //Returns and Removes item from front of queueu
    void insert(Item item); //appends item to end of queueu
    boolean isEmpty();
    int size();

}
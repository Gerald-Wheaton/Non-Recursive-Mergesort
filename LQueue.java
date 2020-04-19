class LQueue <Item> implements Queue <Item>
{
  private Node front, rear;
  private int size; 
 
  //struct for Node
  private class Node
  { 
    Item item;
    Node next;
  }
 
  public LQueue()
  {
    front = null;
	rear = null;
    size = 0;
  }
 
  public boolean isEmpty()
  {
    return (size == 0);
  }
 
  //Removes item from front of list
  public Item delete()
  {
        Item item = front.item;
        front = front.next;
        if (isEmpty()) 
        {
        rear = null;
        }
        size--;

    return item;
  }
 
  //appends item to end of list
  public void insert(Item item)
  {
    Node oldRear = rear;
    rear = new Node();
    rear.item = item;
    rear.next = null;
    if (isEmpty()) 
    {
      front = rear;
    }
    else 
    {
      oldRear.next = rear;
    }
    size++;
  }
 
  public int size()
  {
    return size;
  }

}
package contactOrganizer;
public class ContactList {
    private Contact[] contactArray;
    private int nextIndex;
    private int initSize;
    private double loadFact;

    public ContactList(int initSize, double loadFact) {
        contactArray = new Contact[initSize];
        nextIndex = 0;
        this.initSize = initSize;
        this.loadFact = loadFact;
    }
    
    //NEW CONTACT
    public boolean add(Contact contact){
        if(isFull()){
            extendArray();
        }
        contactArray[nextIndex++]=contact;
        return true;
    }
    
    //CHECK ARRAY FULL?
    public boolean isFull(){
        return nextIndex>=contactArray.length;
    }
    
    //Extend Array
    public void extendArray(){
        Contact[] tempContactArray = new Contact[contactArray.length+(int)(contactArray.length*loadFact)];
        for (int i = 0; i < contactArray.length; i++) {
            tempContactArray[i]=contactArray[i];
        }
        contactArray=tempContactArray;
    }
    
    //Contact ID genarate
    public String genarateContactId(){
        if(nextIndex==0){
            return "C0001";
        }
        String lastContactId = contactArray[nextIndex-1].getContactId();
        int number = Integer.parseInt(lastContactId.substring(1));
        return String.format("%s%04d","C",(number+1));
    }
}

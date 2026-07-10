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
    
    //Search Contact
    public Object[][] searchContact(String name){
        int count = 0;
        for(int i=0; i<nextIndex; i++){
            if(contactArray[i].getName().equalsIgnoreCase(name)){
                count++;
            }
        }
        
        Object[][] deta = new Object[count][3];
        
        int row = 0;
        for (int i = 0; i < nextIndex; i++) {
            if(contactArray[i].getName().equalsIgnoreCase(name)){
                deta[row][0]=contactArray[i].getContactId();
                deta[row][1]=contactArray[i].getName();
                deta[row][2]=contactArray[i].getPhoneNumber();
                row++;
            }
        }
        return deta;
    }
    //get Contact
    public Contact getContact(String id) {
    for(int i = 0; i < nextIndex; i++) {
        if(contactArray[i].getContactId().equals(id)) {
            return contactArray[i];
        }
    }
        return null;
    }
}

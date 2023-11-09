
public class Hand extends CardCollection {
    
    public Hand() {
        super();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < cards.size(); i++) {
            str += " "+cards.get(i).toString();
        }
        return str;
    }
}

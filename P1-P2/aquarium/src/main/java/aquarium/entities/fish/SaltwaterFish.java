package aquarium.entities.fish;

public class SaltwaterFish extends BaseFish{
    //Can only live in SaltwaterAquarium!

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(5);
    }

    @Override
    public void eat() {
        super.setSize(getSize() + 2);
    }
}

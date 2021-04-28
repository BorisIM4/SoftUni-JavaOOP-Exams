package aquarium.core;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller{
    private DecorationRepository decorations;
    private Collection<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        if (!aquariumType.equals("FreshwaterAquarium") && !aquariumType.equals("SaltwaterAquarium")) {
            throw new NullPointerException(ExceptionMessages.INVALID_AQUARIUM_TYPE);
        }
        if ("FreshwaterAquarium".equals(aquariumType)) {
            Aquarium aquarium = new FreshwaterAquarium(aquariumName);
            this.aquariums.add(aquarium);
        } else {
            Aquarium aquarium = new SaltwaterAquarium(aquariumName);
            this.aquariums.add(aquarium);
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        if (!"Ornament".equals(type) && !"Plant".equals(type)) {
            throw new IllegalArgumentException (ExceptionMessages.INVALID_DECORATION_TYPE);
        }
        if ("Ornament".equals(type)) {
            Decoration decoration = new Ornament();
            this.decorations.add(decoration);
        } else {
            Decoration decoration = new Plant();
            this.decorations.add(decoration);
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_TYPE,type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration currentDecoration = this.decorations.findByType(decorationType);

        if (currentDecoration == null || !currentDecoration.getClass().getSimpleName().equals(decorationType)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_DECORATION_FOUND , decorationType));
        }

        for (Aquarium currentAquarium : this.aquariums) {
            String name = currentAquarium.getName();
            if (name.equals(aquariumName)) {
                currentAquarium.addDecoration(currentDecoration);
                this.decorations.remove(currentDecoration);
            }
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName );
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        if (!fishType.equals("FreshwaterFish") && !fishType.equals("SaltwaterFish")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_FISH_TYPE);
        }

        Fish fish;
        if (fishType.equals("FreshwaterFish")) {
            fish = new FreshwaterFish(fishName, fishSpecies, price);
        } else {
            fish = new SaltwaterFish(fishName, fishSpecies, price);
        }

        for (Aquarium currAqua : this.aquariums) {
            String nameCurrAqua = currAqua.getName();
            if (nameCurrAqua.equals(aquariumName)) {
                String simpleNameAquarium = currAqua.getClass().getSimpleName();
                if (simpleNameAquarium.equals("SaltwaterAquarium")  && fishType.equals("FreshwaterFish")) {
                    return ConstantMessages.WATER_NOT_SUITABLE;
                } else if (simpleNameAquarium.equals("FreshwaterAquarium")  && fishType.equals("SaltwaterFish")) {
                    return ConstantMessages.WATER_NOT_SUITABLE;
                }
                currAqua.addFish(fish);
                break;
            }
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        int numberOfFish = 0;

        for (Aquarium aquarium : this.aquariums) {
            if (aquarium.getName().equals(aquariumName)) {
                aquarium.feed();
                int size = aquarium.getFish().size();
                numberOfFish = size;
            }
        }

        return String.format(ConstantMessages.FISH_FED, numberOfFish);
    }

    @Override
    public String calculateValue(String aquariumName) {
        double decorPrice = 0;
        double fishPrice = 0;

        for (Aquarium aquarium : this.aquariums) {
            if (aquarium.getName().equals(aquariumName)) {
                Collection<Decoration> decorations = aquarium.getDecorations();
                for (Decoration decoration : decorations) {
                    double price = decoration.getPrice();
                    decorPrice += price;
                }

                Collection<Fish> fish = aquarium.getFish();
                for (Fish fish1 : fish) {
                    double price = fish1.getPrice();
                    fishPrice += price;
                }
            }
        }

        double sum = decorPrice + fishPrice;

        return String.format(ConstantMessages.VALUE_AQUARIUM, aquariumName,sum);
    }

    @Override
    public String report() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Aquarium aquarium : this.aquariums) {
            String info = aquarium.getInfo();
            stringBuilder.append(info).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}

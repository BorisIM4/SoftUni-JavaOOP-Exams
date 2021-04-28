package aquarium.entities.aquariums;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public abstract class BaseAquarium implements Aquarium{
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    protected BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        decorations = new ArrayList<>();
        fish = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int calculateComfort() {
        int sumOfDecoration = 0;

        for (Decoration decoration : this.decorations) {
            int currentComfort = decoration.getComfort();
            sumOfDecoration += currentComfort;
        }

        return sumOfDecoration;
    }

    @Override
    public void addFish(Fish fish) {
        if (this.capacity == 0) {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }
        this.fish.add(fish);
        this.capacity--;
    }

    @Override
    public void removeFish(Fish fish) {
        if (this.fish.contains(fish)) {
            this.fish.remove(fish);
            this.capacity++;
        }
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        for (Fish currentFish : this.fish) {
            currentFish.eat();
        }
    }

    @Override
    public String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("%s (%s):", getName(), this.getClass().getSimpleName())).append(System.lineSeparator())
                .append("Fish: ");
        if (getFish().isEmpty()) {
            stringBuilder.append("none");
        } else {
            String allFish = getFish().stream().map(Fish::getName).collect(Collectors.joining(" "));
            stringBuilder.append(allFish);
        }
        stringBuilder.append(System.lineSeparator())
                .append(String.format("Decorations: %d" ,getDecorations().size())).append(System.lineSeparator())
                .append(String.format("Comfort: %d", getDecorations().stream().mapToInt(Decoration::getComfort).sum()));


        return stringBuilder.toString().trim();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Fish> getFish() {
        return Collections.unmodifiableCollection(this.fish);
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return Collections.unmodifiableCollection(this.decorations);
    }
}

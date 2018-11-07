...
List<Animal> list = ...
Animal animal = null;
if (sound == null)
    return new MuteAnimal();
else if (soundsTrumpet(sound))
    animal = new Elephant();
else if (soundsNeigh(sound))
    animal = new Horse();
else if (soundsHiss(sound))
    animal = new Snake();
else if (soundsBuzz(sound))
    animal = new Bee();
else
    animal = new Mystery();
list.add(animal);
...
